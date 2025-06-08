package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSewageSolidWasteMapper;
import com.ruoyi.admin.domain.TSewageSolidWaste;
import com.ruoyi.admin.service.ITSewageSolidWasteService;

/**
 * 污水处理厂固体废物产生量及污染防治措施Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
@Service
public class TSewageSolidWasteServiceImpl implements ITSewageSolidWasteService 
{
    @Autowired
    private TSewageSolidWasteMapper tSewageSolidWasteMapper;

    /**
     * 查询污水处理厂固体废物产生量及污染防治措施
     * 
     * @param id 污水处理厂固体废物产生量及污染防治措施主键
     * @return 污水处理厂固体废物产生量及污染防治措施
     */
    @Override
    public TSewageSolidWaste selectTSewageSolidWasteById(Long id)
    {
        return tSewageSolidWasteMapper.selectTSewageSolidWasteById(id);
    }

    /**
     * 查询污水处理厂固体废物产生量及污染防治措施列表
     * 
     * @param tSewageSolidWaste 污水处理厂固体废物产生量及污染防治措施
     * @return 污水处理厂固体废物产生量及污染防治措施
     */
    @Override
    public List<TSewageSolidWaste> selectTSewageSolidWasteList(TSewageSolidWaste tSewageSolidWaste)
    {
        return tSewageSolidWasteMapper.selectTSewageSolidWasteList(tSewageSolidWaste);
    }

    /**
     * 新增污水处理厂固体废物产生量及污染防治措施
     * 
     * @param tSewageSolidWaste 污水处理厂固体废物产生量及污染防治措施
     * @return 结果
     */
    @Override
    public int insertTSewageSolidWaste(TSewageSolidWaste tSewageSolidWaste)
    {
        return tSewageSolidWasteMapper.insertTSewageSolidWaste(tSewageSolidWaste);
    }

    /**
     * 修改污水处理厂固体废物产生量及污染防治措施
     * 
     * @param tSewageSolidWaste 污水处理厂固体废物产生量及污染防治措施
     * @return 结果
     */
    @Override
    public int updateTSewageSolidWaste(TSewageSolidWaste tSewageSolidWaste)
    {
        return tSewageSolidWasteMapper.updateTSewageSolidWaste(tSewageSolidWaste);
    }

    /**
     * 批量删除污水处理厂固体废物产生量及污染防治措施
     * 
     * @param ids 需要删除的污水处理厂固体废物产生量及污染防治措施主键
     * @return 结果
     */
    @Override
    public int deleteTSewageSolidWasteByIds(Long[] ids)
    {
        return tSewageSolidWasteMapper.deleteTSewageSolidWasteByIds(ids);
    }

    /**
     * 删除污水处理厂固体废物产生量及污染防治措施信息
     * 
     * @param id 污水处理厂固体废物产生量及污染防治措施主键
     * @return 结果
     */
    @Override
    public int deleteTSewageSolidWasteById(Long id)
    {
        return tSewageSolidWasteMapper.deleteTSewageSolidWasteById(id);
    }
}
