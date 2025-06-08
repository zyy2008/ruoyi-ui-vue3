package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地块污染情况分析对象 t_land_pollution_analysis
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Data
@ApiModel("地块污染情况分析")
public class TLandPollutionAnalysis extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 疑似土壤污染区名称
     */
    @ApiModelProperty("疑似土壤污染区名称")
    @Excel(name = "疑似土壤污染区名称")
    private String suspectedPollutionAreaName;

    /**
     * 特征污染物名称
     */
    @Excel(name = "特征污染物名称")
    @ApiModelProperty("特征污染物名称")
    private String characteristicPollutantName;

    /**
     * 绑定的污染源
     */
    @Excel(name = "绑定的污染源")
    @ApiModelProperty("绑定的污染源")
    private String enterprisePollutionInfoId;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
