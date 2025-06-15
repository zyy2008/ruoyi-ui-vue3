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
import com.ruoyi.admin.domain.TSurveySoilPollution;
import com.ruoyi.admin.service.ITSurveySoilPollutionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 调查评估土壤污染区信息Controller
 *
 * @author ruoyi
 * @date 2025-06-10
 */
@Api(tags = "调查评估土壤污染区信息")
@RestController
@RequestMapping("/admin/surveySoilPollution")
public class TSurveySoilPollutionController extends BaseController {
    @Autowired
    private ITSurveySoilPollutionService tSurveySoilPollutionService;

    /**
     * 查询调查评估土壤污染区信息列表
     */
    @ApiOperation("查询调查评估土壤污染区信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:surveySoilPollution:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSurveySoilPollution tSurveySoilPollution) {
        startPage();
        List<TSurveySoilPollution> list = tSurveySoilPollutionService.selectTSurveySoilPollutionList(tSurveySoilPollution);
        return getDataTable(list);
    }

    /**
     * 导出调查评估土壤污染区信息列表
     */
    @ApiOperation("导出调查评估土壤污染区信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:surveySoilPollution:export')")
    @Log(title = "调查评估土壤污染区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSurveySoilPollution tSurveySoilPollution) {
        List<TSurveySoilPollution> list = tSurveySoilPollutionService.selectTSurveySoilPollutionList(tSurveySoilPollution);
        ExcelUtil<TSurveySoilPollution> util = new ExcelUtil<TSurveySoilPollution>(TSurveySoilPollution.class);
        util.exportExcel(response, list, "调查评估土壤污染区信息数据");
    }

    /**
     * 获取调查评估土壤污染区信息详细信息
     */
    @ApiOperation("获取调查评估土壤污染区信息详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveySoilPollution:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSurveySoilPollutionService.selectTSurveySoilPollutionById(id));
    }

    /**
     * 新增调查评估土壤污染区信息
     */
    @ApiOperation("新增调查评估土壤污染区信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveySoilPollution:add')")
    @Log(title = "调查评估土壤污染区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSurveySoilPollution tSurveySoilPollution) {
        return toAjax(tSurveySoilPollutionService.insertTSurveySoilPollution(tSurveySoilPollution));
    }

    /**
     * 修改调查评估土壤污染区信息
     */
    @ApiOperation("修改调查评估土壤污染区信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveySoilPollution:edit')")
    @Log(title = "调查评估土壤污染区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSurveySoilPollution tSurveySoilPollution) {
        return toAjax(tSurveySoilPollutionService.updateTSurveySoilPollution(tSurveySoilPollution));
    }

    /**
     * 删除调查评估土壤污染区信息
     */
    @ApiOperation("删除调查评估土壤污染区信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveySoilPollution:remove')")
    @Log(title = "调查评估土壤污染区信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSurveySoilPollutionService.deleteTSurveySoilPollutionByIds(ids));
    }
}
