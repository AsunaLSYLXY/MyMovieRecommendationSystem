package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.User;
import com.lsy.my_movie_recommendation_system.entity.UserBasicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService {
    List<User> selectAllUserAllInfo();
    List<UserBasicInfo> selectAllUserBasicInfo();
    User selectUserAllInfoByUserID(Integer id);
    UserBasicInfo selectUserBasicInfoByID(Integer id);
    UserBasicInfo selectUserBasicInfoByPhone(String phone);
    Boolean queryUserExistsByID(Integer id);
    String selectUserPasswordByID(Integer id);
    String selectUserPasswordByPhone(String phone);
    Boolean updateUserNameByID(Integer id, String name);
    Boolean updateUserPasswordByID(Integer id, String password);
    Boolean updateUserPhoneByID(Integer id, String phone);
    Boolean updateUserMoodByID(Integer id, String mood);
    void addUser(User user);
    Boolean removeUserByID(Integer id);
    Boolean queryUserExistsByPhone(String phone);
    User selectUserAllInfoByPhone(String phone);
    Boolean updateUserInfo(User user);
}
