package com.ruoyi.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.admin.domain.bo.PublishLayerBo;

import java.util.ArrayList;
import java.util.List;

public class GeoServerHttpClient {

    private static final String RESTURL = "http://124.221.121.154:8081/geoserver";
    private static final String RESTUSER = "admin";
    private static final String RESTPW = "35ffc39e";

    /**
     * 获取GeoServer中所有样式的列表
     *
     * @param workspace 工作区名称（如果为null，则获取全局样式列表）
     * @return 样式名称列表，如果获取失败返回空列表
     */
    public static List<String> getStyleList(String workspace) {
        List<String> styleList = new ArrayList<>();
        try {
            // 构造获取样式列表的URL
            String styleUrl;
            if (workspace == null || workspace.isEmpty()) {
                // 获取全局样式列表
                styleUrl = RESTURL + "/rest/styles.json";
            } else {
                // 获取指定工作区的样式列表
                styleUrl = RESTURL + "/rest/workspaces/" + workspace + "/styles.json";
            }

            // 发送GET请求获取样式列表
            HttpResponse response = HttpRequest.get(styleUrl)
                    .basicAuth(RESTUSER, RESTPW)
                    .execute();

            if (response.isOk()) {
                // 解析JSON响应
                String responseBody = response.body();
                JSONObject jsonResponse = JSONUtil.parseObj(responseBody);
                JSONArray stylesArray = jsonResponse.getJSONObject("styles").getJSONArray("style");

                // 提取样式名称
                for (Object styleObj : stylesArray) {
                    JSONObject style = (JSONObject) styleObj;
                    String styleName = style.getStr("name");
                    styleList.add(styleName);
                }
            } else {
                System.out.println("====获取样式列表失败，状态码: " + response.getStatus() + "====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return styleList;
    }

    /**
     * 批量发布
     *
     * @param data         数据
     * @param workspace    工作空间
     * @param datastore    仓库
     * @param forcePublish 是否覆盖
     * @return
     */
    public static boolean publishLyaerList(List<PublishLayerBo> data, String workspace, String datastore, boolean forcePublish) {
        try {
            for (PublishLayerBo publishLayerBo : data) {
                publishLayer(
                        workspace, // 工作区名称
                        datastore, // 数据存储名称
                        publishLayerBo.getTableName(), // 图层名称
                        publishLayerBo.getName(), // 图层标题
                        publishLayerBo.getName(), // 图层描述
                        "EPSG:4326", // 空间参考系统
                        publishLayerBo.getStyle(), // 默认样式名称
                        forcePublish // 是否强制删除并重新发布
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 发布图层到GeoServer
     *
     * @param workspace    工作区名称
     * @param datastore    数据存储名称
     * @param layerName    图层名称
     * @param title        图层标题
     * @param description  图层描述
     * @param srs          空间参考系统（如EPSG:4326）
     * @param styleName    默认样式名称
     * @param forcePublish 是否覆盖并重新发布
     * @return 是否发布成功
     */
    public static boolean publishLayer(String workspace, String datastore, String layerName, String title,
                                       String description, String srs, String styleName, boolean forcePublish) {
        try {
            // 检查图层是否已存在
//            if (layerExists(workspace, layerName) && forcePublish) {
            if (layerExists(workspace, layerName)) {
                System.out.println("====图层：" + workspace + ":" + datastore + ":" + layerName + " 已存在====");
                // 更新已存在的图层
                return updateLayer(layerName, styleName);
            } else {
                System.out.println("====图层：" + workspace + ":" + datastore + ":" + layerName + " 不存在，准备发布====");
                // 构造FeatureType的XML配置
                String featureTypeXml = "<featureType>"
                        + "<name>" + layerName + "</name>"
                        + "<title>" + title + "</title>"
                        + "<abstract>" + description + "</abstract>"
                        + "<srs>" + srs + "</srs>"
                        + "<keywords>"
                        + "<string>test</string>"
                        + "<string>layer</string>"
                        + "</keywords>"
                        + "</featureType>";

                // 发布图层的URL
                String publishUrl = RESTURL + "/rest/workspaces/" + workspace + "/datastores/" + datastore + "/featuretypes";

                // 发送POST请求发布图层
                HttpResponse response = HttpRequest.post(publishUrl)
                        .basicAuth(RESTUSER, RESTPW)
                        .header("Content-Type", "application/xml")
                        .body(featureTypeXml)
                        .execute();

                if (response.isOk()) {
                    updateLayer(layerName, styleName);
                    return true;
                } else {
                    System.out.println("====图层：" + workspace + ":" + datastore + ":" + layerName + " 发布失败，状态码: " + response.getStatus() + "====");
                    System.out.println(response.body());
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查图层是否已存在
     *
     * @param workspace 工作区名称
     * @param layerName 图层名称
     * @return 图层是否存在
     */
    private static boolean layerExists(String workspace, String layerName) {
        try {
            // 获取图层信息的URL
            String layerUrl = RESTURL + "/rest/workspaces/" + workspace + "/layers/" + layerName + ".json";

            // 发送GET请求检查图层是否存在
            HttpResponse response = HttpRequest.get(layerUrl)
                    .basicAuth(RESTUSER, RESTPW)
                    .execute();

            return response.isOk();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新已存在的图层，并指定样式（使用 XML 格式）
     *
     * @param layerName 图层名称
     * @param styleName 样式名称
     */
    private static boolean updateLayer(String layerName, String styleName) {
        // 构建请求体 (XML 格式)
        String layerXml = "<layer>" +
                "<defaultStyle>" +
                "<name>" + styleName + "</name>" +
                "</defaultStyle>" +
                "</layer>";

        // 请求 URL：更新 FeatureType
        String publishUrl = RESTURL + "/rest/layers/" + layerName;

        // 发送POST请求发布图层
        HttpResponse response = HttpRequest.put(publishUrl)
                .basicAuth(RESTUSER, RESTPW)
                .header("Content-Type", "application/xml")
                .body(layerXml)
                .execute();

        if (response.isOk()) {
            System.out.println("====图层：" + layerName + " 发布成功====");
            return true;
        } else {
            System.out.println("====图层：" + layerName + " 发布失败，状态码: " + response.getStatus() + "====");
            System.out.println(response.body());
            return false;
        }
    }

    public static void main(String[] args) {
        // 测试发布图层（强制删除并重新发布）
//        publishLayer(
//                "test", // 工作区名称
//                "test", // 数据存储名称
//                "DLGX_D_JSONToFeatures", // 图层名称
//                "测试发布", // 图层标题
//                "描述1231231123", // 图层描述
//                "EPSG:4326", // 空间参考系统
//                "point", // 默认样式名称
//                true // 是否强制删除并重新发布
//        );
        // 测试获取全局样式列表
//        List<String> globalStyles = getStyleList(null);
//        System.out.println("全局样式列表：");
//        globalStyles.forEach(System.out::println);
    }
}