package com.ruoyi.admin.service;

import com.ruoyi.admin.domain.TLayerSet;
import com.ruoyi.admin.domain.bo.AxisBo;
import com.ruoyi.admin.domain.bo.LayerSetShareBo;
import com.ruoyi.admin.domain.bo.ShortestPathBo;
import com.ruoyi.admin.domain.bo.TLayerSetBo;
import com.ruoyi.admin.domain.vo.AxisVo;
import com.ruoyi.admin.domain.vo.ShortestPathVo;
import com.ruoyi.admin.domain.vo.TBaseVo;
import com.ruoyi.admin.domain.vo.TLayerSetVo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;

import java.util.List;

/**
 * 图层集Service接口
 *
 * @author ruoyi
 * @date 2024-10-26
 */
public interface ITLayerSetService {
    /**
     * 查询图层集
     *
     * @param id 图层集主键
     * @return 图层集
     */
    public TLayerSetVo selectTLayerSetById(Long id);

    /**
     * 查询图层集列表
     *
     * @param tLayerSet 图层集
     * @return 图层集集合
     */
    public List<TLayerSet> selectTLayerSetList(TLayerSet tLayerSet);

    public List<TLayerSetVo> selectTLayerSetVoList(TLayerSet tLayerSet);

    /**
     * 新增图层集
     *
     * @param tLayerSet 图层集
     * @return 结果
     */
    public AjaxResult insertTLayerSet(TLayerSetBo tLayerSet);

    /**
     * 修改图层集
     *
     * @return 结果
     */
    public AjaxResult updateTLayerSet(TLayerSetBo tLayerSetBo);

    /**
     * 批量删除图层集
     *
     * @param ids 需要删除的图层集主键集合
     * @return 结果
     */
    public int deleteTLayerSetByIds(Long[] ids);

    /**
     * 删除图层集信息
     *
     * @param id 图层集主键
     * @return 结果
     */
    public int deleteTLayerSetById(Long id);

    /**
     * 分享图层集
     */
    public R<Void> share(LayerSetShareBo bo);

    /**
     * 查询最短路网路径
     */
    R<List<ShortestPathVo>> getShortestPath(ShortestPathBo bo);

    /**
     * 坐标转换
     */
    AxisVo axisConvert(AxisBo bo);

    /**
     * 查询图层组合树形结构
     */
    List<TBaseVo> layerTree(Long[] baseId);
}
