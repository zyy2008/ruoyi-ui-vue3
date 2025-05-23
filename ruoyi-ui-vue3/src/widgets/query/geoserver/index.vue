<template>
  <mars-dialog draggable title="条件查询" left="0" bottom="10" width="100%">
    <a-form>
      <div class="search-div">
        <a-form-item label="图层">
          <el-cascader
            ref="cascaderRef"
            @change="changeline"
            v-model="queryParams.layer"
            :options="lineOptions"
            :props="{
              expandTrigger: 'hover',
              value: 'id',
              label: 'title',
            }"
          />
        </a-form-item>
        <a-form-item label="管径">
          <mars-slider
            v-model:value="queryParams.standard1"
            range
            :min="0"
            :max="9000"
            :step="2"
          />
        </a-form-item>
        <a-form-item label="材质">
          <mars-input
            class="inputServe"
            v-model:value="queryParams.matter"
            placeholder="请输入查询关键字"
          ></mars-input>
        </a-form-item>
        <a-form-item label="埋深">
          <mars-slider
            v-model:value="queryParams.startdep_1"
            range
            :min="0"
            :max="40"
            :step="0.2"
          />
        </a-form-item>
        <a-form-item label="附属物">
          <mars-input
            class="inputServe"
            v-model:value="queryParams.coverstsle"
            placeholder="请输入查询关键字"
          ></mars-input>
        </a-form-item>
        <a-form-item label="高级查询">
          <mars-input
            class="inputServe"
            v-model:value="queryParams.cql"
            placeholder="示例: name = '名称' "
          ></mars-input>
        </a-form-item>

        <a-form-item label="范围">
          <div class="range-select">
            <a-space>
              <mars-button @click="drawRectangle">框选范围</mars-button>
              <mars-button @click="drawCircle">圆形范围</mars-button>
              <mars-button class="long-btn" @click="drawPolygon">多边形范围</mars-button>
            </a-space>
          </div>
        </a-form-item>
      </div>
      <div class="query">
        <a-space>
          <mars-button @click="query">查询</mars-button>
          <mars-button @click="removeAll" danger>清除</mars-button>
        </a-space>
      </div>

      <div v-show="showTable" class="f-pt">
        <a-form-item>
          <mars-table
            :pagination="true"
            :dataSource="dataSource"
            :columns="columns"
            :custom-row="customRow"
            size="small"
            bordered
            :scroll="{ y: 400 }"
          >
            <template #bodyCell="{ column ,record }">
              <template v-if="column.dataIndex === 'XQ'">
                <a @click="showDetail(record)" class="a-btn">详情</a>
              </template>
            </template>
          </mars-table>
        </a-form-item>
      </div>
    </a-form>
  </mars-dialog>
</template>

<script setup lang="ts">
import { onUnmounted, computed, ref, toRaw, reactive, toRefs } from "vue";
import * as mapWork from "./map.js";
import { $message } from "@mars/components/mars-ui/index";
import layerStore from "@/store/modules/layer";
const cascaderRef = ref(null);
interface DataItem {
  key: number;
  name: string;
  age: number;
  address: string;
  graphic: any;
}

let nodeCache: any;
let layerCache: any;

function showDetail(row:any){
  console.log(row);
  if(row.graphic && row.graphic.attr){
    window.showLayerAttribute(toRaw(row.graphic))
  }
}

function changeline(val: any) {
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
function addLayer(layer) {
  map.addLayer(layer);
}

//查询
function buildCQLQuery(queryParams) {
  const conditions = [];

  // 检查 classify 是否存在并添加到条件
  if (queryParams.classify) {
    conditions.push(`classify LIKE '%${queryParams.classify}%'`);
  }

  // 检查 matter 是否存在并添加到条件
  if (queryParams.matter) {
    conditions.push(`matter LIKE '%${queryParams.matter}%'`);
  }

  // 检查管径条件
  if (queryParams.standard1 && queryParams.standard1.length) {
    if (queryParams.standard1[0] > 0 || queryParams.standard1[1] < 90000) {
      conditions.push(
        `standard1 >= ${queryParams.standard1[0]} AND standard1 <= ${queryParams.standard1[1]}`
      );
    }
  }

  // 检查埋深条件-2  -1
  if (queryParams.startdep_1 && queryParams.startdep_1.length) {
    conditions.push(
      `startdepth >= ${queryParams.startdep_1[0]} AND enddepth <= ${queryParams.startdep_1[1]}`
    );
  }

  // 附属物的条件可以在这里添加，假设有一个属性叫附属物
  if (queryParams.accessory) {
    conditions.push(`accessory LIKE '%${queryParams.accessory}%'`);
  }

  if (queryParams.cql) {
    conditions.push(`${queryParams.cql}`);
  }

  // 生成 CQL 查询字符串
  const cqlQuery = conditions.length > 0 ? conditions.join(" AND ") : "";

  return cqlQuery;
}

//可按管径、材质、埋深、附属物查询符合条件的管线
const data = reactive({
  form: {},
  queryParams: {
    standard1: [0, 9000],
    startdep_1: [0, 40],
    classify: undefined, //地物名称
    matter: undefined, //材质
    coverstsle: undefined, //附属物
    layer: undefined,
  },
  rules: {},
});

const { queryParams, form, rules } = toRefs(data);

const serverName = ref("");
const showTable = computed(() => dataSource.value.length > 0);
const lineOptions = computed(() => {
  if (layerStore().treeData && layerStore().treeData.length > 0) {
    return layerStore().treeData[0].children;
  }
  return [];
});

// 表格数据
const dataSource = ref([]);
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
// 启用map.ts生命周期
useLifecycle(mapWork);

mapWork.eventTarget.on("befortUI", function (event: any) {
  dataSource.value = [];
  event.list.forEach((item: any, index: number) => {
    dataSource.value.push(item);
    // dataSource.value.push({
    //   key: index,
    //   name: item["name"],
    //   type: item["classify"],
    //   address: item["mark"],
    //   graphic: item.graphic,
    // });
  });
});

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
    title: "管径标准",
    dataIndex: "standard",
    key: "standard",
  },
  {
    title: "材质",
    dataIndex: "matter",
    key: "matter",
  },
  {
    title: "开始深度1",
    dataIndex: "startdep_1",
    key: "startdep_1",
  },
  {
    title: "结束深度GC",
    dataIndex: "enddepthgc",
    key: "enddepthgc",
  },
  {
    title: "操作",
    dataIndex: "XQ",
    key: "XQ",
  },
]);

const customRow = (record: DataItem) => {
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

// 绘制范围
const drawRectangle = () => {
  mapWork.drawRectangle();
};

const drawCircle = () => {
  mapWork.drawCircle();
};

const drawPolygon = () => {
  mapWork.drawPolygon();
};

// 查询数据
const query = () => {
  if (!nodeCache) {
    globalMsg("请先选择目标图层！");
    return;
  }
  let cql = buildCQLQuery(queryParams.value);
  if("standard1 >= 0 AND standard1 <= 9000 AND startdepth >= 0 AND enddepth <= 40"==cql){
    cql=" 1=1 "
  }
  console.log(cql);
  dataSource.value = [];
  mapWork.clearAll(true);
  mapWork.query(cql, nodeCache);
};

onUnmounted(()=>{
  removeAll()
})

// 清除数据
const removeAll = () => {
  if (layerCache) {
    layerCache.show = false;
  }
  dataSource.value = [];
  mapWork.clearAll();
};
</script>
<style lang="scss">
.search-div {
  .el-input__wrapper{
    background-color: rgba(35, 39, 47, 0.7);
    border-color:rgba(64, 150, 255, 1);
    border: none;
    box-shadow:none;
  }
}
.a-btn{
    color: #42BE93;
  }
</style>
<style scoped lang="scss">
.search-div {
  display: flex;
  justify-content: space-around;
}
.inputServe {
  width: 256px;
}

.range-select {
  .mars-button {
    width: 80px;
  }

  .long-btn {
    padding-left: 5px;
  }
}

.query {
  .mars-button {
    width: 146px;
  }
}

:deep(.ant-table-row:nth-of-type(even)) {
  background-color: transparent !important;
}
</style>
