package com.mpocket.disk.controller;

import com.mpocket.disk.bean.*;
import com.mpocket.disk.dao.RoleMapper;
import com.mpocket.disk.dao.UserMapper;
import com.mpocket.disk.dao.UserMapperExt;
import com.mpocket.disk.dao.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LoginController implements UserDetailsService {

    @Autowired
    private UserMapperExt userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //根据登录邮箱从数据库索引对应账号
        User user = userMapper.selectByEmail(email);

        /*
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andEmailEqualTo(s);

        //根据用户名从数据库查询对应记录
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        User user = userList.get(0);
        */

        //控制台可查看用户登录数据
        System.out.println("email is : " + user.getEmail() + ", password is :" + user.getPassword());

        //查询用户所拥有的权限列表
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
        userRoleExampleCriteria.andUserEqualTo(user.getId());
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
        if (userRoleList == null) {
            try {
                throw new UserPrincipalNotFoundException("用户权限未设置！");
            } catch (UserPrincipalNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<Role> roleList = new ArrayList<>();

        for (UserRole value : userRoleList) {
            Integer roleId = value.getRole();
            roleList.add(roleMapper.selectByPrimaryKey(roleId));
        }

        UserLogin userLogin = new UserLogin(user);
        userLogin.setRoles(roleList);
        return userLogin;
    }
}
