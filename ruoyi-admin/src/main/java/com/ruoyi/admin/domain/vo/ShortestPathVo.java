package com.ruoyi.admin.domain.vo;

import lombok.Data;

/**
 * 路网路径
 */
@Data
public class ShortestPathVo {
    private int seq;
    private long gid;
    private String name;
    private double costs;
    private double azimuth;
    private String geom;
}
