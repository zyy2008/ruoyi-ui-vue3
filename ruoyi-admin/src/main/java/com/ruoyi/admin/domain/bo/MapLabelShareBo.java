package com.ruoyi.admin.domain.bo;

import lombok.Data;

/**
 * 分享地图标注实体
 */
@Data
public class MapLabelShareBo {
    /**
     * 地图标注id
     */
    private Long[] mapLabelId;
    /**
     * 用户id
     */
    private Long[] userId;
    /**
     * 基地id
     */
    private Long[] baseId;
    /**
     * 部门id
     */
    private Long[] deptId;
    /**
     * 专业id
     */
    private Long[] thisDeptId;
}
