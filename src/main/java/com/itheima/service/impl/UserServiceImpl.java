package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 根据用户查询用户
    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    // 注册用户
    @Override
    public void register(String username, String password) {
        // 密码加密
        password = Md5Util.getMD5String(password);
        // 保存用户
        userMapper.insert(username, password);
    }
}
