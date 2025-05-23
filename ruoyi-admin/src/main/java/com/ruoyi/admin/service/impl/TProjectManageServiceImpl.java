package com.ruoyi.admin.service.impl;

import com.ruoyi.admin.domain.TProjectManage;
import com.ruoyi.admin.mapper.TProjectManageMapper;
import com.ruoyi.admin.service.ITProjectManageService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-07
 */
@Service
public class TProjectManageServiceImpl implements ITProjectManageService {
    @Autowired
    private TProjectManageMapper tProjectManageMapper;

    /**
     * 查询项目管理
     *
     * @param id 项目管理主键
     * @return 项目管理
     */
    @Override
    public TProjectManage selectTProjectManageById(Long id) {
        return tProjectManageMapper.selectTProjectManageById(id);
    }

    /**
     * 查询项目管理列表
     *
     * @param tProjectManage 项目管理
     * @return 项目管理
     */
    @Override
    public List<TProjectManage> selectTProjectManageList(TProjectManage tProjectManage) {
        return tProjectManageMapper.selectTProjectManageList(tProjectManage);
    }

    /**
     * 新增项目管理
     *
     * @param tProjectManage 项目管理
     * @return 结果
     */
    @Override
    public int insertTProjectManage(TProjectManage tProjectManage) {
        tProjectManage.setUserId(SecurityUtils.getUserId());
        tProjectManage.setDeptId(SecurityUtils.getDeptId());
        return tProjectManageMapper.insertTProjectManage(tProjectManage);
    }

    /**
     * 修改项目管理
     *
     * @param tProjectManage 项目管理
     * @return 结果
     */
    @Override
    public int updateTProjectManage(TProjectManage tProjectManage) {
        return tProjectManageMapper.updateTProjectManage(tProjectManage);
    }

    /**
     * 批量删除项目管理
     *
     * @param ids 需要删除的项目管理主键
     * @return 结果
     */
    @Override
    public int deleteTProjectManageByIds(Long[] ids) {
        return tProjectManageMapper.deleteTProjectManageByIds(ids);
    }

    /**
     * 删除项目管理信息
     *
     * @param id 项目管理主键
     * @return 结果
     */
    @Override
    public int deleteTProjectManageById(Long id) {
        return tProjectManageMapper.deleteTProjectManageById(id);
    }
}
