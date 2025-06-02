<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> 实时数据 </span>
      <!-- <el-button @click="enterpriseApi"> 测试数据</el-button> -->
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="650"
      :row-class-name="tableRowClassName"
      @row-click="clickTable"
    >
      <el-table-column type="index" width="50" />
      <el-table-column prop="wellCode" label="名称" width="60" />
      <el-table-column prop="ph" label="PH" width="60" />
      <el-table-column prop="WD" label="温度" width="60" />
      <el-table-column prop="waterBuriedDepth" label="水位" width="60" />
      <el-table-column prop="DW" label="电位" />
      <el-table-column prop="RJY" label="溶解氧" width="70" />
      <el-table-column prop="DDL" label="电导率" width="70" />
      <el-table-column prop="AD" label="氨氮" width="60" />
      <el-table-column prop="lng" label="位置" width="60" />
    </el-table>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from "vue";
import * as mars3d from "mars3d";
import {
  DataType,
  Authority,
  projectApi,
  DataMethod,
  DataParams,
  ApiData,
} from "@/api/platform/index";
import {
  listWells,
  getWells,
  delWells,
  addWells,
  updateWells,
} from "@/api/admin/wells";
import {
  listMonitoring,
  getMonitoring,
  delMonitoring,
  addMonitoring,
  updateMonitoring,
} from "@/api/admin/monitoring";
import { param } from "@mars/utils";
const tableData = ref();
const tableTotle = ref();
const selectedRow = ref(null);

const tableRowClassName = ({ row }) => {
  return row === selectedRow.value ? "selected-row" : "";
};

const enterpriseApi = async () => {
  const {} = await projectApi({
    apiData: {
      dataType: "pollutantSource" as DataType,
      dataMethod: "add" as DataMethod,
      params: { id: 0 },
      authority: "admin",
    },
  });
};

onMounted(() => {
  createBaseList({ pageNum: 1, pageSize: 1000 });
});
const createBaseList = (option) => {
  let data = {};
  listWells(option).then((response) => {
    data = response.rows;
    tableTotle.value = response.total;
    listMonitoring(option).then((res) => {
      for (let i in data) {
        res.rows.forEach((ele) => {
          if (ele.pointId === data[i].wellCode) {
            data[i]["ph"] = ele.ph;
          }
        });
      }
      tableData.value = data;
      if (data.length > 0) {
        selectedRow.value = data[0];
        emit("changeTableLine", data[0]);
      }
    });
  });
};
const emit = defineEmits(["changeTableLine"]);
const clickTable = (value) => {
  selectedRow.value = value;
  emit("changeTableLine", value);

  console.log(value);

  if (map && value.longitude && value.latitude) {

    map.setCameraView({
      lng:Number(value.longitude),
      lat:Number(value.latitude),
      alt:1200
    })
  }
};
</script>

<style lang="scss" scoped>
.table {
  width: 604px;
  padding: 10px;
  background: url("@/assets/static/left.png") no-repeat;

  :deep(.selected-row) {
    background-color: #409eff !important;
    color: #fff;
  }

  .title {
    height: 42px;
    line-height: 42px;
    display: flex;

    span {
      font-size: 18px;
      display: inline-block;
    }
  }
}
</style>