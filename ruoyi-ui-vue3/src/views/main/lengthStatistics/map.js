
export let map; // mars3d.Map三维地图对象
let graphic;
let graphicLayer;
import * as mars3d from "mars3d"
export const eventTarget = new mars3d.BaseClass()
let queryType = "1"

/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance, mars3dGlobe) {
    map = mapInstance // 记录map

    graphicLayer = new mars3d.layer.GraphicLayer({
        // isRestorePositions: true,
        hasEdit: false,
        isAutoEditing: false // 绘制完成后是否自动激活编辑
        // drawAddEventType: false,
        // drawEndEventType: mars3d.EventType.rightClick,
        // drawDelEventType: mars3d.EventType.middleClick
    })
    map.addLayer(graphicLayer)
}

/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {

    clearLayer();
    console.log("地图被释放了")
}
export function getGraphic() {
    return graphic;
}

export function clearLayer() {
    graphicLayer.clear()
    graphic=null;
}

export function getExtent() {
    return map.getExtent()
}
export async function StopDraw() {
    graphicLayer.stopDraw();
}


export async function drawAll(dataType) {
    graphic = await graphicLayer.startDraw({
        type: dataType,
        style: {
            pixelSize: 14,
            color: "#3388ff"
        }
    })
}

export async function queryAll(layerObj, radioType) {
    queryType = radioType; //确定查询类型
    $showLoading()
    for (const key in layerObj) {
        if (layerObj.hasOwnProperty(key)) { // 检查属性是否是对象自身的属性
            layerObj[key].queryMapserver = layerObj[key].queryMapserver || createQueryGeoServer(layerObj[key].url, layerObj[key].layers)
            layerObj[key].queryFun = layerObj[key].queryFun || function queryFun() {
                return new Promise((resolve, reject) => {
                    query(layerObj[key].queryMapserver, resolve, reject)
                })
            }
            layerObj[key].queryRes = await layerObj[key].queryFun();
        }
    }
    $hideLoading()
    return layerObj;
}


export function query(queryMapserver, resolve, reject) {

    let cql_filter=""
    if (queryType == "1") {
        graphic=null;//置空
        const rectangleBounds = getExtent(); //获取屏幕范围
        // 构建BBOX字符串
        cql_filter = `BBOX(geom, ${rectangleBounds.xmin}, ${rectangleBounds.ymin}, ${rectangleBounds.xmax}, ${rectangleBounds.ymax})`;
    }

    if (queryType == "2") {
        cql_filter=""
    }
    if (queryType == "3") {
        cql_filter=""
        graphic=null;//置空
    }

    queryMapserver.queryBySql({
        maxFeatures: 10000,
        geometryName: "geom",
        cql_filter: cql_filter,
        graphic: graphic,
        success: (result) => {
            resolve(result.geojson)
        },
        error: (error, msg) => {
            reject(error)
        }
    })
}


//构建查询对象
export function createQueryGeoServer(url, layer) {
    let queryMapserver = new mars3d.query.QueryGeoServer({
        url: url,
        layer: layer
    })
    return queryMapserver
}








