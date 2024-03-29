package com.lsy.my_movie_recommendation_system.test.testIMapper;

import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.mapper.IMovieMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIMovieCase {
    @Autowired
    IMovieMapper movieMapper;

    @Test
    public void testSelectAllMovie() {
        List<Movie> movieList = movieMapper.selectAllMovie();
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testSelectMovieByPage() {
        Integer pageOffset = 100;
        Integer pageSize = 10;
        List<Movie> movieList = movieMapper.selectMovieByPage(pageOffset, pageSize);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testSelectMovieByID() {
        Integer pageIndex = 116;
        Movie movie = movieMapper.selectMovieByID(pageIndex);
        System.out.println(movie);
    }

    @Test
    public void testSelectMovieByName() {
        String name = "Saturn 3 (1980)";
        List<Movie> movieList = movieMapper.selectMovieByName(name);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testSelectMovieByLikelyName() {
        String name = "Sa%";
        List<Movie> movieList = movieMapper.selectMovieByLikelyName(name);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testSelectMovieCount() {
        Integer movieCount = movieMapper.selectMovieCount();
        System.out.println(movieCount);
    }

    @Test
    public void testSelectMovieByLikelyNameAndPage() {
        String name = "Sa%";
        Integer pageIndex = 1;
        Integer pageSize = 12;
        List<Movie> movieList = movieMapper.selectMovieByLikelyNameAndPage(name, pageIndex, pageSize);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testSelectMovieByLikelyNameAndLimitN() {
        String name = "Sa%";
        Integer pageSize = 12;
        List<Movie> movieList = movieMapper.selectMovieByLikelyNameAndLimitN(name, pageSize);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void testSelectMovieCountWithLikelyName() {
        String namePattern = "Sa%";
        Integer movieCount = movieMapper.selectMovieCountWithLikelyName(namePattern);
        System.out.println(movieCount);
    }

    @Test
    public void testSelectMovieByIdList() {
        List<Integer> idList = new ArrayList<>();
        for(int i = 1;i < 10;++i) {
            idList.add(i);
        }
        List<Movie> movieList = movieMapper.selectMovieByIdList(idList);
        System.out.println(movieList);
    }

    @Test
    public void testShowMovieByPageOrderByRecordCountAndAvgRating() {
        Integer pageOffset = 20;
        Integer pageSize = 10;
        List<Movie> movieList = movieMapper.showMovieByPageOrderByRecordCountAndAvgRating(pageOffset, pageSize);
        System.out.println(movieList);
    }
}
