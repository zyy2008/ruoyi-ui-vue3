package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.bo.PublishBo;
import com.ruoyi.admin.service.ITLayerService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.util.GeoServerHttpClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图层Controller
 *
 * @author ruoyi
 * @date 2024-10-08
 */
@Api("图层")
@RestController
@RequestMapping("/admin/layer")
public class TLayerController extends BaseController {
    @Autowired
    private ITLayerService tLayerService;

    /**
     * 查询图层列表
     */
    @ApiOperation("查询图层列表")
    @PreAuthorize("@ss.hasPermi('admin:layer:list')")
    @GetMapping("/list")
    public AjaxResult list(TLayer tLayer) {
        List<TLayer> list = tLayerService.selectTLayerList(tLayer);
        return success(list);
    }

    /**
     * 获取图层详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:layer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tLayerService.selectTLayerById(id));
    }

    /**
     * 新增图层
     */
    @PreAuthorize("@ss.hasPermi('admin:layer:add')")
    @Log(title = "图层", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLayer tLayer) {
        return toAjax(tLayerService.insertTLayer(tLayer));
    }

    /**
     * 修改图层
     */
    @PreAuthorize("@ss.hasPermi('admin:layer:edit')")
    @Log(title = "图层", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLayer tLayer) {
        return toAjax(tLayerService.updateTLayer(tLayer));
    }

    /**
     * 删除图层
     */
    @PreAuthorize("@ss.hasPermi('admin:layer:remove')")
    @Log(title = "图层", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tLayerService.deleteTLayerByIds(ids));
    }

    /**
     * 查询图层表名
     */
    @GetMapping("/getLayerTable")
    public TableDataInfo getLayerTable() {
        return getDataTable(tLayerService.getLayerTable());
    }

    /**
     * 获取样式列表
     * @return
     */
    @GetMapping("/getLayerStyle")
    public AjaxResult getLayerStyle() {
        List<String> globalStyles = GeoServerHttpClient.getStyleList(null);
        return AjaxResult.success(globalStyles);
    }

    /**
     * 图层发布
     */
    @Log(title = "图层", businessType = BusinessType.INSERT)
    @PostMapping("/publishLayer")
    public AjaxResult publishLayer(@RequestBody PublishBo bo) {
        if (null == bo || null == bo.getPublishLayer() || bo.getPublishLayer().isEmpty())
            return AjaxResult.error("参数异常");
        return tLayerService.publishLayer(bo);
//        return AjaxResult.success();
    }

}
