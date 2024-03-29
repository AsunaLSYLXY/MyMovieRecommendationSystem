package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import com.lsy.my_movie_recommendation_system.mapper.IUserCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentService implements IUserCommentService{

    IUserCommentMapper userCommentMapper;

    @Autowired
    public UserCommentService(IUserCommentMapper userCommentMapper) {
        this.userCommentMapper = userCommentMapper;
    }

    @Override
    public List<UserComment> selectAllComment() {
        return userCommentMapper.selectAllComment();
    }

    @Override
    public UserComment selectCommentByRecordId(Integer recordId) {
        return userCommentMapper.selectCommentByRecordId(recordId);
    }

    @Override
    public List<UserComment> selectCommentByUserId(Integer userId) {
        return userCommentMapper.selectCommentByUserId(userId);
    }

    @Override
    public List<UserComment> selectCommentByMovieId(Integer movieId) {
        return userCommentMapper.selectCommentByMovieId(movieId);
    }

    @Override
    public UserComment selectCommentByUserIdAndMovieId(Integer userId, Integer movieId) {
        return userCommentMapper.selectCommentByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public Boolean updateCommentByRecordId(Integer recordId, Double score, String comment, Long timestamp) {
        return userCommentMapper.updateCommentByRecordId(recordId, score, comment, timestamp);
    }

    @Override
    public Boolean addComment(UserComment userComment) {
        try {
            userCommentMapper.addComment(userComment);
            return true;
        } catch (Exception e) {
            System.out.println("加入评论异常");
            return false;
        }
    }

    @Override
    public Boolean removeCommentByRecordId(Integer recordId) {
        return userCommentMapper.removeCommentByRecordId(recordId);
    }
}
