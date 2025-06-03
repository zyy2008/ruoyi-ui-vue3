package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行业分类对象 t_dc_industry
 *
 * @author ruoyi
 * @date 2025-06-03
 */
@Data
@ApiModel("行业分类")
public class TDcIndustry extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 行业名称
     */
    @Excel(name = "行业名称")
    @ApiModelProperty("行业名称")
    private String industryCategory;

    /**
     * 行业编码
     */
    @Excel(name = "行业编码")
    @ApiModelProperty("行业编码")
    private String code;

    /**
     * 绑定的大类
     */
    @Excel(name = "绑定的大类")
    @ApiModelProperty("绑定的大类")
    private String pCode;

}
