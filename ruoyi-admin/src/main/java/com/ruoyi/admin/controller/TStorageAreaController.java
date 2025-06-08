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
import com.ruoyi.admin.domain.TStorageArea;
import com.ruoyi.admin.service.ITStorageAreaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 储存区Controller
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Api(tags = "储存区")
@RestController
@RequestMapping("/admin/storageArea")
public class TStorageAreaController extends BaseController {
    @Autowired
    private ITStorageAreaService tStorageAreaService;

    /**
     * 查询储存区列表
     */
    @ApiOperation("查询储存区列表")
    //@PreAuthorize("@ss.hasPermi('admin:storageArea:list')")
    @GetMapping("/list")
    public TableDataInfo list(TStorageArea tStorageArea) {
        startPage();
        List<TStorageArea> list = tStorageAreaService.selectTStorageAreaList(tStorageArea);
        return getDataTable(list);
    }

    /**
     * 导出储存区列表
     */
    @ApiOperation("导出储存区列表")
    //@PreAuthorize("@ss.hasPermi('admin:storageArea:export')")
    @Log(title = "储存区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TStorageArea tStorageArea) {
        List<TStorageArea> list = tStorageAreaService.selectTStorageAreaList(tStorageArea);
        ExcelUtil<TStorageArea> util = new ExcelUtil<TStorageArea>(TStorageArea.class);
        util.exportExcel(response, list, "储存区数据");
    }

    /**
     * 获取储存区详细信息
     */
    @ApiOperation("获取储存区详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:storageArea:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tStorageAreaService.selectTStorageAreaById(id));
    }

    /**
     * 新增储存区
     */
    @ApiOperation("新增储存区")
    //@PreAuthorize("@ss.hasPermi('admin:storageArea:add')")
    @Log(title = "储存区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TStorageArea tStorageArea) {
        return toAjax(tStorageAreaService.insertTStorageArea(tStorageArea));
    }

    /**
     * 修改储存区
     */
    @ApiOperation("修改储存区")
    //@PreAuthorize("@ss.hasPermi('admin:storageArea:edit')")
    @Log(title = "储存区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TStorageArea tStorageArea) {
        return toAjax(tStorageAreaService.updateTStorageArea(tStorageArea));
    }

    /**
     * 删除储存区
     */
    @ApiOperation("删除储存区")
    //@PreAuthorize("@ss.hasPermi('admin:storageArea:remove')")
    @Log(title = "储存区", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tStorageAreaService.deleteTStorageAreaByIds(ids));
    }
}
