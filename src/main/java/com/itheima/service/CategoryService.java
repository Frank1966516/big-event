package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增文章分类
    void insert(Category category);

    // 文章分类列表
    List<Category> list();

    // 根据id查询文章分类
    Category getById(Integer id);

    // 更新文章分类
    void update(Category category);

    // 删除文章分类
    void delete(Integer id);
}
