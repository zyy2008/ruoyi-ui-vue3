package com.ruoyi.admin.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysDeptVo extends BaseEntity {

    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
    private List<TLayerSetVo> layerSetVoList;
    /**
     * 子部门
     */
    private List<SysDeptVo> children = new ArrayList<SysDeptVo>();
}
