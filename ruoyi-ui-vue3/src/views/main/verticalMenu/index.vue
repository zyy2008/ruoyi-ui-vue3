<template>
  <div class="verticalSection">
    <span class="log-div">
      <img src="../../../assets/logo/logo.png" />
    </span>
    <el-menu
      class="el-menu-vertical-demo"
      popper-class="popper-el-menu-vertical"
      :collapse="isCollapse"
      @open="handleOpen"
      @close="handleClose"
      @select="selectMenu"
    >
      <el-sub-menu index="1">
        <template #title>
          <svg-icon icon-class="cygn" />
        </template>
        <el-menu-item index="在线监测" @click="showOnlineMonitorDialog">在线监测</el-menu-item>
        <el-menu-item index="坐标查询">坐标查询</el-menu-item>
        <el-menu-item index="距离测量">距离测量</el-menu-item>
        <el-menu-item index="面积测量">面积测量</el-menu-item>
        <!-- <el-menu-item index="属性查询">属性查询</el-menu-item>
        <el-menu-item index="条件查询">条件查询</el-menu-item> -->
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <svg-icon icon-class="ghfz" />
        </template>
        <el-menu-item index="地图标注">地图标注</el-menu-item>
        <el-menu-item index="气象分析">气象分析</el-menu-item>
        <el-menu-item index="降雨分析">降雨分析</el-menu-item>
        <el-menu-item index="降雪分析">降雪分析</el-menu-item>
        <el-menu-item index="降雾分析">降雾分析</el-menu-item>
        <el-menu-item index="风场分析">风场分析</el-menu-item>
        <!-- <el-menu-item index="选址辅助">选址辅助</el-menu-item> -->
        <!-- <el-menu-item index="评价辅助">评价辅助</el-menu-item> -->
        <el-menu-item index="土方量分析">土方量分析</el-menu-item>
        <!-- <el-menu-item index="横断面分析">横断面分析</el-menu-item>
        <el-menu-item index="纵断面分析">纵断面分析</el-menu-item>
        <el-menu-item index="管线流向分析">管线流向分析</el-menu-item>
        <el-menu-item index="长度计算">长度计算</el-menu-item>
        <el-menu-item index="容量比较">容量比较</el-menu-item> -->
      </el-sub-menu>

      <el-sub-menu index="3">
        <template #title>
          <svg-icon icon-class="tcgl" />
        </template>
        <el-menu-item index="图层管理">图层管理</el-menu-item>
      </el-sub-menu>
    </el-menu>

    <div class="menu-bottom">
      <el-menu
        class="el-menu-vertical-demo"
        popper-class="popper-el-menu-vertical"
        :collapse="isCollapse"
        @select="selectMenu"
      >
        <el-sub-menu index="1">
          <template #title>
            <svg-icon icon-class="dysc" />
          </template>
          <el-menu-item index="场景出图">场景出图</el-menu-item>
          <!-- <el-menu-item index="打印输出">打印输出</el-menu-item> -->
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <svg-icon icon-class="xtsz" />
          </template>
          <el-menu-item index="系统管理">系统管理</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>

    <!-- 添加或修改定时任务对话框 -->
    <el-dialog
      modal-class="visible_LayerTree"
      draggable
      :title="'图层树'"
      v-model="visible_LayerTree"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
    >
      <TileManage />
    </el-dialog>
    <el-dialog
      modal-class="visible_SelectPoint map-dialog"
      draggable
      :title="'坐标查询'"
      v-model="visible_SelectPoint"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <SelectPoint />
    </el-dialog>

    <el-dialog
      modal-class="visible_Distance map-dialog"
      draggable
      :title="'距离测量'"
      v-model="visible_Distance"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <Distance />
    </el-dialog>

    <el-dialog
      modal-class="visible_Area map-dialog"
      draggable
      :title="'面积测量'"
      v-model="visible_Area"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <Area />
    </el-dialog>

    <el-dialog
      modal-class="visible_MapLabel map-dialog"
      draggable
      :title="'地图标注'"
      v-model="visible_MapLabel"
      width="550px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <MapLabel />
    </el-dialog>

    <el-dialog
      modal-class="visible_Print map-dialog"
      draggable
      :title="'打印输出'"
      v-model="visible_Print"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <Print />
    </el-dialog>

    <el-dialog
      modal-class="visible_ExpImage map-dialog"
      draggable
      :title="'场景出图'"
      v-model="visible_ExpImage"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <ExpImage />
    </el-dialog>

    <el-dialog
      modal-class="visible_ShortestPath map-dialog"
      draggable
      :title="'评价辅助'"
      v-model="visible_ShortestPath"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <ShortestPath />
    </el-dialog>

    <el-dialog
      modal-class="visible_VerticalSection map-dialog"
      draggable
      :title="'纵断面分析'"
      v-model="visible_VerticalSection"
      width="550px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <VerticalSection />
    </el-dialog>

    <el-dialog
      modal-class="visible_CrossSection map-dialog"
      draggable
      :title="'横断面分析'"
      v-model="visible_CrossSection"
      width="550px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <CrossSection />
    </el-dialog>

    <el-dialog
      modal-class="visible_FlowDirection map-dialog"
      draggable
      :title="'管线流向分析'"
      v-model="visible_FlowDirection"
      width="550px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <FlowDirection />
    </el-dialog>

    <el-dialog
      modal-class="visible_LengthStatistics map-dialog"
      draggable
      :title="'长度计算'"
      v-model="visible_LengthStatistics"
      width="1400px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <LengthStatistics />
    </el-dialog>

    <el-dialog
      modal-class="visible_Attribute map-dialog"
      draggable
      :title="'属性查询'"
      v-model="visible_Attribute"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <Attribute />
    </el-dialog>

    <el-dialog
      modal-class="visible_Attribute map-dialog"
      draggable
      :title="'气象分析'"
      v-model="visible_TempAnimation"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <TempAnimation />
    </el-dialog>
    <el-dialog
      modal-class="visible_Attribute map-dialog"
      draggable
      :title="'风场分析'"
      v-model="visible_CloudVue"
      width="350px"
      :modal="false"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <CloudVue />
    </el-dialog>

    <OnlineMonitorDialog ref="onlineMonitorDialogRef" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import TileManage from "../TileManage.vue";
import SelectPoint from "../SelectPoint.vue";
import Distance from "../Distance.vue";
import Area from "../Area.vue";
import LengthStatistics from "../lengthStatistics/index.vue";
import CrossSection from "../crossSection/index.vue";
import VerticalSection from "../verticalSection/index.vue";
import FlowDirection from "../flowDirection/index.vue";
import Print from "../print/index.vue";
import ShortestPath from "../shortestPath/index.vue";
import MapLabel from "@/views/admin/label/mapIndex.vue";
import { useWidget } from "@mars/widgets/common/store/widget";
import ExpImage from "../expImage/index.vue";
import Attribute from "../attribute/index.vue";
import TempAnimation from "../tempAnimation/index.vue";
import CloudVue from "../cloud/index.vue";
import OnlineMonitorDialog from './OnlineMonitorDialog.vue'

import { useRouter } from "vue-router";
const router = useRouter();

//相关处理
const { activate, disable, isActivate, updateWidget } = useWidget();

const isCollapse = ref(true);
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath);
};
const handleClose = (key, keyPath) => {
  console.log(key, keyPath);
};

function selectMenu(key, keyPath) {
  console.log(key, keyPath);
  menuClick(key);
}

let visible_LayerTree = ref(false);
let visible_SelectPoint = ref(false);
let visible_Distance = ref(false);
let visible_Area = ref(false);
let visible_MapLabel = ref(false);
let visible_Print = ref(false);
let visible_ShortestPath = ref(false);
let visible_VerticalSection = ref(false);
let visible_CrossSection = ref(false);
let visible_LengthStatistics = ref(false);
let visible_FlowDirection = ref(false);
let visible_ExpImage = ref(false);
let visible_Attribute = ref(false);
let visible_TempAnimation = ref(false);
let visible_CloudVue = ref(false);

const onlineMonitorDialogRef = ref(null)

function openCloudVue() {
  visible_CloudVue.value = true;
}

function openTempAnimation() {
  visible_TempAnimation.value = true;
}

function openAttribute() {
  visible_Attribute.value = true;
}

function openExpImage() {
  visible_ExpImage.value = true;
}

function openLengthStatistics() {
  visible_LengthStatistics.value = true;
}

function openFlowDirection() {
  visible_FlowDirection.value = true;
}

function openCrossSection() {
  visible_CrossSection.value = true;
}

function openVerticalSection() {
  visible_VerticalSection.value = true;
}

function openShortestPath() {
  visible_ShortestPath.value = true;
}

function openPrint() {
  visible_Print.value = true;
}

function openBuffer() {
  activate("query-buffer");
}

function openLocationAid() {
  activate("query-geoserver");
}

function openMeasureVolume() {
  activate("measure-volume");
}

function showLayerTree() {
  //visible_LayerTree.value = true;
  activate("manage-layers");
}

function openRain() {
  activate("analysis-rain");
}

function openSnow() {
  activate("analysis-snow");
}

function openFog() {
  activate("analysis-fog");
}

function showSelectPoint() {
  visible_SelectPoint.value = true;
}

function showDistance() {
  visible_Distance.value = true;
}

function showArea() {
  visible_Area.value = true;
}

function showMapLabel() {
  visible_MapLabel.value = true;
}

function goSystem() {
  router.push({ path: "/index" });
}

function showOnlineMonitorDialog() {
  if (onlineMonitorDialogRef.value) {
    onlineMonitorDialogRef.value.visible = true
  }
}

function menuClick(item) {
  switch (item) {
    case "系统管理":
      goSystem();
      break;
    case "属性查询":
      openAttribute();
      break;
    case "图层管理":
      showLayerTree();
      break;
    case "坐标查询":
      showSelectPoint();
      break;
    case "距离测量":
      showDistance();
      break;
    case "面积测量":
      showArea();
      break;
    case "地图标注":
      showMapLabel();
      break;
    case "土方量分析":
      openMeasureVolume();
      break;
    case "选址辅助":
      openBuffer();
      break;
    case "条件查询":
      openLocationAid();
      break;
    case "场景出图":
      openExpImage();
      break;
    case "打印输出":
      openPrint();
      break;
    case "评价辅助":
      openShortestPath();
      break;
    case "纵断面分析":
      openVerticalSection();
      break;
    case "横断面分析":
      openCrossSection();
      break;
    case "管线流向分析":
      openFlowDirection();
      break;
    case "长度计算":
      openLengthStatistics();
      break;
    case "气象分析":
      openTempAnimation();
      break;
    case "降雨分析":
      openRain();
      break;
    case "降雪分析":
      openSnow();
      break;
    case "降雾分析":
      openFog();
      break;
    case "风场分析":
      openCloudVue();
      break;
    default:
      break;
  }
}
</script>

<style lang="scss">
.verticalSection {
  position: fixed;
  left: 0px;
  top: 0px;
  width: 40px;
  height: 100vh;
  opacity: 1;
  background: #111111;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
  z-index: 9;
  .log-div img {
    width: auto;
    width: 36px;
    border-radius: 4px;
    z-index: 9;
    margin: 10px 0;
  }
  .menu-bottom {
    padding-bottom: 40px;
    border-top: solid 1px rgba(#ffffff, 0.2);
  }
  .el-menu-vertical-demo {
    width: 100%;
    background-color: transparent;
    border-right: none;
    .el-sub-menu {
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .el-sub-menu__title {
      padding: 0;
      justify-content: center;
      width: 32px;
      height: 32px;
      border-radius: 4px;
      color: rgba(#ffffff, 0.5);
      font-size: 14px;
    }

    .el-sub-menu__title:hover {
      background: rgba(#ffffff, 0.2);
      color: rgba(#ffffff, 1);
    }

    .el-menu-item .el-menu-tooltip__trigger {
      padding: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}

.popper-el-menu-vertical {
  background: rgba(0, 0, 0, 0.8) !important;
  width: 120px;
  border-radius: 4px;
  border: none !important;
  .popper-el-menu-vertical {
    background: transparent !important;
  }
  .el-menu--vertical > .el-menu--popup {
    background-color: transparent !important;
    width: 120px !important;
    min-width: 120px !important;
    padding: 0;
    margin: 0;
    height: auto !important;
    overflow-y: hidden !important;
    .el-menu-item {
      margin: 0;
      padding: 0;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 32px;
      font-family: MiSans;
      font-size: 14px;
      font-weight: 600;
      line-height: 14px;
      letter-spacing: 0em;
      font-variation-settings: "opsz" auto;
      font-feature-settings: "kern" on;
      color: rgba(#ffffff, 0.5);
    }

    .el-menu-item:hover {
      background-color: rgba(255, 255, 255, 0.2) !important;
      color: #ffffff;
    }
  }
}
</style>

<style lang="scss">
.visible_LayerTree {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_SelectPoint {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}
.visible_Distance {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_Area {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_MapLabel {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }

  .pagination-container .el-pagination {
    right: 40px;
  }
}

.visible_Print {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_ExpImage {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_Attribute {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_ShortestPath {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_VerticalSection {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}
.visible_CrossSection {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}
.visible_LengthStatistics {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}

.visible_FlowDirection {
  pointer-events: none;
  .el-dialog {
    right: 20px;
    position: absolute;
    left: auto;
    pointer-events: auto;
  }
}
</style>
