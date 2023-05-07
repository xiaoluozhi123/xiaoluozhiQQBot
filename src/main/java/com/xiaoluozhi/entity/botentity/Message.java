package com.xiaoluozhi.entity.botentity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

// TODO 消息类
@Data
public class Message {
    private String post_type;
    private String meta_event_type;
    private String message_type;
    // 时间戳
    private long time;
    // 机器人QQ号
    private String self_id;
    // 消息子类型
    @JSONField(name = "sub_type")
    private String subType;
    // 用户QQ号
    private String user_id;
    private String sender_id;
    // 群号
    private String group_id;
    // 讨论组号
    private String target_id;
    // 消息内容
    private String message;
    // 原始消息内容
    private String raw_message;
    // 字体
    private String font;
    // 发送人信息
    private Sender sender;
    // 消息ID
    private String message_id;
    // 消息序号
    private String message_seq;
    // 匿名信息
    private String anonymous;
    // 请求类型
    @JSONField(name = "request_type")
    private String requestType;
    // 验证信息
    @JSONField(name = "comment")
    private String comment;
    // 请求 flag, 在调用处理请求的 API 时需要传入
    @JSONField(name = "flag")
    private String flag;
}
