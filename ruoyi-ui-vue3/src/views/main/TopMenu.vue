<template>
  <div class="top-menu">
    <ul class="menu">
      <li
        v-for="(item, index) in menuItems"
        :key="index"
        class="menu-item"
        @mouseenter="onMouseEnter(index, $event, item.children.length)"
        @mouseleave="onMouseLeave"
      >
        <span>{{ item.name }}</span>
        <!-- 一级菜单 -->
        <transition name="slide-fade">
          <ul
            v-if="activeMenuIndex === index"
            @mouseleave="onMouseLeavesub"
            :style="submenuStyle"
            ref="submenu"
            class="submenu"
          >
            <!-- 二级菜单 -->
            <div
              v-for="(group, groupIndex) in item.children"
              :key="groupIndex"
              class="submenu-group"
            >
              <ul>
                <!-- 渲染子菜单项 -->
                <li
                  class="menu-item-sub"
                  v-for="(subItem, subIndex) in group.items"
                  :key="subIndex"
                  @click="menuClick(subItem)"
                >
                  {{ subItem }}
                </li>
              </ul>
              <div class="submenu-group-text">{{ group.groupName }}</div>
            </div>
          </ul>
        </transition>
      </li>
    </ul>
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
      modal-class="visible_Distance"
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
      modal-class="visible_Area"
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
      :destroy-on-close="false"
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
      modal-class="visible_ShortestPath"
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
  </div>
</template>

<script setup>
import { ref, nextTick } from "vue";
import TileManage from "./TileManage.vue";
import SelectPoint from "./SelectPoint.vue";
import Distance from "./Distance.vue";
import Area from "./Area.vue";
import LengthStatistics from "./lengthStatistics/index.vue";
import CrossSection from "./crossSection/index.vue";
import VerticalSection from "./verticalSection/index.vue";
import FlowDirection from "./flowDirection/index.vue";
import Print from "./print/index.vue";
import ShortestPath from "./shortestPath/index.vue";
import MapLabel from "@/views/admin/label/mapIndex.vue";
import { useWidget } from "@mars/widgets/common/store/widget";
import ExpImage from "./expImage/index.vue";
import Attribute from "./attribute/index.vue";

//相关处理
const { activate, disable, isActivate, updateWidget } = useWidget();

const menuItems = ref([
  {
    name: "常用功能",
    children: [
      {
        groupName: "",
        items: ["属性查询"],
      },
    ],
  },
  {
    name: "图层管理",
    children: [
      {
        groupName: "",
        items: ["图层管理"],
      },
    ],
  },
  {
    name: "规划辅助",
    children: [
      {
        groupName: "地图量算",
        items: ["坐标查询", "距离测量", "面积测量"],
      },
      {
        groupName: "信息查询",
        items: ["条件查询"],
      },
      {
        groupName: "",
        items: ["地图标注"],
      },
      {
        groupName: "",
        items: ["选址辅助"],
      },
      {
        groupName: "",
        items: ["评价辅助"],
      },
      {
        groupName: "",
        items: ["土方量分析"],
      },
      {
        groupName: "管网设计辅助",
        items: [
          "管网专题图",
          "横断面分析",
          "纵断面分析",
          "管线流向分析",
          "长度计算",
          "容量比较",
        ],
      },
    ],
  },
  {
    name: "项目管理",
    children: [
      {
        groupName: "",
        items: ["项目管理"],
      },
    ],
  },
  {
    name: "打印输出",
    children: [
      {
        groupName: "",
        items: ["场景出图", "打印输出"],
      },
    ],
  },
  // {
  //   name: "系统管理",
  //   children: [
  //     {
  //       groupName: "",
  //       items: ["用户管理", "角色管理", "申请审批", "密码修改", "个人信息", "帮助"],
  //     },
  //   ],
  // },
]);

const activeMenuIndex = ref(null);
const submenuStyle = ref({
  left: "50%",
  transform: "translateX(-50%)",
});

const submenu = ref(null); // 用来引用当前的 submenu 元素

const onMouseEnter = async (index, event, count) => {
  activeMenuIndex.value = index;

  // 等待下一次 DOM 更新
  await nextTick();

  const submenuWidth = submenu.value[0].offsetWidth; // 获取二级菜单的实际宽度
  //const submenuHeight = 80 || submenu.value[0].offsetHeight; // 获取二级菜单的实际宽度
  console.log(submenuWidth);

  //const submenuWidth = count * 200; // 获取二级菜单的实际宽度
  const pageWidth = window.innerWidth;
  const boundingRect = event.target.getBoundingClientRect();

  let leftPosition = -submenuWidth / 2 + boundingRect.width / 2;

  // 如果超出右侧窗口，则调整位置
  if (
    boundingRect.left + boundingRect.width / 2 + submenuWidth / 2 >
    pageWidth
  ) {
    leftPosition = -submenuWidth + (pageWidth - boundingRect.left); // 保留一些空隙
  }

  // 如果超出左侧窗口，则调整位置
  if (submenuWidth / 2 > boundingRect.left + boundingRect.width / 2) {
    leftPosition = -(boundingRect.left + boundingRect.width / 2); // 保留一些空隙
  }
  // 动态更新 submenuStyle 的值
  submenuStyle.value = {
    left: `${leftPosition}px`,
    transform: "translateX(0)",
    //height: `${submenuHeight}px`,
  };
};

const onMouseLeavesub = () => {
  activeMenuIndex.value = null;
};
const onMouseLeave = () => {
  //activeMenuIndex.value = null;
};

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

function openBuffer(){
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

const menuClick = (item) => {
  console.log(item);
  switch (item) {
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
    default:
      break;
  }
};
</script>

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
<style scoped>
.top-menu {
  background-color: #ffffff;
  color: #333333;
  height: 50px;
  display: flex;
  align-items: center;
}

.menu {
  list-style: none;
  padding: 0;
  margin: 0;
  height: 100%;
  display: flex;
  align-items: center;
}

.menu-item {
  position: relative;
  margin-right: 20px;
  cursor: pointer;
  height: 100%;
  display: flex;
  align-items: center;
}

.submenu {
  position: absolute;
  top: 52px;
  left: 50%;
  transform: translateX(-50%);
  background-color: #eeeeee;
  padding: 10px;
  display: flex;
  justify-content: space-around;
  white-space: nowrap;
  overflow: hidden;
  z-index: 999999;
  /* height: 75px; */
}
.menu-item-sub {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 8px 0px;
  width: 100px;
  list-style: none;
}
.submenu-group-text {
  width: 100%;
  border-top: 1px solid #cccccc;
  display: flex;
  align-items: center;
  justify-content: center;
}

.submenu-group {
  border-right: 1px solid #cccccc;
}

.submenu-group:last-child {
  border-right: none;
}
.submenu-group ul {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
  margin: 0;
}
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: height 0.03s ease;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  height: 0;
  /* opacity: 0; */
  /* transform: translateY(-10px); */
}
</style>
