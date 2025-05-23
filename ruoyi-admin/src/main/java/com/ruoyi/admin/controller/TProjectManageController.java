package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TProjectManage;
import com.ruoyi.admin.service.ITProjectManageService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目管理Controller
 *
 * @author ruoyi
 * @date 2024-12-07
 */
@RestController
@RequestMapping("/admin/manage")
public class TProjectManageController extends BaseController {
    @Autowired
    private ITProjectManageService tProjectManageService;

    /**
     * 查询项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(TProjectManage tProjectManage) {
        startPage();
        List<TProjectManage> list = tProjectManageService.selectTProjectManageList(tProjectManage);
        return getDataTable(list);
    }

    /**
     * 获取项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tProjectManageService.selectTProjectManageById(id));
    }

    /**
     * 新增项目管理
     */
    @PreAuthorize("@ss.hasPermi('admin:manage:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProjectManage tProjectManage) {
        return toAjax(tProjectManageService.insertTProjectManage(tProjectManage));
    }

    /**
     * 修改项目管理
     */
    @PreAuthorize("@ss.hasPermi('admin:manage:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProjectManage tProjectManage) {
        return toAjax(tProjectManageService.updateTProjectManage(tProjectManage));
    }

    /**
     * 删除项目管理
     */
    @PreAuthorize("@ss.hasPermi('admin:manage:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tProjectManageService.deleteTProjectManageByIds(ids));
    }
}
