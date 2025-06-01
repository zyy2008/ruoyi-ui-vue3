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
import com.ruoyi.admin.domain.TGroundwaterPollutant;
import com.ruoyi.admin.service.ITGroundwaterPollutantService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地下水监测发现的超标污染物信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "地下水监测发现的超标污染物信息")
@RestController
@RequestMapping("/admin/groundwaterPollutant")
public class TGroundwaterPollutantController extends BaseController {
    @Autowired
    private ITGroundwaterPollutantService tGroundwaterPollutantService;

    /**
     * 查询地下水监测发现的超标污染物信息列表
     */
    @ApiOperation("查询地下水监测发现的超标污染物信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:pollutant:list')")
    @GetMapping("/list")
    public TableDataInfo list(TGroundwaterPollutant tGroundwaterPollutant) {
        startPage();
        List<TGroundwaterPollutant> list = tGroundwaterPollutantService.selectTGroundwaterPollutantList(tGroundwaterPollutant);
        return getDataTable(list);
    }

    /**
     * 导出地下水监测发现的超标污染物信息列表
     */
    @ApiOperation("导出地下水监测发现的超标污染物信息列表")
    //@PreAuthorize("@ss.hasPermi('admin:pollutant:export')")
    @Log(title = "地下水监测发现的超标污染物信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TGroundwaterPollutant tGroundwaterPollutant) {
        List<TGroundwaterPollutant> list = tGroundwaterPollutantService.selectTGroundwaterPollutantList(tGroundwaterPollutant);
        ExcelUtil<TGroundwaterPollutant> util = new ExcelUtil<TGroundwaterPollutant>(TGroundwaterPollutant.class);
        util.exportExcel(response, list, "地下水监测发现的超标污染物信息数据");
    }

    /**
     * 获取地下水监测发现的超标污染物信息详细信息
     */
    @ApiOperation("获取地下水监测发现的超标污染物信息详细信息")
    //@PreAuthorize("@ss.hasPermi('admin:pollutant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tGroundwaterPollutantService.selectTGroundwaterPollutantById(id));
    }

    /**
     * 新增地下水监测发现的超标污染物信息
     */
    @ApiOperation("新增地下水监测发现的超标污染物信息")
    //@PreAuthorize("@ss.hasPermi('admin:pollutant:add')")
    @Log(title = "地下水监测发现的超标污染物信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGroundwaterPollutant tGroundwaterPollutant) {
        return toAjax(tGroundwaterPollutantService.insertTGroundwaterPollutant(tGroundwaterPollutant));
    }

    /**
     * 修改地下水监测发现的超标污染物信息
     */
    @ApiOperation("修改地下水监测发现的超标污染物信息")
    //@PreAuthorize("@ss.hasPermi('admin:pollutant:edit')")
    @Log(title = "地下水监测发现的超标污染物信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGroundwaterPollutant tGroundwaterPollutant) {
        return toAjax(tGroundwaterPollutantService.updateTGroundwaterPollutant(tGroundwaterPollutant));
    }

    /**
     * 删除地下水监测发现的超标污染物信息
     */
    @ApiOperation("删除地下水监测发现的超标污染物信息")
    //@PreAuthorize("@ss.hasPermi('admin:pollutant:remove')")
    @Log(title = "地下水监测发现的超标污染物信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tGroundwaterPollutantService.deleteTGroundwaterPollutantByIds(ids));
    }
}
