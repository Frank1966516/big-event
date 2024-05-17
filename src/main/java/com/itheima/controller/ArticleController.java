package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // 新增文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.insert(article);
        return Result.success("新增成功");
    }

    // 文章列表查询
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }

    // 文章详情查询
    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article article = articleService.getById(id);
        return Result.success(article);
    }

    // 文章更新
    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success("更新成功");
    }
}
