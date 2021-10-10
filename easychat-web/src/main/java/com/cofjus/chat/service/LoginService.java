package com.cofjus.chat.service;

import com.cofjus.chat.model.User;

/**
 * @Author Rui
 * @Date 2021/10/9 16:22
 * @Version 1.0
 */
public interface LoginService {

    Boolean hasLogin(String sessionId);

    String check(User user);

}
