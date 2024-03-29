package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.entity.MoviePage;
import com.lsy.my_movie_recommendation_system.mapper.IMovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovieService implements IMovieService{

    IMovieMapper movieMapper;

    @Autowired
    public MovieService(IMovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Override
    public List<Movie> selectAllMovie() {
        return movieMapper.selectAllMovie();
    }

    @Override
    public MoviePage selectMovieByPage(Integer pageIndex, Integer pageSize) {
        List<Movie> movieList = movieMapper.selectMovieByPage(pageIndex * pageSize, pageSize);
        Integer movieCount = movieMapper.selectMovieCount();
        MoviePage moviePage = new MoviePage();
        moviePage.setMovieList(movieList);
        moviePage.setPageIndex(pageIndex);
        moviePage.setPageSize(pageSize);
        // 上取整数
        moviePage.setPageCount((movieCount + pageSize - 1) / pageSize);
        return moviePage;
    }

    @Override
    public Movie selectMovieByID(Integer id) {
        return movieMapper.selectMovieByID(id);
    }

    @Override
    public List<Movie> selectMovieByName(String name) {
        return movieMapper.selectMovieByName(name);
    }

    @Override
    public List<Movie> selectMovieByLikelyName(String namePrefix) {
        return movieMapper.selectMovieByLikelyName(namePrefix + "%");
    }

    @Override
    public MoviePage selectMovieByLikelyNameAndPage(String namePrefix, Integer pageIndex, Integer pageSize) {
        List<Movie> movieList = movieMapper.selectMovieByLikelyNameAndPage(namePrefix + "%", pageIndex * pageSize, pageSize);
        Integer movieCount = movieMapper.selectMovieCountWithLikelyName(namePrefix + "%");
        MoviePage moviePage = new MoviePage();
        moviePage.setMovieList(movieList);
        moviePage.setPageIndex(pageIndex);
        moviePage.setPageSize(pageSize);
        // 上取整数
        moviePage.setPageCount((movieCount + pageSize - 1) / pageSize);
        return moviePage;
    }

    @Override
    public List<Movie> selectMovieByLikelyNameAndLimitN(String namePrefix, Integer pageSize) {
        return movieMapper.selectMovieByLikelyNameAndLimitN(namePrefix + "%", pageSize);
    }

    @Override
    public List<Movie> movieRecommend(Integer userId, Integer pageIndex, Integer pageSize) {

        Random randomEngine = new Random(userId + pageIndex);

        int movieLimit = 5000;

        List<Movie> result = new ArrayList<>();

        while (result.size() < pageSize) {
            List<Integer> idList = new ArrayList<>();
            for(int i = 0;i < pageSize;++i) {
                int id = randomEngine.nextInt() % movieLimit;
                if (id < 0) {
                    id += movieLimit;
                }
                idList.add(id);
            }
            List<Movie> movieList = movieMapper.selectMovieByIdList(idList);
            result.addAll(movieList);
        }

        while (result.size() > pageSize) {
            result.remove(result.size() - 1);
        }

        return result;
    }

    @Override
    public List<Movie> showMovieByPageOrderByRecordCountAndAvgRating(Integer pageIndex, Integer pageSize) {
        return movieMapper.showMovieByPageOrderByRecordCountAndAvgRating(pageIndex * pageSize, pageSize);
    }

}
