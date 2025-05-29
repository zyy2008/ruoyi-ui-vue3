package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TSensitiveReceptor;

/**
 * 地块及周边敏感受体信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-28
 */
public interface TSensitiveReceptorMapper 
{
    /**
     * 查询地块及周边敏感受体信息
     * 
     * @param id 地块及周边敏感受体信息主键
     * @return 地块及周边敏感受体信息
     */
    public TSensitiveReceptor selectTSensitiveReceptorById(Long id);

    /**
     * 查询地块及周边敏感受体信息列表
     * 
     * @param tSensitiveReceptor 地块及周边敏感受体信息
     * @return 地块及周边敏感受体信息集合
     */
    public List<TSensitiveReceptor> selectTSensitiveReceptorList(TSensitiveReceptor tSensitiveReceptor);

    /**
     * 新增地块及周边敏感受体信息
     * 
     * @param tSensitiveReceptor 地块及周边敏感受体信息
     * @return 结果
     */
    public int insertTSensitiveReceptor(TSensitiveReceptor tSensitiveReceptor);

    /**
     * 修改地块及周边敏感受体信息
     * 
     * @param tSensitiveReceptor 地块及周边敏感受体信息
     * @return 结果
     */
    public int updateTSensitiveReceptor(TSensitiveReceptor tSensitiveReceptor);

    /**
     * 删除地块及周边敏感受体信息
     * 
     * @param id 地块及周边敏感受体信息主键
     * @return 结果
     */
    public int deleteTSensitiveReceptorById(Long id);

    /**
     * 批量删除地块及周边敏感受体信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSensitiveReceptorByIds(Long[] ids);
}
