<template>
  <div class="table">
    <div class="title">
      <div class="image">
        <img src="@/assets/static/subTitle.png" alt="" />
        <span> {{ chart.chartInfo.wellCode }} </span>
      </div>
      <el-button type="text" class="text" @click="openChartLine">更多</el-button>
    </div>
    <div class="selectRadio">
      <el-select v-model="selectValue" placeholder="请选择" style="width: 150px; margin-left: 55px" @change="changeSelect">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <el-radio-group v-model="xAxis" class="radio" @change="changeChart">
        <el-radio-button label="周"></el-radio-button>
        <el-radio-button label="月"></el-radio-button>
      </el-radio-group>
    </div>
    <div id="echart"></div>
    <ChartLineModal ref="chartLine" :chart="chart" :lineXAxis="lineXAxis" :lineSeries="lineSeries" />
  </div>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
  import * as echarts from "echarts";
  import ChartLineModal from "./ChartLineModal.vue";
  import {
    listMonitoring,
    getMonitoring,
    delMonitoring,
    addMonitoring,
    updateMonitoring,
  } from "@/api/admin/monitoring";
  const chartLine = ref();
  const xAxis = ref("周");
  let lineChart = null;
  let lineOption = {};
  const selectValue = ref("PH");
  let lineInfo = ref([]);
  let lineXAxis = ref([]);
  let lineSeries = ref([]);
  const chart = defineProps(["chartInfo"]);

  const options = [
    {
      value: "1",
      label: "PH",
    },
    {
      value: "2",
      label: "温度",
    },
    {
      value: "3",
      label: "水位",
    },
    {
      value: "4",
      label: "电位",
    },
    {
      value: "5",
      label: "溶解性",
    },
    {
      value: "6",
      label: "电导率",
    },
    {
      value: "7",
      label: "氨氮",
    },
  ];
  onMounted(() => {
    seekLineData({ pageNum: 1, pageSize: 1000, pointId: "J01" }, 'ph');
  });

  watch(chart, (newValue, oldValue) => {
       selectValue.value='PH'
    seekLineData({
      pageNum: 1,
      pageSize: 1000,
      pointId: chart.chartInfo.wellCode,
    },'ph');
  });

  function seekLineData(option, type) {
 
    chartLine.value.tableData = [];
    listMonitoring(option).then((res) => {
      lineOption.xAxis[0].data = [];
      lineOption.series[0].data = [];
      lineSeries.value = [];
      lineXAxis.value = [];
      const data = res.rows;
      let dataList = data.sort((a, b) => {
        return Number(a.sampleTime) - Number(b.sampleTime)
      })

      dataList.forEach((element, index) => {

        chartLine.value.tableData.push({
          ph: element.ph,
          totalDissolvedSolids: element.totalDissolvedSolids,
          ammoniaNitrogen:element.ammoniaNitrogen,
          sampleTime:element.sampleTime
        });
        
       
        if (type === 'ph') {
          if (element.ph) {
            lineOption.series[0].data.push(element.ph);
            lineSeries.value.push(element.ph);
                  lineOption.xAxis[0].data.push(element.sampleTime);
        lineXAxis.value.push(element.sampleTime);
          }
        } else if (type === 'dissolved') {
          if (element.totalDissolvedSolids) {
            lineOption.series[0].data.push(element.totalDissolvedSolids);
            lineSeries.value.push(element.totalDissolvedSolids);
                  lineOption.xAxis[0].data.push(element.sampleTime);
        lineXAxis.value.push(element.sampleTime);
          }
        } else if (type === 'ammoniaNitrogen') {
          if (element.ammoniaNitrogen) {
            lineOption.series[0].data.push(element.ammoniaNitrogen);
            lineSeries.value.push(element.ammoniaNitrogen);
                  lineOption.xAxis[0].data.push(element.sampleTime);
        lineXAxis.value.push(element.sampleTime);
          }
        }
      });
      lineChart.setOption(lineOption);
    });
  }

  function changeSelect(label) {
    if (label === '1') {
      seekLineData({
        pageNum: 1,
        pageSize: 1000,
        pointId: chart.chartInfo.wellCode,
      }, 'ph');
    } else if (label === '5') {
      seekLineData({
        pageNum: 1,
        pageSize: 1000,
        pointId: chart.chartInfo.wellCode,
      }, 'dissolved');
    } else if (label === '7') {
      seekLineData({
        pageNum: 1,
        pageSize: 1000,
        pointId: chart.chartInfo.wellCode,
      }, 'ammoniaNitrogen');
    } else {
      lineOption.xAxis[0].data = [];
      lineOption.series[0].data = [];
      lineChart.setOption(lineOption);
    }
  }

  function openChartLine() {
    seekLineData({
      pageNum: 1,
      pageSize: 1000,
      pointId: chart.chartInfo.wellCode,
    },'ph');
    chartLine.value.dialogVisible = true;
  }

  window.openJCPageVue = function (data) {
    let attr = data.attr;
    seekLineData({ pageNum: 1, pageSize: 1000, pointId: attr.wellCode },'ph');
    nextTick(() => {
      chartLine.value.dialogVisible = true;
    });
  };

  window.openLineChartPage = function (data) {
    nextTick(() => {
      chart.chartInfo.wellCode = data.graphic.label.text;
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
        data: ["PH", "温度", "水位", "氧化还原电位", "溶解性", "电导率", "氨氮"],
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
          name: "PH",
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
          data: [220, 182, 191, 234, 290, 330, 310, 201, 154, 190, 330, 410],
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
        display: flex;
      }

      span {
        font-size: 18px;
        display: inline-block;
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

  :deep() .el-radio-button__original-radio:checked+.el-radio-button__inner {
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

    .el-select__placeholder {
      color: white;
    }
  }
</style>