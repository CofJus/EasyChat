package com.cofjus.chat.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author Rui
 * @Date 2021/10/6 15:56
 * @Version 1.0
 */
@Data
public abstract class Message {

    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 获取message指令
     * @return command
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
