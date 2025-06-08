<template>
  <el-dialog z-index="10" v-model="dialogVisible" title="指标详情" width="100vw" :top="'200px'" destroy-on-close
    @open="openChartBar" @close="closeChartBar" style="height: 650px;">
    <el-select v-model="selectValue" placeholder="请选择" style="width: 300px; " @change="changeSelect">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
      </el-option>
    </el-select>
    <div id="echartBar"></div>
  </el-dialog>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
  import * as echarts from "echarts";
  import { listMonitoring } from "@/api/admin/monitoring";
  let barChart = null;
  let barOption = {};
  const selectValue = ref("pH");
  const dialogVisible = ref(false);
  let xAxisData = []
  let seriesData = []
  let barList = {}
  defineExpose({ dialogVisible });
  const options = [
    {
      value: "1",
      label: "pH",
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
    }
  ]


  const changeSelect = (label) => {
    seriesData = []
    barList.forEach((ele, index) => {
      if (index < 18) {
        if (label === '1') {
          seriesData.push(ele.ph)
        } else if (label === '5') {
          seriesData.push(ele.totalDissolvedSolids)
        } else if (label === '7') {
          seriesData.push(ele.ammoniaNitrogen)
        }
      }
    })
    barOption.series[0].data = seriesData;
    barChart.setOption(barOption);
  }

  function closeChartBar() {
    const elements = document.getElementsByClassName('leftTable');
    elements[0].style.zIndex = 20
  }

  function openChartBar() {
    xAxisData = []
    seriesData = []
    selectValue.value='pH'
    var chartDom = document.getElementById("echartBar");
    barChart = echarts.init(chartDom);
    var fontColor = "#30eee9";
    listMonitoring({ pageNum: 1, pageSize: 18 }).then((res) => {
      if (res.code === 200) {
        barList = res.rows
        barList.forEach((ele, index) => {
          if (index < 18) {
            xAxisData.push(ele.pointId)
            seriesData.push(ele.ph)
          }
        })
        barOption = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          grid: {
            top: '15%',
            right: '3%',
            left: '5%',
            bottom: '12%'
          },
          xAxis: [{
            type: 'category',
            data: xAxisData,
            axisLine: {
              lineStyle: {
                color: 'rgba(255,255,255,0.12)'
              }
            },
            axisLabel: {
              margin: 10,
              color: '#e2e9ff',
              textStyle: {
                fontSize: 14
              },
            },
          }],
          yAxis: [{
            axisLabel: {
              formatter: '{value}',
              color: '#e2e9ff',
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: 'rgba(255,255,255,1)'
              }
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(255,255,255,0.12)'
              }
            }
          }],
          series: [{
            type: 'bar',
            data: seriesData,
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: 'rgba(0,244,255,1)' // 0% 处的颜色
                }, {
                  offset: 1,
                  color: 'rgba(0,77,167,1)' // 100% 处的颜色
                }], false),
                barBorderRadius: [30, 30, 30, 30],
                shadowColor: 'rgba(0,160,221,1)',
                shadowBlur: 4,
              }
            },
            label: {
              normal: {
                show: true,
                lineHeight: 30,
                width: 80,
                height: 30,
                backgroundColor: 'rgba(0,160,221,0.1)',
                borderRadius: 200,
                position: ['-8', '-60'],
                distance: 1,
                formatter: [
                  '    {d|●}',
                  ' {a|{c}}     \n',
                  '    {b|}'
                ].join(','),
                rich: {
                  d: {
                    color: '#3CDDCF',
                  },
                  a: {
                    color: '#fff',
                    align: 'center',
                  },
                  b: {
                    width: 1,
                    height: 30,
                    borderWidth: 1,
                    borderColor: '#234e6c',
                    align: 'left'
                  },
                }
              }
            }
          }]
        };
        barOption && barChart.setOption(barOption);
      }
    });




  }
</script>

<style lang="scss" scoped>
  #echartBar {
    width: 100%;
    height: 500px;
    margin-top: 15px;
  }

  :deep() {
    .el-select__placeholder {
      color: white;
    }

  }
</style>