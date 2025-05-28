package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TGroundwaterPollutant;

/**
 * 地下水监测发现的超标污染物信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TGroundwaterPollutantMapper 
{
    /**
     * 查询地下水监测发现的超标污染物信息
     * 
     * @param id 地下水监测发现的超标污染物信息主键
     * @return 地下水监测发现的超标污染物信息
     */
    public TGroundwaterPollutant selectTGroundwaterPollutantById(Long id);

    /**
     * 查询地下水监测发现的超标污染物信息列表
     * 
     * @param tGroundwaterPollutant 地下水监测发现的超标污染物信息
     * @return 地下水监测发现的超标污染物信息集合
     */
    public List<TGroundwaterPollutant> selectTGroundwaterPollutantList(TGroundwaterPollutant tGroundwaterPollutant);

    /**
     * 新增地下水监测发现的超标污染物信息
     * 
     * @param tGroundwaterPollutant 地下水监测发现的超标污染物信息
     * @return 结果
     */
    public int insertTGroundwaterPollutant(TGroundwaterPollutant tGroundwaterPollutant);

    /**
     * 修改地下水监测发现的超标污染物信息
     * 
     * @param tGroundwaterPollutant 地下水监测发现的超标污染物信息
     * @return 结果
     */
    public int updateTGroundwaterPollutant(TGroundwaterPollutant tGroundwaterPollutant);

    /**
     * 删除地下水监测发现的超标污染物信息
     * 
     * @param id 地下水监测发现的超标污染物信息主键
     * @return 结果
     */
    public int deleteTGroundwaterPollutantById(Long id);

    /**
     * 批量删除地下水监测发现的超标污染物信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTGroundwaterPollutantByIds(Long[] ids);
}
