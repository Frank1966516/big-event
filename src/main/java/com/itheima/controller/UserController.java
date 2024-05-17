package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

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
        if (!Md5Util.getMD5String(password).equals(user.getPassword())){
            return Result.error("密码错误");
        }

        // 生成jwt令牌
        Map <String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        String token = JwtUtil.genToken(claims);

        // 将令牌存到redis中
        redisTemplate.opsForValue()
                .set(token, token, 12, TimeUnit.HOURS);
        return Result.success(token);
    }

    // 详细信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        // 在threadLocal中拿取用户信息
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = claims.get("username").toString();

        // 查询用户
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    // 更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        // 更新用户信息
        userService.update(user);
        return Result.success("更新成功");
    }

    // 更新用户头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        User user = new User();
        user.setUserPic(avatarUrl);
        user.setUpdateTime(LocalDateTime.now());
        Map<String, Object> claims = ThreadLocalUtil.get();
        user.setId((Integer) claims.get("id"));
        userService.update(user);
        return Result.success("更新成功");
    }

    // 更新用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token){
        // 校验参数
        String newPassword = params.get("new_pwd");
        String oldPassword = params.get("old_pwd");
        String rePassword = params.get("re_pwd");

        if (!StringUtils.hasLength(newPassword) || !StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(rePassword)){
            return Result.error("参数不能为空");
        }

        // 查询原密码与现在密码是否相同
        Map<String, Object> claims = ThreadLocalUtil.get();
        User user = userService.findByUsername(claims.get("username").toString());

        if (!Md5Util.getMD5String(oldPassword).equals(user.getPassword())){
            return Result.error("原密码错误");
        }
        // 判断两次密码是否相同
        if (!newPassword.equals(rePassword)){
            return Result.error("两次密码不一致");
        }

        // 修改密码
        user.setPassword(Md5Util.getMD5String(newPassword));
        user.setUpdateTime(LocalDateTime.now());
        userService.update(user);

        // 删除原token
        redisTemplate.delete(token);
        return Result.success("更新成功");
    }
}
