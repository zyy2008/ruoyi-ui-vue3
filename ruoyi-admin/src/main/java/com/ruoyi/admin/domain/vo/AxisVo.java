package com.ruoyi.admin.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 坐标
 */
@Data
public class AxisVo {
    //x坐标
    private BigDecimal axisX;
    //y坐标
    private BigDecimal axisY;
}
