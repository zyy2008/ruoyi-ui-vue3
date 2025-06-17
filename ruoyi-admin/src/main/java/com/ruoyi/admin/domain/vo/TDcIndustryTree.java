package com.ruoyi.admin.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 行业分类
 */
@ApiModel("行业分类树形结构")
@Data
public class TDcIndustryTree implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 行业名称
     */
    @ApiModelProperty("行业名称")
    private String industryCategory;

    /**
     * 行业编码
     */
    @ApiModelProperty("行业编码")
    private String code;

    /**
     * 绑定的大类（上级行业编码）
     */
    @ApiModelProperty("绑定的大类（上级行业编码）")
    private String pCode;

    /**
     * 子节点列表
     */
    @ApiModelProperty("子行业列表")
    private List<TDcIndustryTree> children;
}
