package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSoilMonitoringMapper;
import com.ruoyi.admin.domain.TSoilMonitoring;
import com.ruoyi.admin.service.ITSoilMonitoringService;

/**
 * 土壤环境调查监测信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TSoilMonitoringServiceImpl implements ITSoilMonitoringService 
{
    @Autowired
    private TSoilMonitoringMapper tSoilMonitoringMapper;

    /**
     * 查询土壤环境调查监测信息
     * 
     * @param id 土壤环境调查监测信息主键
     * @return 土壤环境调查监测信息
     */
    @Override
    public TSoilMonitoring selectTSoilMonitoringById(Long id)
    {
        return tSoilMonitoringMapper.selectTSoilMonitoringById(id);
    }

    /**
     * 查询土壤环境调查监测信息列表
     * 
     * @param tSoilMonitoring 土壤环境调查监测信息
     * @return 土壤环境调查监测信息
     */
    @Override
    public List<TSoilMonitoring> selectTSoilMonitoringList(TSoilMonitoring tSoilMonitoring)
    {
        return tSoilMonitoringMapper.selectTSoilMonitoringList(tSoilMonitoring);
    }

    /**
     * 新增土壤环境调查监测信息
     * 
     * @param tSoilMonitoring 土壤环境调查监测信息
     * @return 结果
     */
    @Override
    public int insertTSoilMonitoring(TSoilMonitoring tSoilMonitoring)
    {
        return tSoilMonitoringMapper.insertTSoilMonitoring(tSoilMonitoring);
    }

    /**
     * 修改土壤环境调查监测信息
     * 
     * @param tSoilMonitoring 土壤环境调查监测信息
     * @return 结果
     */
    @Override
    public int updateTSoilMonitoring(TSoilMonitoring tSoilMonitoring)
    {
        return tSoilMonitoringMapper.updateTSoilMonitoring(tSoilMonitoring);
    }

    /**
     * 批量删除土壤环境调查监测信息
     * 
     * @param ids 需要删除的土壤环境调查监测信息主键
     * @return 结果
     */
    @Override
    public int deleteTSoilMonitoringByIds(Long[] ids)
    {
        return tSoilMonitoringMapper.deleteTSoilMonitoringByIds(ids);
    }

    /**
     * 删除土壤环境调查监测信息信息
     * 
     * @param id 土壤环境调查监测信息主键
     * @return 结果
     */
    @Override
    public int deleteTSoilMonitoringById(Long id)
    {
        return tSoilMonitoringMapper.deleteTSoilMonitoringById(id);
    }
}
