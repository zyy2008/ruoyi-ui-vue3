<template>
  <el-dialog z-index="10" v-model="dialogVisible"
    :title="chart.chart.chartInfo.wellCode+'('+chart.chart.chartInfo.location+')'" width="100vw" :top="'200px'"
    destroy-on-close @open="openChartLine" style="height: 650px;">
    <el-date-picker v-model="timeValue" type="daterange" range-separator="至" start-placeholder="开始日期"
      style="width: 400px" end-placeholder="结束日期">
    </el-date-picker>
    <el-button type="primary" color="#072d50" :icon="Search" style="margin-left: 20px;">查询</el-button>
    <el-select v-model="selectValue" placeholder="请选择" style="width: 300px; float: right" @change="changeSelect">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
      </el-option>
    </el-select>
    <div id="echartLine"></div>
    <el-table :data="tableInfo" style="width: 100%" :row-class-name="tableRowClassName">
      <!-- <el-table-column type="index" label="序号" width="55" /> -->
      <!-- <el-table-column prop="ph" label="PH" />
      <el-table-column prop="name" label="温度" />
      <el-table-column prop="address" label="水位" />
      <el-table-column prop="name1" label="电位" />
      <el-table-column prop="totalDissolvedSolids" label="溶解性" />
      <el-table-column prop="name1" label="电导率" />
      <el-table-column prop="ammoniaNitrogen" label="氨氮" /> -->
      <el-table-column prop="sampleTime" label="时间" width="100" />
      <el-table-column prop="color" label="色度" />
      <el-table-column prop="odorAndTaste" label="臭和味" />
      <el-table-column prop="turbidity" label="浊度" />
      <el-table-column prop="visibleMatters" label="肉眼可见物" width="100" />
      <el-table-column prop="ph" label="pH" />
      <el-table-column prop="totalHardness" label="总硬度" />
      <el-table-column prop="totalDissolvedSolids" label="溶解性总固体" width="110" />
      <el-table-column prop="sulfate" label="硫酸盐" />
      <el-table-column prop="chloride" label="氯化物" />
      <el-table-column prop="iron" label="铁" />
      <el-table-column prop="manganese" label="锰" />
      <el-table-column prop="aluminum" label="铝" />
      <el-table-column prop="anionicSurfactant" label="阴离子表面活性剂" width="140" />
      <el-table-column prop="oxygenConsumption" label="耗氧量" />
      <el-table-column prop="sulfide" label="硫化物" />
      <el-table-column prop="sodium" label="钠" />
      <el-table-column prop="nitriteNitrogen" label="亚硝酸盐氮" width="100" />
      <el-table-column prop="nitrateNitrogen" label="硝酸盐氮" />
      <el-table-column prop="iodide" label="碘化物" />
      <el-table-column prop="selenium" label="硒" />
      <el-table-column prop="copper" label="铜" />
      <el-table-column prop="zinc" label="锌" />
      <el-table-column prop="volatilePhenol" label="挥发酚" />
      <el-table-column prop="ammoniaNitrogen" label="氨氮" />
      <el-table-column prop="cyanide" label="氰化物" />
      <el-table-column prop="fluoride" label="氟化物" />
      <el-table-column prop="mercury" label="汞" />
      <el-table-column prop="arsenic" label="砷" />
      <el-table-column prop="cadmium" label="镉" />
      <el-table-column prop="chromiumVi" label="六价铬" />
      <el-table-column prop="lead" label="铅" />
      <el-table-column prop="chloroform" label="氯仿" />
      <el-table-column prop="carbonTetrachloride" label="四氯化碳" />
      <el-table-column prop="benzene" label="苯" />
      <el-table-column prop="toluene" label="甲苯" />
      <el-table-column prop="molybdenum" label="钼" />
      <el-table-column prop="vanadium" label="钒" />
      <el-table-column prop="cobalt" label="钴" />
      <el-table-column prop="nickel" label="镍" />
      <el-table-column prop="dichloromethane" label="二氯甲烷" />
      <el-table-column prop="dichloroethane12" label="1,2-二氯乙烷" width="110" />
      <el-table-column prop="trichloroethane111" label="1,1,1-三氯乙烷" width="120" />
      <el-table-column prop="trichloroethane112" label="1,1,2-三氯乙烷" width="120" />
      <el-table-column prop="dichloropropane12" label="1,2-二氯丙烷" width="110" />
      <el-table-column prop="vinylChloride" label="氯乙烯" />
      <el-table-column prop="dichloroethylene11" label="1,1-二氯乙烯" width="110" />
      <el-table-column prop="transDichloroethylene12" label="反式-1,2-二氯乙烯" width="140" />
      <el-table-column prop="cisDichloroethylene12" label="顺式-1,2-二氯乙烯" width="140" />
      <el-table-column prop="trichloroethylene" label="三氯乙烯" />
      <el-table-column prop="tetrachloroethylene" label="四氯乙烯" />
      <el-table-column prop="chlorobenzene" label="氯苯" />
      <el-table-column prop="dichlorobenzene12" label="1,2-二氯苯" width="100" />
      <el-table-column prop="dichlorobenzene14" label="1,4-二氯苯" width="100" />
      <el-table-column prop="ethylbenzene" label="乙苯" />
      <el-table-column prop="xyleneOrtho" label="邻二甲苯" />
      <el-table-column prop="xyleneMetaPara" label="间/对-二甲苯" width="110" />
      <el-table-column prop="styrene" label="苯乙烯" />
      <el-table-column prop="nitrobenzene" label="硝基苯" />
      <el-table-column prop="naphthalene" label="萘" />
      <el-table-column prop="anthracene" label="蒽" />
      <el-table-column prop="fluoranthene" label="荧蒽" />
      <el-table-column prop="benzoBFluoranthene" label="苯并[b]荧蒽" width="100" />
      <el-table-column prop="benzoAPyrene" label="苯并[a]芘" width="100" />
      <el-table-column prop="petroleumHydrocarbonsC6C9" label="石油烃(C6-C9)" width="120" />
      <el-table-column prop="petroleumHydrocarbonsC10C40" label="石油烃(C10-C40)" width="140" />
      <el-table-column prop="phenol" label="苯酚" />
      <el-table-column prop="chlorophenol2" label="2-氯酚" />
      <el-table-column prop="aniline" label="苯胺" />
      <el-table-column prop="acenaphthylene" label="苊烯" />
      <el-table-column prop="acenaphthene" label="苊" />
      <el-table-column prop="fluorene" label="芴" />
      <el-table-column prop="phenanthrene" label="菲" />
      <el-table-column prop="pyrene" label="芘" />
      <el-table-column prop="benzoAAnthracene" label="苯并[a]蒽" width="90" />
      <el-table-column prop="chrysene" label="䓛" />
      <el-table-column prop="benzoKFluoranthene" label="苯并[k]荧蒽" width="100" />
      <el-table-column prop="indeno123cdPyrene" label="茚并[1,2,3-c,d]芘" width="140" />
      <el-table-column prop="dibenzoAhAnthracene" label="二苯并[a,h]蒽" width="110" />
      <el-table-column prop="benzoGhiPerylene" label="苯并[g,h,i]苝" width="110" />
      <el-table-column prop="chloromethane" label="氯甲烷" />
      <el-table-column prop="dichloroethane11" label="1,1-二氯乙烷" width="110" />
      <el-table-column prop="tetrachloroethane1112" label="1,1,1,2-四氯乙烷" width="130" />
      <el-table-column prop="tetrachloroethane1122" label="1,1,2,2-四氯乙烷" width="130" />
      <el-table-column prop="trichloropropane123" label="1,2,3-三氯丙烷" width="130" />
    </el-table>
  </el-dialog>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
  import * as echarts from "echarts";
  import { Search } from '@element-plus/icons-vue'
  import { listMonitoring } from "@/api/admin/monitoring";
  let lineChart = null;
  let lineOption = {};
  const selectValue = ref("PH");
  const timeValue = ref();
  const dialogVisible = ref(false);
  const chart = defineProps(["chart", "lineXAxis", "lineSeries"]);
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

  const tableData = ref([]);
  const tableInfo = ref([])
  onMounted(() => {
    createLineInfo()
  })

  function createLineInfo() {
    listMonitoring({ pageNum: 1, pageSize: 1000, pointId: chart.chart.chartInfo.wellCode }).then((res) => {
      if (res.code === 200) {
        tableInfo.value = res.rows
      }
    });
  }
  defineExpose({ dialogVisible, tableData });

  watch(
    chart,
    (newValue, oldValue) => {
      if (chart.lineXAxis && lineOption.xAxis) {
        const time = tableData.value.map((ele) => ele.sampleTime);
        const data = tableData.value.map((ele) => ele.ph);
        seekLineData(data, time);
        createLineInfo()
      }
    },
    {
      deep: true,
      immediate: true,
    }
  );

  function seekLineData(data, time) {
    lineOption.xAxis[0].data = time;
    lineOption.series[0].data = data;
    lineChart.setOption(lineOption);
  }

  function changeSelect(label) {
    let time = tableData.value.map((ele) => ele.sampleTime);
    let data = [];
    if (label === "1") {
      tableData.value.forEach((ele) => {
        if (ele.ph) {
          data.push(ele.ph);
        }
      });
    } else if (label === "5") {
      tableData.value.forEach((ele) => {
        if (ele.totalDissolvedSolids) {
          data.push(ele.totalDissolvedSolids);
        }
      });
    } else if (label === "7") {
      tableData.value.forEach((ele) => {
        if (ele.ammoniaNitrogen) {
          data.push(ele.ammoniaNitrogen);
        }
      });
    } else {
      data = [];
      time = [];
    }
    seekLineData(data, time);
  }
  function openChartLine() {
    selectValue.value = "PH";
    var chartDom = document.getElementById("echartLine");
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
          data: ["0", "1", "2"],
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
          data: [7.04, 7.27, 7.65],
        },
      ],
    };
    lineOption && lineChart.setOption(lineOption);
  }
</script>

<style lang="scss" scoped>
  #echartLine {
    width: 100%;
    height: 300px;
    margin-top: 15px;
  }

  :deep() {
    .el-select__placeholder {
      color: white;
    }
    
  }
</style>