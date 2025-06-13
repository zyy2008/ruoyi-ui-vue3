package com.ruoyi.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TRealtimeWaterQualityMonitoringMapper;
import com.ruoyi.admin.domain.TRealtimeWaterQualityMonitoring;
import com.ruoyi.admin.service.ITRealtimeWaterQualityMonitoringService;

/**
 * 实时数据监测Service业务层处理
 *
 * @author ruoyi
 * @date 2025-06-13
 */
@Service
public class TRealtimeWaterQualityMonitoringServiceImpl implements ITRealtimeWaterQualityMonitoringService {
    @Autowired
    private TRealtimeWaterQualityMonitoringMapper tRealtimeWaterQualityMonitoringMapper;

    /**
     * 查询实时数据监测
     *
     * @param id 实时数据监测主键
     * @return 实时数据监测
     */
    @Override
    public TRealtimeWaterQualityMonitoring selectTRealtimeWaterQualityMonitoringById(String id) {
        return tRealtimeWaterQualityMonitoringMapper.selectTRealtimeWaterQualityMonitoringById(id);
    }

    /**
     * 查询实时数据监测列表
     *
     * @param tRealtimeWaterQualityMonitoring 实时数据监测
     * @return 实时数据监测
     */
    @Override
    public List<TRealtimeWaterQualityMonitoring> selectTRealtimeWaterQualityMonitoringList(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        return tRealtimeWaterQualityMonitoringMapper.selectTRealtimeWaterQualityMonitoringList(tRealtimeWaterQualityMonitoring);
    }

    @Override
    public List<TRealtimeWaterQualityMonitoring> getBatchData(String deptIdString) {
        List<String> deptId = new ArrayList<>();
        if (-1 == deptIdString.indexOf(",")) {
            String[] wellArr = deptIdString.split(",");
            deptId = Arrays.asList(wellArr);
        } else {
            deptId.add(deptIdString);
        }
        if (deptId.isEmpty())
            return new ArrayList<>();
        return tRealtimeWaterQualityMonitoringMapper.getBatchData(deptId);
    }

    /**
     * 新增实时数据监测
     *
     * @param tRealtimeWaterQualityMonitoring 实时数据监测
     * @return 结果
     */
    @Override
    public int insertTRealtimeWaterQualityMonitoring(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        return tRealtimeWaterQualityMonitoringMapper.insertTRealtimeWaterQualityMonitoring(tRealtimeWaterQualityMonitoring);
    }

    /**
     * 修改实时数据监测
     *
     * @param tRealtimeWaterQualityMonitoring 实时数据监测
     * @return 结果
     */
    @Override
    public int updateTRealtimeWaterQualityMonitoring(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        return tRealtimeWaterQualityMonitoringMapper.updateTRealtimeWaterQualityMonitoring(tRealtimeWaterQualityMonitoring);
    }

    /**
     * 批量删除实时数据监测
     *
     * @param ids 需要删除的实时数据监测主键
     * @return 结果
     */
    @Override
    public int deleteTRealtimeWaterQualityMonitoringByIds(String[] ids) {
        return tRealtimeWaterQualityMonitoringMapper.deleteTRealtimeWaterQualityMonitoringByIds(ids);
    }

    /**
     * 删除实时数据监测信息
     *
     * @param id 实时数据监测主键
     * @return 结果
     */
    @Override
    public int deleteTRealtimeWaterQualityMonitoringById(String id) {
        return tRealtimeWaterQualityMonitoringMapper.deleteTRealtimeWaterQualityMonitoringById(id);
    }
}
