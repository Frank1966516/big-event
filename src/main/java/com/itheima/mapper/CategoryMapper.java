package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // 新增文章分类
    void insert(Category category);

    // 文章分类列表
    @Select("select * from category where create_user = #{id}")
    List<Category> list(Integer id);

    // 根据id查询文章分类
    @Select("select * from category where id = #{id} and create_user = #{userId}")
    Category getById(Integer id, Integer userId);

    // 更新文章分类
    void update(Category category);

    // 删除文章分类
    @Delete("delete from category where id = #{id} and create_user = #{userId}")
    void delete(Integer id, Integer userId);
}
