package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TPipelineLeakMapper;
import com.ruoyi.admin.domain.TPipelineLeak;
import com.ruoyi.admin.service.ITPipelineLeakService;

/**
 * 管道泄漏Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
@Service
public class TPipelineLeakServiceImpl implements ITPipelineLeakService 
{
    @Autowired
    private TPipelineLeakMapper tPipelineLeakMapper;

    /**
     * 查询管道泄漏
     * 
     * @param id 管道泄漏主键
     * @return 管道泄漏
     */
    @Override
    public TPipelineLeak selectTPipelineLeakById(Long id)
    {
        return tPipelineLeakMapper.selectTPipelineLeakById(id);
    }

    /**
     * 查询管道泄漏列表
     * 
     * @param tPipelineLeak 管道泄漏
     * @return 管道泄漏
     */
    @Override
    public List<TPipelineLeak> selectTPipelineLeakList(TPipelineLeak tPipelineLeak)
    {
        return tPipelineLeakMapper.selectTPipelineLeakList(tPipelineLeak);
    }

    /**
     * 新增管道泄漏
     * 
     * @param tPipelineLeak 管道泄漏
     * @return 结果
     */
    @Override
    public int insertTPipelineLeak(TPipelineLeak tPipelineLeak)
    {
        return tPipelineLeakMapper.insertTPipelineLeak(tPipelineLeak);
    }

    /**
     * 修改管道泄漏
     * 
     * @param tPipelineLeak 管道泄漏
     * @return 结果
     */
    @Override
    public int updateTPipelineLeak(TPipelineLeak tPipelineLeak)
    {
        return tPipelineLeakMapper.updateTPipelineLeak(tPipelineLeak);
    }

    /**
     * 批量删除管道泄漏
     * 
     * @param ids 需要删除的管道泄漏主键
     * @return 结果
     */
    @Override
    public int deleteTPipelineLeakByIds(Long[] ids)
    {
        return tPipelineLeakMapper.deleteTPipelineLeakByIds(ids);
    }

    /**
     * 删除管道泄漏信息
     * 
     * @param id 管道泄漏主键
     * @return 结果
     */
    @Override
    public int deleteTPipelineLeakById(Long id)
    {
        return tPipelineLeakMapper.deleteTPipelineLeakById(id);
    }
}
