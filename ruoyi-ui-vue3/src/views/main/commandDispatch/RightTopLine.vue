<template>
  <div class="table">
    <div class="title">
      <div class="image">
        <img src="@/assets/static/subTitle.png" alt="" />
        <span> {{ chart.chartInfo.wellCode+'(' +chart.chartInfo.location+')' }} </span>
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
  import { listMonitoring } from "@/api/admin/monitoring";
  const chartLine = ref();
  const xAxis = ref("周");
  let lineChart = null;
  let lineOption = {};
  const selectValue = ref("pH");
  let lineInfo = ref([]);
  let lineXAxis = ref([]);
  let lineSeries = ref([]);
  const chart = defineProps(["chartInfo"]);

  const options = [
    {
      value: "1",
      label: "pH", // 修正为标准写法
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
      label: "溶解性固体", // 建议更具体的描述
    },
    {
      value: "6",
      label: "电导率",
    },
    {
      value: "7",
      label: "氨氮",
    },
    {
      value: "8",
      label: "色度",
    },
    {
      value: "9",
      label: "浑浊度",
    },
    {
      value: "10",
      label: "总硬度",
    },
    {
      value: "11",
      label: "溶解性总固体",
    },
    {
      value: "12",
      label: "硫酸盐",
    },
    {
      value: "13",
      label: "氯化物",
    },
    {
      value: "14",
      label: "铁",
    },
    {
      value: "15",
      label: "锰",
    },
    {
      value: "16",
      label: "铝",
    },
    {
      value: "17",
      label: "阴离子表面活性剂",
    },
    {
      value: "18",
      label: "耗氧量",
    },
    {
      value: "19",
      label: "硫化物",
    },
    {
      value: "20",
      label: "钠",
    },
    {
      value: "21",
      label: "亚硝酸盐氮",
    },
    {
      value: "22",
      label: "硝酸盐氮",
    },
    {
      value: "23",
      label: "碘化物",
    },
    {
      value: "24",
      label: "硒",
    },
    {
      value: "25",
      label: "铜",
    },
    {
      value: "26",
      label: "挥发酚",
    },
    {
      value: "27",
      label: "氰化物",
    },
    {
      value: "28",
      label: "氟化物",
    },
    {
      value: "29",
      label: "汞",
    },
    {
      value: "30",
      label: "砷",
    },
    {
      value: "31",
      label: "镉",
    },
    // 新增选项（去除单位，仅保留核心名称）
    { value: "32", label: "六价铬" },
    { value: "33", label: "铅" },
    { value: "34", label: "氯仿" },
    { value: "35", label: "四氯化碳" },
    { value: "36", label: "苯" },
    { value: "37", label: "甲苯" },
    { value: "38", label: "钼" },
    { value: "39", label: "钒" },
    { value: "40", label: "钴" },
    { value: "41", label: "镍" },
    { value: "42", label: "二氯甲烷" },
    { value: "43", label: "1,2-二氯乙烷" },
    { value: "44", label: "1,1,1-三氯乙烷" },
    { value: "45", label: "1,1,2-三氯乙烷" },
    { value: "46", label: "1,2-二氯丙烷" },
    { value: "47", label: "氯乙烯" },
    { value: "48", label: "1,1-二氯乙烯" },
    { value: "49", label: "反式-1,2-二氯乙烯" },
    { value: "50", label: "顺式-1,2-二氯乙烯" },
    { value: "51", label: "三氯乙烯" },
    { value: "52", label: "四氯乙烯" },
    { value: "53", label: "氯苯" },
    { value: "54", label: "1,2-二氯苯" },
    { value: "55", label: "1,4-二氯苯" },
    { value: "56", label: "乙苯" },
    { value: "57", label: "邻二甲苯" },
    { value: "58", label: "间/对-二甲苯" },
    { value: "59", label: "苯乙烯" },
    { value: "60", label: "硝基苯" },
    { value: "61", label: "萘" },
    { value: "62", label: "蒽" },
    { value: "63", label: "荧蒽" },
    { value: "64", label: "苯并[b]荧蒽" },
    { value: "65", label: "苯并[a]芘" },
    { value: "66", label: "石油烃(C6-C9)" },
    { value: "67", label: "石油烃(C10-C40)" },
    { value: "68", label: "苯酚" },
    { value: "69", label: "2-氯酚" },
    { value: "70", label: "苯胺" },
    { value: "71", label: "苊烯" },
    { value: "72", label: "苊" },
    { value: "73", label: "芴" },
    { value: "74", label: "菲" },
    { value: "75", label: "芘" },
    { value: "76", label: "苯并[a]蒽" },
    { value: "77", label: "䓛" },
    { value: "78", label: "苯并[k]荧蒽" },
    { value: "79", label: "茚并[1,2,3-c,d]芘" },
    { value: "80", label: "二苯并[a,h]蒽" },
    { value: "81", label: "苯并[g,h,i]苝" },
    { value: "82", label: "氯甲烷" },
    { value: "83", label: "1,1-二氯乙烷" },
    { value: "84", label: "1,1,1,2-四氯乙烷" },
    { value: "85", label: "1,1,2,2-四氯乙烷" },
    { value: "86", label: "1,2,3-三氯丙烷" },
  ];
  onMounted(() => {
    seekLineData({ pageNum: 1, pageSize: 1000, pointId: "J01" }, 'ph');
  });

  watch(chart, (newValue, oldValue) => {
    selectValue.value = 'pH'
    seekLineData({
      pageNum: 1,
      pageSize: 1000,
      pointId: chart.chartInfo.wellCode,
    }, 'ph');
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
          ammoniaNitrogen: element.ammoniaNitrogen,
          sampleTime: element.sampleTime
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
    }, 'ph');
    chartLine.value.dialogVisible = true;
    const elements = document.getElementsByClassName('RightLine');
    elements[0].style.zIndex = 100
  }

  window.openJCPageVue = function (data) {
    let attr = data.attr;
    seekLineData({ pageNum: 1, pageSize: 1000, pointId: attr.wellCode }, 'ph');
    nextTick(() => {
      chartLine.value.dialogVisible = true;
    });
  };

  window.openLineChartPage = function (data) {
    nextTick(() => {
      chart.chartInfo.wellCode = data.attr.wellCode;
      chart.chartInfo.location = data.attr.location
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
        data: ["pH", "温度", "水位", "氧化还原电位", "溶解性总固体", "电导率", "氨氮"],
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

  :deep() .el-radio-button__original-radio:checked+.el-radio-button__inner {
    background-color: rgba(9, 21, 42, 0.8);
    border-color: rgba(9, 21, 42, 0.8);
  }
</style>