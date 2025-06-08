package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 污水处理厂固体废物产生量及污染防治措施对象 t_sewage_solid_waste
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Data
@ApiModel("污水处理厂固体废物产生量及污染防治措施")
public class TSewageSolidWaste extends BaseEntity {
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
     * 地点
     */
    @Excel(name = "地点")
    @ApiModelProperty("地点")
    private String location;

    /**
     * 坐标位置
     */
    @Excel(name = "坐标位置")
    @ApiModelProperty("坐标位置")
    private String coordinates;

    /**
     * 面积
     */
    @Excel(name = "面积")
    @ApiModelProperty("面积")
    private String area;

    /**
     * 固体废物名称
     */
    @Excel(name = "固体废物名称")
    @ApiModelProperty("固体废物名称")
    private String solidWasteName;

    /**
     * 是否为危险废物
     */
    @Excel(name = "是否为危险废物")
    @ApiModelProperty("是否为危险废物")
    private String isHazardousWaste;

    /**
     * 贮存量处置量
     */
    @Excel(name = "贮存量处置量")
    @ApiModelProperty("贮存量处置量")
    private String storageAndDisposalAmount;

    /**
     * 贮存量处置照片
     */
    @Excel(name = "贮存量处置照片")
    @ApiModelProperty("贮存量处置照片")
    private String storageAndDisposalPhotos;

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
