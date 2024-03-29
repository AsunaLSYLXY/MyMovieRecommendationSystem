package com.lsy.my_movie_recommendation_system.entity.po;

import lombok.Data;

@Data
public class MovieRatingRecordForCompare {
    Integer recordId;
    Integer userId;
    Integer movieId;
    Double rating;
}
