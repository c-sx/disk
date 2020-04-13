package com.mpocket.disk.dao;

import com.mpocket.disk.bean.User;

public interface UserMapperExt extends UserMapper {

    User selectByEmail(String email);

    User selectByPhoneNumber(String phoneNumber);
}
