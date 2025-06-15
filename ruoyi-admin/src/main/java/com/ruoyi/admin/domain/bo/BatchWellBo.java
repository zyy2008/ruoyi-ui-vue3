package com.ruoyi.admin.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取监测井最新数据
 */
@Data
@ApiModel("获取监测井最新数据")
public class BatchWellBo {
//    @ApiModelProperty("部门")
//    private String deptId;
    @ApiModelProperty("数据监测开始时间")
    private String startMonitoringTime;
    @ApiModelProperty("数据监测结束时间")
    private String endMonitoringTime;
}
