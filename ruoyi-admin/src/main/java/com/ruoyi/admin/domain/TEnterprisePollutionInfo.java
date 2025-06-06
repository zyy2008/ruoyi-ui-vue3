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
    @Excel(name = "储存区面积", readConverterExp = "平=方米")
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
    @Excel(name = "是否存在废气治理设施", readConverterExp = "t=rue/false")
    @ApiModelProperty("是否存在废气治理设施")
    private String gasTreatmentExists;
    /**
     * 废气排放总量
     */
    @Excel(name = "废气排放量")
    @ApiModelProperty("废气排放量")
    private String totalGasEmission;
    /**
     * 废气中主要污染物名称
     */
    @ApiModelProperty(name = "废气中主要污染物名称")
    private String mainPollutantsInEmissions;
    /**
     * 是否有废气在线监测装置
     */
    @ApiModelProperty(name = "是否有废气在线监测装置")
    private String isThereAnOnlineMonitoringDeviceForEmissions;
    /**
     * 在线监测装置运行情况
     */
    @ApiModelProperty(name = "在线监测装置运行情况")
    private String operationStatusOfOnlineMonitoringDevice;
    /**
     * 是否有废气治理设施
     */
    @ApiModelProperty(name = "是否有废气治理设施")
    private String isThereAnEmissionTreatmentFacility;
    /**
     * 废气治理设施运行情况
     */
    @ApiModelProperty(name = "废气治理设施运行情况")
    private String operationStatusOfEmissionTreatmentFacility;


    /**
     * 是否有废水排放
     */
    @ApiModelProperty(name = "是否有废水排放")
    private String isThereWastewaterDischarge;
    /**
     * 废水排放量
     */
    @ApiModelProperty(name = "废水排放量")
    private String wastewaterDischargeVolume;
    /**
     * 废水中主要污染物名称
     */
    @ApiModelProperty(name = "废水中主要污染物名称")
    private String mainPollutantsInWastewater;
    /**
     * 是否有废水在线监测装置
     */
    @ApiModelProperty(name = "是否有废水在线监测装置")
    private String isThereAnOnlineMonitoringDeviceForWastewater;
    /**
     * 是否有废水治理设施
     */
    @ApiModelProperty(name = "是否有废水治理设施")
    private String isThereAWastewaterTreatmentFacility;
    /**
     * 废水治理设施运行情况
     */
    @ApiModelProperty(name = "废水治理设施运行情况")
    private String operationStatusOfWastewaterTreatmentFacility;
    /**
     * 废水治理区坐标位置
     */
    @ApiModelProperty(name = "废水治理区坐标位置")
    private String coordinatesOfWastewaterTreatmentArea;
    /**
     * 废水治理区面积
     */
    @ApiModelProperty(name = "废水治理区面积")
    private String areaOfWastewaterTreatmentArea;
    /**
     * 废水治理区污染痕迹
     */
    @ApiModelProperty(name = "废水治理区污染痕迹")
    private String pollutionTracesInWastewaterTreatmentArea;
    /**
     * 污染痕迹照片
     */
    @ApiModelProperty(name = "污染痕迹照片")
    private String photosOfPollutionTraces;
    /**
     * 废水治理区防护措施
     */
    @ApiModelProperty(name = "废水治理区防护措施")
    private String protectionMeasuresInWastewaterTreatmentArea;













    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
