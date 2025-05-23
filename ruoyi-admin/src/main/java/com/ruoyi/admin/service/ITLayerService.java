package com.ruoyi.admin.service;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.bo.DxfByTargetBo;
import com.ruoyi.admin.domain.bo.PublishBo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.generator.domain.GenTable;
import org.apache.ibatis.annotations.Param;
import com.vividsolutions.jts.geom.Geometry;

import java.util.List;
import java.util.Map;

/**
 * 图层Service接口
 *
 * @author ruoyi
 * @date 2024-10-08
 */
public interface ITLayerService {
    /**
     * 查询图层
     *
     * @param id 图层主键
     * @return 图层
     */
    public TLayer selectTLayerById(Long id);

    /**
     * 查询图层列表
     *
     * @param tLayer 图层
     * @return 图层集合
     */
    public List<TLayer> selectTLayerList(TLayer tLayer);

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
     * 批量删除图层
     *
     * @param ids 需要删除的图层主键集合
     * @return 结果
     */
    public int deleteTLayerByIds(Long[] ids);

    /**
     * 删除图层信息
     *
     * @param id 图层主键
     * @return 结果
     */
    public int deleteTLayerById(Long id);

    public AjaxResult getDxfByTarget(DxfByTargetBo bo);

    List<GenTable> getLayerTable();

    AjaxResult publishLayer(PublishBo bo);

    public AjaxResult getAllSpatialRefSystems();

    List<String> getIntersectionExt(
           String tableName,
           String targetGeoJson,
           Integer  srid
    );
}
