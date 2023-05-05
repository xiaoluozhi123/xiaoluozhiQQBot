package com.xiaoluozhi.entity.setuentity;

import lombok.Data;

@Data
public class SeTuApi {
    // 0为非R18，1为R18，2为混合（在库中的分类，不等同于作品本身的 R18 标识）
    private int r18;
    // 一次返回的结果数量，范围为1到20；在指定关键字或标签的情况下，结果数量可能会不足指定的数量
    private int num;
    // 返回指定uid作者的作品，最多20个
    private int[] uid;
    // 返回从标题、作者、标签中按指定关键字模糊匹配的结果，大小写不敏感，性能和准度较差且功能单一，建议使用tag代替
    private String keyword;
    // 返回匹配指定标签的作品
    private String[] tag;
    // 返回指定图片规格的地址
    private String[] size;
    // 设置图片地址所使用的在线反代服务
    private String proxy;
    // 返回在这个时间及以后上传的作品；时间戳，单位为毫秒
    private int dateAfter;
    // 返回在这个时间及以前上传的作品；时间戳，单位为毫秒
    private int dateBefore;
    // 禁用对某些缩写keyword和tag的自动转换
    private boolean dsc;
    // 禁用对某些缩写keyword和tag的自动转换
    private boolean excludeAI;
}
