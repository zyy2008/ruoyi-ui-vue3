package com.ruoyi.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 项目管理对象 t_project_manage
 *
 * @author ruoyi
 * @date 2024-12-07
 */
@Data
public class TProjectManage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 工程编号
     */
    @Excel(name = "工程编号")
    private String engineeringCode;

    /**
     * 工程时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工程时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date engineeringDate;

    /**
     * 设计单位
     */
    @Excel(name = "设计单位")
    private String designUnit;

    /**
     * 设计者
     */
    @Excel(name = "设计者")
    private String designer;

    /**
     * 设计阶段
     */
    @Excel(name = "设计阶段")
    private String designPhase;

    /**
     * 归属人
     */
    @Excel(name = "归属人")
    private Long userId;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    private Long deptId;

    /**
     * 文件地址
     */
    @Excel(name = "文件地址")
    private String fileUrl;

}
