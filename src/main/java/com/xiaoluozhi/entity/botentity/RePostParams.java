package com.xiaoluozhi.entity.botentity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RePostParams {
    // 加好友/加群请求的 flag（需从上报的数据中获得）
    @JSONField(name = "flag")
    private String flag;
    // 是否同意请求
    @JSONField(name = "approve")
    private boolean approve;
    // 添加后的好友备注（仅在同意时有效）
    @JSONField(name = "remark")
    private String remark;
    // add 或 invite, 请求类型（需要和上报消息中的 sub_type 字段相符）
    @JSONField(name = "sub_type")
    private String subType;
    @JSONField(name = "type")
    private String Type;
    // 拒绝理由（仅在拒绝时有效）
    @JSONField(name = "reason")
    private String reason;
}
