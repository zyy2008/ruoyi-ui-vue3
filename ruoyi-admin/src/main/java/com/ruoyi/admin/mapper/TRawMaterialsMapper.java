package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TRawMaterials;

/**
 * 原辅材料信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
public interface TRawMaterialsMapper 
{
    /**
     * 查询原辅材料信息
     * 
     * @param id 原辅材料信息主键
     * @return 原辅材料信息
     */
    public TRawMaterials selectTRawMaterialsById(Long id);

    /**
     * 查询原辅材料信息列表
     * 
     * @param tRawMaterials 原辅材料信息
     * @return 原辅材料信息集合
     */
    public List<TRawMaterials> selectTRawMaterialsList(TRawMaterials tRawMaterials);

    /**
     * 新增原辅材料信息
     * 
     * @param tRawMaterials 原辅材料信息
     * @return 结果
     */
    public int insertTRawMaterials(TRawMaterials tRawMaterials);

    /**
     * 修改原辅材料信息
     * 
     * @param tRawMaterials 原辅材料信息
     * @return 结果
     */
    public int updateTRawMaterials(TRawMaterials tRawMaterials);

    /**
     * 删除原辅材料信息
     * 
     * @param id 原辅材料信息主键
     * @return 结果
     */
    public int deleteTRawMaterialsById(Long id);

    /**
     * 批量删除原辅材料信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTRawMaterialsByIds(Long[] ids);
}
