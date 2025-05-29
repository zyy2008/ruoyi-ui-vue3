package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TGroundwaterMonitoring;

/**
 * 地下水环境调查监测信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TGroundwaterMonitoringMapper 
{
    /**
     * 查询地下水环境调查监测信息
     * 
     * @param id 地下水环境调查监测信息主键
     * @return 地下水环境调查监测信息
     */
    public TGroundwaterMonitoring selectTGroundwaterMonitoringById(Long id);

    /**
     * 查询地下水环境调查监测信息列表
     * 
     * @param tGroundwaterMonitoring 地下水环境调查监测信息
     * @return 地下水环境调查监测信息集合
     */
    public List<TGroundwaterMonitoring> selectTGroundwaterMonitoringList(TGroundwaterMonitoring tGroundwaterMonitoring);

    /**
     * 新增地下水环境调查监测信息
     * 
     * @param tGroundwaterMonitoring 地下水环境调查监测信息
     * @return 结果
     */
    public int insertTGroundwaterMonitoring(TGroundwaterMonitoring tGroundwaterMonitoring);

    /**
     * 修改地下水环境调查监测信息
     * 
     * @param tGroundwaterMonitoring 地下水环境调查监测信息
     * @return 结果
     */
    public int updateTGroundwaterMonitoring(TGroundwaterMonitoring tGroundwaterMonitoring);

    /**
     * 删除地下水环境调查监测信息
     * 
     * @param id 地下水环境调查监测信息主键
     * @return 结果
     */
    public int deleteTGroundwaterMonitoringById(Long id);

    /**
     * 批量删除地下水环境调查监测信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTGroundwaterMonitoringByIds(Long[] ids);
}
