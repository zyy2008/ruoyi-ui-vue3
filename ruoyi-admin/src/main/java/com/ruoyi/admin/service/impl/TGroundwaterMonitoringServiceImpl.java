package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TGroundwaterMonitoringMapper;
import com.ruoyi.admin.domain.TGroundwaterMonitoring;
import com.ruoyi.admin.service.ITGroundwaterMonitoringService;

/**
 * 地下水环境调查监测信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TGroundwaterMonitoringServiceImpl implements ITGroundwaterMonitoringService 
{
    @Autowired
    private TGroundwaterMonitoringMapper tGroundwaterMonitoringMapper;

    /**
     * 查询地下水环境调查监测信息
     * 
     * @param id 地下水环境调查监测信息主键
     * @return 地下水环境调查监测信息
     */
    @Override
    public TGroundwaterMonitoring selectTGroundwaterMonitoringById(Long id)
    {
        return tGroundwaterMonitoringMapper.selectTGroundwaterMonitoringById(id);
    }

    /**
     * 查询地下水环境调查监测信息列表
     * 
     * @param tGroundwaterMonitoring 地下水环境调查监测信息
     * @return 地下水环境调查监测信息
     */
    @Override
    public List<TGroundwaterMonitoring> selectTGroundwaterMonitoringList(TGroundwaterMonitoring tGroundwaterMonitoring)
    {
        return tGroundwaterMonitoringMapper.selectTGroundwaterMonitoringList(tGroundwaterMonitoring);
    }

    /**
     * 新增地下水环境调查监测信息
     * 
     * @param tGroundwaterMonitoring 地下水环境调查监测信息
     * @return 结果
     */
    @Override
    public int insertTGroundwaterMonitoring(TGroundwaterMonitoring tGroundwaterMonitoring)
    {
        return tGroundwaterMonitoringMapper.insertTGroundwaterMonitoring(tGroundwaterMonitoring);
    }

    /**
     * 修改地下水环境调查监测信息
     * 
     * @param tGroundwaterMonitoring 地下水环境调查监测信息
     * @return 结果
     */
    @Override
    public int updateTGroundwaterMonitoring(TGroundwaterMonitoring tGroundwaterMonitoring)
    {
        return tGroundwaterMonitoringMapper.updateTGroundwaterMonitoring(tGroundwaterMonitoring);
    }

    /**
     * 批量删除地下水环境调查监测信息
     * 
     * @param ids 需要删除的地下水环境调查监测信息主键
     * @return 结果
     */
    @Override
    public int deleteTGroundwaterMonitoringByIds(Long[] ids)
    {
        return tGroundwaterMonitoringMapper.deleteTGroundwaterMonitoringByIds(ids);
    }

    /**
     * 删除地下水环境调查监测信息信息
     * 
     * @param id 地下水环境调查监测信息主键
     * @return 结果
     */
    @Override
    public int deleteTGroundwaterMonitoringById(Long id)
    {
        return tGroundwaterMonitoringMapper.deleteTGroundwaterMonitoringById(id);
    }
}
