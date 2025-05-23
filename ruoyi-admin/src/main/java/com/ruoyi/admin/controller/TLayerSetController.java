package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TLayerSet;
import com.ruoyi.admin.domain.bo.AxisBo;
import com.ruoyi.admin.domain.bo.LayerSetShareBo;
import com.ruoyi.admin.domain.bo.ShortestPathBo;
import com.ruoyi.admin.domain.bo.TLayerSetBo;
import com.ruoyi.admin.domain.vo.AxisVo;
import com.ruoyi.admin.domain.vo.ShortestPathVo;
import com.ruoyi.admin.domain.vo.TLayerSetVo;
import com.ruoyi.admin.service.ITLayerSetService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图层集Controller
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/admin/set")
public class TLayerSetController extends BaseController {
    @Autowired
    private ITLayerSetService tLayerSetService;

    /**
     * 查询图层集列表
     */
    @PreAuthorize("@ss.hasPermi('admin:set:list')")
    @GetMapping("/list")
    public AjaxResult list(TLayerSet tLayerSet) {
        List<TLayerSetVo> list = tLayerSetService.selectTLayerSetVoList(tLayerSet);
        return success(list);
    }

    /**
     * 获取图层集详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:set:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tLayerSetService.selectTLayerSetById(id));
    }

    /**
     * 新增图层集
     */
    @PreAuthorize("@ss.hasPermi('admin:set:add')")
    @Log(title = "图层集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLayerSetBo tLayerSet) {
        if (null == tLayerSet) return AjaxResult.error("参数异常");
        if (null == tLayerSet.getPid()) return AjaxResult.error("父节点不能为空");
        return tLayerSetService.insertTLayerSet(tLayerSet);
    }

    /**
     * 修改图层集
     */
    @PreAuthorize("@ss.hasPermi('admin:set:edit')")
    @Log(title = "图层集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLayerSetBo tLayerSetBo) {
        if (null == tLayerSetBo) return AjaxResult.error("参数异常");
        if (null == tLayerSetBo.getPid()) return AjaxResult.error("父节点不能为空");
        return tLayerSetService.updateTLayerSet(tLayerSetBo);
    }

    /**
     * 删除图层集
     */
    @PreAuthorize("@ss.hasPermi('admin:set:remove')")
    @Log(title = "图层集", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tLayerSetService.deleteTLayerSetByIds(ids));
    }

    /**
     * 分享地图集
     */
    @Log(title = "图层集", businessType = BusinessType.UPDATE)
    @PostMapping("/share")
    public R<Void> share(@RequestBody LayerSetShareBo bo) {
        if (null == bo) return R.fail("参数异常");
        if (null == bo.getLayerSetId()) return R.fail("图层集不能为空");
        if ((null == bo.getDeptId() || 0 == bo.getDeptId().length)
                && (null == bo.getUserId() || 0 == bo.getUserId().length))
            return R.fail("至少选择一个部门或工作人员");
        return tLayerSetService.share(bo);
    }


    /**
     * 查询最短路网路径
     */
    @PostMapping("/getShortestPath")
    public R<List<ShortestPathVo>> getShortestPath(@RequestBody ShortestPathBo bo) {
        if (null == bo || null == bo.getStartAxisX() || null == bo.getStartAxisY()
                || null == bo.getEndAxisX() || null == bo.getEndAxisY())
            return R.fail("参数异常");
        return tLayerSetService.getShortestPath(bo);
    }

    /**
     * 坐标转换接口
     */
    @PostMapping("/axisConvert")
    public R<AxisVo> axisConvert(@RequestBody AxisBo bo) {
        if (null == bo || null == bo.getAxisX() || null == bo.getAxisY()
                || null == bo.getOldCoordinate() || null == bo.getNewCoordinate())
            return R.fail("参数异常");
        return R.ok(tLayerSetService.axisConvert(bo));
    }


    /**
     * 查询图层组合树形结构
     */
    @GetMapping("/layerTree")
    public AjaxResult layerTree(Long[] baseId) {
        return AjaxResult.success("success", tLayerSetService.layerTree(baseId));
    }
}
