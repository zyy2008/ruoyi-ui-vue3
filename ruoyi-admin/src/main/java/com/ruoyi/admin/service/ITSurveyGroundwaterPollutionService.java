package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TSurveyGroundwaterPollution;

/**
 * 调查评估地下水污染区信息Service接口
 * 
 * @author ruoyi
 * @date 2025-06-10
 */
public interface ITSurveyGroundwaterPollutionService 
{
    /**
     * 查询调查评估地下水污染区信息
     * 
     * @param id 调查评估地下水污染区信息主键
     * @return 调查评估地下水污染区信息
     */
    public TSurveyGroundwaterPollution selectTSurveyGroundwaterPollutionById(Long id);

    /**
     * 查询调查评估地下水污染区信息列表
     * 
     * @param tSurveyGroundwaterPollution 调查评估地下水污染区信息
     * @return 调查评估地下水污染区信息集合
     */
    public List<TSurveyGroundwaterPollution> selectTSurveyGroundwaterPollutionList(TSurveyGroundwaterPollution tSurveyGroundwaterPollution);

    /**
     * 新增调查评估地下水污染区信息
     * 
     * @param tSurveyGroundwaterPollution 调查评估地下水污染区信息
     * @return 结果
     */
    public int insertTSurveyGroundwaterPollution(TSurveyGroundwaterPollution tSurveyGroundwaterPollution);

    /**
     * 修改调查评估地下水污染区信息
     * 
     * @param tSurveyGroundwaterPollution 调查评估地下水污染区信息
     * @return 结果
     */
    public int updateTSurveyGroundwaterPollution(TSurveyGroundwaterPollution tSurveyGroundwaterPollution);

    /**
     * 批量删除调查评估地下水污染区信息
     * 
     * @param ids 需要删除的调查评估地下水污染区信息主键集合
     * @return 结果
     */
    public int deleteTSurveyGroundwaterPollutionByIds(Long[] ids);

    /**
     * 删除调查评估地下水污染区信息信息
     * 
     * @param id 调查评估地下水污染区信息主键
     * @return 结果
     */
    public int deleteTSurveyGroundwaterPollutionById(Long id);
}
