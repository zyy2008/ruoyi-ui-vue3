package com.ruoyi.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TSuspectedPollutionTraceMapper;
import com.ruoyi.admin.domain.TSuspectedPollutionTrace;
import com.ruoyi.admin.service.ITSuspectedPollutionTraceService;

/**
 * 可疑污染源或污染痕迹Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-09
 */
@Service
public class TSuspectedPollutionTraceServiceImpl implements ITSuspectedPollutionTraceService 
{
    @Autowired
    private TSuspectedPollutionTraceMapper tSuspectedPollutionTraceMapper;

    /**
     * 查询可疑污染源或污染痕迹
     * 
     * @param id 可疑污染源或污染痕迹主键
     * @return 可疑污染源或污染痕迹
     */
    @Override
    public TSuspectedPollutionTrace selectTSuspectedPollutionTraceById(Long id)
    {
        return tSuspectedPollutionTraceMapper.selectTSuspectedPollutionTraceById(id);
    }

    /**
     * 查询可疑污染源或污染痕迹列表
     * 
     * @param tSuspectedPollutionTrace 可疑污染源或污染痕迹
     * @return 可疑污染源或污染痕迹
     */
    @Override
    public List<TSuspectedPollutionTrace> selectTSuspectedPollutionTraceList(TSuspectedPollutionTrace tSuspectedPollutionTrace)
    {
        return tSuspectedPollutionTraceMapper.selectTSuspectedPollutionTraceList(tSuspectedPollutionTrace);
    }

    /**
     * 新增可疑污染源或污染痕迹
     * 
     * @param tSuspectedPollutionTrace 可疑污染源或污染痕迹
     * @return 结果
     */
    @Override
    public int insertTSuspectedPollutionTrace(TSuspectedPollutionTrace tSuspectedPollutionTrace)
    {
        return tSuspectedPollutionTraceMapper.insertTSuspectedPollutionTrace(tSuspectedPollutionTrace);
    }

    /**
     * 修改可疑污染源或污染痕迹
     * 
     * @param tSuspectedPollutionTrace 可疑污染源或污染痕迹
     * @return 结果
     */
    @Override
    public int updateTSuspectedPollutionTrace(TSuspectedPollutionTrace tSuspectedPollutionTrace)
    {
        return tSuspectedPollutionTraceMapper.updateTSuspectedPollutionTrace(tSuspectedPollutionTrace);
    }

    /**
     * 批量删除可疑污染源或污染痕迹
     * 
     * @param ids 需要删除的可疑污染源或污染痕迹主键
     * @return 结果
     */
    @Override
    public int deleteTSuspectedPollutionTraceByIds(Long[] ids)
    {
        return tSuspectedPollutionTraceMapper.deleteTSuspectedPollutionTraceByIds(ids);
    }

    /**
     * 删除可疑污染源或污染痕迹信息
     * 
     * @param id 可疑污染源或污染痕迹主键
     * @return 结果
     */
    @Override
    public int deleteTSuspectedPollutionTraceById(Long id)
    {
        return tSuspectedPollutionTraceMapper.deleteTSuspectedPollutionTraceById(id);
    }
}
