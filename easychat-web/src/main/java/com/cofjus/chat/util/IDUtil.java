package com.cofjus.chat.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

/**
 * @Author Rui
 * @Date 2021/10/8 9:45
 * @Version 1.0
 */
public class IDUtil {

    public static Long randomId() {
        long min = 1L;
        long max = 999999999999999999L;
        return min + (((long) (new Random().nextDouble() * (max - min))));
    }

}
