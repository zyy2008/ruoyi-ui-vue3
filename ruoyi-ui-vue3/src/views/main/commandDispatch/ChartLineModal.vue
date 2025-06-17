<template>
  <el-dialog z-index="10" v-model="dialogVisible"
    :title="chart.chartType==='monitor'?chart.chartTitle.monitoringWell:chart.chartTitle.wellCode+'('+chart.chartTitle.location+')' "
    width="100vw" :top="'200px'" destroy-on-close @open="openChartLine" @close="closeChartLine" style="height: 900px;">
    <el-date-picker v-model="timeValue" type="daterange" range-separator="至" start-placeholder="开始日期"
      style="width: 400px" end-placeholder="结束日期">
    </el-date-picker>
    <el-button type="primary" color="#072d50" :icon="Search" style="margin-left: 20px;">查询</el-button>
    <el-select v-model="selectValue" placeholder="请选择" style="width: 300px; float: right" @change="changeSelect">
      <el-option v-for="item in chart.chartType==='monitor'?options1: indicatorsOption" :key="item.value"
        :label="item.label" :value="item.value">
      </el-option>
    </el-select>
    <div id="echartLine"></div>
    <el-table :data="tableData" style="width: 100%" max-height="350" v-show="chart.chartType==='monitor'">
      <el-table-column type="index" label="序号" width="55" />
      <el-table-column prop="phValue" label="pH" />
      <el-table-column prop="temperature" label="温度" />
      <el-table-column prop="waterLevel" label="水位" />
      <!-- <el-table-column prop="name1" label="电位" /> -->
      <el-table-column prop="dissolvedOxygen" label="溶解氧" />
      <el-table-column prop="conductivity" label="电导率" />
      <el-table-column prop="ammoniaNitrogen" label="氨氮" />
    </el-table>
    <el-table :data="tableData" style="width: 100%" max-height="350" v-show="chart.chartType!='monitor'">
      <!-- <el-table-column type="index" label="序号" width="55" /> -->
      <!-- <el-table-column prop="ph" label="pH" />
      <el-table-column prop="name" label="温度" />
      <el-table-column prop="address" label="水位" />
      <el-table-column prop="name1" label="电位" />
      <el-table-column prop="totalDissolvedSolids" label="溶解性" />
      <el-table-column prop="name1" label="电导率" />
      <el-table-column prop="ammoniaNitrogen" label="氨氮" /> -->
      <el-table-column prop="pointId" label="点位编号" width="100" />
      <el-table-column prop="sampleTime" label="采样时间" width="100" />
      <el-table-column prop="analysisNumber" label="分析编号" width="100" />
      <el-table-column prop="originalNumber" label="原编号" />
      <el-table-column prop="carbonate" label="碳酸根(mg/L)" width="120" />
      <el-table-column prop="bicarbonate" label="碳酸氢根(mg/L)" width="130" />

      <el-table-column prop="ferrousIon" label="亚铁离子(mg/L)" width="130" />
      <el-table-column prop="potassium" label="钾(mg/L)" width="110" />
      <el-table-column prop="calcium" label="钙(mg/L)" width="110" />
      <el-table-column prop="magnesium" label="镁(mg/L)" width="110" />
      <el-table-column prop="chloroprene" label="氯丁二烯(μg/L)" width="130" />
      <el-table-column prop="dichloroethane11" label="1,1-二氯乙烷(μg/L)" width="150" />

      <el-table-column prop="tetrachloroethane1122" label="1,1,2,2-四氯乙烷(μg/L)" width="170" />
      <el-table-column prop="color" label="色度(度)" />
      <el-table-column prop="odorAndTaste" label="臭和味" />
      <el-table-column prop="turbidity" label="浑浊度(NTU)" width="110" />
      <el-table-column prop="visibleMatters" label="肉眼可见物" width="100" />
      <el-table-column prop="ph" label="pH" />
      <el-table-column prop="totalHardness" label="总硬度(mg/L)" />
      <el-table-column prop="totalDissolvedSolids" label="溶解性总固体(mg/L)" width="110" />
      <el-table-column prop="sulfate" label="硫酸盐(mg/L)" />
      <el-table-column prop="chloride" label="氯化物(mg/L)" />
      <el-table-column prop="iron" label="铁(mg/L)" />
      <el-table-column prop="manganese" label="锰(mg/L)" />

      <el-table-column prop="aluminum" label="铝(mg/L)" />
      <el-table-column prop="anionicSurfactant" label="阴离子表面活性剂(mg/L)" width="140" />
      <el-table-column prop="oxygenConsumption" label="耗氧量(mg/L)" />
      <el-table-column prop="sulfide" label="硫化物(mg/L)" />
      <el-table-column prop="sodium" label="钠(mg/L)" />
      <el-table-column prop="nitriteNitrogen" label="亚硝酸盐氮(mg/L)" width="100" />

      <el-table-column prop="nitrateNitrogen" label="硝酸盐氮(mg/L)" />
      <el-table-column prop="iodide" label="碘化物(mg/L)" />
      <el-table-column prop="selenium" label="硒(mg/L)" />
      <el-table-column prop="copper" label="铜(mg/L)" />
      <el-table-column prop="zinc" label="锌(mg/L)" />
      <el-table-column prop="volatilePhenol" label="挥发酚(mg/L)" />

      <el-table-column prop="ammoniaNitrogen" label="氨氮(mg/L)" />
      <el-table-column prop="cyanide" label="氰化物(mg/L)" />
      <el-table-column prop="fluoride" label="氟化物(mg/L)" />
      <el-table-column prop="mercury" label="汞(μg/L)" />
      <el-table-column prop="arsenic" label="砷(μg/L)" />
      <el-table-column prop="cadmium" label="镉(μg/L)" />

      <el-table-column prop="chromiumVi" label="六价铬(μg/L)" />
      <el-table-column prop="lead" label="铅(μg/L)" />
      <el-table-column prop="chloroform" label="氯仿(μg/L)" />
      <el-table-column prop="carbonTetrachloride" label="四氯化碳(μg/L)" />
      <el-table-column prop="benzene" label="苯(μg/L)" />
      <el-table-column prop="toluene" label="甲苯(μg/L)" />

      <el-table-column prop="molybdenum" label="钼(mg/L)" />
      <el-table-column prop="vanadium" label="钒(mg/L)" />
      <el-table-column prop="cobalt" label="钴(mg/L)" />
      <el-table-column prop="nickel" label="镍(mg/L)" />
      <el-table-column prop="dichloromethane" label="二氯甲烷(μg/L)" />
      <el-table-column prop="dichloroethane12" label="1,2-二氯乙烷(μg/L)" width="110" />

      <el-table-column prop="trichloroethane111" label="1,1,1-三氯乙烷(μg/L)" width="120" />
      <el-table-column prop="trichloroethane112" label="1,1,2-三氯乙烷(μg/L)" width="120" />
      <el-table-column prop="dichloropropane12" label="1,2-二氯丙烷(μg/L)" width="110" />
      <el-table-column prop="vinylChloride" label="氯乙烯(μg/L)" />
      <el-table-column prop="dichloroethylene11" label="1,1-二氯乙烯(μg/L)" width="110" />
      <el-table-column prop="transDichloroethylene12" label="反式-1,2-二氯乙烯(μg/L)" width="140" />

      <el-table-column prop="cisDichloroethylene12" label="顺式-1,2-二氯乙烯(μg/L)" width="140" />
      <el-table-column prop="trichloroethylene" label="三氯乙烯(μg/L)" />
      <el-table-column prop="tetrachloroethylene" label="四氯乙烯(μg/L)" />
      <el-table-column prop="chlorobenzene" label="氯苯(μg/L)" />
      <el-table-column prop="dichlorobenzene12" label="1,2-二氯苯(μg/L)" width="100" />
      <el-table-column prop="dichlorobenzene14" label="1,4-二氯苯(μg/L)" width="100" />

      <el-table-column prop="ethylbenzene" label="乙苯(μg/L)" />
      <el-table-column prop="xyleneOrtho" label="邻二甲苯(μg/L)" />
      <el-table-column prop="xyleneMetaPara" label="间/对-二甲苯(μg/L)" width="110" />
      <el-table-column prop="styrene" label="苯乙烯(μg/L)" />
      <el-table-column prop="nitrobenzene" label="硝基苯(μg/L)" />
      <el-table-column prop="naphthalene" label="萘(μg/L)" />

      <el-table-column prop="anthracene" label="蒽(μg/L)" />
      <el-table-column prop="fluoranthene" label="荧蒽(μg/L)" />
      <el-table-column prop="benzoBFluoranthene" label="苯并[b]荧蒽(μg/L)" width="100" />
      <el-table-column prop="benzoAPyrene" label="苯并[a]芘(μg/L)" width="100" />
      <el-table-column prop="petroleumHydrocarbonsC6C9" label="石油烃(C6-C9)(mg/L)" width="120" />
      <el-table-column prop="petroleumHydrocarbonsC10C40" label="石油烃(C10-C40)(mg/L)" width="140" />

      <el-table-column prop="phenol" label="苯酚(mg/L)" />
      <el-table-column prop="chlorophenol2" label="2-氯酚(mg/L)" />
      <el-table-column prop="aniline" label="苯胺(μg/L)" />
      <el-table-column prop="acenaphthylene" label="苊烯(μg/L)" />
      <el-table-column prop="acenaphthene" label="苊(μg/L)" />
      <el-table-column prop="fluorene" label="芴(μg/L)" />

      <el-table-column prop="phenanthrene" label="菲(μg/L)" />
      <el-table-column prop="pyrene" label="芘(μg/L)" />
      <el-table-column prop="benzoAAnthracene" label="苯并[a]蒽(μg/L)" width="90" />
      <el-table-column prop="chrysene" label="䓛(μg/L)" />
      <el-table-column prop="benzoKFluoranthene" label="苯并[k]荧蒽(μg/L)" width="100" />
      <el-table-column prop="indeno123cdPyrene" label="茚并[1,2,3-c,d]芘(μg/L)" width="140" />

      <el-table-column prop="dibenzoAhAnthracene" label="二苯并[a,h]蒽(μg/L)" width="110" />
      <el-table-column prop="benzoGhiPerylene" label="苯并[g,h,i]苝(μg/L)" width="110" />
      <el-table-column prop="chloromethane" label="氯甲烷(μg/L)" />
      <el-table-column prop="dichloroethane11" label="1,1-二氯乙烷(μg/L)" width="110" />
      <el-table-column prop="tetrachloroethane1112" label="1,1,1,2-四氯乙烷(μg/L)" width="130" />
      <el-table-column prop="tetrachloroethane1122" label="1,1,2,2-四氯乙烷(μg/L)" width="130" />

      <el-table-column prop="trichloropropane123" label="1,2,3-三氯丙烷(μg/L)" width="130" />
      <el-table-column prop="bromochloromethane" label="溴氯甲烷(μg/L)" width="130" />
      <el-table-column prop="bromodichloromethane" label="一溴二氯甲烷(μg/L)" width="130" />

      <el-table-column prop="dibromomethane" label="二溴甲烷(μg/L)" width="130" />
      <el-table-column prop="dibromochloromethane" label="二溴氯甲烷(μg/L)" width="130" />
      <el-table-column prop="dibromochloromethane" label="溴仿(μg/L)" width="130" />
      <el-table-column prop="cumene" label="异丙苯(μg/L)" width="130" />
      <el-table-column prop="nPropylbenzene" label="正丙苯(μg/L)" width="130" />
      <el-table-column prop="bromobenzene" label="溴苯(μg/L)" width="130" />

      <el-table-column prop="trimethylbenzene135" label="1,3,5-三甲基苯(μg/L)" width="130" />
      <el-table-column prop="oChlorotoluene" label="邻氯甲苯(μg/L)" width="130" />
      <el-table-column prop="pChlorotoluene" label="对氯甲苯(μg/L)" width="130" />
      <el-table-column prop="tertButylbenzene" label="叔丁基苯(μg/L)" width="130" />
      <el-table-column prop="trimethylbenzene124" label="1,2,4-三甲基苯(μg/L)" width="130" />
      <el-table-column prop="secButylbenzene" label="仲丁基苯(μg/L)" width="130" />

      <el-table-column prop="isopropyltoluene4" label="4-异丙基甲苯(μg/L)" width="130" />
      <el-table-column prop="nButylbenzene" label="正丁基苯(μg/L)" width="130" />
      <el-table-column prop="epichlorohydrin" label="环氧氯丙烷(μg/L)" width="130" />
      <el-table-column prop="dibromo3Chloropropane12" label="1,2-二溴-3-氯丙烷(μg/L)" width="130" />
      <el-table-column prop="trichlorobenzene124" label="1,2,4-三氯苯(μg/L)" width="130" />
      <el-table-column prop="hexachlorobutadiene" label="六氯丁二烯(μg/L)" width="130" />

      <el-table-column prop="methylnaphthalene2" label="2-甲基萘(μg/L)" width="130" />
      <el-table-column prop="methylnaphthalene1" label="1-甲基萘(μg/L)" width="130" />
      <el-table-column prop="dichloropropene11" label="1,1-二氯丙烯(μg/L)" width="130" />
      <el-table-column prop="trans13Dichloropropene" label="反式-1,3-二氯丙烯(μg/L)" width="130" />
      <el-table-column prop="trans13Dichloropropene" label="反-1,3-二氯丙烯(μg/L)" width="130" />
      <el-table-column prop="cis13Dichloropropene" label="顺式-1,3-二氯丙烯(μg/L)" width="130" />

      <el-table-column prop="cis13Dichloropropene" label="顺-1,3-二氯丙烯(μg/L)" width="130" />
      <el-table-column prop="dichloropropane22" label="2,2-二氯丙烷(μg/L)" width="130" />
      <el-table-column prop="dibromoethane12" label="1,2-二溴乙烷(μg/L)" width="130" />
      <el-table-column prop="dichloropropane13" label="1,3-二氯丙烷(μg/L)" width="150" />
      <el-table-column prop="chlorotoluene2" label="2-氯甲苯(μg/L)" width="130" />
      <el-table-column prop="chlorotoluene4" label="4-氯甲苯(μg/L)" width="130" />

      <el-table-column prop="dichlorobenzene13" label="1,3-二氯苯(μg/L)" width="130" />
      <el-table-column prop="trichlorobenzene123" label="1,2,3-三氯苯(μg/L)" width="150" />
    </el-table>
  </el-dialog>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick, watch } from "vue";
  import * as echarts from "echarts";
  import { Search } from '@element-plus/icons-vue'
  import { listMonitoring } from "@/api/admin/monitoring";
  import { listIndicators } from "@/api/admin/indicators";

  let lineChart = null;
  let lineOption = {};
  const selectValue = ref("pH");
  const timeValue = ref();
  const dialogVisible = ref(false);
  const chart = defineProps(["chartTitle", 'chartType']);
  const options1 = [
    {
      value: "phValue",
      label: "pH",
    },
    {
      value: "temperature",
      label: "温度",
    },
    {
      value: "waterLevel",
      label: "水位",
    },
    // {
    //   value: "4",
    //   label: "电位",
    // },
    {
      value: "dissolvedOxygen",
      label: "溶解氧", // 建议更具体的描述
    },
    {
      value: "conductivity",
      label: "电导率",
    },
    {
      value: "ammoniaNitrogen",
      label: "氨氮",
    }
  ]

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
  const indicatorsOption = ref([])
  const tableData = ref([]);
  const tableInfo = ref([]);

  defineExpose({ dialogVisible, tableData, tableInfo });

  onMounted(() => {
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
  // 重新渲染折线图
  function seekLineData(data, time) {
    lineOption.xAxis[0].data = time;
    lineOption.series[0].data = data;
    lineChart.setOption(lineOption);
  }

  // 切换指标
  function changeSelect(label) {
    let data = [];
    let time = tableData.value.map((ele) => chart.chartType === 'monitor' ? ele.monitoringTime : ele.sampleTime);
    tableData.value.forEach((ele) => {
      data.push(ele[label]);
    });

    seekLineData(data, time);
  }

  // 关闭弹窗
  function closeChartLine() {
    const elements = document.getElementsByClassName('RightLine');
    if (elements[0]) {
      elements[0].style.zIndex = 20
    }

    const elements1 = document.getElementsByClassName('RightCenter');
    if (elements1[0]) {
      elements1[0].style.zIndex = 20
    }
  }

  // 打开弹窗
  function openChartLine() {
    selectValue.value = "pH";
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
    changeSelect(chart.chartType === 'monitor' ? 'phValue' : 'ph')
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