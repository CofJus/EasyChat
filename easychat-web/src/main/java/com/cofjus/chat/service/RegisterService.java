package com.cofjus.chat.service;

import com.cofjus.chat.dao.UserDao;
import com.cofjus.chat.model.User;
import com.cofjus.chat.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author Rui
 * @Date 2021/10/7 12:20
 * @Version 1.0
 */
@Service
public interface RegisterService {

    Long register(User user);
}
