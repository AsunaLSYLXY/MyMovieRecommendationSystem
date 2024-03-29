package com.lsy.my_movie_recommendation_system.mapper;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMovieMapper {
    @Mapper
    List<Movie> selectAllMovie();
    @Mapper
    Integer selectMovieCount();
    @Mapper
    List<Movie> selectMovieByPage(@Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);
    @Mapper
    Movie selectMovieByID(@Param("id") Integer id);
    @Mapper
    List<Movie> selectMovieByName(@Param("name") String name);
    @Mapper
    List<Movie> selectMovieByLikelyName(@Param("namePattern") String namePattern);
    @Mapper
    List<Movie> selectMovieByLikelyNameAndPage(@Param("namePattern") String namePattern, @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);
    @Mapper
    List<Movie> selectMovieByLikelyNameAndLimitN(@Param("namePattern") String namePattern, @Param("pageSize") Integer pageSize);
    @Mapper
    Integer selectMovieCountWithLikelyName(@Param("namePattern") String namePattern);
    @Mapper
    List<Movie> selectMovieByIdList(@Param("idList") List<Integer> idList);
    @Mapper
    List<Movie> showMovieByPageOrderByRecordCountAndAvgRating(@Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);
}
