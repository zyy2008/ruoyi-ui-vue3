package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 土壤监测发现的超标污染物信息对象 t_soil_pollutant
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Data
@ApiModel("土壤监测发现的超标污染物信息")
public class TSoilPollutant extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 关联的土壤监测记录ID
     */
    @ApiModelProperty("主键ID")
    private Long monitoringId;

    /**
     * 超标污染物名称
     */
    @Excel(name = "超标污染物名称")
    @ApiModelProperty("主键ID")
    private String pollutantName;

    /**
     * 最大浓度（mg/kg）
     */
    @Excel(name = "最大浓度", readConverterExp = "m=g/kg")
    @ApiModelProperty("主键ID")
    private String maxConcentrationMgkg;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("主键ID")
    private Long deptId;
}
