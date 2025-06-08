package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TLandPollutionAnalysisMapper;
import com.ruoyi.admin.domain.TLandPollutionAnalysis;
import com.ruoyi.admin.service.ITLandPollutionAnalysisService;

/**
 * 地块污染情况分析Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
@Service
public class TLandPollutionAnalysisServiceImpl implements ITLandPollutionAnalysisService 
{
    @Autowired
    private TLandPollutionAnalysisMapper tLandPollutionAnalysisMapper;

    /**
     * 查询地块污染情况分析
     * 
     * @param id 地块污染情况分析主键
     * @return 地块污染情况分析
     */
    @Override
    public TLandPollutionAnalysis selectTLandPollutionAnalysisById(Long id)
    {
        return tLandPollutionAnalysisMapper.selectTLandPollutionAnalysisById(id);
    }

    /**
     * 查询地块污染情况分析列表
     * 
     * @param tLandPollutionAnalysis 地块污染情况分析
     * @return 地块污染情况分析
     */
    @Override
    public List<TLandPollutionAnalysis> selectTLandPollutionAnalysisList(TLandPollutionAnalysis tLandPollutionAnalysis)
    {
        return tLandPollutionAnalysisMapper.selectTLandPollutionAnalysisList(tLandPollutionAnalysis);
    }

    /**
     * 新增地块污染情况分析
     * 
     * @param tLandPollutionAnalysis 地块污染情况分析
     * @return 结果
     */
    @Override
    public int insertTLandPollutionAnalysis(TLandPollutionAnalysis tLandPollutionAnalysis)
    {
        return tLandPollutionAnalysisMapper.insertTLandPollutionAnalysis(tLandPollutionAnalysis);
    }

    /**
     * 修改地块污染情况分析
     * 
     * @param tLandPollutionAnalysis 地块污染情况分析
     * @return 结果
     */
    @Override
    public int updateTLandPollutionAnalysis(TLandPollutionAnalysis tLandPollutionAnalysis)
    {
        return tLandPollutionAnalysisMapper.updateTLandPollutionAnalysis(tLandPollutionAnalysis);
    }

    /**
     * 批量删除地块污染情况分析
     * 
     * @param ids 需要删除的地块污染情况分析主键
     * @return 结果
     */
    @Override
    public int deleteTLandPollutionAnalysisByIds(Long[] ids)
    {
        return tLandPollutionAnalysisMapper.deleteTLandPollutionAnalysisByIds(ids);
    }

    /**
     * 删除地块污染情况分析信息
     * 
     * @param id 地块污染情况分析主键
     * @return 结果
     */
    @Override
    public int deleteTLandPollutionAnalysisById(Long id)
    {
        return tLandPollutionAnalysisMapper.deleteTLandPollutionAnalysisById(id);
    }
}
