package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

public interface ArticleService {

    // 新增文章
    void insert(Article article);

    // 文章列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 文章详情查询
    Article getById(Integer id);

    // 文章更新
    void update(Article article);

    // 文章删除
    void delete(Integer id);

    // 根据文章分类id删除文章
    void deleteByCategoryId(Integer categoryId);
}
