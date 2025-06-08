package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TPipelineLeak;

/**
 * 管道泄漏Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
public interface TPipelineLeakMapper 
{
    /**
     * 查询管道泄漏
     * 
     * @param id 管道泄漏主键
     * @return 管道泄漏
     */
    public TPipelineLeak selectTPipelineLeakById(Long id);

    /**
     * 查询管道泄漏列表
     * 
     * @param tPipelineLeak 管道泄漏
     * @return 管道泄漏集合
     */
    public List<TPipelineLeak> selectTPipelineLeakList(TPipelineLeak tPipelineLeak);

    /**
     * 新增管道泄漏
     * 
     * @param tPipelineLeak 管道泄漏
     * @return 结果
     */
    public int insertTPipelineLeak(TPipelineLeak tPipelineLeak);

    /**
     * 修改管道泄漏
     * 
     * @param tPipelineLeak 管道泄漏
     * @return 结果
     */
    public int updateTPipelineLeak(TPipelineLeak tPipelineLeak);

    /**
     * 删除管道泄漏
     * 
     * @param id 管道泄漏主键
     * @return 结果
     */
    public int deleteTPipelineLeakById(Long id);

    /**
     * 批量删除管道泄漏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPipelineLeakByIds(Long[] ids);
}
