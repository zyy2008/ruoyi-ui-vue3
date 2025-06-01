package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TEnterprisesMapper;
import com.ruoyi.admin.domain.TEnterprises;
import com.ruoyi.admin.service.ITEnterprisesService;

/**
 * 企业管理Service业务层处理
 *
 * @author wy
 * @date 2025-06-01
 */
@Service
public class TEnterprisesServiceImpl implements ITEnterprisesService
{
    @Autowired
    private TEnterprisesMapper tEnterprisesMapper;

    /**
     * 查询企业管理
     *
     * @param id 企业管理主键
     * @return 企业管理
     */
    @Override
    public TEnterprises selectTEnterprisesById(String id)
    {
        return tEnterprisesMapper.selectTEnterprisesById(id);
    }

    /**
     * 查询企业管理列表
     *
     * @param tEnterprises 企业管理
     * @return 企业管理
     */
    @Override
    public List<TEnterprises> selectTEnterprisesList(TEnterprises tEnterprises)
    {
        return tEnterprisesMapper.selectTEnterprisesList(tEnterprises);
    }

    /**
     * 新增企业管理
     *
     * @param tEnterprises 企业管理
     * @return 结果
     */
    @Override
    public int insertTEnterprises(TEnterprises tEnterprises)
    {
        return tEnterprisesMapper.insertTEnterprises(tEnterprises);
    }

    /**
     * 修改企业管理
     *
     * @param tEnterprises 企业管理
     * @return 结果
     */
    @Override
    public int updateTEnterprises(TEnterprises tEnterprises)
    {
        return tEnterprisesMapper.updateTEnterprises(tEnterprises);
    }

    /**
     * 批量删除企业管理
     *
     * @param ids 需要删除的企业管理主键
     * @return 结果
     */
    @Override
    public int deleteTEnterprisesByIds(String[] ids)
    {
        return tEnterprisesMapper.deleteTEnterprisesByIds(ids);
    }

    /**
     * 删除企业管理信息
     *
     * @param id 企业管理主键
     * @return 结果
     */
    @Override
    public int deleteTEnterprisesById(String id)
    {
        return tEnterprisesMapper.deleteTEnterprisesById(id);
    }
}
