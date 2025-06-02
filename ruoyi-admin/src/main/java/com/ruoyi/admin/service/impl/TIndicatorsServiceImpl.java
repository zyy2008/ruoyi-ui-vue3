package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TIndicatorsMapper;
import com.ruoyi.admin.domain.TIndicators;
import com.ruoyi.admin.service.ITIndicatorsService;

/**
 * 指标分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-02
 */
@Service
public class TIndicatorsServiceImpl implements ITIndicatorsService 
{
    @Autowired
    private TIndicatorsMapper tIndicatorsMapper;

    /**
     * 查询指标分类
     * 
     * @param id 指标分类主键
     * @return 指标分类
     */
    @Override
    public TIndicators selectTIndicatorsById(Long id)
    {
        return tIndicatorsMapper.selectTIndicatorsById(id);
    }

    /**
     * 查询指标分类列表
     * 
     * @param tIndicators 指标分类
     * @return 指标分类
     */
    @Override
    public List<TIndicators> selectTIndicatorsList(TIndicators tIndicators)
    {
        return tIndicatorsMapper.selectTIndicatorsList(tIndicators);
    }

    /**
     * 新增指标分类
     * 
     * @param tIndicators 指标分类
     * @return 结果
     */
    @Override
    public int insertTIndicators(TIndicators tIndicators)
    {
        return tIndicatorsMapper.insertTIndicators(tIndicators);
    }

    /**
     * 修改指标分类
     * 
     * @param tIndicators 指标分类
     * @return 结果
     */
    @Override
    public int updateTIndicators(TIndicators tIndicators)
    {
        return tIndicatorsMapper.updateTIndicators(tIndicators);
    }

    /**
     * 批量删除指标分类
     * 
     * @param ids 需要删除的指标分类主键
     * @return 结果
     */
    @Override
    public int deleteTIndicatorsByIds(Long[] ids)
    {
        return tIndicatorsMapper.deleteTIndicatorsByIds(ids);
    }

    /**
     * 删除指标分类信息
     * 
     * @param id 指标分类主键
     * @return 结果
     */
    @Override
    public int deleteTIndicatorsById(Long id)
    {
        return tIndicatorsMapper.deleteTIndicatorsById(id);
    }
}
