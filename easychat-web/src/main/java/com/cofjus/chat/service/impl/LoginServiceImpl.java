package com.cofjus.chat.service.impl;

import com.cofjus.chat.dao.UserDao;
import com.cofjus.chat.dao.redis.RedisDao;
import com.cofjus.chat.model.User;
import com.cofjus.chat.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.cofjus.chat.constant.UserConstant.*;

/**
 * @Author Rui
 * @Date 2021/10/9 16:23
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private UserDao userDao;

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Override
    public Boolean hasLogin(String sessionId) {
        return redisDao.hasKey(sessionId);
    }

    @Override
    public String check(User user) {
        User dbUser = userDao.findUserByUserId(user.getUserId());
        if(Objects.isNull(dbUser)) {
            return NOT_FOUND;
        }
        return ENCODER.matches(user.getPassword(), dbUser.getPassword()) ? SUCCESS : PWD_ERROR;
    }
}
