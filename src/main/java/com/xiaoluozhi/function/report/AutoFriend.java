package com.xiaoluozhi.function.report;

import com.alibaba.fastjson2.JSONObject;
import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.entity.botentity.Params;
import com.xiaoluozhi.entity.botentity.RePostParams;
import com.xiaoluozhi.entity.botentity.Request;
import com.xiaoluozhi.event.IMessageEvent;
import com.xiaoluozhi.websocket.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO 自动同意添加好友
public class AutoFriend implements IMessageEvent {
    @Override
    public int weight() {
        return 100;
    }

    @Override
    public boolean onMessage(Message message) {
        if ("friend".equals(message.getRequestType())) {
            Request<RePostParams> request = new Request<>();
            request.setAction("set_friend_add_request");
            RePostParams rePostParams = new RePostParams();
            rePostParams.setFlag(message.getFlag());
            rePostParams.setApprove(true);
            request.setParams(rePostParams);

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);

            Client.sendMessage(JSONObject.toJSONString(request));

            Request<Params> paramsRequest = new Request<>();
            paramsRequest.setAction("send_msg");

            Params params = new Params();
            params.setUser_id("2686020087");

            params.setMessage("好友请求\n" + "请求发起人：" + message.getUser_id() + "\n发起时间：" + dateString + "\n已自动同意");

            params.setMessage_type(message.getMessage_type());
            params.setAuto_escape(true);
            paramsRequest.setParams(params);

            Client.sendMessage(JSONObject.toJSONString(paramsRequest));
            return true;
        }
        return false;
    }
}
