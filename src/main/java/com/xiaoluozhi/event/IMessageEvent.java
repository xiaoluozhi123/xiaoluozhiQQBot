package com.xiaoluozhi.event;

import com.xiaoluozhi.entity.botentity.Message;

// TODO 功能接口
public interface IMessageEvent {
    /**
     * 当命令重复，只执行优先级最高的
     * @return
     */
    int weight();

    /**
     * 接收到消息时执行，返回true则不再执行后续的命令
     * @param message
     * @return
     */
    boolean onMessage(Message message);
}
