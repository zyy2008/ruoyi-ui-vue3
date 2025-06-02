package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地下水迁移途径信息对象 t_groundwater_pathway
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Data
@ApiModel("地下水迁移途径信息对象")
public class TGroundwaterPathway extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 地下水埋深（10m、不确定）
     */
    @Excel(name = "地下水埋深", readConverterExp = "1=0m、不确定")
    @ApiModelProperty("地下水埋深")
    private String waterDepthCategory;

    /**
     * 含水层渗透性分类
     */
    @Excel(name = "含水层渗透性分类")
    @ApiModelProperty("含水层渗透性分类")
    private String permeabilityCategory;

    /**
     * 是否属于喀斯特地貌
     */
    @Excel(name = "是否属于喀斯特地貌")
    @ApiModelProperty("是否属于喀斯特地貌")
    private String isKarstArea;

    /**
     * 年降雨量范围
     */
    @Excel(name = "年降雨量范围")
    @ApiModelProperty("年降雨量范围")
    private String annualRainfallCategory;

    /**
     * 地下水用途
     */
    @Excel(name = "地下水用途")
    @ApiModelProperty("地下水用途")
    private String groundwaterUse;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
