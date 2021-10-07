package com.cofjus.chat.service.impl;

import com.cofjus.chat.dao.UserDao;
import com.cofjus.chat.model.User;
import com.cofjus.chat.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author Rui
 * @Date 2021/10/7 16:12
 * @Version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Override
    public Long register(User user) {
        user.setPassword(ENCODER.encode(user.getPassword()));
        return userDao.insert(user);
    }
}
