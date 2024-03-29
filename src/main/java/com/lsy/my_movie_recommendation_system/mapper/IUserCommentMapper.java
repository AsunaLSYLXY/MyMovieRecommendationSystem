package com.lsy.my_movie_recommendation_system.mapper;

import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserCommentMapper {
    @Mapper
    List<UserComment> selectAllComment();
    @Mapper
    UserComment selectCommentByRecordId(@Param("recordId") Integer recordId);
    @Mapper
    List<UserComment> selectCommentByUserId(@Param("userId") Integer userId);
    @Mapper
    List<UserComment> selectCommentByMovieId(@Param("movieId") Integer movieId);
    @Mapper
    UserComment selectCommentByUserIdAndMovieId(@Param("userId") Integer userId, @Param("movieId") Integer movieId);
    @Mapper
    Boolean updateCommentByRecordId(@Param("recordId") Integer recordId, @Param("score") Double score, @Param("comment") String comment, @Param("timestamp") Long timestamp);
    @Mapper
    void addComment(UserComment userComment);
    @Mapper
    Boolean removeCommentByRecordId(@Param("recordId") Integer recordId);
}
