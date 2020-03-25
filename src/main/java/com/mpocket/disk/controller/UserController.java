package com.mpocket.disk.controller;

import com.mpocket.disk.bean.User;
import com.mpocket.disk.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    //登录界面，用于界面查看
    @RequestMapping({"/","/hello"})
    public String login() {
        return "hello";
    }


    @RequestMapping(value={"/selectUserById"}, method= RequestMethod.GET)
    public User selectUserById(String id){
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        return user;
    }

    @RequestMapping(value={"/selectUser"}, method= RequestMethod.GET)
    public List<User> selectUser(){
        return userMapper.selectByExample(null);
    }

    //新增User
    @RequestMapping(value={"/addUser"}, method=RequestMethod.POST)
    public void addUser(User user){
        userMapper.insert(user);
    }

    @RequestMapping(value={"/updateUser"}, method=RequestMethod.POST)
    public void updateUser(User user){
        userMapper.updateByPrimaryKey(user);
    }

    @RequestMapping(value={"/deleteUser"}, method=RequestMethod.POST)
    public void deleteUser(String id){
        userMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }
}

