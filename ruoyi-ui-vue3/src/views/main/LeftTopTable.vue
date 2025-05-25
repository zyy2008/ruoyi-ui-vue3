<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="">
      <span>
        实时数据
      </span>
    </div>
    <div>
      <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
        <el-table-column type="index" width="50" />
        <el-table-column prop="date" label="名称" />
        <el-table-column prop="name" label="PH" />
        <el-table-column prop="address" label="温度" />
        <el-table-column prop="date" label="水位" />
        <el-table-column prop="name" label="电位" />
        <el-table-column prop="address" label="溶解氧" />
        <el-table-column prop="date" label="电导率" />
        <el-table-column prop="name" label="氨氮" />
        <el-table-column prop="address" label="位置" />
      </el-table>
    </div>
  </div>

</template>
<script setup>

  const tableData = [
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'Tom',
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'Tom',
    },
    {
      date: '2016-05-04',
      name: 'Tom',
      address: 'Tom',
    },
    {
      date: '2016-05-01',
      name: 'Tom',
      address: 'Tom',
    },
  ]
</script>

<style lang="scss" scoped>
  .table {
    width: 464px;
    padding: 10px;
    background: url('@/assets/static/left.png') no-repeat;

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

  :deep() {
    .el-table tr {
      background-color: rgba(21, 85, 75, 0.2);
    }

    .el-table {
      --el-table-bg-color: none;
      --el-table-text-color: white;
      --el-table-border-color: none;
      /* --el-table-row-hover-bg-color: none */
    }

    .el-table .el-table__header-wrapper th,
    .el-table .el-table__fixed-header-wrapper th {
      background-color: #155548 !important;
      color: white;
    }

  }
</style>