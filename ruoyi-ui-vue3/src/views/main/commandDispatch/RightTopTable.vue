<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> 指标标准 </span>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="name" label="指标类型" />
      <el-table-column prop="address" width="160" label="标准值" />
      <el-table-column prop="name1" label="是否国标" />
    </el-table>
  </div>
</template>
<script setup>
import { onMounted, reactive, toRaw, ref, nextTick } from "vue";

// 色度：≤25；
// 浑浊度：≤10；
// pH 值： 5.5-6.5 或 8.5-9.0；
// 总硬度：≤650；
// 溶解性总固体 ≤2000；
// 硫酸盐  ≤350；
// 氯化物 ≤350；
// 氨氮 ≤1.50
const tableData = [
  {
    date: "检测井1",
    name: "色度",
    address: "≤25",
    name1: "是",
    address1: "是",
  },
  {
    date: "检测井2",
    name: "浑浊度",
    address: "≤10",
    name1: "是",
    address1: "是",
  },
  {
    date: "检测井3",
    name: "pH值",
    address: "5.5-6.5 或 8.5-9.0",
    name1: "是",
    address1: "是",
  },
  {
    date: "检测井4",
    name: "总硬度",
    address: "≤650",
    name1: "是",
    address1: "是",
  },
  {
    date: "检测井5",
    name: "溶解性总固体",
    address: "≤2000",
    name1: "是",
    address1: "是",
  },
  {
    date: "检测井6",
    name: "硫酸盐",
    address: "≤350",
    name1: "是",
    address1: "是",
  },
  {
    date: "检测井6",
    name: "氯化物",
    address: "≤350",
    name1: "是",
    address1: "是",
  },
    {
    date: "检测井6",
    name: "氨氮",
    address: "≤1.50",
    name1: "是",
    address1: "是",
  }
];


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

    span {
      font-size: 18px;
      display: inline-block;
    }
  }
}
</style>