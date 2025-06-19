<template>
  <div class="head-div">
    <div class="header">
      <span class="main-text">清徐地下水在线监测软件平台</span>
      <div class="avatar-container">
        <el-dropdown
          @command="handleCommand"
          class="right-menu-item hover-effect"
          trigger="click"
        >
          <div class="avatar-wrapper">
            <img :src="userStore.avatar" class="user-avatar" />
            <el-icon><caret-bottom /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item divided command="goSystem">
                <span>系统管理</span>
              </el-dropdown-item>
              <el-dropdown-item divided command="logout">
                <span>退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
  <div class="map-tools">
    <div
      class="tool-button"
      @click="handleToolClick('layer')"
      :class="{ active: currentTool === 'layer' }"
    >
      <!-- <el-icon><Position /></el-icon> -->
      <svg-icon icon-class="tcgl" />
      <span>图层管理</span>
    </div>

    <div
      class="tool-button"
      @click="handleToolClick('location')"
      :class="{ active: currentTool === 'location' }"
    >
      <el-icon>
        <Position />
      </el-icon>
      <span>坐标查询</span>
    </div>
    <div
      class="tool-button"
      @click="handleToolClick('distance')"
      :class="{ active: currentTool === 'distance' }"
    >
      <el-icon>
        <Connection />
      </el-icon>
      <span>距离测量</span>
    </div>
    <div
      class="tool-button"
      @click="handleToolClick('area')"
      :class="{ active: currentTool === 'area' }"
    >
      <el-icon>
        <FullScreen />
      </el-icon>
      <span>面积测量</span>
    </div>
  </div>
  <div class="bottom">
    <div class="bottomIn">
      <div class="bottomInOt" @click="clickSystem('zhdd')">
        <img src="@/assets/static/1.png" />
        <div class="text" :class="{ 'text-active': activeText === 'zhdd' }">指挥调度</div>
      </div>
      <div class="bottomInOt" @click="clickSystem('zxjc')">
        <img src="@/assets/static/2.png" />
        <div class="text" :class="{ 'text-active': activeText === 'zxjc' }">在线监测</div>
      </div>
      <div class="bottomInOt" @click="clickSystem('qyxx')">
        <img src="@/assets/static/3.png" />
        <div class="text" :class="{ 'text-active': activeText === 'qyxx' }">企业信息</div>
      </div>
      <div class="bottomInOt" @click="clickSystem('bjyb')">
        <img src="@/assets/static/4.png" />
        <div class="text" :class="{ 'text-active': activeText === 'bjyb' }">报警预报</div>
      </div>
    </div>
  </div>

  <LeftTopTable class="leftTable" v-show="zxjcShow" />
  <RightTopLine class="RightLine" v-show="zhddShow" />
  <RightBottomVideo class="RightVideo" v-show="zxjcShow" />
  <RightBottomTable class="RightTable" v-show="zhddShow || bjybShow" />
  <RightTopTable class="RightLine" v-show="bjybShow" />
  <RightCenterLine class="RightCenter" v-show="zxjcShow" />
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
</template>

<script setup>
import SelectPoint from "./SelectPoint.vue";
import Distance from "./Distance.vue";
import Area from "./Area.vue";
import TopMenu from "./TopMenu.vue";
import { ElMessageBox } from "element-plus";
import useAppStore from "@/store/modules/app";
import useUserStore from "@/store/modules/user";
import useSettingsStore from "@/store/modules/settings";
import { useRouter } from "vue-router";
import RightBottomTable from "./commandDispatch/RightBottomTable.vue";
import RightBottomVideo from "./commandDispatch/RightBottomVideo.vue";
import LeftTopTable from "./commandDispatch/LeftTopTable.vue";
import RightTopLine from "./commandDispatch/RightTopLine.vue";
import RightTopTable from "./commandDispatch/RightTopTable.vue";
import RightCenterLine from "./commandDispatch/RightCenterLine.vue";
import layerStore from "@/store/modules/layer";
import { useVideoStore } from "@/store/modules/app";
import { useWidget } from "@mars/widgets/common/store/widget";
import { watch } from "vue";
//相关处理
const { activate, disable, isActivate, updateWidget } = useWidget();
const router = useRouter();
const appStore = useAppStore();
const userStore = useUserStore();
const settingsStore = useSettingsStore();
const zhddShow = ref(true);
const qyxxShow = ref(false);
const zxjcShow = ref(false);
const bjybShow = ref(false);
const videoStore = useVideoStore();
watch(
  videoStore.videoState,
  (newValue) => {
    if (newValue === true) {
      zhddShow.value = false;
      qyxxShow.value = false;
      zxjcShow.value = false;
      bjybShow.value = false;
    } else if (newValue === false) {
      zxjcShow.value = true;
    }
  },
  { immediate: true, deep: true }
);

// const chartInfo = ref({
//   wellCode: "J01",
// });

const activeText = ref("zhdd");
const emits = defineEmits(["setLayout"]);
function setLayout() {
  emits("setLayout");
}

const currentTool = ref("");

let visible_SelectPoint = ref(false);
let visible_Distance = ref(false);
let visible_Area = ref(false);

function showLayerTree() {
  activate("manage-layers");
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

function handleCommand(command) {
  switch (command) {
    case "goSystem":
      goSystem();
      break;
    case "logout":
      logout();
      break;
    default:
      break;
  }
}

function logout() {
  ElMessageBox.confirm("确定注销并退出系统吗？", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      userStore.logOut().then(() => {
        location.href = "/index";
      });
    })
    .catch(() => {});
}

function clickSystem(ment) {
  if (ment === activeText.value) {
    // If clicking the same button, toggle off
    zhddShow.value = false;
    qyxxShow.value = false;
    zxjcShow.value = false;
    bjybShow.value = false;
    activeText.value = "";
  } else {
    // If clicking a different button, switch to it
    zhddShow.value = ment === "zhdd";
    qyxxShow.value = ment === "qyxx";
    zxjcShow.value = ment === "zxjc";
    bjybShow.value = ment === "bjyb";
    activeText.value = ment;
  }

  if (ment === "qyxx") {
    const route = router.resolve({ path: "/system/enterprise" });
    window.open(route.href, "_blank");
  }
}

// function changeTableLine(value) {
//   console.log(chartInfo.value);

//   chartInfo.value = value;
// }

function goSystem() {
  router.push({ path: "/index" });
}

function handleToolClick(tool) {
  if (currentTool.value === tool) {
    currentTool.value = "";
    switch (tool) {
      case "location":
        // TODO: 取消定位工具
        break;
      case "distance":
        // TODO: 取消距离测量
        break;
      case "area":
        // TODO: 取消面积测量
        break;
      case "angle":
        // TODO: 取消角度测量
        break;
    }
  } else {
    currentTool.value = tool;
    switch (tool) {
      case "layer":
        showLayerTree();
        console.log("激活图层工具");
        break;
      case "location":
        showSelectPoint();
        console.log("激活定位工具");
        break;
      case "distance":
        showDistance();
        console.log("激活距离测量");
        break;
      case "area":
        showArea();
        console.log("激活面积测量");
        break;
      case "angle":
        console.log("激活角度测量");
        break;
    }
  }
}
</script>

<style lang="scss" scoped>
.head-div {
  background: linear-gradient(
    180deg,
    #000000 0%,
    rgba(0, 0, 0, 0.5) 52%,
    rgba(0, 0, 0, 0) 100%
  );
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-between;
  width: 100vw;

  .avatar-container {
    right: 40px;
    position: absolute;
    top: 26px;

    .avatar-wrapper {
      margin-top: 5px;
      position: relative;

      .user-avatar {
        cursor: pointer;
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }

      i {
        cursor: pointer;
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }

  .header {
    width: 100%;
    height: 77px;
    line-height: 90px;
    background: url("@/assets/static/title.png") no-repeat;
    background-size: 100vw 10vh;
    text-align: center;

    span {
      font-size: 1.6vw;
      font-weight: 700;
      background: linear-gradient(75deg, #21f1c2, #66fdf1, #1ebcea);
      -webkit-text-fill-color: transparent;
      -webkit-background-clip: text;
    }

    background-color: rgba(9, 21, 42, 0.8) !important;
  }
}

.bottom {
  width: 100%;
  height: 80px;
  position: absolute;
  bottom: 26px;
  background: url("@/assets/static/bottom.png") no-repeat;
  background-size: 100vw 10vh;
  z-index: 999;
  display: flex;
  justify-content: center;

  .bottomIn {
    width: 700px;
    height: 58px;
    margin-top: 10px;
    display: flex;
    justify-content: space-between;

    .bottomInOt {
      width: 25%;
      height: 100%;
      display: flex;
      justify-content: space-around;
      align-items: center;
      cursor: pointer;
    }

    .text {
      width: 108px;
      height: 46px;
      text-align: center;
      line-height: 46px;
      font-weight: 700;
      font-size: 18px;
      background: url("@/assets/static/textbg.png") no-repeat;
    }
  }
}

.leftTable {
  width: 430px;
  height: 760px;
  position: absolute;
  left: 20px;
  top: 90px;
  z-index: 30;
  background-color: rgba(9, 21, 42, 0.8);
}

.RightLine {
  width: 460px;
  height: 400px;
  position: absolute;
  top: 90px;
  right: 20px;
  z-index: 20;
}

.RightCenter {
  width: 1420px;
  height: 500px;
  position: absolute;
  top: 90px;
  right: 20px;
  z-index: 10;
}

.RightTable {
  width: 460px;
  height: 350px;
  position: absolute;
  top: 510px;
  right: 20px;
  z-index: 10;
}

.RightVideo {
  width: 1420px;
  height: 200px;
  position: absolute;
  top: 600px;
  right: 20px;
  z-index: 10;
}

.app-container {
  background-color: rgba(9, 21, 42, 0.8);
}

:deep() {
  .el-table tr {
    background-color: rgba(21, 85, 75, 0.2);
  }

  .el-table {
    --el-table-bg-color: none;
    --el-table-text-color: white;
    --el-table-border-color: none;
    --el-table-row-hover-bg-color: none;
  }

  .el-table .el-table__row:hover {
    cursor: pointer;
  }

  .el-table .el-table__header-wrapper th,
  .el-table .el-table__fixed-header-wrapper th {
    background-color: #155548 !important;
    color: white;
  }
}

.text-active {
  color: yellow;
  background-color: red;
}

.map-tools {
  position: fixed;
  left: 20px;
  top: 20px;
  z-index: 1;
  display: flex;
  flex-direction: row;
  gap: 10px;
  padding: 0 4px;
  height: 77px;
  align-items: center;

  .tool-button {
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 8px 12px;
    background-color: rgba(21, 85, 75, 0.2);
    border-radius: 4px;
    cursor: pointer;
    color: white;
    transition: all 0.3s ease;
    height: 40px;
    text-align: center;

    &:hover {
      background-color: rgba(21, 85, 75, 0.4);
    }

    &.active {
      background-color: rgba(21, 85, 75, 0.6);
      border: 1px solid #21f1c2;
    }

    .el-icon {
      font-size: 18px;
    }

    span {
      font-size: 14px;
    }
  }

  .text-active {
    color: yellow;
  }
}

:deep() {
  .el-dialog {
    --el-dialog-bg-color: rgba(9, 21, 42, 0.8) !important;
  }

  .el-dialog__title {
    color: white;
  }

  .el-input__wrapper {
    background-color: transparent;
  }

  .el-select__wrapper {
    background-color: transparent;
  }

  .el-select__placeholder {
    color: white;
  }
}
</style>
