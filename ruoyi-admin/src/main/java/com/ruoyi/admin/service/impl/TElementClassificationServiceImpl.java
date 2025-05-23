package com.ruoyi.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.admin.domain.TElementClassification;
import com.ruoyi.admin.domain.TElementClassificationNorm;
import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.vo.TElementClassificationNormVo;
import com.ruoyi.admin.domain.vo.TElementClassificationVo;
import com.ruoyi.admin.mapper.TElementClassificationMapper;
import com.ruoyi.admin.mapper.TElementClassificationNormMapper;
import com.ruoyi.admin.mapper.TLayerMapper;
import com.ruoyi.admin.service.ITElementClassificationService;
import com.ruoyi.common.system.mapper.SysUserMapper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 要素分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@Service
public class TElementClassificationServiceImpl implements ITElementClassificationService {
    @Autowired
    private TElementClassificationMapper tElementClassificationMapper;
    @Autowired
    private TElementClassificationNormMapper tElementClassificationNormMapper;
    @Autowired
    private TLayerMapper tLayerMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询要素分类
     *
     * @param id 要素分类主键
     * @return 要素分类
     */
    @Override
    public TElementClassification selectTElementClassificationById(Long id) {
        return tElementClassificationMapper.selectTElementClassificationById(id);
    }
    @Override
    public List<TElementClassification> selectTElementClassificationByIds(Long[] id) {
        return tElementClassificationMapper.selectTElementClassificationByIds(id);
    }

    /**
     * 查询要素分类列表
     *
     * @param tElementClassification 要素分类
     * @return 要素分类
     */
    @Override
    public List<TElementClassification> selectTElementClassificationList(TElementClassification tElementClassification) {
        return tElementClassificationMapper.selectTElementClassificationList(tElementClassification);
    }

    @Override
    public List<TElementClassificationVo> selectTElementClassificationList(Long baseId) {
        System.out.println("基地id：" + baseId);
        Long[] baseIds = new Long[]{baseId};
        if (null == baseIds) baseIds = SecurityUtils.getBaseId();
        if (0 > baseIds.length) baseIds = null;
        List<TElementClassificationVo> data = new ArrayList<>();
        TElementClassification tElementClassification = new TElementClassification();
        List<TElementClassification> elementClassificationVoList = tElementClassificationMapper.selectTElementClassificationList(tElementClassification);
        for (TElementClassification elementClassification : elementClassificationVoList) {
            TElementClassificationVo elementClassificationVo = BeanUtil.toBean(elementClassification, TElementClassificationVo.class);
            TElementClassificationNorm tElementClassificationNorm = new TElementClassificationNorm();
            tElementClassificationNorm.setElmentId(elementClassification.getId());
            List<TElementClassificationNormVo> elementClassificationNormVoList = tElementClassificationNormMapper.selectTElementClassificationNormListVo(tElementClassificationNorm);
            if (null != baseIds)
                for (TElementClassificationNormVo tElementClassificationNormVo : elementClassificationNormVoList) {
                    for (Long id : baseIds) {
                        TLayer tLayer = new TLayer();
                        tLayer.setBaseId(id);
                        tLayer.setCode(tElementClassificationNormVo.getCode());
                        TLayer layer = tLayerMapper.selectTLayerOne(tLayer);
                        if (null != layer) {
                            tElementClassificationNormVo.setOptional(true);
                            tElementClassificationNormVo.setLayer(layer);
                        }
                    }
                }
            elementClassificationVo.setElementClassificationNormVoList(elementClassificationNormVoList);
            data.add(elementClassificationVo);
        }
        return data;
    }

    @Override
    public List<TElementClassificationVo> selectTElementClassificationList(Long baseId, List<TLayer> layerList) {
        System.out.println("基地id：" + baseId);
        Long[] baseIds = new Long[]{baseId};
        if (null == baseIds) baseIds = SecurityUtils.getBaseId();
        if (0 < baseIds.length) baseIds = null;
        List<TElementClassificationVo> data = new ArrayList<>();
        TElementClassification tElementClassification = new TElementClassification();
        List<TElementClassification> elementClassificationVoList = tElementClassificationMapper.selectTElementClassificationList(tElementClassification);
        for (TElementClassification elementClassification : elementClassificationVoList) {
            TElementClassificationVo elementClassificationVo = BeanUtil.toBean(elementClassification, TElementClassificationVo.class);
            TElementClassificationNorm tElementClassificationNorm = new TElementClassificationNorm();
            tElementClassificationNorm.setElmentId(elementClassification.getId());
            List<TElementClassificationNormVo> elementClassificationNormVoList = tElementClassificationNormMapper.selectTElementClassificationNormListVo(tElementClassificationNorm);
            for (TElementClassificationNormVo tElementClassificationNormVo : elementClassificationNormVoList) {
                for (TLayer layer : layerList) {
                    if (null != layer && tElementClassificationNormVo.getCode().equals(layer.getCode())) {
                        tElementClassificationNormVo.setOptional(true);
                        tElementClassificationNormVo.setLayer(layer);
                    }
                }
            }
            elementClassificationVo.setElementClassificationNormVoList(elementClassificationNormVoList);
            data.add(elementClassificationVo);
        }
        return data;
    }

    public List<TElementClassificationVo> selectTElementClassificationList(Long[] baseIds, List<TLayer> layerList) {
        if (null == baseIds) baseIds = SecurityUtils.getBaseId();
        if (0 < baseIds.length) baseIds = null;
        List<TElementClassificationVo> data = new ArrayList<>();
        TElementClassification tElementClassification = new TElementClassification();
        List<TElementClassification> elementClassificationVoList = tElementClassificationMapper.selectTElementClassificationList(tElementClassification);
        for (TElementClassification elementClassification : elementClassificationVoList) {
            TElementClassificationVo elementClassificationVo = BeanUtil.toBean(elementClassification, TElementClassificationVo.class);
            TElementClassificationNorm tElementClassificationNorm = new TElementClassificationNorm();
            tElementClassificationNorm.setElmentId(elementClassification.getId());
            List<TElementClassificationNormVo> elementClassificationNormVoList = tElementClassificationNormMapper.selectTElementClassificationNormListVo(tElementClassificationNorm);
            for (TElementClassificationNormVo tElementClassificationNormVo : elementClassificationNormVoList) {
                for (TLayer layer : layerList) {
                    if (null != layer && tElementClassificationNormVo.getCode().equals(layer.getCode())) {
                        tElementClassificationNormVo.setOptional(true);
                        tElementClassificationNormVo.setLayer(layer);
                    }
                }
            }
            elementClassificationVo.setElementClassificationNormVoList(elementClassificationNormVoList);
            data.add(elementClassificationVo);
        }
        return data;
    }

    /**
     * 新增要素分类
     *
     * @param tElementClassification 要素分类
     * @return 结果
     */
    @Override
    public int insertTElementClassification(TElementClassification tElementClassification) {
        return tElementClassificationMapper.insertTElementClassification(tElementClassification);
    }

    /**
     * 修改要素分类
     *
     * @param tElementClassification 要素分类
     * @return 结果
     */
    @Override
    public int updateTElementClassification(TElementClassification tElementClassification) {
        return tElementClassificationMapper.updateTElementClassification(tElementClassification);
    }

    /**
     * 批量删除要素分类
     *
     * @param ids 需要删除的要素分类主键
     * @return 结果
     */
    @Override
    public int deleteTElementClassificationByIds(Long[] ids) {
        return tElementClassificationMapper.deleteTElementClassificationByIds(ids);
    }

    /**
     * 删除要素分类信息
     *
     * @param id 要素分类主键
     * @return 结果
     */
    @Override
    public int deleteTElementClassificationById(Long id) {
        return tElementClassificationMapper.deleteTElementClassificationById(id);
    }
}
