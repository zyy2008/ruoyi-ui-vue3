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
import com.ruoyi.admin.domain.TLandPollutionAnalysis;
import com.ruoyi.admin.service.ITLandPollutionAnalysisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地块污染情况分析Controller
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Api(tags = "地块污染情况分析")
@RestController
@RequestMapping("/admin/landPollutionAnalysis")
public class TLandPollutionAnalysisController extends BaseController {
    @Autowired
    private ITLandPollutionAnalysisService tLandPollutionAnalysisService;

    /**
     * 查询地块污染情况分析列表
     */
    @ApiOperation("查询地块污染情况分析列表")
    //@PreAuthorize("@ss.hasPermi('admin:landPollutionAnalysis:list')")
    @GetMapping("/list")
    public TableDataInfo list(TLandPollutionAnalysis tLandPollutionAnalysis) {
        startPage();
        List<TLandPollutionAnalysis> list = tLandPollutionAnalysisService.selectTLandPollutionAnalysisList(tLandPollutionAnalysis);
        return getDataTable(list);
    }

    /**
     * 导出地块污染情况分析列表
     */
    @ApiOperation("导出地块污染情况分析列表")
    //@PreAuthorize("@ss.hasPermi('admin:landPollutionAnalysis:export')")
    @Log(title = "地块污染情况分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TLandPollutionAnalysis tLandPollutionAnalysis) {
        List<TLandPollutionAnalysis> list = tLandPollutionAnalysisService.selectTLandPollutionAnalysisList(tLandPollutionAnalysis);
        ExcelUtil<TLandPollutionAnalysis> util = new ExcelUtil<TLandPollutionAnalysis>(TLandPollutionAnalysis.class);
        util.exportExcel(response, list, "地块污染情况分析数据");
    }

    /**
     * 获取地块污染情况分析详细信息
     */
    @ApiOperation("获取地块污染情况分析详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:landPollutionAnalysis:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tLandPollutionAnalysisService.selectTLandPollutionAnalysisById(id));
    }

    /**
     * 新增地块污染情况分析
     */
    @ApiOperation("新增地块污染情况分析")
    //@PreAuthorize("@ss.hasPermi('admin:landPollutionAnalysis:add')")
    @Log(title = "地块污染情况分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLandPollutionAnalysis tLandPollutionAnalysis) {
        return toAjax(tLandPollutionAnalysisService.insertTLandPollutionAnalysis(tLandPollutionAnalysis));
    }

    /**
     * 修改地块污染情况分析
     */
    @ApiOperation("修改地块污染情况分析")
    //@PreAuthorize("@ss.hasPermi('admin:landPollutionAnalysis:edit')")
    @Log(title = "地块污染情况分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLandPollutionAnalysis tLandPollutionAnalysis) {
        return toAjax(tLandPollutionAnalysisService.updateTLandPollutionAnalysis(tLandPollutionAnalysis));
    }

    /**
     * 删除地块污染情况分析
     */
    @ApiOperation("删除地块污染情况分析")
    //@PreAuthorize("@ss.hasPermi('admin:landPollutionAnalysis:remove')")
    @Log(title = "地块污染情况分析", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tLandPollutionAnalysisService.deleteTLandPollutionAnalysisByIds(ids));
    }
}
