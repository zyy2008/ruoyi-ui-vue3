package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实时数据监测对象 t_realtime_water_quality_monitoring
 *
 * @author ruoyi
 * @date 2025-06-13
 */
@Data
@ApiModel("实时数据监测")
public class TRealtimeWaterQualityMonitoring extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 监测数据唯一标识
     */
    private String id;

    /**
     * 酸碱度，pH值
     */
    @ApiModelProperty("酸碱度，pH值")
    private String phValue;

    /**
     * 温度，单位：摄氏度
     */
    @ApiModelProperty("温度，单位：摄氏度")
    private String temperature;

    /**
     * 液位，单位：米
     */
    @ApiModelProperty("液位，单位：米")
    private String waterLevel;

    /**
     * 溶解氧含量，单位：mg/L
     */
    @ApiModelProperty("溶解氧含量，单位：mg/L")
    private String dissolvedOxygen;

    /**
     * 电导率，单位：μS/cm
     */
    @ApiModelProperty("电导率，单位：μS/cm")
    private String conductivity;

    /**
     * 氨氮含量，单位：mg/L
     */
    @ApiModelProperty("氨氮含量，单位：mg/L")
    private String ammoniaNitrogen;
    /**
     * 氧化还原电位，单位：mV
     */
    @ApiModelProperty("氧化还原电位，单位：mV")
    private String redoxPotential;

    /**
     * 数据监测时间
     */
    @ApiModelProperty("数据监测时间")
    private String monitoringTime;

    /**
     * 数据同步到系统的时间
     */
    @ApiModelProperty("数据同步到系统的时间")
    private String synchronizationTime;

    /**
     * 监测井编号或名称
     */
    @ApiModelProperty("监测井编号或名称")
    private String monitoringWell;

    /**
     * 监测井位置
     */
    @ApiModelProperty("监测井位置")
    private String location;

}
