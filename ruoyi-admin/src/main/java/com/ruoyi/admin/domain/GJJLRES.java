package com.ruoyi.admin.domain;

import lombok.Data;

@Data
public class GJJLRES {

    private static final long serialVersionUID = 1L;

    private String sample_time;
    private String point_id;       // 点位编号（对应 SQL 中的 point_id）
    private String zb;            // 指标名称（固定值 "氯化物"）
    private String bzz;           // 标准值（固定值 "≤350"）
    private Double jcz;           // 检测值（对应 SQL 中的 chloride，建议使用包装类型避免 NULL 问题）
    private  String cbbl;
}
