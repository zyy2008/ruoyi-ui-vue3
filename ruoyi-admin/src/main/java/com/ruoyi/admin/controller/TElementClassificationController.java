package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TElementClassification;
import com.ruoyi.admin.service.ITElementClassificationService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 要素分类Controller
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@RestController
@RequestMapping("/admin/classification")
public class TElementClassificationController extends BaseController {
    @Autowired
    private ITElementClassificationService tElementClassificationService;

    /**
     * 查询要素分类列表
     */
    @PreAuthorize("@ss.hasPermi('admin:classification:list')")
    @GetMapping("/list")
    public TableDataInfo list(TElementClassification tElementClassification) {
        startPage();
        List<TElementClassification> list = tElementClassificationService.selectTElementClassificationList(tElementClassification);
        return getDataTable(list);
    }

    /**
     * 导出要素分类列表
     */
    @PreAuthorize("@ss.hasPermi('admin:classification:export')")
    @Log(title = "要素分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TElementClassification tElementClassification) {
        List<TElementClassification> list = tElementClassificationService.selectTElementClassificationList(tElementClassification);
        ExcelUtil<TElementClassification> util = new ExcelUtil<TElementClassification>(TElementClassification.class);
        util.exportExcel(response, list, "要素分类数据");
    }

    /**
     * 获取要素分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:classification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tElementClassificationService.selectTElementClassificationById(id));
    }

    /**
     * 新增要素分类
     */
    @PreAuthorize("@ss.hasPermi('admin:classification:add')")
    @Log(title = "要素分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TElementClassification tElementClassification) {
        return toAjax(tElementClassificationService.insertTElementClassification(tElementClassification));
    }

    /**
     * 修改要素分类
     */
    @PreAuthorize("@ss.hasPermi('admin:classification:edit')")
    @Log(title = "要素分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TElementClassification tElementClassification) {
        return toAjax(tElementClassificationService.updateTElementClassification(tElementClassification));
    }

    /**
     * 删除要素分类
     */
    @PreAuthorize("@ss.hasPermi('admin:classification:remove')")
    @Log(title = "要素分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tElementClassificationService.deleteTElementClassificationByIds(ids));
    }

    /**
     * 查询图层组合树形结构
     */
    @GetMapping("/layerTree")
    public AjaxResult layerTree(Long baseId) {
        return AjaxResult.success("success", tElementClassificationService.selectTElementClassificationList(baseId));
    }
}
