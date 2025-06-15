package com.ruoyi.admin.service;

import java.util.List;
import com.ruoyi.admin.domain.TSuspectedPollutionTrace;

/**
 * 可疑污染源或污染痕迹Service接口
 * 
 * @author ruoyi
 * @date 2025-06-09
 */
public interface ITSuspectedPollutionTraceService 
{
    /**
     * 查询可疑污染源或污染痕迹
     * 
     * @param id 可疑污染源或污染痕迹主键
     * @return 可疑污染源或污染痕迹
     */
    public TSuspectedPollutionTrace selectTSuspectedPollutionTraceById(Long id);

    /**
     * 查询可疑污染源或污染痕迹列表
     * 
     * @param tSuspectedPollutionTrace 可疑污染源或污染痕迹
     * @return 可疑污染源或污染痕迹集合
     */
    public List<TSuspectedPollutionTrace> selectTSuspectedPollutionTraceList(TSuspectedPollutionTrace tSuspectedPollutionTrace);

    /**
     * 新增可疑污染源或污染痕迹
     * 
     * @param tSuspectedPollutionTrace 可疑污染源或污染痕迹
     * @return 结果
     */
    public int insertTSuspectedPollutionTrace(TSuspectedPollutionTrace tSuspectedPollutionTrace);

    /**
     * 修改可疑污染源或污染痕迹
     * 
     * @param tSuspectedPollutionTrace 可疑污染源或污染痕迹
     * @return 结果
     */
    public int updateTSuspectedPollutionTrace(TSuspectedPollutionTrace tSuspectedPollutionTrace);

    /**
     * 批量删除可疑污染源或污染痕迹
     * 
     * @param ids 需要删除的可疑污染源或污染痕迹主键集合
     * @return 结果
     */
    public int deleteTSuspectedPollutionTraceByIds(Long[] ids);

    /**
     * 删除可疑污染源或污染痕迹信息
     * 
     * @param id 可疑污染源或污染痕迹主键
     * @return 结果
     */
    public int deleteTSuspectedPollutionTraceById(Long id);
}
