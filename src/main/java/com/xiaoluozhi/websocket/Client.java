package com.xiaoluozhi.websocket;

import com.alibaba.fastjson2.JSONObject;
import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.event.Subject;
import com.xiaoluozhi.function.report.AutoAddGroup;
import com.xiaoluozhi.function.report.AutoFriend;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

// TODO 机器人客户端类
@ClientEndpoint
public class Client {
    private Session session;
    private static Client INSTANCE;
    // 重连标志
    private volatile static boolean reConnection = false;

    // 构造方法
    private Client(String url) throws DeploymentException, IOException {
        session = ContainerProvider.getWebSocketContainer().connectToServer(this, URI.create(url));
    }

    // 连接方法
    public synchronized static boolean connect(String url) {
        try {
            INSTANCE = new Client(url);
            reConnection = false;
            return true;
        } catch (Exception e) {
            System.out.println("WebSocket连接失败，尝试重连");
            return false;
        }
    }

    // 重连方法
    public synchronized static void reConnect() {
        if (!reConnection) {
            reConnection = true;
            if (INSTANCE != null) {
                INSTANCE.session = null;
                INSTANCE = null;
            }
            ReConnection.reConnect();
        }
    }

    // 连接成功
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("正向WebSocket连接成功");
    }

    // 收到消息
    @OnMessage
    public void onMessage(String json) {
        System.out.println(json);
        Message message = JSONObject.parseObject(json, Message.class);
        if ("message".equals(message.getPost_type())) {
            Subject.change(message);
        } else if ("request".equals(message.getPost_type())) {
            if ("friend".equals(message.getRequestType())) {
                AutoFriend autoFriend = new AutoFriend();
                autoFriend.onMessage(message);
            } else if ("group".equals(message.getRequestType()) && "invite".equals(message.getSubType())) {
                AutoAddGroup autoAddGroup = new AutoAddGroup();
                autoAddGroup.onMessage(message);
            }
        }
    }

    // 连接关闭
    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket连接关闭");
        reConnect();
    }

    // 连接异常
    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
        System.out.println("WebSocket连接异常");
        reConnect();
    }

    // 发送消息
    public static void sendMessage(String json) {
        Client.INSTANCE.session.getAsyncRemote().sendText(json);
    }
}
