package com.ruoyi.util;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeoServerToDXF {

    static String wpsUrl = "http://124.221.121.154:8081/geoserver/wps";

    public static void main(String[] args) {
        try {
//            getWPSDataFromGeoServer("ne:DLGX_X_JSONToFeatures",
//                    "119.64853 26.491262,119.653005 26.491262,119.653203 26.486671,119.649184 26.486468,119.64853 26.491262");
            getWPSDataFromGeoServer("ne:DLGX_X_JSONToFeatures",
                    "119.658256 26.493094,119.663839 26.492006,119.662231 26.488635,119.657854 26.488349,119.654429 26.48988,119.654293 26.491187,119.658256 26.493094");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getWPSDataFromGeoServer(String layerName, String data) {
        return getWPSDataFromGeoServer(wpsUrl, layerName, data);
    }

    public static List<String> getWPSDataFromGeoServer(String wpsUrl, String layerName, String target) {
        System.out.println("请求地址：" + wpsUrl + "  请求图层：" + layerName + "  请求参数：" + target);
        List<String> returnData = new ArrayList<>();
        try {
            target = toNestedArray(target);
            // WPS 请求体
            String wpsRequest = "<wps:Execute version=\"1.0.0\" service=\"WPS\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    "             xmlns=\"http://www.opengis.net/wps/1.0.0\" " +
                    "             xmlns:wfs=\"http://www.opengis.net/wfs\" " +
                    "             xmlns:wps=\"http://www.opengis.net/wps/1.0.0\" " +
                    "             xmlns:ows=\"http://www.opengis.net/ows/1.1\" " +
                    "             xmlns:gml=\"http://www.opengis.net/gml\" " +
                    "             xmlns:ogc=\"http://www.opengis.net/ogc\" " +
                    "             xmlns:wcs=\"http://www.opengis.net/wcs/1.1.1\" " +
                    "             xmlns:xlink=\"http://www.w3.org/1999/xlink\" " +
                    "             xsi:schemaLocation=\"http://www.opengis.net/wps/1.0.0 http://schemas.opengis.net/wps/1.0.0/wpsAll.xsd\"> " +
                    "  <ows:Identifier>gs:Clip</ows:Identifier>" +
                    "  <wps:DataInputs>" +
                    "    <wps:Input>" +
                    "      <ows:Identifier>features</ows:Identifier>" +
                    "      <wps:Reference mimeType=\"text/xml\" xlink:href=\"http://geoserver/wfs\" method=\"POST\">" +
                    "        <wps:Body>" +
                    "          <wfs:GetFeature service=\"WFS\" cql_filter=\"classify = '碱液管(JY)'\" version=\"1.0.0\" outputFormat=\"GML2\" xmlns:geonode=\"http://www.geonode.org/\">  " +
                    "           <ogc:Filter>" +
                    "            <ogc:PropertyIsEqualTo>" +
                    "            <ogc:PropertyName>classify</ogc:PropertyName>" +
                    "            <ogc:Literal>碱液管(JY)</ogc:Literal>" +
                    "            </ogc:PropertyIsEqualTo>" +
                    "           </ogc:Filter>" +
                    "            <wfs:Query typeName=\"" + layerName + "\"/>" +
                    "          </wfs:GetFeature>" +
                    "        </wps:Body>" +
                    "      </wps:Reference>" +
                    "    </wps:Input>" +
                    "    <wps:Input>" +
                    "      <ows:Identifier>clip</ows:Identifier>" +
                    "      <wps:Data>" +
                    "        <wps:ComplexData mimeType=\"application/json\"><![CDATA[{\"type\":\"Polygon\",\"coordinates\":" + target + "}]]></wps:ComplexData>" +
                    "      </wps:Data>" +
                    "    </wps:Input>" +
                    "  </wps:DataInputs>" +
                    "  <wps:ResponseForm>" +
                    "    <wps:RawDataOutput mimeType=\"application/x-shapefile\">" +
                    "      <ows:Identifier>result</ows:Identifier>" +
                    "    </wps:RawDataOutput>" +
                    "  </wps:ResponseForm>" +
                    "</wps:Execute>";
            HttpResponse response = HttpRequest.post(wpsUrl)
                    .body(wpsRequest)
                    .header("Content-Type", "application/json")
                    .execute();
            // 检查 HTTP 响应状态
            if (response.getStatus() != 200) {
                System.out.println("请求失败，HTTP 状态: " + response.getStatus());
                System.out.println("响应内容: " + response.body());
                System.out.println("WPS 请求失败！");
            } else {
                // 检查内容类型
                String contentType = response.header("Content-Type");
//                System.out.println("响应内容类型: " + contentType);
//            System.out.println("响应数据：" + response.body());
                Map<String, Object> responseMap = XmlUtil.xmlToMap(response.body());
//            System.out.println("解析前数据：" + responseMap.toString());
                List<Map<String, Object>> featureMemberList = (List<Map<String, Object>>) responseMap.get("gml:featureMember");
                for (Map<String, Object> featureMemberMap : featureMemberList) {
                    Map<String, Object> featureLayerNameMap = (Map<String, Object>) featureMemberMap.get("feature:" + layerName.split(":")[1]);
                    Map<String, Object> featureGeom = (Map<String, Object>) featureLayerNameMap.get("feature:geom");
                    Map<String, Object> geomMultiLineString = (Map<String, Object>) featureGeom.get("gml:MultiLineString");
                    Map<String, Object> geomLineStringMember = (Map<String, Object>) geomMultiLineString.get("gml:lineStringMember");
                    Map<String, Object> geomLineString = (Map<String, Object>) geomLineStringMember.get("gml:LineString");
                    String coordinates = (String) geomLineString.get("gml:coordinates");
//                System.out.println("解析前数据：" + coordinates);
                    coordinates = toWktMultipolygon(coordinates);
//                System.out.println("解析后WKT数据：" + coordinates);
                    returnData.add(coordinates);
                }
            }
        } catch (Exception e) {
            System.out.println("解析异常" + e.getMessage());
        }
        return returnData;
    }

    public static String toNestedArray(String coordinatesStr) {
        // 使用逗号分隔每一对坐标
        String[] coordinatePairs = coordinatesStr.split(",");
        // 构建内层的 JSONArray 存储坐标
        JSONArray coordinatesArray = new JSONArray();
        for (String pair : coordinatePairs) {
            // 分割每个坐标对为经纬度
            String[] pairSplit = pair.split(" ");
            double lon = Double.parseDouble(pairSplit[0].trim());
            double lat = Double.parseDouble(pairSplit[1].trim());
            // 将每一对坐标加入到数组中
            JSONArray coordinate = new JSONArray();
            coordinate.add(lon);
            coordinate.add(lat);
            coordinatesArray.add(coordinate);
        }
        // 外层数组，封装一个多边形的坐标
        JSONArray nestedArray = new JSONArray();
        nestedArray.add(coordinatesArray);
        return nestedArray.toString();
    }

    public static String toWktMultipolygon(String coordinatesStr) {
        // 使用空格分隔每一对坐标
        String[] coordinatePairs = coordinatesStr.split(" ");
        // 构建 WKT MULTIPOLYGON 字符串
        StringBuilder wkt = new StringBuilder("MULTIPOLYGON(((");
        for (int i = 0; i < coordinatePairs.length; i++) {
            // 分割每一对坐标为经度、纬度和第三个值（忽略第三个值）
            String[] pairSplit = coordinatePairs[i].split(",");
            double lon = Double.parseDouble(pairSplit[0].trim());
            double lat = Double.parseDouble(pairSplit[1].trim());
            // 拼接 WKT 格式
            wkt.append(lon).append(" ").append(lat);
            // 如果不是最后一个坐标点，添加逗号
            if (i < coordinatePairs.length - 1) {
                wkt.append(",");
            }
        }
        // 结束 WKT 格式
        wkt.append(")))");
        return wkt.toString();
    }

}
