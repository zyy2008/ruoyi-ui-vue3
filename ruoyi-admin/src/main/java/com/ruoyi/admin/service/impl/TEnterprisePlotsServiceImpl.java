package com.ruoyi.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TEnterprisePlotsMapper;
import com.ruoyi.admin.domain.TEnterprisePlots;
import com.ruoyi.admin.service.ITEnterprisePlotsService;

/**
 * 企业信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-26
 */
@Service
public class TEnterprisePlotsServiceImpl implements ITEnterprisePlotsService {
    @Autowired
    private TEnterprisePlotsMapper tEnterprisePlotsMapper;

    /**
     * 查询企业信息
     *
     * @param id 企业信息主键
     * @return 企业信息
     */
    @Override
    public TEnterprisePlots selectTEnterprisePlotsById(Long id) {
        return tEnterprisePlotsMapper.selectTEnterprisePlotsById(id);
    }

    /**
     * 查询企业信息列表
     *
     * @param tEnterprisePlots 企业信息
     * @return 企业信息
     */
    @Override
    public List<TEnterprisePlots> selectTEnterprisePlotsList(TEnterprisePlots tEnterprisePlots) {
        return tEnterprisePlotsMapper.selectTEnterprisePlotsList(tEnterprisePlots);
    }

    /**
     * 新增企业信息
     *
     * @param tEnterprisePlots 企业信息
     * @return 结果
     */
    @Override
    public int insertTEnterprisePlots(TEnterprisePlots tEnterprisePlots) {
        return tEnterprisePlotsMapper.insertTEnterprisePlots(tEnterprisePlots);
    }

    /**
     * 修改企业信息
     *
     * @param tEnterprisePlots 企业信息
     * @return 结果
     */
    @Override
    public int updateTEnterprisePlots(TEnterprisePlots tEnterprisePlots) {
        return tEnterprisePlotsMapper.updateTEnterprisePlots(tEnterprisePlots);
    }

    /**
     * 批量删除企业信息
     *
     * @param ids 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteTEnterprisePlotsByIds(Long[] ids) {
        return tEnterprisePlotsMapper.deleteTEnterprisePlotsByIds(ids);
    }

    /**
     * 删除企业信息信息
     *
     * @param id 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteTEnterprisePlotsById(Long id) {
        return tEnterprisePlotsMapper.deleteTEnterprisePlotsById(id);
    }
}
