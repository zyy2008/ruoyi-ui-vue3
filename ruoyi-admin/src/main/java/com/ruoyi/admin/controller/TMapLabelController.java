package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TMapLabel;
import com.ruoyi.admin.domain.bo.MapLabelShareBo;
import com.ruoyi.admin.service.ITMapLabelService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 地图标注Controller
 *
 * @author ruoyi
 * @date 2024-10-10
 */
@RestController
@RequestMapping("/admin/label")
public class TMapLabelController extends BaseController {
    @Autowired
    private ITMapLabelService tMapLabelService;

    /**
     * 查询地图标注列表
     */
    //@PreAuthorize("@ss.hasPermi('admin:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(TMapLabel tMapLabel) {
        startPage();
        List<TMapLabel> list = tMapLabelService.selectTMapLabelList(tMapLabel);
        return getDataTable(list);
    }

    /**
     * 导出地图标注列表
     */
    //@PreAuthorize("@ss.hasPermi('admin:label:export')")
    @Log(title = "地图标注", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMapLabel tMapLabel) {
        List<TMapLabel> list = tMapLabelService.selectTMapLabelList(tMapLabel);
        ExcelUtil<TMapLabel> util = new ExcelUtil<TMapLabel>(TMapLabel.class);
        util.exportExcel(response, list, "地图标注数据");
    }

    /**
     * 获取地图标注详细信息
     */
    //@PreAuthorize("@ss.hasPermi('admin:label:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tMapLabelService.selectTMapLabelById(id));
    }

    /**
     * 新增地图标注
     */
    //@PreAuthorize("@ss.hasPermi('admin:label:add')")
    @Log(title = "地图标注", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMapLabel tMapLabel) {
        return toAjax(tMapLabelService.insertTMapLabel(tMapLabel));
    }

    /**
     * 修改地图标注
     */
    //@PreAuthorize("@ss.hasPermi('admin:label:edit')")
    @Log(title = "地图标注", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMapLabel tMapLabel) {
        return toAjax(tMapLabelService.updateTMapLabel(tMapLabel));
    }

    /**
     * 删除地图标注
     */
    //@PreAuthorize("@ss.hasPermi('admin:label:remove')")
    @Log(title = "地图标注", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tMapLabelService.deleteTMapLabelByIds(ids));
    }


    /**
     * 分享地图标注数据
     */
    @Log(title = "地图标注", businessType = BusinessType.UPDATE)
    @PostMapping("share")
    public R<Void> share(@RequestBody MapLabelShareBo bo) {
        if (null == bo) return R.fail("参数异常");
        //if (null == bo.getThisDeptId() || 0 == bo.getThisDeptId().length) return R.fail("专业不能为空");
        if (null == bo.getMapLabelId()) return R.fail("地图标注ID不能为空");
        if ((null == bo.getDeptId() || 0 == bo.getDeptId().length)
                && (null == bo.getUserId() || 0 == bo.getUserId().length))
            return R.fail("至少选择一个部门或工作人员");
        return tMapLabelService.share(bo);
    }
}
