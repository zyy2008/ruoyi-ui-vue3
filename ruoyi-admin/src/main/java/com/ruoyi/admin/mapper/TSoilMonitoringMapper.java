package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TSoilMonitoring;

/**
 * 土壤环境调查监测信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TSoilMonitoringMapper 
{
    /**
     * 查询土壤环境调查监测信息
     * 
     * @param id 土壤环境调查监测信息主键
     * @return 土壤环境调查监测信息
     */
    public TSoilMonitoring selectTSoilMonitoringById(Long id);

    /**
     * 查询土壤环境调查监测信息列表
     * 
     * @param tSoilMonitoring 土壤环境调查监测信息
     * @return 土壤环境调查监测信息集合
     */
    public List<TSoilMonitoring> selectTSoilMonitoringList(TSoilMonitoring tSoilMonitoring);

    /**
     * 新增土壤环境调查监测信息
     * 
     * @param tSoilMonitoring 土壤环境调查监测信息
     * @return 结果
     */
    public int insertTSoilMonitoring(TSoilMonitoring tSoilMonitoring);

    /**
     * 修改土壤环境调查监测信息
     * 
     * @param tSoilMonitoring 土壤环境调查监测信息
     * @return 结果
     */
    public int updateTSoilMonitoring(TSoilMonitoring tSoilMonitoring);

    /**
     * 删除土壤环境调查监测信息
     * 
     * @param id 土壤环境调查监测信息主键
     * @return 结果
     */
    public int deleteTSoilMonitoringById(Long id);

    /**
     * 批量删除土壤环境调查监测信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSoilMonitoringByIds(Long[] ids);
}
