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
     * 车间面积（平方米）
     */
    @ApiModelProperty("车间总面积")
    private String workshopArea;
    /**
     * 设施区域
     */
    @ApiModelProperty("设施区域（多选）")
    private String facilitiesAreas;
    /**
     * 平面布置图
     */
    @ApiModelProperty("平面布置图")
    private String floorPlan;
    /**
     * 主中间产物名称
     */
    @ApiModelProperty("主中间产物名称")
    private String mainIntermediateProductName;
    /**
     * 主要生产工艺流程图
     */
    @ApiModelProperty("主要生产工艺流程图")
    private String mainProcessFlowChart;
    /**
     * 主要生产工艺描述
     */
    @ApiModelProperty("主要生产工艺描述")
    private String mainProcessProductionDescription;
    /**
     * 企业是否开展过清洁生产审核
     */
    @ApiModelProperty("企业是否开展过清洁生产审核")
    private String isCleanProductionAudited;
    /**
     * 清洁生产审核时间
     */
    @ApiModelProperty("清洁生产审核时间")
    private String cleanAuditTime;
    /**
     * 清洁生产水平
     */
    @ApiModelProperty("清洁生产水平")
    private String cleanLevel;
    /**
     * 储罐、储槽等储存设施
     */
    @ApiModelProperty("储罐、储槽等储存设施")
    private String storageFacility;
    /**
     * 存放产品、原材料的或其他有毒有害物质的仓库等
     */
    @ApiModelProperty("存放产品、原材料的或其他有毒有害物质的仓库等")
    private String hazardousMaterialWarehouse;
    /**
     * 产品、原材料或其他有毒有害物质装卸区
     */
    @ApiModelProperty("产品、原材料或其他有毒有害物质装卸区")
    private String hazardousMaterialLoadingAndUnloadingArea;
    /**
     * 储存区面积（平方米）
     */
    @ApiModelProperty("储存区面积")
    private String storageArea;
    /**
     * 各储存区域防护措施
     */
    @ApiModelProperty("各储存区域防护措施")
    private String protectionMeasuresForStorageAreas;
    /**
     * 管道是否发生过泄漏
     */
    @ApiModelProperty("管道是否发生过泄漏")
    private String hasThereBeenAPipeLeak;
    /**
     * 地下管道管线图
     */
    @ApiModelProperty("地下管道管线图")
    private String undergroundPipeLineDiagram;
    /**
     * 是否存在废气治理设施（true/false）
     */
    @ApiModelProperty("是否存在废气治理设施")
    private String gasTreatmentExists;
    /**
     * 废气排放总量
     */
    @ApiModelProperty("废气排放量")
    private String totalGasEmission;
    /**
     * 废气中主要污染物名称
     */
    @ApiModelProperty("废气中主要污染物名称")
    private String mainPollutantsInEmissions;
    /**
     * 是否有废气在线监测装置
     */
    @ApiModelProperty("是否有废气在线监测装置")
    private String isThereAnOnlineMonitoringDeviceForEmissions;
    /**
     * 废气在线监测装置运行情况
     */
    @ApiModelProperty("废气在线监测装置运行情况")
    private String operationStatusOfOnlineMonitoringDeviceForEmissions;
    /**
     * 是否有废气治理设施
     */
    @ApiModelProperty("是否有废气治理设施")
    private String isThereAnEmissionTreatmentFacility;
    /**
     * 废气治理设施运行情况
     */
    @ApiModelProperty("废气治理设施运行情况")
    private String operationStatusOfEmissionTreatmentFacility;


    /**
     * 是否有废水排放
     */
    @ApiModelProperty("是否有废水排放")
    private String isThereWastewaterDischarge;
    /**
     * 废水在线监测装置运行情况
     */
    @ApiModelProperty("废水在线监测装置运行情况")
    private String operationStatusOfOnlineMonitoringDeviceForWastewater;
    /**
     * 废水排放量
     */
    @ApiModelProperty("废水排放量")
    private String wastewaterDischargeVolume;
    /**
     * 废水中主要污染物名称
     */
    @ApiModelProperty("废水中主要污染物名称")
    private String mainPollutantsInWastewater;
    /**
     * 是否有废水在线监测装置
     */
    @ApiModelProperty("是否有废水在线监测装置")
    private String isThereAnOnlineMonitoringDeviceForWastewater;
    /**
     * 是否有废水治理设施
     */
    @ApiModelProperty("是否有废水治理设施")
    private String isThereAWastewaterTreatmentFacility;
    /**
     * 废水治理设施运行情况
     */
    @ApiModelProperty("废水治理设施运行情况")
    private String operationStatusOfWastewaterTreatmentFacility;
    /**
     * 废水治理区坐标位置
     */
    @ApiModelProperty("废水治理区坐标位置")
    private String coordinatesOfWastewaterTreatmentArea;
    /**
     * 废水治理区面积
     */
    @ApiModelProperty("废水治理区面积")
    private String areaOfWastewaterTreatmentArea;
    /**
     * 废水治理区污染痕迹
     */
    @ApiModelProperty("废水治理区污染痕迹")
    private String pollutionTracesInWastewaterTreatmentArea;
    /**
     * 污染痕迹照片
     */
    @ApiModelProperty("污染痕迹照片")
    private String photosOfPollutionTraces;
    /**
     * 废水治理区防护措施
     */
    @ApiModelProperty("废水治理区防护措施")
    private String protectionMeasuresInWastewaterTreatmentArea;



    /**
     * 是否产生固体废物
     */
    @ApiModelProperty("是否产生固体废物")
    private String isSolidWasteGenerated;
    /**
     * 是否有固体废物贮存
     */
    @ApiModelProperty("是否有固体废物贮存")
    private String isSolidWasteStored;
    /**
     * 是否有危险废物自处置
     */
    @ApiModelProperty("是否有危险废物自处置")
    private String isHazardousWasteSelfDisposed;
    /**
     * 固废贮存处置区防护措施
     */
    @ApiModelProperty("固废贮存处置区防护措施")
    private String solidWasteStorageAndDisposalAreaProtectionMeasures;
    /**
     * 危废贮存处置区防护措施
     */
    @ApiModelProperty("危废贮存处置区防护措施")
    private String hazardousWasteStorageAndDisposalAreaProtectionMeasures;
    /**
     * 危险废物规范化管理考核评估结果
     */
    @ApiModelProperty("危险废物规范化管理考核评估结果")
    private String hazardousWasteStandardizedManagementAssessmentResults;
    /**
     * 环境污染事故发生情况
     */
    @ApiModelProperty("环境污染事故发生情况")
    private String environmentalPollutionAccidentOccurrence;
    /**
     * 事故基本情况描述
     */
    @ApiModelProperty("事故基本情况描述")
    private String basicDescriptionOfAccident;
    /**
     * 污染区域处理情况
     */
    @ApiModelProperty("污染区域处理情况")
    private String pollutionAreaTreatmentSituation;

    /**
     * 归属部门
     */
    @ApiModelProperty("归属部门")
    private String deptId;


    /**
     * 是否有废气排放
     */
    @ApiModelProperty("是否有废气排放")
    private String isWasteGasEmission;
    /**
     * 主要产品数据来源
     */
    @ApiModelProperty("主要产品数据来源")
    private String dataSourceOfProductionArea;
    /**
     * 原辅材料数据来源
     */
    @ApiModelProperty("原辅材料数据来源")
    private String dataSourceOfRawAndAuxiliaryMaterials;
    /**
     * 储存区数据来源
     */
    @ApiModelProperty("储存区数据来源")
    private String dataSourceOfStorageArea;
    /**
     * 废气数据来源
     */
    @ApiModelProperty("废气数据来源")
    private String dataSourceOfWasteGas;
    /**
     * 废水数据来源
     */
    @ApiModelProperty("废水数据来源")
    private String dataSourceOfWastewater;
    /**
     * 固体废物贮存处置区数据来源
     */
    @ApiModelProperty("固体废物贮存处置区数据来源")
    private String dataSourceOfSolidWasteStorageAndDisposalArea;
    /**
     * 地块内道路、地表、建（构）筑物表面、墙壁、空地污染痕迹
     */
    @ApiModelProperty("地块内道路、地表、建（构）筑物表面、墙壁、空地污染痕迹")
    private String internalPollutionTracesOfThePlot;
    /**
     * 空气异味状况
     */
    @ApiModelProperty("空气异味状况")
    private String airOdorCondition;

}
