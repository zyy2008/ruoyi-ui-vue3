package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TPollutionIncident;

/**
 * 环境污染事故发生情况Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
public interface TPollutionIncidentMapper 
{
    /**
     * 查询环境污染事故发生情况
     * 
     * @param id 环境污染事故发生情况主键
     * @return 环境污染事故发生情况
     */
    public TPollutionIncident selectTPollutionIncidentById(Long id);

    /**
     * 查询环境污染事故发生情况列表
     * 
     * @param tPollutionIncident 环境污染事故发生情况
     * @return 环境污染事故发生情况集合
     */
    public List<TPollutionIncident> selectTPollutionIncidentList(TPollutionIncident tPollutionIncident);

    /**
     * 新增环境污染事故发生情况
     * 
     * @param tPollutionIncident 环境污染事故发生情况
     * @return 结果
     */
    public int insertTPollutionIncident(TPollutionIncident tPollutionIncident);

    /**
     * 修改环境污染事故发生情况
     * 
     * @param tPollutionIncident 环境污染事故发生情况
     * @return 结果
     */
    public int updateTPollutionIncident(TPollutionIncident tPollutionIncident);

    /**
     * 删除环境污染事故发生情况
     * 
     * @param id 环境污染事故发生情况主键
     * @return 结果
     */
    public int deleteTPollutionIncidentById(Long id);

    /**
     * 批量删除环境污染事故发生情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPollutionIncidentByIds(Long[] ids);
}
