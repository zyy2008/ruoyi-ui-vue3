package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 管道泄漏对象 t_pipeline_leak
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Data
@ApiModel("管道泄漏")
public class TPipelineLeak extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 管道类型
     */
    @Excel(name = "管道类型")
    @ApiModelProperty("管道类型")
    private String pipelineType;

    /**
     * 泄漏点坐标位置
     */
    @Excel(name = "泄漏点坐标位置")
    @ApiModelProperty("泄漏点坐标位置")
    private String leakCoordinates;

    /**
     * 泄漏的物质名称
     */
    @Excel(name = "泄漏的物质名称")
    @ApiModelProperty("泄漏的物质名称")
    private String leakedSubstanceName;

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
