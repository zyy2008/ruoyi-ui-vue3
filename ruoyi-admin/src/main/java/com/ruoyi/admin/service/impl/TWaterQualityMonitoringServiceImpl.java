package com.ruoyi.admin.service.impl;

import java.util.List;

import com.ruoyi.admin.domain.TWaterQualityMonitoring;
import com.ruoyi.admin.mapper.TWaterQualityMonitoringMapper;
import com.ruoyi.admin.service.ITWaterQualityMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
@Service
public class TWaterQualityMonitoringServiceImpl implements ITWaterQualityMonitoringService
{
    @Autowired
    private TWaterQualityMonitoringMapper tWaterQualityMonitoringMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param pointId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TWaterQualityMonitoring selectTWaterQualityMonitoringByPointId(String pointId)
    {
        return tWaterQualityMonitoringMapper.selectTWaterQualityMonitoringByPointId(pointId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tWaterQualityMonitoring 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TWaterQualityMonitoring> selectTWaterQualityMonitoringList(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return tWaterQualityMonitoringMapper.selectTWaterQualityMonitoringList(tWaterQualityMonitoring);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tWaterQualityMonitoring 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return tWaterQualityMonitoringMapper.insertTWaterQualityMonitoring(tWaterQualityMonitoring);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tWaterQualityMonitoring 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return tWaterQualityMonitoringMapper.updateTWaterQualityMonitoring(tWaterQualityMonitoring);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param pointIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTWaterQualityMonitoringByPointIds(String[] pointIds)
    {
        return tWaterQualityMonitoringMapper.deleteTWaterQualityMonitoringByPointIds(pointIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param pointId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTWaterQualityMonitoringByPointId(String pointId)
    {
        return tWaterQualityMonitoringMapper.deleteTWaterQualityMonitoringByPointId(pointId);
    }
}
