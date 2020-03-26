package com.mpocket.disk.controller;

import com.mpocket.disk.bean.Msg;
import com.mpocket.disk.bean.User;
import com.mpocket.disk.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    /**
     * 按id查找用户
     *
     * @param id 检索用户id
     * @return 返回json数据
     */
    @RequestMapping(value = {"/selectUserById/{id}"}, method = RequestMethod.GET)
    public Msg selectUserById(@PathVariable Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return Msg.fail("用户id为 " + id + " 的账号不存在");
        }
        return Msg.success("查找用户成功！").add("user", user);
    }

    /**
     * 按Email查找用户
     *
     * @param email 唯一绑定
     * @return 返回json数据
     */
    @RequestMapping(value = {"/selectUserByEmail/{email}"}, method = RequestMethod.GET)
    public Msg selectUserByEmail(@PathVariable String email) {
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return Msg.fail("Email为: " + email + " 的用户不存在");
        }
        return Msg.success("查找用户成功！").add("user", user);
    }

    /**
     * 查找所有用户
     *
     * @return 返回json数据
     */
    @RequestMapping(value = {"/selectAllUser"}, method = RequestMethod.GET)
    public Msg selectUser() {
        List<User> userList = userMapper.selectByExample(null);
        if (userList.isEmpty()) {
            return Msg.fail("数据库中无用户！");
        }
        return Msg.success("查找用户成功！").add("userList", userList);
    }

    /**
     * 新增User
     *
     * @param name        用户名
     * @param password    用户密码，在此会进行加密
     * @param email       用户邮箱
     * @param phoneNumber 用户电话
     * @param userIcon    用户头像
     * @return 返回json数据
     */
    @RequestMapping(value = {"/addUser/{name},{password},{email},{phoneNumber},{userIcon}"}, method = RequestMethod.POST)
    public Msg addUser(@PathVariable String name, @PathVariable String password, @PathVariable String email, @PathVariable String phoneNumber, @PathVariable String userIcon) {
        if (userMapper.selectByEmail(email) != null) {
            return Msg.fail("该邮箱已被注册!");
        }
        if (userMapper.selectByPhoneNumber(phoneNumber) != null) {
            return Msg.fail("该电话号码已被使用!");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User(name, bCryptPasswordEncoder.encode(password), true, false, email, phoneNumber, userIcon);
        if (userMapper.insertSelective(user) <= 0) {
            return Msg.fail("插入用户失败!");
        }
        return Msg.success("插入用户成功！");
    }

    /**
     * 更新用户Email邮箱
     *
     * @param id    用户id
     * @param email 用户邮箱
     * @return 返回json信息
     */
    @RequestMapping(value = {"/updateUserInEmail/{id},{email}"}, method = RequestMethod.PUT)
    public Msg updateUserInEmail(@PathVariable Integer id, @PathVariable String email) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return Msg.fail("用户id为 " + id + " 的账号不存在");
        }
        if (userMapper.selectByEmail(email) != null) {
            return Msg.fail("该邮箱已被使用!");
        }
        user.setEmail(email);
        if (userMapper.updateByPrimaryKey(user) <= 0) {
            return Msg.fail("更新用户数据失败！");
        }
        return Msg.success("更新用户数据成功！");
    }

    @RequestMapping(value = {"/updateUserInPhoneNumber/{id},{phoneNumber}"}, method = RequestMethod.PUT)
    public Msg updateUserInPhoneNumber(@PathVariable Integer id, @PathVariable String phoneNumber) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return Msg.fail("用户id为 " + id + " 的账号不存在");
        }
        if (userMapper.selectByPhoneNumber(phoneNumber) != null) {
            return Msg.fail("该电话已被使用!");
        }
        user.setPhoneNumber(phoneNumber);
        if (userMapper.updateByPrimaryKey(user) <= 0) {
            return Msg.fail("更新用户数据失败！");
        }
        return Msg.success("更新用户数据成功！");
    }

    /**
     * 据Id删除用户
     *
     * @param id 用户id
     * @return 返回json数据
     */
    @RequestMapping(value = {"/deleteUser/{id}"}, method = RequestMethod.DELETE)
    public Msg deleteUser(@PathVariable Integer id) {
        if (userMapper.selectByPrimaryKey(id) == null) {
            return Msg.fail("用户id为 " + id + " 的账号不存在");
        }
        if (userMapper.deleteByPrimaryKey(id) <= 0) {
            return Msg.fail("删除用户失败！");
        }
        return Msg.success("删除用户成功！");
    }
}

