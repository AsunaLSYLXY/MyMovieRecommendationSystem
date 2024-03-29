package com.lsy.my_movie_recommendation_system.test.testService;

import com.lsy.my_movie_recommendation_system.entity.User;
import com.lsy.my_movie_recommendation_system.entity.UserBasicInfo;
import com.lsy.my_movie_recommendation_system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserCase {
    @Autowired
    UserService userService;

    @Test
    public void testSelectAllUserAllInfo() {
        List<User> users = userService.selectAllUserAllInfo();
        for(User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testSelectAllUserBasicInfo() {
        List<UserBasicInfo> users = userService.selectAllUserBasicInfo();
        for(UserBasicInfo u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testSelectUserAllInfoByUserID() {
        Integer id = 8;
        User user = userService.selectUserAllInfoByUserID(id);
        System.out.println(user);
    }

    @Test
    public void testSelectUserBasicInfoByID() {
        Integer id = 9;
        UserBasicInfo user = userService.selectUserBasicInfoByID(id);
        System.out.println(user);
    }

    @Test
    public void testQueryUserExistsByID() {
        Integer id = 10;
        Boolean found = userService.queryUserExistsByID(id);
        System.out.println(found);
    }

    @Test
    public void testSelectUserPasswordByID() {
        Integer id = 12;
        String password = userService.selectUserPasswordByID(id);
        System.out.println(password);
    }

    @Test
    public void testSelectUserPasswordByPhone() {
        String phone = "120";
        String password = userService.selectUserPasswordByPhone(phone);
        System.out.println(password);
    }

    @Test
    public void testUpdateUserNameByID() {
        Integer id = 14;
        String name = "一般窝窝";
        Boolean ok = userService.updateUserNameByID(id, name);
        System.out.println(ok);
    }

    @Test
    public void testUpdateUserPasswordByID() {
        Integer id = 19;
        String password = "787878";
        Boolean ok = userService.updateUserPasswordByID(id, password);
        System.out.println(ok);
    }

    @Test
    public void testUpdateUserPhoneByID() {
        Integer id = 20;
        String phone = "444444";
        Boolean ok = userService.updateUserPhoneByID(id, phone);
        System.out.println(ok);
    }

    @Test
    public void testUpdateUserMoodByID() {
        Integer id = 19;
        String mood = "哼哼和嘎嘎哪一个更可爱";
        Boolean ok = userService.updateUserMoodByID(id, mood);
        System.out.println(ok);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("嘎嘎是大坏坏");
        user.setPassword("88888888");
        user.setPhone("12138");
        user.setMood("嘎叭嘎叭嘎叭, 嘎嘎嘎");
        userService.addUser(user);
        System.out.println(user);
    }

    @Test
    public void testRemoveUser() {
        Integer id = 17;
        Boolean ok = userService.removeUserByID(id);
        System.out.println(ok);
    }

    @Test
    public void testQueryUserExistsByPhone() {
        String phone = "112113";
        Boolean found = userService.queryUserExistsByPhone(phone);
        System.out.println(found);
    }

    @Test
    public void testSelectUserBasicInfoByPhone() {
        String phone = "54321";
        UserBasicInfo user = userService.selectUserBasicInfoByPhone(phone);
        System.out.println(user);
    }

    @Test
    public void testSelectUserAllInfoByPhone() {
        String phone = "119";
        User user = userService.selectUserAllInfoByPhone(phone);
        System.out.println(user);
    }

    @Test
    public void testUpdateUserInfo() {
        User user = new User();
        user.setId(29);
        user.setPhone("110110");
        user.setPassword("123456");
        user.setName("嘎嘎嘎嘎嘎嘎");
        user.setMood("哼哼哼哼哼哼");
        Boolean ok = userService.updateUserInfo(user);
        System.out.println(ok);
    }
}
