<template>
  <mars-dialog draggable title="选址辅助" left="380" top="100" width="550">
    <div class="f-mb buffer-div">
      <a-space style="margin-bottom: 10px">
        <span class="mars-pannel-item-label">目标图层:</span>
        <el-cascader
          style="width: 226px"
          ref="cascaderRef"
          @change="changeline"
          v-model="queryLayer"
          :options="lineOptions"
          :props="{
            expandTrigger: 'hover',
            value: 'id',
            label: 'title',
          }"
        />
      </a-space>
      <a-space  style="margin-bottom: 10px">
        <span class="mars-pannel-item-label">查询半径:</span>
        <mars-input-number
          class="radius"
          @change="radiusChange"
          v-model:value="radiusVal"
          :min="1"
          :step="1"
          :max="99999"
          addon-after="米"
        ></mars-input-number>
      </a-space>

      <a-space>
        <span class="mars-pannel-item-label">绘制范围:</span>
        <mars-button class="draw_btn" @click="drawPoint">点</mars-button>
        <mars-button class="draw_btn" @click="drawPolyline">线</mars-button>
        <mars-button class="draw_btn" @click="drawPolygon">面</mars-button>
      </a-space>
    </div>

    <div class="query">
      <a-space>
        <mars-button @click="query">查询</mars-button>
        <mars-button @click="clearAll" danger>清除</mars-button>
      </a-space>
    </div>

    <div v-show="showTable" class="f-pt">
      <mars-table
        :pagination="true"
        :dataSource="dataSource"
        :columns="columns"
        :custom-row="customRow"
        size="small"
        bordered
        :scroll="{ y: 400 }"
      >
        <template #bodyCell="{ column, record }">
          <template v-if="column.dataIndex === 'XQ'">
            <a @click="showDetail(record)" class="a-btn">详情</a>
          </template>
        </template>
      </mars-table>
    </div>
  </mars-dialog>
</template>

<script setup>
import { onUnmounted, ref } from "vue";
import * as mapWork from "./map.js";
import { $message } from "@mars/components/mars-ui/index";
import layerStore from "@/store/modules/layer";
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
// 启用map.ts生命周期
useLifecycle(mapWork);
const cascaderRef = ref(null);
const radiusVal = ref(50);
const queryLayer = ref("");
// 表格数据
const dataSource = ref([]);
const columns = ref([
  {
    title: "分类",
    dataIndex: "classify",
    key: "classify",
  },
  {
    title: "地理对象编号",
    dataIndex: "geoobjnum",
    key: "geoobjnum",
  },
  {
    title: "名称",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "操作",
    dataIndex: "XQ",
    key: "XQ",
  },
]);
const showTable = computed(() => dataSource.value.length > 0);

mapWork.eventTarget.on("befortUI", function (event) {
  dataSource.value = [];
  event.list.forEach((item, index) => {
    dataSource.value.push(item);
  });
});

function showDetail(row) {
  console.log(row);
  if (row.graphic && row.graphic.attr) {
    window.showLayerAttribute(toRaw(row.graphic));
  }
}

const customRow = (record) => {
  return {
    onClick: () => {
      if (record.graphic == null) {
        $message(record.name + " 无经纬度坐标信息！");
        return;
      }
      mapWork.flyToGraphic(toRaw(record.graphic));
    },
  };
};

const lineOptions = computed(() => {
  if (layerStore().treeData && layerStore().treeData.length > 0) {
    return layerStore().treeData[0].children;
  }
  return [];
});

let nodeCache;
let layerCache;

function changeline(val) {
  if (cascaderRef.value) {
    const checkedNodes = cascaderRef.value.getCheckedNodes();
    console.log("Checked Nodes:", checkedNodes);
    if (checkedNodes && checkedNodes.length > 0) {
      nodeCache = checkedNodes[0].data;
      activeLayer();
    }
  }
}

function activeLayer() {
  if (layerCache) {
    layerCache.show = false;
  }
  let layer = map.getLayer(nodeCache.title, "name");
  if (!layer) {
    $message("图层未加载，请在图层面板中加载【" + nodeCache.title + "】图层");
    return;
  }
  layerCache = layer;
  layer.show = true;
  if (!layer.isAdded) {
    addLayer(layer); //==
  }

  if (!layer.options.noCenter) {
    // 在对应config.json图层节点配置 noCenter:true 可以不定位
    layer.readyPromise.then(function (layer) {
      layer.flyTo({ scale: 2 });
    });
  }
}

// 查询数据
const query = () => {
  if (!nodeCache) {
    globalMsg("请先选择目标图层！");
    return;
  }
  dataSource.value = [];
  mapWork.query("1=1", nodeCache);
};
function addLayer(layer) {
  map.addLayer(layer);
}

// 点
const drawPoint = () => {
  mapWork.drawPoint();
};
// 线
const drawPolyline = () => {
  mapWork.drawPolyline();
};

// 面
const drawPolygon = () => {
  mapWork.drawPolygon();
};

const radiusChange = () => {
  mapWork.radiusChange(radiusVal.value);
};

radiusChange();

onUnmounted(() => {
  clearAll();
});
// 清除
const clearAll = () => {
  if (layerCache) {
    layerCache.show = false;
  }
  dataSource.value = [];
};
</script>
<style scoped lang="scss">
.mars-pannel-item-label {
  width: 55px;
}
.ant-input-number {
  width: 60px !important;
}

.draw_btn {
  width: 68px;
}

.query {
  text-align: center;
  margin-top: 10px;
  .mars-button {
    width: 146px;
  }
}
</style>

<style lang="scss">
.buffer-div {
  text-align: center;
  .el-input__wrapper {
    background-color: rgba(35, 39, 47, 0.7);
    border-color: rgba(64, 150, 255, 1);
    border: none;
    box-shadow: none;
    width: 220px;
  }
}

.a-btn {
  color: #42be93;
}
</style>
