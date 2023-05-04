package com.xiaoluozhi.websocket;

import com.xiaoluozhi.Start;

// TODO 重连方法
public class ReConnection implements Runnable {
    @Override
    public void run() {
        while (true) {
            if (Client.connect(Start.env.getProperty("socket.url"))) {
                break;
            } else {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 重连方法
    public static void reConnect() {
        new Thread(new ReConnection()).start();
    }
}
