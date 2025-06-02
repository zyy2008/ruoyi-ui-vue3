<template>
  <el-dialog z-index="999999" v-model="dialogVisible" :title="chart.chart.chartInfo.wellCode" width="800"
    destroy-on-close @open="openChartLine">
    <el-date-picker v-model="timeValue" type="daterange" range-separator="至" start-placeholder="开始日期"
      style="width: 400px;" end-placeholder="结束日期">
    </el-date-picker>
    <el-select v-model="selectValue" placeholder="请选择" style="width: 300px;float: right;">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
      </el-option>
    </el-select>
    <div id="echartLine"> </div>
    <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column type="index" label="序号" width="55" />
      <el-table-column prop="ph" label="PH" />
      <el-table-column prop="name" label="温度" />
      <el-table-column prop="address" label="水位" />
      <el-table-column prop="name1" label="电位" />
      <el-table-column prop="address1" label="溶解度" />
    </el-table>
  </el-dialog>

</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
  import * as echarts from "echarts";
  let lineChart = null
  let lineOption = {}
  const selectValue = ref('PH')
  const timeValue = ref()
  const dialogVisible = ref(false);
  const chart = defineProps(['chart', 'lineXAxis', 'lineSeries'])
  const options = [{
    value: '1',
    label: 'PH'
  }, {
    value: '2',
    label: '温度'
  }, {
    value: '3',
    label: '水位'
  }, {
    value: '4',
    label: '电位'
  }, {
    value: '5',
    label: '溶解度'
  }, {
    value: '6',
    label: '电导率'
  }, {
    value: '7',
    label: '氨氮'
  }]
  const tableData = ref([])

  defineExpose({ dialogVisible, tableData })
  watch(chart, (newValue, oldValue) => {
    if (chart.lineXAxis&&lineOption.xAxis) {
        seekLineData()
    }
  }, {
    deep: true,
    immediate: true 
  })
  function seekLineData() {
    console.log(chart);
    
    lineOption.xAxis[0].data = chart.lineXAxis
    lineOption.series[0].data = chart.lineSeries
    lineChart.setOption(lineOption);
  }
  function openChartLine() {
    var chartDom = document.getElementById('echartLine');
    lineChart = echarts.init(chartDom);
    var fontColor = '#30eee9';
    lineOption = {
      grid: {
        left: '5%',
        right: '5%',
        top: '2%',
        bottom: '5%',
        containLabel: true
      },
      tooltip: {
        show: true,
        trigger: 'item'
      },
      legend: {
        show: false,
        x: 'center',
        y: '0',
        icon: 'stack',
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: '#1bb4f6'
        },
        data: ['PH', '温度', '水位', '氧化还原电位', '溶解氧', '电导率', '氨氮']
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#397cbc'
            }
          },
          axisTick: {
            show: false,
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#195384'
            }
          },
          // data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天']
          data: ['0', '1', '2']
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: '值',
          min: 0,
          axisLabel: {
            formatter: '{value}',
            textStyle: {
              color: '#2ad1d2'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#27b4c2'
            }
          },
          axisTick: {
            show: false,
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#11366e'
            }
          }
        }
      ],
      series: [
        {
          name: 'PH',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,

          itemStyle: {
            normal: {
              color: '#00d4c7',
              lineStyle: {
                color: "#00d4c7",
                width: 1
              },
              areaStyle: {

                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(0,212,199,0.9)'
                }]),
              }
            }
          },
          data: [7.04, 7.27, 7.65]
        },
      ]
    };
    lineOption && lineChart.setOption(lineOption);
  }
</script>

<style lang="scss" scoped>
  #echartLine {
    width: 100%;
    height: 300px;
  }

  :deep() {
    .el-select__placeholder {
      color: white;
    }
  }
</style>