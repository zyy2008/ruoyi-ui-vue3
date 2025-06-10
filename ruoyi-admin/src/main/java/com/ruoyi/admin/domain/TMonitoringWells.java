package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
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
public class TMonitoringWells extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增ID */
    private String id;

    /** 监测井编码 */
    @Excel(name = "监测井编码")
    private String wellCode;

    /** 监测井所在位置 */
    @Excel(name = "监测井所在位置")
    private String location;

    /** 监测点类型，如内部监测点、对照监测点 */
    @Excel(name = "监测点类型，如内部监测点、对照监测点")
    private String pointType;

    /** 成井时间 */
    @Excel(name = "成井时间")
    private String completionDate;

    /** 水位埋深，单位：米 */
    @Excel(name = "水位埋深，单位：米")
    private String waterBuriedDepth;

    /** 井口高程，单位：米 */
    @Excel(name = "井口高程，单位：米")
    private String wellElevation;

    /** 成井深度，单位：米 */
    @Excel(name = "成井深度，单位：米")
    private String wellDepth;

    /** 井口内径，单位：毫米 */
    @Excel(name = "井口内径，单位：毫米")
    private String innerDiameter;

    /** 井管材质，如PVC、不锈钢 */
    @Excel(name = "井管材质，如PVC、不锈钢")
    private String pipeMaterial;

    /** 是否为多段筛管结构 */
    @Excel(name = "是否为多段筛管结构")
    private String multiScreenPipe;

    /** 筛管上部与下部的埋深范围，单位：米 */
    @Excel(name = "筛管上部与下部的埋深范围，单位：米")
    private String screenDepthRange;

    /** 地下水类型，如潜水、承压水 */
    @Excel(name = "地下水类型，如潜水、承压水")
    private String groundwaterType;

    /** 埋藏条件，如孔隙水、裂隙水 */
    @Excel(name = "埋藏条件，如孔隙水、裂隙水")
    private String burialCondition;

    /** 含水介质 */
    @Excel(name = "含水介质")
    private String waterMedium;

    /** 监测井的权属单位 */
    @Excel(name = "监测井的权属单位")
    private String ownership;

    /** 是否符合长期监测井的标准 */
    @Excel(name = "是否符合长期监测井的标准")
    private String suitableForLongterm;

    /** 经度，WGS84坐标系 */
    @Excel(name = "经度，WGS84坐标系")
    private String longitude;

    /** 纬度，WGS84坐标系 */
    @Excel(name = "纬度，WGS84坐标系")
    private String latitude;

    /** 高程，单位：米 */
    @Excel(name = "高程，单位：米")
    private String altitude;

    /** 视频资料地址（例如外观或施工过程） */
    @Excel(name = "视频资料地址", readConverterExp = "例=如外观或施工过程")
    private String videoUrl;

    /** 所属企业 */
    @Excel(name = "所属企业")
    private String deptId;

    /** 是否实时监测 */
    @Excel(name = "是否实时监测")
    private String realTime;

    private String enterpriseName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setWellCode(String wellCode)
    {
        this.wellCode = wellCode;
    }

    public String getWellCode()
    {
        return wellCode;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setPointType(String pointType)
    {
        this.pointType = pointType;
    }

    public String getPointType()
    {
        return pointType;
    }
    public void setCompletionDate(String completionDate)
    {
        this.completionDate = completionDate;
    }

    public String getCompletionDate()
    {
        return completionDate;
    }
    public void setWaterBuriedDepth(String waterBuriedDepth)
    {
        this.waterBuriedDepth = waterBuriedDepth;
    }

    public String getWaterBuriedDepth()
    {
        return waterBuriedDepth;
    }
    public void setWellElevation(String wellElevation)
    {
        this.wellElevation = wellElevation;
    }

    public String getWellElevation()
    {
        return wellElevation;
    }
    public void setWellDepth(String wellDepth)
    {
        this.wellDepth = wellDepth;
    }

    public String getWellDepth()
    {
        return wellDepth;
    }
    public void setInnerDiameter(String innerDiameter)
    {
        this.innerDiameter = innerDiameter;
    }

    public String getInnerDiameter()
    {
        return innerDiameter;
    }
    public void setPipeMaterial(String pipeMaterial)
    {
        this.pipeMaterial = pipeMaterial;
    }

    public String getPipeMaterial()
    {
        return pipeMaterial;
    }
    public void setMultiScreenPipe(String multiScreenPipe)
    {
        this.multiScreenPipe = multiScreenPipe;
    }

    public String getMultiScreenPipe()
    {
        return multiScreenPipe;
    }
    public void setScreenDepthRange(String screenDepthRange)
    {
        this.screenDepthRange = screenDepthRange;
    }

    public String getScreenDepthRange()
    {
        return screenDepthRange;
    }
    public void setGroundwaterType(String groundwaterType)
    {
        this.groundwaterType = groundwaterType;
    }

    public String getGroundwaterType()
    {
        return groundwaterType;
    }
    public void setBurialCondition(String burialCondition)
    {
        this.burialCondition = burialCondition;
    }

    public String getBurialCondition()
    {
        return burialCondition;
    }
    public void setWaterMedium(String waterMedium)
    {
        this.waterMedium = waterMedium;
    }

    public String getWaterMedium()
    {
        return waterMedium;
    }
    public void setOwnership(String ownership)
    {
        this.ownership = ownership;
    }

    public String getOwnership()
    {
        return ownership;
    }
    public void setSuitableForLongterm(String suitableForLongterm)
    {
        this.suitableForLongterm = suitableForLongterm;
    }

    public String getSuitableForLongterm()
    {
        return suitableForLongterm;
    }
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLongitude()
    {
        return longitude;
    }
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLatitude()
    {
        return latitude;
    }
    public void setAltitude(String altitude)
    {
        this.altitude = altitude;
    }

    public String getAltitude()
    {
        return altitude;
    }
    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl()
    {
        return videoUrl;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptId()
    {
        return deptId;
    }
    public void setRealTime(String realTime)
    {
        this.realTime = realTime;
    }

    public String getRealTime()
    {
        return realTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("wellCode", getWellCode())
                .append("location", getLocation())
                .append("pointType", getPointType())
                .append("completionDate", getCompletionDate())
                .append("waterBuriedDepth", getWaterBuriedDepth())
                .append("wellElevation", getWellElevation())
                .append("wellDepth", getWellDepth())
                .append("innerDiameter", getInnerDiameter())
                .append("pipeMaterial", getPipeMaterial())
                .append("multiScreenPipe", getMultiScreenPipe())
                .append("screenDepthRange", getScreenDepthRange())
                .append("groundwaterType", getGroundwaterType())
                .append("burialCondition", getBurialCondition())
                .append("waterMedium", getWaterMedium())
                .append("ownership", getOwnership())
                .append("suitableForLongterm", getSuitableForLongterm())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("altitude", getAltitude())
                .append("videoUrl", getVideoUrl())
                .append("deptId", getDeptId())
                .append("realTime", getRealTime())
                .toString();
    }
}
