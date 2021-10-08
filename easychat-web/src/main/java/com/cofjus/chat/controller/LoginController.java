package com.cofjus.chat.controller;

import com.cofjus.chat.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author Rui
 * @Date 2021/10/8 9:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {

    }
}
