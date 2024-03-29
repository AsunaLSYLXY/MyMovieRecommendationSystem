package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.entity.MoviePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMovieService {
    List<Movie> selectAllMovie();
    MoviePage selectMovieByPage(Integer pageIndex, Integer pageSize);
    Movie selectMovieByID(Integer id);
    List<Movie> selectMovieByName(String name);
    List<Movie> selectMovieByLikelyName(String namePrefix);
    MoviePage selectMovieByLikelyNameAndPage(String namePrefix, Integer pageIndex, Integer pageSize);
    List<Movie> selectMovieByLikelyNameAndLimitN(String namePrefix, Integer pageSize);
    List<Movie> movieRecommend(Integer userId, Integer pageIndex, Integer pageSize);
    List<Movie> showMovieByPageOrderByRecordCountAndAvgRating(Integer pageIndex, Integer pageSize);
}
