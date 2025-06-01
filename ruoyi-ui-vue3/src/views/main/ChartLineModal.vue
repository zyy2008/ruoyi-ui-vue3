<template>
  <el-dialog z-index="999999" v-model="dialogVisible" title="检测井" width="800"  destroy-on-close @open="openChartLine"> 
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
      <el-table-column prop="date" label="PH值" />
      <el-table-column prop="name" label="温度" width="55" />
      <el-table-column prop="address" label="水位" />
      <el-table-column prop="name1" label="电位" />
      <el-table-column prop="address1" label="溶解度" />
    </el-table>
  </el-dialog>

</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick } from "vue";
  import * as echarts from "echarts";
    let lineChart = null
  let lineOption = {}
  const selectValue = ref('PH')
  const timeValue = ref()
  const dialogVisible = ref(false);
  defineExpose({ dialogVisible })
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
  
  const tableData = [
    {
      date: '检测井1',
      name: 'PH',
      address: '6.7',
      name1: '7',
      address1: '105%',
    },
    {
      date: '检测井2',
      name: '温度',
      address: '42',
      name1: '30',
      address1: '70%',
    },
    {
      date: '检测井3',
      name: '氨氮',
      address: '1',
      name1: '0.8',
      address1: '80%',
    },
    {
      date: '检测井4',
      name: '水位',
      address: '60',
      name1: '80',
      address1: '120%',
    },  
  ]

 
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
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天']
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
          data: [220, 182, 191, 234, 290, 330, 310, 201, 154, 190, 330, 410]
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
  :deep(){
        .el-select__placeholder{
      color: white;
    }
  }
</style>