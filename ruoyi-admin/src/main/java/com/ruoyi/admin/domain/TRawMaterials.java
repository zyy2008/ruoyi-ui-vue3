package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 原辅材料信息对象 t_raw_materials
 *
 * @author ruoyi
 * @date 2025-05-30
 */
@Data
@ApiModel("原辅材料信息")
public class TRawMaterials extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 原辅材料名称
     */
    @Excel(name = "原辅材料名称")
    @ApiModelProperty("原辅材料名称")
    private String materialName;

    /**
     * 使用年代
     */
    @Excel(name = "使用年代")
    @ApiModelProperty("使用年代")
    private Long usageYear;

    /**
     * 计量单位
     */
    @Excel(name = "计量单位")
    @ApiModelProperty("计量单位")
    private String unit;

    /**
     * 年平均用量
     */
    @Excel(name = "年平均用量")
    @ApiModelProperty("年平均用量")
    private String annualUsage;

    /**
     * 数据来源
     */
    @Excel(name = "数据来源")
    @ApiModelProperty("数据来源")
    private String dataSource;

    /**
     * 添加人
     */
    @Excel(name = "添加人")
    @ApiModelProperty("添加人")
    private String createdBy;

    /**
     * 添加时间
     */
    @Excel(name = "添加时间")
    @ApiModelProperty("添加时间")
    private String createdAt;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    @ApiModelProperty("归属部门")
    private String deptId;

}
