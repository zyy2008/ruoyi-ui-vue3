package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TLandPollutionAnalysis;

/**
 * 地块污染情况分析Service接口
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
public interface ITLandPollutionAnalysisService 
{
    /**
     * 查询地块污染情况分析
     * 
     * @param id 地块污染情况分析主键
     * @return 地块污染情况分析
     */
    public TLandPollutionAnalysis selectTLandPollutionAnalysisById(Long id);

    /**
     * 查询地块污染情况分析列表
     * 
     * @param tLandPollutionAnalysis 地块污染情况分析
     * @return 地块污染情况分析集合
     */
    public List<TLandPollutionAnalysis> selectTLandPollutionAnalysisList(TLandPollutionAnalysis tLandPollutionAnalysis);

    /**
     * 新增地块污染情况分析
     * 
     * @param tLandPollutionAnalysis 地块污染情况分析
     * @return 结果
     */
    public int insertTLandPollutionAnalysis(TLandPollutionAnalysis tLandPollutionAnalysis);

    /**
     * 修改地块污染情况分析
     * 
     * @param tLandPollutionAnalysis 地块污染情况分析
     * @return 结果
     */
    public int updateTLandPollutionAnalysis(TLandPollutionAnalysis tLandPollutionAnalysis);

    /**
     * 批量删除地块污染情况分析
     * 
     * @param ids 需要删除的地块污染情况分析主键集合
     * @return 结果
     */
    public int deleteTLandPollutionAnalysisByIds(Long[] ids);

    /**
     * 删除地块污染情况分析信息
     * 
     * @param id 地块污染情况分析主键
     * @return 结果
     */
    public int deleteTLandPollutionAnalysisById(Long id);
}
