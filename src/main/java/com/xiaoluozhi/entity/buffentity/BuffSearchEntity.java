package com.xiaoluozhi.entity.buffentity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// TODO buff搜索实体类
@NoArgsConstructor
@Data
public class BuffSearchEntity {
    // 请求结果
    @JSONField(name = "code")
    private String code;
    // 响应数据
    @JSONField(name = "data")
    private DataDTO data;
    // 未知
    @JSONField(name = "msg")
    private Object msg;

    // 响应数据实体类
    @NoArgsConstructor
    @Data
    public static class DataDTO {
        // 搜索结果列表
        @JSONField(name = "items")
        private List<ItemsDTO> items;
        // 页数
        @JSONField(name = "page_num")
        private Integer pageNum;
        // 一页有多少个
        @JSONField(name = "page_size")
        private Integer pageSize;
        // 总计数
        @JSONField(name = "total_count")
        private Integer totalCount;
        // 总计页数
        @JSONField(name = "total_page")
        private Integer totalPage;

        @NoArgsConstructor
        @Data
        public static class ItemsDTO {
            // 游戏id
            @JSONField(name = "appid")
            private Integer appid;
            // 已添加书签
            @JSONField(name = "bookmarked")
            private Boolean bookmarked;
            // 当前求购最高价格
            @JSONField(name = "buy_max_price")
            private String buyMaxPrice;
            // 当前求购数量
            @JSONField(name = "buy_num")
            private Integer buyNum;
            // 是否可以还价
            @JSONField(name = "can_bargain")
            private Boolean canBargain;
            // 可以按锦标赛搜索
            @JSONField(name = "can_search_by_tournament")
            private Boolean canSearchByTournament;
            // 描述
            @JSONField(name = "description")
            private Object description;
            // 游戏
            @JSONField(name = "game")
            private String game;
            // 类型
            @JSONField(name = "goods_info")
            private GoodsInfoDTO goodsInfo;
            // 是否有历史价格记录
            @JSONField(name = "has_buff_price_history")
            private Boolean hasBuffPriceHistory;
            // 物品id
            @JSONField(name = "id")
            private Integer id;
            // 英文名
            @JSONField(name = "market_hash_name")
            private String marketHashName;
            // 市场最低价格
            @JSONField(name = "market_min_price")
            private String marketMinPrice;
            // 物品名称
            @JSONField(name = "name")
            private String name;
            // 快速出售价格
            @JSONField(name = "quick_price")
            private String quickPrice;
            // 当前在售最低价
            @JSONField(name = "sell_min_price")
            private String sellMinPrice;
            // 当前在售数量
            @JSONField(name = "sell_num")
            private Integer sellNum;
            // 市场出售参考价
            @JSONField(name = "sell_reference_price")
            private String sellReferencePrice;
            // 物品简称
            @JSONField(name = "short_name")
            private String shortName;
            // Steam市场网址
            @JSONField(name = "steam_market_url")
            private String steamMarketUrl;
            // 交易数量
            @JSONField(name = "transacted_num")
            private Integer transactedNum;

            // 市场类型实体类
            @NoArgsConstructor
            @Data
            public static class GoodsInfoDTO {
                // 图片url
                @JSONField(name = "icon_url")
                private String iconUrl;
                // 类型
                @JSONField(name = "info")
                private InfoDTO info;
                // 列表id
                @JSONField(name = "item_id")
                private Object itemId;
                // 原始图片url
                @JSONField(name = "original_icon_url")
                private String originalIconUrl;
                // Steam售价
                @JSONField(name = "steam_price")
                private String steamPrice;
                // Steam售价单位rmb
                @JSONField(name = "steam_price_cny")
                private String steamPriceCny;

                @NoArgsConstructor
                @Data
                public static class InfoDTO {
                    // 标签
                    @JSONField(name = "tags")
                    private TagsDTO tags;

                    @NoArgsConstructor
                    @Data
                    public static class TagsDTO {
                        @JSONField(name = "exterior")
                        private ExteriorDTO exterior;
                        // 质量
                        @JSONField(name = "quality")
                        private QualityDTO quality;
                        // 级别
                        @JSONField(name = "rarity")
                        private RarityDTO rarity;
                        // 类型
                        @JSONField(name = "type")
                        private TypeDTO type;
                        // 类型
                        @JSONField(name = "weapon")
                        private WeaponDTO weapon;

                        @NoArgsConstructor
                        @Data
                        public static class ExteriorDTO {
                            // 类别
                            @JSONField(name = "category")
                            private String category;
                            // 类别id
                            @JSONField(name = "id")
                            private Integer id;
                            // csgo内部名
                            @JSONField(name = "internal_name")
                            private String internalName;
                            // 中文名
                            @JSONField(name = "localized_name")
                            private String localizedName;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class QualityDTO {
                            // 类别
                            @JSONField(name = "category")
                            private String category;
                            // 类别id
                            @JSONField(name = "id")
                            private Integer id;
                            // csgo内部名
                            @JSONField(name = "internal_name")
                            private String internalName;
                            // 中文名
                            @JSONField(name = "localized_name")
                            private String localizedName;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class RarityDTO {
                            // 类别
                            @JSONField(name = "category")
                            private String category;
                            // 类别id
                            @JSONField(name = "id")
                            private Integer id;
                            // csgo内部名
                            @JSONField(name = "internal_name")
                            private String internalName;
                            // 中文名
                            @JSONField(name = "localized_name")
                            private String localizedName;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class TypeDTO {
                            // 类别
                            @JSONField(name = "category")
                            private String category;
                            // 类别id
                            @JSONField(name = "id")
                            private Integer id;
                            // csgo内部名
                            @JSONField(name = "internal_name")
                            private String internalName;
                            // 中文名
                            @JSONField(name = "localized_name")
                            private String localizedName;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class WeaponDTO {
                            // 类别
                            @JSONField(name = "category")
                            private String category;
                            // 类别id
                            @JSONField(name = "id")
                            private Integer id;
                            // csgo内部名
                            @JSONField(name = "internal_name")
                            private String internalName;
                            // 中文名
                            @JSONField(name = "localized_name")
                            private String localizedName;
                        }
                    }
                }
            }
        }
    }
}
