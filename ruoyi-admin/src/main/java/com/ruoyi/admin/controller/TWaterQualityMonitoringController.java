package com.ruoyi.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.admin.domain.TSoilPollutant;
import com.ruoyi.admin.domain.TWaterQualityMonitoring;
import com.ruoyi.admin.service.ITWaterQualityMonitoringService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
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


/**
 * 【监测数据】Controller
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
@RestController
@RequestMapping("/system/monitoring")
public class TWaterQualityMonitoringController extends BaseController
{
    @Autowired
    private ITWaterQualityMonitoringService tWaterQualityMonitoringService;

    /**
     * 查询【监测数据】列表
     */
    @PreAuthorize("@ss.hasPermi('system:monitoring:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWaterQualityMonitoring tWaterQualityMonitoring) {
        startPage();
        List<TWaterQualityMonitoring> list = tWaterQualityMonitoringService.selectTWaterQualityMonitoringList(tWaterQualityMonitoring);
        return getDataTable(list);
    }

    /**
     * 导出【监测数据】列表
     */
    @PreAuthorize("@ss.hasPermi('system:monitoring:export')")
    @Log(title = "【监测数据】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        List<TWaterQualityMonitoring> list = tWaterQualityMonitoringService.selectTWaterQualityMonitoringList(tWaterQualityMonitoring);
        ExcelUtil<TWaterQualityMonitoring> util = new ExcelUtil<TWaterQualityMonitoring>(TWaterQualityMonitoring.class);
        util.exportExcel(response, list, "【监测数据】数据");
    }

    /**
     * 获取【监测数据】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:monitoring:query')")
    @GetMapping(value = "/{pointId}")
    public AjaxResult getInfo(@PathVariable("pointId") String pointId)
    {
        return success(tWaterQualityMonitoringService.selectTWaterQualityMonitoringByPointId(pointId));
    }

    /**
     * 新增【监测数据】
     */
    @PreAuthorize("@ss.hasPermi('system:monitoring:add')")
    @Log(title = "【监测数据】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return toAjax(tWaterQualityMonitoringService.insertTWaterQualityMonitoring(tWaterQualityMonitoring));
    }

    /**
     * 修改【监测数据】
     */
    @PreAuthorize("@ss.hasPermi('system:monitoring:edit')")
    @Log(title = "【监测数据】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return toAjax(tWaterQualityMonitoringService.updateTWaterQualityMonitoring(tWaterQualityMonitoring));
    }

    /**
     * 删除【监测数据】
     */
    @PreAuthorize("@ss.hasPermi('system:monitoring:remove')")
    @Log(title = "【监测数据】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pointIds}")
    public AjaxResult remove(@PathVariable String[] pointIds)
    {
        return toAjax(tWaterQualityMonitoringService.deleteTWaterQualityMonitoringByPointIds(pointIds));
    }
}
