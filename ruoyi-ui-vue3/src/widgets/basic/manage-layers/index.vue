<template>
  <mars-dialog
    custom-class="manage-layer_pannel"
    :draggable="true"
    title="图层管理"
    width="300"
    :min-width="250"
    top="100"
    left="50"
  >
    <el-tabs class="map-tab" v-model="activeName">
      <el-tab-pane label="基础图层" name="basic">
        <div></div>
        <mars-tree
          class="layer-tree"
          checkable
          :tree-data="treeData"
          v-model:expandedKeys="expandedKeys"
          v-model:checkedKeys="checkedKeys"
          v-model:defaultExpandedKeys="defaultExpandedKeys"
          @check="checkedChange"
        >
          <template #title="node">
            <mars-dropdown-menu :trigger="['contextmenu']">
              <span @dblclick="flyTo(node)">{{ node.title }}</span>
              <template #overlay v-if="node.hasZIndex">
                <a-menu @click="(menu) => onContextMenuClick(node, menu.key)">
                  <a-menu-item key="1">图层置为顶层</a-menu-item>
                  <a-menu-item key="2">图层上移一层</a-menu-item>
                  <a-menu-item key="3">图层下移一层</a-menu-item>
                  <a-menu-item key="4">图层置为底层</a-menu-item>
                </a-menu>
              </template>
            </mars-dropdown-menu>
            <span
              v-if="node.hasOpacity"
              v-show="node.checked"
              class="tree-slider"
            >
              <mars-slider
                v-model:value="opacityObj[node.id]"
                :min="0"
                :step="1"
                :max="100"
                @change="opcityChange(node)"
              />
            </span>
          </template>
        </mars-tree>
      </el-tab-pane>
      <el-tab-pane
        style="height: 600px; overflow-y: auto"
        label="分类图层"
        name="FLTC"
      >
        <a-collapse v-model:activeKey="activeKey">
          <a-collapse-panel
            v-for="(value, key, index) in FLDATA"
            :key="index"
            :header="key"
          >
            <div
              @click="flyToWell(item)"
              v-for="item in value"
              class="line-wellCode"
              :key="item.id"
            >
              <!-- <img :src="getImageUrl()" /> -->
              <span style="color:#ffff00;">{{ item.attr.wellCode }}</span>
              <span>{{ `(${item.attr.location})` }}</span>
            </div>
          </a-collapse-panel>
        </a-collapse>
      </el-tab-pane>
    </el-tabs>

    <template #footer>
      <div class="tips">提示：双击可定位视域至其所在位置</div>
    </template>
  </mars-dialog>
</template>

<script lang="ts" setup>
import { onUnmounted, nextTick, reactive, ref, onMounted, computed } from "vue";
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map";
import { useWidget } from "@mars/widgets/common/store/widget";
import { Search } from "@element-plus/icons-vue";
const { activate, disable, currentWidget } = useWidget();
const activeName = ref("basic");
const activeKey = ref(["0"]);
//import layerStore from "@/store/modules/layer";

//const layerStoreIns = layerStore();

useLifecycle(mapWork);

// currentWidget.onUpdate(() => {
//   treeData.value = [];
//   expandedKeys.value = [];
//   checkedKeys.value = [];
//   //initTree();
// });
const baseIds = ref<number[]>([]);
const baseOptions = []; //layerStoreIns.baseList;
const text = ref<string>("");
const treeData = ref<any[]>([]);

const expandedKeys = ref<string[]>([]);
const defaultExpandedKeys = ref<any[]>([]);

const checkedKeys = ref<string[]>([]);

const layersObj: any = {};

const opacityObj: any = reactive({});
let FLDATA = ref({});
onMounted(() => {
  //baseIds.value = layerStoreIns.selectedBase.slice();
  initTree();
  initFLTC();
});
onUnmounted(() => {
  disable("layer-tree");
});
function initFLTC() {
  let layer = map.getLayer("监测井信息", "name");
  let graphics = layer?.graphics || [];
  // 按照 attr.pointType 分类
  FLDATA.value = graphics.reduce((acc: any, graphic: any) => {
    const type = graphic?.attr?.pointType || "未知";
    graphic.logo = "img/marker/well.png";
    if (!acc[type]) acc[type] = [];
    acc[type].push(graphic);
    return acc;
  }, {} as Record<string, any[]>);
  console.log("按pointType分类结果", FLDATA);
}
function flyToWell(item) {
  if (map && item.attr.longitude && item.attr.latitude) {
    map.setCameraView({
      lng: Number(item.attr.longitude),
      lat: Number(item.attr.latitude),
      alt: 1200,
    });
  }
}
function getImageUrl(name) {
  name = name || "well.png";
  alert("获取图片路径：" + name);
  return new URL(`@/assets/images/${name}.png`, import.meta.url).href;
}

function filterTree(node, keyword) {
  // 创建一个数组来存储匹配的节点
  let matchedNodes = [];

  // 检查当前节点是否包含关键字
  if (node.title && node.title.toLowerCase().includes(keyword.toLowerCase())) {
    matchedNodes.push(node);
  }

  // 递归检查子节点
  if (node.children) {
    node.children.forEach((child) => {
      matchedNodes = matchedNodes.concat(filterTree(child, keyword));
    });
  }

  return matchedNodes;
}
// watch(text, (value) => {
//   if (!value) {
//     return;
//   }
//   const expanded = filterTree({ children: treeData.value }, value) || [];

//   console.log(expanded);
//   expandedKeys.value = expanded.map((n) => n.key + "");
//   text.value = value;
// });

let lastWidget: any;
const checkedChange = (keys: string[], e: any) => {
  const layer = layersObj[e.node.id];
  // console.log("点击的矢量图层", layer)
  if (layer) {
    if (!layer.isAdded) {
      mapWork.addLayer(layer);
    }

    // 特殊处理同目录下的单选的互斥的节点，可在config对应图层节点中配置"radio":true即可
    if (layer.options?.radio && e.checked) {
      // 循环所有的图层
      for (const i in layersObj) {
        const item = layersObj[i];
        // 循环所有的打开的图层
        checkedKeys.value.forEach((key, index) => {
          // 在所有图层中筛选与打开图层对应key值的图层 以及 与当前操作的图层的pid相同的图层
          if (item === layersObj[key] && layer.pid === layersObj[key].pid) {
            // 筛选出不是当前的其他图层进行图层隐藏以及移除
            if (item !== layer) {
              checkedKeys.value.splice(index, 1);
              item.show = false;
            }
          }
        });
      }
    }

    // 处理图层的关联事件
    if (layer.options?.onWidget) {
      if (e.checked) {
        if (lastWidget) {
          disable(lastWidget);
        }
        activate({
          name: layer.options.onWidget,
        });
        lastWidget = layer.options.onWidget;
      } else {
        disable(layer.options.onWidget);
      }
    }

    // 处理子节点
    if (e.node.children && e.node.children.length) {
      renderChildNode(keys, e.node.children);
    }
    if (keys.indexOf(e.node.id) !== -1) {
      layer.show = true;
      if (!layer.options.noCenter) {
        // 在对应config.json图层节点配置 noCenter:true 可以不定位
        layer.readyPromise.then(function (layer) {
          layer.flyTo({ scale: 2 });
        });
      }
    } else {
      layer.show = false;
    }

    // 处理图层构件树控件
    if (layer.options?.scenetree) {
      initLayerTree(layer);
    }
  }
};

function renderChildNode(keys: string[], children: any[]) {
  children.forEach((child) => {
    const layer = layersObj[child.id];
    if (layer) {
      if (!layer.isAdded) {
        mapWork.addLayer(layer);
      }
      if (keys.indexOf(child.id) !== -1) {
        layer.show = true;
      } else {
        layer.show = false;
      }
      if (child.children) {
        renderChildNode(keys, child.children);
      }
      if (layer.options.scenetree) {
        initLayerTree(layer);
      }
    }
  });
}

const opcityChange = (node: any) => {
  const id = node.id;
  const layer = layersObj[id];
  if (layer) {
    layer.opacity = opacityObj[id] / 100;
  }
};

const onContextMenuClick = (node: any, type: string) => {
  const parent = node.parent;
  const index = node.index;
  switch (type) {
    case "1": {
      if (index !== 0) {
        parent.children[0].index = index;
        parent.children[index].index = 0;
      }
      break;
    }
    case "2": {
      parent.children[index - 1].index = index;
      parent.children[index].index = index - 1;
      break;
    }
    case "3": {
      parent.children[index + 1].index = index;
      parent.children[index].index = index + 1;
      break;
    }
    case "4": {
      parent.children[parent.children.length - 1].index = index;
      parent.children[index].index = parent.children.length - 1;
      break;
    }
  }

  parent.children = parent.children.sort((a: any, b: any) => a.index - b.index);
};

function flyTo(item: any) {
  if (item.checked) {
    const layer = layersObj[item.id];
    layer.flyTo();
  }
}

let lengthStatistics = {}; //
let flowDirection = [];
let sectionLayers = [];
let sectionLayersOne = [];
function initTree() {
  // lengthStatistics = {}; //
  // flowDirection = [];
  // sectionLayers = [];
  // sectionLayersOne = [];

  const layers = mapWork.getLayers();
  console.log("layers", layers);
  for (let i = layers.length - 1; i >= 0; i--) {
    const layer = layers[i]; // 创建图层

    if (layer == null || !layer.options || layer.isPrivate) {
      continue;
    }
    const item = layer.options;
    if (!item.name || item.name === "未命名") {
      console.log("未命名图层不加入图层管理", layer);
      continue;
    }

    if (
      !layer._hasMapInit &&
      !item._hasMapInit &&
      layer.pid === -1 &&
      layer.id !== 99
    ) {
      layer.pid = 99; // 示例中创建的图层都放到99分组下面
    }

    layersObj[layer.id] = layer;

    if (layer && !layer.pid) {
      const node: any = reactive({
        index: i,
        title: layer.name || `未命名(${layer.type})`,
        key: layer.id,
        id: layer.id,
        pId: layer.pid,
        hasZIndex: layer.hasZIndex,
        hasOpacity: layer.hasOpacity,
        opacity: 100 * (layer.opacity || 0),
      });
      if (layer.hasOpacity) {
        opacityObj[layer.id] = 100 * (layer.opacity || 0);
      }
      node.children = findChild(node, layers);
      treeData.value.push(node);

      if (layer.options.open !== false) {
        expandedKeys.value.push(node.key);
      }

      if (layer.isAdded && layer.show) {
        nextTick(() => {
          checkedKeys.value.push(node.key);
        });
      }
    }
  }
}
import * as mars3d from "mars3d";
const eventTarget = new mars3d.BaseClass(); // 事件对象，用于抛出事件到面板中
function bindLiyersClick() {
  let list = map.getLayers({
    basemaps: true,
    layers: true,
  });
  for (let i = 0; i < list.length; i++) {
    let layer = list[i];
    if (layer) {
      layer.on(mars3d.EventType.click, function (event) {
        console.log("监听layer，单击了矢量对象", event);
        if (event.target && event.target.name == "宝武集团") {
          event.attr = event.graphic.attr;
          changeBase(event.attr);
        }
        if (event.target && event.target.name == "全国钢铁") {
          event.attr = event.graphic.attr;
        }
        // if (layerStoreIns.attribute_flag && event.attr) {
        //   console.log(event.attr);
        //   window.showLayerAttribute(event)
        // }
      });
    }
  }
}

function changeBase(attr) {
  baseIds.value = [attr.ID];
}

function findChild(parent: any, list: any[]) {
  return list
    .filter((layer: any) => {
      if (layer == null || !layer.options || layer.isPrivate) {
        return false;
      }
      const item = layer.options;
      if (!item.name || item.name === "未命名") {
        return false;
      }
      return layer.pid === parent.id;
    })
    .reverse()
    .map((item: any, i: number) => {
      const node: any = {
        index: i,
        title: item.name || `未命名(${item.type})`,
        key: item.id,
        id: item.id,
        pId: item.pid,
        hasZIndex: item.hasZIndex,
        hasOpacity: item.hasOpacity,
        opacity: 100 * (item.opacity || 0),
        parent: parent,
        // url: item.options.url,
        // layers: item.options.layers,
        // code: item.options.code,
      };

      if (item.hasOpacity) {
        opacityObj[item.id] = 100 * (item.opacity || 0);
      }
      layersObj[item.id] = item;

      node.children = findChild(node, list);

      if (item.options.open !== false) {
        expandedKeys.value.push(node.key);
      }

      if (item.isAdded && item.show) {
        nextTick(() => {
          checkedKeys.value.push(node.key);
        });
      }

      // //处理开始
      // //GYGX_X_GG
      // if (node.title == "工业管道" && node.children.length > 0) {
      //   let hasArr = node.children.filter(
      //     (l: any) => l.code != "GYGX_D" && l.code != "GYGX_F"
      //   );
      //   if (hasArr.length > 0) {
      //     lengthStatistics["工业管道"] = hasArr[0];
      //     flowDirection.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasArr,
      //     });
      //   }

      //   let hasSection = hasArr.filter((l: any) => l.code == "GYGX_X_GG");
      //   if (hasSection.length > 0) {
      //     sectionLayers.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasSection,
      //     });

      //     sectionLayersOne.push(...hasSection);
      //   }
      // }
      // //--DLGX_X
      // if (node.title == "电力线" && node.children.length > 0) {
      //   let hasArr = node.children.filter((l: any) => l.code == "DLGX_X");
      //   if (hasArr.length > 0) {
      //     lengthStatistics["电力线"] = hasArr[0];
      //   }

      //   let hasSection = hasArr.filter((l: any) => l.code == "DLGX_X");
      //   if (hasSection.length > 0) {
      //     sectionLayers.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasSection,
      //     });
      //     sectionLayersOne.push(...hasSection);
      //   }
      // }
      // if (node.title == "其他管线" && node.children.length > 0) {
      //   lengthStatistics["其他管线"] = node.children[0];
      // }
      // if (node.title == "热力管道" && node.children.length > 0) {
      //   let hasArr = node.children.filter(
      //     (l: any) => l.code != "RLGX_D" && l.code != "RLGX_F"
      //   );

      //   if (hasArr.length > 0) {
      //     lengthStatistics["热力管道"] = hasArr[0];
      //     flowDirection.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasArr,
      //     });
      //   }
      // }

      // if (node.title == "燃气管道" && node.children.length > 0) {
      //   let hasArr = node.children.filter(
      //     (l: any) => l.code != "RQGX_D" && l.code != "RQGX_F"
      //   );

      //   if (hasArr.length > 0) {
      //     lengthStatistics["燃气管道"] = hasArr[0];
      //     flowDirection.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasArr,
      //     });
      //   }
      // }
      // //XSGX_X_YS、XSGX_X_WSG
      // if (node.title == "下水管" && node.children.length > 0) {
      //   let hasArr = node.children.filter(
      //     (l: any) => l.code != "XSGX_D" && l.code != "XSGX_F"
      //   );

      //   if (hasArr.length > 0) {
      //     lengthStatistics["下水管"] = hasArr[0];
      //     flowDirection.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasArr,
      //     });
      //   }

      //   let hasSection = hasArr.filter(
      //     (l: any) => l.code == "XSGX_X_YS" || l.code == "XSGX_X_WSG"
      //   );
      //   if (hasSection.length > 0) {
      //     sectionLayers.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasSection,
      //     });

      //     sectionLayersOne.push(...hasSection);
      //   }
      // }
      // //SSGX_X_SHSS、SSGX_X_SCSS、MP-XH
      // if (node.title == "上水管" && node.children.length > 0) {
      //   let hasArr = node.children.filter(
      //     (l: any) => l.code != "SSGX_D" && l.code != "SSGX_F"
      //   );
      //   if (hasArr.length > 0) {
      //     lengthStatistics["上水管"] = hasArr[0];
      //     flowDirection.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasArr,
      //     });
      //   }

      //   let hasSection = hasArr.filter(
      //     (l: any) =>
      //       l.code == "SSGX_X_SHSS" || l.code == "SSGX_X_SCSS" || l.code == "MP-XH"
      //   );
      //   if (hasSection.length > 0) {
      //     sectionLayers.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasSection,
      //     });

      //     sectionLayersOne.push(...hasSection);
      //   }
      // }

      // //TXGX_X
      // if (node.title == "通信线" && node.children.length > 0) {
      //   let hasArr = node.children.filter(
      //     (l: any) => l.code != "TXGX_D" && l.code != "TXGX_F"
      //   );

      //   if (hasArr.length > 0) {
      //     lengthStatistics["通信线"] = hasArr[0];
      //   }

      //   let hasSection = hasArr.filter((l) => l.code == "TXGX_X");
      //   if (hasSection.length > 0) {
      //     sectionLayers.push({
      //       id: node.id,
      //       title: node.title,
      //       children: hasSection,
      //     });

      //     sectionLayersOne.push(...hasSection);
      //   }
      // }

      return node;
    });
}

function initLayerTree(layer: any) {
  disable("layer-tree");

  if (lastBindClickLayer) {
    lastBindClickLayer.off("click", onClickBimLayer);
    lastBindClickLayer = null;
  }

  // 处理图层构件树控件
  if (layer.options.scenetree) {
    layer.on("click", onClickBimLayer);
    lastBindClickLayer = layer;
  }
}

let lastBindClickLayer;

function onClickBimLayer(event: any) {
  const layer = event.layer;
  const url = layer.options.url;
  const id = layer.id;
  activate({
    name: "layer-tree",
    data: { url, id },
  });
}

// watch(baseIds, async (val) => {
//   removeLayer();
//   console.log(baseIds);
//   await layerStoreIns.setBaseSelect(baseIds.value);

//   let options = {
//     layers: layerStoreIns.layers,
//   };

//   console.log("options", options);
//   window.map.setOptions(options);

//   treeData.value = [];
//   expandedKeys.value = [];
//   checkedKeys.value = [];

//   initTree();
// });

function removeLayer() {
  let layers = mapWork.getLayersOnly() || [];
  layers.forEach((layer) => {
    const item = layer.options;
    if (item._hasMapInit) {
      window.map.removeLayer(layer, true);
    }
  });
}
</script>

<style lang="scss">
.map-tab {
  .el-tabs__item {
    color: #ffffff;
  }
  .el-tabs__nav-wrap:after {
    height: 1px;
    background-color: rgba(255, 255, 255, 0.4);
  }
}
.manage-layer_pannel {
  .mars-dialog__content {
    overflow-x: hidden !important;
  }
}

.layer-tree {
  height: 600px;
  .ant-tree-treenode-checkbox-checked {
    .ant-tree-node-content-wrapper {
      width: calc(100% - 55px);
      .ant-tree-title {
        display: inline-flex;
        width: calc(100% - 30px);
        align-items: center;
        justify-content: space-between;
      }
    }
  }
}
.manage-layer_pannel {
  .select-with-layer {
    .el-select__wrapper {
      background-color: rgba(#000000, 0.5);
      border-radius: 0;
      box-shadow: 0 0 0 1px rgba(255, 255, 255, 0.3) inset;
    }
  }
}

.input-with-layer {
  .el-input__wrapper {
    background-color: rgba(#000000, 0.5);
    border: none;
    box-shadow: none;
    .el-input__inner {
      height: 40px;
      color: #ffffff;
    }
  }

  .el-input-group__append,
  .el-input-group__prepend {
    background-color: transparent;
    border: none;
    box-shadow: none;
    background-color: rgba(#000000, 0.5);
    .el-button {
      background-color: #42be93;
      border-radius: 4px;
      opacity: 1;
      width: 32px;
      height: 32px;
      display: flex;
      align-items: center;
      justify-content: center;
      .el-icon {
        color: #ffffff;
        font-size: 14px;
      }
    }
    .el-button:hover {
      background-color: rgba(66, 190, 147, 0.5);
    }
  }
}
</style>

<style scoped lang="scss">
.line-wellCode {
  transition: background 0.2s;
  cursor: pointer;
  padding: 4px 6px;
}
.line-wellCode:hover {
  background: rgba(33, 241, 194, 0.3);
}
.title {
  width: 50%;
  display: inline-flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  font-size: 16px;
  font-family: var(--mars-font-family);
}

.tree-slider {
  display: inline-block;
  width: 100px;
  margin-left: 5px;
  margin-right: 5px;
  vertical-align: middle;
}

.tips {
  width: 100%;
  text-align: center;
  margin-top: 10px;
  color: #ffffff;
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 14px;
  font-family: MiSans;
  opacity: 0.5;
}
</style>
