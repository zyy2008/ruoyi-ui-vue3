package com.ruoyi.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostGISDataSource {


    private static final String GEOSERVER_REST_URL = "http://124.221.121.154:8081/geoserver/rest";
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "35ffc39e";

    public static void main(String[] args) throws Exception {

//        try {
//            List<String> workspaces = getAllWorkspaces();
//            System.out.println("GeoServer中的工作空间列表:");
//            for (String workspace : workspaces) {
//                System.out.println("- " + workspace);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String workspace = "test";
//        String dataStoreName = "testJava";
//        String dbHost = "124.221.121.154";
//        String dbPort = "5849";
//        String dbName = "RuoYi";
//        String dbUser = "postgres";
//        String dbPassword = "123456";
//        addPostGISDataSource(
//                workspace, //
//                dataStoreName, //
//                dbHost, //
//                dbPort,
//                dbName,
//                dbUser, //
//                dbPassword//
//        );


        try {
            List<String> dataSources = getDataSources("test"); // 替换为实际工作区名称
            System.out.println("数据源列表:");
            for (String dataSource : dataSources) {
                System.out.println("- " + dataSource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定工作区内的所有数据源--ok
     *
     * @param workspace 工作区名称
     * @return 数据源名称列表
     * @throws IOException 请求或解析失败时抛出
     */
    public static List<String> getDataSources(String workspace) throws IOException {
        List<String> dataSourceList = new ArrayList<>();
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/datastores";

        // 创建 HTTP 客户端
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder()
                    .encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(get);
            String responseString = EntityUtils.toString(response.getEntity());

            // 打印返回的完整响应（调试用）
            System.out.println("GeoServer API 响应: " + responseString);

            // 检查响应状态
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析 JSON 响应
                JSONObject jsonResponse = JSON.parseObject(responseString);
                if (jsonResponse.containsKey("dataStores")) {
                    jsonResponse.getJSONObject("dataStores")
                            .getJSONArray("dataStore")
                            .forEach(item -> {
                                JSONObject dataStore = (JSONObject) item;
                                dataSourceList.add(dataStore.getString("name"));
                            });
                } else {
                    System.out.println("响应中未找到 dataStores 字段！");
                }
            } else {
                System.out.println("获取数据源列表失败，状态码: " + response.getStatusLine().getStatusCode());
                System.out.println("响应内容: " + responseString);
            }
        }

        return dataSourceList;
    }

    /**--ok
     * 获取 GeoServer 添加数据源
     */
    public static void addPostGISDataSource(String workspace, String dataStoreName, String dbHost, String dbPort,
                                            String dbName, String dbUser, String dbPassword) throws IOException {
        // 创建 JSON 对象表示数据源配置
        JSONObject connectionParameters = new JSONObject();
        connectionParameters.put("host", dbHost);
        connectionParameters.put("port", dbPort);
        connectionParameters.put("database", dbName);
        connectionParameters.put("user", dbUser);
        connectionParameters.put("passwd", dbPassword);
        connectionParameters.put("dbtype", "postgis");

        JSONObject dataStore = new JSONObject();
        dataStore.put("name", dataStoreName);
        dataStore.put("connectionParameters", connectionParameters);

        JSONObject json = new JSONObject();
        json.put("dataStore", dataStore);

        // 请求 URL
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/datastores";

        // 创建 HTTP 客户端并发送 POST 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder()
                    .encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 设置请求体
            StringEntity entity = new StringEntity(json.toString());
            post.setEntity(entity);

            // 执行请求
            HttpResponse response = client.execute(post);
            String responseString = EntityUtils.toString(response.getEntity());

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 201) {
                System.out.println("PostGIS 数据源添加成功!");
            } else {
                System.out.println("添加数据源失败，错误信息: " + responseString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取 GeoServer 中所有工作空间的列表
     *
     * @return 工作空间名称列表--ok
     * @throws IOException 请求或解析失败时抛出
     */
    public static List<String> getAllWorkspaces() throws IOException {
        List<String> workspaceList = new ArrayList<>();
        String url = GEOSERVER_REST_URL + "/workspaces";

        // 创建 HTTP 客户端
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder()
                    .encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(get);
            String responseString = EntityUtils.toString(response.getEntity());

            // 检查响应状态
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析 JSON 响应
                JSONObject jsonResponse = JSON.parseObject(responseString);
                if (jsonResponse.containsKey("workspaces")) {
                    JSONObject workspaces = jsonResponse.getJSONObject("workspaces");
                    workspaces.getJSONArray("workspace").forEach(item -> {
                        JSONObject workspace = (JSONObject) item;
                        workspaceList.add(workspace.getString("name"));
                    });
                }
            } else {
                System.out.println("获取工作空间列表失败，状态码: " + response.getStatusLine().getStatusCode());
            }
        }

        return workspaceList;
    }
}
