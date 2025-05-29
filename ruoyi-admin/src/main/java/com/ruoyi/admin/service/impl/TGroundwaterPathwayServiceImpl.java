package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TGroundwaterPathwayMapper;
import com.ruoyi.admin.domain.TGroundwaterPathway;
import com.ruoyi.admin.service.ITGroundwaterPathwayService;

/**
 * 地下水迁移途径信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TGroundwaterPathwayServiceImpl implements ITGroundwaterPathwayService 
{
    @Autowired
    private TGroundwaterPathwayMapper tGroundwaterPathwayMapper;

    /**
     * 查询地下水迁移途径信息
     * 
     * @param id 地下水迁移途径信息主键
     * @return 地下水迁移途径信息
     */
    @Override
    public TGroundwaterPathway selectTGroundwaterPathwayById(Long id)
    {
        return tGroundwaterPathwayMapper.selectTGroundwaterPathwayById(id);
    }

    /**
     * 查询地下水迁移途径信息列表
     * 
     * @param tGroundwaterPathway 地下水迁移途径信息
     * @return 地下水迁移途径信息
     */
    @Override
    public List<TGroundwaterPathway> selectTGroundwaterPathwayList(TGroundwaterPathway tGroundwaterPathway)
    {
        return tGroundwaterPathwayMapper.selectTGroundwaterPathwayList(tGroundwaterPathway);
    }

    /**
     * 新增地下水迁移途径信息
     * 
     * @param tGroundwaterPathway 地下水迁移途径信息
     * @return 结果
     */
    @Override
    public int insertTGroundwaterPathway(TGroundwaterPathway tGroundwaterPathway)
    {
        return tGroundwaterPathwayMapper.insertTGroundwaterPathway(tGroundwaterPathway);
    }

    /**
     * 修改地下水迁移途径信息
     * 
     * @param tGroundwaterPathway 地下水迁移途径信息
     * @return 结果
     */
    @Override
    public int updateTGroundwaterPathway(TGroundwaterPathway tGroundwaterPathway)
    {
        return tGroundwaterPathwayMapper.updateTGroundwaterPathway(tGroundwaterPathway);
    }

    /**
     * 批量删除地下水迁移途径信息
     * 
     * @param ids 需要删除的地下水迁移途径信息主键
     * @return 结果
     */
    @Override
    public int deleteTGroundwaterPathwayByIds(Long[] ids)
    {
        return tGroundwaterPathwayMapper.deleteTGroundwaterPathwayByIds(ids);
    }

    /**
     * 删除地下水迁移途径信息信息
     * 
     * @param id 地下水迁移途径信息主键
     * @return 结果
     */
    @Override
    public int deleteTGroundwaterPathwayById(Long id)
    {
        return tGroundwaterPathwayMapper.deleteTGroundwaterPathwayById(id);
    }
}
