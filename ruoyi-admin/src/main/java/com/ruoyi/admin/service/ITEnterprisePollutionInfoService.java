package com.ruoyi.admin.service;

import java.util.List;

import com.ruoyi.admin.domain.TEnterprisePollutionInfo;

/**
 * 企业污染源Service接口
 *
 * @author ruoyi
 * @date 2025-05-26
 */
public interface ITEnterprisePollutionInfoService {
    /**
     * 查询企业污染源
     *
     * @param id 企业污染源主键
     * @return 企业污染源
     */
    public TEnterprisePollutionInfo selectTEnterprisePollutionInfoById(Long id);

    /**
     * 查询企业污染源列表
     *
     * @param tEnterprisePollutionInfo 企业污染源
     * @return 企业污染源集合
     */
    public List<TEnterprisePollutionInfo> selectTEnterprisePollutionInfoList(TEnterprisePollutionInfo tEnterprisePollutionInfo);

    /**
     * 新增企业污染源
     *
     * @param tEnterprisePollutionInfo 企业污染源
     * @return 结果
     */
    public int insertTEnterprisePollutionInfo(TEnterprisePollutionInfo tEnterprisePollutionInfo);

    /**
     * 修改企业污染源
     *
     * @param tEnterprisePollutionInfo 企业污染源
     * @return 结果
     */
    public int updateTEnterprisePollutionInfo(TEnterprisePollutionInfo tEnterprisePollutionInfo);

    /**
     * 批量删除企业污染源
     *
     * @param ids 需要删除的企业污染源主键集合
     * @return 结果
     */
    public int deleteTEnterprisePollutionInfoByIds(Long[] ids);

    /**
     * 删除企业污染源信息
     *
     * @param id 企业污染源主键
     * @return 结果
     */
    public int deleteTEnterprisePollutionInfoById(Long id);
}
