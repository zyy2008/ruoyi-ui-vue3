package com.ruoyi.admin.domain.bo;

import lombok.Data;

/**
 * 发布图层接收参数
 */
@Data
public class PublishLayerBo {
    /**
     * 名称
     */
    private String name;
    /**
     * 要素编码
     */
    private String code;
    /**
     * 图层参数
     */
    private String param;
    /**
     * 对应表名
     */
    private String tableName;
    /**
     * 展示样式
     */
    private String style;
}
