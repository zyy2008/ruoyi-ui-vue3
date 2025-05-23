package com.ruoyi.admin.domain.vo;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 要素分类编码规范对象 t_element_classification_norm
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
@Data
public class TElementClassificationNormVo extends BaseEntity
{

    /** 主键ID */
    private Long id;

    /** 编码规范名称 */
    private String name;

    /** 代码 */
    private String code;

    /** 几何特征 */
    private String geometricFeatures;

    /** 属性表 */
    private String attribute;

    /** 颜色 */
    private String color;

    /** RGB值 */
    private String rgb;

    /** 要素大类ID */
    private Long elmentId;

    /**
     * 是否可选
     */
    private boolean optional;

    /**
     * 图层属性
     */
    private TLayer layer;
}
