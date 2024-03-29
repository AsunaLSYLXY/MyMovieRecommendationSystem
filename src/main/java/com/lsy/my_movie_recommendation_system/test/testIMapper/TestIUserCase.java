package com.lsy.my_movie_recommendation_system.test.testIMapper;

import com.lsy.my_movie_recommendation_system.entity.User;
import com.lsy.my_movie_recommendation_system.entity.UserBasicInfo;
import com.lsy.my_movie_recommendation_system.mapper.IUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIUserCase {
    @Autowired
    IUserMapper userMapper;

    @Test
    public void testSelectAllUserAllInfo() {
        List<User> users = userMapper.selectAllUserAllInfo();
        for(User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testSelectAllUserBasicInfo() {
        List<UserBasicInfo> users = userMapper.selectAllUserBasicInfo();
        for(UserBasicInfo u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testSelectUserAllInfoByUserID() {
        Integer id = 2;
        User user = userMapper.selectUserAllInfoByUserID(id);
        System.out.println(user);
    }

    @Test
    public void testSelectUserBasicInfoByID() {
        Integer id = 3;
        UserBasicInfo user = userMapper.selectUserBasicInfoByID(id);
        System.out.println(user);
    }

    @Test
    public void testQueryUserExistsByID() {
        Integer id = 1;
        Boolean found = userMapper.queryUserExistsByID(id);
        System.out.println(found);
    }

    @Test
    public void testSelectUserPasswordByID() {
        Integer id = 5;
        String password = userMapper.selectUserPasswordByID(id);
        System.out.println(password);
    }

    @Test
    public void testSelectUserPasswordByPhone() {
        String phone = "110";
        String password = userMapper.selectUserPasswordByPhone(phone);
        System.out.println(password);
    }

    @Test
    public void testSelectUserPasswordByUserName() {
        String name = "ghost";
        String password = userMapper.selectUserPasswordByUserName(name);
        System.out.println(password);
    }

    @Test
    public void testUpdateUserNameByID() {
        Integer id = 8;
        String name = "out man";
        Boolean ok = userMapper.updateUserNameByID(id, name);
        System.out.println(ok);
    }

    @Test
    public void testUpdateUserPasswordByID() {
        Integer id = 14;
        String password = "pass_pass";
        Boolean ok = userMapper.updateUserPasswordByID(id, password);
        System.out.println(ok);
    }

    @Test
    public void testUpdateUserPhoneByID() {
        Integer id = 14;
        String phone = "111222333";
        Boolean ok = userMapper.updateUserPhoneByID(id, phone);
        System.out.println(ok);
    }

    @Test
    public void testUpdateUserMoodByID() {
        Integer id = 8;
        String mood = "泥萌兔兔没有小兔兔可爱";
        Boolean ok = userMapper.updateUserMoodByID(id, mood);
        System.out.println(ok);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("泥萌兔兔兔是好好");
        user.setPassword("66666666");
        user.setPhone("112112112");
        user.setMood("哈哈哈");
        userMapper.addUser(user);
        System.out.println(user);
    }

    @Test
    public void testRemoveUser() {
        Integer id = 13;
        Boolean ok = userMapper.removeUserByID(id);
        System.out.println(ok);
    }

    @Test
    public void testQueryUserExistsByPhone() {
        String phone = "000";
        Boolean found = userMapper.queryUserExistsByPhone(phone);
        System.out.println(found);
    }

    @Test
    public void testSelectUserBasicInfoByPhone() {
        String phone = "119";
        UserBasicInfo user = userMapper.selectUserBasicInfoByPhone(phone);
        System.out.println(user);
    }

    @Test
    public void testSelectUserAllInfoByPhone() {
        String phone = "119";
        User user = userMapper.selectUserAllInfoByPhone(phone);
        System.out.println(user);
    }

    @Test
    public void testUpdateUserInfo() {
        User user = new User();
        user.setId(29);
        user.setPhone("110110");
        user.setPassword("654321");
        user.setName("嘎嘎嘎");
        user.setMood("哼哼哼");
        Boolean ok = userMapper.updateUserInfo(user);
        System.out.println(ok);
    }
}
