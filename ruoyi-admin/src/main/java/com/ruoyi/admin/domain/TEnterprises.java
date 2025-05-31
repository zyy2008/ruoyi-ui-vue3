package com.ruoyi.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业管理对象 t_enterprises
 * 
 * @author wy
 * @date 2025-05-31
 */
public class TEnterprises extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String enterpriseName;

    /** 行业类别 */
    @Excel(name = "行业类别")
    private String industryCategory;

    /** 生产年限 */
    @Excel(name = "生产年限")
    private String productionYears;

    /** 存续状态 */
    @Excel(name = "存续状态")
    private String status;

    /** 报送情况 */
    @Excel(name = "报送情况")
    private String reportingStatus;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 占地面积 */
    @Excel(name = "占地面积")
    private String area;

    /** 是否重点 */
    @Excel(name = "是否重点")
    private String isKeyEnterprise;

    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }
    public void setIndustryCategory(String industryCategory) 
    {
        this.industryCategory = industryCategory;
    }

    public String getIndustryCategory() 
    {
        return industryCategory;
    }
    public void setProductionYears(String productionYears) 
    {
        this.productionYears = productionYears;
    }

    public String getProductionYears() 
    {
        return productionYears;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setReportingStatus(String reportingStatus) 
    {
        this.reportingStatus = reportingStatus;
    }

    public String getReportingStatus() 
    {
        return reportingStatus;
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
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setIsKeyEnterprise(String isKeyEnterprise) 
    {
        this.isKeyEnterprise = isKeyEnterprise;
    }

    public String getIsKeyEnterprise() 
    {
        return isKeyEnterprise;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enterpriseName", getEnterpriseName())
            .append("industryCategory", getIndustryCategory())
            .append("productionYears", getProductionYears())
            .append("status", getStatus())
            .append("reportingStatus", getReportingStatus())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("area", getArea())
            .append("isKeyEnterprise", getIsKeyEnterprise())
            .toString();
    }
}
