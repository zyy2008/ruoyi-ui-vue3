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
import com.ruoyi.admin.domain.TMonitoringWells;
import com.ruoyi.admin.service.ITMonitoringWellsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 园区监测井信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "园区监测井信息")
@RestController
@RequestMapping("/admin/wells")
public class TMonitoringWellsController extends BaseController {
    @Autowired
    private ITMonitoringWellsService tMonitoringWellsService;


    @ApiOperation("查询园区所有监测井信息列表")
    @PreAuthorize("@ss.hasPermi('admin:wells:list')")
    @GetMapping("/all")
    public TableDataInfo all(TMonitoringWells tMonitoringWells) {
        List<TMonitoringWells> list = tMonitoringWellsService.selectTMonitoringWellsList(tMonitoringWells);
        return getDataTable(list);
    }

    /**
     * 查询园区监测井信息列表
     */
    @ApiOperation("查询园区监测井信息列表")
    @PreAuthorize("@ss.hasPermi('admin:wells:list')")
    @GetMapping("/list")
    public TableDataInfo list(TMonitoringWells tMonitoringWells) {
        startPage();
        List<TMonitoringWells> list = tMonitoringWellsService.selectTMonitoringWellsList(tMonitoringWells);
        return getDataTable(list);
    }

    /**
     * 导出园区监测井信息列表
     */
    @ApiOperation("导出园区监测井信息列表")
    @PreAuthorize("@ss.hasPermi('admin:wells:export')")
    @Log(title = "园区监测井信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMonitoringWells tMonitoringWells) {
        List<TMonitoringWells> list = tMonitoringWellsService.selectTMonitoringWellsList(tMonitoringWells);
        ExcelUtil<TMonitoringWells> util = new ExcelUtil<TMonitoringWells>(TMonitoringWells.class);
        util.exportExcel(response, list, "园区监测井信息数据");
    }

    /**
     * 获取园区监测井信息详细信息
     */
    @ApiOperation("获取园区监测井信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:wells:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tMonitoringWellsService.selectTMonitoringWellsById(id));
    }

    /**
     * 新增园区监测井信息
     */
    @ApiOperation("新增园区监测井信息")
    @PreAuthorize("@ss.hasPermi('admin:wells:add')")
    @Log(title = "园区监测井信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMonitoringWells tMonitoringWells) {
        return toAjax(tMonitoringWellsService.insertTMonitoringWells(tMonitoringWells));
    }

    /**
     * 修改园区监测井信息
     */
    @ApiOperation("修改园区监测井信息")
    @PreAuthorize("@ss.hasPermi('admin:wells:edit')")
    @Log(title = "园区监测井信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMonitoringWells tMonitoringWells) {
        return toAjax(tMonitoringWellsService.updateTMonitoringWells(tMonitoringWells));
    }

    /**
     * 删除园区监测井信息
     */
    @ApiOperation("删除园区监测井信息")
    @PreAuthorize("@ss.hasPermi('admin:wells:remove')")
    @Log(title = "园区监测井信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tMonitoringWellsService.deleteTMonitoringWellsByIds(ids));
    }
}
