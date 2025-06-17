<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="监测井编号" prop="monitoringWell">
        <el-input
          v-model="queryParams.monitoringWell"
          placeholder="请输入监测井编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:realtimeWaterQualityMonitoring:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:realtimeWaterQualityMonitoring:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="realtimeWaterQualityMonitoringList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="监测井编号"
        align="center"
        prop="monitoringWell"
      />
      <el-table-column label="pH值" align="center" prop="phValue" />
      <el-table-column label="水温" align="center" prop="temperature" />
      <el-table-column label="水位高度(米)" align="center" prop="waterLevel" />
      <el-table-column label="溶解氧" align="center" prop="dissolvedOxygen" />
      <el-table-column label="电导率" align="center" prop="conductivity" />
      <el-table-column label="氨氮含量" align="center" prop="ammoniaNitrogen" />
      <el-table-column label="监测时间" align="center" prop="monitoringTime" />
      <el-table-column
        label="同步时间"
        align="center"
        prop="synchronizationTime"
      />

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="danger"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:realtimeWaterQualityMonitoring:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改实时数据监测对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form
        ref="realtimeWaterQualityMonitoringRef"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="RealtimeWaterQualityMonitoring">
import {
  listRealtimeWaterQualityMonitoring,
  getRealtimeWaterQualityMonitoring,
  delRealtimeWaterQualityMonitoring,
  addRealtimeWaterQualityMonitoring,
  updateRealtimeWaterQualityMonitoring,
} from "@/api/admin/realtime_water_quality_monitoring.js";

const { proxy } = getCurrentInstance();

const realtimeWaterQualityMonitoringList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    phValue: null,
    temperature: null,
    waterLevel: null,
    dissolvedOxygen: null,
    conductivity: null,
    ammoniaNitrogen: null,
    monitoringTime: null,
    synchronizationTime: null,
    monitoringWell: null,
  },
  rules: {},
});

const { queryParams, form, rules } = toRefs(data);

/** 查询实时数据监测列表 */
function getList() {
  loading.value = true;
  listRealtimeWaterQualityMonitoring(queryParams.value).then((response) => {
    realtimeWaterQualityMonitoringList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    phValue: null,
    temperature: null,
    waterLevel: null,
    dissolvedOxygen: null,
    conductivity: null,
    ammoniaNitrogen: null,
    monitoringTime: null,
    synchronizationTime: null,
    monitoringWell: null,
  };
  proxy.resetForm("realtimeWaterQualityMonitoringRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加实时数据监测";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getRealtimeWaterQualityMonitoring(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改实时数据监测";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["realtimeWaterQualityMonitoringRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateRealtimeWaterQualityMonitoring(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRealtimeWaterQualityMonitoring(form.value).then((response) => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal
    .confirm('是否确认删除实时数据监测编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delRealtimeWaterQualityMonitoring(_ids);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "admin/realtimeWaterQualityMonitoring/export",
    {
      ...queryParams.value,
    },
    `realtimeWaterQualityMonitoring_${new Date().getTime()}.xlsx`
  );
}

getList();
</script>
