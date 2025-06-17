package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TSurveyAssessment;

/**
 * 调查评估基本信息Service接口
 * 
 * @author ruoyi
 * @date 2025-06-10
 */
public interface ITSurveyAssessmentService 
{
    /**
     * 查询调查评估基本信息
     * 
     * @param id 调查评估基本信息主键
     * @return 调查评估基本信息
     */
    public TSurveyAssessment selectTSurveyAssessmentById(Long id);

    /**
     * 查询调查评估基本信息列表
     * 
     * @param tSurveyAssessment 调查评估基本信息
     * @return 调查评估基本信息集合
     */
    public List<TSurveyAssessment> selectTSurveyAssessmentList(TSurveyAssessment tSurveyAssessment);

    /**
     * 新增调查评估基本信息
     * 
     * @param tSurveyAssessment 调查评估基本信息
     * @return 结果
     */
    public int insertTSurveyAssessment(TSurveyAssessment tSurveyAssessment);

    /**
     * 修改调查评估基本信息
     * 
     * @param tSurveyAssessment 调查评估基本信息
     * @return 结果
     */
    public int updateTSurveyAssessment(TSurveyAssessment tSurveyAssessment);

    /**
     * 批量删除调查评估基本信息
     * 
     * @param ids 需要删除的调查评估基本信息主键集合
     * @return 结果
     */
    public int deleteTSurveyAssessmentByIds(Long[] ids);

    /**
     * 删除调查评估基本信息信息
     * 
     * @param id 调查评估基本信息主键
     * @return 结果
     */
    public int deleteTSurveyAssessmentById(Long id);
}
