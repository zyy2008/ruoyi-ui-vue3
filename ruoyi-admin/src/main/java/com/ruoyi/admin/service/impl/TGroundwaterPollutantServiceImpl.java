package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TGroundwaterPollutantMapper;
import com.ruoyi.admin.domain.TGroundwaterPollutant;
import com.ruoyi.admin.service.ITGroundwaterPollutantService;

/**
 * 地下水监测发现的超标污染物信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TGroundwaterPollutantServiceImpl implements ITGroundwaterPollutantService 
{
    @Autowired
    private TGroundwaterPollutantMapper tGroundwaterPollutantMapper;

    /**
     * 查询地下水监测发现的超标污染物信息
     * 
     * @param id 地下水监测发现的超标污染物信息主键
     * @return 地下水监测发现的超标污染物信息
     */
    @Override
    public TGroundwaterPollutant selectTGroundwaterPollutantById(Long id)
    {
        return tGroundwaterPollutantMapper.selectTGroundwaterPollutantById(id);
    }

    /**
     * 查询地下水监测发现的超标污染物信息列表
     * 
     * @param tGroundwaterPollutant 地下水监测发现的超标污染物信息
     * @return 地下水监测发现的超标污染物信息
     */
    @Override
    public List<TGroundwaterPollutant> selectTGroundwaterPollutantList(TGroundwaterPollutant tGroundwaterPollutant)
    {
        return tGroundwaterPollutantMapper.selectTGroundwaterPollutantList(tGroundwaterPollutant);
    }

    /**
     * 新增地下水监测发现的超标污染物信息
     * 
     * @param tGroundwaterPollutant 地下水监测发现的超标污染物信息
     * @return 结果
     */
    @Override
    public int insertTGroundwaterPollutant(TGroundwaterPollutant tGroundwaterPollutant)
    {
        return tGroundwaterPollutantMapper.insertTGroundwaterPollutant(tGroundwaterPollutant);
    }

    /**
     * 修改地下水监测发现的超标污染物信息
     * 
     * @param tGroundwaterPollutant 地下水监测发现的超标污染物信息
     * @return 结果
     */
    @Override
    public int updateTGroundwaterPollutant(TGroundwaterPollutant tGroundwaterPollutant)
    {
        return tGroundwaterPollutantMapper.updateTGroundwaterPollutant(tGroundwaterPollutant);
    }

    /**
     * 批量删除地下水监测发现的超标污染物信息
     * 
     * @param ids 需要删除的地下水监测发现的超标污染物信息主键
     * @return 结果
     */
    @Override
    public int deleteTGroundwaterPollutantByIds(Long[] ids)
    {
        return tGroundwaterPollutantMapper.deleteTGroundwaterPollutantByIds(ids);
    }

    /**
     * 删除地下水监测发现的超标污染物信息信息
     * 
     * @param id 地下水监测发现的超标污染物信息主键
     * @return 结果
     */
    @Override
    public int deleteTGroundwaterPollutantById(Long id)
    {
        return tGroundwaterPollutantMapper.deleteTGroundwaterPollutantById(id);
    }
}
