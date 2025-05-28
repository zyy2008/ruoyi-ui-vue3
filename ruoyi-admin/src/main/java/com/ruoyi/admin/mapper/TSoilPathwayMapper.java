package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TSoilPathway;

/**
 * 土壤途径：含水层以上土壤分层信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TSoilPathwayMapper 
{
    /**
     * 查询土壤途径：含水层以上土壤分层信息
     * 
     * @param id 土壤途径：含水层以上土壤分层信息主键
     * @return 土壤途径：含水层以上土壤分层信息
     */
    public TSoilPathway selectTSoilPathwayById(Long id);

    /**
     * 查询土壤途径：含水层以上土壤分层信息列表
     * 
     * @param tSoilPathway 土壤途径：含水层以上土壤分层信息
     * @return 土壤途径：含水层以上土壤分层信息集合
     */
    public List<TSoilPathway> selectTSoilPathwayList(TSoilPathway tSoilPathway);

    /**
     * 新增土壤途径：含水层以上土壤分层信息
     * 
     * @param tSoilPathway 土壤途径：含水层以上土壤分层信息
     * @return 结果
     */
    public int insertTSoilPathway(TSoilPathway tSoilPathway);

    /**
     * 修改土壤途径：含水层以上土壤分层信息
     * 
     * @param tSoilPathway 土壤途径：含水层以上土壤分层信息
     * @return 结果
     */
    public int updateTSoilPathway(TSoilPathway tSoilPathway);

    /**
     * 删除土壤途径：含水层以上土壤分层信息
     * 
     * @param id 土壤途径：含水层以上土壤分层信息主键
     * @return 结果
     */
    public int deleteTSoilPathwayById(Long id);

    /**
     * 批量删除土壤途径：含水层以上土壤分层信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSoilPathwayByIds(Long[] ids);
}
