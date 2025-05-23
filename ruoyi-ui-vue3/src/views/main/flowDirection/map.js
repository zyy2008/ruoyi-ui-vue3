import * as mars3d from "mars3d"
import { getFilter } from "@/store/modules/layer"
export let map // mars3d.Map三维地图对象

let geoJsonLayer
let drawGraphic

let geoJsonLayerMap = {}

export const eventTarget = new mars3d.BaseClass() // 事件对象，用于抛出事件到面板中

/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance) {
    map = mapInstance // 记录map
    showGeoJsonLayer()
}

/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {
    clearLayer();
    map = null
}
//架空的工业管道
export function query(form) {
    let data = form.data;
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
    $showLoading()
    queryMapserver.queryBySql({
        maxFeatures: 20000,
        geometryName: "geom",
        cql_filter: cql_filter,
        graphic: drawGraphic,
        success: (result) => {
            $hideLoading()
            if (result.count === 0) {
                globalMsg("未查询到相关记录！")
                geoJsonLayer.load({ data: { features: null } })
                return
            } else {
                globalMsg("共查询到 " + result.count + " 条记录！")
            }
            geoJsonLayer.load({ data: result.geojson })
        },
        error: (error, msg) => {
            $hideLoading()
            console.log("服务访问错误", error)
            globalAlert(msg, "服务访问错误")
        }
    })
}


function showGeoJsonLayer() {
    // 用于显示查询结果（geojson）的图层
    geoJsonLayer = new mars3d.layer.GeoJsonLayer({
        symbol: {
            type: "polylineC",
            styleOptions: {
                clampToGround: true,
                width: 10, // 线宽
                materialType: mars3d.MaterialType.LineFlow,
                materialOptions: {
                    color: Cesium.Color.AQUA,
                    image: "img/textures/arrow-h.png",
                    repeat: new Cesium.Cartesian2(20, 1),
                    speed: 30

                    // image: "img/textures/line-gradual.png",
                    // color: "#66bd63",
                    // repeat: new Cesium.Cartesian2(2.0, 1.0),
                    // speed: 25
                }
            }
        },
        //popup: "all"
    })
    map.addLayer(geoJsonLayer)

    geoJsonLayer.on(mars3d.EventType.load, function (event) {
        geoJsonLayer.flyTo();
    })

    geoJsonLayer.on(mars3d.EventType.click, function (event) {
        console.log("监听layer，单击了矢量对象", event);
        event.attr = event.graphic.attr;
        window.showLayerAttribute(event)
    });
}

export async function StopDraw() {
    map.graphicLayer.stopDraw();
}


// 框选查询 矩形
export async function drawRectangle() {
    clearAll()
    drawGraphic = await map.graphicLayer.startDraw({
        type: "rectangle",
        style: {
            color: "#00FF00",
            opacity: 0.3,
            outline: true,
            outlineColor: "#ffffff",
            clampToGround: true
        }
    })
    console.log("矩形：", drawGraphic.toGeoJSON({ outline: true }))
}

// 框选查询   圆
export async function drawCircle() {
    clearAll()
    drawGraphic = await map.graphicLayer.startDraw({
        type: "circle",
        style: {
            color: "#00FF00",
            opacity: 0.3,
            outline: true,
            outlineColor: "#ffffff",
            clampToGround: true
        }
    })
    console.log("圆：", drawGraphic.toGeoJSON({ outline: true }))
}

// 框选查询   多边行
export async function drawPolygon() {
    clearAll()
    drawGraphic = await map.graphicLayer.startDraw({
        type: "polygon",
        style: {
            color: "#00FF00",
            opacity: 0.1,
            outline: true,
            outlineColor: "#ffffff",
            clampToGround: true
        }
    })
    console.log("多边行：", drawGraphic.toGeoJSON())
}

export function clearAll(noClearDraw) {
    if (!noClearDraw) {
        drawGraphic = null
        map.graphicLayer.clear()
    }
    geoJsonLayer.clear()
}

export function clearLayer() {
    geoJsonLayer.clear()
    clearAll()
}

export function flyToGraphic(graphic) {
    graphic.openHighlight()
    graphic.flyTo({
        radius: 1000, // 点数据：radius控制视距距离
        scale: 1.5, // 线面数据：scale控制边界的放大比例
        complete: () => {
            graphic.openPopup()
        }
    })
}



