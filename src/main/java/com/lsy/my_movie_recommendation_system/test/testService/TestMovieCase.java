package com.lsy.my_movie_recommendation_system.test.testService;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.entity.MoviePage;
import com.lsy.my_movie_recommendation_system.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMovieCase {

    @Autowired
    MovieService movieService;

    @Test
    public void testSelectAllMovie() {
        List<Movie> movieList = movieService.selectAllMovie();
        System.out.println(movieList);
    }

    @Test
    public void testSelectMovieByPage() {
        Integer pageIndex = 20;
        Integer pageSize = 10;
        MoviePage moviePage = movieService.selectMovieByPage(pageIndex, pageSize);
        System.out.println(moviePage);
    }

    @Test
    public void testSelectMovieByID() {
        Integer movieID = 20;
        Movie movie = movieService.selectMovieByID(movieID);
        System.out.println(movie);
    }

    @Test
    public void testSelectMovieByName() {
        String name = "War of the Worlds (2005)";
        List<Movie> movieList = movieService.selectMovieByName(name);
        System.out.println(movieList);
    }

    @Test
    public void testSelectMovieByLikelyName() {
        String namePrefix = "War";
        List<Movie> movieList = movieService.selectMovieByLikelyName(namePrefix);
        System.out.println(movieList);
    }

    @Test
    public void testSelectMovieByLikelyNameAndPage() {
        String namePrefix = "Sa";
        Integer pageIndex = 1;
        Integer pageSize = 12;
        MoviePage moviePage = movieService.selectMovieByLikelyNameAndPage(namePrefix, pageIndex, pageSize);
        System.out.println(moviePage);
    }

    @Test
    public void testSelectMovieByLikelyNameAndLimitN() {
        String namePrefix = "Sa";
        Integer pageSize = 12;
        List<Movie> movieList = movieService.selectMovieByLikelyNameAndLimitN(namePrefix, pageSize);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testMovieRecommend() {
        Integer userId = 119;
        Integer pageIndex = 1;
        Integer pageSize = 12;
        List<Movie> movieList = movieService.movieRecommend(userId, pageIndex, pageSize);
        System.out.println(movieList);
    }

    @Test
    public void testShowMovieByPageOrderByRecordCountAndAvgRating() {
        Integer pageIndex = 2;
        Integer pageSize = 10;
        List<Movie> movieList = movieService.showMovieByPageOrderByRecordCountAndAvgRating(pageIndex, pageSize);
        System.out.println(movieList);
    }
}
