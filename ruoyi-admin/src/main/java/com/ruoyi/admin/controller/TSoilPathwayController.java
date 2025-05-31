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
import com.ruoyi.admin.domain.TSoilPathway;
import com.ruoyi.admin.service.ITSoilPathwayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 土壤分层信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "土壤分层信息")
@RestController
@RequestMapping("/admin/soilPathway")
public class TSoilPathwayController extends BaseController {
    @Autowired
    private ITSoilPathwayService tSoilPathwayService;

    /**
     * 查询土壤分层信息列表
     */
    @ApiOperation("查询土壤分层信息列表")
    @PreAuthorize("@ss.hasPermi('admin:pathway:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSoilPathway tSoilPathway) {
        startPage();
        List<TSoilPathway> list = tSoilPathwayService.selectTSoilPathwayList(tSoilPathway);
        return getDataTable(list);
    }

    /**
     * 导出土壤分层信息列表
     */
    @ApiOperation("导出土壤分层信息列表")
    @PreAuthorize("@ss.hasPermi('admin:pathway:export')")
    @Log(title = "土壤分层信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSoilPathway tSoilPathway) {
        List<TSoilPathway> list = tSoilPathwayService.selectTSoilPathwayList(tSoilPathway);
        ExcelUtil<TSoilPathway> util = new ExcelUtil<TSoilPathway>(TSoilPathway.class);
        util.exportExcel(response, list, "土壤分层信息数据");
    }

    /**
     * 获取土壤分层信息详细信息
     */
    @ApiOperation("获取土壤分层信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSoilPathwayService.selectTSoilPathwayById(id));
    }

    /**
     * 新增土壤分层信息
     */
    @ApiOperation("新增土壤分层信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:add')")
    @Log(title = "土壤分层信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSoilPathway tSoilPathway) {
        return toAjax(tSoilPathwayService.insertTSoilPathway(tSoilPathway));
    }

    /**
     * 修改土壤分层信息
     */
    @ApiOperation("修改土壤分层信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:edit')")
    @Log(title = "土壤分层信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSoilPathway tSoilPathway) {
        return toAjax(tSoilPathwayService.updateTSoilPathway(tSoilPathway));
    }

    /**
     * 删除土壤分层信息
     */
    @ApiOperation("删除土壤分层信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:remove')")
    @Log(title = "土壤分层信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSoilPathwayService.deleteTSoilPathwayByIds(ids));
    }
}
