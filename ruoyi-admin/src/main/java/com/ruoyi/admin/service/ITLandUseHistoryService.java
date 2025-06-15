package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TLandUseHistory;

/**
 * 地块历史用途信息Service接口
 * 
 * @author ruoyi
 * @date 2025-06-11
 */
public interface ITLandUseHistoryService 
{
    /**
     * 查询地块历史用途信息
     * 
     * @param id 地块历史用途信息主键
     * @return 地块历史用途信息
     */
    public TLandUseHistory selectTLandUseHistoryById(Long id);

    /**
     * 查询地块历史用途信息列表
     * 
     * @param tLandUseHistory 地块历史用途信息
     * @return 地块历史用途信息集合
     */
    public List<TLandUseHistory> selectTLandUseHistoryList(TLandUseHistory tLandUseHistory);

    /**
     * 新增地块历史用途信息
     * 
     * @param tLandUseHistory 地块历史用途信息
     * @return 结果
     */
    public int insertTLandUseHistory(TLandUseHistory tLandUseHistory);

    /**
     * 修改地块历史用途信息
     * 
     * @param tLandUseHistory 地块历史用途信息
     * @return 结果
     */
    public int updateTLandUseHistory(TLandUseHistory tLandUseHistory);

    /**
     * 批量删除地块历史用途信息
     * 
     * @param ids 需要删除的地块历史用途信息主键集合
     * @return 结果
     */
    public int deleteTLandUseHistoryByIds(Long[] ids);

    /**
     * 删除地块历史用途信息信息
     * 
     * @param id 地块历史用途信息主键
     * @return 结果
     */
    public int deleteTLandUseHistoryById(Long id);
}
