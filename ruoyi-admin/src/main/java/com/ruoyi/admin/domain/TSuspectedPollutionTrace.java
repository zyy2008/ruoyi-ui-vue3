package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 可疑污染源或污染痕迹对象 t_suspected_pollution_trace
 *
 * @author ruoyi
 * @date 2025-06-09
 */
@Data
@ApiModel("可疑污染源或污染痕迹")
public class TSuspectedPollutionTrace extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 污染痕迹出现的地点
     */
    @ApiModelProperty( "污染痕迹出现的地点")
    private String traceLocation;

    /**
     * 污染痕迹出现的坐标位置
     */
    @ApiModelProperty( "污染痕迹出现的坐标位置")
    private String traceCoordinates;

    /**
     * 污染痕迹照片
     */
    @ApiModelProperty( "污染痕迹照片")
    private String tracePhotos;

    /**
     * 绑定的污染源
     */
    @ApiModelProperty( "绑定的污染源")
    private String enterprisePollutionInfoId;

    /**
     * 归属部门
     */
    @ApiModelProperty( "归属部门")
    private String deptId;
}
