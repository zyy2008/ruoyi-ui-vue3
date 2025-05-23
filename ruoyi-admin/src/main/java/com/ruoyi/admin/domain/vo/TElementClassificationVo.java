package com.ruoyi.admin.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 要素分类对象 t_element_classification
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
@Data
public class TElementClassificationVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 要素大类名称 */
    private String name;

    /** 管线及其附属物 */
    private String pipelineCategorize;

    /** 代码 */
    private String code;

    private List<TElementClassificationNormVo> elementClassificationNormVoList;

}
