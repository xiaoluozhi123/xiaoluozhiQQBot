package com.xiaoluozhi;

import com.xiaoluozhi.websocket.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// TODO 启动时连接websocket
@Component
public class Start implements CommandLineRunner {
    // 配置文件
    public static Environment env;

    // 自动装配配置文件
    @Autowired
    public void setEnv(Environment env) {
        Start.env = env;
    }

    // 启动时连接websocket
    @Override
    public void run(String... args)  {
        // 连接失败则重连
        if (!Client.connect(env.getProperty("socket.url"))) {
            Client.reConnect();
        }
    }

}
