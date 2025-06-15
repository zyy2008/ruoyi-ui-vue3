package com.ruoyi.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TStorageAreaMapper;
import com.ruoyi.admin.domain.TStorageArea;
import com.ruoyi.admin.service.ITStorageAreaService;

/**
 * 储存区Service业务层处理
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Service
public class TStorageAreaServiceImpl implements ITStorageAreaService {
    @Autowired
    private TStorageAreaMapper tStorageAreaMapper;

    /**
     * 查询储存区
     *
     * @param id 储存区主键
     * @return 储存区
     */
    @Override
    public TStorageArea selectTStorageAreaById(Long id) {
        return tStorageAreaMapper.selectTStorageAreaById(id);
    }

    /**
     * 查询储存区列表
     *
     * @param tStorageArea 储存区
     * @return 储存区
     */
    @Override
    public List<TStorageArea> selectTStorageAreaList(TStorageArea tStorageArea) {
        return tStorageAreaMapper.selectTStorageAreaList(tStorageArea);
    }

    /**
     * 新增储存区
     *
     * @param tStorageArea 储存区
     * @return 结果
     */
    @Override
    public int insertTStorageArea(TStorageArea tStorageArea) {
        return tStorageAreaMapper.insertTStorageArea(tStorageArea);
    }

    /**
     * 修改储存区
     *
     * @param tStorageArea 储存区
     * @return 结果
     */
    @Override
    public int updateTStorageArea(TStorageArea tStorageArea) {
        return tStorageAreaMapper.updateTStorageArea(tStorageArea);
    }

    /**
     * 批量删除储存区
     *
     * @param ids 需要删除的储存区主键
     * @return 结果
     */
    @Override
    public int deleteTStorageAreaByIds(Long[] ids) {
        return tStorageAreaMapper.deleteTStorageAreaByIds(ids);
    }

    /**
     * 删除储存区信息
     *
     * @param id 储存区主键
     * @return 结果
     */
    @Override
    public int deleteTStorageAreaById(Long id) {
        return tStorageAreaMapper.deleteTStorageAreaById(id);
    }
}
