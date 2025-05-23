package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TLayerSetLayer;

import java.util.List;

/**
 * 图层集与图层的关联Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-26
 */
public interface TLayerSetLayerMapper {
    /**
     * 查询图层集与图层的关联
     *
     * @param layerSetId 图层集与图层的关联主键
     * @return 图层集与图层的关联
     */
    public TLayerSetLayer selectTLayerSetLayerByLayerSetId(Long layerSetId);


    /**
     * 查询图层集与图层的关联列表
     *
     * @param tLayerSetLayer 图层集与图层的关联
     * @return 图层集与图层的关联集合
     */
    public List<TLayerSetLayer> selectTLayerSetLayerList(TLayerSetLayer tLayerSetLayer);


    /**
     * 新增图层集与图层的关联
     *
     * @param tLayerSetLayer 图层集与图层的关联
     * @return 结果
     */
    public int insertTLayerSetLayer(TLayerSetLayer tLayerSetLayer);

    /**
     * 修改图层集与图层的关联
     *
     * @param tLayerSetLayer 图层集与图层的关联
     * @return 结果
     */
    public int updateTLayerSetLayer(TLayerSetLayer tLayerSetLayer);

    /**
     * 删除图层集与图层的关联
     *
     * @param layerSetId 图层集与图层的关联主键
     * @return 结果
     */
    public int deleteTLayerSetLayerByLayerSetId(Long layerSetId);

    /**
     * 批量删除图层集与图层的关联
     *
     * @param layerSetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTLayerSetLayerByLayerSetIds(Long[] layerSetIds);
}