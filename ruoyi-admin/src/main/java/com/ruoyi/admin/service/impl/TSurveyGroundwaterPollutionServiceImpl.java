package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSurveyGroundwaterPollutionMapper;
import com.ruoyi.admin.domain.TSurveyGroundwaterPollution;
import com.ruoyi.admin.service.ITSurveyGroundwaterPollutionService;

/**
 * 调查评估地下水污染区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-10
 */
@Service
public class TSurveyGroundwaterPollutionServiceImpl implements ITSurveyGroundwaterPollutionService 
{
    @Autowired
    private TSurveyGroundwaterPollutionMapper tSurveyGroundwaterPollutionMapper;

    /**
     * 查询调查评估地下水污染区信息
     * 
     * @param id 调查评估地下水污染区信息主键
     * @return 调查评估地下水污染区信息
     */
    @Override
    public TSurveyGroundwaterPollution selectTSurveyGroundwaterPollutionById(Long id)
    {
        return tSurveyGroundwaterPollutionMapper.selectTSurveyGroundwaterPollutionById(id);
    }

    /**
     * 查询调查评估地下水污染区信息列表
     * 
     * @param tSurveyGroundwaterPollution 调查评估地下水污染区信息
     * @return 调查评估地下水污染区信息
     */
    @Override
    public List<TSurveyGroundwaterPollution> selectTSurveyGroundwaterPollutionList(TSurveyGroundwaterPollution tSurveyGroundwaterPollution)
    {
        return tSurveyGroundwaterPollutionMapper.selectTSurveyGroundwaterPollutionList(tSurveyGroundwaterPollution);
    }

    /**
     * 新增调查评估地下水污染区信息
     * 
     * @param tSurveyGroundwaterPollution 调查评估地下水污染区信息
     * @return 结果
     */
    @Override
    public int insertTSurveyGroundwaterPollution(TSurveyGroundwaterPollution tSurveyGroundwaterPollution)
    {
        return tSurveyGroundwaterPollutionMapper.insertTSurveyGroundwaterPollution(tSurveyGroundwaterPollution);
    }

    /**
     * 修改调查评估地下水污染区信息
     * 
     * @param tSurveyGroundwaterPollution 调查评估地下水污染区信息
     * @return 结果
     */
    @Override
    public int updateTSurveyGroundwaterPollution(TSurveyGroundwaterPollution tSurveyGroundwaterPollution)
    {
        return tSurveyGroundwaterPollutionMapper.updateTSurveyGroundwaterPollution(tSurveyGroundwaterPollution);
    }

    /**
     * 批量删除调查评估地下水污染区信息
     * 
     * @param ids 需要删除的调查评估地下水污染区信息主键
     * @return 结果
     */
    @Override
    public int deleteTSurveyGroundwaterPollutionByIds(Long[] ids)
    {
        return tSurveyGroundwaterPollutionMapper.deleteTSurveyGroundwaterPollutionByIds(ids);
    }

    /**
     * 删除调查评估地下水污染区信息信息
     * 
     * @param id 调查评估地下水污染区信息主键
     * @return 结果
     */
    @Override
    public int deleteTSurveyGroundwaterPollutionById(Long id)
    {
        return tSurveyGroundwaterPollutionMapper.deleteTSurveyGroundwaterPollutionById(id);
    }
}
