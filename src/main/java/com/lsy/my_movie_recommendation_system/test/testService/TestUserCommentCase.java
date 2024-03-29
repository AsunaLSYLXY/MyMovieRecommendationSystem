package com.lsy.my_movie_recommendation_system.test.testService;

import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import com.lsy.my_movie_recommendation_system.service.UserCommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserCommentCase {
    @Autowired
    UserCommentService userCommentService;

    @Test
    public void testAddComment() {
        UserComment userComment = new UserComment();
        userComment.setUserId(119);
        userComment.setMovieId(25);
        userComment.setScore(5.0);
        userComment.setComment("见到歌歌好开心");
        userComment.setTimestamp(System.currentTimeMillis());
        Boolean ok = userCommentService.addComment(userComment);
        System.out.println(ok);
        System.out.println(userComment);
    }

    @Test
    public void testSelectAllComment() {
        List<UserComment> commentList =  userCommentService.selectAllComment();
        System.out.println(commentList);
    }

    @Test
    public void testUpdateCommentByRecordId() {
        Integer recordId = 21;
        Double score = 4.0;
        String comment = "善哉善哉";
        Boolean ok = userCommentService.updateCommentByRecordId(recordId, score, comment, System.currentTimeMillis());
        System.out.println(ok);
    }

    @Test
    public void testRemoveCommentByRecordId() {
        Integer recordId = 19;
        Boolean ok = userCommentService.removeCommentByRecordId(recordId);
        System.out.println(ok);
    }

    @Test
    public void testSelectCommentByRecordId() {
        Integer recordId = 10;
        UserComment comment = userCommentService.selectCommentByRecordId(recordId);
        System.out.println(comment);
    }

    @Test
    public void testSelectCommentByUserId() {
        Integer userId = 119;
        List<UserComment> commentList = userCommentService.selectCommentByUserId(userId);
        System.out.println(commentList);
    }

    @Test
    public void testSelectCommentByMovieId() {
        Integer movieId = 11;
        List<UserComment> commentList = userCommentService.selectCommentByMovieId(movieId);
        System.out.println(commentList);
    }

    @Test
    public void testSelectCommentByUserIdAndMovieId() {
        Integer userId = 119;
        Integer movieId = 7;
        UserComment comment = userCommentService.selectCommentByUserIdAndMovieId(userId, movieId);
        System.out.println(comment);
    }
}
