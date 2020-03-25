package com.mpocket.disk;

import com.mpocket.disk.dao.FriendsMapper;
import com.mpocket.disk.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiskApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    FriendsMapper friendsMapper;

    @Test
    void contextLoads() {
    }

    //批量生成测试用户
    @Test
    void addUserBatch() {
/*
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 30; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5);
            userMapper.insertSelective(new User(uid, bCryptPasswordEncoder.encode("123456"), true, false, uid + "@example.com"));
        }
*/
    }

/*    @Test
    void myFriendListTest() {
        FriendController friendController = new FriendController();
        friendController.myFriendList(5);
    }

    @Test
    void addFriendTest() {
        FriendController friendController = new FriendController();
        friendController.addFriend(4, 5);
    }*/
}
