package com.ruoyi.admin.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 地图标注对象 t_map_label
 *
 * @author ruoyi
 * @date 2024-10-10
 */
@Data
public class TMapLabel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 标注对象的JSON字符串
     */
    @Excel(name = "标注对象的JSON字符串")
    private String data;

    /**
     * 标注类型
     */
    @Excel(name = "标注类型")
    private String dataType;

    /**
     * 部门ID为了数据做权限划分
     */
    private Long deptId;

    /**
     * 用户ID为了数据做权限划分
     */
    private Long userId;

    /**
     * 地图视角的JSON字符串
     */
    @Excel(name = "地图视角的JSON字符串")
    private String view;

    /**
     * 图层集ID
     */
    private Long layerSetId;

    /**
     * 地图标注id，分享功能绑定的父节点
     */
    private Long mapLabelId;

    /**
     * 所属基地
     */
    private Long baseId;
    /**
     * 所属基地名称
     */
    private String baseName;
}
