package com.cofjus.chat.controller;

import com.cofjus.chat.model.Result;
import com.cofjus.chat.model.User;
import com.cofjus.chat.request.LoginRequest;
import com.cofjus.chat.response.LoginResponse;
import com.cofjus.chat.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Objects;

import static com.cofjus.chat.constant.Constant.SUCCESS;

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
    private HttpServletRequest request;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Long userId = loginRequest.getUserId();
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        User user = new User(userId, username, password);
        String msg = loginService.check(user);
        LoginResponse loginResponse = new LoginResponse(loginRequest);
        if(SUCCESS.equals(msg)) {
            HttpSession session = request.getSession();
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

    @GetMapping("/logout/{userId}")
    public void logout() {
        System.out.println("LOGOUT SUCCESS");
    }
}
