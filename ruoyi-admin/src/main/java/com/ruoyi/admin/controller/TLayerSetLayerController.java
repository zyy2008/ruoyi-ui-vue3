package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TLayerSetLayer;
import com.ruoyi.admin.domain.bo.TLayerSetLayerBo;
import com.ruoyi.admin.service.ITLayerSetLayerService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图层集与图层的关联Controller
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/admin/layerRelationSet")
public class TLayerSetLayerController extends BaseController {
    @Autowired
    private ITLayerSetLayerService tLayerSetLayerService;

    /**
     * 查询图层集与图层的关联列表
     */
    @PreAuthorize("@ss.hasPermi('admin:layerRelationSet:list')")
    @GetMapping("/list")
    public TableDataInfo list(TLayerSetLayer tLayerSetLayer) {
        startPage();
        List<TLayerSetLayer> list = tLayerSetLayerService.selectTLayerSetLayerList(tLayerSetLayer);
        return getDataTable(list);
    }


    @PostMapping("/relation")
    public R<Void> relation(@RequestBody TLayerSetLayerBo tLayerSetLayerBo) {
        Long layerSetId = tLayerSetLayerBo.getLayerSetId();
        List<Long> layerIds = tLayerSetLayerBo.getLayerId();

        // 判断 layerSetId 是否为空
        if (layerSetId == null) {
            return R.fail("图层集不能为空");
        }
        // 判断 layerIds 是否为空或空列表
//        if (layerIds == null || layerIds.isEmpty()) {
//            return R.fail("图层 不能为空或空列表");
//        }

        return tLayerSetLayerService.insertRelation(tLayerSetLayerBo);
    }

    /**
     * 获取图层集与图层的关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:layerRelationSet:query')")
    @GetMapping(value = "/{layerSetId}")
    public AjaxResult getInfo(@PathVariable("layerSetId") Long layerSetId) {
        return success(tLayerSetLayerService.selectTLayerSetLayerByLayerSetId(layerSetId));
    }

    /**
     * 新增图层集与图层的关联
     */
    @PreAuthorize("@ss.hasPermi('admin:layerRelationSet:add')")
    @Log(title = "图层集与图层的关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLayerSetLayer tLayerSetLayer) {
        return toAjax(tLayerSetLayerService.insertTLayerSetLayer(tLayerSetLayer));
    }

    /**
     * 修改图层集与图层的关联
     */
    @PreAuthorize("@ss.hasPermi('admin:layerRelationSet:edit')")
    @Log(title = "图层集与图层的关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLayerSetLayer tLayerSetLayer) {
        return toAjax(tLayerSetLayerService.updateTLayerSetLayer(tLayerSetLayer));
    }

    /**
     * 删除图层集与图层的关联
     */
    @PreAuthorize("@ss.hasPermi('admin:layerRelationSet:remove')")
    @Log(title = "图层集与图层的关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{layerSetIds}")
    public AjaxResult remove(@PathVariable Long[] layerSetIds) {
        return toAjax(tLayerSetLayerService.deleteTLayerSetLayerByLayerSetIds(layerSetIds));
    }
}
