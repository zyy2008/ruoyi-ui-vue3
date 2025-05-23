package com.ruoyi.admin.domain.bo;

import lombok.Data;

/**
 * 分享图层集实体
 */
@Data
public class LayerSetShareBo {
    /**
     * 图层集id
     */
    private Long layerSetId;

    /**
     * 用户id
     */
    private Long[] userId;

    /**
     * 部门id
     */
    private Long[] deptId;
}
