package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 政策文件对象 t_policy_documents
 *
 * @author ruoyi
 * @date 2025-06-14
 */
@Data
@ApiModel("政策文件")
public class TPolicyDocuments extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序号，主键，自增
     */
    private Long id;

    /**
     * 标题，政策文件的名称
     */
    @ApiModelProperty("标题，政策文件的名称")
    private String title;

    /**
     * 发布时间，政策发布的具体日期
     */
    @ApiModelProperty("发布时间，政策发布的具体日期")
    private String publishDate;

    /**
     * 下载连接，指向政策文件的下载地址
     */
    @ApiModelProperty("下载连接，指向政策文件的下载地址")
    private String downloadUrl;
}
