package com.ruoyi.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.admin.domain.TLandUseHistory;
import com.ruoyi.admin.service.ITLandUseHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地块历史用途信息Controller
 *
 * @author ruoyi
 * @date 2025-06-11
 */
@Api(tags = "地块历史用途信息")
@RestController
@RequestMapping("/admin/landUseHistory")
public class TLandUseHistoryController extends BaseController {
    @Autowired
    private ITLandUseHistoryService tLandUseHistoryService;

    /**
     * 查询地块历史用途信息列表
     */
    @ApiOperation("查询地块历史用途信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:landUseHistory:list')")
    @GetMapping("/list")
    public TableDataInfo list(TLandUseHistory tLandUseHistory) {
        startPage();
        List<TLandUseHistory> list = tLandUseHistoryService.selectTLandUseHistoryList(tLandUseHistory);
        return getDataTable(list);
    }

    /**
     * 导出地块历史用途信息列表
     */
    @ApiOperation("导出地块历史用途信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:landUseHistory:export')")
    @Log(title = "地块历史用途信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TLandUseHistory tLandUseHistory) {
        List<TLandUseHistory> list = tLandUseHistoryService.selectTLandUseHistoryList(tLandUseHistory);
        ExcelUtil<TLandUseHistory> util = new ExcelUtil<TLandUseHistory>(TLandUseHistory.class);
        util.exportExcel(response, list, "地块历史用途信息数据");
    }

    /**
     * 获取地块历史用途信息详细信息
     */
    @ApiOperation("获取地块历史用途信息详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:landUseHistory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tLandUseHistoryService.selectTLandUseHistoryById(id));
    }

    /**
     * 新增地块历史用途信息
     */
    @ApiOperation("新增地块历史用途信息")
    //@PreAuthorize("@ss.hasPermi('admin:landUseHistory:add')")
    @Log(title = "地块历史用途信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLandUseHistory tLandUseHistory) {
        return toAjax(tLandUseHistoryService.insertTLandUseHistory(tLandUseHistory));
    }

    /**
     * 修改地块历史用途信息
     */
    @ApiOperation("修改地块历史用途信息")
    //@PreAuthorize("@ss.hasPermi('admin:landUseHistory:edit')")
    @Log(title = "地块历史用途信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLandUseHistory tLandUseHistory) {
        return toAjax(tLandUseHistoryService.updateTLandUseHistory(tLandUseHistory));
    }

    /**
     * 删除地块历史用途信息
     */
    @ApiOperation("删除地块历史用途信息")
    //@PreAuthorize("@ss.hasPermi('admin:landUseHistory:remove')")
    @Log(title = "地块历史用途信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tLandUseHistoryService.deleteTLandUseHistoryByIds(ids));
    }
}
