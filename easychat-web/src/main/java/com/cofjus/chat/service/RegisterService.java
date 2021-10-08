package com.cofjus.chat.service;

import com.cofjus.chat.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author Rui
 * @Date 2021/10/7 12:20
 * @Version 1.0
 */
@Service
public interface RegisterService {

    Long register(User user);

    User createUser(String username, String password);
}
