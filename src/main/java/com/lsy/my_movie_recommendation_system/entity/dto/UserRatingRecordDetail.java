package com.lsy.my_movie_recommendation_system.entity.dto;

import lombok.Data;

@Data
public class UserRatingRecordDetail {
    Integer userId;
    Double avgScore;
}
