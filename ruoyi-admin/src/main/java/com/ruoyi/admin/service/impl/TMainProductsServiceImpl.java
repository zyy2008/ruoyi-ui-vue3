package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TMainProductsMapper;
import com.ruoyi.admin.domain.TMainProducts;
import com.ruoyi.admin.service.ITMainProductsService;

/**
 * 主要产品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
@Service
public class TMainProductsServiceImpl implements ITMainProductsService 
{
    @Autowired
    private TMainProductsMapper tMainProductsMapper;

    /**
     * 查询主要产品信息
     * 
     * @param id 主要产品信息主键
     * @return 主要产品信息
     */
    @Override
    public TMainProducts selectTMainProductsById(Long id)
    {
        return tMainProductsMapper.selectTMainProductsById(id);
    }

    /**
     * 查询主要产品信息列表
     * 
     * @param tMainProducts 主要产品信息
     * @return 主要产品信息
     */
    @Override
    public List<TMainProducts> selectTMainProductsList(TMainProducts tMainProducts)
    {
        return tMainProductsMapper.selectTMainProductsList(tMainProducts);
    }

    /**
     * 新增主要产品信息
     * 
     * @param tMainProducts 主要产品信息
     * @return 结果
     */
    @Override
    public int insertTMainProducts(TMainProducts tMainProducts)
    {
        return tMainProductsMapper.insertTMainProducts(tMainProducts);
    }

    /**
     * 修改主要产品信息
     * 
     * @param tMainProducts 主要产品信息
     * @return 结果
     */
    @Override
    public int updateTMainProducts(TMainProducts tMainProducts)
    {
        return tMainProductsMapper.updateTMainProducts(tMainProducts);
    }

    /**
     * 批量删除主要产品信息
     * 
     * @param ids 需要删除的主要产品信息主键
     * @return 结果
     */
    @Override
    public int deleteTMainProductsByIds(Long[] ids)
    {
        return tMainProductsMapper.deleteTMainProductsByIds(ids);
    }

    /**
     * 删除主要产品信息信息
     * 
     * @param id 主要产品信息主键
     * @return 结果
     */
    @Override
    public int deleteTMainProductsById(Long id)
    {
        return tMainProductsMapper.deleteTMainProductsById(id);
    }
}
