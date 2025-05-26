package com.ruoyi.admin.controller;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.bo.DxfByTargetBo;
import com.ruoyi.admin.service.ITLayerService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.vividsolutions.jts.geom.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * GDAL
 *
 * @author ruoyi
 * @date 2024-10-15
 */
@RestController
@RequestMapping("/admin/gdal")
public class GdalController {

    @Autowired
    private ITLayerService tLayerService;
//    /**
//     * shp文件转dxf
//     */
//    @GetMapping("/shpToDxf")
//    public AjaxResult shpToDxf() {
//        // 输入的 SHP 文件路径
//        String shpFilePath = "C:\\Users\\86136\\Desktop\\JZW_M_84\\JZW_M_84_gbk2312.shp";
//        // 输出的 DXF 文件路径
//        String dxfFilePath = "C:\\Users\\86136\\Desktop\\JZW_M_84\\JZW_M_84_gbk2312_out.dxf";
//        return ShpToDxfConverterWithGdalJNI.convertShpToDxf(shpFilePath, dxfFilePath);
//    }

    /**
     * 获取目标区域的dxf
     *
     * @return
     */
    @PostMapping("/getDxfByTarget")
    public AjaxResult getDxfByTarget(@RequestBody DxfByTargetBo bo) {
        if (null == bo || null == bo.getTagert()) return AjaxResult.error("参数异常");
        return tLayerService.getDxfByTarget(bo);
    }
    @PostMapping("/getAllSpatialRefSystems")
    public AjaxResult getAllSpatialRefSystems() {
        return tLayerService.getAllSpatialRefSystems();
    }

    @PostMapping("/getIntersectionExt")
    public AjaxResult getIntersectionExt( String tableName,
                                          String targetJson,
                                          Integer  srid)
    {
        //return AjaxResult.success(tLayerService.getIntersectionExt(tableName,targetGeom,srid));

        // 写死坐标并构造 GeoJSON 字符串
        String targetGeoJson = "{\"type\": \"MultiPolygon\", \"coordinates\": [[[" +
                "[119.651778, 26.490597], [119.657014, 26.489975], [119.656742, 26.486518], " +
                "[119.650023, 26.487378], [119.651778, 26.490597]" +
                "]]]}";



//        // 使用 GeometryFactory 创建 Polygon
//        GeometryFactory factory = new GeometryFactory();
//        LinearRing ring = factory.createLinearRing(coordinates);
//        Polygon polygon = factory.createPolygon(ring);
//        Geometry multiPolygon = factory.createMultiPolygon(new Polygon[]{polygon});

        // 设置 SRID（默认 4326）
        Integer  sridIN = 3857;

        // 调用 Mapper 查询
        List<String> intersections = tLayerService.getIntersectionExt("RLGX_X_JSONToFeatures", targetGeoJson, sridIN);

        return AjaxResult.success(intersections);
    }
}

