<template>
  <div :id="withKeyId" class="mars3d-container"></div>
</template>
<script setup>
/**
 * 地图渲染组件
 * @copyright 火星科技 mars3d.cn
 * @author 木遥 2022-02-19
 */
import { defineProps } from "vue";
import { computed, onUnmounted, onMounted } from "vue";
import * as mars3d from "mars3d";
import { $alert, $message } from "@mars/components/mars-ui/index";
import axios from "axios";
import layerStore from "@/store/modules/layer";

const layerStoreIns = layerStore();
const props = defineProps({
  url: "",
  mapKey: "default",
  options: () => ({}),
});

// 用于存放地球组件实例
let map; // 地图对象

// 使用用户传入的 mapKey 拼接生成 withKeyId 作为当前显示容器的id
const withKeyId = computed(() => `mars3d-container-${props.mapKey}`);

onMounted(async () => {
  // let options = {
  //   layers: layerStoreIns.layers,
  // };

  // //当只要一个基地的时候
  // if (layerStoreIns.setTrees.length == 1) {
  //   let base = layerStoreIns.setTrees[0];
  //   if (base.view) {
  //     let view = JSON.parse(base.view);
  //     options.scene = {
  //       center: view,
  //     };

  //     console.log("view", view);
  //   }
  // }

  // 获取配置
  mars3d.Util.fetchJson({ url: props.url }).then((data) => {

    initMars3d({
      // 合并配置项
      ...data.map3d,
      ...props.options,
      // ...options,
    });
  });
});

function testConsole() {
  // 保存原始的 console 对象
  const originalConsole = console;

  // 创建一个新的 console 对象
  const wrappedConsole = {
    // 保存原始的 console.log 方法
    log: originalConsole.log,
  };

  // 重写 wrappedConsole.log 方法
  wrappedConsole.log = function (...args) {
    // 在这里输出日志，表示 console.log 方法被拦截
    console.log("Interceptor:"); // 这里使用全局的 console.log，因为我们还没有覆盖它

    // 在这里进行自定义的操作
    debugger; // 这行代码会在浏览器的开发者工具中设置一个断点

    // 调用原始的 console.log 方法，并传递原始参数
    originalConsole.log.apply(originalConsole, args);
  };

  // 覆盖全局的 console 对象
  console = wrappedConsole;
}

function testMars() {
  const originalAdd = mars3d.Cesium.PrimitiveCollection.prototype.add;
  mars3d.Cesium.PrimitiveCollection.prototype.add = function (primitive) {
    console.log("add 方法被拦截");
    // 在这里进行自定义的操作
    debugger;
    // 调用原始的 add 方法
    return originalAdd.call(this, primitive);
  };
}

// onload事件将在地图渲染后触发
const emit = defineEmits(["onload"]);
const initMars3d = (option) => {
  // testMars();
  map = new mars3d.Map(withKeyId.value, option);

  // //如果有xyz传参，进行定位
  // const lat = getQueryString("lat")
  // const lng = getQueryString("lng")
  // if (lat && lng) {
  //   map.flyToPoint(new mars3d.LngLatPoint(lng, lat), { duration: 0 })
  // }

  // 开场动画
  map.openFlyAnimation();

  // 针对不同终端的优化配置
  if (mars3d.Util.isPCBroswer()) {
    map.zoomFactor = 2.0; // 鼠标滚轮放大的步长参数

    // IE浏览器优化
    if (window.navigator.userAgent.toLowerCase().indexOf("msie") >= 0) {
      map.viewer.targetFrameRate = 20; // 限制帧率
      map.scene.requestRenderMode = false; // 取消实时渲染
    }
  } else {
    map.zoomFactor = 5.0; // 鼠标滚轮放大的步长参数

    // 移动设备上禁掉以下几个选项，可以相对更加流畅
    map.scene.requestRenderMode = false; // 取消实时渲染
    map.scene.fog.enabled = false;
    map.scene.skyAtmosphere.show = false;
    map.scene.globe.showGroundAtmosphere = false;
  }

  // //二三维切换不用动画
  if (map.viewer.sceneModePicker) {
    map.viewer.sceneModePicker.viewModel.duration = 0.0;
  }

  // webgl渲染失败后，刷新页面
  map.on(mars3d.EventType.renderError, async () => {
    // await $alert("程序内存消耗过大，请重启浏览器")
    window.location.reload();
  });

  // map构造完成后的一些处理
  onMapLoad();

  emit("onload", map);
};

// map构造完成后的一些处理
function onMapLoad() {
  // Mars3D地图内部使用，如右键菜单弹窗
  // @ts-ignore
  window.globalAlert = $alert;
  // @ts-ignore
  window.globalMsg = $message;
  // 用于 config.json 中 西藏垭口 图层的详情按钮 演示
  // @ts-ignore
  window.showPopupDetails = (item) => {
    $alert(item.NAME);
  };

  //clipTest();
}

function clipTest() {
  // GeoServer WPS 端点
  const geoserverWpsUrl = "http://124.221.121.154:8081/geoserver/wps";

  // 裁剪范围的坐标
  const coordinates = [
    "119.658256 26.493094",
    "119.663839 26.492006",
    "119.662231 26.488635",
    "119.657854 26.488349",
    "119.654429 26.48988",
    "119.654293 26.491187",
    "119.658256 26.493094",
  ];

  // WPS 请求体--xml结果
  const wpsRequestData_XML = `
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
            <wfs:Query typeName="ne:DLGX_F"/>
          </wfs:GetFeature>
        </wps:Body>
      </wps:Reference>
    </wps:Input>
    <wps:Input>
      <ows:Identifier>clip</ows:Identifier>
      <wps:Data>
        <wps:ComplexData mimeType="application/json"><![CDATA[{"type":"Polygon","coordinates":[[[119.658256,26.493094],[119.663839,26.492006],[119.662231,26.488635],[119.657854,26.488349],[119.654429,26.48988],[119.654293,26.491187],[119.658256,26.493094]]]}]]></wps:ComplexData>
      </wps:Data>
    </wps:Input>
  </wps:DataInputs>
  <wps:ResponseForm>
    <wps:ResponseDocument lineage="true" storeExecuteResponse="true" status="true">
      <wps:Output asReference="false">
        <ows:Identifier>result</ows:Identifier>
      </wps:Output>
    </wps:ResponseDocument>
  </wps:ResponseForm>
</wps:Execute>
`;

  const wpsRequestData = `
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
          <wfs:GetFeature service="WFS" cql_filter="classify = '碱液管(JY)'" version="1.0.0" outputFormat="GML2" xmlns:geonode="http://www.geonode.org/">  

           <ogc:Filter>
            <ogc:PropertyIsEqualTo>
            <ogc:PropertyName>classify</ogc:PropertyName>
            <ogc:Literal>碱液管(JY)</ogc:Literal>
            </ogc:PropertyIsEqualTo>
           </ogc:Filter>
            <wfs:Query typeName="ne:DLGX_F"/>
          </wfs:GetFeature>
        </wps:Body>
      </wps:Reference>
    </wps:Input>
    <wps:Input>
      <ows:Identifier>clip</ows:Identifier>
      <wps:Data>
        <wps:ComplexData mimeType="application/json"><![CDATA[{"type":"Polygon","coordinates":[[[119.658256,26.493094],[119.663839,26.492006],[119.662231,26.488635],[119.657854,26.488349],[119.654429,26.48988],[119.654293,26.491187],[119.658256,26.493094]]]}]]></wps:ComplexData>
      </wps:Data>
    </wps:Input>
  </wps:DataInputs>
  <wps:ResponseForm>
    <wps:RawDataOutput mimeType="application/x-shapefile">
      <ows:Identifier>result</ows:Identifier>
    </wps:RawDataOutput>
  </wps:ResponseForm>
</wps:Execute>
`;
  // 发送 WPS 请求
  axios
    .post(geoserverWpsUrl, wpsRequestData, {
      headers: {
        "Content-Type": "application/xml",
        Accept: "application/x-shapefile", // 修改Accept头以请求Shapefile格式
      },
    })
    .then(function (response) {
      console.log(response.data);
      // 处理裁剪结果
    })
    .catch(function (error) {
      console.error("Error during WPS execution:", error);
    });
}

// 组件卸载之前销毁mars3d实例
onUnmounted(() => {
  if (map) {
    map.destroy();
    map = null;
  }
  console.log("map销毁完成", map);
});
</script>

<style lang="scss">
.mars3d-container {
  .cesium-viewer {
    min-height: 650px;
  }
}
/**cesium 工具按钮栏*/
// .cesium-viewer-toolbar {
//   top: auto !important;
//   bottom: 35px !important;
//   left: auto !important;
//   right: 14px !important;
// }
// .cesium-toolbar-button img {
//   height: 100%;
// }
// .cesium-viewer-toolbar > .cesium-toolbar-button,
// .cesium-navigationHelpButton-wrapper,
// .cesium-viewer-geocoderContainer {
//   margin-bottom: 5px;
//   float: right;
//   clear: both;
//   text-align: center;
// }
// .cesium-button {
//   background-color: rgba(23, 49, 71, 0.8);
//   color: #e6e6e6;
//   fill: #e6e6e6;
//   box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
//   line-height: 32px;
// }
// .cesium-button:hover {
//   background: #3ea6ff;
// }

// /**cesium 底图切换面板*/
// .cesium-baseLayerPicker-dropDown {
//   bottom: 0;
//   left: 40px;
//   max-height: 700px;
//   margin-bottom: 5px;
//   background-color: rgba(23, 49, 71, 0.8);
// }

// /**cesium 帮助面板*/
// .cesium-navigation-help {
//   top: auto;
//   bottom: 0;
//   right: 40px;
//   transform-origin: right bottom;
//   background: none;
//   background-color: rgba(23, 49, 71, 0.8);
//   .cesium-navigation-help-instructions {
//     background: none;
//   }
//   .cesium-navigation-button {
//     background: none;
//   }
//   .cesium-navigation-button-selected,
//   .cesium-navigation-button-unselected:hover {
//     background: rgba(0, 138, 255, 0.2);
//   }
// }

// /**cesium 二维三维切换*/
// .cesium-sceneModePicker-wrapper {
//   width: auto;
// }
// .cesium-sceneModePicker-wrapper .cesium-sceneModePicker-dropDown-icon {
//   float: left;
//   margin: 0 3px;
// }

/**cesium POI查询输入框*/
.cesium-viewer-geocoderContainer .search-results {
  left: 0;
  right: 40px;
  width: auto;
  z-index: 9999;
}
.cesium-geocoder-searchButton {
  background-color: rgba(23, 49, 71, 0.8);
}
.cesium-viewer-geocoderContainer .cesium-geocoder-input {
  background-color: rgba(63, 72, 84, 0.7);
}
.cesium-viewer-geocoderContainer .cesium-geocoder-input:focus {
  background-color: rgba(63, 72, 84, 0.9);
}
.cesium-viewer-geocoderContainer .search-results {
  background-color: rgba(23, 49, 71, 0.8);
}

/**cesium info信息框*/
.cesium-infoBox {
  top: 50px;
  background-color: rgba(23, 49, 71, 0.8);
}
.cesium-infoBox-title {
  background-color: rgba(23, 49, 71, 0.8);
}

/**cesium 任务栏的FPS信息*/
.cesium-performanceDisplay-defaultContainer {
  top: auto;
  bottom: 35px;
  right: 50px;
}
.cesium-performanceDisplay-ms,
.cesium-performanceDisplay-fps {
  color: #fff;
}

/**cesium tileset调试信息面板*/
.cesium-viewer-cesiumInspectorContainer {
  top: 10px;
  left: 10px;
  right: auto;
}
.cesium-cesiumInspector {
  background-color: rgba(23, 49, 71, 0.8);
}

/**覆盖mars3d内部控件的颜色等样式*/
.mars3d-compass .mars3d-compass-outer {
  fill: rgba(23, 49, 71, 0.8);
}
.mars3d-contextmenu-ul,
.mars3d-sub-menu {
  background-color: rgba(23, 49, 71, 0.8);

  > li > a:hover,
  > li > a:focus,
  > li > .active {
    background-color: #3ea6ff;
  }

  > .active > a,
  > .active > a:hover,
  > .active > a:focus {
    background-color: #3ea6ff;
  }
}

/* Popup样式*/
.mars3d-popup-color {
  color: #ffffff;
}
.mars3d-popup-content {
  margin: 15px;
}
.mars3d-template-content label {
  padding-right: 6px;
  width: 80px;
}
.mars3d-template-titile {
  border-bottom: 1px solid #3ea6ff;
}
.mars3d-template-titile a {
  font-size: 16px;
}
.mars3d-tooltip {
  background: rgba(23, 49, 71, 0.8);
  border: 1px solid rgba(23, 49, 71, 0.8);
}

.mars3d-popup-btn-custom {
  padding: 3px 10px;
  border: 1px solid #209ffd;
  background: #209ffd1c;
}
</style>
