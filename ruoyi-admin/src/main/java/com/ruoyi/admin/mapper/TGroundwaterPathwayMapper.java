package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TGroundwaterPathway;

/**
 * 地下水迁移途径信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TGroundwaterPathwayMapper 
{
    /**
     * 查询地下水迁移途径信息
     * 
     * @param id 地下水迁移途径信息主键
     * @return 地下水迁移途径信息
     */
    public TGroundwaterPathway selectTGroundwaterPathwayById(Long id);

    /**
     * 查询地下水迁移途径信息列表
     * 
     * @param tGroundwaterPathway 地下水迁移途径信息
     * @return 地下水迁移途径信息集合
     */
    public List<TGroundwaterPathway> selectTGroundwaterPathwayList(TGroundwaterPathway tGroundwaterPathway);

    /**
     * 新增地下水迁移途径信息
     * 
     * @param tGroundwaterPathway 地下水迁移途径信息
     * @return 结果
     */
    public int insertTGroundwaterPathway(TGroundwaterPathway tGroundwaterPathway);

    /**
     * 修改地下水迁移途径信息
     * 
     * @param tGroundwaterPathway 地下水迁移途径信息
     * @return 结果
     */
    public int updateTGroundwaterPathway(TGroundwaterPathway tGroundwaterPathway);

    /**
     * 删除地下水迁移途径信息
     * 
     * @param id 地下水迁移途径信息主键
     * @return 结果
     */
    public int deleteTGroundwaterPathwayById(Long id);

    /**
     * 批量删除地下水迁移途径信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTGroundwaterPathwayByIds(Long[] ids);
}
