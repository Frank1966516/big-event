package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("/register")
    public Result register(String username, String password){
        // 查询用户
        User user = userService.findByUsername(username);

        // 判断用户名是否存在
        if(user == null){
            // 注册
            userService.register(username, password);
            return Result.success();
        }
        return Result.error("用户名已存在");
    }
}
