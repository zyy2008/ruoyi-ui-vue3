package com.ruoyi.util;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PublishLayer {

    // GeoServer REST API的基本URL
    private static final String GEOSERVER_REST_URL = "http://124.221.121.154:8081/geoserver/rest";
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "35ffc39e";

    public static void main(String[] args) {
        String workspace = "test";  // 工作空间
        String dataStoreName = "testJava";  // 数据源名称
        String layerName = "CL_X";  // PostGIS数据库中的表名
        String layerTitle = "CL_X_styleName";  // 图层的标题
        String srs = "EPSG:4326";  // 坐标系统
        String styleName="CL_X";
//
//        try {
//            List<String> layers = getLayersInWorkspace(workspace);
//            System.out.println("命名空间 '" + workspace + "' 下的图层列表:");
//            for (String layer : layers) {
//                System.out.println("- " + layer);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            boolean exists = layerExists(workspace, layerName);
//            if (exists) {
//                System.out.println("图层 '" + layerName + "' 已经发布。");
//            } else {
//                System.out.println("图层 '" + layerName + "' 尚未发布。");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            String layerInfo = getLayerInfo(layerName);
//            if (layerInfo != null) {
//                System.out.println("图层信息: " + layerInfo);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            createFeatureType(workspace, dataStoreName, layerName, layerTitle, srs,styleName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改图层的默认样式
     *
     * @param layerName  图层名称
     * @param styleName  默认样式名称
     * @throws IOException 如果发生 IO 错误
     */
    public static void updateLayerDefaultStyle(String layerName, String styleName) throws IOException {
        String url = GEOSERVER_REST_URL + "/layers/" + layerName; // 修改图层的 URL

        // 构建请求体 (XML 格式)
        String layerXml = "<layer>" +
                "<defaultStyle>" +
                "<name>" + styleName + "</name>" +
                "</defaultStyle>" +
                "</layer>";

        // 创建 HTTP 客户端并发送 PUT 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut put = new HttpPut(url);
            put.setHeader("Content-Type", "application/xml");
            put.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 设置请求体
            StringEntity entity = new StringEntity(layerXml);
            put.setEntity(entity);

            // 执行请求
            HttpResponse response = client.execute(put);
            String responseString = EntityUtils.toString(response.getEntity());

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("图层 '" + layerName + "' 的默认样式更新为 '" + styleName + "' 成功!");
            } else {
                System.out.println("修改图层默认样式失败，错误信息: " + responseString);
            }
        }
    }
    /**
     * 创建一个新的 FeatureType
     */
    public static void createFeatureType(String workspace, String dataStoreName, String layerName, String layerTitle, String srs,String styleName) throws IOException {
        // 检查图层是否已存在
        if (layerExists(workspace, layerName)) {
            System.out.println("图层 '" + layerName + "' 已存在...");
            putLayer(workspace,dataStoreName,layerName,layerTitle,srs,styleName);
            //更新样式
            updateLayerDefaultStyle(layerName,styleName);
            return;
        }
        // 构建 FeatureType 的 XML 配置
        String featureTypeXml = "<featureType>" +
                "<name>" + layerName + "</name>" +
                "<title>" + layerTitle + "</title>" +
                "<srs>" + srs + "</srs>" +
                "</featureType>";

        // 请求 URL：发布 FeatureType
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/datastores/" + dataStoreName + "/featuretypes";

        // 创建 HTTP 客户端并发送 POST 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/xml");
            post.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 设置请求体
            StringEntity entity = new StringEntity(featureTypeXml);
            post.setEntity(entity);

            // 执行请求
            HttpResponse response = client.execute(post);
            String responseString = EntityUtils.toString(response.getEntity());

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 201) {
                System.out.println("图层创建成功!");

                //更新样式
                updateLayerDefaultStyle(layerName,styleName);
            } else {
                System.out.println("创建图层失败，错误信息: " + responseString);
            }
        }
    }


    /**
     * 更新已存在的图层，并指定样式（使用 XML 格式）
     * @param workspace        工作空间名称
     * @param storeName        数据存储名称
     * @param layerName        图层名称
     * @param layerTitle       图层标题
     * @param srs              SRS（空间参考系统）值
     * @param styleName        样式名称
     * @throws IOException     如果出现 I/O 错误
     */
    private static void putLayer(String workspace, String storeName, String layerName, String layerTitle, String srs, String styleName) throws IOException {
        // 构建更新图层的 XML 配置，并添加样式配置
        String featureTypeXml = "<featureType>" +
                "<name>" + layerName + "</name>" +
                "<title>" + layerTitle + "</title>" +
                "<srs>" + srs + "</srs>" +
                "</featureType>";

        // 请求 URL：更新 FeatureType
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/datastores/" + storeName + "/featuretypes/" + layerName;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut put = new HttpPut(url);
            put.setHeader("Content-Type", "application/xml");
            put.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 设置请求体
            StringEntity entity = new StringEntity(featureTypeXml);
            put.setEntity(entity);

            // 执行请求
            HttpResponse response = client.execute(put);
            String responseString = EntityUtils.toString(response.getEntity());

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("图层 '" + layerName + "' 更新成功!");
            } else {
                System.out.println("更新图层失败，错误信息: " + responseString);
            }
        }
    }


//    // 更新已存在的图层
//    private static void putLayer(String workspace, String storeName, String layerName, String layerTitle, String srs,String styleName) throws IOException {
//        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/datastores/" + storeName + "/featuretypes/" + layerName;
//
//        // 构建更新图层的 JSON 配置，并添加样式配置
//        String featureTypeJson = "{\n" +
//                "  \"featureType\": {\n" +
//                "    \"name\": \"" + layerName + "\",\n" +
//                "    \"title\": \"" + layerTitle + "\",\n" +
//                "    \"srs\": \"" + srs + "\",\n" +
//                "    \"defaultStyle\": {\n" +
//                "      \"name\": \"" + styleName + "\"\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//
//        try (CloseableHttpClient client = HttpClients.createDefault()) {
//            HttpPut put = new HttpPut(url);
//            put.setHeader("Content-Type", "application/json");
//            put.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));
//
//            StringEntity entity = new StringEntity(featureTypeJson);
//            put.setEntity(entity);
//
//            HttpResponse response = client.execute(put);
//            String responseString = EntityUtils.toString(response.getEntity());
//
//            if (response.getStatusLine().getStatusCode() == 200) {
//                System.out.println("图层 '" + layerName + "' 更新成功!");
//            } else {
//                System.out.println("更新图层失败，错误信息: " + responseString);
//            }
//        }
//    }
    /**
     * 获取指定图层的配置信息--ok
     *
     * @param layerName 图层名称
     * @return 图层信息（JSON 格式字符串）
     * @throws IOException
     */
    public static String getLayerInfo(String layerName) throws IOException {
        // 构建请求 URL
        String url = GEOSERVER_REST_URL + "/layers/" + layerName + ".json";

        // 创建 HTTP 客户端并发送 GET 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

            // 判断请求是否成功
            if (statusCode == 200) {
                System.out.println("获取图层信息成功！");
                return responseString;
            } else if (statusCode == 404) {
                System.out.println("图层 '" + layerName + "' 不存在！");
                return null;
            } else {
                System.out.println("获取图层信息失败，错误信息: " + responseString);
                return null;
            }
        }
    }
    /**
     * 判断指定图层是否已经发布--ok
     *
     * @param workspace 工作空间名称
     * @param layerName 图层名称
     * @return 如果图层已发布，则返回 true；否则返回 false
     * @throws IOException 请求失败时抛出异常
     */
    public static boolean layerExists(String workspace, String layerName) throws IOException {
        // 获取指定命名空间下的所有图层
        List<String> layers = getLayersInWorkspace(workspace);

        // 检查目标图层是否存在于列表中
        for (String layer : layers) {
            if (layer.equals(layerName)) {
                System.out.println("图层 '" + layerName + "' 已发布在命名空间 '" + workspace + "' 下。");
                return true;
            }
        }

        System.out.println("图层 '" + layerName + "' 未发布在命名空间 '" + workspace + "' 下。");
        return false;
    }

    /**
     * 获取指定命名空间下的所有图层--ok
     *
     * @param workspace 工作空间名称
     * @return 图层名称列表
     * @throws IOException 请求失败时抛出异常
     */
    public static List<String> getLayersInWorkspace(String workspace) throws IOException {
        List<String> layerList = new ArrayList<>();

        // 构建 URL 获取指定命名空间的图层
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/layers";

        // 创建 HTTP 客户端并发送 GET 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                // 解析 JSON 响应
                String responseString = EntityUtils.toString(response.getEntity());
                JSONObject jsonResponse = JSONObject.parseObject(responseString);

                // 检查 JSON 中是否包含 "layers" 对象
                if (jsonResponse != null && jsonResponse.containsKey("layers")) {
                    JSONObject layersObject = jsonResponse.getJSONObject("layers");

                    // 检查 "layers" 对象中是否包含 "layer" 数组
                    if (layersObject != null && layersObject.containsKey("layer")) {
                        JSONArray layersArray = layersObject.getJSONArray("layer");

                        // 提取每个图层的名称
                        for (int i = 0; i < layersArray.size(); i++) {
                            JSONObject layer = layersArray.getJSONObject(i);
                            if (layer.containsKey("name")) {
                                layerList.add(layer.getString("name"));
                            }
                        }
                    } else {
                        System.err.println("JSON 响应中缺少 'layer' 数组。");
                    }
                } else {
                    System.err.println("JSON 响应中缺少 'layers' 对象。");
                }
            } else {
                // 如果返回其他状态码，记录错误信息
                String responseString = EntityUtils.toString(response.getEntity());
                System.err.println("获取图层列表失败，状态码: " + statusCode + "，响应内容: " + responseString);
                throw new IOException("Failed to fetch layers. HTTP Status: " + statusCode);
            }
        }

        return layerList;
    }


    /**
     * 检查工作空间是否已存在
     */
    public static boolean workspaceExists(String workspace) throws IOException {
        // 获取工作空间信息的 URL
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + ".json";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseString = EntityUtils.toString(response.getEntity());

            if (statusCode == 200) {
                System.out.println("工作空间 '" + workspace + "' 已存在！");
                return true;
            } else {
                System.out.println("工作空间 '" + workspace + "' 不存在，状态码: " + statusCode);
                System.out.println("响应内容: " + responseString);
                return false;
            }
        }
    }

    /**
     * 创建工作空间
     */
    private static void createWorkspace(String workspace) throws IOException {
        // 创建工作空间的XML配置
        String workspaceXml = "<workspace><name>" + workspace + "</name></workspace>";

        // 请求 URL
        String url = GEOSERVER_REST_URL + "/workspaces";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/xml");
            post.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 设置请求体
            StringEntity entity = new StringEntity(workspaceXml);
            post.setEntity(entity);

            // 执行请求
            HttpResponse response = client.execute(post);
            String responseString = EntityUtils.toString(response.getEntity());

            if (response.getStatusLine().getStatusCode() == 201) {
                System.out.println("工作空间 '" + workspace + "' 创建成功！");
            } else {
                System.out.println("创建工作空间失败，错误信息: " + responseString);
            }
        }
    }

}


