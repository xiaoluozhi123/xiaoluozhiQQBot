package com.xiaoluozhi.function;

import com.alibaba.fastjson2.JSONObject;
import com.xiaoluozhi.entity.Message;
import com.xiaoluozhi.entity.Params;
import com.xiaoluozhi.entity.Request;
import com.xiaoluozhi.event.IMessageEvent;
import com.xiaoluozhi.websocket.Client;
import org.springframework.stereotype.Component;

@Component
public class TestFun implements IMessageEvent {
    // 权重
    @Override
    public int weight() {
        return 5;
    }

    // 消息处理
    @Override
    public boolean onMessage(Message message) {
        if (!"测试".equals(message.getMessage())) {
            return false;
        }

        // 机器人回复消息
        Request<Params> paramsRequest = new Request<>();
        paramsRequest.setAction("send_msg");

        Params params = new Params();
        params.setUser_id(message.getUser_id());
        params.setGroup_id(message.getGroup_id());

        params.setMessage("测试成功");

        params.setMessage_type(message.getMessage_type());
        params.setAuto_escape(true);
        paramsRequest.setParams(params);

        Client.sendMessage(JSONObject.toJSONString(paramsRequest));
        return true;
    }
}
