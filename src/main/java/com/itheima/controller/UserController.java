package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password){
//        // 判断用户名和密码合法性
//        if(username == null || username.trim().isEmpty() || username.length() < 5 || username.length() > 16
//                || password == null || password.trim().isEmpty() || password.length() < 5 || password.length() > 16){
//            return Result.error("用户名或密码不合法");
//        }

        // 查询用户
        User user = userService.findByUsername(username);

        // 判断用户名是否存在
        if(user == null){
            // 注册
            userService.register(username, password);
            return Result.success("注册成功");
        }
        return Result.error("用户名已存在");
    }

    // 登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询用户
        User user = userService.findByUsername(username);

        // 判断用户是否存在
        if(user == null){
            return Result.error("用户不存在");
        }

        // 判断密码是否正确
        if (Md5Util.getMD5String(password).equals(user.getPassword())){
            // 生成jwt令牌
            Map <String, Object> claims = Map.of("username", username);
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }
}
