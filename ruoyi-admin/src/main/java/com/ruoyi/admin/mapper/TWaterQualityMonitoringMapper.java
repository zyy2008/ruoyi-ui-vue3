package com.ruoyi.admin.mapper;


import com.ruoyi.admin.domain.TWaterQualityMonitoring;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
public interface TWaterQualityMonitoringMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param pointId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TWaterQualityMonitoring selectTWaterQualityMonitoringByPointId(String pointId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tWaterQualityMonitoring 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TWaterQualityMonitoring> selectTWaterQualityMonitoringList(TWaterQualityMonitoring tWaterQualityMonitoring);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tWaterQualityMonitoring 【请填写功能名称】
     * @return 结果
     */
    public int insertTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tWaterQualityMonitoring 【请填写功能名称】
     * @return 结果
     */
    public int updateTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring);

    /**
     * 删除【请填写功能名称】
     * 
     * @param pointId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTWaterQualityMonitoringByPointId(String pointId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param pointIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTWaterQualityMonitoringByPointIds(String[] pointIds);
}
