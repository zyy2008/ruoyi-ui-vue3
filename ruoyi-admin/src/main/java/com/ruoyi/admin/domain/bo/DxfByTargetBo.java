package com.ruoyi.admin.domain.bo;

import lombok.Data;

@Data
public class DxfByTargetBo {
    /**
     * 目标坐标
     */
    private String[] tagert;
    /**
     * 目标图层，不传则查询所有图层
     */
    private Long[] ids;

    private String name;

    private String content;


}
