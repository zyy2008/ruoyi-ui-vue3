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
 * @date 2025-06-01
 */
@Data
@ApiModel("园区监测井信息")
public class TMonitoringWells extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增ID
     */
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 监测井编码
     */
    @ApiModelProperty("监测井编码")
    @Excel(name = "监测井编码")
    private String wellCode;

    /**
     * 监测井所在位置
     */
    @Excel(name = "监测井所在位置")
    @ApiModelProperty("监测井所在位置")
    private String location;

    /**
     * 监测点类型，如内部监测点、对照监测点
     */
    @Excel(name = "监测点类型，如内部监测点、对照监测点")
    @ApiModelProperty("监测点类型")
    private String pointType;

    /**
     * 成井时间
     */
    @Excel(name = "成井时间")
    @ApiModelProperty("成井时间")
    private String completionDate;

    /**
     * 水位埋深，单位：米
     */
    @Excel(name = "水位埋深，单位：米")
    @ApiModelProperty("水位埋深")
    private String waterBuriedDepth;

    /**
     * 井口高程，单位：米
     */
    @Excel(name = "井口高程，单位：米")
    @ApiModelProperty("井口高程")
    private String wellElevation;

    /**
     * 成井深度，单位：米
     */
    @Excel(name = "成井深度，单位：米")
    @ApiModelProperty("成井深度")
    private String wellDepth;

    /**
     * 井口内径，单位：毫米
     */
    @Excel(name = "井口内径，单位：毫米")
    @ApiModelProperty("井口内径")
    private String innerDiameter;

    /**
     * 井管材质，如PVC、不锈钢
     */
    @Excel(name = "井管材质，如PVC、不锈钢")
    @ApiModelProperty("井管材质")
    private String pipeMaterial;

    /**
     * 是否为多段筛管结构
     */
    @Excel(name = "是否为多段筛管结构")
    @ApiModelProperty("是否为多段筛管结构")
    private String multiScreenPipe;

    /**
     * 筛管上部与下部的埋深范围，单位：米
     */
    @Excel(name = "筛管上部与下部的埋深范围，单位：米")
    @ApiModelProperty("筛管上部与下部的埋深范围")
    private String screenDepthRange;

    /**
     * 地下水类型，如潜水、承压水
     */
    @Excel(name = "地下水类型，如潜水、承压水")
    @ApiModelProperty("地下水类型")
    private String groundwaterType;

    /**
     * 埋藏条件，如孔隙水、裂隙水
     */
    @Excel(name = "埋藏条件，如孔隙水、裂隙水")
    @ApiModelProperty("主键ID")
    private String burialCondition;

    /**
     * 含水介质
     */
    @Excel(name = "含水介质")
    @ApiModelProperty("含水介质")
    private String waterMedium;

    /**
     * 监测井的权属单位
     */
    @Excel(name = "监测井的权属单位")
    @ApiModelProperty("监测井的权属单位")
    private String ownership;

    /**
     * 是否符合长期监测井的标准
     */
    @Excel(name = "是否符合长期监测井的标准")
    @ApiModelProperty("是否符合长期监测井的标准")
    private String suitableForLongterm;

    /**
     * 经度，WGS84坐标系
     */
    @Excel(name = "经度，WGS84坐标系")
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 纬度，WGS84坐标系
     */
    @Excel(name = "纬度，WGS84坐标系")
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 高程，单位：米
     */
    @Excel(name = "高程，单位：米")
    @ApiModelProperty("高程")
    private String altitude;

    /**
     * 视频资料地址（例如外观或施工过程）
     */
    @Excel(name = "视频资料地址", readConverterExp = "例=如外观或施工过程")
    @ApiModelProperty("视频资料地址")
    private String videoUrl;

    /**
     * 归属部门
     */
    @ApiModelProperty("归属部门")
    private String deptId;

}
