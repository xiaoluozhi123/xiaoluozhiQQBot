package com.xiaoluozhi.entity.setuentity;

import com.alibaba.fastjson2.annotation.JSONAutowired;
import lombok.Data;

@Data
public class SeTuEntity {
    // 作品 pid
    private int pid;
    // 作品所在页
    private int p;
    // 作者 uid
    private int uid;
    // 作品标题
    private String title;
    // 作者名（入库时，并过滤掉 @ 及其后内容）
    private String author;
    // 作者名（入库时，并过滤掉 @ 及其后内容）
    private boolean r18;
    // 原图宽度 px
    private int width;
    // 原图高度 px
    private int height;
    // 作品标签，包含标签的中文翻译（有的话）
    private String[] tags;
    // 图片扩展名
    private String ext;
    // 是否是 AI 作品，0 未知（旧画作或字段未更新），1 不是，2 是
    private int aiType;
    // 作品上传日期；时间戳，单位为毫秒
    private int uploadDate;
    // 包含了所有指定size的图片地址
    private SeTuUrl urls;
}