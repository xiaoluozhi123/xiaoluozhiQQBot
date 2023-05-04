package com.xiaoluozhi;

import com.xiaoluozhi.websocket.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// TODO 启动时连接websocket
@Component
public class Start implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Client.connect("ws://175.24.206.88:8801");
    }
}
