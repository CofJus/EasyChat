package com.cofjus.chat.interceptor;

import com.cofjus.chat.dao.redis.RedisDao;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author Rui
 * @Date 2021/10/10 9:15
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userId = session.getAttribute("userId").toString();
        System.out.println("Interceptor: userId: " + userId);
        System.out.println(Objects.nonNull(userId));
        return Objects.nonNull(userId);
    }
}
