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
import com.ruoyi.admin.domain.TSurveyAssessment;
import com.ruoyi.admin.service.ITSurveyAssessmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 调查评估基本信息Controller
 *
 * @author ruoyi
 * @date 2025-06-10
 */
@Api(tags = "调查评估基本信息")
@RestController
@RequestMapping("/admin/surveyAssessment")
public class TSurveyAssessmentController extends BaseController {
    @Autowired
    private ITSurveyAssessmentService tSurveyAssessmentService;

    /**
     * 查询调查评估基本信息列表
     */
    @ApiOperation("查询调查评估基本信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:surveyAssessment:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSurveyAssessment tSurveyAssessment) {
        startPage();
        List<TSurveyAssessment> list = tSurveyAssessmentService.selectTSurveyAssessmentList(tSurveyAssessment);
        return getDataTable(list);
    }

    /**
     * 导出调查评估基本信息列表
     */
    @ApiOperation("导出调查评估基本信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:surveyAssessment:export')")
    @Log(title = "调查评估基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSurveyAssessment tSurveyAssessment) {
        List<TSurveyAssessment> list = tSurveyAssessmentService.selectTSurveyAssessmentList(tSurveyAssessment);
        ExcelUtil<TSurveyAssessment> util = new ExcelUtil<TSurveyAssessment>(TSurveyAssessment.class);
        util.exportExcel(response, list, "调查评估基本信息数据");
    }

    /**
     * 获取调查评估基本信息详细信息
     */
    @ApiOperation("获取调查评估基本信息详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveyAssessment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSurveyAssessmentService.selectTSurveyAssessmentById(id));
    }

    /**
     * 新增调查评估基本信息
     */
    @ApiOperation("新增调查评估基本信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveyAssessment:add')")
    @Log(title = "调查评估基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSurveyAssessment tSurveyAssessment) {
        return toAjax(tSurveyAssessmentService.insertTSurveyAssessment(tSurveyAssessment));
    }

    /**
     * 修改调查评估基本信息
     */
    @ApiOperation("修改调查评估基本信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveyAssessment:edit')")
    @Log(title = "调查评估基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSurveyAssessment tSurveyAssessment) {
        return toAjax(tSurveyAssessmentService.updateTSurveyAssessment(tSurveyAssessment));
    }

    /**
     * 删除调查评估基本信息
     */
    @ApiOperation("删除调查评估基本信息")
    //@PreAuthorize("@ss.hasPermi('admin:surveyAssessment:remove')")
    @Log(title = "调查评估基本信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSurveyAssessmentService.deleteTSurveyAssessmentByIds(ids));
    }
}
