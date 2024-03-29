package com.lsy.my_movie_recommendation_system.entity.po;

import lombok.Data;

@Data
public class UserComment {
    Integer recordId;
    Integer userId;
    Integer movieId;
    Double score;
    String comment;
    Long timestamp;
}
