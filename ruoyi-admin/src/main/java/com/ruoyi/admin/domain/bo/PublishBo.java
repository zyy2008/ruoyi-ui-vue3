package com.ruoyi.admin.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * 发布接收参数
 */
@Data
public class PublishBo {
    /**
     * 发布图层
     */
    private List<PublishLayerBo> publishLayer;

    /**
     * 基地id
     */
    private Long baseId;

    /**
     * 工作空间
     */
    private String workspace;

    /**
     * 仓库
     */
    private String datastore;

    /**
     * 是否覆盖
     */
    private boolean forcePublish = false;

}
