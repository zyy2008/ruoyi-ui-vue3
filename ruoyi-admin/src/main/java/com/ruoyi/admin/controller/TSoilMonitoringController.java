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
import com.ruoyi.admin.domain.TSoilMonitoring;
import com.ruoyi.admin.service.ITSoilMonitoringService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 土壤环境调查监测信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "土壤环境调查监测信息")
@RestController
@RequestMapping("/admin/soilMonitoring")
public class TSoilMonitoringController extends BaseController {
    @Autowired
    private ITSoilMonitoringService tSoilMonitoringService;

    /**
     * 查询土壤环境调查监测信息列表
     */
    @ApiOperation("查询土壤环境调查监测信息列表")
    @PreAuthorize("@ss.hasPermi('admin:monitoring:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSoilMonitoring tSoilMonitoring) {
        startPage();
        List<TSoilMonitoring> list = tSoilMonitoringService.selectTSoilMonitoringList(tSoilMonitoring);
        return getDataTable(list);
    }

    /**
     * 导出土壤环境调查监测信息列表
     */
    @ApiOperation("导出土壤环境调查监测信息列表")
    @PreAuthorize("@ss.hasPermi('admin:monitoring:export')")
    @Log(title = "土壤环境调查监测信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSoilMonitoring tSoilMonitoring) {
        List<TSoilMonitoring> list = tSoilMonitoringService.selectTSoilMonitoringList(tSoilMonitoring);
        ExcelUtil<TSoilMonitoring> util = new ExcelUtil<TSoilMonitoring>(TSoilMonitoring.class);
        util.exportExcel(response, list, "土壤环境调查监测信息数据");
    }

    /**
     * 获取土壤环境调查监测信息详细信息
     */
    @ApiOperation("获取土壤环境调查监测信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:monitoring:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSoilMonitoringService.selectTSoilMonitoringById(id));
    }

    /**
     * 新增土壤环境调查监测信息
     */
    @ApiOperation("新增土壤环境调查监测信息")
    @PreAuthorize("@ss.hasPermi('admin:monitoring:add')")
    @Log(title = "土壤环境调查监测信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSoilMonitoring tSoilMonitoring) {
        return toAjax(tSoilMonitoringService.insertTSoilMonitoring(tSoilMonitoring));
    }

    /**
     * 修改土壤环境调查监测信息
     */
    @ApiOperation("修改土壤环境调查监测信息")
    @PreAuthorize("@ss.hasPermi('admin:monitoring:edit')")
    @Log(title = "土壤环境调查监测信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSoilMonitoring tSoilMonitoring) {
        return toAjax(tSoilMonitoringService.updateTSoilMonitoring(tSoilMonitoring));
    }

    /**
     * 删除土壤环境调查监测信息
     */
    @ApiOperation("删除土壤环境调查监测信息")
    @PreAuthorize("@ss.hasPermi('admin:monitoring:remove')")
    @Log(title = "土壤环境调查监测信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSoilMonitoringService.deleteTSoilMonitoringByIds(ids));
    }
}
