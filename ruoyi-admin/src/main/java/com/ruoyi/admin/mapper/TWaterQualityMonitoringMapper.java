package com.ruoyi.admin.mapper;

import java.util.List;

import com.ruoyi.admin.domain.GJJLRES;
import com.ruoyi.admin.domain.TWaterQualityMonitoring;

/**
 * 监测数据管理Mapper接口
 *
 * @author ruoyi
 * @date 2025-06-01
 */
public interface TWaterQualityMonitoringMapper
{

    public List<GJJLRES> selectGJJL();
    /**
     * 查询监测数据管理
     *
     * @param id 监测数据管理主键
     * @return 监测数据管理
     */
    public TWaterQualityMonitoring selectTWaterQualityMonitoringById(String id);

    /**
     * 查询监测数据管理列表
     *
     * @param tWaterQualityMonitoring 监测数据管理
     * @return 监测数据管理集合
     */
    public List<TWaterQualityMonitoring> selectTWaterQualityMonitoringList(TWaterQualityMonitoring tWaterQualityMonitoring);

    /**
     * 新增监测数据管理
     *
     * @param tWaterQualityMonitoring 监测数据管理
     * @return 结果
     */
    public int insertTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring);

    /**
     * 修改监测数据管理
     *
     * @param tWaterQualityMonitoring 监测数据管理
     * @return 结果
     */
    public int updateTWaterQualityMonitoring(TWaterQualityMonitoring tWaterQualityMonitoring);

    /**
     * 删除监测数据管理
     *
     * @param id 监测数据管理主键
     * @return 结果
     */
    public int deleteTWaterQualityMonitoringById(String id);

    /**
     * 批量删除监测数据管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTWaterQualityMonitoringByIds(String[] ids);
}
