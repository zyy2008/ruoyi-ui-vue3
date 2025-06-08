package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TStorageArea;

/**
 * 储存区Service接口
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
public interface ITStorageAreaService 
{
    /**
     * 查询储存区
     * 
     * @param id 储存区主键
     * @return 储存区
     */
    public TStorageArea selectTStorageAreaById(Long id);

    /**
     * 查询储存区列表
     * 
     * @param tStorageArea 储存区
     * @return 储存区集合
     */
    public List<TStorageArea> selectTStorageAreaList(TStorageArea tStorageArea);

    /**
     * 新增储存区
     * 
     * @param tStorageArea 储存区
     * @return 结果
     */
    public int insertTStorageArea(TStorageArea tStorageArea);

    /**
     * 修改储存区
     * 
     * @param tStorageArea 储存区
     * @return 结果
     */
    public int updateTStorageArea(TStorageArea tStorageArea);

    /**
     * 批量删除储存区
     * 
     * @param ids 需要删除的储存区主键集合
     * @return 结果
     */
    public int deleteTStorageAreaByIds(Long[] ids);

    /**
     * 删除储存区信息
     * 
     * @param id 储存区主键
     * @return 结果
     */
    public int deleteTStorageAreaById(Long id);
}
