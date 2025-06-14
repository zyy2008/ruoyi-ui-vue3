package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TPolicyDocuments;

/**
 * 政策文件Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-14
 */
public interface TPolicyDocumentsMapper 
{
    /**
     * 查询政策文件
     * 
     * @param id 政策文件主键
     * @return 政策文件
     */
    public TPolicyDocuments selectTPolicyDocumentsById(Long id);

    /**
     * 查询政策文件列表
     * 
     * @param tPolicyDocuments 政策文件
     * @return 政策文件集合
     */
    public List<TPolicyDocuments> selectTPolicyDocumentsList(TPolicyDocuments tPolicyDocuments);

    /**
     * 新增政策文件
     * 
     * @param tPolicyDocuments 政策文件
     * @return 结果
     */
    public int insertTPolicyDocuments(TPolicyDocuments tPolicyDocuments);

    /**
     * 修改政策文件
     * 
     * @param tPolicyDocuments 政策文件
     * @return 结果
     */
    public int updateTPolicyDocuments(TPolicyDocuments tPolicyDocuments);

    /**
     * 删除政策文件
     * 
     * @param id 政策文件主键
     * @return 结果
     */
    public int deleteTPolicyDocumentsById(Long id);

    /**
     * 批量删除政策文件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPolicyDocumentsByIds(Long[] ids);
}
