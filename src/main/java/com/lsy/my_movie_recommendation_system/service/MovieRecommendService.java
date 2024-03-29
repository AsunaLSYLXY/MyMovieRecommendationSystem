package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.entity.po.MovieRatingRecordForCompare;
import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import com.lsy.my_movie_recommendation_system.mapper.IMovieMapper;
import com.lsy.my_movie_recommendation_system.mapper.IMovieRatingRecordMapper;
import com.lsy.my_movie_recommendation_system.mapper.IUserCommentMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieRecommendService implements IMovieRecommendService{

    @Data
    private static class RecommendItem {
        Integer movieId;   // 电影编号
        Double score;      // 分数
    }

    @Data
    private static class UserScoreItem {
        Integer userId;   // 用户编号
        Double score;     // 分数
    }

    IMovieMapper movieMapper;
    IUserCommentMapper userCommentMapper;
    IMovieRatingRecordMapper ratingRecordMapper;

    @Autowired
    MovieRecommendService(IMovieMapper movieMapper, IUserCommentMapper userCommentMapper, IMovieRatingRecordMapper ratingRecordMapper) {
        this.movieMapper = movieMapper;
        this.userCommentMapper = userCommentMapper;
        this.ratingRecordMapper = ratingRecordMapper;
    }

    @Override
    public List<Movie> recommendMovieByPageOrderByRecordCountAndRatingScore(Integer pageIndex, Integer pageSize) {
        return movieMapper.showMovieByPageOrderByRecordCountAndAvgRating(pageIndex * pageSize, pageSize);
    }

    @Override
    public List<Movie> recommendMovieByPageByUserCf(Integer userId, Integer pageIndex, Integer pageSize, Integer minCommendRecordInNeedForUser, Integer minCommendRecordInNeedForMovie) {
        List<UserComment> userCommentList = userCommentMapper.selectCommentByUserId(userId);
        // todo 判断是否可以使用 user cf 推荐
        // 判断评论是否过少
        if (userCommentList.size() < minCommendRecordInNeedForUser) {
            return movieMapper.showMovieByPageOrderByRecordCountAndAvgRating(pageIndex * pageSize, pageSize);
        }
        // 判断用户打分是不是都打的一样
        boolean scoreIsAllSame = true;
        for(int i = 1;i < userCommentList.size();++i) {
            if (!userCommentList.get(i).getScore().equals(userCommentList.get(0).getScore())) {
                scoreIsAllSame = false;
                break;
            }
        }
        if (scoreIsAllSame) {
            return movieMapper.showMovieByPageOrderByRecordCountAndAvgRating(pageIndex * pageSize, pageSize);
        }
        // todo 计算用户相似度
        // 计算平均值
        double userAvgScore = 0;
        for(UserComment comment : userCommentList) {
            userAvgScore += comment.getScore();
        }
        userAvgScore /= userCommentList.size();
        // 每部电影最终评分 = 原始评分 - 用户评价评分
        HashMap<Integer, Double> userScoreTable = new HashMap<>();
        for(UserComment comment : userCommentList) {
            userScoreTable.put(comment.getMovieId(), comment.getScore() - userAvgScore);
        }
        // 参与推荐计算的数据集用户, 经过检验，我们发现原先数据集已经实现了按照 userId 升序排列
        List<MovieRatingRecordForCompare> ratingRecordList = ratingRecordMapper.selectAllRecord();
        // 用户相似度(采用皮尔森系数)
        HashMap<Integer, Double> similarityTable = new HashMap<>();
        // 开始逐个录入
        for(int i = 0;i < ratingRecordList.size();++i) {
            HashMap<Integer, Double> ratingTable = new HashMap<>();
            double avg = 0;   // 平均打分
            int j;
            Integer personId = ratingRecordList.get(i).getUserId();
            for(j = i;j < ratingRecordList.size();++j) {
                if (ratingRecordList.get(j).getUserId().equals(personId)) {
                    Integer movieId = ratingRecordList.get(j).getMovieId();
                    Double score = ratingRecordList.get(j).getRating();
                    ratingTable.put(movieId, score);
                    avg += score;
                } else {
                    break;
                }
            }
            avg /= ratingTable.size();
            for(Map.Entry<Integer, Double> kv : ratingTable.entrySet()) {
                Integer key = kv.getKey();
                Double value = kv.getValue();
                // 每部电影最终评分 = 原始评分 - 用户评价评分
                ratingTable.put(key, value - avg);
            }
            // 计算皮尔森系数
            double vDot = 0;       // 点积之和(分子部分)
            double vUsr2 = 0;      // 用户的平方和(分母部分)
            double vRecord2 = 0;   // 记录里面用户的平方和(分母部分)
            if (userScoreTable.size() <= ratingTable.size()) {
                for(Map.Entry<Integer, Double> kv : userScoreTable.entrySet()) {
                    Integer key = kv.getKey();
                    Double value1 = kv.getValue();
                    if (ratingTable.containsKey(key)) {
                        Double value2 = ratingTable.get(key);
                        vDot += value1 * value2;
                        // 注意看清楚 value1 是用户的, value2 是记录的
                        vUsr2 += value1 * value1;
                        vRecord2 += value2 * value2;
                    }
                }
            } else {
                for(Map.Entry<Integer, Double> kv : ratingTable.entrySet()) {
                    Integer key = kv.getKey();
                    Double value1 = kv.getValue();
                    if (userScoreTable.containsKey(key)) {
                        Double value2 = userScoreTable.get(key);
                        vDot += value1 * value2;
                        // 注意看 value1 是记录的, value2 是用户的
                        vUsr2 += value2 * value2;
                        vRecord2 += value1 * value1;
                    }
                }
            }
            // 因为 vUser2 是平方和每一个加进来的都 >= 0, 所以只要 > 0, 说明就有
            if (vUsr2 > 0 && vRecord2 > 0) {
                // 相似度
                Double similarity = vDot / Math.sqrt(vUsr2) / Math.sqrt(vRecord2);
                // 计算完成后要加入列表
                similarityTable.put(personId, similarity);
                i = j - 1;   // 最后一个满足的 userId 相等的, 下一次就跳到第一个不相等的
            }
        }
        // todo 统计每一个电影有多少用户参与过评分
        HashMap<Integer, List<UserScoreItem>> recordSet = new HashMap<>();
        for (MovieRatingRecordForCompare record : ratingRecordList) {
            Integer mId = record.getMovieId();
            Integer uId = record.getUserId();
            Double score = record.getRating();
            UserScoreItem scoreItem = new UserScoreItem();
            scoreItem.setUserId(uId);
            scoreItem.setScore(score);
            List<UserScoreItem> userScoreItemList;
            if (recordSet.containsKey(mId)) {
                userScoreItemList = recordSet.get(mId);
            } else {
                userScoreItemList = new ArrayList<>();
            }
            userScoreItemList.add(scoreItem);
            recordSet.put(mId, userScoreItemList);
        }
        // todo 给没有进行评分的物品进行评分
        List<Movie> movieList = movieMapper.selectAllMovie();
        List<RecommendItem> recommendList = new ArrayList<>();
        for (Movie movie : movieList) {
            Integer mId = movie.getId();
            if (!userScoreTable.containsKey(mId) && recordSet.containsKey(mId)) {
                double sumWeight = 0;
                double sumScore = 0;
                List<UserScoreItem> userScoreItemList = recordSet.get(mId);
                if (userScoreItemList.size() < minCommendRecordInNeedForMovie) {
                    continue;
                }
                // 权重约定为 相似度 -> [0, 1] 的线性映射
                for (UserScoreItem userScoreItem : userScoreItemList) {
                    Integer uId = userScoreItem.getUserId();
                    Double score = userScoreItem.getScore();
                    if (similarityTable.containsKey(uId)) {
                        double weight = similarityTable.get(uId);
                        // x 属于 [-1, 1] -> x / 2 + 0.5 -> [0, 1]
                        weight = (weight / 2) + 0.5;
                        sumWeight += weight;
                        sumScore += weight * score;
                    }
                }
                if (sumWeight != 0) {
                    // 平均分数
                    double avgScore = sumScore / sumWeight;
                    RecommendItem recommendItem = new RecommendItem();
                    recommendItem.setMovieId(mId);
                    recommendItem.setScore(avgScore);
                    recommendList.add(recommendItem);
                }
            }
        }
        for(RecommendItem recommendItem : recommendList) {
            if (recommendItem.getScore().isNaN()) {
                System.out.println(recommendItem);
            }
        }
        // todo 排序并给出结果
        // 使用 Comparator 接口定义一个比较器
        Comparator<RecommendItem> scoreComparator = (item1, item2) -> {
            // 降序排列
            return Double.compare(item2.score, item1.score);
        };
        recommendList.sort(scoreComparator);
        // todo 切片给出答案
        HashMap<Integer, Movie> movieIdMap = new HashMap<>();
        for(Movie movie : movieList) {
            movieIdMap.put(movie.getId(), movie);
        }
        List<Movie> result = new ArrayList<>();
        for(int i = pageIndex * pageSize;i < pageIndex * pageSize + pageSize && i < recommendList.size();++i) {
            Integer movieId = recommendList.get(i).getMovieId();
            if (movieIdMap.containsKey(movieId)) {
                Movie movie = movieIdMap.get(movieId);
                result.add(movie);
            }
        }
        return result;
    }

}

