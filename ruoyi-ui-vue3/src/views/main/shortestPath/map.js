
let map; // mars3d.Map三维地图对象

let graphicLayer;
let graphicLayerPath;
let pointQD
let pointZD
import * as mars3d from "mars3d"
export const eventTarget = new mars3d.BaseClass()

/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance, mars3dGlobe) {
    console.log("路径检索被挂载了")
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

    graphicLayerPath = new mars3d.layer.GraphicLayer({
        // isRestorePositions: true,
        hasEdit: false,
        isAutoEditing: false // 绘制完成后是否自动激活编辑
        // drawAddEventType: false,
        // drawEndEventType: mars3d.EventType.rightClick,
        // drawDelEventType: mars3d.EventType.middleClick
    })
    map.addLayer(graphicLayerPath)
}


/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {

    console.log("路径检索被释放了")
}

// 绘制起点
export async function startPoint() {
    if (pointQD) {
        pointQD.remove()
        pointQD = null
    }
    pointQD = await graphicLayer.startDraw({
        type: "point",
        style: {
            pixelSize: 10,
            color: "red",
            label: {
                text: "起点",
                font_size: 20,
                color: "#ffffff",
                outline: true,
                outlineColor: "#000000",
                pixelOffsetY: -20
            }
        }
    })
}


// 绘制终点
export async function endPoint() {
    if (pointZD) {
        pointZD.remove()
        pointZD = null
    }
    pointZD = await graphicLayer.startDraw({
        type: "point",
        style: {
            pixelSize: 10,
            color: "red",
            label: {
                text: "终点",
                font_size: 20,
                color: "#ffffff",
                outline: true,
                outlineColor: "#000000",
                pixelOffsetY: -20
            }
        }
    })
}

export function getDataQD() {
    if (pointQD) {
        //return mars3d.PointTrans.lonlat2mercator(pointQD.coordinate) // 起点
        return pointQD.coordinate // 起点
    }

    return null;
}

export function getDataZD() {
    if (pointZD) {
        return pointZD.coordinate// 终点
    }

    return null;
}

export function clearPath() {
    graphicLayerPath.clear()
}


export function clearLayer() {
    pointQD = null
    pointZD = null
    graphicLayer.clear()
    graphicLayerPath.clear()
}

export function addGraphic(positions) {
    const graphic = new mars3d.graphic.PolylineEntity({
      positions: positions,
      style: {
        width: 5,
        clampToGround: true,
        materialType: mars3d.MaterialType.LineFlow,
        materialOptions: {
          image: "/img/textures/line-interval.png",
          axisY: false,
          repeat: new Cesium.Cartesian2(10.0, 1.0),
          color: "#ffffff",
          speed: 10
        }
      },
    })
    graphicLayerPath.addGraphic(graphic)
  }

export function parseMultilineString(multilineString) {

    // 解析提供的多线字符串数据
    const regex = /MULTILINESTRING$([\d\s,\.]+)$/;
    const match = multilineString.match(regex);
    if (match) {
        const coordinatesString = match[1];
        const coordinatesArray = coordinatesString.split(',').map(coord => coord.split(' ').map(Number));

        // 构建GeoJSON对象
        const geojson = {
            type: "Feature",
            geometry: {
                type: "MultiLineString",
                coordinates: [coordinatesArray]
            }
        };

        console.log(geojson);
        return geojson
    } else {
        console.error("Invalid MULTILINESTRING format");
        return null
    }
}


export function parseWKT(wkt) {
    // 去除前后空格和特殊字符
    wkt = wkt.trim().replace(/[\n\r]/g, '');

    // 提取第一个单词，确定几何类型
    const type = wkt.split(' ')[0];

    // 根据几何类型解析WKT
    if (type === 'POINT') {
        // 解析POINT(x y)格式
        const coords = wkt.match(/-?\d+(\.\d+)?/g);
        return {
            type: 'Point',
            coordinates: coords ? [parseFloat(coords[0]), parseFloat(coords[1])] : []
        };
    } else if (type === 'LINESTRING') {
        // 解析LINESTRING(x1 y1, x2 y2, ...)格式
        const points = wkt.split(/LINESTRING|$/g).filter(Boolean);
        return {
            type: 'LineString',
            coordinates: points.map(point => point.split(' ').map(coord => parseFloat(coord)))
        };
    } else if (type === 'POLYGON') {
        // 解析POLYGON((x1 y1, x2 y2, ...))格式
        const rings = wkt.split(/POLYGON|$/g).filter(Boolean);
        return {
            type: 'Polygon',
            coordinates: rings.map(ring => ring.split(',').map(point => point.split(' ').map(coord => parseFloat(coord))))
        };
    } else if (type === 'MULTILINESTRING') {
        // 解析MULTILINESTRING((x1 y1, x2 y2, ...), (x3 y3, x4 y4, ...))格式
        const multiLines = wkt.split(/$/).filter(Boolean);
        const coordinates = multiLines.map(line => {
            const points = line.split(',').map(point => point.split(' ').map(coord => parseFloat(coord)));
            return points;
        });
        return {
            type: 'MultiLineString',
            coordinates: coordinates
        };
    } else {
        throw new Error('Unsupported WKT type: ' + type);
    }
}