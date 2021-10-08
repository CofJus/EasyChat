package com.cofjus.chat.service.impl;

import com.cofjus.chat.service.SessionService;
import com.cofjus.chat.dao.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rui
 * @Date 2021/10/8 10:41
 * @Version 1.0
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private RedisDao redisDao;

    @Override
    public Boolean hasLogin(Long id) {
        return redisDao.hasKey(id.toString());
    }
}
