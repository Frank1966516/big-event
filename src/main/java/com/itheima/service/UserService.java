package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {
    // 查询用户
    User findByUsername(String username);

    // 注册用户
    void register(String username, String password);

    // 更新用户信息
    void update(User user);
}
