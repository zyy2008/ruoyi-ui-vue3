package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 储存区对象 t_storage_area
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Data
@ApiModel("储存区")
public class TStorageArea extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 区域名称
     */
    @Excel(name = "区域名称")
    @ApiModelProperty("区域名称")
    private String areaName;

    /**
     * 区域类型
     */
    @Excel(name = "区域类型")
    @ApiModelProperty("区域类型")
    private String areaType;

    /**
     * 左边位置
     */
    @Excel(name = "左边位置")
    @ApiModelProperty("左边位置")
    private String leftPosition;

    /**
     * 储罐数量
     */
    @Excel(name = "储罐数量")
    @ApiModelProperty("储罐数量")
    private Long tankCount;

    /**
     * 储罐类型
     */
    @Excel(name = "储罐类型")
    @ApiModelProperty("储罐类型")
    private String tankType;

    /**
     * 储存物质名称
     */
    @Excel(name = "储存物质名称")
    @ApiModelProperty("储存物质名称")
    private String storedSubstanceName;

    /**
     * 是否为危险化学品
     */
    @Excel(name = "是否为危险化学品")
    @ApiModelProperty("是否为危险化学品")
    private String isHazardousChemical;

    /**
     * 储存量
     */
    @Excel(name = "储存量")
    @ApiModelProperty("储存量")
    private String storageAmount;

    /**
     * 计量单位
     */
    @Excel(name = "计量单位")
    @ApiModelProperty("计量单位")
    private String unit;

    /**
     * 储存年代
     */
    @Excel(name = "储存年代")
    @ApiModelProperty("储存年代")
    private String storageYear;

    /**
     * 泄漏或污染痕迹
     */
    @Excel(name = "泄漏或污染痕迹")
    @ApiModelProperty("泄漏或污染痕迹")
    private String leakageOrPollutionTraces;

    /**
     * 照片
     */
    @Excel(name = "照片")
    @ApiModelProperty("照片")
    private String photos;

    /**
     * 绑定的污染源
     */
    @Excel(name = "绑定的污染源")
    @ApiModelProperty("绑定的污染源")
    private String enterprisePollutionInfoId;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
