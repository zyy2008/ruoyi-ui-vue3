package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 调查评估基本信息对象 t_survey_assessment
 *
 * @author ruoyi
 * @date 2025-06-10
 */
@Data
@ApiModel("调查评估基本信息")
public class TSurveyAssessment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 调查评估结论或内容
     */
    @ApiModelProperty("调查评估结论或内容")
    private String assessment;

    /**
     * 调查评估时间
     */
    @ApiModelProperty("调查评估时间")
    private String assessmentTime;

    /**
     * 是否存在土壤污染（是/否）
     */
    @ApiModelProperty("是否存在土壤污染")
    private String hasSoilPollution;

    /**
     * 是否存在地下水污染（是/否）
     */
    @ApiModelProperty("是否存在地下水污染")
    private String hasGroundwaterPollution;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;
}
