package com.ruoyi.admin.domain.bo;

import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;

/**
 * 图层集对象 t_layer_set
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@Data
public class TLayerSetBo extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图层ID
     */
    private String layerId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 是否是组节点
     */
    private Boolean isGroup;

    /**
     * 父节点
     */
    private Long pid;

    /**
     * 父id，分享后绑定的主节点
     */
    private Long layerSetId;

    /**
     * 基地（字典）
     */
    private Long baseId;

    /**
     * 规范id
     */
    private Long[] norm;
}
