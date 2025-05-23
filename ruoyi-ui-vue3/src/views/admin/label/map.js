
let map; // mars3d.Map三维地图对象

export let graphicLayer;
let formUpdate;
let graphic;
import * as mars3d from "mars3d"
export const eventTarget = new mars3d.BaseClass()

let measure

/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance, mars3dGlobe) {
    console.log("地图标注被挂载了")
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
        hasEdit: true,
        isAutoEditing: true // 绘制完成后是否自动激活编辑
        // drawAddEventType: false,
        // drawEndEventType: mars3d.EventType.rightClick,
        // drawDelEventType: mars3d.EventType.middleClick
    })
    map.addLayer(graphicLayer)

    //标注测量对象
    measure = new mars3d.thing.Measure({
        label: {
            color: "#ffffff",
            font_family: "楷体",
            font_size: 20,
            background: false
        },
        isAutoEditing: false // 绘制完成后是否自动激活编辑
        // drawEndEventType: mars3d.EventType.rightClick,
        // drawDelEventType: mars3d.EventType.middleClick
    })

    measure.on(mars3d.EventType.start, function (e) {
        console.log("开始异步分析", e)
        $showLoading()
    })
    measure.on(mars3d.EventType.end, function (e) {
        console.log("完成异步分析", e)
        $hideLoading()

        if (measure.graphicLayer.length === 0) {
            globalMsg("当前没有标注任何数据，无需保存！")
            return
        }
        // const json = measure.graphicLayer.toJSON()
        // formUpdate(JSON.stringify(json))
    })

    map.addThing(measure)
}


/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {



    console.log("地图标注被释放了")
}

export function clearData() {
    if (graphicLayer) {
        graphicLayer.clear()
    }
    if (measure) {
        measure.clear()
    }
}

export function getGraphicById(id) {
    return graphicLayer.getGraphicById(id)
}

export function removeGraphic(row) {
    //
    let data = JSON.parse(row.data);
    graphic = getGraphicById(data.id);
    if (graphic) {
        graphicLayer.removeGraphic(graphic)
    }

}

//缓存对象
let graphicCache = {};



export function loadLabel(row, show) {
    //
    let data = JSON.parse(row.data);
    //是测量
    graphic = getGraphicById(data.id);
    if (data.measured && !graphic) {
        graphic = measure.graphicLayer.getGraphicById(data.id);
    }
    console.log(graphic)
    //没有选中则退出
    if (!show && !graphic) {
        return;
    }
    if (!graphic && show) {
        if (data.measured && data.type == "distanceMeasure") {
            data.label = {
                // 自定义显示label的graphic类型
                type: "div",
                updateText: function (text, graphic) {
                    // updateText是必须，用于动态更新 text
                    graphic.html = `<div class="marsGreenGradientPnl" >${text}</div>`
                },
                // 下面是graphic对应类型本身的参数
                html: `<div class="marsGreenGradientPnl" ></div>`,
                horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
                verticalOrigin: Cesium.VerticalOrigin.BOTTOM
                // pointerEvents: false
            }

        }
        graphic = graphicLayer.loadJSON(data);
    }
    if (graphic) {
        graphic.show = show;
    }
}

//回显
export function handleFlyTo(row, show, flyTo) {
    //
    let data = JSON.parse(row.data);
    //是测量
    // if (data.measured) {
    //     //graphicLayer.getGraphicById(id)
    //     graphic = graphicCache[data.id];
    //     if (!graphic && show) {
    //         graphic= measure.graphicLayer.addGraphic([data])
    //         measure.graphicLayer.flyTo()
    //         graphicCache[data.id]=graphic;
    //     }

    //     if (graphic && flyTo) {
    //         graphic.flyTo();
    //     }

    //     if (graphic && !show) {
    //         console.log("移除")
    //     }
    //     return;
    // }
    graphic = getGraphicById(data.id);
    if (data.measured && !graphic) {
        graphic = measure.graphicLayer.getGraphicById(data.id);
    }
    console.log(graphic)
    //没有选中则推出
    if (!show && !graphic) {
        return;
    }
    if (!graphic && show) {
        graphic = graphicLayer.loadJSON(data);
    }
    if (graphic) {
        graphic.show = show;
    }

    if (graphic && flyTo) {
        graphic.flyTo();
    }
}

export function setStyle(text) {
    graphic.setStyle({
        label: {
            text: text
        }
    });
}

export function setUpdateForm(row, callback) {
    let data = JSON.parse(row.data);
    graphic = getGraphicById(data.id);
    formUpdate = callback;
}
export async function drawAll(form, callback) {
    formUpdate = callback;
    if (form.value.dataType == "distance") {
        measureLength();
        return;
    }
    if (form.value.dataType == "area") {
        measureArea();
        return;
    }
    if (form.value.dataType == "angle") {
        measureAngle();
        return;
    }
    graphic = await graphicLayer.startDraw({
        type: form.value.dataType,
        style: {
            pixelSize: 14,
            color: "#3388ff",
            label: {
                // 不需要文字时，去掉label配置即可
                text: form.value.name,
                font_size: 20,
                color: "#ffffff",
                outline: true,
                outlineColor: "#000000",
                pixelOffsetY: -20
            }
        }
    })

    const json = graphic.toJSON() // 文件处理
    formUpdate(JSON.stringify(json))
    //console.log("完成了draw标绘", graphic)
}

//测量开始
export async function measureLength() {
    const graphic = await measure.distance({
        showAddText: true,
        label: {
            // 自定义显示label的graphic类型
            type: "div",
            updateText: function (text, graphic) {
                // updateText是必须，用于动态更新 text
                graphic.html = `<div class="marsGreenGradientPnl" >${text}</div>`
            },
            // 下面是graphic对应类型本身的参数
            html: `<div class="marsGreenGradientPnl" ></div>`,
            horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
            verticalOrigin: Cesium.VerticalOrigin.BOTTOM
            // pointerEvents: false
        }
        // style: {
        //   color: '#ffff00',
        //   width: 3,
        //   clampToGround: false //是否贴地
        // }
    })
    const json = graphic.toJSON()
    formUpdate(JSON.stringify(json))
}

// 水平面积
export async function measureArea() {
    const graphic = await measure.area({
        // style: {
        //   color: '#00fff2',
        //   opacity: 0.4,
        //   outline: true,
        //   outlineColor: '#fafa5a',
        //   outlineWidth: 1,
        //   clampToGround: false //贴地
        // }
    })

    // 下面代码抬升面的高度到一个平面，来示意“水平”
    if (map.scene.mode === Cesium.SceneMode.SCENE3D) {
        const oldPositions = graphic.positionsShow
        const rang = await mars3d.PolyUtil.getHeightRangeByDepth(oldPositions, map.scene)
        graphic.positions = mars3d.PointUtil.setPositionsHeight(oldPositions, rang.maxHeight)
    }

    const json = graphic.toJSON()
    formUpdate(JSON.stringify(json))
}

// 方位角
export async function measureAngle() {
    const graphic = await measure.angle()

    const json = graphic.toJSON()
    formUpdate(JSON.stringify(json))
}

//获取视角
export function getMapView() {
    return map.getCameraView();
}

// 飞向视角
export function flytoView(center) {
    map.setCameraView(center)
}





