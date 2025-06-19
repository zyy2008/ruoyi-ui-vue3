package com.ruoyi.admin.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 获取监测数据
 */
@Data
@ApiModel("获取监测数据")
public class WaterQualityMonitoringAmLineChartDataBo {
    @NotBlank(message = "监测井编码不能为空")
    @ApiModelProperty(value = "监测井编码", required = true)
    private String pointId;
    @ApiModelProperty(value = "数据监测开始时间")
    private String startMonitoringTime;
    @ApiModelProperty(value = "数据监测结束时间")
    private String endMonitoringTime;

}
