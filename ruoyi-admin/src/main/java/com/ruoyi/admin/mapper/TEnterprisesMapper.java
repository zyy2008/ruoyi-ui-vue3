package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TEnterprises;

/**
 * 企业管理Mapper接口
 * 
 * @author wy
 * @date 2025-05-31
 */
public interface TEnterprisesMapper 
{
    /**
     * 查询企业管理
     * 
     * @param enterpriseName 企业管理主键
     * @return 企业管理
     */
    public TEnterprises selectTEnterprisesByEnterpriseName(String enterpriseName);

    /**
     * 查询企业管理列表
     * 
     * @param tEnterprises 企业管理
     * @return 企业管理集合
     */
    public List<TEnterprises> selectTEnterprisesList(TEnterprises tEnterprises);

    /**
     * 新增企业管理
     * 
     * @param tEnterprises 企业管理
     * @return 结果
     */
    public int insertTEnterprises(TEnterprises tEnterprises);

    /**
     * 修改企业管理
     * 
     * @param tEnterprises 企业管理
     * @return 结果
     */
    public int updateTEnterprises(TEnterprises tEnterprises);

    /**
     * 删除企业管理
     * 
     * @param enterpriseName 企业管理主键
     * @return 结果
     */
    public int deleteTEnterprisesByEnterpriseName(String enterpriseName);

    /**
     * 批量删除企业管理
     * 
     * @param enterpriseNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTEnterprisesByEnterpriseNames(String[] enterpriseNames);
}
