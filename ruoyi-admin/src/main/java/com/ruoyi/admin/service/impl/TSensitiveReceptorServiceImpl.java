package com.ruoyi.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSensitiveReceptorMapper;
import com.ruoyi.admin.domain.TSensitiveReceptor;
import com.ruoyi.admin.service.ITSensitiveReceptorService;

/**
 * 地块及周边敏感受体信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Service
public class TSensitiveReceptorServiceImpl implements ITSensitiveReceptorService {
    @Autowired
    private TSensitiveReceptorMapper tSensitiveReceptorMapper;

    /**
     * 查询地块及周边敏感受体信息
     *
     * @param id 地块及周边敏感受体信息主键
     * @return 地块及周边敏感受体信息
     */
    @Override
    public TSensitiveReceptor selectTSensitiveReceptorById(Long id) {
        return tSensitiveReceptorMapper.selectTSensitiveReceptorById(id);
    }

    /**
     * 查询地块及周边敏感受体信息列表
     *
     * @param tSensitiveReceptor 地块及周边敏感受体信息
     * @return 地块及周边敏感受体信息
     */
    @Override
    public List<TSensitiveReceptor> selectTSensitiveReceptorList(TSensitiveReceptor tSensitiveReceptor) {
        return tSensitiveReceptorMapper.selectTSensitiveReceptorList(tSensitiveReceptor);
    }

    /**
     * 新增地块及周边敏感受体信息
     *
     * @param tSensitiveReceptor 地块及周边敏感受体信息
     * @return 结果
     */
    @Override
    public int insertTSensitiveReceptor(TSensitiveReceptor tSensitiveReceptor) {
        return tSensitiveReceptorMapper.insertTSensitiveReceptor(tSensitiveReceptor);
    }

    /**
     * 修改地块及周边敏感受体信息
     *
     * @param tSensitiveReceptor 地块及周边敏感受体信息
     * @return 结果
     */
    @Override
    public int updateTSensitiveReceptor(TSensitiveReceptor tSensitiveReceptor) {
        return tSensitiveReceptorMapper.updateTSensitiveReceptor(tSensitiveReceptor);
    }

    /**
     * 批量删除地块及周边敏感受体信息
     *
     * @param ids 需要删除的地块及周边敏感受体信息主键
     * @return 结果
     */
    @Override
    public int deleteTSensitiveReceptorByIds(Long[] ids) {
        return tSensitiveReceptorMapper.deleteTSensitiveReceptorByIds(ids);
    }

    /**
     * 删除地块及周边敏感受体信息信息
     *
     * @param id 地块及周边敏感受体信息主键
     * @return 结果
     */
    @Override
    public int deleteTSensitiveReceptorById(Long id) {
        return tSensitiveReceptorMapper.deleteTSensitiveReceptorById(id);
    }
}
