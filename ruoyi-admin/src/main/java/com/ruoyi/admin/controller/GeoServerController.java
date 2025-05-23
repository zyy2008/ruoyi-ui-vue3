package com.ruoyi.admin.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.util.GeoServerHttpClient;
import com.ruoyi.util.PostGISDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * geoserver
 */
@RestController
@RequestMapping("/geoserver")
public class GeoServerController {

    /**
     * 获取工作空间
     *
     * @return
     */
    @GetMapping("/getAllWorkspaces")
    public AjaxResult getAllWorkspaces() {
        try {
            List<String> data = PostGISDataSource.getAllWorkspaces();
            return AjaxResult.success(data);
        } catch (IOException e) {
            return AjaxResult.error();
        }
    }

    /**
     * 获取样式列表
     *
     * @return
     */
    @GetMapping("/getStyleByStyleName")
    public AjaxResult getLayerStyle(@RequestParam("workspace") String workspace) {
        List<String> globalStyles = GeoServerHttpClient.getStyleList(workspace);
        return AjaxResult.success(globalStyles);
    }

    /**
     * 获取仓库列表
     * @return
     */
    @GetMapping("/getDataSourceByWorkspace")
    public AjaxResult getDataSourceByWorkspace(@RequestParam("workspace") String workspace) {
        if (null == workspace || "".equals(workspace))
            return AjaxResult.error("参数异常");
        List<String> globalDataSource = null;
        try {
            globalDataSource = PostGISDataSource.getDataSources(workspace);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
        return AjaxResult.success(globalDataSource);
    }

}
