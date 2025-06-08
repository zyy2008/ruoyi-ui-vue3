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
import com.ruoyi.admin.domain.TPipelineLeak;
import com.ruoyi.admin.service.ITPipelineLeakService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 管道泄漏Controller
 *
 * @author ruoyi
 * @date 2025-06-08
 */
@Api(tags = "管道泄漏")
@RestController
@RequestMapping("/admin/pipelineLeak")
public class TPipelineLeakController extends BaseController {
    @Autowired
    private ITPipelineLeakService tPipelineLeakService;

    /**
     * 查询管道泄漏列表
     */
    @ApiOperation("查询管道泄漏列表")
    //@PreAuthorize("@ss.hasPermi('admin:pipelineLeak:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPipelineLeak tPipelineLeak) {
        startPage();
        List<TPipelineLeak> list = tPipelineLeakService.selectTPipelineLeakList(tPipelineLeak);
        return getDataTable(list);
    }

    /**
     * 导出管道泄漏列表
     */
    @ApiOperation("导出管道泄漏列表")
    //@PreAuthorize("@ss.hasPermi('admin:pipelineLeak:export')")
    @Log(title = "管道泄漏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPipelineLeak tPipelineLeak) {
        List<TPipelineLeak> list = tPipelineLeakService.selectTPipelineLeakList(tPipelineLeak);
        ExcelUtil<TPipelineLeak> util = new ExcelUtil<TPipelineLeak>(TPipelineLeak.class);
        util.exportExcel(response, list, "管道泄漏数据");
    }

    /**
     * 获取管道泄漏详细信息
     */
    @ApiOperation("获取管道泄漏详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:pipelineLeak:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tPipelineLeakService.selectTPipelineLeakById(id));
    }

    /**
     * 新增管道泄漏
     */
    @ApiOperation("新增管道泄漏")
    //@PreAuthorize("@ss.hasPermi('admin:pipelineLeak:add')")
    @Log(title = "管道泄漏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPipelineLeak tPipelineLeak) {
        return toAjax(tPipelineLeakService.insertTPipelineLeak(tPipelineLeak));
    }

    /**
     * 修改管道泄漏
     */
    @ApiOperation("修改管道泄漏")
    //@PreAuthorize("@ss.hasPermi('admin:pipelineLeak:edit')")
    @Log(title = "管道泄漏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPipelineLeak tPipelineLeak) {
        return toAjax(tPipelineLeakService.updateTPipelineLeak(tPipelineLeak));
    }

    /**
     * 删除管道泄漏
     */
    @ApiOperation("删除管道泄漏")
    //@PreAuthorize("@ss.hasPermi('admin:pipelineLeak:remove')")
    @Log(title = "管道泄漏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tPipelineLeakService.deleteTPipelineLeakByIds(ids));
    }
}
