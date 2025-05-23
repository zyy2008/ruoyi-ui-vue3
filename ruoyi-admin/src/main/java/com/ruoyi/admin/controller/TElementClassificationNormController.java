package com.ruoyi.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.admin.domain.TElementClassificationNorm;
import com.ruoyi.admin.service.ITElementClassificationNormService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 要素分类编码规范Controller
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@RestController
@RequestMapping("/admin/norm")
public class TElementClassificationNormController extends BaseController {
    @Autowired
    private ITElementClassificationNormService tElementClassificationNormService;

    /**
     * 查询要素分类编码规范列表
     */
    @PreAuthorize("@ss.hasPermi('admin:norm:list')")
    @GetMapping("/list")
    public TableDataInfo list(TElementClassificationNorm tElementClassificationNorm) {
        startPage();
        List<TElementClassificationNorm> list = tElementClassificationNormService.selectTElementClassificationNormList(tElementClassificationNorm);
        return getDataTable(list);
    }

    /**
     * 导出要素分类编码规范列表
     */
    @PreAuthorize("@ss.hasPermi('admin:norm:export')")
    @Log(title = "要素分类编码规范", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TElementClassificationNorm tElementClassificationNorm) {
        List<TElementClassificationNorm> list = tElementClassificationNormService.selectTElementClassificationNormList(tElementClassificationNorm);
        ExcelUtil<TElementClassificationNorm> util = new ExcelUtil<TElementClassificationNorm>(TElementClassificationNorm.class);
        util.exportExcel(response, list, "要素分类编码规范数据");
    }

    /**
     * 获取要素分类编码规范详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:norm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tElementClassificationNormService.selectTElementClassificationNormById(id));
    }

    /**
     * 新增要素分类编码规范
     */
    @PreAuthorize("@ss.hasPermi('admin:norm:add')")
    @Log(title = "要素分类编码规范", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TElementClassificationNorm tElementClassificationNorm) {
        return toAjax(tElementClassificationNormService.insertTElementClassificationNorm(tElementClassificationNorm));
    }

    /**
     * 修改要素分类编码规范
     */
    @PreAuthorize("@ss.hasPermi('admin:norm:edit')")
    @Log(title = "要素分类编码规范", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TElementClassificationNorm tElementClassificationNorm) {
        return toAjax(tElementClassificationNormService.updateTElementClassificationNorm(tElementClassificationNorm));
    }

    /**
     * 删除要素分类编码规范
     */
    @PreAuthorize("@ss.hasPermi('admin:norm:remove')")
    @Log(title = "要素分类编码规范", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tElementClassificationNormService.deleteTElementClassificationNormByIds(ids));
    }
}
