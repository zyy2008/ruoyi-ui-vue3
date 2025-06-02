<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="">
      <span>
        指标标准
      </span>
    </div>
    <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column type="index" label="序号" width="55" />
      <el-table-column prop="name" label="指标类型" />
      <el-table-column prop="address" label="标准值" />
      <el-table-column prop="name1" label="是否国标" />
      <el-table-column prop="address1" label="是否启用" />
    </el-table>
  </div>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick } from "vue";
  const tableData = [
    {
      date: '检测井1',
      name: 'PH',
      address: '6.7',
      name1: '是',
    },
    {
      date: '检测井2',
      name: '温度',
      address: '42',
      name1: '是',
    },
    {
      date: '检测井3',
      name: '水位',
      address: '10',
      name1: '是',
    },
    {
      date: '检测井4',
      name: '电位',
      address: '60',
      name1: '是',
    },
    {
      date: '检测井5',
      name: '溶解氧',
      address: '6.7',
      name1: '是',
    },
    {
      date: '检测井6',
      name: '电导率',
      address: '9.8',
      name1: '是',
    },
    {
      date: '检测井6',
      name: '氨氮',
      address: '30',
      name1: '是',
    },
  ]
</script>

<style lang="scss" scoped>
  .table {
    width: 604px;
    padding: 10px;
    background: url('@/assets/static/left.png') no-repeat;
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