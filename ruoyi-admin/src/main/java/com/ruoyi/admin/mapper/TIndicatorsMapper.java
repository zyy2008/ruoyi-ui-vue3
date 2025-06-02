package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TIndicators;

/**
 * 指标分类Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-02
 */
public interface TIndicatorsMapper 
{
    /**
     * 查询指标分类
     * 
     * @param id 指标分类主键
     * @return 指标分类
     */
    public TIndicators selectTIndicatorsById(Long id);

    /**
     * 查询指标分类列表
     * 
     * @param tIndicators 指标分类
     * @return 指标分类集合
     */
    public List<TIndicators> selectTIndicatorsList(TIndicators tIndicators);

    /**
     * 新增指标分类
     * 
     * @param tIndicators 指标分类
     * @return 结果
     */
    public int insertTIndicators(TIndicators tIndicators);

    /**
     * 修改指标分类
     * 
     * @param tIndicators 指标分类
     * @return 结果
     */
    public int updateTIndicators(TIndicators tIndicators);

    /**
     * 删除指标分类
     * 
     * @param id 指标分类主键
     * @return 结果
     */
    public int deleteTIndicatorsById(Long id);

    /**
     * 批量删除指标分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTIndicatorsByIds(Long[] ids);
}
