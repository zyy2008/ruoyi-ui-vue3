package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TRealtimeWaterQualityMonitoring;

/**
 * 实时数据监测Service接口
 * 
 * @author ruoyi
 * @date 2025-06-13
 */
public interface ITRealtimeWaterQualityMonitoringService 
{
    /**
     * 查询实时数据监测
     * 
     * @param id 实时数据监测主键
     * @return 实时数据监测
     */
    public TRealtimeWaterQualityMonitoring selectTRealtimeWaterQualityMonitoringById(String id);

    /**
     * 查询实时数据监测列表
     * 
     * @param tRealtimeWaterQualityMonitoring 实时数据监测
     * @return 实时数据监测集合
     */
    public List<TRealtimeWaterQualityMonitoring> selectTRealtimeWaterQualityMonitoringList(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring);

    /**
     * 新增实时数据监测
     * 
     * @param tRealtimeWaterQualityMonitoring 实时数据监测
     * @return 结果
     */
    public int insertTRealtimeWaterQualityMonitoring(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring);

    /**
     * 修改实时数据监测
     * 
     * @param tRealtimeWaterQualityMonitoring 实时数据监测
     * @return 结果
     */
    public int updateTRealtimeWaterQualityMonitoring(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring);

    /**
     * 批量删除实时数据监测
     * 
     * @param ids 需要删除的实时数据监测主键集合
     * @return 结果
     */
    public int deleteTRealtimeWaterQualityMonitoringByIds(String[] ids);

    /**
     * 删除实时数据监测信息
     * 
     * @param id 实时数据监测主键
     * @return 结果
     */
    public int deleteTRealtimeWaterQualityMonitoringById(String id);
}
