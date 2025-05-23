import * as mars3d from "mars3d"
import { getFilter } from "@/store/modules/layer"
export const eventTarget = new mars3d.BaseClass() // 事件对象，用于抛出事件到面板中
export const mapOptions = {
    scene: {
        center: { lat: 31.967015, lng: 117.316406, alt: 9150, heading: 206, pitch: -42 },
        fxaa: true
    }
}

export let map // mars3d.Map三维地图对象
export let graphicLayer // 矢量图层对象
let geoJsonLayer
let geoJsonLayerMap = {}
let graphicBuffer;

export function flyToGraphic(graphic) {
    graphic.bindHighlight({
        type: "polylineC",
        styleOptions: {
            clampToGround: true,
            width: 10, // 线宽
            materialType: "PolylineGlow",
            materialOptions: {
                color: "#FF4500",
                opacity: 0.9,
                glowPower: 0.06 // 发光强度
            }
        }
    })

    graphic.flyTo({
        radius: 1000, // 点数据：radius控制视距距离
        scale: 1.5, // 线面数据：scale控制边界的放大比例
        complete: () => {
            //graphic.openPopup()
            //geoJsonLayer.openHighlight(graphic)
        }
    })
}


/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance) {
    map = mapInstance // 记录map
    graphicLayer = new mars3d.layer.GraphicLayer({
        hasEdit: true,
        isAutoEditing: true // 绘制完成后是否自动激活编辑
    })
    map.addLayer(graphicLayer)

    graphicLayer.on(mars3d.EventType.drawCreated, function (e) {
        updateBuffer(e.graphic)
    })

    graphicLayer.on(mars3d.EventType.editMovePoint, function (e) {
        updateBuffer(e.graphic)
    })

    graphicLayer.on(mars3d.EventType.editRemovePoint, function (e) {
        updateBuffer(e.graphic)
    })

    showGeoJsonLayer()
}

function showGeoJsonLayer() {
    // 用于显示查询结果（geojson）的图层
    geoJsonLayer = new mars3d.layer.GeoJsonLayer({

        symbol: {
            //type: "polylineC",
            styleOptions: {
                clampToGround: true,
                width: 10, // 线宽
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

    geoJsonLayer.on(mars3d.EventType.load, function (event) {
        console.log(event)
        const list = event.list
        eventTarget.fire("befortUI", { list })
    })

    geoJsonLayer.on(mars3d.EventType.click, function (event) {
        console.log("监听layer，单击了矢量对象", event);
        event.attr = event.graphic.attr;
        window.showLayerAttribute(event)
    });
}

export function query(cql_filter, form) {

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
    let cql_filter_code = getFilter(data.code)

    if (cql_filter_code) {
        cql_filter = cql_filter_code + " AND " + cql_filter
    }

    $showLoading()
    queryMapserver.queryBySql({
        maxFeatures: 10000,
        geometryName: "geom",
        cql_filter: cql_filter,
        graphic: graphicBuffer,
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
            //globalAlert(msg, "服务访问错误")
        }
    })
}


/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {
    deleteAll();
    map = null
}

export function drawPoint() {
    deleteAll()

    graphicLayer.startDraw({
        type: "point",
        style: {
            pixelSize: 12,
            color: "#ffff00"
        }
    })
}

export function drawPolyline() {
    deleteAll()

    graphicLayer.startDraw({
        type: "polyline",
        style: {
            color: "#ffff00",
            width: 3,
            clampToGround: true
        }
    })
}

export function drawPolygon() {
    deleteAll()

    graphicLayer.startDraw({
        type: "polygon",
        style: {
            color: "#ffff00",
            outline: true,
            outlineColor: "#f0ce22",
            outlineWidth: 4,
            opacity: 0.5,
            clampToGround: true
        }
    })
}

export function deleteAll() {
    geoJsonLayer.clear()
    graphicLayer.clear()
    if (map.graphicLayer) {
        map.graphicLayer.clear()
    }
    lastgeojson = null
    removeSelect()
}

let width
export function radiusChange(val) {
    width = val * 1 // m
    if (lastgeojson) {
        updateBuffer()
    }
}

let lastgeojson
function updateBuffer(graphic) {
    let buffere
    try {
        const geojson = graphic ? graphic.toGeoJSON() : lastgeojson
        geojson.properties = {}

        buffere = mars3d.PolyUtil.buffer(geojson, width)

        lastgeojson = geojson
    } catch (e) {
        console.log("缓冲分析异常", e)
    }
    if (!buffere) {
        return
    }

    const graphicsOptions = mars3d.Util.geoJsonToGraphics(buffere, {
        type: "polygon",
        style: {
            color: "rgba(255,0,0,0.2)",
            clampToGround: true
        }
    })[0]

    map.graphicLayer.clear()

    const drawGraphic = map.graphicLayer.addGraphic(graphicsOptions)
    updateSelect(drawGraphic)
}

function updateSelect(drawGraphic) {
    removeSelect()
    if (!drawGraphic) {
        return
    }

    graphicBuffer = drawGraphic;

}

export function removeSelect() {

    graphicBuffer = null;
}
