package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 土壤途径：含水层以上土壤分层信息对象 t_soil_pathway
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Data
@ApiModel("土壤环境调查监测信息")
public class TSoilPathway extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 土壤层序号
     */
    @Excel(name = "土壤层序号")
    @ApiModelProperty("土壤层序号")
    private Long seqNo;

    /**
     * 土层性质（如碎石土、砂土等）
     */
    @Excel(name = "土层性质", readConverterExp = "如=碎石土、砂土等")
    @ApiModelProperty("土层性质")
    private String soilType;

    /**
     * 上层埋深（单位cm）
     */
    @Excel(name = "上层埋深", readConverterExp = "单=位cm")
    @ApiModelProperty("上层埋深")
    private Long depthTopCm;

    /**
     * 土层厚度（单位cm）
     */
    @Excel(name = "土层厚度", readConverterExp = "单=位cm")
    @ApiModelProperty("土层厚度")
    private Long thicknessCm;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
