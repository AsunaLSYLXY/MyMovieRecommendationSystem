package com.lsy.my_movie_recommendation_system.entity;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String password;
    String phone;
    String mood;
}
