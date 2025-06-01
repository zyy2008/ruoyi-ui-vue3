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
 * 生产车间分布与环境情况对象 t_workshop_environment
 *
 * @author ruoyi
 * @date 2025-05-30
 */
@Data
@ApiModel("生产车间分布与环境情况")
public class TWorkshopEnvironment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 车间名称
     */
    @Excel(name = "车间名称")
    @ApiModelProperty("车间名称")
    private String workshopName;

    /**
     * 坐标位置
     */
    @Excel(name = "坐标位置")
    @ApiModelProperty("坐标位置")
    private String coordinate;

    /**
     * 生产起始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("生产起始日期")
    private Date startDate;

    /**
     * 生产工艺描述
     */
    @Excel(name = "生产工艺描述")
    @ApiModelProperty("生产工艺描述")
    private String processDescription;

    /**
     * 是否有泄漏现象
     */
    @Excel(name = "是否有泄漏现象")
    @ApiModelProperty("是否有泄漏现象")
    private String leakage;

    /**
     * 跑冒滴漏点照片路径
     */
    @Excel(name = "跑冒滴漏点照片路径")
    @ApiModelProperty("跑冒滴漏点照片路径")
    private String leakImagePath;

    /**
     * 添加人
     */
    @Excel(name = "添加人")
    @ApiModelProperty("添加人")
    private String createdBy;

    /**
     * 添加时间
     */
    @Excel(name = "添加时间")
    @ApiModelProperty("添加时间")
    private String createdAt;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
