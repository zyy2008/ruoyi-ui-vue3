package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TDcIndustry;

/**
 * 行业分类Service接口
 * 
 * @author ruoyi
 * @date 2025-06-03
 */
public interface ITDcIndustryService 
{
    /**
     * 查询行业分类
     * 
     * @param id 行业分类主键
     * @return 行业分类
     */
    public TDcIndustry selectTDcIndustryById(Long id);

    /**
     * 查询行业分类列表
     * 
     * @param tDcIndustry 行业分类
     * @return 行业分类集合
     */
    public List<TDcIndustry> selectTDcIndustryList(TDcIndustry tDcIndustry);

    /**
     * 新增行业分类
     * 
     * @param tDcIndustry 行业分类
     * @return 结果
     */
    public int insertTDcIndustry(TDcIndustry tDcIndustry);

    /**
     * 修改行业分类
     * 
     * @param tDcIndustry 行业分类
     * @return 结果
     */
    public int updateTDcIndustry(TDcIndustry tDcIndustry);

    /**
     * 批量删除行业分类
     * 
     * @param ids 需要删除的行业分类主键集合
     * @return 结果
     */
    public int deleteTDcIndustryByIds(Long[] ids);

    /**
     * 删除行业分类信息
     * 
     * @param id 行业分类主键
     * @return 结果
     */
    public int deleteTDcIndustryById(Long id);
}
