package com.ruoyi.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 指标分类对象 t_indicators
 *
 * @author ruoyi
 * @date 2025-06-15
 */
public class TIndicators extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 值 */
    @Excel(name = "值")
    private Long value;

    /** 是否国标 */
    @Excel(name = "是否国标")
    private String isNationalStandard;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String isEnabled;

    /** 归属部门 */
    @Excel(name = "归属部门")
    private String deptId;

    /** Java属性 */
    @Excel(name = "Java属性")
    private String attribute;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setValue(Long value)
    {
        this.value = value;
    }

    public Long getValue()
    {
        return value;
    }
    public void setIsNationalStandard(String isNationalStandard)
    {
        this.isNationalStandard = isNationalStandard;
    }

    public String getIsNationalStandard()
    {
        return isNationalStandard;
    }
    public void setIsEnabled(String isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled()
    {
        return isEnabled;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptId()
    {
        return deptId;
    }
    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }

    public String getAttribute()
    {
        return attribute;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("value", getValue())
                .append("isNationalStandard", getIsNationalStandard())
                .append("isEnabled", getIsEnabled())
                .append("deptId", getDeptId())
                .append("attribute", getAttribute())
                .toString();
    }
}
