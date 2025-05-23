package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TElementClassificationNorm;

/**
 * 要素分类编码规范Service接口
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
public interface ITElementClassificationNormService 
{
    /**
     * 查询要素分类编码规范
     * 
     * @param id 要素分类编码规范主键
     * @return 要素分类编码规范
     */
    public TElementClassificationNorm selectTElementClassificationNormById(Long id);

    /**
     * 查询要素分类编码规范列表
     * 
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 要素分类编码规范集合
     */
    public List<TElementClassificationNorm> selectTElementClassificationNormList(TElementClassificationNorm tElementClassificationNorm);

    /**
     * 新增要素分类编码规范
     * 
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 结果
     */
    public int insertTElementClassificationNorm(TElementClassificationNorm tElementClassificationNorm);

    /**
     * 修改要素分类编码规范
     * 
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 结果
     */
    public int updateTElementClassificationNorm(TElementClassificationNorm tElementClassificationNorm);

    /**
     * 批量删除要素分类编码规范
     * 
     * @param ids 需要删除的要素分类编码规范主键集合
     * @return 结果
     */
    public int deleteTElementClassificationNormByIds(Long[] ids);

    /**
     * 删除要素分类编码规范信息
     * 
     * @param id 要素分类编码规范主键
     * @return 结果
     */
    public int deleteTElementClassificationNormById(Long id);
}
