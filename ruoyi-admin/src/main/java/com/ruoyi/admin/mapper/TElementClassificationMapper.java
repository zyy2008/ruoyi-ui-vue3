package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TElementClassification;
import com.ruoyi.common.annotation.DataScope;

import java.util.List;

/**
 * 要素分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
public interface TElementClassificationMapper 
{
    /**
     * 查询要素分类
     * 
     * @param id 要素分类主键
     * @return 要素分类
     */
    @DataScope(deptAlias = "ec", userAlias = "ec")
    public TElementClassification selectTElementClassificationById(Long id);
    @DataScope(deptAlias = "ec", userAlias = "ec")
    public List<TElementClassification> selectTElementClassificationByIds(Long[] ids);

    /**
     * 查询要素分类列表
     * 
     * @param tElementClassification 要素分类
     * @return 要素分类集合
     */
    public List<TElementClassification> selectTElementClassificationList(TElementClassification tElementClassification);

    /**
     * 新增要素分类
     * 
     * @param tElementClassification 要素分类
     * @return 结果
     */
    public int insertTElementClassification(TElementClassification tElementClassification);

    /**
     * 修改要素分类
     * 
     * @param tElementClassification 要素分类
     * @return 结果
     */
    public int updateTElementClassification(TElementClassification tElementClassification);

    /**
     * 删除要素分类
     * 
     * @param id 要素分类主键
     * @return 结果
     */
    public int deleteTElementClassificationById(Long id);

    /**
     * 批量删除要素分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTElementClassificationByIds(Long[] ids);
}
