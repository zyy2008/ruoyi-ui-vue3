package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TLayerSet;
import com.ruoyi.admin.domain.bo.ShortestPathBo;
import com.ruoyi.admin.domain.vo.ShortestPathVo;
import com.ruoyi.admin.domain.vo.TLayerSetVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 图层集Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-26
 */
public interface TLayerSetMapper {
    /**
     * 查询图层集
     *
     * @param id 图层集主键
     * @return 图层集
     */
    public TLayerSet selectTLayerSetById(Long id);

    /**
     * 查询图层集列表
     *
     * @param tLayerSet 图层集
     * @return 图层集集合
     */
//    @DataScope(deptAlias = "ls", userAlias = "ls")
    public List<TLayerSet> selectTLayerSetList(TLayerSet tLayerSet);
    public List<TLayerSet> selectTLayerSetListByBaseId(Long baseId);
    public List<TLayerSet> selectTLayerSetListByDeptId(Long deptId);
    public List<TLayerSet> selectTLayerSetListByDeptIds(Long[] deptId);

//    @DataScope(deptAlias = "ls", userAlias = "ls")
    public List<TLayerSetVo> selectTLayerSetVoList(TLayerSet tLayerSet);

    /**
     * 新增图层集
     *
     * @param tLayerSet 图层集
     * @return 结果
     */
    public int insertTLayerSet(TLayerSet tLayerSet);

    /**
     * 修改图层集
     *
     * @param tLayerSet 图层集
     * @return 结果
     */
    public int updateTLayerSet(TLayerSet tLayerSet);

    /**
     * 删除图层集
     *
     * @param id 图层集主键
     * @return 结果
     */
    public int deleteTLayerSetById(Long id);

    public int deleteTLayerSetByNameAndUserIdAndDeptId(TLayerSet layerSet);

    public int deleteTLayerSetByLayerSetId(Long layerSetId);

    /**
     * 批量删除图层集
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTLayerSetByIds(Long[] ids);

    /**
     * 坐标转换平面坐标
     * @return
     */
    String ST_AsEWKT(@Param("x") BigDecimal x, @Param("y") BigDecimal y, @Param("t1") int t1, @Param("t2") int t2);
    List<ShortestPathVo> getShortestPath(ShortestPathBo bo);
}
