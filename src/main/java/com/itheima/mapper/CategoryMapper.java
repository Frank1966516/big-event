package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    // 新增文章分类
    void insert(Category category);
}
