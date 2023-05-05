package com.xiaoluozhi.event;

import com.xiaoluozhi.entity.botentity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO 功能排序
@Component
public class Subject {
    private static Map<String, IMessageEvent> map;
    private static List<IMessageEvent> list;

    @Autowired
    public void setMap(Map<String, IMessageEvent> map) {
        Subject.map = map;
    }

    @PostConstruct
    public void init() {
        list = new ArrayList<>(map.size());
        list.addAll(map.values());

        // 根据权重降序排序
        list = list.stream().sorted(Comparator.comparing(IMessageEvent::weight).reversed()).collect(Collectors.toList());
    }

    // 遍历集合，并执行方法
    public static void change(Message message) {
        for (IMessageEvent iMessageEvent : list) {
            if (iMessageEvent.onMessage(message)) {
                break;
            }
        }
    }
}
