package com.lsy.my_movie_recommendation_system.test.testIMapper;

import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import com.lsy.my_movie_recommendation_system.mapper.IUserCommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIUserCommentCase {
    @Autowired
    IUserCommentMapper userCommentMapper;

    @Test
    public void testAddComment() {
        UserComment userComment = new UserComment();
        userComment.setUserId(119);
        userComment.setMovieId(23);
        userComment.setScore(4.5);
        userComment.setComment("歌歌的演技太棒啦");
        userComment.setTimestamp(System.currentTimeMillis());
        userCommentMapper.addComment(userComment);
        System.out.println(userComment);
    }

    @Test
    public void testSelectAllComment() {
        List<UserComment> commentList =  userCommentMapper.selectAllComment();
        System.out.println(commentList);
    }

    @Test
    public void testUpdateCommentByRecordId() {
        Integer recordId = 3;
        Double score = 4.0;
        String comment = "我要吃电影";
        Boolean ok = userCommentMapper.updateCommentByRecordId(recordId, score, comment, System.currentTimeMillis());
        System.out.println(ok);
    }

    @Test
    public void testRemoveCommentByRecordId() {
        Integer recordId = 23;
        Boolean ok = userCommentMapper.removeCommentByRecordId(recordId);
        System.out.println(ok);
    }

    @Test
    public void testSelectCommentByRecordId() {
        Integer recordId = 23;
        UserComment comment = userCommentMapper.selectCommentByRecordId(recordId);
        System.out.println(comment);
    }

    @Test
    public void testSelectCommentByUserId() {
        Integer userId = 119;
        List<UserComment> commentList = userCommentMapper.selectCommentByUserId(userId);
        System.out.println(commentList);
    }

    @Test
    public void testSelectCommentByMovieId() {
        Integer movieId = 11;
        List<UserComment> commentList = userCommentMapper.selectCommentByMovieId(movieId);
        System.out.println(commentList);
    }

    @Test
    public void testSelectCommentByUserIdAndMovieId() {
        Integer userId = 119;
        Integer movieId = 13;
        UserComment comment = userCommentMapper.selectCommentByUserIdAndMovieId(userId, movieId);
        System.out.println(comment);
    }
}
