package com.lsy.my_movie_recommendation_system.entity.dto;

import lombok.Data;

@Data
public class MovieRatingRecordDetail {
    Integer movieId;
    Double avgScore;
}
