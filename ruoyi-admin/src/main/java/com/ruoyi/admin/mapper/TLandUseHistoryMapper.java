package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TLandUseHistory;

/**
 * 地块历史用途信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-11
 */
public interface TLandUseHistoryMapper 
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
     * 删除地块历史用途信息
     * 
     * @param id 地块历史用途信息主键
     * @return 结果
     */
    public int deleteTLandUseHistoryById(Long id);

    /**
     * 批量删除地块历史用途信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTLandUseHistoryByIds(Long[] ids);
}
