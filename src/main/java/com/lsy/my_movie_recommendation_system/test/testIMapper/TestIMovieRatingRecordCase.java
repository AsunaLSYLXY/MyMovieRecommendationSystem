package com.lsy.my_movie_recommendation_system.test.testIMapper;

import com.lsy.my_movie_recommendation_system.entity.po.MovieRatingRecordForCompare;
import com.lsy.my_movie_recommendation_system.mapper.IMovieRatingRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIMovieRatingRecordCase {
    @Autowired
    IMovieRatingRecordMapper movieRatingRecordMapper;

    @Test
    public void testSelectAllRecord() {
        List<MovieRatingRecordForCompare> recordList = movieRatingRecordMapper.selectAllRecord();
        System.out.println(recordList);
    }

    @Test
    public void testSelectRecordByRecordId() {
        Integer recordId = 100;
        MovieRatingRecordForCompare record = movieRatingRecordMapper.selectRecordByRecordId(recordId);
        System.out.println(record);
    }

    @Test
    public void testSelectRecordByUserId() {
        Integer userId = 110;
        List<MovieRatingRecordForCompare> recordList = movieRatingRecordMapper.selectRecordByUserId(userId);
        System.out.println(recordList);
    }

    @Test
    public void testSelectRecordByMovieId() {
        Integer movieId = 50;
        List<MovieRatingRecordForCompare> recordList = movieRatingRecordMapper.selectRecordByMovieId(movieId);
        System.out.println(recordList);
    }
}
