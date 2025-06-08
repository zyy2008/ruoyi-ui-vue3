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
import com.ruoyi.admin.domain.TPollutionIncident;
import com.ruoyi.admin.service.ITPollutionIncidentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 环境污染事故发生情况Controller
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Api(tags = "环境污染事故发生情况")
@RestController
@RequestMapping("/admin/pollutionIncident")
public class TPollutionIncidentController extends BaseController {
    @Autowired
    private ITPollutionIncidentService tPollutionIncidentService;

    /**
     * 查询环境污染事故发生情况列表
     */
    @ApiOperation("查询环境污染事故发生情况列表")
    //@PreAuthorize("@ss.hasPermi('admin:pollutionIncident:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPollutionIncident tPollutionIncident) {
        startPage();
        List<TPollutionIncident> list = tPollutionIncidentService.selectTPollutionIncidentList(tPollutionIncident);
        return getDataTable(list);
    }

    /**
     * 导出环境污染事故发生情况列表
     */
    @ApiOperation("导出环境污染事故发生情况列表")
    //@PreAuthorize("@ss.hasPermi('admin:pollutionIncident:export')")
    @Log(title = "环境污染事故发生情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPollutionIncident tPollutionIncident) {
        List<TPollutionIncident> list = tPollutionIncidentService.selectTPollutionIncidentList(tPollutionIncident);
        ExcelUtil<TPollutionIncident> util = new ExcelUtil<TPollutionIncident>(TPollutionIncident.class);
        util.exportExcel(response, list, "环境污染事故发生情况数据");
    }

    /**
     * 获取环境污染事故发生情况详细信息
     */
    @ApiOperation("获取环境污染事故发生情况详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:pollutionIncident:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tPollutionIncidentService.selectTPollutionIncidentById(id));
    }

    /**
     * 新增环境污染事故发生情况
     */
    @ApiOperation("新增环境污染事故发生情况")
    //@PreAuthorize("@ss.hasPermi('admin:pollutionIncident:add')")
    @Log(title = "环境污染事故发生情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPollutionIncident tPollutionIncident) {
        return toAjax(tPollutionIncidentService.insertTPollutionIncident(tPollutionIncident));
    }

    /**
     * 修改环境污染事故发生情况
     */
    @ApiOperation("修改环境污染事故发生情况")
    //@PreAuthorize("@ss.hasPermi('admin:pollutionIncident:edit')")
    @Log(title = "环境污染事故发生情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPollutionIncident tPollutionIncident) {
        return toAjax(tPollutionIncidentService.updateTPollutionIncident(tPollutionIncident));
    }

    /**
     * 删除环境污染事故发生情况
     */
    @ApiOperation("删除环境污染事故发生情况")
    //@PreAuthorize("@ss.hasPermi('admin:pollutionIncident:remove')")
    @Log(title = "环境污染事故发生情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tPollutionIncidentService.deleteTPollutionIncidentByIds(ids));
    }
}
