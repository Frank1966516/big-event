package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.ArticleService;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleService articleService;

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

    // 文章分类列表
    @Override
    public List<Category> list() {
        // 得到用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        return categoryMapper.list( (Integer) claims.get("id"));
    }

    // 根据id查询文章分类
    @Override
    public Category getById(Integer id) {
        // 得到用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        return categoryMapper.getById(id, (Integer) claims.get("id"));
    }

    // 更新文章分类
    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    // 删除文章分类
    @Transactional
    @Override
    public void delete(Integer id) {
        // 得到用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        // 删除文章分类
        categoryMapper.delete(id, (Integer) claims.get("id"));
        // 删除文章分类下的文章
        articleService.deleteByCategoryId(id);
    }
}
