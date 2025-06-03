package com.ruoyi.admin.service.impl;

import java.util.List;

import com.ruoyi.admin.domain.GJJLRES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TWaterQualityMonitoringMapper;
import com.ruoyi.admin.domain.TWaterQualityMonitoring;
import com.ruoyi.admin.service.ITWaterQualityMonitoringService;

/**
 * 监测数据管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-06-01
 */
@Service
public class TWaterQualityMonitoringServiceImpl implements ITWaterQualityMonitoringService
{
    @Autowired
    private TWaterQualityMonitoringMapper tWaterQualityMonitoringMapper;


    @Override
    public List<GJJLRES> selectGJJL(){
        return tWaterQualityMonitoringMapper.selectGJJL();
    }
    /**
     * 查询监测数据管理
     *
     * @param id 监测数据管理主键
     * @return 监测数据管理
     */
    @Override
    public TWaterQualityMonitoring selectTWaterQualityMonitoringById(String id)
    {
        return tWaterQualityMonitoringMapper.selectTWaterQualityMonitoringById(id);
    }

    /**
     * 查询监测数据管理列表
     *
     * @param tWaterQualityMonitoring 监测数据管理
     * @return 监测数据管理
     */
    @Override
    public List<TWaterQualityMonitoring> selectTWaterQualityMonitoringList(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return tWaterQualityMonitoringMapper.selectTWaterQualityMonitoringList(tWaterQualityMonitoring);
    }

    /**
     * 新增监测数据管理
     *
     * @param tWaterQualityMonitoring 监测数据管理
     * @return 结果
     */
    @Override
    public int insertTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return tWaterQualityMonitoringMapper.insertTWaterQualityMonitoring(tWaterQualityMonitoring);
    }

    /**
     * 修改监测数据管理
     *
     * @param tWaterQualityMonitoring 监测数据管理
     * @return 结果
     */
    @Override
    public int updateTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return tWaterQualityMonitoringMapper.updateTWaterQualityMonitoring(tWaterQualityMonitoring);
    }

    /**
     * 批量删除监测数据管理
     *
     * @param ids 需要删除的监测数据管理主键
     * @return 结果
     */
    @Override
    public int deleteTWaterQualityMonitoringByIds(String[] ids)
    {
        return tWaterQualityMonitoringMapper.deleteTWaterQualityMonitoringByIds(ids);
    }

    /**
     * 删除监测数据管理信息
     *
     * @param id 监测数据管理主键
     * @return 结果
     */
    @Override
    public int deleteTWaterQualityMonitoringById(String id)
    {
        return tWaterQualityMonitoringMapper.deleteTWaterQualityMonitoringById(id);
    }
}
