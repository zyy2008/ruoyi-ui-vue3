package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 环境污染事故发生情况对象 t_pollution_incident
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Data
@ApiModel("环境污染事故发生情况")
public class TPollutionIncident extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 发生时间
     */
    @Excel(name = "发生时间")
    @ApiModelProperty("发生时间")
    private String incidentTime;

    /**
     * 事故发生地点
     */
    @Excel(name = "事故发生地点")
    @ApiModelProperty("事故发生地点")
    private String location;

    /**
     * 事故发生坐标
     */
    @Excel(name = "事故发生坐标")
    @ApiModelProperty("事故发生坐标")
    private String coordinates;

    /**
     * 突发环境事件等级
     */
    @Excel(name = "突发环境事件等级")
    @ApiModelProperty("突发环境事件等级")
    private String incidentLevel;

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
