package com.ruoyi.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.admin.domain.vo.TDcIndustryTree;
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
import com.ruoyi.admin.domain.TDcIndustry;
import com.ruoyi.admin.service.ITDcIndustryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行业分类Controller
 *
 * @author ruoyi
 * @date 2025-06-03
 */
@Api(tags = "行业分类")
@RestController
@RequestMapping("/admin/industry")
public class TDcIndustryController extends BaseController {
    @Autowired
    private ITDcIndustryService tDcIndustryService;

    /**
     * 查询行业分类列表
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:list')")
    @GetMapping("/list")
    @ApiOperation("查询行业分类列表")
    public TableDataInfo list(TDcIndustry tDcIndustry) {
        startPage();
        List<TDcIndustry> list = tDcIndustryService.selectTDcIndustryList(tDcIndustry);
        return getDataTable(list);
    }
    /**
     * 查询行业分类树形结构
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:list')")
    @GetMapping("/treeList")
    @ApiOperation("查询行业分类树形结构")
    public AjaxResult treeList(TDcIndustry tDcIndustry) {
        return AjaxResult.success(tDcIndustryService.treeList(tDcIndustry));
    }

    /**
     * 导出行业分类列表
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:export')")
    @Log(title = "行业分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出行业分类列表")
    public void export(HttpServletResponse response, TDcIndustry tDcIndustry) {
        List<TDcIndustry> list = tDcIndustryService.selectTDcIndustryList(tDcIndustry);
        ExcelUtil<TDcIndustry> util = new ExcelUtil<TDcIndustry>(TDcIndustry.class);
        util.exportExcel(response, list, "行业分类数据");
    }

    /**
     * 获取行业分类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取行业分类详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tDcIndustryService.selectTDcIndustryById(id));
    }

    /**
     * 新增行业分类
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:add')")
    @Log(title = "行业分类", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增行业分类")
    public AjaxResult add(@RequestBody TDcIndustry tDcIndustry) {
        return toAjax(tDcIndustryService.insertTDcIndustry(tDcIndustry));
    }

    /**
     * 修改行业分类
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:edit')")
    @Log(title = "行业分类", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改行业分类")
    public AjaxResult edit(@RequestBody TDcIndustry tDcIndustry) {
        return toAjax(tDcIndustryService.updateTDcIndustry(tDcIndustry));
    }

    /**
     * 删除行业分类
     */
    //@PreAuthorize("@ss.hasPermi('admin:industry:remove')")
    @Log(title = "行业分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除行业分类")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tDcIndustryService.deleteTDcIndustryByIds(ids));
    }
}
