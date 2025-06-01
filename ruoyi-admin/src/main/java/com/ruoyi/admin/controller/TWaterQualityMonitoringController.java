package com.ruoyi.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.admin.domain.TWaterQualityMonitoring;
import com.ruoyi.admin.service.ITWaterQualityMonitoringService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 监测数据管理Controller
 *
 * @author ruoyi
 * @date 2025-06-01
 */
@RestController
@RequestMapping("/admin/monitoring")
public class TWaterQualityMonitoringController extends BaseController
{
    @Autowired
    private ITWaterQualityMonitoringService tWaterQualityMonitoringService;

    /**
     * 查询监测数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:monitoring:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        startPage();
        List<TWaterQualityMonitoring> list = tWaterQualityMonitoringService.selectTWaterQualityMonitoringList(tWaterQualityMonitoring);
        return getDataTable(list);
    }

    /**
     * 导出监测数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:monitoring:export')")
    @Log(title = "监测数据管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        List<TWaterQualityMonitoring> list = tWaterQualityMonitoringService.selectTWaterQualityMonitoringList(tWaterQualityMonitoring);
        ExcelUtil<TWaterQualityMonitoring> util = new ExcelUtil<TWaterQualityMonitoring>(TWaterQualityMonitoring.class);
        util.exportExcel(response, list, "监测数据管理数据");
    }

    /**
     * 获取监测数据管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:monitoring:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tWaterQualityMonitoringService.selectTWaterQualityMonitoringById(id));
    }

    /**
     * 新增监测数据管理
     */
    @PreAuthorize("@ss.hasPermi('admin:monitoring:add')")
    @Log(title = "监测数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return toAjax(tWaterQualityMonitoringService.insertTWaterQualityMonitoring(tWaterQualityMonitoring));
    }

    /**
     * 修改监测数据管理
     */
    @PreAuthorize("@ss.hasPermi('admin:monitoring:edit')")
    @Log(title = "监测数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWaterQualityMonitoring tWaterQualityMonitoring)
    {
        return toAjax(tWaterQualityMonitoringService.updateTWaterQualityMonitoring(tWaterQualityMonitoring));
    }

    /**
     * 删除监测数据管理
     */
    @PreAuthorize("@ss.hasPermi('admin:monitoring:remove')")
    @Log(title = "监测数据管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tWaterQualityMonitoringService.deleteTWaterQualityMonitoringByIds(ids));
    }
}
