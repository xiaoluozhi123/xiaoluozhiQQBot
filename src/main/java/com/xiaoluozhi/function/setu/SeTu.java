package com.xiaoluozhi.function.setu;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.entity.setuentity.SeTuApi;
import com.xiaoluozhi.entity.setuentity.SeTuResponse;
import com.xiaoluozhi.event.IMessageEvent;
import com.xiaoluozhi.util.SendUtil;
import org.springframework.stereotype.Component;

@Component
public class SeTu implements IMessageEvent {
    // 接口地址
    private final String api = "https://api.lolicon.app/setu/v2";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 权重
    @Override
    public int weight() {
        return 10;
    }

    // 消息处理
    @Override
    public boolean onMessage(Message message) {
        if ("涩图".equals(message.getMessage()) || "色图".equals(message.getMessage())) {
            SeTuApi seTuApi = new SeTuApi();
            seTuApi.setR18(R18Swich.r18);
            seTuApi.setExcludeAI(AiSwich.ai);
            seTuApi.setNum(1);

            String result = HttpUtil.post(api, JSONObject.toJSONString(seTuApi), 5 * 1000);
            SeTuResponse seTuResponse;
            try {
                seTuResponse = objectMapper.readValue(result, SeTuResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (seTuResponse.getError().isEmpty()) {

                SendUtil.sendText(message, "获取涩图成功，正在发送，如没有发送成功则可能被拦截");
                for (SeTuResponse.DataDTO dto : seTuResponse.getData()) {
                    // 发送图片
                    SendUtil.sendImg(message, dto.getUrls().getOriginal());
                }
            } else {
                SendUtil.sendText(message, "获取涩图失败");
            }
            return true;
        }

        if ("真涩图".equals(message.getMessage()) || "真色图".equals(message.getMessage())) {
            if ("912119255".equals(message.getGroup_id())) {
                SeTuApi seTuApi = new SeTuApi();
                seTuApi.setR18(1);
                seTuApi.setExcludeAI(AiSwich.ai);
                seTuApi.setNum(5);

                String result = HttpUtil.post(api, JSONObject.toJSONString(seTuApi), 5 * 1000);
                SeTuResponse seTuResponse;
                try {
                    seTuResponse = objectMapper.readValue(result, SeTuResponse.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                if (seTuResponse.getError().isEmpty()) {

                    SendUtil.sendText(message, "获取涩图成功，正在发送，如没有发送成功则可能被拦截");
                    for (SeTuResponse.DataDTO dto : seTuResponse.getData()) {
                        // 发送图片
                        SendUtil.sendImg(message, dto.getUrls().getOriginal());
                    }
                } else {
                    SendUtil.sendText(message, "获取涩图失败");
                }
                return true;
            }
            return false;
        }

        return false;
    }
}
