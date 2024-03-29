package com.lsy.my_movie_recommendation_system.mapper;

import com.lsy.my_movie_recommendation_system.entity.po.MovieRatingRecordForCompare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMovieRatingRecordMapper {
    @Mapper
    List<MovieRatingRecordForCompare> selectAllRecord();
    @Mapper
    MovieRatingRecordForCompare selectRecordByRecordId(@Param("recordId") Integer recordId);
    @Mapper
    List<MovieRatingRecordForCompare> selectRecordByUserId(@Param("userId") Integer userId);
    @Mapper
    List<MovieRatingRecordForCompare> selectRecordByMovieId(@Param("movieId") Integer movieId);
}
