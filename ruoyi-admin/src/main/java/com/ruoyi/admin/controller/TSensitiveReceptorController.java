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
import com.ruoyi.admin.domain.TSensitiveReceptor;
import com.ruoyi.admin.service.ITSensitiveReceptorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地块及周边敏感受体信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "地块及周边敏感受体信息")
@RestController
@RequestMapping("/admin/receptor")
public class TSensitiveReceptorController extends BaseController {
    @Autowired
    private ITSensitiveReceptorService tSensitiveReceptorService;

    /**
     * 查询地块及周边敏感受体信息列表
     */
    @ApiOperation("查询地块及周边敏感受体信息列表")
    @PreAuthorize("@ss.hasPermi('admin:receptor:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSensitiveReceptor tSensitiveReceptor) {
        startPage();
        List<TSensitiveReceptor> list = tSensitiveReceptorService.selectTSensitiveReceptorList(tSensitiveReceptor);
        return getDataTable(list);
    }

    /**
     * 导出地块及周边敏感受体信息列表
     */
    @ApiOperation("导出地块及周边敏感受体信息列表")
    @PreAuthorize("@ss.hasPermi('admin:receptor:export')")
    @Log(title = "地块及周边敏感受体信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSensitiveReceptor tSensitiveReceptor) {
        List<TSensitiveReceptor> list = tSensitiveReceptorService.selectTSensitiveReceptorList(tSensitiveReceptor);
        ExcelUtil<TSensitiveReceptor> util = new ExcelUtil<TSensitiveReceptor>(TSensitiveReceptor.class);
        util.exportExcel(response, list, "地块及周边敏感受体信息数据");
    }

    /**
     * 获取地块及周边敏感受体信息详细信息
     */
    @ApiOperation("获取地块及周边敏感受体信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:receptor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSensitiveReceptorService.selectTSensitiveReceptorById(id));
    }

    /**
     * 新增地块及周边敏感受体信息
     */
    @ApiOperation("新增地块及周边敏感受体信息")
    @PreAuthorize("@ss.hasPermi('admin:receptor:add')")
    @Log(title = "地块及周边敏感受体信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSensitiveReceptor tSensitiveReceptor) {
        return toAjax(tSensitiveReceptorService.insertTSensitiveReceptor(tSensitiveReceptor));
    }

    /**
     * 修改地块及周边敏感受体信息
     */
    @ApiOperation("修改地块及周边敏感受体信息")
    @PreAuthorize("@ss.hasPermi('admin:receptor:edit')")
    @Log(title = "地块及周边敏感受体信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSensitiveReceptor tSensitiveReceptor) {
        return toAjax(tSensitiveReceptorService.updateTSensitiveReceptor(tSensitiveReceptor));
    }

    /**
     * 删除地块及周边敏感受体信息
     */
    @ApiOperation("删除地块及周边敏感受体信息")
    @PreAuthorize("@ss.hasPermi('admin:receptor:remove')")
    @Log(title = "地块及周边敏感受体信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSensitiveReceptorService.deleteTSensitiveReceptorByIds(ids));
    }
}
