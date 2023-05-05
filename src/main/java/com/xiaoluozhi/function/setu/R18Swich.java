package com.xiaoluozhi.function.setu;

import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.event.IMessageEvent;
import com.xiaoluozhi.util.SendUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class R18Swich implements IMessageEvent {
    // r18标识
    public static int r18 = 0;
    // 主人QQ
    @Value("${bot.master}")
    private String masterQQ;

    @Override
    public int weight() {
        return 9;
    }

    @Override
    public boolean onMessage(Message message) {
        if ("开启R18".equals(message.getMessage())) {
            if (masterQQ.equals(message.getUser_id())) {
                r18 = 1;
                SendUtil.sendText(message, "开启R18涩图成功");
                return true;
            } else {
                System.out.println(masterQQ);
                System.out.println(message.getUser_id());
                SendUtil.sendText(message, "你不是我的主人");
                return true;
            }
        }
        if ("关闭R18".equals(message.getMessage())) {
            if (masterQQ.equals(message.getUser_id())) {
                r18 = 0;
                SendUtil.sendText(message, "关闭R18涩图成功");
                return true;
            } else {
                SendUtil.sendText(message, "你不是我的主人");
                return true;
            }
        }
        if ("开启混合模式".equals(message.getMessage())) {
            if (masterQQ.equals(message.getUser_id())) {
                r18 = 2;
                SendUtil.sendText(message, "开启混合涩图成功");
                return true;
            } else {
                SendUtil.sendText(message, "你不是我的主人");
                return true;
            }
        }
        return false;
    }
}
