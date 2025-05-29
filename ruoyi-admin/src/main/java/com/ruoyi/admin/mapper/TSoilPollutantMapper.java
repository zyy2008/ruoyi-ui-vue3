package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TSoilPollutant;

/**
 * 土壤监测发现的超标污染物信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TSoilPollutantMapper 
{
    /**
     * 查询土壤监测发现的超标污染物信息
     * 
     * @param id 土壤监测发现的超标污染物信息主键
     * @return 土壤监测发现的超标污染物信息
     */
    public TSoilPollutant selectTSoilPollutantById(Long id);

    /**
     * 查询土壤监测发现的超标污染物信息列表
     * 
     * @param tSoilPollutant 土壤监测发现的超标污染物信息
     * @return 土壤监测发现的超标污染物信息集合
     */
    public List<TSoilPollutant> selectTSoilPollutantList(TSoilPollutant tSoilPollutant);

    /**
     * 新增土壤监测发现的超标污染物信息
     * 
     * @param tSoilPollutant 土壤监测发现的超标污染物信息
     * @return 结果
     */
    public int insertTSoilPollutant(TSoilPollutant tSoilPollutant);

    /**
     * 修改土壤监测发现的超标污染物信息
     * 
     * @param tSoilPollutant 土壤监测发现的超标污染物信息
     * @return 结果
     */
    public int updateTSoilPollutant(TSoilPollutant tSoilPollutant);

    /**
     * 删除土壤监测发现的超标污染物信息
     * 
     * @param id 土壤监测发现的超标污染物信息主键
     * @return 结果
     */
    public int deleteTSoilPollutantById(Long id);

    /**
     * 批量删除土壤监测发现的超标污染物信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSoilPollutantByIds(Long[] ids);
}
