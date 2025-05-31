package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TWorkshopEnvironment;

/**
 * 生产车间分布与环境情况Service接口
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
public interface ITWorkshopEnvironmentService 
{
    /**
     * 查询生产车间分布与环境情况
     * 
     * @param id 生产车间分布与环境情况主键
     * @return 生产车间分布与环境情况
     */
    public TWorkshopEnvironment selectTWorkshopEnvironmentById(Long id);

    /**
     * 查询生产车间分布与环境情况列表
     * 
     * @param tWorkshopEnvironment 生产车间分布与环境情况
     * @return 生产车间分布与环境情况集合
     */
    public List<TWorkshopEnvironment> selectTWorkshopEnvironmentList(TWorkshopEnvironment tWorkshopEnvironment);

    /**
     * 新增生产车间分布与环境情况
     * 
     * @param tWorkshopEnvironment 生产车间分布与环境情况
     * @return 结果
     */
    public int insertTWorkshopEnvironment(TWorkshopEnvironment tWorkshopEnvironment);

    /**
     * 修改生产车间分布与环境情况
     * 
     * @param tWorkshopEnvironment 生产车间分布与环境情况
     * @return 结果
     */
    public int updateTWorkshopEnvironment(TWorkshopEnvironment tWorkshopEnvironment);

    /**
     * 批量删除生产车间分布与环境情况
     * 
     * @param ids 需要删除的生产车间分布与环境情况主键集合
     * @return 结果
     */
    public int deleteTWorkshopEnvironmentByIds(Long[] ids);

    /**
     * 删除生产车间分布与环境情况信息
     * 
     * @param id 生产车间分布与环境情况主键
     * @return 结果
     */
    public int deleteTWorkshopEnvironmentById(Long id);
}
