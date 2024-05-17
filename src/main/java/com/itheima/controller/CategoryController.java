package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 新增文章分类
    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        categoryService.insert(category);
        return Result.success("添加成功");
    }

    // 文章分类列表
    @GetMapping
    public Result<List<Category>> list() {
        return Result.success(categoryService.list());
    }

    // 获取文章分类
    @GetMapping("/detail")
    public Result<Category> get(Integer id) {
        return Result.success(categoryService.getById(id));
    }

    // 更新文章分类
    @PutMapping
    public Result update(@RequestBody @Validated(Category.update.class) Category category) {
        categoryService.update(category);
        return Result.success("更新成功");
    }

    // 删除文章分类
    @DeleteMapping
    public Result delete(Integer id) {
        categoryService.delete(id);
        return Result.success("删除成功");
    }
}
