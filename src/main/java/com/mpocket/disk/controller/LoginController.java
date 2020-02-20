package com.mpocket.disk.controller;

import com.mpocket.disk.bean.User;
import com.mpocket.disk.bean.UserExample;
import com.mpocket.disk.bean.UserLogin;
import com.mpocket.disk.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginController implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(s);

        //根据用户名从数据库查询对应记录
        User user = (User) userMapper.selectByExample(userExample);
        if (user == null) {
            throw new UsernameNotFoundException("username is not exists");
        }
        System.out.println("username is : " + user.getEmail() + ", password is :" + user.getPassword());
        return new UserLogin(user);
    }
}
