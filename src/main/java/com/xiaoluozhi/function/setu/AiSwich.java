package com.xiaoluozhi.function.setu;

import com.xiaoluozhi.entity.botentity.Message;
import com.xiaoluozhi.event.IMessageEvent;
import com.xiaoluozhi.util.SendUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AiSwich implements IMessageEvent {
    // ai标识
    public static boolean ai = false;
    // 主人QQ
    @Value("${bot.master}")
    private String masterQQ;

    @Override
    public int weight() {
        return 9;
    }

    @Override
    public boolean onMessage(Message message) {
        if ("开启排除AI".equals(message.getMessage())) {
            if (masterQQ.equals(message.getUser_id())) {
                ai = true;
                SendUtil.sendText(message, "开启排除ai涩图成功");
                return true;
            } else {
                System.out.println(masterQQ);
                System.out.println(message.getUser_id());
                SendUtil.sendText(message, "你不是我的主人");
                return true;
            }
        }
        if ("关闭排除AI".equals(message.getMessage()) && "message".equals(message.getPost_type())) {
            if (masterQQ.equals(message.getUser_id())) {
                ai = false;
                SendUtil.sendText(message, "关闭排除ai涩图成功");
                return true;
            } else {
                SendUtil.sendText(message, "你不是我的主人");
                return true;
            }
        }
        return false;
    }
}
