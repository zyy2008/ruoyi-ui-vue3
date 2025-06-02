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
import com.ruoyi.admin.domain.TIndicators;
import com.ruoyi.admin.service.ITIndicatorsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 指标分类Controller
 *
 * @author ruoyi
 * @date 2025-06-02
 */
@Api(tags = "指标分类")
@RestController
@RequestMapping("/admin/indicators")
public class TIndicatorsController extends BaseController {
    @Autowired
    private ITIndicatorsService tIndicatorsService;

    /**
     * 查询指标分类列表
     */
    @ApiOperation("查询指标分类列表")
    //@PreAuthorize("@ss.hasPermi('admin:indicators:list')")
    @GetMapping("/list")
    public TableDataInfo list(TIndicators tIndicators) {
        startPage();
        List<TIndicators> list = tIndicatorsService.selectTIndicatorsList(tIndicators);
        return getDataTable(list);
    }

    /**
     * 导出指标分类列表
     */
    @ApiOperation("导出指标分类列表")
    //@PreAuthorize("@ss.hasPermi('admin:indicators:export')")
    @Log(title = "指标分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TIndicators tIndicators) {
        List<TIndicators> list = tIndicatorsService.selectTIndicatorsList(tIndicators);
        ExcelUtil<TIndicators> util = new ExcelUtil<TIndicators>(TIndicators.class);
        util.exportExcel(response, list, "指标分类数据");
    }

    /**
     * 获取指标分类详细信息
     */
    @ApiOperation("获取指标分类详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:indicators:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tIndicatorsService.selectTIndicatorsById(id));
    }

    /**
     * 新增指标分类
     */
    @ApiOperation("新增指标分类")
    //@PreAuthorize("@ss.hasPermi('admin:indicators:add')")
    @Log(title = "指标分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TIndicators tIndicators) {
        return toAjax(tIndicatorsService.insertTIndicators(tIndicators));
    }

    /**
     * 修改指标分类
     */
    @ApiOperation("修改指标分类")
    //@PreAuthorize("@ss.hasPermi('admin:indicators:edit')")
    @Log(title = "指标分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TIndicators tIndicators) {
        return toAjax(tIndicatorsService.updateTIndicators(tIndicators));
    }

    /**
     * 删除指标分类
     */
    @ApiOperation("删除指标分类")
    //@PreAuthorize("@ss.hasPermi('admin:indicators:remove')")
    @Log(title = "指标分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tIndicatorsService.deleteTIndicatorsByIds(ids));
    }
}
