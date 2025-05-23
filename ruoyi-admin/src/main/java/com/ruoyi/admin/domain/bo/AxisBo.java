package com.ruoyi.admin.domain.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 坐标
 */
@Data
public class AxisBo {
    //x坐标
    private BigDecimal axisX;
    //y坐标
    private BigDecimal axisY;
    //原坐标系
    private Integer oldCoordinate;
    //新坐标系
    private Integer newCoordinate;
}
