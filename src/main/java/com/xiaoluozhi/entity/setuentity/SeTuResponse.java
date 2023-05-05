package com.xiaoluozhi.entity.setuentity;

import lombok.Data;

@Data
public class SeTuResponse {
    // 错误信息
    private String error;
    // 色图数组
    private String[] data;
}
