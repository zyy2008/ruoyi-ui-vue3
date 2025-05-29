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
import com.ruoyi.admin.domain.TSoilPollutant;
import com.ruoyi.admin.service.ITSoilPollutantService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 土壤监测发现的超标污染物信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@RestController
@RequestMapping("/admin/soilPollutant")
public class TSoilPollutantController extends BaseController {
    @Autowired
    private ITSoilPollutantService tSoilPollutantService;

    /**
     * 查询土壤监测发现的超标污染物信息列表
     */
    @PreAuthorize("@ss.hasPermi('admin:pollutant:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSoilPollutant tSoilPollutant) {
        startPage();
        List<TSoilPollutant> list = tSoilPollutantService.selectTSoilPollutantList(tSoilPollutant);
        return getDataTable(list);
    }

    /**
     * 导出土壤监测发现的超标污染物信息列表
     */
    @PreAuthorize("@ss.hasPermi('admin:pollutant:export')")
    @Log(title = "土壤监测发现的超标污染物信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSoilPollutant tSoilPollutant) {
        List<TSoilPollutant> list = tSoilPollutantService.selectTSoilPollutantList(tSoilPollutant);
        ExcelUtil<TSoilPollutant> util = new ExcelUtil<TSoilPollutant>(TSoilPollutant.class);
        util.exportExcel(response, list, "土壤监测发现的超标污染物信息数据");
    }

    /**
     * 获取土壤监测发现的超标污染物信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:pollutant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tSoilPollutantService.selectTSoilPollutantById(id));
    }

    /**
     * 新增土壤监测发现的超标污染物信息
     */
    @PreAuthorize("@ss.hasPermi('admin:pollutant:add')")
    @Log(title = "土壤监测发现的超标污染物信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSoilPollutant tSoilPollutant) {
        return toAjax(tSoilPollutantService.insertTSoilPollutant(tSoilPollutant));
    }

    /**
     * 修改土壤监测发现的超标污染物信息
     */
    @PreAuthorize("@ss.hasPermi('admin:pollutant:edit')")
    @Log(title = "土壤监测发现的超标污染物信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSoilPollutant tSoilPollutant) {
        return toAjax(tSoilPollutantService.updateTSoilPollutant(tSoilPollutant));
    }

    /**
     * 删除土壤监测发现的超标污染物信息
     */
    @PreAuthorize("@ss.hasPermi('admin:pollutant:remove')")
    @Log(title = "土壤监测发现的超标污染物信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSoilPollutantService.deleteTSoilPollutantByIds(ids));
    }
}
