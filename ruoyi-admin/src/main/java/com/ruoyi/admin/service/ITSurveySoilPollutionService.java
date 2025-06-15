package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TSurveySoilPollution;

/**
 * 调查评估土壤污染区信息Service接口
 * 
 * @author ruoyi
 * @date 2025-06-10
 */
public interface ITSurveySoilPollutionService 
{
    /**
     * 查询调查评估土壤污染区信息
     * 
     * @param id 调查评估土壤污染区信息主键
     * @return 调查评估土壤污染区信息
     */
    public TSurveySoilPollution selectTSurveySoilPollutionById(Long id);

    /**
     * 查询调查评估土壤污染区信息列表
     * 
     * @param tSurveySoilPollution 调查评估土壤污染区信息
     * @return 调查评估土壤污染区信息集合
     */
    public List<TSurveySoilPollution> selectTSurveySoilPollutionList(TSurveySoilPollution tSurveySoilPollution);

    /**
     * 新增调查评估土壤污染区信息
     * 
     * @param tSurveySoilPollution 调查评估土壤污染区信息
     * @return 结果
     */
    public int insertTSurveySoilPollution(TSurveySoilPollution tSurveySoilPollution);

    /**
     * 修改调查评估土壤污染区信息
     * 
     * @param tSurveySoilPollution 调查评估土壤污染区信息
     * @return 结果
     */
    public int updateTSurveySoilPollution(TSurveySoilPollution tSurveySoilPollution);

    /**
     * 批量删除调查评估土壤污染区信息
     * 
     * @param ids 需要删除的调查评估土壤污染区信息主键集合
     * @return 结果
     */
    public int deleteTSurveySoilPollutionByIds(Long[] ids);

    /**
     * 删除调查评估土壤污染区信息信息
     * 
     * @param id 调查评估土壤污染区信息主键
     * @return 结果
     */
    public int deleteTSurveySoilPollutionById(Long id);
}
