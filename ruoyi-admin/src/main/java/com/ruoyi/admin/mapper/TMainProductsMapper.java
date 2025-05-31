package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TMainProducts;

/**
 * 主要产品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
public interface TMainProductsMapper 
{
    /**
     * 查询主要产品信息
     * 
     * @param id 主要产品信息主键
     * @return 主要产品信息
     */
    public TMainProducts selectTMainProductsById(Long id);

    /**
     * 查询主要产品信息列表
     * 
     * @param tMainProducts 主要产品信息
     * @return 主要产品信息集合
     */
    public List<TMainProducts> selectTMainProductsList(TMainProducts tMainProducts);

    /**
     * 新增主要产品信息
     * 
     * @param tMainProducts 主要产品信息
     * @return 结果
     */
    public int insertTMainProducts(TMainProducts tMainProducts);

    /**
     * 修改主要产品信息
     * 
     * @param tMainProducts 主要产品信息
     * @return 结果
     */
    public int updateTMainProducts(TMainProducts tMainProducts);

    /**
     * 删除主要产品信息
     * 
     * @param id 主要产品信息主键
     * @return 结果
     */
    public int deleteTMainProductsById(Long id);

    /**
     * 批量删除主要产品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTMainProductsByIds(Long[] ids);
}
