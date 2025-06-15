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
import com.ruoyi.admin.domain.TSuspectedPollutionTrace;
import com.ruoyi.admin.service.ITSuspectedPollutionTraceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 可疑污染源或污染痕迹Controller
 *
 * @author ruoyi
 * @date 2025-06-09
 */
@Api(tags = "可疑污染源或污染痕迹")
@RestController
@RequestMapping("/admin/suspectedPollutionTrace")
public class TSuspectedPollutionTraceController extends BaseController {
    @Autowired
    private ITSuspectedPollutionTraceService tSuspectedPollutionTraceService;

    /**
     * 查询可疑污染源或污染痕迹列表
     */
    @ApiOperation("查询可疑污染源或污染痕迹列表")
    //@PreAuthorize("@ss.hasPermi('admin:suspectedPollutionTrace:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSuspectedPollutionTrace tSuspectedPollutionTrace) {
        startPage();
        List<TSuspectedPollutionTrace> list = tSuspectedPollutionTraceService.selectTSuspectedPollutionTraceList(tSuspectedPollutionTrace);
        return getDataTable(list);
    }

    /**
     * 导出可疑污染源或污染痕迹列表
     */
    @ApiOperation("导出可疑污染源或污染痕迹列表")
    //@PreAuthorize("@ss.hasPermi('admin:suspectedPollutionTrace:export')")
    @Log(title = "可疑污染源或污染痕迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSuspectedPollutionTrace tSuspectedPollutionTrace) {
        List<TSuspectedPollutionTrace> list = tSuspectedPollutionTraceService.selectTSuspectedPollutionTraceList(tSuspectedPollutionTrace);
        ExcelUtil<TSuspectedPollutionTrace> util = new ExcelUtil<TSuspectedPollutionTrace>(TSuspectedPollutionTrace.class);
        util.exportExcel(response, list, "可疑污染源或污染痕迹数据");
    }

    /**
     * 获取可疑污染源或污染痕迹详细信息
     */
    @ApiOperation("获取可疑污染源或污染痕迹详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:suspectedPollutionTrace:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSuspectedPollutionTraceService.selectTSuspectedPollutionTraceById(id));
    }

    /**
     * 新增可疑污染源或污染痕迹
     */
    @ApiOperation("新增可疑污染源或污染痕迹")
    //@PreAuthorize("@ss.hasPermi('admin:suspectedPollutionTrace:add')")
    @Log(title = "可疑污染源或污染痕迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSuspectedPollutionTrace tSuspectedPollutionTrace) {
        return toAjax(tSuspectedPollutionTraceService.insertTSuspectedPollutionTrace(tSuspectedPollutionTrace));
    }

    /**
     * 修改可疑污染源或污染痕迹
     */
    @ApiOperation("修改可疑污染源或污染痕迹")
    //@PreAuthorize("@ss.hasPermi('admin:suspectedPollutionTrace:edit')")
    @Log(title = "可疑污染源或污染痕迹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSuspectedPollutionTrace tSuspectedPollutionTrace) {
        return toAjax(tSuspectedPollutionTraceService.updateTSuspectedPollutionTrace(tSuspectedPollutionTrace));
    }

    /**
     * 删除可疑污染源或污染痕迹
     */
    @ApiOperation("删除可疑污染源或污染痕迹")
    //@PreAuthorize("@ss.hasPermi('admin:suspectedPollutionTrace:remove')")
    @Log(title = "可疑污染源或污染痕迹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSuspectedPollutionTraceService.deleteTSuspectedPollutionTraceByIds(ids));
    }
}
