<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="">
      <span>
        报警列表
      </span>
    </div>
    <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column type="index" label="序号" width="55" />
      <el-table-column prop="date" label="监测井" />
      <el-table-column prop="name" label="指标参数" width="55" />
      <el-table-column prop="address" label="标准值" />
      <el-table-column prop="name1" label="监测值" />
      <el-table-column prop="address1" label="超标倍率" />
    </el-table>
  </div>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick } from "vue";
  const tableData = []

  nextTick(() => {
    setTimeout(() => {
      var text = document.getElementsByClassName('el-table__empty-text');
      text[0].innerHTML = '暂无报警'
    }, 50);
  })
  // const tableData = [
  //   {
  //     date: '检测井1',
  //     name: 'PH',
  //     address: '6.7',
  //     name1: '7',
  //     address1: '105%',
  //   },
  //   {
  //     date: '检测井2',
  //     name: '温度',
  //     address: '42',
  //     name1: '30',
  //     address1: '70%',
  //   },
  //   {
  //     date: '检测井3',
  //     name: '氨氮',
  //     address: '1',
  //     name1: '0.8',
  //     address1: '80%',
  //   },
  //   {
  //     date: '检测井4',
  //     name: '水位',
  //     address: '60',
  //     name1: '80',
  //     address1: '120%',
  //   },
  //    {
  //     date: '检测井5',
  //     name: 'PH',
  //     address: '6.7',
  //     name1: '7',
  //     address1: '105%',
  //   },
  //        {
  //     date: '检测井6',
  //     name: '水位',
  //     address: '60',
  //     name1: '40',
  //     address1: '80%',
  //   },
  // ]
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