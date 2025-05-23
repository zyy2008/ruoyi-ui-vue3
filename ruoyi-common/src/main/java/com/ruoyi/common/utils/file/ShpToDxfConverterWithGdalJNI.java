package com.ruoyi.common.utils.file;


import com.ruoyi.common.core.domain.AjaxResult;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.Driver;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;
import org.gdal.ogr.*;
import org.gdal.osr.SpatialReference;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * shp文件转dxf
 */
public class ShpToDxfConverterWithGdalJNI {

    static {

        // 注册所有驱动
        ogr.RegisterAll();
        gdal.AllRegister();

    }

    public static String GADL_DATA() {
        String data = System.getenv("GDAL_DATA");
        System.out.println("GDAL_DATA：" + data);
        if (null == data){
            System.out.println("GDAL_DATA：");
            System.setProperty("GDAL_DATA", "C:\\Program Files\\GDAL\\gdal-data");
            System.setProperty("PROJ_LIB", "C:\\Program Files\\GDAL\\projlib");
        }
        return data;
    }

    /**
     * shp文件转dxf
     *
     * @param shpFilePath 输入的shp文件路径
     * @param dxfFilePath 输出的dxf文件路径
     * @param webPath     网络访问路径
     */
    public static AjaxResult convertShpToDxf(String shpFilePath, String dxfFilePath, String webPath) {
//        // 打开 SHP 文件
//        DataSource shpDataSource = ogr.Open(shpFilePath, 0); // 0 = read-only
//        if (shpDataSource == null) {
//            System.err.println("无法打开 SHP 文件: " + shpFilePath);
//            return AjaxResult.error("无法打开 SHP 文件");
//        }
//        // 获取 DXF 驱动
//        org.gdal.ogr.Driver dxfDriver = ogr.GetDriverByName("DXF");
//        if (dxfDriver == null) {
//            System.err.println("DXF 驱动不可用");
//            return AjaxResult.error("DXF 驱动不可用");
//        }
//        // 创建 DXF 文件并复制 SHP 内容
//        DataSource dxfDataSource = dxfDriver.CreateDataSource(dxfFilePath, null);
//        if (dxfDataSource == null) {
//            System.err.println("无法创建 DXF 文件: " + dxfFilePath);
//            return AjaxResult.error("无法创建 DXF 文件");
//        }
//        // 复制所有图层
//        for (int i = 0; i < shpDataSource.GetLayerCount(); i++) {
//            dxfDataSource.CopyLayer(shpDataSource.GetLayerByIndex(i), shpDataSource.GetLayerByIndex(i).GetName());
//        }
//        // 关闭数据源
//        shpDataSource.delete();
//        dxfDataSource.delete();
//        System.out.println("SHP 转换为 DXF 成功！");
//        return AjaxResult.success("成功", webPath);


        // 打开 SHP 文件
        DataSource shpDataSource = ogr.Open(shpFilePath, 0);
        if (shpDataSource == null) {
            System.err.println("无法打开 SHP 文件: " + shpFilePath);
            return AjaxResult.error("无法打开 SHP 文件");
        }

        // 获取 DXF 驱动
        org.gdal.ogr.Driver dxfDriver = ogr.GetDriverByName("DXF");
        if (dxfDriver == null) {
            System.err.println("DXF 驱动不可用");
            return AjaxResult.error("DXF 驱动不可用");
        }

        // 创建 DXF 数据源
        DataSource dxfDataSource = dxfDriver.CreateDataSource(dxfFilePath, null);
        if (dxfDataSource == null) {
            System.err.println("无法创建 DXF 文件: " + dxfFilePath);
            return AjaxResult.error("无法创建 DXF 文件");
        }

        for (int i = 0; i < shpDataSource.GetLayerCount(); i++) {
            Layer shpLayer = shpDataSource.GetLayerByIndex(i);
            if (shpLayer == null) continue;

            // 检查 SHP 是否有要素
            if (shpLayer.GetFeatureCount() == 0) {
                System.out.println("SHP 图层为空，跳过: " + shpLayer.GetName());
                continue;
            }

            // 创建 DXF 图层
            Layer dxfLayer = dxfDataSource.CreateLayer(shpLayer.GetName(), null, ogr.wkbLineString, null);

            shpLayer.ResetReading();
            Feature shpFeature;
            while ((shpFeature = shpLayer.GetNextFeature()) != null) {

                Geometry geometry = shpFeature.GetGeometryRef();
                if (geometry == null || geometry.IsEmpty()) continue;


                if (geometry.GetGeometryType() == ogr.wkbPolygon || geometry.GetGeometryType() == ogr.wkbMultiPolygon) {
                    geometry = geometry.Boundary(); // 取 Polygon 的边界线
                }
                    Feature dxfFeature = new Feature(dxfLayer.GetLayerDefn());
                    dxfFeature.SetGeometry(geometry);
                    if (dxfLayer.CreateFeature(dxfFeature) != 0) {
                        System.err.println("DXF 要素创建失败");
                    }
                    dxfFeature.delete();

                shpFeature.delete();

            }
        }

        // 关闭数据源
        shpDataSource.delete();
        dxfDataSource.delete();

        System.out.println("SHP 转换为 DXF 成功: " + dxfFilePath);
        return AjaxResult.success("成功", webPath);
    }


    /**
     * shp文件转dxf
     *
     * @param shpFilePath 输入的shp文件路径
     * @param dxfFilePath 输出的dxf文件路径
     * @param webPath     网络访问路径
     */
    public static AjaxResult convertShpToDxfExt_back(String shpFilePath, String dxfFilePath, String webPath) {
// 打开 SHP 文件
        DataSource shpDataSource = ogr.Open(shpFilePath, 0); // 0 = read-only
        if (shpDataSource == null) {
            System.err.println("无法打开 SHP 文件: " + shpFilePath);
            return AjaxResult.error("无法打开 SHP 文件");
        }

        // 获取 DXF 驱动
        org.gdal.ogr.Driver dxfDriver = ogr.GetDriverByName("DXF");
        if (dxfDriver == null) {
            System.err.println("DXF 驱动不可用");
            return AjaxResult.error("DXF 驱动不可用");
        }

        // 创建 DXF 文件并复制 SHP 内容
        DataSource dxfDataSource = dxfDriver.CreateDataSource(dxfFilePath, null);
        if (dxfDataSource == null) {
            System.err.println("无法创建 DXF 文件: " + dxfFilePath);
            return AjaxResult.error("无法创建 DXF 文件");
        }

        // 复制所有图层

        for (int i = 0; i < shpDataSource.GetLayerCount(); i++) {
            Layer shpLayer = shpDataSource.GetLayerByIndex(i);
            SpatialReference shpSpatialRef = shpLayer.GetSpatialRef();
            Layer dxfLayer = dxfDataSource.CreateLayer(shpLayer.GetName(), null, shpLayer.GetGeomType(), null);

//            // 为 DXF 图层设置颜色和线型
//            // 例如，设置颜色为红色 (1)，设置线型为虚线（需要根据实际需求调整线型名称）
//            dxfLayer.SetMetadataItem("COLOR", "1");  // 设置图层的颜色为红色
//            dxfLayer.SetMetadataItem("LTYPE", "DASHED");  // 设置线型为虚线

            // 复制图层数据
            Feature shpFeature;
            shpLayer.ResetReading();
            while ((shpFeature = shpLayer.GetNextFeature()) != null) {
                // 将每个特征的几何数据添加到 DXF 图层
                Geometry geometry = shpFeature.GetGeometryRef();
                Feature dxfFeature = new Feature(dxfLayer.GetLayerDefn());
                dxfFeature.SetGeometry(geometry); // 只设置几何数据，不创建字段
                dxfLayer.CreateFeature(dxfFeature);
            }
        }

        // 关闭数据源
        shpDataSource.delete();
        dxfDataSource.delete();
        System.out.println("SHP 转换为 DXF 成功！");
        return AjaxResult.success("成功", webPath);
    }


    public static AjaxResult convertShpToDxfExt(String shpFilePath, String dxfFilePath, String webPath) {
        // 打开 SHP 文件
        DataSource shpDataSource = ogr.Open(shpFilePath, 0); // 0 = read-only
        if (shpDataSource == null) {
            System.err.println("无法打开 SHP 文件: " + shpFilePath);
            return AjaxResult.error("无法打开 SHP 文件");
        }

        // 获取 DXF 驱动
        org.gdal.ogr.Driver dxfDriver = ogr.GetDriverByName("DXF");
        if (dxfDriver == null) {
            System.err.println("DXF 驱动不可用");
            return AjaxResult.error("DXF 驱动不可用");
        }

        // 创建 DXF 文件
        DataSource dxfDataSource = dxfDriver.CreateDataSource(dxfFilePath, null);
        if (dxfDataSource == null) {
            System.err.println("无法创建 DXF 文件: " + dxfFilePath);
            return AjaxResult.error("无法创建 DXF 文件");
        }

        // 复制 SHP 文件中的所有图层
        for (int i = 0; i < shpDataSource.GetLayerCount(); i++) {
            Layer shpLayer = shpDataSource.GetLayerByIndex(i);
            if (shpLayer == null) continue;

            // 确保 SHP 图层有要素
            if (shpLayer.GetFeatureCount() == 0) {
                System.out.println("SHP 图层为空，跳过: " + shpLayer.GetName());
                continue;
            }

            SpatialReference shpSpatialRef = shpLayer.GetSpatialRef();
            if (shpSpatialRef == null) {
                System.err.println("SHP 文件缺少坐标系信息，可能导致 DXF 解析错误");
                continue;
            }

            // 目标坐标系（可选）
            SpatialReference dxfSRS = new SpatialReference();
            dxfSRS.ImportFromEPSG(4528); // 目标坐标系 高斯三度带 EPSG:4528

            // 创建 DXF 图层（与 SHP 图层同名）
            Layer dxfLayer = dxfDataSource.CreateLayer(shpLayer.GetName(), null, shpLayer.GetGeomType(), null);

            // 读取 SHP 图层的所有要素并转换
            shpLayer.ResetReading();
            Feature shpFeature;
            while ((shpFeature = shpLayer.GetNextFeature()) != null) {
                Geometry geometry = shpFeature.GetGeometryRef();
                if (geometry == null) continue;

                // **转换几何**
                //geometry.TransformTo(dxfSRS);

                // **仅处理有效的 DXF 几何类型**
                if (geometry.GetGeometryType() != ogr.wkbPoint &&
                        geometry.GetGeometryType() != ogr.wkbLineString &&
                        geometry.GetGeometryType() != ogr.wkbPolygon) {
                    System.out.println("跳过不支持的几何类型: " + geometry.GetGeometryType());
                    continue;
                }

                // 创建 DXF 特征
                Feature dxfFeature = new Feature(dxfLayer.GetLayerDefn());
                dxfFeature.SetGeometry(geometry);

                // 添加到 DXF 图层
                dxfLayer.CreateFeature(dxfFeature);
            }
        }

        // 关闭数据源
        shpDataSource.delete();
        dxfDataSource.delete();

        System.out.println("SHP 转换为 DXF 成功: " + dxfFilePath);
        return AjaxResult.success("成功", webPath);
    }


    /**
     * 创建shp文件
     *
     * @param geometries
     * @param filePath
     * @return
     */
    public static String createShapefile(List<String> geometries, String filePath) {
        System.out.println("文件路径：" + filePath);
        if (null == GADL_DATA()) {
            System.out.println("GADL_DATA环境异常");
            return null;
        }
        // 创建 DXF 驱动
//        Driver driver = gdal.GetDriverByName("SHP");
        System.out.println("创建 DXF 驱动");
        Driver driver = gdal.GetDriverByName("DXF");
        if (driver == null) {
            System.err.println("确实DXF驱动");
            return null;
        }
        // 创建新的 DXF 数据集
        System.out.println("创建新的 DXF 数据集");
        Dataset dataset = driver.Create(filePath, 0, 0, 0, 0);
        if (dataset == null) {
            System.err.println("创建DXF数据失败");
            return null;
        }
        // 创建图层
        System.out.println("创建图层");
        Layer layer = dataset.CreateLayer("layer", null, 0, null);
        if (layer == null) {
            System.err.println("创建图层失败");
            dataset.delete();
            return null;
        }
        // 遍历几何数据并添加到图层
        System.out.println("遍历几何数据并添加到图层：" + geometries.size());
        for (String geometryStr : geometries) {
            Geometry geometry = Geometry.CreateFromWkt(geometryStr);
            if (geometry != null) {
                // 创建一个新的特征
                Feature feature = new Feature(layer.GetLayerDefn());
                feature.SetGeometry(geometry);  // 设置几何体
                // 将特征添加到图层
                layer.CreateFeature(feature);
            } else {
                System.err.println("根据WKT数据创建Geometry失败: " + geometryStr);
            }
        }
        System.out.println("清理和关闭数据集");
        // 清理和关闭数据集
        dataset.delete();
        return filePath;
    }


    public static String createShapefileFromGeoJSONstr(String geoJsonStr, String filePath) throws  Exception{
        System.out.println("文件路径：" + filePath);

        if (null == GADL_DATA()) {
            System.out.println("GADL_DATA环境异常");
            return null;
        }

        System.out.println("创建 Shapefile 驱动");
        Driver driver = gdal.GetDriverByName("ESRI Shapefile");
        if (driver == null) {
            System.err.println("缺少Shapefile驱动");
            return null;
        }

        System.out.println("创建新的 Shapefile 数据集");
        Dataset dataset = driver.Create(filePath, 0, 0, 0, gdalconstConstants.GDT_Unknown);
        if (dataset == null) {
            System.err.println("创建Shapefile数据失败");
            return null;
        }

        // 解析GeoJSON
        JSONObject jsonObject = new JSONObject(geoJsonStr);
        JSONArray features = jsonObject.getJSONArray("data");

        // 获取空间参考系和几何类型
        SpatialReference srs = new SpatialReference();
        int geometryType = ogr.wkbUnknown; // 默认未知类型
        int epsgCode = 3857; // 默认 EPSG:3857

        if (features.length() > 0) {
            String firstFeatureStr = features.getString(0);
            JSONObject firstFeature = new JSONObject(firstFeatureStr);
            JSONObject geometryJson = new JSONObject(firstFeature.getString("geometry"));

            // 从 geometry.crs 中提取空间参考系
            if (geometryJson.has("crs")) {
                JSONObject crs = geometryJson.getJSONObject("crs");
                if (crs.getString("type").equals("name")) {
                    String crsName = crs.getJSONObject("properties").getString("name");
                    if (crsName.startsWith("EPSG:")) {
                        epsgCode = Integer.parseInt(crsName.replace("EPSG:", ""));
                    }
                }
            }

            // 从 geometry.type 中提取几何类型
            String geoType = geometryJson.getString("type");
            switch (geoType) {
                case "Point":
                    geometryType = ogr.wkbPoint;
                    break;
                case "LineString":
                    geometryType = ogr.wkbLineString;
                    break;
                case "Polygon":
                    geometryType = ogr.wkbPolygon;
                    break;
                case "MultiPoint":
                    geometryType = ogr.wkbMultiPoint;
                    break;
                case "MultiLineString":
                    geometryType = ogr.wkbMultiLineString;
                    break;
                case "MultiPolygon":
                    geometryType = ogr.wkbMultiPolygon;
                    break;
                default:
                    System.err.println("不支持的几何类型: " + geoType);
                    geometryType = ogr.wkbUnknown;
            }
        }

        // 设置空间参考系
        System.out.println("设置空间参考系 EPSG:" + epsgCode);
        try {
            int result = srs.ImportFromEPSG(epsgCode);
            if (result != 0) {
                System.err.println("设置空间参考系 EPSG:" + epsgCode + " 失败，错误代码: " + result);
                dataset.delete();
                return null;
            }
            System.out.println("空间参考系 EPSG:" + epsgCode + " 设置成功: " + srs.ExportToWkt());
        } catch (RuntimeException e) {
            System.err.println("设置空间参考系失败: " + e.getMessage());
            dataset.delete();
            return null;
        }

        // 创建图层
        System.out.println("创建图层，几何类型: " + geometryType);
        Vector<String> options = new Vector<>();
        options.add("ENCODING=UTF-8");
        Layer layer = dataset.CreateLayer("layer", srs, geometryType, options);
        if (layer == null) {
            System.err.println("创建图层失败");
            dataset.delete();
            return null;
        }

        // 动态定义字段
        if (features.length() > 0) {
            String firstFeatureStr = features.getString(0);
            JSONObject firstFeature = new JSONObject(firstFeatureStr);
            JSONObject properties = firstFeature.getJSONObject("properties");

            Map<String, Integer> fieldDefs = new HashMap<>();
            Iterator<String> keys = properties.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.length() > 10) {
                    key = key.substring(0, 10);
                }
                Object value = properties.get(key);
                if (!properties.isNull(key) && value != null) {
                    FieldDefn fieldDefn;
                    if (value instanceof Integer) {
                        fieldDefn = new FieldDefn(key, ogr.OFTInteger);
                    } else {
                        fieldDefn = new FieldDefn(key, ogr.OFTString);
                        fieldDefn.SetWidth(50);
                    }
                    layer.CreateField(fieldDefn);
                    fieldDefs.put(key, fieldDefn.GetFieldType());
                }
            }

            System.out.println("遍历GeoJSON数据并添加到图层：" + features.length());
            for (int i = 0; i < features.length(); i++) {
                String featureStr = features.getString(i);
                try {
                    JSONObject feature = new JSONObject(featureStr);
                    String geometryStr = feature.getString("geometry");
                    JSONObject geometryJson = new JSONObject(geometryStr);

                    Geometry geometry = Geometry.CreateFromJson(geometryJson.toString());
                    if (geometry != null) {
                        Feature feat = new Feature(layer.GetLayerDefn());
                        feat.SetGeometry(geometry);

                        JSONObject featProperties = feature.getJSONObject("properties");
                        Iterator<String> featKeys = featProperties.keys();
                        while (featKeys.hasNext()) {
                            String key = featKeys.next();
                            if (key.length() > 10) {
                                key = key.substring(0, 10);
                            }
                            if (!featProperties.isNull(key) && fieldDefs.containsKey(key)) {
                                Object value = featProperties.get(key);
                                if (value instanceof Integer) {
                                    feat.SetField(key, (Integer) value);
                                } else {
                                    feat.SetField(key, value.toString());
                                }
                            }
                        }

                        layer.CreateFeature(feat);
                        feat.delete();
                    } else {
                        System.err.println("根据GeoJSON数据创建Geometry失败: " + geometryJson);
                    }
                } catch (Exception e) {
                    System.err.println("解析Feature失败: " + featureStr);
                    e.printStackTrace();
                }
            }
        }

        System.out.println("清理和关闭数据集");
        dataset.delete();
        return filePath;
    }


    // 新增函数：基于GeoJSON创建Shapefile
    public static String createShapefileFromGeoJSON(String geoJsonStr, String filePath) throws  Exception {
        System.setProperty("PROJ_LIB", "C:\\Program Files\\GDAL\\projlib");   // GDAL PROJ 数据路径，确保正确
        System.out.println("文件路径：" + filePath);

        if (null == GADL_DATA()) {
            System.out.println("GADL_DATA环境异常");
            return null;
        }

        System.out.println("创建 Shapefile 驱动");
        Driver driver = gdal.GetDriverByName("ESRI Shapefile");
        if (driver == null) {
            System.err.println("缺少Shapefile驱动");
            return null;
        }

        System.out.println("创建新的 Shapefile 数据集");
        Dataset dataset = driver.Create(filePath, 0, 0, 0, gdalconstConstants.GDT_Unknown);
        if (dataset == null) {
            System.err.println("创建Shapefile数据失败");
            return null;
        }

        // 创建空间参考系 EPSG:3857
        SpatialReference srs = new SpatialReference();
        srs.ImportFromEPSG(3857); // 设置为 EPSG:3857

        System.out.println("创建图层");
        Vector<String> options = new Vector<>();
        options.add("ENCODING=UTF-8");
        Layer layer = dataset.CreateLayer("layer", srs, ogr.wkbMultiLineString, options);
        if (layer == null) {
            System.err.println("创建图层失败");
            dataset.delete();
            return null;
        }

        // 解析GeoJSON
        JSONObject jsonObject = new JSONObject(geoJsonStr);
        JSONArray features = jsonObject.getJSONArray("data");

        // 动态定义字段（基于第一个 Feature 的 properties）
        if (features.length() > 0) {
            String firstFeatureStr = features.getString(0);
            JSONObject firstFeature = new JSONObject(firstFeatureStr);
            JSONObject properties = firstFeature.getJSONObject("properties");

            Map<String, Integer> fieldDefs = new HashMap<>(); // 存储字段名和类型
            Iterator<String> keys = properties.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.length() > 10) {
                    key = key.substring(0, 10); // Shapefile 字段名限制为 10 个字符
                }
                Object value = properties.get(key);
                if (!properties.isNull(key) && value != null) {
                    FieldDefn fieldDefn;
                    if (value instanceof Integer) {
                        fieldDefn = new FieldDefn(key, ogr.OFTInteger);
                    } else {
                        fieldDefn = new FieldDefn(key, ogr.OFTString);
                        fieldDefn.SetWidth(50); // 默认宽度，可根据需要调整
                    }
                    layer.CreateField(fieldDefn);
                    fieldDefs.put(key, fieldDefn.GetFieldType());
                }
            }

            System.out.println("遍历GeoJSON数据并添加到图层：" + features.length());
            for (int i = 0; i < features.length(); i++) {
                String featureStr = features.getString(i);
                try {
                    JSONObject feature = new JSONObject(featureStr);
                    String geometryStr = feature.getString("geometry");
                    JSONObject geometryJson = new JSONObject(geometryStr);

                    Geometry geometry = Geometry.CreateFromJson(geometryJson.toString());
                    if (geometry != null) {
                        Feature feat = new Feature(layer.GetLayerDefn());
                        feat.SetGeometry(geometry);

                        JSONObject featProperties = feature.getJSONObject("properties");
                        Iterator<String> featKeys = featProperties.keys();
                        while (featKeys.hasNext()) {
                            String key = featKeys.next();
                            if (key.length() > 10) {
                                key = key.substring(0, 10); // 截断字段名
                            }
                            if (!featProperties.isNull(key) && fieldDefs.containsKey(key)) {
                                Object value = featProperties.get(key);
                                if (value instanceof Integer) {
                                    feat.SetField(key, (Integer) value);
                                } else {
                                    feat.SetField(key, value.toString());
                                }
                            }
                        }

                        layer.CreateFeature(feat);
                        feat.delete();
                    } else {
                        System.err.println("根据GeoJSON数据创建Geometry失败: " + geometryJson);
                    }
                } catch (Exception e) {
                    System.err.println("解析Feature失败: " + featureStr);
                    e.printStackTrace();
                }
            }
        }

        System.out.println("清理和关闭数据集");
        dataset.delete();
        return filePath;

    }


    public static void main(String[] args) throws Exception {

//        String geoJsonStr = "{ \"msg\": \"操作成功\", \"code\": 200, \"data\": [{\"type\": \"Feature\", \"geometry\": \"{\\\"type\\\":\\\"MultiLineString\\\",\\\"crs\\\":{\\\"type\\\":\\\"name\\\",\\\"properties\\\":{\\\"name\\\":\\\"EPSG:3857\\\"}},\\\"coordinates\\\":[[[13319530.054225404,3059617.087548074,0],[13319530.302356558,3059618.306922432,0],[13319527.576142233,3059618.794721956,0]]]}\", \"properties\": {\"id\": 7, \"geoguid\": \"385e44d3-b9b9-47ab-8830-7c28032c2e57\", \"smscode\": \"蒸汽管\"}}]}";
//        String filePath = "output.shp";
//        String result = createShapefileFromGeoJSONstr(geoJsonStr, filePath);
//        System.out.println("Shapefile创建结果: " + result);

        convertShpToDxfExt("E:\\项目同步\\hebei-port\\output.shp",    "E:\\项目同步\\hebei-port\\output.dxf", "");



//        String userDir = System.getProperty("user.dir");
//        List<String> geometries = new ArrayList<>();
//        geometries.add("MULTIPOLYGON(((119.65392780709874 26.481976616150234,119.65392414890277 26.48195797015154,119.65401993553678 26.481943124953716,119.65403790462585 26.48203470886483,119.6540157835991 26.48203818759663,119.6540264825702 26.482093832264354,119.65345424886834 26.482183483500307,119.65343769797175 26.482099364289695,119.65341620137133 26.48210259602422,119.65340401774245 26.48203955973321,119.65350034207233 26.4820246029891,119.65350384870555 26.48204397966623,119.65392780709874 26.481976616150234)))");
//        geometries.add("MULTIPOLYGON(((119.65326126462526 26.48104077194597,119.65330785340768 26.48103280605261,119.65331555419561 26.48106929442224,119.6532689658203 26.481077262488782,119.65326126462526 26.48104077194597)))");
//        String fileName = "layer_" + System.currentTimeMillis();
//        String filePath = userDir + "\\" + fileName + ".shp";
//        String result = createShapefile(geometries, filePath);
//        if (result != null) {
//            convertShpToDxf(result, userDir + "\\" + fileName + ".dxf", "https://localhost/layer/" + fileName + ".dxf");
//        } else {
//            System.out.println("创建失败.");
//        }
    }
}
