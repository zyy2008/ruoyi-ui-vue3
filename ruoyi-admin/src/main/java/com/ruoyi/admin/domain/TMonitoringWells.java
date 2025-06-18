package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 园区初调监测井信息，记录每个监测井的基础属性与附加信息对象 t_monitoring_wells
 *
 * @author ruoyi
 * @date 2025-06-09
 */
@Data
@ApiModel("监测井对象")
public class TMonitoringWells extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增ID
     */
    private String id;

    /**
     * 监测井编码
     */
    @ApiModelProperty("监测井编码")
    private String wellCode;

    /**
     * 监测井所在位置
     */
    @ApiModelProperty("监测井所在位置")
    private String location;

    /**
     * 监测点类型，如内部监测点、对照监测点
     */
    @ApiModelProperty("监测点类型，如内部监测点、对照监测点")
    private String pointType;

    /**
     * 成井时间
     */
    @ApiModelProperty("成井时间")
    private String completionDate;

    /**
     * 水位埋深，单位：米
     */
    @ApiModelProperty("水位埋深，单位：米")
    private String waterBuriedDepth;

    /**
     * 井口高程，单位：米
     */
    @ApiModelProperty("井口高程，单位：米")
    private String wellElevation;

    /**
     * 成井深度，单位：米
     */
    @ApiModelProperty("成井深度，单位：米")
    private String wellDepth;

    /**
     * 井口内径，单位：毫米
     */
    @ApiModelProperty("井口内径，单位：毫米")
    private String innerDiameter;

    /**
     * 井管材质，如PVC、不锈钢
     */
    @ApiModelProperty("井管材质，如PVC、不锈钢")
    private String pipeMaterial;

    /**
     * 是否为多段筛管结构
     */
    @ApiModelProperty("是否为多段筛管结构")
    private String multiScreenPipe;

    /**
     * 筛管上部与下部的埋深范围，单位：米
     */
    @ApiModelProperty("筛管上部与下部的埋深范围，单位：米")
    private String screenDepthRange;

    /**
     * 地下水类型，如潜水、承压水
     */
    @ApiModelProperty("地下水类型，如潜水、承压水")
    private String groundwaterType;

    /**
     * 埋藏条件，如孔隙水、裂隙水
     */
    @ApiModelProperty("埋藏条件，如孔隙水、裂隙水")
    private String burialCondition;

    /**
     * 含水介质
     */
    @ApiModelProperty("含水介质")
    private String waterMedium;

    /**
     * 监测井的权属单位
     */
    @ApiModelProperty("监测井的权属单位")
    private String ownership;

    /**
     * 是否符合长期监测井的标准
     */
    @ApiModelProperty("是否符合长期监测井的标准")
    private String suitableForLongterm;

    /**
     * 经度，WGS84坐标系
     */
    @ApiModelProperty("经度，WGS84坐标系")
    private String longitude;

    /**
     * 纬度，WGS84坐标系
     */
    @ApiModelProperty("纬度，WGS84坐标系")
    private String latitude;

    /**
     * 高程，单位：米
     */
    @ApiModelProperty("高程，单位：米")
    private String altitude;

    /**
     * 视频资料地址（例如外观或施工过程）
     */
    @ApiModelProperty("视频资料地址")
    private String videoUrl;

    /**
     * 所属企业
     */
    @ApiModelProperty("所属企业")
    private String deptId;

    /**
     * 是否实时监测
     */
    @ApiModelProperty("是否实时监测")
    private String realTime;

    @ApiModelProperty("设备编号")
    private String deviceNo;

    /**
     * 排序顺序
      */
    @ApiModelProperty("排序顺序")
    private Integer sortOrder;

    /**
     * 企业名称
     */
    @ApiModelProperty("企业名称")
    private String enterpriseName;
}
