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
import com.ruoyi.admin.domain.TEnterprises;
import com.ruoyi.admin.service.ITEnterprisesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业管理Controller
 *
 * @author wy
 * @date 2025-06-01
 */
@RestController
@RequestMapping("/admin/enterprises")
public class TEnterprisesController extends BaseController
{
    @Autowired
    private ITEnterprisesService tEnterprisesService;

    /**
     * 查询企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:enterprises:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEnterprises tEnterprises)
    {
        startPage();
        List<TEnterprises> list = tEnterprisesService.selectTEnterprisesList(tEnterprises);
        return getDataTable(list);
    }

    /**
     * 导出企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:enterprises:export')")
    @Log(title = "企业管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TEnterprises tEnterprises)
    {
        List<TEnterprises> list = tEnterprisesService.selectTEnterprisesList(tEnterprises);
        ExcelUtil<TEnterprises> util = new ExcelUtil<TEnterprises>(TEnterprises.class);
        util.exportExcel(response, list, "企业管理数据");
    }

    /**
     * 获取企业管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:enterprises:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tEnterprisesService.selectTEnterprisesById(id));
    }

    /**
     * 新增企业管理
     */
    @PreAuthorize("@ss.hasPermi('admin:enterprises:add')")
    @Log(title = "企业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TEnterprises tEnterprises)
    {
        return toAjax(tEnterprisesService.insertTEnterprises(tEnterprises));
    }

    /**
     * 修改企业管理
     */
    @PreAuthorize("@ss.hasPermi('admin:enterprises:edit')")
    @Log(title = "企业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TEnterprises tEnterprises)
    {
        return toAjax(tEnterprisesService.updateTEnterprises(tEnterprises));
    }

    /**
     * 删除企业管理
     */
    @PreAuthorize("@ss.hasPermi('admin:enterprises:remove')")
    @Log(title = "企业管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tEnterprisesService.deleteTEnterprisesByIds(ids));
    }
}
