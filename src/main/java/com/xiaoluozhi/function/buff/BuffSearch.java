package com.xiaoluozhi.function.buff;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.entity.buffentity.BuffSearchEntity;
import com.xiaoluozhi.enums.XmlAndJson;
import com.xiaoluozhi.event.IMessageEvent;
import com.xiaoluozhi.util.SendUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

// TODO buff搜索功能
@Component
public class BuffSearch implements IMessageEvent {
    private Integer page = 1;
    @Value("${buff.cookie}")
    private String cookie;

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public boolean onMessage(Message message) {
        if (message.getMessage().matches("[buff搜索]+[ ].+")) {

            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setMaxConnTotal(100)
                    .setMaxConnPerRoute(20)
                    .setConnectionTimeToLive(30, TimeUnit.SECONDS)
                    .build();


            String key = message.getMessage().replaceAll("[buff搜索]+[ ]", "");
            // url编码
            try {
                key = URLEncoder.encode(key, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            String url = "https://buff.163.com/api/market/goods?page=1&page_size=12&search=" + key + "&game=csgo&appid=730";

            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("CooKie", cookie);
            String result = null;

            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                result = EntityUtils.toString(response.getEntity());
                httpClient.close();
                httpGet.clone();
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }

            BuffSearchEntity buffSearchEntity = JSONObject.parseObject(result, BuffSearchEntity.class);
            if (buffSearchEntity.getData().getItems().size() != 0) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"1\" templateID=\"0\" action=\"\" brief=\"Buff搜索结果\" sourceMsgId=\"0\" url=\"\" flag=\"5\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"3\" advertiser_id=\"0\" aid=\"0\"><picture cover=\"");
                stringBuilder.append(buffSearchEntity.getData().getItems().get(0).getGoodsInfo().getIconUrl());
                stringBuilder.append("\" w=\"0\" h=\"0\" /></item><item layout=\"6\" advertiser_id=\"0\" aid=\"0\">");
                for (int i = 0; i < buffSearchEntity.getData().getItems().size(); i++) {
                    BuffSearchEntity.DataDTO.ItemsDTO itemsDTO = buffSearchEntity.getData().getItems().get(i);
                    stringBuilder.append("<title size=\"25\" color=\"#CC2EFA\" style=\"0\">");
                    stringBuilder.append((i + 1) + "-" + itemsDTO.getName());
                    stringBuilder.append("</title><hr hidden=\"false\" style=\"0\" />");

                    stringBuilder.append("<title size=\"25\" color=\"#CC2EFA\" style=\"0\">");
                    stringBuilder.append("\t\t\t" + "当前最低售价：" + itemsDTO.getSellMinPrice() + "元");
                    stringBuilder.append("</title><hr hidden=\"false\" style=\"0\" />");

                    stringBuilder.append("<title size=\"25\" color=\"#CC2EFA\" style=\"0\">");
                    stringBuilder.append("\t\t\t" + "当前最高求购价：" + itemsDTO.getBuyMaxPrice() + "元");
                    stringBuilder.append("</title><hr hidden=\"false\" style=\"0\" />");

                    stringBuilder.append("<title size=\"25\" color=\"#CC2EFA\" style=\"0\">");
                    stringBuilder.append("\t\t\t" + "Steam售价：" + itemsDTO.getGoodsInfo().getSteamPriceCny() + "元");
                    stringBuilder.append("</title><hr hidden=\"false\" style=\"0\" />");
                }
                stringBuilder.append("<title size=\"25\" color=\"#CC2EFA\" style=\"0\"> 搜索结果最多显示12条 </title></item><source name=\"\" icon=\"\" action=\"\" appid=\"0\" /></msg>");

                if (!"private".equals(message.getMessage_type())) {
                    SendUtil.sendText(message, "已私聊发送搜索结果");
                }

                SendUtil.sendXmlAndJson(message, XmlAndJson.xml, stringBuilder.toString());
                // SendUtil.sendText(message, stringBuilder.toString());
            } else {
                SendUtil.sendText(message, "搜索没有结果，换个关键词试试吧！");
            }


            return true;
        }
        return false;
    }
}
