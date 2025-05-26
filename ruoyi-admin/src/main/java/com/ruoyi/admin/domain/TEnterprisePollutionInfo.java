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
 * 企业污染源对象 t_enterprise_pollution_info
 *
 * @author ruoyi
 * @date 2025-05-26
 */
@Data
@ApiModel("企业污染源")
public class TEnterprisePollutionInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 产品生产年份范围（例如：2020-2023）
     */
    @Excel(name = "产品生产年份范围", readConverterExp = "例=如：2020-2023")
    @ApiModelProperty("产品生产年份范围")
    private String productionYearRange;

    /**
     * 产品单位（例如：吨、件）
     */
    @Excel(name = "产品单位", readConverterExp = "例=如：吨、件")
    @ApiModelProperty("产品单位")
    private String productUnit;

    /**
     * 年均产量
     */
    @Excel(name = "年均产量")
    @ApiModelProperty("年均产量")
    private String productAvgOutput;

    /**
     * 原料名称
     */
    @Excel(name = "原料名称")
    @ApiModelProperty("原料名称")
    private String materialName;

    /**
     * 原料使用年份范围
     */
    @Excel(name = "原料使用年份范围")
    @ApiModelProperty("原料使用年份范围")
    private String materialUseYearRange;

    /**
     * 原料单位
     */
    @Excel(name = "原料单位")
    @ApiModelProperty("原料单位")
    private String materialUnit;

    /**
     * 原料年均用量
     */
    @Excel(name = "原料年均用量")
    @ApiModelProperty("原料年均用量")
    private String materialAvgUsage;

    /**
     * 生产线名称
     */
    @Excel(name = "生产线名称")
    @ApiModelProperty("生产线名称")
    private String processLineName;

    /**
     * 生产线经度
     */
    @Excel(name = "生产线经度")
    @ApiModelProperty("生产线经度")
    private String longitude;

    /**
     * 生产线纬度
     */
    @Excel(name = "生产线纬度")
    @ApiModelProperty("生产线纬度")
    private String latitude;

    /**
     * 投产时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投产时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("投产时间")
    private Date startupDate;

    /**
     * 工艺描述
     */
    @Excel(name = "工艺描述")
    @ApiModelProperty("工艺描述")
    private String processDescription;

    /**
     * 是否存在泄漏（是/否/不详）
     */
    @Excel(name = "是否存在泄漏", readConverterExp = "是=/否/不详")
    @ApiModelProperty("是否存在泄漏")
    private String leakStatus;

    /**
     * 车间面积（平方米）
     */
    @Excel(name = "车间面积", readConverterExp = "平=方米")
    @ApiModelProperty("车间面积")
    private String workshopArea;

    /**
     * 储存区名称
     */
    @Excel(name = "储存区名称")
    @ApiModelProperty("储存区名称")
    private String storageAreaName;

    /**
     * 储存区经度
     */
    @Excel(name = "储存区经度")
    @ApiModelProperty("储存区经度")
    private String storageLongitude;

    /**
     * 储存区纬度
     */
    @Excel(name = "储存区纬度")
    @ApiModelProperty("储存区纬度")
    private String storageLatitude;

    /**
     * 储罐数量
     */
    @Excel(name = "储罐数量")
    @ApiModelProperty("储罐数量")
    private Long tankCount;

    /**
     * 储存物料名称
     */
    @Excel(name = "储存物料名称")
    @ApiModelProperty("储存物料名称")
    private String storedMaterial;

    /**
     * 是否为危化品（true/false）
     */
    @Excel(name = "是否为危化品", readConverterExp = "t=rue/false")
    @ApiModelProperty("是否为危化品")
    private String isHazardous;

    /**
     * 储存量
     */
    @Excel(name = "储存量")
    @ApiModelProperty("储存量")
    private String storageAmount;

    /**
     * 储存单位
     */
    @Excel(name = "储存单位")
    @ApiModelProperty("储存单位")
    private String storageUnit;

    /**
     * 储存开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "储存开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("储存开始时间")
    private Date storageStartDate;

    /**
     * 是否存在泄漏（是/否/不详）
     */
    @Excel(name = "是否存在泄漏", readConverterExp = "是=/否/不详")
    @ApiModelProperty("是否存在泄漏")
    private String storageLeakStatus;

    /**
     * 备注信息
     */
    @Excel(name = "备注信息")
    @ApiModelProperty("备注信息")
    private String storageRemark;

    /**
     * 储存区面积（平方米）
     */
    @Excel(name = "储存区面积", readConverterExp = "平=方米")
    @ApiModelProperty("储存区面积")
    private String storageArea;

    /**
     * 是否存在废气治理设施（true/false）
     */
    @Excel(name = "是否存在废气治理设施", readConverterExp = "t=rue/false")
    @ApiModelProperty("是否存在废气治理设施")
    private String gasTreatmentExists;

    /**
     * 废气排放总量
     */
    @Excel(name = "废气排放总量")
    @ApiModelProperty("废气排放总量")
    private String totalGasEmission;

    /**
     * 废气排放单位（例如：立方米/年）
     */
    @Excel(name = "废气排放单位", readConverterExp = "例=如：立方米/年")
    @ApiModelProperty("废气排放单位")
    private String gasEmissionUnit;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private Long deptId;

}
