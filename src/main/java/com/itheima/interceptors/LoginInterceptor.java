package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证令牌
        String token = request.getHeader("Authorization");
        try {
            // 从redis中获取token
            String redisToken = redisTemplate.opsForValue().get(token);
            if (redisToken == null){
                throw new RuntimeException("令牌已过期");
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 将解析后的用户数据放到threadLocal中
            ThreadLocalUtil.set(claims);
            // 解析成功，放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            // 解析失败，不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清楚threadLocal的数据
        ThreadLocalUtil.remove();
    }
}
