<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="">
      <span>
        实时数据
      </span>
    </div>
    <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column type="index" width="50" />
      <el-table-column prop="code" label="名称" width="60" />
      <el-table-column prop="PH" label="PH" width="60" />
      <el-table-column prop="WD" label="温度" width="60" />
      <el-table-column prop="SW" label="水位" width="60" />
      <el-table-column prop="DW" label="电位" />
      <el-table-column prop="RJY" label="溶解氧" width="70" />
      <el-table-column prop="DDL" label="电导率" width="70" />
      <el-table-column prop="AD" label="氨氮" width="60" />
      <el-table-column prop="lng" label="位置" width="60" />
    </el-table>
  </div>

</template>
<script setup>

  const tableData = ref([
    {
      index: 1,
      code: "J15",
      WD: '60°',
      SW: "60",
      PH: '7',
      DW: '20',
      RJY: '15',
      DDL: '8',
      AD: '30',
      lng: "680.8",
    },
    {
      index: 2,
      code: "J21",
      WD: '50°',
      SW: "40",
      PH: '8.6',
      DW: '10',
      RJY: '35',
      DDL: '80',
      AD: '14',
      lng: "650.8",
    },
    {
      index: 3,
      code: "J54",
      WD: '58°',
      SW: "40",
      PH: '8',
      DW: '10',
      RJY: '13',
      DDL: '12',
      AD: '20',
      lng: "610.8",
    },
    {
      index: 4,
      code: "J28-1",
      WD: '60°',
      SW: "60",
      PH: '7',
      DW: '20',
      RJY: '15',
      DDL: '8',
      AD: '30',
      lng: "610.8",
    },
    {
      index: 5,
      code: "J45",
      WD: '44°',
      SW: "55",
      PH: '8.6',
      DW: '60',
      RJY: '15',
      DDL: '11',
      AD: '31',
      lng: "640.3",
    },
    {
      index: 6,
      code: "J47",
      WD: '30°',
      SW: "40",
      PH: '7.5',
      DW: '28',
      RJY: '12',
      DDL: '17',
      AD: '20',
      lng: "610.8",
    },
  ]);
</script>

<style lang="scss" scoped>
  .table {
    width: 604px;
    padding: 10px;
    background: url('@/assets/static/left.png') no-repeat;
    /* background-size: 100% 100%; */

    .title {
      height: 42px;
      line-height: 42px;
      display: flex;

      span {
        font-size: 18px;
        display: inline-block;
      }
    }
  }
</style>