package com.ruoyi.admin.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 获取单个监测井折线图数据
 */
@Data
@ApiModel("获取单个监测井折线图数据")
public class SingleMonitoringWellLineChartDataBo {
    @NotBlank(message = "监测井编码不能为空")
    @ApiModelProperty(value = "监测井编码", required = true)
    private String wellCode;
    @NotBlank(message = "数据监测开始时间不能为空")
    @ApiModelProperty(value = "数据监测开始时间", required = true)
    private String startMonitoringTime;
    @NotBlank(message = "数据监测结束时间不能为空")
    @ApiModelProperty(value = "数据监测结束时间", required = true)
    private String endMonitoringTime;

}
