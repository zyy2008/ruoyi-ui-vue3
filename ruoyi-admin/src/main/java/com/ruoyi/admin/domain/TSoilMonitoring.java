package com.ruoyi.admin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 土壤环境调查监测信息对象 t_soil_monitoring
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Data
@ApiModel("土壤环境调查监测信息")
public class TSoilMonitoring extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 是否曾开展土壤环境调查监测
     */
    @Excel(name = "是否曾开展土壤环境调查监测")
    @ApiModelProperty("是否曾开展土壤环境调查监测")
    private String hasMonitoring;

    /**
     * 调查时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调查时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("调查时间")
    private String surveyDate;

    /**
     * 是否发现超标污染物
     */
    @Excel(name = "是否发现超标污染物")
    @ApiModelProperty("是否发现超标污染物")
    private String pollutantsExceed;

    /**
     * 数据来源
     */
    @Excel(name = "数据来源")
    @ApiModelProperty("数据来源")
    private String source;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
