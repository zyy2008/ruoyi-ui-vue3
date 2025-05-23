package com.ruoyi.admin.service;

import com.ruoyi.admin.domain.TProjectManage;

import java.util.List;

/**
 * 项目管理Service接口
 *
 * @author ruoyi
 * @date 2024-12-07
 */
public interface ITProjectManageService {
    /**
     * 查询项目管理
     *
     * @param id 项目管理主键
     * @return 项目管理
     */
    public TProjectManage selectTProjectManageById(Long id);

    /**
     * 查询项目管理列表
     *
     * @param tProjectManage 项目管理
     * @return 项目管理集合
     */
    public List<TProjectManage> selectTProjectManageList(TProjectManage tProjectManage);

    /**
     * 新增项目管理
     *
     * @param tProjectManage 项目管理
     * @return 结果
     */
    public int insertTProjectManage(TProjectManage tProjectManage);

    /**
     * 修改项目管理
     *
     * @param tProjectManage 项目管理
     * @return 结果
     */
    public int updateTProjectManage(TProjectManage tProjectManage);

    /**
     * 批量删除项目管理
     *
     * @param ids 需要删除的项目管理主键集合
     * @return 结果
     */
    public int deleteTProjectManageByIds(Long[] ids);

    /**
     * 删除项目管理信息
     *
     * @param id 项目管理主键
     * @return 结果
     */
    public int deleteTProjectManageById(Long id);
}
