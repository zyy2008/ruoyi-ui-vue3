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
import com.ruoyi.admin.domain.TEnterprisePollutionInfo;
import com.ruoyi.admin.service.ITEnterprisePollutionInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业污染源Controller
 *
 * @author ruoyi
 * @date 2025-05-26
 */
@Api(tags = "企业污染源")
@RestController
@RequestMapping("/admin/pollution")
public class TEnterprisePollutionInfoController extends BaseController {
    @Autowired
    private ITEnterprisePollutionInfoService tEnterprisePollutionInfoService;

    /**
     * 查询企业污染源列表
     */
    @ApiOperation("查询企业污染源列表")
    @PreAuthorize("@ss.hasPermi('admin:pollution:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        startPage();
        List<TEnterprisePollutionInfo> list = tEnterprisePollutionInfoService.selectTEnterprisePollutionInfoList(tEnterprisePollutionInfo);
        return getDataTable(list);
    }

    /**
     * 导出企业污染源列表
     */
    @ApiOperation("导出企业污染源列表")
    @PreAuthorize("@ss.hasPermi('admin:pollution:export')")
    @Log(title = "企业污染源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        List<TEnterprisePollutionInfo> list = tEnterprisePollutionInfoService.selectTEnterprisePollutionInfoList(tEnterprisePollutionInfo);
        ExcelUtil<TEnterprisePollutionInfo> util = new ExcelUtil<TEnterprisePollutionInfo>(TEnterprisePollutionInfo.class);
        util.exportExcel(response, list, "企业污染源数据");
    }

    /**
     * 获取企业污染源详细信息
     */
    @ApiOperation("获取企业污染源详细信息")
    @PreAuthorize("@ss.hasPermi('admin:pollution:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tEnterprisePollutionInfoService.selectTEnterprisePollutionInfoById(id));
    }

    /**
     * 新增企业污染源
     */
    @ApiOperation("新增企业污染源")
    @PreAuthorize("@ss.hasPermi('admin:pollution:add')")
    @Log(title = "企业污染源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        return toAjax(tEnterprisePollutionInfoService.insertTEnterprisePollutionInfo(tEnterprisePollutionInfo));
    }

    /**
     * 修改企业污染源
     */
    @ApiOperation("修改企业污染源")
    @PreAuthorize("@ss.hasPermi('admin:pollution:edit')")
    @Log(title = "企业污染源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        return toAjax(tEnterprisePollutionInfoService.updateTEnterprisePollutionInfo(tEnterprisePollutionInfo));
    }

    /**
     * 删除企业污染源
     */
    @ApiOperation("删除企业污染源")
    @PreAuthorize("@ss.hasPermi('admin:pollution:remove')")
    @Log(title = "企业污染源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tEnterprisePollutionInfoService.deleteTEnterprisePollutionInfoByIds(ids));
    }
}
