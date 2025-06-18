package com.ruoyi.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TMonitoringWellsMapper;
import com.ruoyi.admin.domain.TMonitoringWells;
import com.ruoyi.admin.service.ITMonitoringWellsService;

/**
 * 园区初调监测井信息，记录每个监测井的基础属性与附加信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TMonitoringWellsServiceImpl implements ITMonitoringWellsService {
    @Autowired
    private TMonitoringWellsMapper tMonitoringWellsMapper;

    /**
     * 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息
     *
     * @param id 园区初调监测井信息，记录每个监测井的基础属性与附加信息主键
     * @return 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     */
    @Override
    public TMonitoringWells selectTMonitoringWellsById(String id) {
        return tMonitoringWellsMapper.selectTMonitoringWellsById(id);
    }

    /**
     * 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息列表
     *
     * @param tMonitoringWells 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * @return 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     */
    @Override
    public List<TMonitoringWells> selectTMonitoringWellsList(TMonitoringWells tMonitoringWells) {
        return tMonitoringWellsMapper.selectTMonitoringWellsList(tMonitoringWells);
    }

    /**
     * 新增园区初调监测井信息，记录每个监测井的基础属性与附加信息
     *
     * @param tMonitoringWells 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * @return 结果
     */
    @Override
    public int insertTMonitoringWells(TMonitoringWells tMonitoringWells) {
        return tMonitoringWellsMapper.insertTMonitoringWells(tMonitoringWells);
    }

    /**
     * 修改园区初调监测井信息，记录每个监测井的基础属性与附加信息
     *
     * @param tMonitoringWells 园区初调监测井信息，记录每个监测井的基础属性与附加信息
     * @return 结果
     */
    @Override
    public int updateTMonitoringWells(TMonitoringWells tMonitoringWells) {
        return tMonitoringWellsMapper.updateTMonitoringWells(tMonitoringWells);
    }

    /**
     * 批量删除园区初调监测井信息，记录每个监测井的基础属性与附加信息
     *
     * @param ids 需要删除的园区初调监测井信息，记录每个监测井的基础属性与附加信息主键
     * @return 结果
     */
    @Override
    public int deleteTMonitoringWellsByIds(String[] ids) {
        return tMonitoringWellsMapper.deleteTMonitoringWellsByIds(ids);
    }

    /**
     * 删除园区初调监测井信息，记录每个监测井的基础属性与附加信息信息
     *
     * @param id 园区初调监测井信息，记录每个监测井的基础属性与附加信息主键
     * @return 结果
     */
    @Override
    public int deleteTMonitoringWellsById(String id) {
        return tMonitoringWellsMapper.deleteTMonitoringWellsById(id);
    }
}
