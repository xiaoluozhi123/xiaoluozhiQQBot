package com.xiaoluozhi.entity.buffentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO Buff搜索api
@NoArgsConstructor
@Data
public class BuffSearchApi {
    // 页数
    @JsonProperty("page")
    private Integer page;
    // 每页显示个数
    @JsonProperty("page_size")
    private Integer pageSize;
    // 搜索关键字
    @JsonProperty("search")
    private String search;
    // 游戏名
    @JsonProperty("game")
    private String game;
    // 游戏id
    @JsonProperty("appid")
    private Integer appid;
}
