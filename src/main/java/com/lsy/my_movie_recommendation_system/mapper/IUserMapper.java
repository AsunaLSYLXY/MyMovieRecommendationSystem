package com.lsy.my_movie_recommendation_system.mapper;

import com.lsy.my_movie_recommendation_system.entity.User;
import com.lsy.my_movie_recommendation_system.entity.UserBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserMapper {
    @Mapper
    List<User> selectAllUserAllInfo();
    @Mapper
    List<UserBasicInfo> selectAllUserBasicInfo();
    @Mapper
    User selectUserAllInfoByUserID(@Param("id") Integer id);
    @Mapper
    User selectUserAllInfoByPhone(@Param("phone") String phone);
    @Mapper
    UserBasicInfo selectUserBasicInfoByID(@Param("id") Integer id);
    @Mapper
    UserBasicInfo selectUserBasicInfoByPhone(@Param("phone") String phone);
    @Mapper
    Boolean queryUserExistsByPhone(@Param("phone") String phone);
    @Mapper
    Boolean queryUserExistsByID(@Param("id") Integer id);
    @Mapper
    String selectUserPasswordByID(@Param("id") Integer id);
    @Mapper
    String selectUserPasswordByPhone(@Param("phone") String phone);
    @Mapper
    String selectUserPasswordByUserName(@Param("name") String name);
    @Mapper
    Boolean updateUserNameByID(@Param("id") Integer id, @Param("name") String name);
    @Mapper
    Boolean updateUserPasswordByID(@Param("id") Integer id, @Param("password") String password);
    @Mapper
    Boolean updateUserPhoneByID(@Param("id") Integer id, @Param("phone") String phone);
    @Mapper
    Boolean updateUserMoodByID(@Param("id") Integer id, @Param("mood") String mood);
    @Mapper
    void addUser(User user);
    @Mapper
    Boolean removeUserByID(@Param("id") Integer id);
    @Mapper
    Boolean updateUserInfo(@Param("user") User user);
}
