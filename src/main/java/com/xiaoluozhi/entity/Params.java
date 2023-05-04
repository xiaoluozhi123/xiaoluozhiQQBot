package com.xiaoluozhi.entity;

import lombok.Data;

@Data
public class Params {
    // 消息类型
    private String message_type;
    // 对方QQ号
    private String user_id;
    // 群号
    private String group_id;
    // 消息内容
    private String message;
    // 是否作为纯文本发送
    private boolean auto_escape;
}
