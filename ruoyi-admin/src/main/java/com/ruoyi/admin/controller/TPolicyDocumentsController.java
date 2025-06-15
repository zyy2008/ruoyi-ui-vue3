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
import com.ruoyi.admin.domain.TPolicyDocuments;
import com.ruoyi.admin.service.ITPolicyDocumentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 政策文件Controller
 *
 * @author ruoyi
 * @date 2025-06-14
 */
@Api(tags = "政策文件")
@RestController
@RequestMapping("/admin/policyDocuments")
public class TPolicyDocumentsController extends BaseController {
    @Autowired
    private ITPolicyDocumentsService tPolicyDocumentsService;

    /**
     * 查询政策文件列表
     */
    @ApiOperation("查询政策文件列表")
    //@PreAuthorize("@ss.hasPermi('admin:policyDocuments:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPolicyDocuments tPolicyDocuments) {
        startPage();
        List<TPolicyDocuments> list = tPolicyDocumentsService.selectTPolicyDocumentsList(tPolicyDocuments);
        return getDataTable(list);
    }

    /**
     * 导出政策文件列表
     */
    @ApiOperation("导出政策文件列表")
    //@PreAuthorize("@ss.hasPermi('admin:policyDocuments:export')")
    @Log(title = "政策文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPolicyDocuments tPolicyDocuments) {
        List<TPolicyDocuments> list = tPolicyDocumentsService.selectTPolicyDocumentsList(tPolicyDocuments);
        ExcelUtil<TPolicyDocuments> util = new ExcelUtil<TPolicyDocuments>(TPolicyDocuments.class);
        util.exportExcel(response, list, "政策文件数据");
    }

    /**
     * 获取政策文件详细信息
     */
    @ApiOperation("获取政策文件详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:policyDocuments:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tPolicyDocumentsService.selectTPolicyDocumentsById(id));
    }

    /**
     * 新增政策文件
     */
    @ApiOperation("新增政策文件")
    //@PreAuthorize("@ss.hasPermi('admin:policyDocuments:add')")
    @Log(title = "政策文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPolicyDocuments tPolicyDocuments) {
        return toAjax(tPolicyDocumentsService.insertTPolicyDocuments(tPolicyDocuments));
    }

    /**
     * 修改政策文件
     */
    @ApiOperation("修改政策文件")
    //@PreAuthorize("@ss.hasPermi('admin:policyDocuments:edit')")
    @Log(title = "政策文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPolicyDocuments tPolicyDocuments) {
        return toAjax(tPolicyDocumentsService.updateTPolicyDocuments(tPolicyDocuments));
    }

    /**
     * 删除政策文件
     */
    @ApiOperation("删除政策文件")
    //@PreAuthorize("@ss.hasPermi('admin:policyDocuments:remove')")
    @Log(title = "政策文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tPolicyDocumentsService.deleteTPolicyDocumentsByIds(ids));
    }
}
