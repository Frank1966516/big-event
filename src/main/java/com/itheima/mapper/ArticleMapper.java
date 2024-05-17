package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    // 新增文章
    void insert(Article article);
}
