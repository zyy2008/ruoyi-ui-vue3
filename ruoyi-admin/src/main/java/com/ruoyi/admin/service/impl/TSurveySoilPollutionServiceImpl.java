package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSurveySoilPollutionMapper;
import com.ruoyi.admin.domain.TSurveySoilPollution;
import com.ruoyi.admin.service.ITSurveySoilPollutionService;

/**
 * 调查评估土壤污染区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-10
 */
@Service
public class TSurveySoilPollutionServiceImpl implements ITSurveySoilPollutionService 
{
    @Autowired
    private TSurveySoilPollutionMapper tSurveySoilPollutionMapper;

    /**
     * 查询调查评估土壤污染区信息
     * 
     * @param id 调查评估土壤污染区信息主键
     * @return 调查评估土壤污染区信息
     */
    @Override
    public TSurveySoilPollution selectTSurveySoilPollutionById(Long id)
    {
        return tSurveySoilPollutionMapper.selectTSurveySoilPollutionById(id);
    }

    /**
     * 查询调查评估土壤污染区信息列表
     * 
     * @param tSurveySoilPollution 调查评估土壤污染区信息
     * @return 调查评估土壤污染区信息
     */
    @Override
    public List<TSurveySoilPollution> selectTSurveySoilPollutionList(TSurveySoilPollution tSurveySoilPollution)
    {
        return tSurveySoilPollutionMapper.selectTSurveySoilPollutionList(tSurveySoilPollution);
    }

    /**
     * 新增调查评估土壤污染区信息
     * 
     * @param tSurveySoilPollution 调查评估土壤污染区信息
     * @return 结果
     */
    @Override
    public int insertTSurveySoilPollution(TSurveySoilPollution tSurveySoilPollution)
    {
        return tSurveySoilPollutionMapper.insertTSurveySoilPollution(tSurveySoilPollution);
    }

    /**
     * 修改调查评估土壤污染区信息
     * 
     * @param tSurveySoilPollution 调查评估土壤污染区信息
     * @return 结果
     */
    @Override
    public int updateTSurveySoilPollution(TSurveySoilPollution tSurveySoilPollution)
    {
        return tSurveySoilPollutionMapper.updateTSurveySoilPollution(tSurveySoilPollution);
    }

    /**
     * 批量删除调查评估土壤污染区信息
     * 
     * @param ids 需要删除的调查评估土壤污染区信息主键
     * @return 结果
     */
    @Override
    public int deleteTSurveySoilPollutionByIds(Long[] ids)
    {
        return tSurveySoilPollutionMapper.deleteTSurveySoilPollutionByIds(ids);
    }

    /**
     * 删除调查评估土壤污染区信息信息
     * 
     * @param id 调查评估土壤污染区信息主键
     * @return 结果
     */
    @Override
    public int deleteTSurveySoilPollutionById(Long id)
    {
        return tSurveySoilPollutionMapper.deleteTSurveySoilPollutionById(id);
    }
}
