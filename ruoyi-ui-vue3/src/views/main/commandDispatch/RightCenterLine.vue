<template>
  <div class="table">
    <div class="title">
      <div class="image">
        <img src="@/assets/static/subTitle.png" alt="" />
        <span> {{ chartTitle.monitoringWell }} </span>
        <!-- <span> {{ chartTitle.chartInfo.monitoringWell+'(' +chartTitle.chartInfo.location+')' }} </span> -->
      </div>
      <el-radio-group v-model="xAxis" style="width: 150px;" @change="changeChart">
        <el-radio-button label="周"></el-radio-button>
        <el-radio-button label="月"></el-radio-button>
      </el-radio-group>
      <el-button type="text" class="text" @click="openChartLine">更多</el-button>
    </div>
    <div class="echartLine">
      <div class="echart">
        <div id="echart1"></div>
        <div id="echart2"> </div>
        <div id="echart3"></div>
      </div>
      <div class="echart">
        <div id="echart4"></div>
        <div id="echart5"></div>
        <div id="echart6"></div>
      </div>
    </div>
    <ChartLineModal ref="chartLine" :chartTitle="chartTitle" :chartType="chartType" />
  </div>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
  import * as echarts from "echarts";
  import ChartLineModal from "./ChartLineModal.vue";
  import { listMonitoring } from "@/api/admin/monitoring";
  import { listWells } from "@/api/admin/wells";
  import { getBatchData, getSingleWellMonitoringLineChartData } from "@/api/monitoring";
  import emitter from '@/mitt/mitt';
  import dayjs from 'dayjs';
  const chartLine = ref();
  const xAxis = ref("周");
  let lineOption = {};
  const selectValue = ref("pH");
  let lineInfo = ref([]);
  let lineXAxis = ref([]);
  let lineSeries = ref([]);
  const chartType = ref('monitor')

  const chartTitle = reactive({
    wellCode: '',
    location: ''
  })

  let dataList = []
  emitter.on('changeTableLine', changeTableLine);
  // 获取检测井数据
  onMounted(() => {
    seekLineData({
      pageNum: 1,
      pageSize: 1000,
      pointId: chartTitle.monitoringWell,
    });
    nextTick(() => {

    });
  });

  function seekLineData(option) {
    chartLine.value.tableData = [];
    getBatchData({}).then(res => {
      if (res.code === 200) {
        const data = res.data;
        dataList = data.sort((a, b) => {
          return new Date(a.monitoringTime).getTime() - new Date(b.monitoringTime).getTime()
        })
        chartLine.value.tableData = dataList
      }
    })
  }

  function openChartLine() {
    chartLine.value.dialogVisible = true;
    const elements = document.getElementsByClassName('RightCenter');
    elements[0].style.zIndex = 100
  }

  function changeTableLine(ment) {
    chartTitle.monitoringWell = ment.monitoringWell
    if (!ment.monitoringTime) return
    let days = xAxis.value === '周' ? 7 : 30
    const endDay = dayjs(ment.monitoringTime).add(days, 'day').format('YYYY-MM-DD HH:mm:ss');
    getSingleWellMonitoringLineChartData({ wellCode: ment.monitoringWell, startMonitoringTime: ment.monitoringTime, endMonitoringTime: endDay }).then(res => {
      if (res.code === 200) {
        const data = res.data
        const dataList = data.sort((a, b) => {
          return new Date(a.monitoringTime).getTime() - new Date(b.monitoringTime).getTime()
        })
        let xAxisData = []
        let seriesData = { phValue: [], temperature: [], waterLevel: [], dissolvedOxygen: [], conductivity: [], ammoniaNitrogen: [] }
        dataList.forEach(ele => {
          xAxisData.push(ele.monitoringTime.slice(5, 16))
          seriesData['phValue'].push(ele.phValue)
          seriesData['temperature'].push(ele.temperature)
          seriesData['waterLevel'].push(ele.waterLevel)
          seriesData['dissolvedOxygen'].push(ele.dissolvedOxygen)
          seriesData['conductivity'].push(ele.conductivity)
          seriesData['ammoniaNitrogen'].push(ele.ammoniaNitrogen)
        })

        initEchart('echart1', 'pH', xAxisData, seriesData['phValue']);
        initEchart('echart2', '温度', xAxisData, seriesData['temperature']);
        initEchart('echart3', '水位', xAxisData, seriesData['waterLevel']);
        initEchart('echart4', '溶解氧', xAxisData, seriesData['dissolvedOxygen']);
        initEchart('echart5', '电导率', xAxisData, seriesData['conductivity']);
        initEchart('echart6', '氨氮', xAxisData, seriesData['ammoniaNitrogen']);
      }
    })
  }

  function initEchart(id, title, xAxisData, seriesData) {
    var chartDom = document.getElementById(id);
    const lineChart = echarts.init(chartDom);
    var fontColor = "#30eee9";
    lineOption = {
      "title": {
        "text": title,
        x: "center",
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
      },
      grid: {
        left: "5%",
        right: "5%",
        top: "10%",
        bottom: "5%",
        containLabel: true,
      },
      tooltip: {
        show: true,
        trigger: "item",
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: true,
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
            show: true,
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384",
            },
          },
          data: xAxisData,
        },
      ],
      yAxis: [
        {
          type: "value",
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
          data: seriesData,
        },
      ],
    };
    lineOption && lineChart.setOption(lineOption);
  }
  //切换周和月
  const changeChart = () => {
  };
</script>

<style lang="scss" scoped>
  .table {
    width: 604px;
    padding: 10px;
    /* background: url("@/assets/static/left.png") no-repeat; */
    background-color: rgba(9, 21, 42, 0.8);

    .title {
      height: 42px;
      line-height: 42px;
      display: flex;
      justify-content: space-between;
      align-items: center;

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

    .echartLine {
      height: calc(100% - 42px);
      display: flex;

      flex-direction: column;

      .echart {
        display: flex;
        height: 50%;
        justify-content: space-between;

        #echart1,
        #echart2,
        #echart3,
        #echart4,
        #echart5,
        #echart6 {
          flex: 1;
          width: 465px;
          height: 220px;
        }
      }

    }

    .selectRadio {
      display: flex;
      justify-content: space-between;
    }
  }

  :deep() .el-radio-button__original-radio:checked+.el-radio-button__inner {
    background-color: rgba(9, 21, 42, 0.8);
    border-color: rgba(9, 21, 42, 0.8);
  }
</style>