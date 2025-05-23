package com.ruoyi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class PublishStyle {

    private static final String GEOSERVER_REST_URL = "http://124.221.121.154:8081/geoserver/rest";
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "35ffc39e";

    /**
     * 获取所有样式
     */
    public static void getAllStyles() throws IOException {
        String url = GEOSERVER_REST_URL + "/styles";  // 获取所有样式的URL

        // 创建 HTTP 客户端并发送 GET 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(get);
            String responseString = EntityUtils.toString(response.getEntity());

            // 打印原始的响应字符串，以便调试
            System.out.println("响应内容: " + responseString);

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析返回的 JSON 响应
                JSONObject jsonResponse = JSONObject.parseObject(responseString);

                // 检查 jsonResponse 是否包含 styles 字段
                if (jsonResponse.containsKey("styles")) {
                    JSONObject styles = jsonResponse.getJSONObject("styles");

                    // 获取 style 数组
                    if (styles.containsKey("style")) {
                        JSONArray stylesArray = styles.getJSONArray("style");

                        // 打印所有样式名称
                        System.out.println("所有样式列表:");
                        for (int i = 0; i < stylesArray.size(); i++) {
                            JSONObject style = stylesArray.getJSONObject(i);
                            String styleName = style.getString("name");
                            System.out.println("样式名称: " + styleName);
                        }
                    } else {
                        System.out.println("没有找到 style 数组");
                    }
                } else {
                    System.out.println("没有找到 styles 字段");
                }
            } else {
                System.out.println("获取样式列表失败，错误信息: " + responseString);
            }
        }
    }

    /**
     * 上传并添加新的样式，并指定样式名称
     * @param styleFile 样式文件（.sld 文件）
     * @param styleName 指定的样式名称
     * @throws IOException
     */
    public static void addStyleWithName(File styleFile, String styleName,String fileName) throws IOException {
        // 创建样式元数据，包含样式名称和文件名
        String styleMetadata = "<style>" +
                "<name>" + styleName + "</name>" +
                "<filename>" + fileName + "</filename>" +
                "</style>";

        // 1. 首先使用 POST 请求创建样式元数据
        String urlMetadata = GEOSERVER_REST_URL + "/styles";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost postMetadata = new HttpPost(urlMetadata);
            postMetadata.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));
            postMetadata.setHeader("Content-Type", "application/xml");

            // 设置样式元数据请求体
            StringEntity metadataEntity = new StringEntity(styleMetadata);
            postMetadata.setEntity(metadataEntity);

            // 执行请求
            HttpResponse responseMetadata = client.execute(postMetadata);
            String responseMetadataString = EntityUtils.toString(responseMetadata.getEntity());

            // 处理响应：检查元数据创建是否成功
            if (responseMetadata.getStatusLine().getStatusCode() != 201) {
                System.out.println("创建样式元数据失败，错误信息: " + responseMetadataString);
                return;
            }
        }

        // 2. 然后使用 PUT 请求上传样式文件
        String urlStyleFile = GEOSERVER_REST_URL + "/styles/" + styleName + ".sld";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut putStyleFile = new HttpPut(urlStyleFile);
            putStyleFile.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));
            putStyleFile.setHeader("Content-Type", "application/vnd.ogc.sld+xml");

            // 创建文件实体，用于上传样式文件
            FileEntity fileEntity = new FileEntity(styleFile);
            putStyleFile.setEntity(fileEntity);

            // 执行请求
            HttpResponse responseStyleFile = client.execute(putStyleFile);
            String responseStyleFileString = EntityUtils.toString(responseStyleFile.getEntity());

            // 处理响应：检查文件上传是否成功
            if (responseStyleFile.getStatusLine().getStatusCode() == 200) {
                System.out.println("样式 " + styleName + " 创建并上传成功！");
            } else {
                System.out.println("上传样式文件失败，错误信息: " + responseStyleFileString);
            }
        }
    }


    /**
     * 删除指定名称的样式
     * @param styleName 要删除的样式名称
     * @param purge 是否删除样式文件
     * @param recurse 是否删除该样式在图层中的引用
     * @throws IOException
     */
    public static void deleteStyle(String styleName, boolean purge, boolean recurse) throws IOException {
        // 构建删除样式的URL
        String url = GEOSERVER_REST_URL + "/styles/" + styleName;

        // 在URL中添加查询参数
        StringBuilder urlWithParams = new StringBuilder(url);
        if (purge) {
            urlWithParams.append("?purge=true");
        }
        if (recurse) {
            // 如果有 purge 参数，使用 "&" 连接，如果没有，使用 "?" 开始查询参数
            urlWithParams.append(purge ? "&recurse=true" : "?recurse=true");
        }

        // 创建 HTTP 客户端并发送 DELETE 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete delete = new HttpDelete(urlWithParams.toString());
            delete.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(delete);
            String responseString = EntityUtils.toString(response.getEntity());

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("样式 '" + styleName + "' 删除成功!");
            } else {
                System.out.println("删除样式失败，错误信息: " + responseString);
            }
        }
    }


    /**
     * 获取指定工作空间的样式列表
     * @param workspace   工作空间名称
     * @throws IOException 如果发生 IO 错误
     */
    public static void getStylesInWorkspace(String workspace) throws IOException {
        String url = GEOSERVER_REST_URL + "/workspaces/" + workspace + "/styles";  // 获取指定工作空间的样式列表

        // 创建 HTTP 客户端并发送 GET 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((ADMIN_USER + ":" + ADMIN_PASSWORD).getBytes()));

            // 执行请求
            HttpResponse response = client.execute(get);
            String responseString = EntityUtils.toString(response.getEntity());

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("工作空间 '" + workspace + "' 的样式列表: ");
                // 解析 JSON 响应
                JSONObject jsonResponse = JSONObject.parseObject(responseString);
                JSONArray stylesArray = jsonResponse.getJSONObject("styles").getJSONArray("style");

                for (int i = 0; i < stylesArray.size(); i++) {
                    JSONObject style = stylesArray.getJSONObject(i);
                    System.out.println("样式名称: " + style.getString("name"));
                    System.out.println("样式 URL: " + style.getString("href"));
                }
            } else {
                System.out.println("获取样式列表失败，错误信息: " + responseString);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 获取所有样式
       // getAllStyles();


        // 指定样式文件路径
//        File styleFile = new File("F:\\邱岭峰\\desheng_shp\\CL_D.sld");
//
//        // 添加样式
//        addStyleWithName(styleFile,"javaPOST","CL_D.sld");


        // deleteStyle("javaPOST",true,true);

        try {
            // 示例：获取指定工作空间的样式列表
            getStylesInWorkspace("ne");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
