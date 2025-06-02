package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 指标分类对象 t_indicators
 *
 * @author ruoyi
 * @date 2025-06-02
 */
@Data
@ApiModel("指标分类")
public class TIndicators extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 类型
     */
    @Excel(name = "类型")
    @ApiModelProperty("类型")
    private String type;

    /**
     * 值
     */
    @Excel(name = "值")
    @ApiModelProperty("值")
    private Long value;

    /**
     * 是否国标
     */
    @Excel(name = "是否国标")
    @ApiModelProperty("是否国标")
    private String isNationalStandard;

    /**
     * 是否启用
     */
    @Excel(name = "是否启用")
    @ApiModelProperty("是否启用")
    private String isEnabled;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;
}
