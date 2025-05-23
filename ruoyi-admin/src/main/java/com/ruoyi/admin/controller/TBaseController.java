package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TBase;
import com.ruoyi.admin.service.ITBaseService;
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
 * 基地Controller
 * 
 * @author ruoyi
 * @date 2024-11-06
 */
@RestController
@RequestMapping("/admin/base")
public class TBaseController extends BaseController
{
    @Autowired
    private ITBaseService tBaseService;

    /**
     * 查询基地列表
     */
    @PreAuthorize("@ss.hasPermi('admin:base:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBase tBase)
    {
        startPage();
        List<TBase> list = tBaseService.selectTBaseList(tBase);
        return getDataTable(list);
    }

    /**
     * 导出基地列表
     */
    @PreAuthorize("@ss.hasPermi('admin:base:export')")
    @Log(title = "基地", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TBase tBase)
    {
        List<TBase> list = tBaseService.selectTBaseList(tBase);
        ExcelUtil<TBase> util = new ExcelUtil<TBase>(TBase.class);
        util.exportExcel(response, list, "基地数据");
    }

    /**
     * 获取基地详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:base:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tBaseService.selectTBaseById(id));
    }

    /**
     * 新增基地
     */
    @PreAuthorize("@ss.hasPermi('admin:base:add')")
    @Log(title = "基地", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBase tBase)
    {
        return toAjax(tBaseService.insertTBase(tBase));
    }

    /**
     * 修改基地
     */
    @PreAuthorize("@ss.hasPermi('admin:base:edit')")
    @Log(title = "基地", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBase tBase)
    {
        return toAjax(tBaseService.updateTBase(tBase));
    }

    /**
     * 删除基地
     */
    @PreAuthorize("@ss.hasPermi('admin:base:remove')")
    @Log(title = "基地", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tBaseService.deleteTBaseByIds(ids));
    }

    /**
     * 查询能查看的基地
     */
    @GetMapping("/getBase")
    public AjaxResult getBase(){
        return AjaxResult.success(tBaseService.getBase());
    }
}
