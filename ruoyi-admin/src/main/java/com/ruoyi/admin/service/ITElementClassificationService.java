package com.ruoyi.admin.service;

import com.ruoyi.admin.domain.TElementClassification;
import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.vo.TElementClassificationVo;

import java.util.List;

/**
 * 要素分类Service接口
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
public interface ITElementClassificationService 
{
    /**
     * 查询要素分类
     * 
     * @param id 要素分类主键
     * @return 要素分类
     */
    public TElementClassification selectTElementClassificationById(Long id);
    public List<TElementClassification> selectTElementClassificationByIds(Long[] id);

    /**
     * 查询要素分类列表
     * 
     * @param tElementClassification 要素分类
     * @return 要素分类集合
     */
    public List<TElementClassification> selectTElementClassificationList(TElementClassification tElementClassification);

    /**
     * 查询要素大类规范
     * @return
     */
    public List<TElementClassificationVo> selectTElementClassificationList(Long baseId);
    public List<TElementClassificationVo> selectTElementClassificationList(Long baseId,List<TLayer> layerList);
    public List<TElementClassificationVo> selectTElementClassificationList(Long[] baseIds,List<TLayer> layerList);

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
     * 批量删除要素分类
     * 
     * @param ids 需要删除的要素分类主键集合
     * @return 结果
     */
    public int deleteTElementClassificationByIds(Long[] ids);

    /**
     * 删除要素分类信息
     * 
     * @param id 要素分类主键
     * @return 结果
     */
    public int deleteTElementClassificationById(Long id);
}
