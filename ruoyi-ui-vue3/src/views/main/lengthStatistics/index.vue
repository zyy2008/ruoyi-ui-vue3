<template>
  <div class="lengthStatistics-div">
    <div class="top">
      <el-form ref="crossSectionRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="统计方式" prop="line">
          <el-radio-group v-model="form.radioType" size="small">
            <el-radio @change="changeType" value="1" border>屏幕范围</el-radio>
            <el-radio @change="changeType" value="2" border>手动绘制</el-radio>
            <el-radio @change="changeType" value="3" border>全库统计</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <el-tag type="warning">总长度{{ sumLeng }}（米）</el-tag>
      <el-button-group>
        <el-button type="primary" @click="handleQuery">开始统计</el-button>
        <el-button type="danger" @click="handleClear">清空结果</el-button>
      </el-button-group>
    </div>
    <div v-show="showRes" class="lengthStatistics-main">
      <el-table height="700" :data="tableDataRef" :span-method="objectSpanMethod" border>
        <el-table-column width="90" prop="mainType" label="管线类型" />
        <el-table-column width="170" prop="subType" label="分类" />
        <el-table-column prop="length" label="长度(米)" />
        <el-table-column prop="sum" label="总长度(米)" />
      </el-table>

      <div class="chart">
        <div id="barChartRef" style="height: 350px; width: 900px"></div>
        <div id="pieChartRef" style="height: 350px; width: 900px"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, reactive, toRefs } from "vue";
import layerStore from "@/store/modules/layer";
import { ElMessage } from "element-plus";
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map.js";

import { init_bar, init_pie } from "./chart.js";

const data = reactive({
  form: {
    radioType: "1",
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    roleName: undefined,
    roleKey: undefined,
    status: undefined,
  },
  rules: {
    roleName: [{ required: true, message: "角色名称不能为空", trigger: "blur" }],
    roleKey: [{ required: true, message: "权限字符不能为空", trigger: "blur" }],
    roleSort: [{ required: true, message: "角色顺序不能为空", trigger: "blur" }],
  },
});
const { queryParams, form, rules } = toRefs(data);

const tableDataRef = ref([
  {
    mainType: "给水",
    subType: "地下生产给水",
    length: "2485.17",
    sum: "18621.42",
  },
  {
    mainType: "给水",
    subType: "地下生活给水",
    length: "2485.17",
    sum: "18621.42",
  },
  {
    mainType: "排水",
    subType: "地下雨排水",
    length: "2485.17",
    sum: "18621.42",
  },
]);
let sumLeng = ref(0);
let showRes = ref(false);

const objectSpanMethod = ({ row, column, rowIndex, columnIndex }) => {
  let tableData = tableDataRef.value;
  if (columnIndex === 0) {
    // 合并管线类型列
    const prevRow = tableData[rowIndex - 1];
    if (rowIndex > 0 && prevRow && row.mainType === prevRow.mainType) {
      return {
        rowspan: 0,
        colspan: 0,
      };
    }

    let rowspan = 1;
    let colspan = 1;

    // 计算管线类型列的 rowspan
    for (let i = rowIndex + 1; i < tableData.length; i++) {
      if (tableData[i].mainType === row.mainType) {
        rowspan++;
      } else {
        break;
      }
    }

    return {
      rowspan: rowspan,
      colspan: colspan,
    };
  }

  if (columnIndex === 3) {
    // 合并管线类型列
    const prevRow = tableData[rowIndex - 1];
    if (rowIndex > 0 && prevRow && row.mainType === prevRow.mainType) {
      return {
        rowspan: 0,
        colspan: 0,
      };
    }

    let rowspan = 1;
    let colspan = 1;

    // 计算管线类型列的 rowspan
    for (let i = rowIndex + 1; i < tableData.length; i++) {
      if (tableData[i].mainType === row.mainType) {
        rowspan++;
      } else {
        break;
      }
    }

    return {
      rowspan: rowspan,
      colspan: colspan,
    };
  }

  return {
    rowspan: 1,
    colspan: 1,
  };
};

onMounted(() => {
  init_bar("barChartRef", tableDataRef.value);
  init_pie("pieChartRef", tableDataRef.value);
});

// 启用map.ts生命周期
useLifecycle(mapWork);

function changeType() {
  mapWork.StopDraw(); //关闭编辑
  if (form.value.radioType == "2") {
    mapWork.drawAll("polygon");
  }
  // if (form.value.radioType == "3") {
  //   selectLine();
  //   //mapWork.drawAll("polyline");
  // }
}

function handleClear() {
  showRes.value = false;
  tableDataRef.value = [];
  mapWork.clearLayer();
  sumLeng.value = 0;
}

async function handleQuery() {
  let lengthStatistics = layerStore().lengthStatistics;

  if (!lengthStatistics || Object.keys(lengthStatistics).length < 1) {
    ElMessage({
      message: "目标图层未加载，请在后台配置对应图层",
      type: "warning",
    });
    return;
  }

  if (form.value.radioType == "2") {
    let g = mapWork.getGraphic();

    if (!g) {
      ElMessage({
        message: "请先在地图上绘制目标范围",
        type: "warning",
      });
      return;
    }
  }

  let lengthStatisticsMap = await mapWork.queryAll(
    lengthStatistics,
    form.value.radioType
  );
  tableDataRef.value = createTableData(lengthStatisticsMap);
  init_bar("barChartRef", tableDataRef.value);
  init_pie("pieChartRef", tableDataRef.value);
  showRes.value = true;
}

function createTableData(layerObj) {
  let tableDataRes = [];
  let tableList = [];
  sumLeng.value = 0;
  for (const key in layerObj) {
    if (layerObj.hasOwnProperty(key)) {
      // 检查属性是否是对象自身的属性
      let mainType = key;
      layerObj[key].queryRes = layerObj[key].queryRes || {};
      let features = layerObj[key].queryRes.features || []; //子类
      let mainTypeSum = 0;
      //分组
      const groupedSums = features.reduce((acc, item) => {
        let groupeFiled = item.properties.classify;
        // 检查累加器对象中是否已有该类别
        if (!acc[groupeFiled]) {
          acc[groupeFiled] = {
            mainType: mainType,
            subType: groupeFiled,
            length: 0,
            sum: 0,
          };
        }
        // 累加当前类别的值
        acc[groupeFiled].length += Number(item.properties.shape_leng);
        mainTypeSum += Number(item.properties.shape_leng);
        sumLeng.value += Number(item.properties.shape_leng);
        return acc;
      }, {});

      // 将累加器对象转换为数组                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
      const result = Object.values(groupedSums);
      if (result && result.length > 0) {
        result[0].sum = mainTypeSum;
        tableList.push(result);
      }
      // console.log("result", result);
    }
  }

  for (let i = 0; i < tableList.length; i++) {
    for (let j = 0; j < tableList[i].length; j++) {
      tableList[i][j].length = tableList[i][j].length.toFixed(2);
      tableList[i][j].sum = Number(tableList[i][0].sum).toFixed(2);
      tableDataRes.push(tableList[i][j]);
    }
  }

  sumLeng.value = sumLeng.value.toFixed(2);
  return tableDataRes;
}

function selectLine() {
  let layer = mapWork.map.getLayer("工业管道_线", "name");

  if (!layer || !layer.show) {
    ElMessage({
      message: "图层未加载，请在图层面板中加载【工业管道_线】图层",
      type: "warning",
    });
    return;
  }

  console.log(layer);

  layer.bindPopup("all");
  layer.bindHighlight({
    type: "polylineP",
    width: 10,
    clampToGround: true,
    materialType: "PolylineGlow",
    materialOptions: {
      color: "#ff0000",
      glowPower: 0.1, // 发光强度
    },
  });

  layer.on(mars3d.EventType.click, function (e) {
    console.log(e);
    if (e && e.features && e.features.length) {
    }
  });
}

function cancel() {
  let layer = window.map.getLayer("工业管道_线", "name");
  if (layer) {
    layer.closeHighlight();
    layer.closePopup();
  }
}
</script>

<style lang="scss">
.lengthStatistics-div {
  padding-top: 20px;
  .el-form-item__label,
  .el-radio__label {
    color: #fff;
  }
  .top {
    display: flex;
    justify-content: space-between;
  }
  .lengthStatistics-main {
    display: flex;
  }
  .chart {
    margin-left: 20px;

    #pieChartRef {
      border-top: 1px solid #c3c3c3;
      padding-top: 20px;
      margin-top: 20px;
    }
  }
}
</style>
