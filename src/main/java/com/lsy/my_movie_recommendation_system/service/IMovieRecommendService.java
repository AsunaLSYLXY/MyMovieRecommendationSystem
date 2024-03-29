package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.Movie;

import java.util.List;

public interface IMovieRecommendService {
    /**
     * 分页推荐电影, 按照评论数量, 电影评分进行推荐
     * @param pageIndex 分页所对应的页数
     * @param pageSize 分页大小
     * @return 推荐的电影列表
     */
    List<Movie> recommendMovieByPageOrderByRecordCountAndRatingScore(Integer pageIndex, Integer pageSize);
    /**
     * 分页推荐电影, 正常情况下, 按照 User Cf (用户协同过滤) 算法实现, 如果用户评论过少, 则按照评论数量, 电影评分进行推荐
     * @param userId 用户 id
     * @param pageIndex 分页所对应的页数
     * @param pageSize 分页大小
     * @param minCommendRecordInNeedForUser 用户最少需要多少条评分记录才能采用这种方式推荐
     * @param minCommendRecordInNeedForMovie 电影最少需要打分多少次才能进入推荐部分
     * @return 推荐的电影列表
     */
    List<Movie> recommendMovieByPageByUserCf(Integer userId, Integer pageIndex, Integer pageSize, Integer minCommendRecordInNeedForUser, Integer minCommendRecordInNeedForMovie);
}
