package com.xiaoluozhi.entity;

import lombok.Data;

// TODO 请求实例类
@Data
public class Request<T> {
    // 终结点名称
    private String action;
    // 参数
    private T params;
    // 回显
    private String echo;
}
