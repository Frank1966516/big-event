package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 新增文章
    void insert(Article article);

    // 文章列表查询
    List<Article> list(Integer categoryId, String state, Integer id);

    // 根据id查询文章
    @Select("select * from article where id = #{id}")
    Article getById(Integer id);

    // 文章更新
    void update(Article article);

    // 删除文章
    @Delete("delete from article where id = #{id}")
    void delete(Integer id);

    // 根据文章分类id删除文章
    @Delete("delete from article where category_id = #{categoryId}")
    void deleteByCategoryId(Integer categoryId);
}