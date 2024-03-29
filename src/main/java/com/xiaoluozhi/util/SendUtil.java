package com.xiaoluozhi.util;

import com.alibaba.fastjson2.JSONObject;
import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.entity.botentity.Params;
import com.xiaoluozhi.entity.botentity.Request;
import com.xiaoluozhi.enums.XmlAndJson;
import com.xiaoluozhi.websocket.Client;

// 发送消息工具类
public class SendUtil {
    private SendUtil() {
    }

    // 发送文本消息
    public static void sendText(Message message, String content) {
        // 机器人回复消息
        Request<Params> paramsRequest = new Request<>();
        paramsRequest.setAction("send_msg");

        Params params = new Params();
        params.setUser_id(message.getUser_id());
        params.setGroup_id(message.getGroup_id());

        params.setMessage(content);

        params.setMessage_type(message.getMessage_type());
        params.setAuto_escape(true);
        paramsRequest.setParams(params);

        Client.sendMessage(JSONObject.toJSONString(paramsRequest));
    }

    // 发送图片消息
    public static void sendImg(Message message, String imgUrl) {
        // 机器人回复消息
        Request<Params> paramsRequest = new Request<>();
        paramsRequest.setAction("send_msg");

        Params params = new Params();
        params.setUser_id(message.getUser_id());
        params.setGroup_id(message.getGroup_id());

        params.setMessage("[CQ:image,file=" + imgUrl + "]");

        params.setMessage_type(message.getMessage_type());
        params.setAuto_escape(false);
        paramsRequest.setParams(params);

        Client.sendMessage(JSONObject.toJSONString(paramsRequest));
    }

    // 发送xml或json消息
    public static void sendXmlAndJson(Message message, XmlAndJson type, String data) {
        // 机器人回复消息
        Request<Params> paramsRequest = new Request<>();
        paramsRequest.setAction("send_msg");

        Params params = new Params();
        params.setUser_id(message.getUser_id());
        params.setGroup_id(message.getGroup_id());
        if ("xml".equals(type.toString())) {
            params.setMessage("[CQ:" + type + ",data=" + data + "]");
        } else {
            String text = "[CQ:json,data=" + data + "]";
            // System.out.println(text);
            params.setMessage(text);
        }

        params.setMessage_type("private");
        params.setAuto_escape(false);
        paramsRequest.setParams(params);

        Client.sendMessage(JSONObject.toJSONString(paramsRequest));
    }

    // 发送大图消息
    public static void sendCardImg(Message message, String imgUrl) {
        // 机器人回复消息
        Request<Params> paramsRequest = new Request<>();
        paramsRequest.setAction("send_msg");

        Params params = new Params();
        params.setUser_id(message.getUser_id());
        params.setGroup_id(message.getGroup_id());

        params.setMessage("[CQ:cardimage,file=" + imgUrl + "]");

        params.setMessage_type(message.getMessage_type());
        params.setAuto_escape(false);
        paramsRequest.setParams(params);

        Client.sendMessage(JSONObject.toJSONString(paramsRequest));
    }
}
