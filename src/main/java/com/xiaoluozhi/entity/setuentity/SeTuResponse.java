package com.xiaoluozhi.entity.setuentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// TODO 色图响应实体类
@NoArgsConstructor
@Data
public class SeTuResponse {
    // 错误信息
    @JsonProperty("error")
    private String error;
    // 色图数组
    @JsonProperty("data")
    private List<DataDTO> data;

    // 色图数组实体类
    @NoArgsConstructor
    @Data
    public static class DataDTO {
        // 作品 pid
        @JsonProperty("pid")
        private Integer pid;
        // 作品所在页
        @JsonProperty("p")
        private Integer p;
        // 作者 uid
        @JsonProperty("uid")
        private Integer uid;
        // 作品标题
        @JsonProperty("title")
        private String title;
        // 作者名（入库时，并过滤掉 @ 及其后内容）
        @JsonProperty("author")
        private String author;
        // 是否 R18（在库中的分类，不等同于作品本身的 R18 标识）
        @JsonProperty("r18")
        private Boolean r18;
        // 原图宽度 px
        @JsonProperty("width")
        private Integer width;
        // 原图高度 px
        @JsonProperty("height")
        private Integer height;
        // 作品标签，包含标签的中文翻译（有的话）
        @JsonProperty("tags")
        private List<String> tags;
        // 图片扩展名
        @JsonProperty("ext")
        private String ext;
        // 是否是 AI 作品，0 未知（旧画作或字段未更新），1 不是，2 是
        @JsonProperty("aiType")
        private Integer aiType;
        // 作品上传日期；时间戳，单位为毫秒
        @JsonProperty("uploadDate")
        private Long uploadDate;
        // 包含了所有指定size的图片地址
        @JsonProperty("urls")
        private UrlsDTO urls;

        // urls实体类
        @NoArgsConstructor
        @Data
        public static class UrlsDTO {
            // 图片地址
            @JsonProperty("original")
            private String original;
        }
    }
}
