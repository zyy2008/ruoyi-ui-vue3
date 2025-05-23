package com.ruoyi.admin.domain.vo;

import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;

import java.util.List;

/**
 * 图层集对象 t_layer_set
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@Data
public class TLayerSetVo extends TreeEntity {
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
    private String deptId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

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
     * 基地
     */
    private Long baseId;

    /**
     * 要素大类
     */
    private List<TElementClassificationVo> elementClassificationVoList;
    /** 基地名称*/
    private String baseName;
    /** 部门名称*/
    private String deptName;
}
