package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.UpdateArticle;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    // 新增文章
    @Override
    public void insert(Article article) {
        // 补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> claims = ThreadLocalUtil.get();
        article.setCreateUser((Integer) claims.get("id"));
        articleMapper.insert(article);
    }

    // 文章列表查询
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        // 查询文章列表
        Map<String, Object> claims = ThreadLocalUtil.get();
        List<Article> articleList = articleMapper.list(categoryId, state, (Integer) claims.get("id"));
        // 封装分页信息
        Page<Article> page = (Page<Article>) articleList;

        return new PageBean<>(page.getTotal(), page.getResult());
    }

    // 文章详情查询
    @Override
    public Article getById(Integer id) {
        return articleMapper.getById(id);
    }

    // 文章更新
    @Override
    @UpdateArticle
    public void update(Article article) {
        articleMapper.update(article);
    }

    // 文章删除
    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    // 文章批量删除
    @Override
    public void deleteByCategoryId(Integer categoryId) {
        articleMapper.deleteByCategoryId(categoryId);
    }
}
