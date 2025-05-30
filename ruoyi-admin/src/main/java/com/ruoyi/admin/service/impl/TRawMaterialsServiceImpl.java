package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TRawMaterialsMapper;
import com.ruoyi.admin.domain.TRawMaterials;
import com.ruoyi.admin.service.ITRawMaterialsService;

/**
 * 原辅材料信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
@Service
public class TRawMaterialsServiceImpl implements ITRawMaterialsService 
{
    @Autowired
    private TRawMaterialsMapper tRawMaterialsMapper;

    /**
     * 查询原辅材料信息
     * 
     * @param id 原辅材料信息主键
     * @return 原辅材料信息
     */
    @Override
    public TRawMaterials selectTRawMaterialsById(Long id)
    {
        return tRawMaterialsMapper.selectTRawMaterialsById(id);
    }

    /**
     * 查询原辅材料信息列表
     * 
     * @param tRawMaterials 原辅材料信息
     * @return 原辅材料信息
     */
    @Override
    public List<TRawMaterials> selectTRawMaterialsList(TRawMaterials tRawMaterials)
    {
        return tRawMaterialsMapper.selectTRawMaterialsList(tRawMaterials);
    }

    /**
     * 新增原辅材料信息
     * 
     * @param tRawMaterials 原辅材料信息
     * @return 结果
     */
    @Override
    public int insertTRawMaterials(TRawMaterials tRawMaterials)
    {
        return tRawMaterialsMapper.insertTRawMaterials(tRawMaterials);
    }

    /**
     * 修改原辅材料信息
     * 
     * @param tRawMaterials 原辅材料信息
     * @return 结果
     */
    @Override
    public int updateTRawMaterials(TRawMaterials tRawMaterials)
    {
        return tRawMaterialsMapper.updateTRawMaterials(tRawMaterials);
    }

    /**
     * 批量删除原辅材料信息
     * 
     * @param ids 需要删除的原辅材料信息主键
     * @return 结果
     */
    @Override
    public int deleteTRawMaterialsByIds(Long[] ids)
    {
        return tRawMaterialsMapper.deleteTRawMaterialsByIds(ids);
    }

    /**
     * 删除原辅材料信息信息
     * 
     * @param id 原辅材料信息主键
     * @return 结果
     */
    @Override
    public int deleteTRawMaterialsById(Long id)
    {
        return tRawMaterialsMapper.deleteTRawMaterialsById(id);
    }
}
