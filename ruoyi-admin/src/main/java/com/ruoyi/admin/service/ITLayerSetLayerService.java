package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TLayerSetLayer;
import com.ruoyi.admin.domain.bo.TLayerSetLayerBo;
import com.ruoyi.common.core.domain.R;

/**
 * 图层集与图层的关联Service接口
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
public interface ITLayerSetLayerService 
{
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




    public R<Void> insertRelation(TLayerSetLayerBo tLayerSetLayerBo);


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
     * 批量删除图层集与图层的关联
     * 
     * @param layerSetIds 需要删除的图层集与图层的关联主键集合
     * @return 结果
     */
    public int deleteTLayerSetLayerByLayerSetIds(Long[] layerSetIds);

    /**
     * 删除图层集与图层的关联信息
     * 
     * @param layerSetId 图层集与图层的关联主键
     * @return 结果
     */
    public int deleteTLayerSetLayerByLayerSetId(Long layerSetId);
}
