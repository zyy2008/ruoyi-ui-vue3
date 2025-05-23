import axios from 'axios'
let map; // mars3d.Map三维地图对象
let geoJsonLayer
export let graphicLayer;
let graphic;
import * as mars3d from "mars3d"
export const eventTarget = new mars3d.BaseClass()
import { getFilter } from "@/store/modules/layer"
let geoJsonLayerMap = {}

/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance, mars3dGlobe) {
    console.log("地图被挂载了")
    map = mapInstance // 记录map
    // 设置编辑点样式
    // mars3d.DrawUtil.setEditPointStyle(mars3d.EditPointType.Control, {
    //   type: mars3d.GraphicType.billboardP, // 支持设置type指定编辑点类型
    //   image: "img/icon/move.png",
    //   horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
    //   verticalOrigin: Cesium.VerticalOrigin.CENTER
    // })

    graphicLayer = new mars3d.layer.GraphicLayer({
        // isRestorePositions: true,
        hasEdit: false,
        isAutoEditing: false // 绘制完成后是否自动激活编辑
        // drawAddEventType: false,
        // drawEndEventType: mars3d.EventType.rightClick,
        // drawDelEventType: mars3d.EventType.middleClick
    })
    map.addLayer(graphicLayer)

    // 用于显示查询结果（geojson）的图层
    geoJsonLayer = new mars3d.layer.GeoJsonLayer({
        symbol: {
            type: "polylineC",
            styleOptions: {
                clampToGround: true,
                width: 20, // 线宽
                materialType: "PolylineGlow",
                materialOptions: {
                    color: "#FF4500",
                    opacity: 0.9,
                    glowPower: 0.06 // 发光强度
                }
            }
        },
        //popup: "all"
    })
    map.addLayer(geoJsonLayer)

    geoJsonLayer.on(mars3d.EventType.click, function (event) {
        console.log("监听layer，单击了矢量对象", event);
        event.attr = event.graphic.attr;
        window.showLayerAttribute(event)
    });

}


/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {

    console.log("地图被释放了")
}

export function clearLayer() {

    geoJsonLayer.clear()
    graphicLayer.clear()
}

export function addLayer(layer) {
    map.addLayer(layer)
}

export function getGraphicById(id) {
    return graphicLayer.getGraphicById(id)
}

export function removeGraphic() {
    if (graphic) {
        graphicLayer.removeGraphic(graphic)
    }
}

export async function drawAll(selectedLayers) {
    $showLoading()
    graphic = await graphicLayer.startDraw({
        type: "polyline",
        style: {
            width: 3,
            color: "#ffff00"
        }
    })

    console.log(graphic.toGeoJSON())
    let featuresAll = [];

    for(let k=0;k<selectedLayers.length;k++){
        let form=selectedLayers[k];
        let geojson = await queryOut(form)
        if(geojson && geojson.features){
            featuresAll.push(...geojson.features)
            geoJsonLayer.loadGeoJSON(geojson,{
                clear:false
            })
        }
        console.log(geojson)

    }
    console.log("featuresAll",featuresAll)
    eventTarget.fire("selectLine", { features: featuresAll })
    globalMsg("共查询到 " + featuresAll.length + " 条记录！")
    $hideLoading()
    return graphic;

    // let points = graphic.points;
    // let lineStr= convertToLineString(points)
    // console.log(lineStr)
    // queryWFSIntersection("http://124.221.121.154:8081/geoserver/ne/wfs","ne:GYGX_X",lineStr)
}

export async function queryOut(form) {

    return new Promise((resolve, reject) => {
        query(form, resolve, reject)
    })
}



export async function query(form, resolve, reject) {

    let data = form;
    let key = data.id + data.title
    let queryMapserver = geoJsonLayerMap[key]
    if (!queryMapserver) {
        queryMapserver = new mars3d.query.QueryGeoServer({
            url: data.url,
            layer: data.layers
        })

        geoJsonLayerMap[key] = queryMapserver
    }

    let cql_filter = getFilter(data.code)

    if (cql_filter) {
        cql_filter += " and "
    }

    queryMapserver.queryBySql({
        geometryName: "geom",
        cql_filter: cql_filter + "INTERSECTS (geom," + convertToLineString(graphic.points) + ")",
        graphic: graphic,
        success: (result) => {
            resolve(result.geojson)
            //eventTarget.fire("selectLine", { features: result.geojson.features })
        },
        error: (error, msg) => {
            reject(error)
        }
    })
}

async function queryWFSIntersection(wfsUrl, layerName, lineGeometry) {
    // 构建WFS请求
    const wfsRequest = `service=WFS&version=1.1.0&request=GetFeature&typeName=${encodeURIComponent(layerName)}&outputFormat=application/json&featureCount=10&filter=<Filter xmlns="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml"><Crosses><PropertyName>geom</PropertyName><gml:LineString><gml:coordinates>${lineGeometry}</gml:coordinates></gml:LineString></Crosses></Filter>`;
    try {
        // 发送请求
        const response = await axios.get(wfsUrl + "?" + wfsRequest, {
            // params: {
            //     request: wfsRequest
            // }
        });

        // 处理返回的数据
        console.log('Query Result:', response.data);
        return response.data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
}


function convertToLineString(coordinates) {
    // 确保坐标数组不为空且至少包含两个点
    if (!coordinates || coordinates.length < 2) {
        return null;
    }

    // 初始化LINESTRING字符串
    let lineString = 'LINESTRING(';

    // 构建LINESTRING坐标字符串
    for (let i = 0; i < coordinates.length; i++) {
        lineString += `${coordinates[i]._lng} ${coordinates[i]._lat}`;
        if (i < coordinates.length - 1) {
            lineString += ',';
        }
    }

    // 关闭LINESTRING
    lineString += ')';

    return lineString;
}