package com.ruoyi.admin.mapper;

import java.util.List;
import com.ruoyi.admin.domain.TSewageSolidWaste;

/**
 * 污水处理厂固体废物产生量及污染防治措施Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-08
 */
public interface TSewageSolidWasteMapper 
{
    /**
     * 查询污水处理厂固体废物产生量及污染防治措施
     * 
     * @param id 污水处理厂固体废物产生量及污染防治措施主键
     * @return 污水处理厂固体废物产生量及污染防治措施
     */
    public TSewageSolidWaste selectTSewageSolidWasteById(Long id);

    /**
     * 查询污水处理厂固体废物产生量及污染防治措施列表
     * 
     * @param tSewageSolidWaste 污水处理厂固体废物产生量及污染防治措施
     * @return 污水处理厂固体废物产生量及污染防治措施集合
     */
    public List<TSewageSolidWaste> selectTSewageSolidWasteList(TSewageSolidWaste tSewageSolidWaste);

    /**
     * 新增污水处理厂固体废物产生量及污染防治措施
     * 
     * @param tSewageSolidWaste 污水处理厂固体废物产生量及污染防治措施
     * @return 结果
     */
    public int insertTSewageSolidWaste(TSewageSolidWaste tSewageSolidWaste);

    /**
     * 修改污水处理厂固体废物产生量及污染防治措施
     * 
     * @param tSewageSolidWaste 污水处理厂固体废物产生量及污染防治措施
     * @return 结果
     */
    public int updateTSewageSolidWaste(TSewageSolidWaste tSewageSolidWaste);

    /**
     * 删除污水处理厂固体废物产生量及污染防治措施
     * 
     * @param id 污水处理厂固体废物产生量及污染防治措施主键
     * @return 结果
     */
    public int deleteTSewageSolidWasteById(Long id);

    /**
     * 批量删除污水处理厂固体废物产生量及污染防治措施
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSewageSolidWasteByIds(Long[] ids);
}
