package com.ruoyi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.TLayerSet;
import com.ruoyi.admin.domain.bo.TLayerSetLayerBo;
import com.ruoyi.admin.mapper.TLayerMapper;
import com.ruoyi.admin.mapper.TLayerSetMapper;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TLayerSetLayerMapper;
import com.ruoyi.admin.domain.TLayerSetLayer;
import com.ruoyi.admin.service.ITLayerSetLayerService;

/**
 * 图层集与图层的关联Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@Service
public class TLayerSetLayerServiceImpl implements ITLayerSetLayerService {
    @Autowired
    private TLayerSetLayerMapper tLayerSetLayerMapper;

    @Autowired
    private TLayerMapper tLayerMapper;

    @Autowired
    private TLayerSetMapper tLayerLayerMapper;


    /**
     * 查询图层集与图层的关联
     *
     * @param layerSetId 图层集与图层的关联主键
     * @return 图层集与图层的关联
     */
    @Override
    public TLayerSetLayer selectTLayerSetLayerByLayerSetId(Long layerSetId) {
        return tLayerSetLayerMapper.selectTLayerSetLayerByLayerSetId(layerSetId);
    }

    /**
     * 查询图层集与图层的关联列表
     *
     * @param tLayerSetLayer 图层集与图层的关联
     * @return 图层集与图层的关联
     */
    @Override
    public List<TLayerSetLayer> selectTLayerSetLayerList(TLayerSetLayer tLayerSetLayer) {

        return tLayerSetLayerMapper.selectTLayerSetLayerList(tLayerSetLayer);
    }

    @Override
    public R<Void> insertRelation(TLayerSetLayerBo tLayerSetLayerBo) {

        TLayerSet set = tLayerLayerMapper.selectTLayerSetById(tLayerSetLayerBo.getLayerSetId());
        if (set == null) {
            return R.fail("图层集不存在");
        }

        List<Long> layerIds = tLayerSetLayerBo.getLayerId();
        for (Long layerId : layerIds) {
            TLayer layer = tLayerMapper.selectTLayerById(layerId);
            if (layer == null) {
                return R.fail("图层不存在");
            }
        }

        //根据图层集的 id 删除中间表的内容
        tLayerSetLayerMapper.deleteTLayerSetLayerByLayerSetId(tLayerSetLayerBo.getLayerSetId());


        for (Long layerId : layerIds) {
            TLayerSetLayer tLayerSetLayer = new TLayerSetLayer();
            // 设置图层集 ID 和图层 ID
            tLayerSetLayer.setLayerSetId(tLayerSetLayerBo.getLayerSetId());
            tLayerSetLayer.setLayerId(layerId);

            // 将关系实体类添加到列表中
            tLayerSetLayerMapper.insertTLayerSetLayer(tLayerSetLayer);
        }

        return R.ok();
    }

    /**
     * 新增图层集与图层的关联
     *
     * @param tLayerSetLayer 图层集与图层的关联
     * @return 结果
     */
    @Override
    public int insertTLayerSetLayer(TLayerSetLayer tLayerSetLayer) {
        return tLayerSetLayerMapper.insertTLayerSetLayer(tLayerSetLayer);
    }

    /**
     * 修改图层集与图层的关联
     *
     * @param tLayerSetLayer 图层集与图层的关联
     * @return 结果
     */
    @Override
    public int updateTLayerSetLayer(TLayerSetLayer tLayerSetLayer) {
        return tLayerSetLayerMapper.updateTLayerSetLayer(tLayerSetLayer);
    }

    /**
     * 批量删除图层集与图层的关联
     *
     * @param layerSetIds 需要删除的图层集与图层的关联主键
     * @return 结果
     */
    @Override
    public int deleteTLayerSetLayerByLayerSetIds(Long[] layerSetIds) {
        return tLayerSetLayerMapper.deleteTLayerSetLayerByLayerSetIds(layerSetIds);
    }

    /**
     * 删除图层集与图层的关联信息
     *
     * @param layerSetId 图层集与图层的关联主键
     * @return 结果
     */
    @Override
    public int deleteTLayerSetLayerByLayerSetId(Long layerSetId) {
        return tLayerSetLayerMapper.deleteTLayerSetLayerByLayerSetId(layerSetId);
    }
}
