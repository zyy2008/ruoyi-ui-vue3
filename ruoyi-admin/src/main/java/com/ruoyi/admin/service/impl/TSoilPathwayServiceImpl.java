package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSoilPathwayMapper;
import com.ruoyi.admin.domain.TSoilPathway;
import com.ruoyi.admin.service.ITSoilPathwayService;

/**
 * 土壤途径：含水层以上土壤分层信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TSoilPathwayServiceImpl implements ITSoilPathwayService 
{
    @Autowired
    private TSoilPathwayMapper tSoilPathwayMapper;

    /**
     * 查询土壤途径：含水层以上土壤分层信息
     * 
     * @param id 土壤途径：含水层以上土壤分层信息主键
     * @return 土壤途径：含水层以上土壤分层信息
     */
    @Override
    public TSoilPathway selectTSoilPathwayById(Long id)
    {
        return tSoilPathwayMapper.selectTSoilPathwayById(id);
    }

    /**
     * 查询土壤途径：含水层以上土壤分层信息列表
     * 
     * @param tSoilPathway 土壤途径：含水层以上土壤分层信息
     * @return 土壤途径：含水层以上土壤分层信息
     */
    @Override
    public List<TSoilPathway> selectTSoilPathwayList(TSoilPathway tSoilPathway)
    {
        return tSoilPathwayMapper.selectTSoilPathwayList(tSoilPathway);
    }

    /**
     * 新增土壤途径：含水层以上土壤分层信息
     * 
     * @param tSoilPathway 土壤途径：含水层以上土壤分层信息
     * @return 结果
     */
    @Override
    public int insertTSoilPathway(TSoilPathway tSoilPathway)
    {
        return tSoilPathwayMapper.insertTSoilPathway(tSoilPathway);
    }

    /**
     * 修改土壤途径：含水层以上土壤分层信息
     * 
     * @param tSoilPathway 土壤途径：含水层以上土壤分层信息
     * @return 结果
     */
    @Override
    public int updateTSoilPathway(TSoilPathway tSoilPathway)
    {
        return tSoilPathwayMapper.updateTSoilPathway(tSoilPathway);
    }

    /**
     * 批量删除土壤途径：含水层以上土壤分层信息
     * 
     * @param ids 需要删除的土壤途径：含水层以上土壤分层信息主键
     * @return 结果
     */
    @Override
    public int deleteTSoilPathwayByIds(Long[] ids)
    {
        return tSoilPathwayMapper.deleteTSoilPathwayByIds(ids);
    }

    /**
     * 删除土壤途径：含水层以上土壤分层信息信息
     * 
     * @param id 土壤途径：含水层以上土壤分层信息主键
     * @return 结果
     */
    @Override
    public int deleteTSoilPathwayById(Long id)
    {
        return tSoilPathwayMapper.deleteTSoilPathwayById(id);
    }
}
