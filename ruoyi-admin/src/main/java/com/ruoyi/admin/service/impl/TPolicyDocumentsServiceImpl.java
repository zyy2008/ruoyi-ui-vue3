package com.ruoyi.admin.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TPolicyDocumentsMapper;
import com.ruoyi.admin.domain.TPolicyDocuments;
import com.ruoyi.admin.service.ITPolicyDocumentsService;

/**
 * 政策文件Service业务层处理
 *
 * @author ruoyi
 * @date 2025-06-14
 */
@Service
public class TPolicyDocumentsServiceImpl implements ITPolicyDocumentsService {
    @Autowired
    private TPolicyDocumentsMapper tPolicyDocumentsMapper;

    /**
     * 查询政策文件
     *
     * @param id 政策文件主键
     * @return 政策文件
     */
    @Override
    public TPolicyDocuments selectTPolicyDocumentsById(Long id) {
        return tPolicyDocumentsMapper.selectTPolicyDocumentsById(id);
    }

    /**
     * 查询政策文件列表
     *
     * @param tPolicyDocuments 政策文件
     * @return 政策文件
     */
    @Override
    public List<TPolicyDocuments> selectTPolicyDocumentsList(TPolicyDocuments tPolicyDocuments) {
        return tPolicyDocumentsMapper.selectTPolicyDocumentsList(tPolicyDocuments);
    }

    /**
     * 新增政策文件
     *
     * @param tPolicyDocuments 政策文件
     * @return 结果
     */
    @Override
    public int insertTPolicyDocuments(TPolicyDocuments tPolicyDocuments) {
        if(null==tPolicyDocuments.getPublishDate())
            tPolicyDocuments.setPublishDate(DateUtils.getDate());
        return tPolicyDocumentsMapper.insertTPolicyDocuments(tPolicyDocuments);
    }

    /**
     * 修改政策文件
     *
     * @param tPolicyDocuments 政策文件
     * @return 结果
     */
    @Override
    public int updateTPolicyDocuments(TPolicyDocuments tPolicyDocuments) {
        if(null==tPolicyDocuments.getPublishDate())
            tPolicyDocuments.setPublishDate(DateUtils.getDate());
        return tPolicyDocumentsMapper.updateTPolicyDocuments(tPolicyDocuments);
    }

    /**
     * 批量删除政策文件
     *
     * @param ids 需要删除的政策文件主键
     * @return 结果
     */
    @Override
    public int deleteTPolicyDocumentsByIds(Long[] ids) {
        return tPolicyDocumentsMapper.deleteTPolicyDocumentsByIds(ids);
    }

    /**
     * 删除政策文件信息
     *
     * @param id 政策文件主键
     * @return 结果
     */
    @Override
    public int deleteTPolicyDocumentsById(Long id) {
        return tPolicyDocumentsMapper.deleteTPolicyDocumentsById(id);
    }
}
