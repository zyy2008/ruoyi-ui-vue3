package com.ruoyi.admin.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 要素分类编码规范对象 t_element_classification_norm
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@Data
public class TElementClassificationNorm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 编码规范名称
     */
    @Excel(name = "编码规范名称")
    private String name;

    /**
     * 代码
     */
    @Excel(name = "代码")
    private String code;

    /**
     * 几何特征
     */
    @Excel(name = "几何特征")
    private String geometricFeatures;

    /**
     * 属性表
     */
    @Excel(name = "属性表")
    private String attribute;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    /**
     * RGB值
     */
    @Excel(name = "RGB值")
    private String rgb;

    /**
     * 要素大类ID
     */
    @Excel(name = "要素大类ID")
    private Long elmentId;

    /**
     * 要素大类名称
     */
    private String elmentName;

    /**
     * 要素分类信息
     */
    private List<TElementClassification> tElementClassificationList;

}
