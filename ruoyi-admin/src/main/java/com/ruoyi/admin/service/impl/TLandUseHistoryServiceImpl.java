package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TLandUseHistoryMapper;
import com.ruoyi.admin.domain.TLandUseHistory;
import com.ruoyi.admin.service.ITLandUseHistoryService;

/**
 * 地块历史用途信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-11
 */
@Service
public class TLandUseHistoryServiceImpl implements ITLandUseHistoryService 
{
    @Autowired
    private TLandUseHistoryMapper tLandUseHistoryMapper;

    /**
     * 查询地块历史用途信息
     * 
     * @param id 地块历史用途信息主键
     * @return 地块历史用途信息
     */
    @Override
    public TLandUseHistory selectTLandUseHistoryById(Long id)
    {
        return tLandUseHistoryMapper.selectTLandUseHistoryById(id);
    }

    /**
     * 查询地块历史用途信息列表
     * 
     * @param tLandUseHistory 地块历史用途信息
     * @return 地块历史用途信息
     */
    @Override
    public List<TLandUseHistory> selectTLandUseHistoryList(TLandUseHistory tLandUseHistory)
    {
        return tLandUseHistoryMapper.selectTLandUseHistoryList(tLandUseHistory);
    }

    /**
     * 新增地块历史用途信息
     * 
     * @param tLandUseHistory 地块历史用途信息
     * @return 结果
     */
    @Override
    public int insertTLandUseHistory(TLandUseHistory tLandUseHistory)
    {
        return tLandUseHistoryMapper.insertTLandUseHistory(tLandUseHistory);
    }

    /**
     * 修改地块历史用途信息
     * 
     * @param tLandUseHistory 地块历史用途信息
     * @return 结果
     */
    @Override
    public int updateTLandUseHistory(TLandUseHistory tLandUseHistory)
    {
        return tLandUseHistoryMapper.updateTLandUseHistory(tLandUseHistory);
    }

    /**
     * 批量删除地块历史用途信息
     * 
     * @param ids 需要删除的地块历史用途信息主键
     * @return 结果
     */
    @Override
    public int deleteTLandUseHistoryByIds(Long[] ids)
    {
        return tLandUseHistoryMapper.deleteTLandUseHistoryByIds(ids);
    }

    /**
     * 删除地块历史用途信息信息
     * 
     * @param id 地块历史用途信息主键
     * @return 结果
     */
    @Override
    public int deleteTLandUseHistoryById(Long id)
    {
        return tLandUseHistoryMapper.deleteTLandUseHistoryById(id);
    }
}
