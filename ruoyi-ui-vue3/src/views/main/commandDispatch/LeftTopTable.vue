<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> 实时数据 </span>
      <el-button type="text" class="text" @click="openChartBar">详情</el-button>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="650"
      :row-class-name="tableRowClassName"
      @row-click="clickTable"
    >
      <el-table-column type="index" label="序号" width="60" />
      <el-table-column prop="monitoringWell" label="名称" width="60" />
      <el-table-column prop="monitoringTime" label="时间" width="160" />
      <el-table-column prop="phValue" label="PH" width="60" />
      <el-table-column prop="temperature" label="温度" width="60" />
      <el-table-column prop="waterLevel" label="水位" width="60" />
      <el-table-column prop="dissolvedOxygen" label="溶解氧" width="70" />
      <el-table-column prop="conductivity" label="电导率" width="70" />
      <el-table-column prop="ammoniaNitrogen" label="氨氮" width="60" />
      <el-table-column prop="location" label="地址" width="300" />
    </el-table>
    <ChartBarModal ref="chartBar" />
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
  ApiData,
} from "@/api/platform/index";

import {
  listMonitoring,
  getMonitoring,
  delMonitoring,
  addMonitoring,
  updateMonitoring,
} from "@/api/admin/monitoring";

import { getBatchDataDetail } from "@/api/monitoring";
import { getBatchData } from "@/api/monitoring";
import ChartBarModal from "./ChartBarModal.vue";
import { param } from "@mars/utils";
import emitter from "@/mitt/mitt";

const tableData = ref();
const tableTotle = ref();
const selectedRow = ref(null);
const chartBar = ref();
const tableRowClassName = ({ row }) => {
  return row === selectedRow.value ? "selected-row" : "";
};

const enterpriseApi = async () => {
  const {} = await projectApi({
    apiData: {
      dataType: "pollutantSource",
      dataMethod: "add",
      params: { id: 0 },
      authority: "admin",
    },
  });
};
onMounted(() => {
  getBatchData({}).then((res) => {
    if (res.code === 200) {
      const data = res.data.sort((a, b) => {
        return (
          parseInt(a.monitoringWell.substring(1)) -
          parseInt(b.monitoringWell.substring(1))
        );
      });

      data.forEach((ele) => {
        ele.monitoringTime = ele.monitoringTime
          ? ele.monitoringTime.slice(5, 16)
          : ele.monitoringTime;
      });
      tableData.value = data;

      if (data.length > 0) {
        selectedRow.value = data[0];
        emitter.emit("changeTableLine", data[0]);
      }
    }
  });
});

const clickTable = (ment) => {
  selectedRow.value = ment;
  emitter.emit("changeTableLine", ment);
  if (map && ment.longitude && ment.latitude) {
    map.setCameraView({
      lng: Number(ment.longitude),
      lat: Number(ment.latitude),
      alt: 1200,
    });
  }
};

const openChartBar = () => {
  chartBar.value.dialogVisible = true;
  const elements = document.getElementsByClassName("leftTable");
  elements[0].style.zIndex = 100;
};
</script>

<style lang="scss" scoped>
.table {
  width: 604px;
  padding: 10px;
  background: url("@/assets/static/left.png") no-repeat;
  z-index: -3;

  :deep(.selected-row) {
    background-color: #409eff !important;
    color: #fff;
  }

  .title {
    height: 42px;
    line-height: 42px;
    display: flex;
    align-items: center;

    span {
      font-size: 18px;
      display: inline-block;
    }

    .text {
      margin-left: 250px;
    }
  }
}
</style>
