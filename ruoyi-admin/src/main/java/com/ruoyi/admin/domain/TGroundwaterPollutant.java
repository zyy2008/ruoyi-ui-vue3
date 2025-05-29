package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地下水监测发现的超标污染物信息对象 t_groundwater_pollutant
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Data
@ApiModel("地下水监测发现的超标污染物信息")
public class TGroundwaterPollutant extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 关联的地下水监测记录ID
     */
    @ApiModelProperty("关联的地下水监测记录ID")
    private Long monitoringId;

    /**
     * 超标污染物名称
     */
    @Excel(name = "超标污染物名称")
    @ApiModelProperty("超标污染物名称")
    private String pollutantName;

    /**
     * 最大浓度（mg/l）
     */
    @Excel(name = "最大浓度", readConverterExp = "m=g/l")
    @ApiModelProperty("最大浓度")
    private String maxConcentrationMgl;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private Long deptId;

}
