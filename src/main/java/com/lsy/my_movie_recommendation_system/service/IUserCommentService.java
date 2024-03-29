package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserCommentService {
    List<UserComment> selectAllComment();
    UserComment selectCommentByRecordId(Integer recordId);
    List<UserComment> selectCommentByUserId(Integer userId);
    List<UserComment> selectCommentByMovieId(Integer movieId);
    UserComment selectCommentByUserIdAndMovieId(Integer userId, Integer movieId);
    Boolean updateCommentByRecordId(Integer recordId, Double score, String comment, Long timestamp);
    Boolean addComment(UserComment userComment);
    Boolean removeCommentByRecordId(@Param("recordId") Integer recordId);
}
