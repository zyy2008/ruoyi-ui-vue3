package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TElementClassification;
import com.ruoyi.admin.domain.TElementClassificationNorm;
import com.ruoyi.admin.domain.vo.TElementClassificationNormVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 要素分类编码规范Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-31
 */
public interface TElementClassificationNormMapper 
{
    /**
     * 查询要素分类编码规范
     * 
     * @param id 要素分类编码规范主键
     * @return 要素分类编码规范
     */
    public TElementClassificationNorm selectTElementClassificationNormById(Long id);

    /**
     * 查询要素分类编码规范列表
     * 
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 要素分类编码规范集合
     */

    public List<TElementClassificationNorm> selectTElementClassificationNormList(TElementClassificationNorm tElementClassificationNorm);
    public List<TElementClassificationNorm> selectTElementClassificationNormByIdList(@Param("id") Long[] id);
    public List<TElementClassificationNorm> selectTElementClassificationNormByCodeList(@Param("code") String[] code);
    public List<TElementClassificationNormVo> selectTElementClassificationNormListVo(TElementClassificationNorm tElementClassificationNorm);

    /**
     * 新增要素分类编码规范
     * 
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 结果
     */
    public int insertTElementClassificationNorm(TElementClassificationNorm tElementClassificationNorm);

    /**
     * 修改要素分类编码规范
     * 
     * @param tElementClassificationNorm 要素分类编码规范
     * @return 结果
     */
    public int updateTElementClassificationNorm(TElementClassificationNorm tElementClassificationNorm);

    /**
     * 删除要素分类编码规范
     * 
     * @param id 要素分类编码规范主键
     * @return 结果
     */
    public int deleteTElementClassificationNormById(Long id);

    /**
     * 批量删除要素分类编码规范
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTElementClassificationNormByIds(Long[] ids);

    /**
     * 批量删除要素分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTElementClassificationByIds(Long[] ids);
    
    /**
     * 批量新增要素分类
     * 
     * @param tElementClassificationList 要素分类列表
     * @return 结果
     */
    public int batchTElementClassification(List<TElementClassification> tElementClassificationList);
    

    /**
     * 通过要素分类编码规范主键删除要素分类信息
     * 
     * @param id 要素分类编码规范ID
     * @return 结果
     */
    public int deleteTElementClassificationById(Long id);
}
