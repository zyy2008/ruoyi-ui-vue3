package com.ruoyi.admin.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 图层对象 t_layer
 *
 * @author ruoyi
 * @date 2024-10-08
 */
@Data
public class TLayer extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 访问地址
     */
    @Excel(name = "访问地址")
    private String url;

    /**
     * 服务类型
     */
    @Excel(name = "服务类型")
    private String serverType;

    /**
     * 父节点
     */
    @Excel(name = "父节点")
    private Long pid;

    /**
     * 是否显示
     */
    @Excel(name = "是否显示")
    private Boolean isShow;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private String dataType;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 部门ID为了数据做权限划分
     */
    private Long deptId;

    /**
     * 用户ID为了数据做权限划分
     */
    private Long userId;

    /**
     * 图层参数，可能是JSON字符串
     */
    @Excel(name = "图层参数，可能是JSON字符串")
    private String layerParam;

    /**
     * 基地（字典表）
     */
    private Long baseId;

    @Transient
    private Long[] baseIds;

    /**
     * 编码，对应编码规范
     */
    @Excel(name = "编码，对应编码规范")
    private String code;

    /**
     * 基地名称
     */
    private String baseName;

    /**
     * 删除标记
     */
    private Integer delFlag;
}
