package com.cofjus.chat.operation.base;

import com.cofjus.chat.message.base.MessageBody;

/**
 * @Author Rui
 * @Date 2021/10/5 10:00
 * @Version 1.0
 */
public abstract class Operation extends MessageBody {

    /**
     * 实现业务逻辑
     * @return OperationResult
     */
    public abstract OperationResult execute();
}
