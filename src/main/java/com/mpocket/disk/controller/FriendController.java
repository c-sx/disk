package com.mpocket.disk.controller;

import com.mpocket.disk.bean.*;
import com.mpocket.disk.dao.FriendsMapper;
import com.mpocket.disk.dao.UserMapper;
import com.mpocket.disk.dao.UserMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/friends")
public class FriendController {

    @Autowired
    FriendsMapper friendsMapper;

    @Autowired
    UserMapperExt userMapper;

    /**
     * 用于查询用户好友资料
     *
     * @param id 本用户id
     * @return 返回json数据
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
            return Msg.fail("好友列表为空！");
        }

        return Msg.success("好友列表检索成功！").add("friends", friends);
    }

    /**
     * 添加好友对关系
     *
     * @param id1 好友id1
     * @param id2 好友id2
     * @return 返回json数据
     */
    @RequestMapping(value = {"addFriend/{id1},{id2}"}, method = RequestMethod.POST)
    public Msg addFriend(@PathVariable Integer id1, @PathVariable Integer id2) {
        //鉴别user1和user2是否存在
        User user1 = userMapper.selectByPrimaryKey(id1);
        User user2 = userMapper.selectByPrimaryKey(id2);
        if (user1 == null || user2 == null) {
            if (user1 == null && user2 == null) {
                return Msg.fail("id为 " + id1 + " 的用户及id为 " + id2 + " 的用户不存在!");
            } else if (user2 == null) {
                return Msg.fail("id为 " + id2 + " 的用户不存在!");
            } else {
                return Msg.fail("id为 " + id1 + " 的用户不存在!");
            }
        }

        //判断好友关系是否存在，若无执行添加
        FriendsExample friendsExample = new FriendsExample();
        FriendsExample.Criteria friendsExampleCriteria = friendsExample.createCriteria();
        friendsExampleCriteria.andUid1EqualTo(id1).andUid2EqualTo(id2);
        if (!friendsMapper.selectByExample(friendsExample).isEmpty()) {
            return Msg.fail("好友关系已存在!");
        }
        Friends friendsRelation1 = new Friends();
        friendsRelation1.setUid1(id1);
        friendsRelation1.setUid2(id2);
        Friends friendsRelation2 = new Friends();
        friendsRelation2.setUid1(id2);
        friendsRelation2.setUid2(id1);

        return Msg.success("添加好友关系成功！");
    }

    /**
     * 删除好友关系
     *
     * @param id1 好友id1
     * @param id2 好友id2
     * @return 返回json数据
     */
    @RequestMapping(value = {"deleteFriend/{id1},{id2}"}, method = RequestMethod.DELETE)
    public Msg deleteFriend(@PathVariable Integer id1, @PathVariable Integer id2) {
        //鉴别user1和user2是否存在
        User user1 = userMapper.selectByPrimaryKey(id1);
        User user2 = userMapper.selectByPrimaryKey(id2);
        if (user1 == null || user2 == null) {
            if (user1 == null && user2 == null) {
                return Msg.fail("id为 " + id1 + " 的用户及id为 " + id2 + " 的用户不存在!");
            } else if (user2 == null) {
                return Msg.fail("id为 " + id2 + " 的用户不存在!");
            } else {
                return Msg.fail("id为 " + id1 + " 的用户不存在!");
            }
        }

        //判断好友关系是否存在，若有执行删除
        FriendsExample friendsExample = new FriendsExample();
        FriendsExample.Criteria friendsExampleCriteria = friendsExample.createCriteria();
        FriendsExample.Criteria friendsExampleCriteria2 = friendsExample.or();
        friendsExampleCriteria.andUid1EqualTo(id1).andUid2EqualTo(id2);
        friendsExampleCriteria2.andUid1EqualTo(id2).andUid2EqualTo(id1);

        if (friendsMapper.selectByExample(friendsExample).isEmpty()) {
            return Msg.fail("好友关系不存在!");
        }
        return Msg.success("删除好友关系成功！");
    }
}
