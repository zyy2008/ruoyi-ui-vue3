package com.ruoyi.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产车间分布与环境情况对象 t_workshop_environment
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
public class TWorkshopEnvironment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 车间名称 */
    @Excel(name = "车间名称")
    private String workshopName;

    /** 坐标位置 */
    @Excel(name = "坐标位置")
    private String coordinate;

    /** 生产起始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 生产工艺描述 */
    @Excel(name = "生产工艺描述")
    private String processDescription;

    /** 是否有泄漏现象 */
    @Excel(name = "是否有泄漏现象")
    private String leakage;

    /** 跑冒滴漏点照片路径 */
    @Excel(name = "跑冒滴漏点照片路径")
    private String leakImagePath;

    /** 添加人 */
    @Excel(name = "添加人")
    private String createdBy;

    /** 添加时间 */
    @Excel(name = "添加时间")
    private String createdAt;

    /** 归属部门 */
    @Excel(name = "归属部门")
    private Long deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWorkshopName(String workshopName) 
    {
        this.workshopName = workshopName;
    }

    public String getWorkshopName() 
    {
        return workshopName;
    }
    public void setCoordinate(String coordinate) 
    {
        this.coordinate = coordinate;
    }

    public String getCoordinate() 
    {
        return coordinate;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setProcessDescription(String processDescription) 
    {
        this.processDescription = processDescription;
    }

    public String getProcessDescription() 
    {
        return processDescription;
    }
    public void setLeakage(String leakage) 
    {
        this.leakage = leakage;
    }

    public String getLeakage() 
    {
        return leakage;
    }
    public void setLeakImagePath(String leakImagePath) 
    {
        this.leakImagePath = leakImagePath;
    }

    public String getLeakImagePath() 
    {
        return leakImagePath;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedAt(String createdAt) 
    {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() 
    {
        return createdAt;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workshopName", getWorkshopName())
            .append("coordinate", getCoordinate())
            .append("startDate", getStartDate())
            .append("processDescription", getProcessDescription())
            .append("leakage", getLeakage())
            .append("leakImagePath", getLeakImagePath())
            .append("createdBy", getCreatedBy())
            .append("createdAt", getCreatedAt())
            .append("deptId", getDeptId())
            .toString();
    }
}
