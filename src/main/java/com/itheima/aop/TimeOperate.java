package com.itheima.aop;

import com.itheima.pojo.Article;
import com.itheima.pojo.Category;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class TimeOperate {
    @Pointcut("@annotation(com.itheima.anno.UpdateCategory)")
    public void pointCutUpdateCategory(){}

    @Pointcut("@annotation(com.itheima.anno.UpdateArticle)")
    public void pointCutUpdateArticle(){}

    // 对文章分类更新操作的前置操作
    @Around("pointCutUpdateCategory()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 前置操作

        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println(args[0]);

        // 将参数转换为文章分类对象
        Category category = (Category) args[0];

        // 修改文章分类的更新时间
        category.setUpdateTime(LocalDateTime.now());

        // 修改参数
        args[0] = category;

        // 方法操作
        Object result = joinPoint.proceed(args);

        // 后置操作
        return result;
    }


    // 对文章更新操作的前置操作
    @Around("pointCutUpdateArticle()")
    public Object around2(ProceedingJoinPoint joinPoint) throws Throwable {

        // 前置操作

        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println(args[0]);

        // 将参数转换为文章对象
        Article article = (Article) args[0];

        // 修改文章的更新时间
        article.setUpdateTime(LocalDateTime.now());

        // 修改参数
        args[0] = article;

        // 方法操作
        Object result = joinPoint.proceed(args);

        // 后置操作
        return result;
    }
}
