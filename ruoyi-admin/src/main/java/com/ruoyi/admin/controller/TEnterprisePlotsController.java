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
import com.ruoyi.admin.domain.TEnterprisePlots;
import com.ruoyi.admin.service.ITEnterprisePlotsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 在产企业地块信息Controller
 *
 * @author ruoyi
 * @date 2025-05-26
 */
@Api(tags = "在产企业地块信息")
@RestController
@RequestMapping("/admin/enterprise")
public class TEnterprisePlotsController extends BaseController {
    @Autowired
    private ITEnterprisePlotsService tEnterprisePlotsService;

    /**
     * 查询在产企业地块信息列表
     */
    @ApiOperation("查询在产企业地块信息列表")
    @PreAuthorize("@ss.hasPermi('admin:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEnterprisePlots tEnterprisePlots) {
        startPage();
        List<TEnterprisePlots> list = tEnterprisePlotsService.selectTEnterprisePlotsList(tEnterprisePlots);
        return getDataTable(list);
    }

    /**
     * 导出在产企业地块信息列表
     */
    @ApiOperation("导出在产企业地块信息列表")
    @PreAuthorize("@ss.hasPermi('admin:enterprise:export')")
    @Log(title = "在产企业地块信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TEnterprisePlots tEnterprisePlots) {
        List<TEnterprisePlots> list = tEnterprisePlotsService.selectTEnterprisePlotsList(tEnterprisePlots);
        ExcelUtil<TEnterprisePlots> util = new ExcelUtil<TEnterprisePlots>(TEnterprisePlots.class);
        util.exportExcel(response, list, "在产企业地块信息数据");
    }

    /**
     * 获取在产企业地块信息详细信息
     */
    @ApiOperation("获取在产企业地块信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tEnterprisePlotsService.selectTEnterprisePlotsById(id));
    }

    /**
     * 新增在产企业地块信息
     */
    @ApiOperation("新增在产企业地块信息")
    @PreAuthorize("@ss.hasPermi('admin:enterprise:add')")
    @Log(title = "在产企业地块信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TEnterprisePlots tEnterprisePlots) {
        return toAjax(tEnterprisePlotsService.insertTEnterprisePlots(tEnterprisePlots));
    }

    /**
     * 修改在产企业地块信息
     */
    @ApiOperation("修改在产企业地块信息")
    @PreAuthorize("@ss.hasPermi('admin:enterprise:edit')")
    @Log(title = "在产企业地块信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TEnterprisePlots tEnterprisePlots) {
        return toAjax(tEnterprisePlotsService.updateTEnterprisePlots(tEnterprisePlots));
    }

    /**
     * 删除在产企业地块信息
     */
    @ApiOperation("删除在产企业地块信息")
    @PreAuthorize("@ss.hasPermi('admin:enterprise:remove')")
    @Log(title = "在产企业地块信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tEnterprisePlotsService.deleteTEnterprisePlotsByIds(ids));
    }
}
