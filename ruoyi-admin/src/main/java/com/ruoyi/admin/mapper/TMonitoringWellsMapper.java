package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TMonitoringWells;

/**
 * 园区初调监测井信息，记录每个监测井的基础属性与附加信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TMonitoringWellsMapper 
{
    /**
     * 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * 
     * @param id 园区初调监测井信息，记录每个监测井的基础属性与附加信息主键
     * @return 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     */
    public TMonitoringWells selectTMonitoringWellsById(Long id);

    /**
     * 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息列表
     * 
     * @param tMonitoringWells 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * @return 园区初调监测井信息，记录每个监测井的基础属性与附加信息集合
     */
    public List<TMonitoringWells> selectTMonitoringWellsList(TMonitoringWells tMonitoringWells);

    /**
     * 新增园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * 
     * @param tMonitoringWells 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * @return 结果
     */
    public int insertTMonitoringWells(TMonitoringWells tMonitoringWells);

    /**
     * 修改园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * 
     * @param tMonitoringWells 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * @return 结果
     */
    public int updateTMonitoringWells(TMonitoringWells tMonitoringWells);

    /**
     * 删除园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * 
     * @param id 园区初调监测井信息，记录每个监测井的基础属性与附加信息主键
     * @return 结果
     */
    public int deleteTMonitoringWellsById(Long id);

    /**
     * 批量删除园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTMonitoringWellsByIds(Long[] ids);
}
