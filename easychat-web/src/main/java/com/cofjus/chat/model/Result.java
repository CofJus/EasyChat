package com.cofjus.chat.model;

import lombok.Data;

/**
 * @Author Rui
 * @Date 2021/10/10 9:52
 * @Version 1.0
 */
@Data
public class Result<T> {

    private String msg;

    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(msg);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(msg, data);
    }
}
