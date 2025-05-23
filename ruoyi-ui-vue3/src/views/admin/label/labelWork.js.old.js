import * as mars3d from "mars3d";
let graphicLayer;
let formUpdate;
let graphic;

export function onMountedInit() {
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


    // 矢量数据创建完成
    graphicLayer.on(mars3d.EventType.drawCreated, function (e) {
        showEditor(e)
    })
    // 修改了矢量数据
    graphicLayer.on(
        [mars3d.EventType.editStart, mars3d.EventType.editMovePoint, mars3d.EventType.editStyle, mars3d.EventType.editRemovePoint],
        function (e) {
            console.log("修改了draw标绘")
            showEditor(e)
        }
    )
    // 停止编辑
    graphicLayer.on([mars3d.EventType.editStop, mars3d.EventType.removeGraphic], function (e) {
        showEditor(e)
    })
}


const showEditor = (e) => {
    let json = e.graphic.toJSON();
    console.log(json);
    formUpdate(JSON.stringify(json));
}


export function getGraphicById(id) {
    return graphicLayer.getGraphicById(id)
}

export function handleFlyTo(row, show,flyTo) {
    //
    let data = JSON.parse(row.data);
    graphic = getGraphicById(data.id);
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

    if (flyTo) {
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

export function setUpdateForm(row,callback){
    let data = JSON.parse(row.data);
    graphic = getGraphicById(data.id);
    formUpdate = callback;
}
export async function drawPoint(form, callback) {
    formUpdate = callback;
    graphic = await graphicLayer.startDraw({
        type: "point",
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
    //console.log("完成了draw标绘", graphic)
}