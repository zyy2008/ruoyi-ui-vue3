<template>
  <div v-loading="loading" class="TileManage">
    <el-input v-model="filterText" style="width: 100%" placeholder="快速搜索" />
    <el-tree
      ref="treeRef"
      :data="treeData"
      show-checkbox
      node-key="id"
      :default-expanded-keys="expandedKeys"
      :default-checked-keys="checkedKeys"
      :props="defaultProps"
      :filter-node-method="filterNode"
      @check-change="checkChange"
    />
  </div>
</template>
<script  setup>
import { ref, watch, onMounted, reactive, toRefs, getCurrentInstance } from "vue";
import { listLayer, getLayer, delLayer, addLayer, updateLayer } from "@/api/admin/layer";
const defaultProps = {
  children: "children",
  label: "name",
};
import { ElTree } from "element-plus";
import * as mars3d from "mars3d";

import layerStore from "@/store/modules/layer";

const layerStoreIns = layerStore();

const filterText = ref("");
const treeRef = ref<any>("");

watch(filterText, (val) => {
  //treeRef.value!.filter(val);
});
const filterNode = (value, data) => {
  if (!value) return true;
  return data.name.includes(value);
};

const treeData = layerStoreIns.treeData;

const expandedKeys =layerStoreIns.expandedKeys;

const checkedKeys = layerStoreIns.checkedKeys;

const layersObj = {};
const loading = ref(true);
const dataQuery = reactive({
  form: {},
  queryParams: {
    name: null,
    url: null,
    serverType: null,
    pid: null,
    isShow: null,
    dataType: null,
  },
  rules: {
    name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(dataQuery);
const { proxy } = getCurrentInstance();
onMounted(() => {
  //initTree();
});

function addTileLayer(url, layers,flyTo) {
  // 方式2：在创建地球后调用addLayer添加图层(直接new对应type类型的图层类)
  let tileLayer = new mars3d.layer.WmsLayer({
    url: url,
    layers: layers || "ne:t_jzw_m_84_gbk2312",
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
    // popupOptions: {
    //   autoClose: false,
    //   closeOnClick: false,
    //   checkData: function (attr, graphic) {
    //     if (Cesium.defined(attr.OBJECTID)) {
    //       return graphic.attr.OBJECTID === attr.OBJECTID
    //     }
    //     if (Cesium.defined(attr.NAME)) {
    //       return graphic.attr.NAME === attr.NAME
    //     }
    //     return false
    //   }
    // },
    flyTo: flyTo
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

/** 查询图层列表 */
function getList() {
  loading.value = true;
  listLayer(queryParams.value).then((response) => {
    treeData.value = proxy.handleTree(response.data, "id", "pid");

    console.log(treeData.value);
    setCheck(response.data);
    loading.value = false;
  });
}

function setCheck(list) {
  list.forEach((node) => {
    if (node && node.url && node.isShow) {
      let layer = addTileLayer(node.url, null,true);
      let key = node.name + node.id;
      window.map.addLayer(layer);
      layersObj[key] = layer;
      expandedKeys.value.push(node.id);
      checkedKeys.value.push(node.id);
    }
  });
}

function checkChange(data, node, subNode) {
  console.log(data, node, subNode);
  let key = data.name + data.id;
  let layer = layersObj[key];
  if (data && data.url) {
    //显示
    if (node) {
      if (!layer) {
        layer = addTileLayer(data.url, null);
        window.map.addLayer(layer);
        layersObj[key] = layer;
      }
      if (layer) {
        layer.show = true;
        layer.flyTo();
      }
    } else {
      if (layer) {
        layer.show = false;
      }
    }
  }
}
const checkedChange = (keys, e) => {
  const layer = layersObj[e.node.key];

  if (layer) {
    if (!layer.isAdded) {
      //mapWork.addLayer(layer)
    }

    // 处理子节点
    if (e.node.children && e.node.children.length) {
      renderChildNode(keys, e.node.children);
    }

    if (keys.indexOf(e.node.key) !== -1) {
      layer.show = true;
      layer.flyTo();
    } else {
      layer.show = false;
    }
  }
};

function renderChildNode(keys, children) {
  children.forEach((child) => {
    const layer = layersObj[child.key];
    if (layer) {
      if (!layer.isAdded) {
        //mapWork.addLayer(layer)
      }

      if (keys.indexOf(child.key) !== -1) {
        layer.show = true;
      } else {
        layer.show = false;
      }
      if (child.children) {
        renderChildNode(keys, child.children);
      }
    }
  });
}

// 初始化树构件
const initTree = () => {
  getList();
};

function findChild(parent, list) {
  return list
    .filter((item) => item.pid === parent.id)
    .map((item) => {
      const node = {
        title: item.name,
        key: item.id,
        id: item.id,
        pId: item.pid,
        group: item.type === "group",
      };
      layersObj[item.id] = item;
      expandedKeys.value.push(node.key);

      if (item.hasEmptyGroup || item.hasChildLayer) {
        node.children = findChild(node, list);
      }
      if (item.isAdded && item.show) {
        checkedKeys.value.push(node.key);
      }
      return node;
    });
}
</script>

<style scoped lang="scss">
.TileManage {
  height: 80vh;
}
</style>
