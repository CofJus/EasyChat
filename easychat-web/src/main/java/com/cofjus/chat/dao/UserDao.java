package com.cofjus.chat.dao;

import com.cofjus.chat.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Rui
 * @Date 2021/10/7 15:08
 * @Version 1.0
 */
@Repository
public interface UserDao {

    /**
     * 新注册用户
     * @param user User
     * @return user的自增id
     */
    Long insert(User user);

    /**
     * 根据用户id查询
     * @param userId userId
     * @return user
     */
    User findUserByUserId(Long userId);

}
