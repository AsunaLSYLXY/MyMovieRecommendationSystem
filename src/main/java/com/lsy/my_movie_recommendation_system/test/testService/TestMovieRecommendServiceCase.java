package com.lsy.my_movie_recommendation_system.test.testService;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.service.MovieRecommendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMovieRecommendServiceCase {
    @Autowired
    MovieRecommendService movieRecommendService;

    @Test
    public void testRecommendMovieByPageOrderByRecordCountAndRatingScore() {
        Integer pageIndex = 2;
        Integer pageSize = 12;
        List<Movie> movieList = movieRecommendService.recommendMovieByPageOrderByRecordCountAndRatingScore(pageIndex, pageSize);
        System.out.println(movieList);
    }

    @Test
    public void testRecommendMovieByPageByUserCf() {
        Integer userId = 119;
        Integer pageIndex = 0;
        Integer pageSize = 36;
        Integer minCommendRecordInNeedForUser = 20;
        Integer minCommendRecordInNeedForMovie = 5;
        List<Movie> movieList = movieRecommendService.recommendMovieByPageByUserCf(userId, pageIndex, pageSize, minCommendRecordInNeedForUser, minCommendRecordInNeedForMovie);
        System.out.println(movieList);
    }
}
