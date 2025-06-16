package com.ruoyi.admin.service.impl;

import java.util.List;

import com.ruoyi.admin.domain.vo.TDcIndustryTree;
import com.ruoyi.util.IndustryTreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TDcIndustryMapper;
import com.ruoyi.admin.domain.TDcIndustry;
import com.ruoyi.admin.service.ITDcIndustryService;

/**
 * 行业分类Service业务层处理
 *
 * @author ruoyi
 * @date 2025-06-03
 */
@Service
public class TDcIndustryServiceImpl implements ITDcIndustryService {
    @Autowired
    private TDcIndustryMapper tDcIndustryMapper;

    /**
     * 查询行业分类
     *
     * @param id 行业分类主键
     * @return 行业分类
     */
    @Override
    public TDcIndustry selectTDcIndustryById(Long id) {
        return tDcIndustryMapper.selectTDcIndustryById(id);
    }

    /**
     * 查询行业分类列表
     *
     * @param tDcIndustry 行业分类
     * @return 行业分类
     */
    @Override
    public List<TDcIndustry> selectTDcIndustryList(TDcIndustry tDcIndustry) {
        return tDcIndustryMapper.selectTDcIndustryList(tDcIndustry);
    }
    @Override
    public List<TDcIndustryTree> treeList(TDcIndustry tDcIndustry) {
        List<TDcIndustryTree> flatList = tDcIndustryMapper.treeList(tDcIndustry);
        return IndustryTreeBuilder.buildTree(flatList); // 构建树结构
    }


    /**
     * 新增行业分类
     *
     * @param tDcIndustry 行业分类
     * @return 结果
     */
    @Override
    public int insertTDcIndustry(TDcIndustry tDcIndustry) {
        return tDcIndustryMapper.insertTDcIndustry(tDcIndustry);
    }

    /**
     * 修改行业分类
     *
     * @param tDcIndustry 行业分类
     * @return 结果
     */
    @Override
    public int updateTDcIndustry(TDcIndustry tDcIndustry) {
        return tDcIndustryMapper.updateTDcIndustry(tDcIndustry);
    }

    /**
     * 批量删除行业分类
     *
     * @param ids 需要删除的行业分类主键
     * @return 结果
     */
    @Override
    public int deleteTDcIndustryByIds(Long[] ids) {
        return tDcIndustryMapper.deleteTDcIndustryByIds(ids);
    }

    /**
     * 删除行业分类信息
     *
     * @param id 行业分类主键
     * @return 结果
     */
    @Override
    public int deleteTDcIndustryById(Long id) {
        return tDcIndustryMapper.deleteTDcIndustryById(id);
    }
}
