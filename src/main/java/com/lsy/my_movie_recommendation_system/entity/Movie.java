package com.lsy.my_movie_recommendation_system.entity;

import lombok.Data;

@Data
public class Movie {
    Integer id;
    String name;
    Double runtime;
    String tags;
    String description;
    String directors;
    String writers;
    String starts;
    String releaseTime;
    String country;
    String type;
    String localUrl;
}
