<template>
  <el-dialog v-model="dialogVisible" title="检测井" width="800"  destroy-on-close @open="openChartLine"> 
    <el-date-picker v-model="timeValue" type="daterange" range-separator="至" start-placeholder="开始日期"
      style="width: 400px;" end-placeholder="结束日期">
    </el-date-picker>
    <el-select v-model="selectValue" placeholder="请选择" style="width: 300px;float: right;" multiple>
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
  console.log(123131);
  
  const timeValue = ref()
  const selectValue = ref()
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
  // nextTick(() => {
  //   initEchart()
  // })

  function openChartLine() {
    var chartDom = document.getElementById('echartLine');
    var myChart = echarts.init(chartDom);
    var option;
    var fontColor = '#30eee9';
    option = {
      grid: {
        left: '5%',
        right: '5%',
        top: '15%',
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
          name: 'PH',
          min: 0,
          max: 1000,
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
        },
        {
          type: 'value',
          name: '温度',
          min: 0,
          max: 1000,
          axisLabel: {
            formatter: '{value} ',
            textStyle: {
              color: '#186afe'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#186afe'
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
              color: '#0092f6',
              lineStyle: {
                color: "#0092f6",
                width: 1
              },
              areaStyle: {
                //color: '#94C9EC'
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(0,146,246,0.9)'
                }]),
              }
            }
          },
          markPoint: {
            itemStyle: {
              normal: {
                color: 'red'
              }
            }
          },
          data: [120, 132, 101, 134, 90, 230, 210, 182, 191, 234, 290, 330]
        },
        {
          name: '温度',
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
                //color: '#94C9EC'
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
        {
          name: '水位',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: '#aecb56',
              lineStyle: {
                color: "#aecb56",
                width: 1
              },
              areaStyle: {
                //color: '#94C9EC'
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(114,144,89,0.9)'
                }]),
              }
            }
          },
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        },
        {
          name: '氧化还原电位',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: '#aecb56',
              lineStyle: {
                color: "#aecb56",
                width: 1
              },
              areaStyle: {
                //color: '#94C9EC'
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(114,144,89,0.9)'
                }]),
              }
            }
          },
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        },
        {
          name: '溶解氧',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: '#aecb56',
              lineStyle: {
                color: "#aecb56",
                width: 1
              },
              areaStyle: {
                //color: '#94C9EC'
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(114,144,89,0.9)'
                }]),
              }
            }
          },
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        },
        {
          name: '电导率',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: '#aecb56',
              lineStyle: {
                color: "#aecb56",
                width: 1
              },
              areaStyle: {
                //color: '#94C9EC'
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(114,144,89,0.9)'
                }]),
              }
            }
          },
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        },
        {
          name: '氨氮',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: '#aecb56',
              lineStyle: {
                color: "#aecb56",
                width: 1
              },
              areaStyle: {
                //color: '#94C9EC'
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: 'rgba(7,44,90,0.3)'
                }, {
                  offset: 1,
                  color: 'rgba(114,144,89,0.9)'
                }]),
              }
            }
          },
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };

    option && myChart.setOption(option);
    //  data: ['PH', '温度', '水位', '氧化还原电位', '溶解氧', '电导率', '氨氮']
  }
</script>

<style lang="scss" scoped>
  #echartLine {
    width: 100%;
    height: 300px;
  }
</style>