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
import com.ruoyi.admin.domain.TRawMaterials;
import com.ruoyi.admin.service.ITRawMaterialsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 原辅材料信息Controller
 *
 * @author ruoyi
 * @date 2025-05-30
 */
@Api(tags = "原辅材料信息")
@RestController
@RequestMapping("/admin/rawMaterials")
public class TRawMaterialsController extends BaseController {
    @Autowired
    private ITRawMaterialsService tRawMaterialsService;

    /**
     * 查询原辅材料信息列表
     */
    @ApiOperation("查询原辅材料信息列表")
    @PreAuthorize("@ss.hasPermi('admin:rawMaterials:list')")
    @GetMapping("/list")
    public TableDataInfo list(TRawMaterials tRawMaterials) {
        startPage();
        List<TRawMaterials> list = tRawMaterialsService.selectTRawMaterialsList(tRawMaterials);
        return getDataTable(list);
    }

    /**
     * 导出原辅材料信息列表
     */
    @ApiOperation("导出原辅材料信息列表")
    @PreAuthorize("@ss.hasPermi('admin:rawMaterials:export')")
    @Log(title = "原辅材料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TRawMaterials tRawMaterials) {
        List<TRawMaterials> list = tRawMaterialsService.selectTRawMaterialsList(tRawMaterials);
        ExcelUtil<TRawMaterials> util = new ExcelUtil<TRawMaterials>(TRawMaterials.class);
        util.exportExcel(response, list, "原辅材料信息数据");
    }

    /**
     * 获取原辅材料信息详细信息
     */
    @ApiOperation("获取原辅材料信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:rawMaterials:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tRawMaterialsService.selectTRawMaterialsById(id));
    }

    /**
     * 新增原辅材料信息
     */
    @ApiOperation("新增原辅材料信息")
    @PreAuthorize("@ss.hasPermi('admin:rawMaterials:add')")
    @Log(title = "原辅材料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TRawMaterials tRawMaterials) {
        return toAjax(tRawMaterialsService.insertTRawMaterials(tRawMaterials));
    }

    /**
     * 修改原辅材料信息
     */
    @ApiOperation("修改原辅材料信息")
    @PreAuthorize("@ss.hasPermi('admin:rawMaterials:edit')")
    @Log(title = "原辅材料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TRawMaterials tRawMaterials) {
        return toAjax(tRawMaterialsService.updateTRawMaterials(tRawMaterials));
    }

    /**
     * 删除原辅材料信息
     */
    @ApiOperation("删除原辅材料信息")
    @PreAuthorize("@ss.hasPermi('admin:rawMaterials:remove')")
    @Log(title = "原辅材料信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tRawMaterialsService.deleteTRawMaterialsByIds(ids));
    }
}
