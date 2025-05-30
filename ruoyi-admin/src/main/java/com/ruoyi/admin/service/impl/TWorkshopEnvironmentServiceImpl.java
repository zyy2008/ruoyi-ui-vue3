package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TWorkshopEnvironmentMapper;
import com.ruoyi.admin.domain.TWorkshopEnvironment;
import com.ruoyi.admin.service.ITWorkshopEnvironmentService;

/**
 * 生产车间分布与环境情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
@Service
public class TWorkshopEnvironmentServiceImpl implements ITWorkshopEnvironmentService 
{
    @Autowired
    private TWorkshopEnvironmentMapper tWorkshopEnvironmentMapper;

    /**
     * 查询生产车间分布与环境情况
     * 
     * @param id 生产车间分布与环境情况主键
     * @return 生产车间分布与环境情况
     */
    @Override
    public TWorkshopEnvironment selectTWorkshopEnvironmentById(Long id)
    {
        return tWorkshopEnvironmentMapper.selectTWorkshopEnvironmentById(id);
    }

    /**
     * 查询生产车间分布与环境情况列表
     * 
     * @param tWorkshopEnvironment 生产车间分布与环境情况
     * @return 生产车间分布与环境情况
     */
    @Override
    public List<TWorkshopEnvironment> selectTWorkshopEnvironmentList(TWorkshopEnvironment tWorkshopEnvironment)
    {
        return tWorkshopEnvironmentMapper.selectTWorkshopEnvironmentList(tWorkshopEnvironment);
    }

    /**
     * 新增生产车间分布与环境情况
     * 
     * @param tWorkshopEnvironment 生产车间分布与环境情况
     * @return 结果
     */
    @Override
    public int insertTWorkshopEnvironment(TWorkshopEnvironment tWorkshopEnvironment)
    {
        return tWorkshopEnvironmentMapper.insertTWorkshopEnvironment(tWorkshopEnvironment);
    }

    /**
     * 修改生产车间分布与环境情况
     * 
     * @param tWorkshopEnvironment 生产车间分布与环境情况
     * @return 结果
     */
    @Override
    public int updateTWorkshopEnvironment(TWorkshopEnvironment tWorkshopEnvironment)
    {
        return tWorkshopEnvironmentMapper.updateTWorkshopEnvironment(tWorkshopEnvironment);
    }

    /**
     * 批量删除生产车间分布与环境情况
     * 
     * @param ids 需要删除的生产车间分布与环境情况主键
     * @return 结果
     */
    @Override
    public int deleteTWorkshopEnvironmentByIds(Long[] ids)
    {
        return tWorkshopEnvironmentMapper.deleteTWorkshopEnvironmentByIds(ids);
    }

    /**
     * 删除生产车间分布与环境情况信息
     * 
     * @param id 生产车间分布与环境情况主键
     * @return 结果
     */
    @Override
    public int deleteTWorkshopEnvironmentById(Long id)
    {
        return tWorkshopEnvironmentMapper.deleteTWorkshopEnvironmentById(id);
    }
}
