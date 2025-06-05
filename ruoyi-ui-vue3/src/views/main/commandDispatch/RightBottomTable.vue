<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> 报警列表 </span>
    </div>
    <el-table
      :data="tableData"
      height="280"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <!-- <el-table-column type="index" label="序号" width="55" /> -->
      <el-table-column  prop="point_id" label="监测井" />
      <el-table-column width="70" prop="zb" label="指标" />
      <!-- <el-table-column prop="bzz" label="标准值" /> -->
      <el-table-column width="110" prop="sample_time" label="监测时间" />
      <el-table-column  width="70" prop="jcz" label="监测值" />
      <!-- <el-table-column  prop="cbbl" label="超标倍率" /> -->
           <el-table-column prop="cbbl" label="超标倍率">
      <template v-slot="scope">
        <span style="color: red;">
          {{ scope.row.cbbl }}
        </span>
      </template>
    </el-table-column>
    </el-table>
  </div>
</template>
<script setup>
import { onMounted, reactive, toRaw, ref, nextTick } from "vue";
import { selectGJJL } from "@/api/monitoring";
const tableData = ref([]);
function initList() {
  selectGJJL().then((res) => {
    tableData.value = res.data;
  });
}
initList();
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