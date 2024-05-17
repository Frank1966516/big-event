package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;

    // 新增文章分类
    @Override
    public void insert(Category category) {
        // 补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> claims = ThreadLocalUtil.get();
        category.setCreateUser((Integer) claims.get("id"));

        categoryMapper.insert(category);
    }
}
