<template>
  <div class="table">
    <div class="title">
      <div class="image">
        <img src="@/assets/static/subTitle.png" alt="" />
        <span> {{ chartTitle.wellCode+'(' +chartTitle.location+')' }} </span>
      </div>
      <el-button type="text" class="text" @click="openChartLine">更多</el-button>
    </div>
    <div class="selectRadio">
      <el-select v-model="selectValue" placeholder="请选择" style="width: 150px; margin-left: 55px" @change="seekLineData">
        <el-option v-for="item in indicatorsOption" :key="item.value" :label="item.label" :value="item.value">
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
  import { listMonitoring } from "@/api/admin/monitoring";
  import { listWells } from "@/api/admin/wells";
  import { listIndicators } from "@/api/admin/indicators";
  const chartLine = ref();
  const xAxis = ref("周");
  let lineChart = null;
  let lineOption = {};
  const selectValue = ref("pH");
  let dataList = []
  const indicatorsOption = ref([])
  const chartTitle = reactive({
    wellCode: '',
    location: ''
  })
  // const options = [
  //   {
  //     value: "ph",
  //     label: "pH", // 修正为标准写法
  //   },
  //   // {
  //   //   value: "2",
  //   //   label: "温度",
  //   // },
  //   // {
  //   //   value: "3",
  //   //   label: "水位",
  //   // },
  //   // {
  //   //   value: "4",
  //   //   label: "电位",
  //   // },
  //   // {
  //   //   value: "5",
  //   //   label: "溶解性固体", // 建议更具体的描述
  //   // },
  //   // {
  //   //   value: "6",
  //   //   label: "电导率",
  //   // },
  //   {
  //     value: "ammoniaNitrogen",
  //     label: "氨氮",
  //   },
  //   {
  //     value: "color",
  //     label: "色度",
  //   },
  //   {
  //     value: "turbidity",
  //     label: "浑浊度",
  //   },
  //   {
  //     value: "totalHardness",
  //     label: "总硬度",
  //   },
  //   {
  //     value: "totalDissolvedSolids",
  //     label: "溶解性总固体",
  //   },
  //   {
  //     value: "sulfate",
  //     label: "硫酸盐",
  //   },
  //   {
  //     value: "chloride",
  //     label: "氯化物",
  //   },
  //   {
  //     value: "iron",
  //     label: "铁",
  //   },
  //   {
  //     value: "manganese",
  //     label: "锰",
  //   },
  //   {
  //     value: "aluminum",
  //     label: "铝",
  //   },
  //   {
  //     value: "anionicSurfactant",
  //     label: "阴离子表面活性剂",
  //   },
  //   {
  //     value: "oxygenConsumption",
  //     label: "耗氧量",
  //   },
  //   {
  //     value: "sulfide",
  //     label: "硫化物",
  //   },
  //   {
  //     value: "sodium",
  //     label: "钠",
  //   },
  //   {
  //     value: "nitriteNitrogen",
  //     label: "亚硝酸盐氮",
  //   },
  //   {
  //     value: "nitrateNitrogen",
  //     label: "硝酸盐氮",
  //   },
  //   {
  //     value: "iodide",
  //     label: "碘化物",
  //   },
  //   {
  //     value: "selenium",
  //     label: "硒",
  //   },
  //   {
  //     value: "copper",
  //     label: "铜",
  //   },
  //   {
  //     value: "volatilePhenol",
  //     label: "挥发酚",
  //   },
  //   {
  //     value: "cyanide",
  //     label: "氰化物",
  //   },
  //   {
  //     value: "fluoride",
  //     label: "氟化物",
  //   },
  //   {
  //     value: "mercury",
  //     label: "汞",
  //   },
  //   {
  //     value: "arsenic",
  //     label: "砷",
  //   },
  //   {
  //     value: "cadmium",
  //     label: "镉",
  //   },
  //   // 新增选项（去除单位，仅保留核心名称）
  //   { value: "chromiumVi", label: "六价铬" },
  //   { value: "lead", label: "铅" },
  //   { value: "chloroform", label: "氯仿" },
  //   { value: "carbonTetrachloride", label: "四氯化碳" },
  //   { value: "benzene", label: "苯" },
  //   { value: "toluene", label: "甲苯" },
  //   { value: "molybdenum", label: "钼" },
  //   { value: "vanadium", label: "钒" },
  //   { value: "cobalt", label: "钴" },
  //   { value: "nickel", label: "镍" },
  //   { value: "dichloromethane", label: "二氯甲烷" },
  //   { value: "dichloroethane12", label: "1,2-二氯乙烷" },
  //   { value: "trichloroethane111", label: "1,1,1-三氯乙烷" },
  //   { value: "trichloroethane112", label: "1,1,2-三氯乙烷" },
  //   { value: "dichloropropane12", label: "1,2-二氯丙烷" },
  //   { value: "vinylChloride", label: "氯乙烯" },
  //   { value: "dichloroethylene11", label: "1,1-二氯乙烯" },
  //   { value: "transDichloroethylene12", label: "反式-1,2-二氯乙烯" },
  //   { value: "cisDichloroethylene12", label: "顺式-1,2-二氯乙烯" },
  //   { value: "trichloroethylene", label: "三氯乙烯" },
  //   { value: "tetrachloroethylene", label: "四氯乙烯" },
  //   { value: "chlorobenzene", label: "氯苯" },
  //   { value: "dichlorobenzene12", label: "1,2-二氯苯" },
  //   { value: "dichlorobenzene14", label: "1,4-二氯苯" },
  //   { value: "ethylbenzene", label: "乙苯" },
  //   { value: "xyleneOrtho", label: "邻二甲苯" },
  //   { value: "xyleneMetaPara", label: "间/对-二甲苯" },
  //   { value: "styrene", label: "苯乙烯" },
  //   { value: "nitrobenzene", label: "硝基苯" },
  //   { value: "naphthalene", label: "萘" },
  //   { value: "anthracene", label: "蒽" },
  //   { value: "fluoranthene", label: "荧蒽" },
  //   { value: "benzoBFluoranthene", label: "苯并[b]荧蒽" },
  //   { value: "benzoAPyrene", label: "苯并[a]芘" },
  //   { value: "petroleumHydrocarbonsC6C9", label: "石油烃(C6-C9)" },
  //   { value: "petroleumHydrocarbonsC10C40", label: "石油烃(C10-C40)" },
  //   { value: "phenol", label: "苯酚" },
  //   { value: "chlorophenol2", label: "2-氯酚" },
  //   { value: "aniline", label: "苯胺" },
  //   { value: "acenaphthylene", label: "苊烯" },
  //   { value: "acenaphthene", label: "苊" },
  //   { value: "fluorene", label: "芴" },
  //   { value: "phenanthrene", label: "菲" },
  //   { value: "pyrene", label: "芘" },
  //   { value: "benzoAAnthracene", label: "苯并[a]蒽" },
  //   { value: "chrysene", label: "䓛" },
  //   { value: "benzoKFluoranthene", label: "苯并[k]荧蒽" },
  //   { value: "indeno123cdPyrene", label: "茚并[1,2,3-c,d]芘" },
  //   { value: "dibenzoAhAnthracene", label: "二苯并[a,h]蒽" },
  //   { value: "benzoGhiPerylene", label: "苯并[g,h,i]苝" },
  //   { value: "chloromethane", label: "氯甲烷" },
  //   { value: "dichloroethane11", label: "1,1-二氯乙烷" },
  //   { value: "tetrachloroethane1112", label: "1,1,1,2-四氯乙烷" },
  //   { value: "tetrachloroethane1122", label: "1,1,2,2-四氯乙烷" },
  //   { value: "trichloropropane123", label: "1,2,3-三氯丙烷" },
  // ];

  // 获取检测井数据
  onMounted(() => {
    listWells({ pageNum: 1, pageSize: 10 }).then((response) => {
      if (response.code === 200) {
        const data = response.rows
        chartTitle.wellCode = data[0].wellCode
        chartTitle.location = data[0].location
        listMonitoringList({ pageNum: 1, pageSize: 2000, pointId: data[0].wellCode }, 'ph');
      }
    });
    // 下拉指标切换
    listIndicators({ pageNum: 1, pageSize: 2000 }).then((response) => {
      if (response.code === 200) {
        response.rows.forEach((ele) => {
          if (ele.attribute != 'pointId' && ele.attribute != "sampleTime") {
            indicatorsOption.value.push(
              {
                value: ele.attribute,
                label: ele.type
              }
            )
          }
        })

      }
    });
  });

  // 调用接口
  function listMonitoringList(option, type) {
    listMonitoring(option).then((res) => {
      const data = res.rows;
      dataList = data.sort((a, b) => {
        return Number(a.sampleTime) - Number(b.sampleTime)
      })
      chartLine.value.tableData = dataList
      seekLineData(type)
    });
  }

  // 调用接口
  function seekLineData(type) {
    // chartLine.value.tableData = [];
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
    seekLineData('ph');
    chartLine.value.dialogVisible = true;
    const elements = document.getElementsByClassName('RightLine');
    elements[0].style.zIndex = 100
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
      chartTitle.location = data.attr.location
      seekLineData('ph');
      selectValue.value = 'pH'
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

  :deep() .el-radio-button__original-radio:checked+.el-radio-button__inner {
    background-color: rgba(9, 21, 42, 0.8);
    border-color: rgba(9, 21, 42, 0.8);
  }
</style>