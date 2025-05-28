package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSoilPollutantMapper;
import com.ruoyi.admin.domain.TSoilPollutant;
import com.ruoyi.admin.service.ITSoilPollutantService;

/**
 * 土壤监测发现的超标污染物信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TSoilPollutantServiceImpl implements ITSoilPollutantService 
{
    @Autowired
    private TSoilPollutantMapper tSoilPollutantMapper;

    /**
     * 查询土壤监测发现的超标污染物信息
     * 
     * @param id 土壤监测发现的超标污染物信息主键
     * @return 土壤监测发现的超标污染物信息
     */
    @Override
    public TSoilPollutant selectTSoilPollutantById(Long id)
    {
        return tSoilPollutantMapper.selectTSoilPollutantById(id);
    }

    /**
     * 查询土壤监测发现的超标污染物信息列表
     * 
     * @param tSoilPollutant 土壤监测发现的超标污染物信息
     * @return 土壤监测发现的超标污染物信息
     */
    @Override
    public List<TSoilPollutant> selectTSoilPollutantList(TSoilPollutant tSoilPollutant)
    {
        return tSoilPollutantMapper.selectTSoilPollutantList(tSoilPollutant);
    }

    /**
     * 新增土壤监测发现的超标污染物信息
     * 
     * @param tSoilPollutant 土壤监测发现的超标污染物信息
     * @return 结果
     */
    @Override
    public int insertTSoilPollutant(TSoilPollutant tSoilPollutant)
    {
        return tSoilPollutantMapper.insertTSoilPollutant(tSoilPollutant);
    }

    /**
     * 修改土壤监测发现的超标污染物信息
     * 
     * @param tSoilPollutant 土壤监测发现的超标污染物信息
     * @return 结果
     */
    @Override
    public int updateTSoilPollutant(TSoilPollutant tSoilPollutant)
    {
        return tSoilPollutantMapper.updateTSoilPollutant(tSoilPollutant);
    }

    /**
     * 批量删除土壤监测发现的超标污染物信息
     * 
     * @param ids 需要删除的土壤监测发现的超标污染物信息主键
     * @return 结果
     */
    @Override
    public int deleteTSoilPollutantByIds(Long[] ids)
    {
        return tSoilPollutantMapper.deleteTSoilPollutantByIds(ids);
    }

    /**
     * 删除土壤监测发现的超标污染物信息信息
     * 
     * @param id 土壤监测发现的超标污染物信息主键
     * @return 结果
     */
    @Override
    public int deleteTSoilPollutantById(Long id)
    {
        return tSoilPollutantMapper.deleteTSoilPollutantById(id);
    }
}
