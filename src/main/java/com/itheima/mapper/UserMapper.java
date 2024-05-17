package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // 根据用户查询用户
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    // 添加用户
    @Insert("insert into user (username, password, create_time, update_time)" +
            "values (#{username}, #{password}, now(), now())")
    void insert(String username, String password);

    // 更新用户信息
    void update(User user);
}
