package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基地Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-06
 */
public interface TBaseMapper 
{
    /**
     * 查询基地
     * 
     * @param id 基地主键
     * @return 基地
     */
    public TBase selectTBaseById(Long id);

    /**
     * 查询基地列表
     * 
     * @param tBase 基地
     * @return 基地集合
     */
    public List<TBase> selectTBaseList(TBase tBase);
    public List<TBase> selectTBaseListByBaseId(@Param("baseId") List<Long> baseId);

    /**
     * 新增基地
     * 
     * @param tBase 基地
     * @return 结果
     */
    public int insertTBase(TBase tBase);

    /**
     * 修改基地
     * 
     * @param tBase 基地
     * @return 结果
     */
    public int updateTBase(TBase tBase);

    /**
     * 删除基地
     * 
     * @param id 基地主键
     * @return 结果
     */
    public int deleteTBaseById(Long id);

    /**
     * 批量删除基地
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBaseByIds(Long[] ids);
}
