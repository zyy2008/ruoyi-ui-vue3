
let map; // mars3d.Map三维地图对象

export let graphicLayer;
let formUpdate;
let graphic;
import * as mars3d from "mars3d"
export const eventTarget = new mars3d.BaseClass()
import axios from 'axios';
/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance, mars3dGlobe) {
    console.log("地图打印被挂载了")
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
}


/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {

    graphicLayer.clear();
    console.log("地图打印被释放了")
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

export function setUpdateForm(row, callback) {
    let data = JSON.parse(row.data);
    graphic = getGraphicById(data.id);
    formUpdate = callback;
}
export async function drawAll(form, callback) {
    formUpdate = callback;
    graphic = await graphicLayer.startDraw({
        type: "polygon",
        style: {
            color: "#00FF00",
            opacity: 0.1,
            outline: true,
            outlineColor: "#ffffff",
            clampToGround: true
        }
    })

    const json = graphic.toJSON() // 文件处理
    formUpdate(json)
    //console.log("完成了draw标绘", graphic)
}


export function getTargetLayers(layerCodes) {

    let layerIds = [];
    for (let i = 0; i < layerCodes.length; i++) {
        let code = layerCodes[i];
        let layer = map.getLayer(code, "code");
        if (layer && layer.isAdded && layer.show) {
            console.log(layer)
            layerIds.push(layer.options.layerId)
        }

    }

    return layerIds;

}


// export function getTargetLayers(layerCodes) {

//     let layerIds = [];
//     for (let i = 0; i < layerCodes.length; i++) {
//         let code = layerCodes[i];
//         let layer = map.getLayer(code, "code");
//         if (layer && layer.isAdded && layer.show) {
//             console.log(layer)
//             layerIds.push(layer.options.layerId)

//             let url = layer.options.url;
//             let typeName = layer.options.layers;

          
//             let geoJSON = graphic.toGeoJSON();
//             let coordinates = geoJSON.geometry.coordinates;
//             coordinates[0].push(coordinates[0][0])
//             //geoJSON.geometry.coordinates=coordinates;
//             console.log(geoJSON)

//             let queryData = JSON.stringify(geoJSON.geometry);

//             console.log(queryData)
//             clipWPS(url,typeName,queryData)
//         }

//     }

//     return layerIds;

// }



function clipWPS(url, typeName, queryData) {


    // GeoServer WPS 端点
    const geoserverWpsUrl = url;// 'http://124.221.121.154:8081/geoserver/wps';

    let wpsRequestData = `
    <wps:Execute version="1.0.0" service="WPS" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
                 xmlns="http://www.opengis.net/wps/1.0.0" 
                 xmlns:wfs="http://www.opengis.net/wfs" 
                 xmlns:wps="http://www.opengis.net/wps/1.0.0" 
                 xmlns:ows="http://www.opengis.net/ows/1.1" 
                 xmlns:gml="http://www.opengis.net/gml" 
                 xmlns:ogc="http://www.opengis.net/ogc" 
                 xmlns:wcs="http://www.opengis.net/wcs/1.1.1" 
                 xmlns:xlink="http://www.w3.org/1999/xlink" 
                 xsi:schemaLocation="http://www.opengis.net/wps/1.0.0 http://schemas.opengis.net/wps/1.0.0/wpsAll.xsd"> 
      <ows:Identifier>gs:Clip</ows:Identifier>
      <wps:DataInputs>
        <wps:Input>
          <ows:Identifier>features</ows:Identifier>
          <wps:Reference mimeType="text/xml" xlink:href="http://geoserver/wfs" method="POST">
            <wps:Body>
              <wfs:GetFeature service="WFS" version="1.0.0" outputFormat="GML2" xmlns:geonode="http://www.geonode.org/">  
                <wfs:Query typeName="${typeName}" />
              </wfs:GetFeature>
            </wps:Body>
          </wps:Reference>
        </wps:Input>
        <wps:Input>
          <ows:Identifier>clip</ows:Identifier>
          <wps:Data>
            <wps:ComplexData mimeType="application/json"><![CDATA[${queryData}]]></wps:ComplexData>
          </wps:Data>
        </wps:Input>
      </wps:DataInputs>
      <wps:ResponseForm>
        <wps:RawDataOutput mimeType="application/json">
          <ows:Identifier>result</ows:Identifier>
        </wps:RawDataOutput>
      </wps:ResponseForm>
    </wps:Execute>
    `

    // wpsRequestData=`
    // <wps:Execute version="1.0.0" service="WPS" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    //              xmlns="http://www.opengis.net/wps/1.0.0" 
    //              xmlns:wfs="http://www.opengis.net/wfs" 
    //              xmlns:wps="http://www.opengis.net/wps/1.0.0" 
    //              xmlns:ows="http://www.opengis.net/ows/1.1" 
    //              xmlns:gml="http://www.opengis.net/gml" 
    //              xmlns:ogc="http://www.opengis.net/ogc" 
    //              xmlns:wcs="http://www.opengis.net/wcs/1.1.1" 
    //              xmlns:xlink="http://www.w3.org/1999/xlink" 
    //              xsi:schemaLocation="http://www.opengis.net/wps/1.0.0 http://schemas.opengis.net/wps/1.0.0/wpsAll.xsd"> 
    //   <ows:Identifier>gs:Clip</ows:Identifier>
    //   <wps:DataInputs>
    //     <wps:Input>
    //       <ows:Identifier>features</ows:Identifier>
    //       <wps:Reference mimeType="text/xml" xlink:href="http://geoserver/wfs" method="POST">
    //         <wps:Body>
    //           <wfs:GetFeature service="WFS" version="1.0.0" outputFormat="GML2" xmlns:geonode="http://www.geonode.org/">  
    //           <wfs:Query typeName="ne:DLGX_F"/>
    //           </wfs:GetFeature>
    //         </wps:Body>
    //       </wps:Reference>
    //     </wps:Input>
    //     <wps:Input>
    //       <ows:Identifier>clip</ows:Identifier>
    //       <wps:Data>
    //       <wps:ComplexData mimeType="application/json"><![CDATA[{"type":"Polygon","coordinates":[[[119.658256,26.493094],[119.663839,26.492006],[119.662231,26.488635],[119.657854,26.488349],[119.654429,26.48988],[119.654293,26.491187],[119.658256,26.493094]]]}]]></wps:ComplexData>
    //     </wps:Data>
    //     </wps:Input>
    //   </wps:DataInputs>
    //   <wps:ResponseForm>
    //     <wps:RawDataOutput mimeType="application/json">
    //       <ows:Identifier>result</ows:Identifier>
    //     </wps:RawDataOutput>
    //   </wps:ResponseForm>
    // </wps:Execute>
    // `
    // 发送 WPS 请求
    axios.post(geoserverWpsUrl, wpsRequestData, {
        headers: {
            'Content-Type': 'application/xml',
            'Accept': 'application/json' //
        }
    })
        .then(function (response) {
            console.log(response.data);
            // 处理裁剪结果
        })
        .catch(function (error) {
            console.error('Error during WPS execution:', error);
        });
}
