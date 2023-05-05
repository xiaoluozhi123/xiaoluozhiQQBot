package com.xiaoluozhi.entity.botentity;

import lombok.Data;

@Data
public class BotMessage {
    // 响应
    private String[] data;
    // 消息id
    private String message_id;
    // 信息
    private String msg;
    // 响应码
    private int retCode;
    // 是否成功
    private String status;
    // 错误信息
    private String wording;
}
