package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSurveyAssessmentMapper;
import com.ruoyi.admin.domain.TSurveyAssessment;
import com.ruoyi.admin.service.ITSurveyAssessmentService;

/**
 * 调查评估基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-10
 */
@Service
public class TSurveyAssessmentServiceImpl implements ITSurveyAssessmentService 
{
    @Autowired
    private TSurveyAssessmentMapper tSurveyAssessmentMapper;

    /**
     * 查询调查评估基本信息
     * 
     * @param id 调查评估基本信息主键
     * @return 调查评估基本信息
     */
    @Override
    public TSurveyAssessment selectTSurveyAssessmentById(Long id)
    {
        return tSurveyAssessmentMapper.selectTSurveyAssessmentById(id);
    }

    /**
     * 查询调查评估基本信息列表
     * 
     * @param tSurveyAssessment 调查评估基本信息
     * @return 调查评估基本信息
     */
    @Override
    public List<TSurveyAssessment> selectTSurveyAssessmentList(TSurveyAssessment tSurveyAssessment)
    {
        return tSurveyAssessmentMapper.selectTSurveyAssessmentList(tSurveyAssessment);
    }

    /**
     * 新增调查评估基本信息
     * 
     * @param tSurveyAssessment 调查评估基本信息
     * @return 结果
     */
    @Override
    public int insertTSurveyAssessment(TSurveyAssessment tSurveyAssessment)
    {
        return tSurveyAssessmentMapper.insertTSurveyAssessment(tSurveyAssessment);
    }

    /**
     * 修改调查评估基本信息
     * 
     * @param tSurveyAssessment 调查评估基本信息
     * @return 结果
     */
    @Override
    public int updateTSurveyAssessment(TSurveyAssessment tSurveyAssessment)
    {
        return tSurveyAssessmentMapper.updateTSurveyAssessment(tSurveyAssessment);
    }

    /**
     * 批量删除调查评估基本信息
     * 
     * @param ids 需要删除的调查评估基本信息主键
     * @return 结果
     */
    @Override
    public int deleteTSurveyAssessmentByIds(Long[] ids)
    {
        return tSurveyAssessmentMapper.deleteTSurveyAssessmentByIds(ids);
    }

    /**
     * 删除调查评估基本信息信息
     * 
     * @param id 调查评估基本信息主键
     * @return 结果
     */
    @Override
    public int deleteTSurveyAssessmentById(Long id)
    {
        return tSurveyAssessmentMapper.deleteTSurveyAssessmentById(id);
    }
}
