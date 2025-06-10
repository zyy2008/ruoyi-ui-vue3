package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 调查评估土壤污染区信息对象 t_survey_soil_pollution
 *
 * @author ruoyi
 * @date 2025-06-10
 */
@Data
@ApiModel("调查评估基本信息")
public class TSurveySoilPollution extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 关联的调查评估ID
     */
    @ApiModelProperty("关联的调查评估ID")
    private String surveyAssessmentId;

    /**
     * 土壤超标污染物名称
     */
    @ApiModelProperty("土壤超标污染物名称")
    private String pollutantName;

    /**
     * 最大浓度（单位mg/kg）
     */
    @ApiModelProperty("最大浓度")
    private String maxConcentration;

}
