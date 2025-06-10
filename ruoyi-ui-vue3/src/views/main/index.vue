<template>
  <config-provider :locale="locale">
    <div id="mars-main-view" class="map-div mars-main-view">
      <TopHead />
      <MarsMap :url="configUrl" map-key="WXYMAP" @onload="marsOnload" />
      <template v-if="mapLoaded">
        <template v-for="comp in widgets" :key="comp.key">
          <mars-widget
            v-if="openAtStart.includes(comp.name) && comp.visible"
            v-model:visible="comp.visible"
            :widget="comp"
          />
        </template>
      </template>

      <!-- <VerticalMenu /> -->
    </div>

    <mars-dialog
      draggable
      width="350"
      right="20"
      top="200"
      bottom="20"
      title="要素属性"
      zIndex="99999"
      v-model:visible="showLayer"
    >
      <div v-html="htmlComponent"></div>
    </mars-dialog>

    <el-dialog
      v-model="dialogVisible"
      :destroy-on-close="true"
      title="企业信息"
      width="100vw"
    >
      <TabsCard :deptId="deptId" />
      <!-- <el-tabs
        v-model="activeName"
        type="card"
        class="map-tabs"
        @tab-click="handleClick"
      >
        <el-tab-pane label="企业地块基本情况" name="first"
          ><form_tab1
        /></el-tab-pane>
        <el-tab-pane label="企业污染源信息" name="second"
          ><form_tab2
        /></el-tab-pane>
        <el-tab-pane label="迁移途径信息" name="third"
          ><form_tab3
        /></el-tab-pane>
        <el-tab-pane label="敏感受体信息" name="fourth"
          ><form_tab4
        /></el-tab-pane>
        <el-tab-pane label="土壤或地下水环境监测" name="five"
          ><form_tab5
        /></el-tab-pane>
        <el-tab-pane label="环境监测和调查评估信息" name="six"
          ><form_tab6
        /></el-tab-pane>
      </el-tabs> -->
    </el-dialog>
  </config-provider>
</template>

<script setup>
import { ref, provide, computed } from "vue";
import form_tab1 from "@/views/corporate/components/tab1/form";
import form_tab2 from "@/views/corporate/components/tab2/form";
import form_tab3 from "@/views/corporate/components/tab3/form";
import form_tab4 from "@/views/corporate/components/tab4/form";
import form_tab5 from "@/views/corporate/components/tab5/form";
import form_tab6 from "@/views/corporate/components/tab6/form";
import TopHead from "./TopHead.vue";
import VerticalMenu from "./verticalMenu/index.vue";
import * as mars3d from "mars3d";
import MarsMap from "@/components/Map/mars-map.vue";
import {
  getPositionTransCoordinatesPolygon,
  geoJsonTransJson,
  getPositionTranslationOffest,
  geoJsonTransSelf,
} from "./LocalWorldTransform";
const configUrl = "config/config.json"; //"/config/config.json";
import request from "@/utils/request";
const Cesium = mars3d.Cesium;

import locale from "ant-design-vue/es/locale/zh_CN";
import { ConfigProvider } from "ant-design-vue";
import MarsWidget from "@mars/widgets/widget.vue";
import { useWidgetStore } from "@mars/widgets/common/store/widget";
import TabsCard from "@/views/corporate";
const widgetStore = useWidgetStore();
const mapLoaded = ref(false); // map加载完成
const widgets = computed(() => widgetStore.state.widgets);
const openAtStart = computed(() => widgetStore.state.openAtStart);
let mapInstance = null;
const { proxy } = getCurrentInstance();
import layerStore from "@/store/modules/layer";
import userStore from "@/store/modules/user";
const layerStoreIns = layerStore();

import { addLayer } from "@/api/admin/layer";
import { loadWells, loadEnterprises } from "./mainMap";
import { loadCadLayer } from "@/views/main/CadLayer.ts";
const deptId = ref(null);
const activeName = ref("first");

const handleClick = (tab, event) => {
  console.log(tab, event);
};

const dialogVisible = ref(false);

window.openQYMSG = function (id) {
  // console.log(event);

  // userStore().setEnterpriseId(event.attr.id);

  dialogVisible.value = true;

  deptId.value = id;
};

provide("getMapInstance", () => {
  return mapInstance;
});

let showLayer = ref(false);
let htmlComponent = ref("");

//全局函数
window.showLayerAttribute = function (event) {
  htmlComponent.value = layerStore.getPopup(event);
  showLayer.value = true;
};

const marsOnload = async (map) => {
  //layerStoreIns.setProxy(proxy);
  mapInstance = map;
  window.mars3d = mars3d;
  mapLoaded.value = true;

  const graphicLayer = new mars3d.layer.GraphicLayer();
  map.addLayer(graphicLayer);

  // 2.在layer上绑定监听事件
  graphicLayer.on(mars3d.EventType.click, function (event) {
    console.log("监听layer，单击了矢量对象", event);
  });
  graphicLayer.on(mars3d.EventType.mouseOver, function (event) {
    console.log("监听layer，鼠标移入了矢量对象", event);
  });
  graphicLayer.on(mars3d.EventType.mouseOut, function (event) {
    console.log("监听layer，鼠标移出了矢量对象", event);
  });

  window.map = map;
  window.layersObj = {};
  loadCadLayer(map);
  await loadWells();
  await loadEnterprises();

  //加载图层
  // layerStoreIns
  //   .getlistLayer()
  //   .then((res) => {
  //     setCheck(layerStoreIns.treeData);
  //   })
  //   .catch((err) => {
  //     console.error(err);
  //   });

  //加载图层集
  //await layerStoreIns.getSetlistLayer();

  //getGeoJson("http://localhost:8016/geojson/DLGX_X.geojson")
  //showRegion("建筑物面", "json/JZW_M.json");

  // getJson(
  //   "http://localhost:8016/json/GIS_JZW_M_GB312.json",
  //   "http://localhost:8016/json/JZ_JZW_M.json"
  // );

  // let offest=getPositionTranslationOffest(Cesium.Cartesian3.fromDegrees(119.65326126462519, 26.48104077194596));
  // console.log(offest);

  // loadLayers("http://localhost:8016/config/layers.txt");
};

// watch(
//   () => layerStoreIns.clipLayersCode,
//   (data) => {
//     bindLiyersClick();
//   },
//   { immediate: true }
// );

//绑定图层属性查询
function bindLiyersClick() {
  let layerCodes = layerStoreIns.clipLayersCode;
  for (let i = 0; i < layerCodes.length; i++) {
    let code = layerCodes[i];
    let layer = map.getLayer(code, "code");
    if (layer) {
      layer.on(mars3d.EventType.click, function (event) {
        console.log("监听layer，单击了矢量对象", event);
        showLayer.value = false;
        if (layerStoreIns.attribute_flag && event.attr) {
          console.log(event.attr);
          htmlComponent.value = layerStoreIns.getPopup(event);
          showLayer.value = true;
        }
      });
    }
  }
}

async function loadLayers(url) {
  let res = await request({
    url: url,
    method: "get",
  });
  console.log(res);

  res.forEach(async (l) => {
    if (l.code && l.layers) {
      await addLayer({
        id: null,
        baseId: 2,
        code: l.code, //---
        name: l.name,
        url: l.url,
        serverType: l.type,
        pid: 0,
        isShow: false,
        dataType: null,
        layerParam: `layer=${l.layers}`,
      });
    }
  });

  alert();
}

function getWMS(node) {
  return {
    name: node.name,
    crs: "EPSG:4326",
    type: "wms",
    show: node.isShow,
    url: node.url,
    layers: node.dataType,
    parameters: {
      transparent: true,
      format: "image/png",
    },
    getFeatureInfoParameters: {
      feature_count: 10,
    },
    popup: "all",
    flyTo: true,
  };
}

function getChildrenLayers(list) {
  let layers = [];
  list.forEach((node) => {
    if (node && node.url) {
      if (node.serverType == 1) {
        let layer = getWMS(node);
        layers.push(layer);
      }
    }
  });

  return layers;
}

function setCheck(list) {
  list.forEach((node) => {
    if (node && !node.url && node.children && node.children.length) {
      let g = new mars3d.layer.GroupLayer({
        layers: getChildrenLayers(node.children),
        pid: node.pid == "0" ? -1 : node.pid,
        name: node.name,
        show: node.isShow,
      });
      g._hasMapInit = true;
      window.map.addLayer(g);
    }

    if (node && node.children && node.children.length) {
      setCheck(node.children);
    }

    // if (node && node.url && node.isShow) {
    //   let layer = addTileLayer(node.url, null, true);
    //   layer.name = node.name;
    //   layer.id = node.id;
    //   layer.pid = node.pid;
    //   window.map.addLayer(layer);
    //   layerStoreIns.expandedKeys.push(node.id);
    //   layerStoreIns.checkedKeys.push(node.id);
    //   window.layersObj[node.id] = layer;
    // }
  });
}

function addTileLayer(url, layers, flyTo) {
  // 方式2：在创建地球后调用addLayer添加图层(直接new对应type类型的图层类)
  let tileLayer = new mars3d.layer.WmsLayer({
    url: url,
    layers: layers,
    parameters: {
      transparent: true,
      format: "image/png",
    },
    getFeatureInfoParameters: {
      feature_count: 10,
    },
    // 单击高亮及其样式
    highlight: {
      type: "wallP",
      diffHeight: 100,
      materialType: mars3d.MaterialType.LineFlow,
      materialOptions: {
        image: "img/textures/fence.png",
        color: "#ffff00",
        speed: 10, // 速度，建议取值范围1-100
        axisY: true,
      },
    },
    popup: "all",
    flyTo: flyTo,
  });
  // 单击事件
  tileLayer.on(mars3d.EventType.loadConfig, function (event) {
    console.log("加载了GetCapabilities", event);
  });
  tileLayer.on(mars3d.EventType.click, function (event) {
    console.log("单击了矢量数据，共" + event.features.length + "条", event);
  });
  return tileLayer;
}

async function getGeoJson(geojsonUrl) {
  let geojson = await request({
    url: geojsonUrl,
    method: "get",
  });
  let res = geoJsonTransSelf(geojson);
  console.log(res);
}

async function getJson(jsonUrl, geojsonUrl) {
  let json = await request({
    url: jsonUrl,
    method: "get",
  });

  let geojson = await request({
    url: geojsonUrl,
    method: "get",
  });
  let res = geoJsonTransJson(json, geojson);
  console.log(res);
}

function formatGeoJSON(geojson) {
  let features = geojson.features;
  for (let i = 0; i < features.length; i++) {
    let feature = features[i];
    let geometry = feature.geometry;
    let coordinates = geometry.coordinates.slice();
    geometry.coordinates = getPositionTransCoordinatesPolygon(coordinates);
  }

  console.log(geojson);

  return geojson;
}

function showRegion(name, url) {
  let graphicLayer = new mars3d.layer.GeoJsonLayer({
    name: name,
    toPrimitive: true,
    format: formatGeoJSON, // 用于自定义处理geojson
    url: url,
    symbol: {
      styleOptions: {
        fill: true,
        randomColor: true, // 随机色
        opacity: 0.3,
        outline: true,
        outlineStyle: {
          color: "#FED976",
          width: 3,
          opacity: 1,
        },
      },
    },
    popup: "{NAME}",
    flyTo: true,
  });
  map.addLayer(graphicLayer);
}
</script>

<style lang="scss" scoped>
.map-div {
  width: 100vw;
  height: 100vh;
}

// #mars-main-view {
//   position: fixed;
//   left: 0;
//   top: 0;
//   height: 100vh;
//   width: 100vw;
//   pointer-events: none;
//   z-index: 10;
//   .mars-dialog{
//     pointer-events: all;
//   }
// }
</style>
