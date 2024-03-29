package com.lsy.my_movie_recommendation_system.entity;

import lombok.Data;

import java.util.List;

@Data
public class MoviePage {
    Integer pageIndex;
    Integer pageSize;
    Integer pageCount;
    List<Movie> movieList;
}
