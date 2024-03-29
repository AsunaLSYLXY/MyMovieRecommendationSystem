package com.lsy.my_movie_recommendation_system.service;

import com.lsy.my_movie_recommendation_system.entity.User;
import com.lsy.my_movie_recommendation_system.entity.UserBasicInfo;
import com.lsy.my_movie_recommendation_system.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAllUserAllInfo() {
        return userMapper.selectAllUserAllInfo();
    }

    @Override
    public List<UserBasicInfo> selectAllUserBasicInfo() {
        return userMapper.selectAllUserBasicInfo();
    }

    @Override
    public User selectUserAllInfoByUserID(Integer id) {
        return userMapper.selectUserAllInfoByUserID(id);
    }

    @Override
    public UserBasicInfo selectUserBasicInfoByID(Integer id) {
        return userMapper.selectUserBasicInfoByID(id);
    }

    @Override
    public UserBasicInfo selectUserBasicInfoByPhone(String phone) {
        return userMapper.selectUserBasicInfoByPhone(phone);
    }

    @Override
    public Boolean queryUserExistsByID(Integer id) {
        return userMapper.queryUserExistsByID(id);
    }

    @Override
    public String selectUserPasswordByID(Integer id) {
        return userMapper.selectUserPasswordByID(id);
    }

    @Override
    public String selectUserPasswordByPhone(String phone) {
        return userMapper.selectUserPasswordByPhone(phone);
    }

    @Override
    public Boolean updateUserNameByID(Integer id, String name) {
        return userMapper.updateUserNameByID(id, name);
    }

    @Override
    public Boolean updateUserPasswordByID(Integer id, String password) {
        return userMapper.updateUserPasswordByID(id, password);
    }

    @Override
    public Boolean updateUserPhoneByID(Integer id, String phone) {
        try {
            return userMapper.updateUserPhoneByID(id, phone);
        } catch (DataIntegrityViolationException e) {
            // 手机号重复等
            System.out.println("修改手机号失败，可能是手机号已经被注册");
            return false;
        } catch (Exception e) {
            // 处理其他类型的异常
            System.out.println("修改手机号失败失败，发生未知异常");
            return false;
        }
    }

    @Override
    public Boolean updateUserMoodByID(Integer id, String mood) {
        return userMapper.updateUserMoodByID(id, mood);
    }

    @Override
    public void addUser(User user) {
        try {
            userMapper.addUser(user);
        } catch (DataIntegrityViolationException e) {
            // 处理数据库约束异常，比如手机号重复等
            // 这里可以选择抛出自定义异常、记录日志等
            // 也可以直接将异常向上层抛出，让控制器层处理
            user.setId(null);
            System.out.println("添加用户失败，可能是手机号已经被注册");
        } catch (Exception e) {
            // 处理其他类型的异常
            user.setId(null);
            System.out.println("添加用户失败，发生未知异常");
        }
    }

    @Override
    public Boolean removeUserByID(Integer id) {
        return userMapper.removeUserByID(id);
    }

    @Override
    public Boolean queryUserExistsByPhone(String phone) {
        return userMapper.queryUserExistsByPhone(phone);
    }

    @Override
    public User selectUserAllInfoByPhone(String phone) {
        return userMapper.selectUserAllInfoByPhone(phone);
    }

    @Override
    public Boolean updateUserInfo(User user) {
        try {
            return userMapper.updateUserInfo(user);
        } catch (DataIntegrityViolationException e) {
            // 处理数据库约束异常，比如手机号重复等
            // 这里可以选择抛出自定义异常、记录日志等
            // 也可以直接将异常向上层抛出，让控制器层处理
            System.out.println("更新用户信息失败，可能是手机号已经被注册");
            return false;
        } catch (Exception e) {
            // 处理其他类型的异常
            System.out.println("更新用户信息失败，发生未知异常");
            return false;
        }
    }
}
