package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.generator.domain.GenTable;
import com.vividsolutions.jts.geom.Geometry;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 图层Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-08
 */
public interface TLayerMapper {
    /**
     * 查询图层
     *
     * @param id 图层主键
     * @return 图层
     */
    public TLayer selectTLayerById(Long id);

    public List<TLayer> selectTLayerByIds(@Param("ids") Long[] ids);

    /**
     * 查询图层列表
     *
     * @param tLayer 图层
     * @return 图层集合
     */
//    @DataScope(deptAlias = "layer", userAlias = "layer")
    public List<TLayer> selectTLayerList(TLayer tLayer);
    public List<TLayer> selectTLayerListGroupLayerParam (TLayer tLayer);

    /**
     * 查询数量
     *
     * @param tLayer
     * @return
     */
//    @DataScope(deptAlias = "layer", userAlias = "layer")
    public long selectCount(TLayer tLayer);

    //    @DataScope(deptAlias = "layer", userAlias = "layer")
    public TLayer selectTLayerOne(TLayer tLayer);

    //@DataScope(deptAlias = "layer", userAlias = "layer")
    public List<TLayer> selectTLayerBySetIdList(long layerSetId);

    /**
     * 新增图层
     *
     * @param tLayer 图层
     * @return 结果
     */
    public int insertTLayer(TLayer tLayer);

    /**
     * 修改图层
     *
     * @param tLayer 图层
     * @return 结果
     */
    public int updateTLayer(TLayer tLayer);

    /**
     * 删除图层
     *
     * @param id 图层主键
     * @return 结果
     */
    public int deleteTLayerById(Long id);

    /**
     * 批量删除图层
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTLayerByIds(Long[] ids);

    public List<String> getIntersection(@Param("tableName") String tableName, @Param("tagert") String tagert);

    List<GenTable> getLayerTable();

    /**
     * 查询所有坐标系统
     *
     * @return 坐标系统集合，每个元素是一个 Map，包含 srid、authName 等字段
     */
    public List<Map<String, Object>> getAllSpatialRefSystems();


    /**
     * 查询与多边形相交的几何对象
     *
     * @param tableName 表名
     * @param targetGeoJson 目标几何对象（Java 对象）
     * @param srid 空间参考系 ID
     * @return 相交的几何对象列表（Geometry 类型）
     */
    public List<String> getIntersectionExt(
             @Param("tableName") String tableName,
             @Param("targetGeoJson") String targetGeoJson,
             @Param("srid") Integer srid
    );
}
