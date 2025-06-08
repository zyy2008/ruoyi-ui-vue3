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
import com.ruoyi.admin.domain.TSewageSolidWaste;
import com.ruoyi.admin.service.ITSewageSolidWasteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 污水处理厂固体废物产生量及污染防治措施Controller
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Api(tags = "污水处理厂固体废物产生量及污染防治措施")
@RestController
@RequestMapping("/admin/sewageSolidWaste")
public class TSewageSolidWasteController extends BaseController {
    @Autowired
    private ITSewageSolidWasteService tSewageSolidWasteService;

    /**
     * 查询污水处理厂固体废物产生量及污染防治措施列表
     */
    @ApiOperation("查询污水处理厂固体废物产生量及污染防治措施列表")
    //@PreAuthorize("@ss.hasPermi('admin:sewageSolidWaste:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSewageSolidWaste tSewageSolidWaste) {
        startPage();
        List<TSewageSolidWaste> list = tSewageSolidWasteService.selectTSewageSolidWasteList(tSewageSolidWaste);
        return getDataTable(list);
    }

    /**
     * 导出污水处理厂固体废物产生量及污染防治措施列表
     */
    @ApiOperation("导出污水处理厂固体废物产生量及污染防治措施列表")
    //@PreAuthorize("@ss.hasPermi('admin:sewageSolidWaste:export')")
    @Log(title = "污水处理厂固体废物产生量及污染防治措施", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSewageSolidWaste tSewageSolidWaste) {
        List<TSewageSolidWaste> list = tSewageSolidWasteService.selectTSewageSolidWasteList(tSewageSolidWaste);
        ExcelUtil<TSewageSolidWaste> util = new ExcelUtil<TSewageSolidWaste>(TSewageSolidWaste.class);
        util.exportExcel(response, list, "污水处理厂固体废物产生量及污染防治措施数据");
    }

    /**
     * 获取污水处理厂固体废物产生量及污染防治措施详细信息
     */
    @ApiOperation("获取污水处理厂固体废物产生量及污染防治措施详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:sewageSolidWaste:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSewageSolidWasteService.selectTSewageSolidWasteById(id));
    }

    /**
     * 新增污水处理厂固体废物产生量及污染防治措施
     */
    @ApiOperation("新增污水处理厂固体废物产生量及污染防治措施")
    //@PreAuthorize("@ss.hasPermi('admin:sewageSolidWaste:add')")
    @Log(title = "污水处理厂固体废物产生量及污染防治措施", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSewageSolidWaste tSewageSolidWaste) {
        return toAjax(tSewageSolidWasteService.insertTSewageSolidWaste(tSewageSolidWaste));
    }

    /**
     * 修改污水处理厂固体废物产生量及污染防治措施
     */
    @ApiOperation("修改污水处理厂固体废物产生量及污染防治措施")
    //@PreAuthorize("@ss.hasPermi('admin:sewageSolidWaste:edit')")
    @Log(title = "污水处理厂固体废物产生量及污染防治措施", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSewageSolidWaste tSewageSolidWaste) {
        return toAjax(tSewageSolidWasteService.updateTSewageSolidWaste(tSewageSolidWaste));
    }

    /**
     * 删除污水处理厂固体废物产生量及污染防治措施
     */
    @ApiOperation("删除污水处理厂固体废物产生量及污染防治措施")
    //@PreAuthorize("@ss.hasPermi('admin:sewageSolidWaste:remove')")
    @Log(title = "污水处理厂固体废物产生量及污染防治措施", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSewageSolidWasteService.deleteTSewageSolidWasteByIds(ids));
    }
}
