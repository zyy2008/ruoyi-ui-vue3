package com.ruoyi.admin.service;

import java.util.List;

import com.ruoyi.admin.domain.TEnterprisePlots;

/**
 * 企业信息Service接口
 *
 * @author ruoyi
 * @date 2025-05-26
 */
public interface ITEnterprisePlotsService {
    /**
     * 查询企业信息
     *
     * @param id 企业信息主键
     * @return 企业信息
     */
    public TEnterprisePlots selectTEnterprisePlotsById(Long id);

    /**
     * 查询企业信息列表
     *
     * @param tEnterprisePlots 企业信息
     * @return 企业信息集合
     */
    public List<TEnterprisePlots> selectTEnterprisePlotsList(TEnterprisePlots tEnterprisePlots);

    /**
     * 新增企业信息
     *
     * @param tEnterprisePlots 企业信息
     * @return 结果
     */
    public int insertTEnterprisePlots(TEnterprisePlots tEnterprisePlots);

    /**
     * 修改企业信息
     *
     * @param tEnterprisePlots 企业信息
     * @return 结果
     */
    public int updateTEnterprisePlots(TEnterprisePlots tEnterprisePlots);

    /**
     * 批量删除企业信息
     *
     * @param ids 需要删除的企业信息主键集合
     * @return 结果
     */
    public int deleteTEnterprisePlotsByIds(Long[] ids);

    /**
     * 删除企业信息信息
     *
     * @param id 企业信息主键
     * @return 结果
     */
    public int deleteTEnterprisePlotsById(Long id);
}
