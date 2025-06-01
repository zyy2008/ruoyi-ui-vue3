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
import com.ruoyi.admin.domain.TMainProducts;
import com.ruoyi.admin.service.ITMainProductsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 主要产品信息Controller
 *
 * @author ruoyi
 * @date 2025-05-30
 */
@Api(tags = "主要产品信息")
@RestController
@RequestMapping("/admin/mainProducts")
public class TMainProductsController extends BaseController {
    @Autowired
    private ITMainProductsService tMainProductsService;

    /**
     * 查询主要产品信息列表
     */
    @ApiOperation("查询主要产品信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:mainProducts:list')")
    @GetMapping("/list")
    public TableDataInfo list(TMainProducts tMainProducts) {
        startPage();
        List<TMainProducts> list = tMainProductsService.selectTMainProductsList(tMainProducts);
        return getDataTable(list);
    }

    /**
     * 导出主要产品信息列表
     */
    @ApiOperation("导出主要产品信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:mainProducts:export')")
    @Log(title = "主要产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMainProducts tMainProducts) {
        List<TMainProducts> list = tMainProductsService.selectTMainProductsList(tMainProducts);
        ExcelUtil<TMainProducts> util = new ExcelUtil<TMainProducts>(TMainProducts.class);
        util.exportExcel(response, list, "主要产品信息数据");
    }

    /**
     * 获取主要产品信息详细信息
     */
    @ApiOperation("获取主要产品信息详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:mainProducts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tMainProductsService.selectTMainProductsById(id));
    }

    /**
     * 新增主要产品信息
     */
    @ApiOperation("新增主要产品信息")
    //@PreAuthorize("@ss.hasPermi('admin:mainProducts:add')")
    @Log(title = "主要产品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMainProducts tMainProducts) {
        return toAjax(tMainProductsService.insertTMainProducts(tMainProducts));
    }

    /**
     * 修改主要产品信息
     */
    @ApiOperation("修改主要产品信息")
    //@PreAuthorize("@ss.hasPermi('admin:mainProducts:edit')")
    @Log(title = "主要产品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMainProducts tMainProducts) {
        return toAjax(tMainProductsService.updateTMainProducts(tMainProducts));
    }

    /**
     * 删除主要产品信息
     */
    @ApiOperation("删除主要产品信息")
    //@PreAuthorize("@ss.hasPermi('admin:mainProducts:remove')")
    @Log(title = "主要产品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tMainProductsService.deleteTMainProductsByIds(ids));
    }
}
