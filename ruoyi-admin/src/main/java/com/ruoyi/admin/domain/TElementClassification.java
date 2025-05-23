package com.ruoyi.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 要素分类对象 t_element_classification
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
public class TElementClassification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 要素大类名称 */
    @Excel(name = "要素大类名称")
    private String name;

    /** 管线及其附属物 */
    @Excel(name = "管线及其附属物")
    private String pipelineCategorize;

    /** 代码 */
    @Excel(name = "代码")
    private String code;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPipelineCategorize(String pipelineCategorize) 
    {
        this.pipelineCategorize = pipelineCategorize;
    }

    public String getPipelineCategorize() 
    {
        return pipelineCategorize;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("pipelineCategorize", getPipelineCategorize())
            .append("code", getCode())
            .toString();
    }
}
