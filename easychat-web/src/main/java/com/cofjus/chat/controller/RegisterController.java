package com.cofjus.chat.controller;

import com.cofjus.chat.model.User;
import com.cofjus.chat.request.RegisterRequest;
import com.cofjus.chat.response.RegisterResponse;
import com.cofjus.chat.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @Author Rui
 * @Date 2021/10/7 12:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        User user = registerService.createUser(registerRequest.getUserName(), registerRequest.getPassword());
        Long id = registerService.register(user);
        return new RegisterResponse(registerRequest);
    }
}
