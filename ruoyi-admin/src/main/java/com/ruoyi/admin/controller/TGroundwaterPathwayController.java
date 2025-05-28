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
import com.ruoyi.admin.domain.TGroundwaterPathway;
import com.ruoyi.admin.service.ITGroundwaterPathwayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地下水迁移途径信息Controller
 *
 * @author ruoyi
 * @date 2025-05-28
 */
@Api(tags = "地下水迁移途径信息")
@RestController
@RequestMapping("/admin/groundwaterPathway")
public class TGroundwaterPathwayController extends BaseController {
    @Autowired
    private ITGroundwaterPathwayService tGroundwaterPathwayService;

    /**
     * 查询地下水迁移途径信息列表
     */
    @ApiOperation("查询地下水迁移途径信息列表")
    @PreAuthorize("@ss.hasPermi('admin:pathway:list')")
    @GetMapping("/list")
    public TableDataInfo list(TGroundwaterPathway tGroundwaterPathway) {
        startPage();
        List<TGroundwaterPathway> list = tGroundwaterPathwayService.selectTGroundwaterPathwayList(tGroundwaterPathway);
        return getDataTable(list);
    }

    /**
     * 导出地下水迁移途径信息列表
     */
    @ApiOperation("导出地下水迁移途径信息列表")
    @PreAuthorize("@ss.hasPermi('admin:pathway:export')")
    @Log(title = "地下水迁移途径信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TGroundwaterPathway tGroundwaterPathway) {
        List<TGroundwaterPathway> list = tGroundwaterPathwayService.selectTGroundwaterPathwayList(tGroundwaterPathway);
        ExcelUtil<TGroundwaterPathway> util = new ExcelUtil<TGroundwaterPathway>(TGroundwaterPathway.class);
        util.exportExcel(response, list, "地下水迁移途径信息数据");
    }

    /**
     * 获取地下水迁移途径信息详细信息
     */
    @ApiOperation("获取地下水迁移途径信息详细信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tGroundwaterPathwayService.selectTGroundwaterPathwayById(id));
    }

    /**
     * 新增地下水迁移途径信息
     */
    @ApiOperation("新增地下水迁移途径信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:add')")
    @Log(title = "地下水迁移途径信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGroundwaterPathway tGroundwaterPathway) {
        return toAjax(tGroundwaterPathwayService.insertTGroundwaterPathway(tGroundwaterPathway));
    }

    /**
     * 修改地下水迁移途径信息
     */
    @ApiOperation("修改地下水迁移途径信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:edit')")
    @Log(title = "地下水迁移途径信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGroundwaterPathway tGroundwaterPathway) {
        return toAjax(tGroundwaterPathwayService.updateTGroundwaterPathway(tGroundwaterPathway));
    }

    /**
     * 删除地下水迁移途径信息
     */
    @ApiOperation("删除地下水迁移途径信息")
    @PreAuthorize("@ss.hasPermi('admin:pathway:remove')")
    @Log(title = "地下水迁移途径信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tGroundwaterPathwayService.deleteTGroundwaterPathwayByIds(ids));
    }
}
