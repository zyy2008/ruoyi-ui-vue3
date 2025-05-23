<template>
  <el-drawer
    modal-class="online-monitor-drawer-root"
    v-model="visible"
    title="在线监测统计"
    direction="btt"
    :with-header="false"
    :modal="false"
    size="440px"
    :show-close="true"
    :append-to-body="false"
  >
    <div class="online-monitor-grid">
      <el-card class="monitor-card" shadow="hover">
        <div class="card-title">实时数据</div>
        <div class="card-content">
          <el-table
            ref="realtimeTableRef"
            :data="realtimeData"
            border
            size="small"
            style="width: 900px; min-width: 900px"
            :header-cell-style="{ whiteSpace: 'nowrap' }"
            height="320"
          >
            <el-table-column prop="index" label="序号" width="60" />
            <el-table-column prop="code" label="站点编号" width="100" />
            <el-table-column prop="name" label="站点名称" width="80" />
            <el-table-column prop="level" label="站点等级" width="90" />
            <el-table-column prop="type" label="站点类型" width="110" />
            <el-table-column prop="lng" label="站点经度" width="130" />
            <el-table-column prop="lat" label="站点纬度" width="130" />
            <el-table-column prop="company" label="所在企业" width="220" />
          </el-table>
        </div>
      </el-card>
      <el-card class="monitor-card" shadow="hover">
        <div class="card-title">周数据趋势</div>
        <div style="margin-bottom: 8px">
          <el-button-group>
            <el-button
              size="small"
              :type="selectedTypeWeek === '氟化物' ? 'primary' : 'default'"
              @click="selectedTypeWeek = '氟化物'"
              >氟化物</el-button
            >
            <el-button
              size="small"
              :type="selectedTypeWeek === '石油类' ? 'primary' : 'default'"
              @click="selectedTypeWeek = '石油类'"
              >石油类</el-button
            >
            <el-button
              size="small"
              :type="selectedTypeWeek === '硫化物' ? 'primary' : 'default'"
              @click="selectedTypeWeek = '硫化物'"
              >硫化物</el-button
            >
          </el-button-group>
        </div>
        <div class="card-content chart-content">
          <v-chart
            :option="weekChartOption"
            autoresize
            style="width: 100%; height: 250px; flex: 1; min-height: 0"
          />
        </div>
      </el-card>
      <el-card class="monitor-card" shadow="hover">
        <div class="card-title">月数据趋势</div>
        <div style="margin-bottom: 8px">
          <el-button-group>
            <el-button
              size="small"
              :type="selectedTypeMonth === '氟化物' ? 'primary' : 'default'"
              @click="selectedTypeMonth = '氟化物'"
              >氟化物</el-button
            >
            <el-button
              size="small"
              :type="selectedTypeMonth === '石油类' ? 'primary' : 'default'"
              @click="selectedTypeMonth = '石油类'"
              >石油类</el-button
            >
            <el-button
              size="small"
              :type="selectedTypeMonth === '硫化物' ? 'primary' : 'default'"
              @click="selectedTypeMonth = '硫化物'"
              >硫化物</el-button
            >
          </el-button-group>
        </div>
        <div class="card-content chart-content">
          <v-chart
            :option="monthChartOption"
            autoresize
            style="width: 100%; height: 250px; flex: 1; min-height: 0"
          />
        </div>
      </el-card>
      <el-card class="monitor-card" shadow="hover">
        <div class="card-title">监控画面</div>
        <div
          class="card-content"
          style="display: flex; align-items: center; justify-content: center"
        >
          <video
            src="https://www.w3schools.com/html/mov_bbb.mp4"
            controls
            style="width: 440px"
          >
            您的浏览器不支持 video 标签。
          </video>
        </div>
      </el-card>
      <!-- <el-card class="monitor-card" shadow="hover">
        <div class="card-title">生态环境局信息</div>
        <div class="card-content">[生态环境局信息展示模块]</div>
      </el-card>
      <el-card class="monitor-card" shadow="hover">
        <div class="card-title">企业信息动态</div>
        <div class="card-content">[企业信息动态展示模块]</div>
      </el-card> -->
    </div>
    <template #footer>
      <el-button @click="visible = false">关闭</el-button>
    </template>
  </el-drawer>
</template>

<script setup>
import {
  ref,
  defineExpose,
  onMounted,
  onBeforeUnmount,
  watch,
  computed,
} from "vue";
import VChart from "vue-echarts";
import * as echarts from "echarts/core";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
echarts.use([
  LineChart,
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  CanvasRenderer,
]);
const visible = ref(true);
const realtimeTableRef = ref(null);
const scrollInterval = ref(null);
const selectedTypeWeek = ref("氟化物");
const selectedTypeMonth = ref("氟化物");

const realtimeData = ref([
  {
    index: 1,
    code: "J15",
    name: "J15",
    level: "国控",
    type: "地下水",
    lng: "613086.463",
    lat: "4160651.33",
    company: "阳煤集团太原化工",
  },
  {
    index: 2,
    code: "J21",
    name: "J21",
    level: "国控",
    type: "地下水",
    lng: "611468.4",
    lat: "4159415.751",
    company: "清徐.泓博污水处理厂/美锦华盛焦化",
  },
  {
    index: 3,
    code: "J54",
    name: "J54",
    level: "国控",
    type: "地下水",
    lng: "610819.844",
    lat: "4160197.915",
    company: "清徐.泓博污水处理厂",
  },
  {
    index: 4,
    code: "J28-1",
    name: "J28-1",
    level: "国控",
    type: "地下水",
    lng: "613875.491",
    lat: "4161820.857",
    company: "榆阳焦化（拆除）",
  },
  {
    index: 5,
    code: "J45",
    name: "J45",
    level: "国控",
    type: "地下水",
    lng: "612689.383",
    lat: "4161849.331",
    company: "煜昱源新材料",
  },
  {
    index: 6,
    code: "J47",
    name: "J47",
    level: "国控",
    type: "地下水",
    lng: "614965.481",
    lat: "4161951.481",
    company: "榆阳新能源",
  },
  {
    index: 7,
    code: "J55",
    name: "J55",
    level: "国控",
    type: "地下水",
    lng: "609365.288",
    lat: "4161045.761",
    company: "铸造（美锦钢铁）",
  },
  {
    index: 8,
    code: "J56-1",
    name: "J56-1",
    level: "国控",
    type: "地下水",
    lng: "610808.286",
    lat: "4160925.857",
    company: "化工（高台华泰）",
  },
  {
    index: 9,
    code: "J69",
    name: "J69",
    level: "国控",
    type: "地下水",
    lng: "612031.129",
    lat: "4160907.85",
    company: "榆阳煤炭",
  },
  {
    index: 10,
    code: "J71",
    name: "J71",
    level: "国控",
    type: "地下水",
    lng: "609325.77",
    lat: "4161045.761",
    company: "科斯姆",
  },
  {
    index: 11,
    code: "J97",
    name: "J97",
    level: "国控",
    type: "地下水",
    lng: "610848.67",
    lat: "4159691.16",
    company: "黑猫焦黑储罐区",
  },
  {
    index: 12,
    code: "J103",
    name: "J103",
    level: "国控",
    type: "地下水",
    lng: "611756.161",
    lat: "4159830.83",
    company: "公共区域",
  },
  {
    index: 13,
    code: "J113",
    name: "J113",
    level: "国控",
    type: "地下水",
    lng: "611563.61",
    lat: "4159863.81",
    company: "山西三强新能源科技有限公司",
  },
  {
    index: 14,
    code: "J119",
    name: "J119",
    level: "国控",
    type: "地下水",
    lng: "610104.83",
    lat: "4161332.67",
    company: "山西三强新能源科技有限公司日/充电站",
  },
  {
    index: 15,
    code: "J115",
    name: "J115",
    level: "国控",
    type: "地下水",
    lng: "610104.83",
    lat: "4161332.67",
    company: "美锦煤焦燃区",
  },
  {
    index: 16,
    code: "J131",
    name: "J131",
    level: "国控",
    type: "地下水",
    lng: "610104.83",
    lat: "4160353.852",
    company: "亚新煤焦化西侧",
  },
  {
    index: 17,
    code: "J135",
    name: "J135",
    level: "国控",
    type: "地下水",
    lng: "615459.023",
    lat: "4160486.071",
    company: "美锦焦化下游",
  },
  {
    index: 18,
    code: "J120",
    name: "J120",
    level: "国控",
    type: "地下水",
    lng: "615630.786",
    lat: "4159472.691",
    company: "中成药生产（迈迪制药）",
  },
]);

const weekChartOption = ref({
  title: {
    text: "最近一周监测数据",
    left: "center",
    top: 10,
    textStyle: { fontSize: 16 },
  },
  tooltip: { trigger: "axis" },
  grid: { left: 30, right: 20, bottom: 30, top: 50 },
  xAxis: {
    type: "category",
    data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    axisLabel: { fontSize: 13 },
  },
  yAxis: {
    type: "value",
    axisLabel: { fontSize: 13 },
  },
  series: [
    {
      name: "监测值",
      type: "line",
      data: [23, 25, 22, 28, 27, 26, 30],
      smooth: true,
      symbol: "circle",
      symbolSize: 8,
      lineStyle: { width: 3 },
      itemStyle: { color: "#409EFF" },
      areaStyle: { color: "rgba(64,158,255,0.15)" },
    },
  ],
});

const monthChartOption = ref({
  title: {
    text: "最近一个月监测数据",
    left: "center",
    top: 10,
    textStyle: { fontSize: 16 },
  },
  tooltip: { trigger: "axis" },
  grid: { left: 30, right: 20, bottom: 30, top: 50 },
  xAxis: {
    type: "category",
    data: Array.from({ length: 30 }, (_, i) => `${i + 1}日`),
    axisLabel: { fontSize: 12, interval: 2 },
  },
  yAxis: {
    type: "value",
    axisLabel: { fontSize: 13 },
  },
  series: [
    {
      name: "监测值",
      type: "line",
      data: Array.from(
        { length: 30 },
        () => Math.floor(Math.random() * 10) + 20
      ), // 20~29的模拟数据
      smooth: true,
      symbol: "circle",
      symbolSize: 8,
      lineStyle: { width: 3 },
      itemStyle: { color: "#67C23A" },
      areaStyle: { color: "rgba(103,194,58,0.15)" },
    },
  ],
});

const weekDataMap = {
  氟化物: [23, 25, 22, 28, 27, 26, 30],
  石油类: [12, 15, 14, 18, 17, 16, 19],
  硫化物: [5, 7, 6, 8, 7, 6, 9],
};
const monthDataMap = {
  氟化物: Array.from(
    { length: 30 },
    (_, i) => 20 + Math.floor(Math.random() * 10)
  ),
  石油类: Array.from(
    { length: 30 },
    (_, i) => 10 + Math.floor(Math.random() * 10)
  ),
  硫化物: Array.from(
    { length: 30 },
    (_, i) => 5 + Math.floor(Math.random() * 5)
  ),
};

watch(selectedTypeWeek, (val) => {
  weekChartOption.value.series[0].name = val;
  weekChartOption.value.series[0].data = weekDataMap[val];
});
watch(selectedTypeMonth, (val) => {
  monthChartOption.value.series[0].name = val;
  monthChartOption.value.series[0].data = monthDataMap[val];
});

// 初始化时也赋值
weekChartOption.value.series[0].name = selectedTypeWeek.value;
weekChartOption.value.series[0].data = weekDataMap[selectedTypeWeek.value];
monthChartOption.value.series[0].name = selectedTypeMonth.value;
monthChartOption.value.series[0].data = monthDataMap[selectedTypeMonth.value];

defineExpose({ visible });

function startAutoScroll() {
  stopAutoScroll();
  scrollInterval.value = setInterval(() => {
    const tableBody = realtimeTableRef.value?.$el?.querySelector(
      ".el-scrollbar__wrap"
    );
    if (tableBody) {
      // 每次向下滚动1行高度
      tableBody.scrollTop += 30;
      // 到底后回到顶部
      if (
        tableBody.scrollTop + tableBody.clientHeight >=
        tableBody.scrollHeight - 1
      ) {
        setTimeout(() => {
          tableBody.scrollTop = 0;
        }, 500);
      }
    }
  }, 1200);
}
function stopAutoScroll() {
  if (scrollInterval.value) {
    clearInterval(scrollInterval.value);
    scrollInterval.value = null;
  }
}

watch(visible, (val) => {
  if (val) {
    setTimeout(startAutoScroll, 500);
  } else {
    stopAutoScroll();
  }
});
onBeforeUnmount(stopAutoScroll);
</script>

<style scoped>
.online-monitor-drawer-root {
  pointer-events: none;
}
.online-monitor-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 24px;
  padding: 12px;
}

.card-title {
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 10px;
  color: #409eff;
}
.card-content {
  flex: 1;
  color: #666;
  font-size: 15px;
  display: flex;
  flex-direction: column;
  min-height: 0;
}
.chart-content {
  padding: 0;
}
</style>

<style>
.online-monitor-drawer-root {
  pointer-events: none;
}

.online-monitor-drawer-root > div {
  pointer-events: all;
}
.monitor-card {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  height: 340px;
}
</style>