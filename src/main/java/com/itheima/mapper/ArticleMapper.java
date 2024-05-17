package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 新增文章
    void insert(Article article);

    // 文章列表查询
    List<Article> list(Integer categoryId, String state, Integer id);
}
