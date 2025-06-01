<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> {{chart.chartInfo.wellCode}} </span>
      <el-radio-group v-model="radio1" class="radio">
        <el-radio-button label="一周"></el-radio-button>
        <el-radio-button label="一月"></el-radio-button>
      </el-radio-group>
      <el-button type="text" class="text" @click="openChartLine">更多</el-button>
    </div>
    <div id="echart"></div>
    <ChartLineModal ref="chartLine" />
  </div>
</template>
<script setup>
import { onMounted, reactive, toRaw, ref, nextTick } from "vue";
import * as echarts from "echarts";
import ChartLineModal from "./ChartLineModal.vue";
const chartLine = ref();
const radio1 = ref("一周");
const chart=defineProps(['chartInfo'])

function openChartLine() {
  chartLine.value.dialogVisible = true;
}

window.openJCPageVue = function (data) {
  nextTick(() => {
    chartLine.value.dialogVisible = true;
  });
};
nextTick(() => {
  initEchart();
});
function initEchart() {
  var chartDom = document.getElementById("echart");
  var myChart = echarts.init(chartDom);
  var option;

  var fontColor = "#30eee9";
  option = {
    grid: {
      left: "5%",
      right: "5%",
      top: "5%",
      bottom: "5%",
      containLabel: true,
    },
    tooltip: {
      show: true,
      trigger: "item",
    },
    legend: {
      show: true,
      x: "center",
      y: "0",
      icon: "stack",
      itemWidth: 10,
      itemHeight: 10,
      textStyle: {
        color: "#1bb4f6",
      },
      data: ["PH", "温度", "水位", "氧化还原电位", "溶解氧", "电导率", "氨氮"],
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
        data: ["周一", "周二", "周三", "周四", "周五", "周六", "周天"],
      },
    ],
    yAxis: [
      {
        type: "value",
        name: "PH",
        min: 0,
        max: 1000,
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
      {
        type: "value",
        name: "温度",
        min: 0,
        max: 1000,
        axisLabel: {
          formatter: "{value} ",
          textStyle: {
            color: "#186afe",
          },
        },
        axisLine: {
          lineStyle: {
            color: "#186afe",
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
        name: "PH",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,
        itemStyle: {
          normal: {
            color: "#0092f6",
            lineStyle: {
              color: "#0092f6",
              width: 1,
            },
            areaStyle: {
              //color: '#94C9EC'
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(0,146,246,0.9)",
                },
              ]),
            },
          },
        },
        markPoint: {
          itemStyle: {
            normal: {
              color: "red",
            },
          },
        },
        data: [120, 132, 101, 134, 90, 230, 210, 182, 191, 234, 290, 330],
      },
      {
        name: "温度",
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
              //color: '#94C9EC'
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
        data: [220, 182, 191, 234, 290, 330, 310, 201, 154, 190, 330, 410],
      },
      {
        name: "水位",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,
        itemStyle: {
          normal: {
            color: "#aecb56",
            lineStyle: {
              color: "#aecb56",
              width: 1,
            },
            areaStyle: {
              //color: '#94C9EC'
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(114,144,89,0.9)",
                },
              ]),
            },
          },
        },
        data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190],
      },
      {
        name: "氧化还原电位",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,
        itemStyle: {
          normal: {
            color: "#aecb56",
            lineStyle: {
              color: "#aecb56",
              width: 1,
            },
            areaStyle: {
              //color: '#94C9EC'
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(114,144,89,0.9)",
                },
              ]),
            },
          },
        },
        data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190],
      },
      {
        name: "溶解氧",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,
        itemStyle: {
          normal: {
            color: "#aecb56",
            lineStyle: {
              color: "#aecb56",
              width: 1,
            },
            areaStyle: {
              //color: '#94C9EC'
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(114,144,89,0.9)",
                },
              ]),
            },
          },
        },
        data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190],
      },
      {
        name: "电导率",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,
        itemStyle: {
          normal: {
            color: "#aecb56",
            lineStyle: {
              color: "#aecb56",
              width: 1,
            },
            areaStyle: {
              //color: '#94C9EC'
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(114,144,89,0.9)",
                },
              ]),
            },
          },
        },
        data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190],
      },
      {
        name: "氨氮",
        type: "line",
        stack: "总量",
        symbol: "circle",
        symbolSize: 8,
        itemStyle: {
          normal: {
            color: "#aecb56",
            lineStyle: {
              color: "#aecb56",
              width: 1,
            },
            areaStyle: {
              //color: '#94C9EC'
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: "rgba(7,44,90,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(114,144,89,0.9)",
                },
              ]),
            },
          },
        },
        data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190],
      },
    ],
  };

  option && myChart.setOption(option);
  //  data: ['PH', '温度', '水位', '氧化还原电位', '溶解氧', '电导率', '氨氮']
}
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

    span {
      font-size: 18px;
      display: inline-block;
    }

    .radio {
      margin-left: 130px;
    }

    .text {
      margin-left: 50px;
      margin-top: 6px;
    }
  }

  #echart {
    width: 100%;
    margin-top: 10px;
    height: calc(100% - 42px);
  }
}

:deep() .el-radio-button__original-radio:checked + .el-radio-button__inner {
  background-color: rgba(9, 21, 42, 0.8);
  border-color: rgba(9, 21, 42, 0.8);
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
}
</style>
