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
import com.ruoyi.admin.domain.TRealtimeWaterQualityMonitoring;
import com.ruoyi.admin.service.ITRealtimeWaterQualityMonitoringService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实时数据监测Controller
 *
 * @author ruoyi
 * @date 2025-06-13
 */
@Api(tags = "实时数据监测")
@RestController
@RequestMapping("/admin/realtimeWaterQualityMonitoring")
public class TRealtimeWaterQualityMonitoringController extends BaseController {
    @Autowired
    private ITRealtimeWaterQualityMonitoringService tRealtimeWaterQualityMonitoringService;

    /**
     * 查询实时数据监测列表
     */
    @ApiOperation("查询实时数据监测列表")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:list')")
    @GetMapping("/list")
    public TableDataInfo list(TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        startPage();
        List<TRealtimeWaterQualityMonitoring> list = tRealtimeWaterQualityMonitoringService.selectTRealtimeWaterQualityMonitoringList(tRealtimeWaterQualityMonitoring);
        return getDataTable(list);
    }

    /**
     * 通过部门获取监测井最新数据
     */
    @ApiOperation("通过部门获取监测井最新数据")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:list')")
    @GetMapping("/getBatchData/{deptId}")
    public AjaxResult getBatchData(@PathVariable("deptId") String deptId) {
        return AjaxResult.success(tRealtimeWaterQualityMonitoringService.getBatchData(deptId));
    }

    /**
     * 导出实时数据监测列表
     */
    @ApiOperation("导出实时数据监测列表")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:export')")
    @Log(title = "实时数据监测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        List<TRealtimeWaterQualityMonitoring> list = tRealtimeWaterQualityMonitoringService.selectTRealtimeWaterQualityMonitoringList(tRealtimeWaterQualityMonitoring);
        ExcelUtil<TRealtimeWaterQualityMonitoring> util = new ExcelUtil<TRealtimeWaterQualityMonitoring>(TRealtimeWaterQualityMonitoring.class);
        util.exportExcel(response, list, "实时数据监测数据");
    }

    /**
     * 获取实时数据监测详细信息
     */
    @ApiOperation("获取实时数据监测详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(tRealtimeWaterQualityMonitoringService.selectTRealtimeWaterQualityMonitoringById(id));
    }

    /**
     * 新增实时数据监测
     */
    @ApiOperation("新增实时数据监测")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:add')")
    @Log(title = "实时数据监测", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        return toAjax(tRealtimeWaterQualityMonitoringService.insertTRealtimeWaterQualityMonitoring(tRealtimeWaterQualityMonitoring));
    }

    /**
     * 修改实时数据监测
     */
    @ApiOperation("修改实时数据监测")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:edit')")
    @Log(title = "实时数据监测", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring) {
        return toAjax(tRealtimeWaterQualityMonitoringService.updateTRealtimeWaterQualityMonitoring(tRealtimeWaterQualityMonitoring));
    }

    /**
     * 删除实时数据监测
     */
    @ApiOperation("删除实时数据监测")
    //@PreAuthorize("@ss.hasPermi('admin:realtimeWaterQualityMonitoring:remove')")
    @Log(title = "实时数据监测", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(tRealtimeWaterQualityMonitoringService.deleteTRealtimeWaterQualityMonitoringByIds(ids));
    }
}
