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
import com.ruoyi.admin.domain.TGroundwaterMonitoring;
import com.ruoyi.admin.service.ITGroundwaterMonitoringService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地下水环境调查监测信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "地下水环境调查监测")
@RestController
@RequestMapping("/admin/groundwaterMonitoring")
public class TGroundwaterMonitoringController extends BaseController {
    @Autowired
    private ITGroundwaterMonitoringService tGroundwaterMonitoringService;

    /**
     * 查询地下水环境调查监测信息列表
     */
    @ApiOperation("查询地下水环境调查监测信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:monitoring:list')")
    @GetMapping("/list")
    public TableDataInfo list(TGroundwaterMonitoring tGroundwaterMonitoring) {
        startPage();
        List<TGroundwaterMonitoring> list = tGroundwaterMonitoringService.selectTGroundwaterMonitoringList(tGroundwaterMonitoring);
        return getDataTable(list);
    }

    /**
     * 导出地下水环境调查监测信息列表
     */
    @ApiOperation("导出地下水环境调查监测信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:monitoring:export')")
    @Log(title = "地下水环境调查监测信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TGroundwaterMonitoring tGroundwaterMonitoring) {
        List<TGroundwaterMonitoring> list = tGroundwaterMonitoringService.selectTGroundwaterMonitoringList(tGroundwaterMonitoring);
        ExcelUtil<TGroundwaterMonitoring> util = new ExcelUtil<TGroundwaterMonitoring>(TGroundwaterMonitoring.class);
        util.exportExcel(response, list, "地下水环境调查监测信息数据");
    }

    /**
     * 获取地下水环境调查监测信息详细信息
     */
    @ApiOperation("获取地下水环境调查监测信息详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:monitoring:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tGroundwaterMonitoringService.selectTGroundwaterMonitoringById(id));
    }

    /**
     * 新增地下水环境调查监测信息
     */
    @ApiOperation("新增地下水环境调查监测信息")
    //@PreAuthorize("@ss.hasPermi('admin:monitoring:add')")
    @Log(title = "地下水环境调查监测信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGroundwaterMonitoring tGroundwaterMonitoring) {
        return toAjax(tGroundwaterMonitoringService.insertTGroundwaterMonitoring(tGroundwaterMonitoring));
    }

    /**
     * 修改地下水环境调查监测信息
     */
    @ApiOperation("修改地下水环境调查监测信息")
    //@PreAuthorize("@ss.hasPermi('admin:monitoring:edit')")
    @Log(title = "地下水环境调查监测信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGroundwaterMonitoring tGroundwaterMonitoring) {
        return toAjax(tGroundwaterMonitoringService.updateTGroundwaterMonitoring(tGroundwaterMonitoring));
    }

    /**
     * 删除地下水环境调查监测信息
     */
    @ApiOperation("删除地下水环境调查监测信息")
    //@PreAuthorize("@ss.hasPermi('admin:monitoring:remove')")
    @Log(title = "地下水环境调查监测信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tGroundwaterMonitoringService.deleteTGroundwaterMonitoringByIds(ids));
    }
}
