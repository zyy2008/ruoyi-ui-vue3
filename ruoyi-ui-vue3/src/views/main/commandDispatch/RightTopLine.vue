<template>
  <div class="table">
    <div class="title">
      <div class="image">
        <img src="@/assets/static/subTitle.png" alt="" />
        <span> {{ chartTitle.wellCode + "(" + chartTitle.location + ")" }} </span>
      </div>
      <el-button type="text" class="text" @click="openChartLine">更多</el-button>
    </div>
    <div class="selectRadio">
      <el-select
        v-model="selectValue"
        placeholder="请选择"
        style="width: 150px; margin-left: 55px"
        @change="seekLineData"
      >
        <el-option
          v-for="item in indicatorsOption"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-radio-group v-model="xAxis" class="radio" @change="changeChart">
        <el-radio-button label="周"></el-radio-button>
        <el-radio-button label="月"></el-radio-button>
      </el-radio-group>
    </div>
    <div id="echart"></div>
    <ChartLineModal ref="chartLine" :chartTitle="chartTitle" />
  </div>
</template>
<script setup>
import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
import * as echarts from "echarts";
import ChartLineModal from "./ChartLineModal.vue";
import { listMonitoringAmlist } from "@/api/admin/monitoring";
import { listWells } from "@/api/admin/wells";
import { listIndicators } from "@/api/admin/indicators";
const chartLine = ref();
const xAxis = ref("周");
let lineChart = null;
let lineOption = {};
const selectValue = ref("pH");
let dataList = [];
const indicatorsOption = ref([]);
const chartTitle = reactive({
  wellCode: "",
  location: "",
});

// 获取检测井数据
onMounted(() => {
  listWells({ pageNum: 1, pageSize: 1000 }).then((response) => {
    if (response.code === 200) {
      const data = response.rows;
      chartTitle.wellCode = data[0].wellCode;
      chartTitle.location = data[0].location;
      listMonitoringList({ pointId: data[0].wellCode }, "ph");
    }
  });
  // 下拉指标切换
  listIndicators({ pageNum: 1, pageSize: 2000 }).then((response) => {
    if (response.code === 200) {
      response.rows.forEach((ele) => {
        if (ele.attribute != "pointId" && ele.attribute != "sampleTime") {
          indicatorsOption.value.push({
            value: ele.attribute,
            label: ele.type,
          });
        }
      });
    }
  });
});

// 调用接口
function listMonitoringList(option, type) {
  listMonitoringAmlist(option).then((res) => {
    const data = res.rows;
    dataList = data.sort((a, b) => {
      return Number(a.sampleTime) - Number(b.sampleTime);
    });

    chartLine.value.tableData = dataList;
    // chartLine.value.tableInfo = dataList.reverse()
    seekLineData(type);
  });
}

// 调用接口
function seekLineData(type) {
  lineOption.xAxis[0].data = [];
  lineOption.series[0].data = [];
  dataList.forEach((element, index) => {
    lineOption.series[0].data.push(element[type]);
    lineOption.xAxis[0].data.push(element.sampleTime);
  });
  lineChart.setOption(lineOption);
}

// 打开更多弹窗
function openChartLine() {
  seekLineData("ph");
  chartLine.value.dialogVisible = true;
  const elements = document.getElementsByClassName("RightLine");
  elements[0].style.zIndex = 100;
  const elements1 = document.getElementsByClassName("mars3d-divGraphic ");
  if (elements1) {
    elements1[0].style.display = "none";
  }
}

// 点击地图详情打开折线图弹窗
window.openJCPageVue = function (data) {
  let attr = data.attr;
  nextTick(() => {
    chartLine.value.dialogVisible = true;
  });
};

// 点击地图切换右侧折线图
window.openLineChartPage = function (data) {
  nextTick(() => {
    chartTitle.wellCode = data.attr.wellCode;
    chartTitle.location = data.attr.location;
    // seekLineData('ph');
    listMonitoringList({ pointId: data.attr.wellCode }, "ph");
    selectValue.value = "pH";
  });
};

nextTick(() => {
  initEchart();
});

function initEchart() {
  var chartDom = document.getElementById("echart");
  lineChart = echarts.init(chartDom);
  var fontColor = "#30eee9";
  lineOption = {
    grid: {
      left: "5%",
      right: "5%",
      top: "2%",
      bottom: "5%",
      containLabel: true,
    },
    tooltip: {
      show: true,
      trigger: "item",
    },
    legend: {
      show: false,
      x: "center",
      y: "0",
      icon: "stack",
      itemWidth: 10,
      itemHeight: 10,
      textStyle: {
        color: "#1bb4f6",
      },
      data: [],
    },
    xAxis: [
      {
        type: "category",
        boundaryGap: false,
        axisLabel: {
          color: fontColor,
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: "#397cbc",
          },
        },
        axisTick: {
          show: false,
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: "#195384",
          },
        },
        data: [],
      },
    ],
    yAxis: [
      {
        type: "value",
        name: "值",
        min: 0,
        axisLabel: {
          formatter: "{value}",
          textStyle: {
            color: "#2ad1d2",
          },
        },
        axisLine: {
          lineStyle: {
            color: "#27b4c2",
          },
        },
        axisTick: {
          show: false,
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: "#11366e",
          },
        },
      },
    ],
    series: [
      {
        name: "pH",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,

        itemStyle: {
          normal: {
            color: "#00d4c7",
            lineStyle: {
              color: "#00d4c7",
              width: 1,
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(0,212,199,0.9)",
                },
              ]),
            },
          },
        },
        data: [],
      },
    ],
  };
  lineOption && lineChart.setOption(lineOption);
}
//切换周和月
const changeChart = () => {
  // if (xAxis.value === '月') {
  //   lineOption.xAxis[0].data = ['10-01', '10-05', '10-10', '10-15', '10-20', '10-25', '10-30']
  // } else {
  //   lineOption.xAxis[0].data = ['周一', '周二', '周三', '周四', '周五', '周六', '周天']
  // }
  // lineChart.setOption(lineOption);
};
</script>

<style lang="scss" scoped>
.table {
  width: 604px;
  padding: 10px;
  background: url("@/assets/static/left.png") no-repeat;
  background-color: rgba(9, 21, 42, 0.8);

  .title {
    height: 42px;
    line-height: 42px;
    display: flex;
    justify-content: space-between;

    .image {
      width: 100%;
      display: flex;
    }

    span {
      font-size: 18px;
      display: inline-block;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .text {
      display: flex;
      align-items: center;
    }
  }

  .selectRadio {
    display: flex;
    justify-content: space-between;
  }

  #echart {
    width: 100%;
    margin-top: 5px;
    height: calc(100% - 60px);
  }
}

:deep() .el-radio-button__original-radio:checked + .el-radio-button__inner {
  background-color: rgba(9, 21, 42, 0.8);
  border-color: rgba(9, 21, 42, 0.8);
}
</style>
