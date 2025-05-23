package com.ruoyi.admin.domain.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 路网路径
 */
@Data
public class ShortestPathBo {
    //开始坐标x
    private BigDecimal startAxisY;
    //开始坐标y
    private BigDecimal startAxisX;
    //结束坐标x
    private BigDecimal endAxisX;
    //结束坐标y
    private BigDecimal endAxisY;
}
