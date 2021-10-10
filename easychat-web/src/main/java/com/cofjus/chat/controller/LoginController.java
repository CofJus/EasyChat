package com.cofjus.chat.controller;

import com.cofjus.chat.model.Result;
import com.cofjus.chat.model.User;
import com.cofjus.chat.request.LoginRequest;
import com.cofjus.chat.response.LoginResponse;
import com.cofjus.chat.response.LogoutResponse;
import com.cofjus.chat.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.cofjus.chat.constant.UserConstant.*;

/**
 * @Author Rui
 * @Date 2021/10/8 9:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginResponse> login(HttpSession session, @RequestBody LoginRequest loginRequest) {
        Long userId = loginRequest.getUserId();
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        User user = new User(userId, username, password);
        String msg = loginService.check(user);
        LoginResponse loginResponse = new LoginResponse(loginRequest);
        if(SUCCESS.equals(msg)) {
            session.setAttribute("userId", loginRequest.getUserId());
            String sessionId = session.getId();
            log.info("sessionId: " + sessionId);
            loginResponse.setSessionId(sessionId);
            loginResponse.setSuccess(true);
            return Result.success(loginResponse);
        }
        else {
            loginResponse.setSuccess(false);
            return Result.error(msg, loginResponse);
        }
    }

    @GetMapping("/logout")
    public Result<LogoutResponse> logout(HttpSession session) {
        Object o = session.getAttribute("userId");
        LogoutResponse logoutResponse = new LogoutResponse();
        String id = session.getId();
        // 已登录
        if(o != null) {
            logoutResponse.setSuccess(true);
            logoutResponse.setUserId(Long.parseLong(o.toString()));
            System.out.println("session-userId" + o.toString());
            session.removeAttribute("userId");
            System.out.println("LOGOUT SUCCESS");
            return Result.success(LOG_OUT, logoutResponse);
        }
        // 未登录
        else {
            return Result.error(NOT_LOG_IN);
        }
    }
}
