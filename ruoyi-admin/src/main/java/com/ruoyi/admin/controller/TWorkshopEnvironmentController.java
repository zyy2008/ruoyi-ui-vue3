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
import com.ruoyi.admin.domain.TWorkshopEnvironment;
import com.ruoyi.admin.service.ITWorkshopEnvironmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产车间分布与环境情况Controller
 *
 * @author ruoyi
 * @date 2025-05-30
 */
@Api(tags = "产车间分布与环境情况")
@RestController
@RequestMapping("/admin/workshopEnvironment")
public class TWorkshopEnvironmentController extends BaseController {
    @Autowired
    private ITWorkshopEnvironmentService tWorkshopEnvironmentService;

    /**
     * 查询生产车间分布与环境情况列表
     */
    @ApiOperation("查询生产车间分布与环境情况列表")
    @PreAuthorize("@ss.hasPermi('admin:workshopEnvironment:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWorkshopEnvironment tWorkshopEnvironment) {
        startPage();
        List<TWorkshopEnvironment> list = tWorkshopEnvironmentService.selectTWorkshopEnvironmentList(tWorkshopEnvironment);
        return getDataTable(list);
    }

    /**
     * 导出生产车间分布与环境情况列表
     */
    @ApiOperation("导出生产车间分布与环境情况列表")
    @PreAuthorize("@ss.hasPermi('admin:workshopEnvironment:export')")
    @Log(title = "生产车间分布与环境情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TWorkshopEnvironment tWorkshopEnvironment) {
        List<TWorkshopEnvironment> list = tWorkshopEnvironmentService.selectTWorkshopEnvironmentList(tWorkshopEnvironment);
        ExcelUtil<TWorkshopEnvironment> util = new ExcelUtil<TWorkshopEnvironment>(TWorkshopEnvironment.class);
        util.exportExcel(response, list, "生产车间分布与环境情况数据");
    }

    /**
     * 获取生产车间分布与环境情况详细信息
     */
    @ApiOperation("获取生产车间分布与环境情况详细信息")
    @PreAuthorize("@ss.hasPermi('admin:workshopEnvironment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tWorkshopEnvironmentService.selectTWorkshopEnvironmentById(id));
    }

    /**
     * 新增生产车间分布与环境情况
     */
    @ApiOperation("新增生产车间分布与环境情况")
    @PreAuthorize("@ss.hasPermi('admin:workshopEnvironment:add')")
    @Log(title = "生产车间分布与环境情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWorkshopEnvironment tWorkshopEnvironment) {
        return toAjax(tWorkshopEnvironmentService.insertTWorkshopEnvironment(tWorkshopEnvironment));
    }

    /**
     * 修改生产车间分布与环境情况
     */
    @ApiOperation("修改生产车间分布与环境情况")
    @PreAuthorize("@ss.hasPermi('admin:workshopEnvironment:edit')")
    @Log(title = "生产车间分布与环境情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWorkshopEnvironment tWorkshopEnvironment) {
        return toAjax(tWorkshopEnvironmentService.updateTWorkshopEnvironment(tWorkshopEnvironment));
    }

    /**
     * 删除生产车间分布与环境情况
     */
    @ApiOperation("删除生产车间分布与环境情况")
    @PreAuthorize("@ss.hasPermi('admin:workshopEnvironment:remove')")
    @Log(title = "生产车间分布与环境情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tWorkshopEnvironmentService.deleteTWorkshopEnvironmentByIds(ids));
    }
}
