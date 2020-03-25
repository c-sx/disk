package com.mpocket.disk.controller;

import com.mpocket.disk.bean.Friends;
import com.mpocket.disk.bean.FriendsExample;
import com.mpocket.disk.bean.Msg;
import com.mpocket.disk.bean.User;
import com.mpocket.disk.dao.FriendsMapper;
import com.mpocket.disk.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    FriendsMapper friendsMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 用于查询用户好友资料
     *
     * @param id 本用户id
     * @return
     */
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public Msg myFriendList(@PathVariable Integer id) {
        FriendsExample friendsExample = new FriendsExample();
        FriendsExample.Criteria friendsExampleCriteria = friendsExample.createCriteria();
        friendsExampleCriteria.andUid1EqualTo(id);
        List<Friends> friendsList = friendsMapper.selectByExample(friendsExample);

        List<User> friends = new ArrayList<>();
        for (Friends value : friendsList) {
            Integer friendId = value.getUid2();
            friends.add(userMapper.selectByPrimaryKey(friendId));
        }

        if (friends.isEmpty()) {
            return Msg.fail();
        }

        return Msg.success().add("friends", friends);
    }

    /**
     * 添加好友对关系
     * @param id1 好友id1
     * @param id2 好友id2
     * @return
     */
    @RequestMapping(value = {"addFriend/{id1},{id2}"}, method = RequestMethod.GET)
    public Msg addFriend(@PathVariable Integer id1, @PathVariable Integer id2) {
        FriendsExample friendsExample = new FriendsExample();
        FriendsExample.Criteria friendsExampleCriteria = friendsExample.createCriteria();
        friendsExampleCriteria.andUid1EqualTo(id1).andUid2EqualTo(id2);
        if (!friendsMapper.selectByExample(friendsExample).isEmpty()) {
            return Msg.fail();
        }
        Friends friendsRelation1 = new Friends();
        friendsRelation1.setUid1(id1);
        friendsRelation1.setUid2(id2);
        Friends friendsRelation2 = new Friends();
        friendsRelation2.setUid1(id2);
        friendsRelation2.setUid2(id1);
        friendsMapper.insertSelective(friendsRelation1);
        friendsMapper.insertSelective(friendsRelation2);

        return Msg.success();
    }
}
