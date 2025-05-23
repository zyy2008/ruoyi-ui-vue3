package com.ruoyi.admin.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 基地对象 t_base
 *
 * @author ruoyi
 * @date 2024-11-07
 */
@Data
public class TBase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 简称
     */
    private String abbr;

    /**
     * 位置
     */
    private String position;

    /**
     * 面积（单位：平方公里）
     */
    private Double area;

    /**
     * 拥有土地面积
     */
    private Double haveLandArea;

    /**
     * 上报面积
     */
    private Double reportArea;

    /**
     * 高程系统
     */
    private String elevationSystem;

    /**
     * 管线布置基本原则
     */
    private String pipelineArrangementPrinciple;

    /**
     * 地图视角
     */
    private String view;
    /**
     * 地图原点
     */
    private String origin;
    /**
     * 地图偏移
     */
    private String offset;

}
