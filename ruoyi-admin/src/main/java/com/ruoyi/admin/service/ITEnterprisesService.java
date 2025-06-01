package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TEnterprises;

/**
 * 企业管理Service接口
 *
 * @author wy
 * @date 2025-06-01
 */
public interface ITEnterprisesService
{
    /**
     * 查询企业管理
     *
     * @param id 企业管理主键
     * @return 企业管理
     */
    public TEnterprises selectTEnterprisesById(String id);

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
     * 批量删除企业管理
     *
     * @param ids 需要删除的企业管理主键集合
     * @return 结果
     */
    public int deleteTEnterprisesByIds(String[] ids);

    /**
     * 删除企业管理信息
     *
     * @param id 企业管理主键
     * @return 结果
     */
    public int deleteTEnterprisesById(String id);
}
