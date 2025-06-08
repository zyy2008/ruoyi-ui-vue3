package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TPollutionIncidentMapper;
import com.ruoyi.admin.domain.TPollutionIncident;
import com.ruoyi.admin.service.ITPollutionIncidentService;

/**
 * 环境污染事故发生情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
@Service
public class TPollutionIncidentServiceImpl implements ITPollutionIncidentService 
{
    @Autowired
    private TPollutionIncidentMapper tPollutionIncidentMapper;

    /**
     * 查询环境污染事故发生情况
     * 
     * @param id 环境污染事故发生情况主键
     * @return 环境污染事故发生情况
     */
    @Override
    public TPollutionIncident selectTPollutionIncidentById(Long id)
    {
        return tPollutionIncidentMapper.selectTPollutionIncidentById(id);
    }

    /**
     * 查询环境污染事故发生情况列表
     * 
     * @param tPollutionIncident 环境污染事故发生情况
     * @return 环境污染事故发生情况
     */
    @Override
    public List<TPollutionIncident> selectTPollutionIncidentList(TPollutionIncident tPollutionIncident)
    {
        return tPollutionIncidentMapper.selectTPollutionIncidentList(tPollutionIncident);
    }

    /**
     * 新增环境污染事故发生情况
     * 
     * @param tPollutionIncident 环境污染事故发生情况
     * @return 结果
     */
    @Override
    public int insertTPollutionIncident(TPollutionIncident tPollutionIncident)
    {
        return tPollutionIncidentMapper.insertTPollutionIncident(tPollutionIncident);
    }

    /**
     * 修改环境污染事故发生情况
     * 
     * @param tPollutionIncident 环境污染事故发生情况
     * @return 结果
     */
    @Override
    public int updateTPollutionIncident(TPollutionIncident tPollutionIncident)
    {
        return tPollutionIncidentMapper.updateTPollutionIncident(tPollutionIncident);
    }

    /**
     * 批量删除环境污染事故发生情况
     * 
     * @param ids 需要删除的环境污染事故发生情况主键
     * @return 结果
     */
    @Override
    public int deleteTPollutionIncidentByIds(Long[] ids)
    {
        return tPollutionIncidentMapper.deleteTPollutionIncidentByIds(ids);
    }

    /**
     * 删除环境污染事故发生情况信息
     * 
     * @param id 环境污染事故发生情况主键
     * @return 结果
     */
    @Override
    public int deleteTPollutionIncidentById(Long id)
    {
        return tPollutionIncidentMapper.deleteTPollutionIncidentById(id);
    }
}
