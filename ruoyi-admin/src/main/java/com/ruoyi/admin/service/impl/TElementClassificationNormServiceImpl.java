package com.ruoyi.admin.service.impl;

import com.ruoyi.admin.domain.TElementClassification;
import com.ruoyi.admin.domain.TElementClassificationNorm;
import com.ruoyi.admin.mapper.TElementClassificationNormMapper;
import com.ruoyi.admin.service.ITElementClassificationNormService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 要素分类编码规范Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@Service
public class TElementClassificationNormServiceImpl implements ITElementClassificationNormService {
    @Autowired
    private TElementClassificationNormMapper tElementClassificationNormMapper;

    /**
     * 查询要素分类编码规范
     *
     * @param id 要素分类编码规范主键
     * @return 要素分类编码规范
     */
    @Override
    public TElementClassificationNorm selectTElementClassificationNormById(Long id) {
        return tElementClassificationNormMapper.selectTElementClassificationNormById(id);
    }

    /**
     * 查询要素分类编码规范列表
     *
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 要素分类编码规范
     */
    @Override
    public List<TElementClassificationNorm> selectTElementClassificationNormList(TElementClassificationNorm tElementClassificationNorm) {
        return tElementClassificationNormMapper.selectTElementClassificationNormList(tElementClassificationNorm);
    }

    /**
     * 新增要素分类编码规范
     *
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTElementClassificationNorm(TElementClassificationNorm tElementClassificationNorm) {
        tElementClassificationNorm.setId(null);
        int rows = tElementClassificationNormMapper.insertTElementClassificationNorm(tElementClassificationNorm);
        insertTElementClassification(tElementClassificationNorm);
        return rows;
    }

    /**
     * 修改要素分类编码规范
     *
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTElementClassificationNorm(TElementClassificationNorm tElementClassificationNorm) {
        tElementClassificationNormMapper.deleteTElementClassificationById(tElementClassificationNorm.getId());
        insertTElementClassification(tElementClassificationNorm);
        return tElementClassificationNormMapper.updateTElementClassificationNorm(tElementClassificationNorm);
    }

    /**
     * 批量删除要素分类编码规范
     *
     * @param ids 需要删除的要素分类编码规范主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTElementClassificationNormByIds(Long[] ids) {
        tElementClassificationNormMapper.deleteTElementClassificationByIds(ids);
        return tElementClassificationNormMapper.deleteTElementClassificationNormByIds(ids);
    }

    /**
     * 删除要素分类编码规范信息
     *
     * @param id 要素分类编码规范主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTElementClassificationNormById(Long id) {
        tElementClassificationNormMapper.deleteTElementClassificationById(id);
        return tElementClassificationNormMapper.deleteTElementClassificationNormById(id);
    }

    /**
     * 新增要素分类信息
     *
     * @param tElementClassificationNorm 要素分类编码规范对象
     */
    public void insertTElementClassification(TElementClassificationNorm tElementClassificationNorm) {
        List<TElementClassification> tElementClassificationList = tElementClassificationNorm.getTElementClassificationList();
        Long id = tElementClassificationNorm.getId();
        if (StringUtils.isNotNull(tElementClassificationList)) {
            List<TElementClassification> list = new ArrayList<TElementClassification>();
            for (TElementClassification tElementClassification : tElementClassificationList) {
                tElementClassification.setId(id);
                list.add(tElementClassification);
            }
            if (list.size() > 0) {
                tElementClassificationNormMapper.batchTElementClassification(list);
            }
        }
    }
}
