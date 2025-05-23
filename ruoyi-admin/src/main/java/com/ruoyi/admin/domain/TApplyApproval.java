package com.ruoyi.admin.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 申请审批对象 t_apply_approval
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@Data
public class TApplyApproval extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 申请单名称
     */
    @Excel(name = "申请单名称")
    private String name;

    /**
     * 申请单内容
     */
    @Excel(name = "申请单内容")
    private String content;

    /**
     * 部门ID
     */
    @Excel(name = "部门ID")
    private Long deptId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 审核员ID
     */
    @Excel(name = "审核员ID")
    private Long auditorId;

    /**
     * 申请单状态
     */
    @Excel(name = "申请单状态")
    private String state;

    /**
     * 申请参数
     */
    @Excel(name = "申请参数")
    private String param;

    /**
     * 文件下载路径
     */
    @Excel(name = "文件下载路径")
    private String fileUrl;

}
