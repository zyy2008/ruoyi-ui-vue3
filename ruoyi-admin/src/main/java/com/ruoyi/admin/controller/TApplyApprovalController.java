package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TApplyApproval;
import com.ruoyi.admin.domain.bo.ApplyBo;
import com.ruoyi.admin.service.ITApplyApprovalService;
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
 * 申请审批Controller
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@RestController
@RequestMapping("/admin/approval")
public class TApplyApprovalController extends BaseController {
    @Autowired
    private ITApplyApprovalService tApplyApprovalService;

    /**
     * 查询申请审批列表
     */
    @PreAuthorize("@ss.hasPermi('admin:approval:list')")
    @GetMapping("/list")
    public TableDataInfo list(TApplyApproval tApplyApproval) {
        startPage();
        List<TApplyApproval> list = tApplyApprovalService.selectTApplyApprovalList(tApplyApproval);
        return getDataTable(list);
    }

    /**
     * 导出申请审批列表
     */
    @PreAuthorize("@ss.hasPermi('admin:approval:export')")
    @Log(title = "申请审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TApplyApproval tApplyApproval) {
        List<TApplyApproval> list = tApplyApprovalService.selectTApplyApprovalList(tApplyApproval);
        ExcelUtil<TApplyApproval> util = new ExcelUtil<TApplyApproval>(TApplyApproval.class);
        util.exportExcel(response, list, "申请审批数据");
    }

    /**
     * 获取申请审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:approval:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tApplyApprovalService.selectTApplyApprovalById(id));
    }

    /**
     * 新增申请审批
     */
    @PreAuthorize("@ss.hasPermi('admin:approval:add')")
    @Log(title = "申请审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TApplyApproval tApplyApproval) {
        return toAjax(tApplyApprovalService.insertTApplyApproval(tApplyApproval));
    }

    /**
     * 修改申请审批
     */
    @PreAuthorize("@ss.hasPermi('admin:approval:edit')")
    @Log(title = "申请审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TApplyApproval tApplyApproval) {
        return toAjax(tApplyApprovalService.updateTApplyApproval(tApplyApproval));
    }

    /**
     * 删除申请审批
     */
    @PreAuthorize("@ss.hasPermi('admin:approval:remove')")
    @Log(title = "申请审批", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tApplyApprovalService.deleteTApplyApprovalByIds(ids));
    }

    /**
     * 审批
     */
    @PostMapping("/state")
    public AjaxResult state(@RequestBody ApplyBo applyBo) {
        if (null == applyBo || null == applyBo.getId() || null == applyBo.getState())
            return AjaxResult.error("参数异常");
        if (null == tApplyApprovalService.selectTApplyApprovalById(applyBo.getId()))
            return AjaxResult.error("申请单不存在");
        return toAjax(tApplyApprovalService.updateState(applyBo));
    }


}
