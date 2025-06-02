package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地块及周边敏感受体信息对象 t_sensitive_receptor
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Data
@ApiModel("地块及周边敏感受体信息")
public class TSensitiveReceptor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 东侧用地类型
     */
    @Excel(name = "东侧用地类型")
    @ApiModelProperty("东侧用地类型")
    private String surroundingLandEast;

    /**
     * 南侧用地类型
     */
    @Excel(name = "南侧用地类型")
    @ApiModelProperty("南侧用地类型")
    private String surroundingLandSouth;

    /**
     * 西侧用地类型
     */
    @Excel(name = "西侧用地类型")
    @ApiModelProperty("西侧用地类型")
    private String surroundingLandWest;

    /**
     * 北侧用地类型
     */
    @Excel(name = "北侧用地类型")
    @ApiModelProperty("北侧用地类型")
    private String surroundingLandNorth;

    /**
     * 地块内职工人数分类
     */
    @Excel(name = "地块内职工人数分类")
    @ApiModelProperty("地块内职工人数分类")
    private String employeeCountCategory;

    /**
     * 500m范围内常驻人口数量分类
     */
    @Excel(name = "500m范围内常驻人口数量分类")
    @ApiModelProperty("500m范围内常驻人口数量分类")
    private String permanentPopulationCategory;

    /**
     * 1km范围内敏感目标及距离分类
     */
    @Excel(name = "1km范围内敏感目标及距离分类")
    @ApiModelProperty("1km范围内敏感目标及距离分类")
    private String sensitiveTargets;

    /**
     * 所属部门
     */
    @Excel(name = "所属部门")
    @ApiModelProperty("所属部门")
    private String deptId;

}
