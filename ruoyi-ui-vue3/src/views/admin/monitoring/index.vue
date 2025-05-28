<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="调查时间" prop="surveyDate">
        <el-date-picker clearable
          v-model="queryParams.surveyDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择调查时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="归属部门" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入归属部门"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['admin:monitoring:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:monitoring:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:monitoring:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:monitoring:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="monitoringList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="是否曾开展土壤环境调查监测" align="center" prop="hasMonitoring" />
      <el-table-column label="调查时间" align="center" prop="surveyDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.surveyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否发现超标污染物" align="center" prop="pollutantsExceed" />
      <el-table-column label="数据来源" align="center" prop="source" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:monitoring:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:monitoring:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改土壤环境调查监测信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="monitoringRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="调查时间" prop="surveyDate">
          <el-date-picker clearable
            v-model="form.surveyDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择调查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="数据来源" prop="source">
          <el-input v-model="form.source" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="归属部门" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入归属部门" />
        </el-form-item>
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

<script setup name="Monitoring">
import { listMonitoring, getMonitoring, delMonitoring, addMonitoring, updateMonitoring } from "@/api/admin/monitoring";

const { proxy } = getCurrentInstance();

const monitoringList = ref([]);
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
    hasMonitoring: null,
    surveyDate: null,
    pollutantsExceed: null,
    source: null,
    deptId: null
  },
  rules: {
    hasMonitoring: [
      { required: true, message: "是否曾开展土壤环境调查监测不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询土壤环境调查监测信息列表 */
function getList() {
  loading.value = true;
  listMonitoring(queryParams.value).then(response => {
    monitoringList.value = response.rows;
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
    hasMonitoring: null,
    surveyDate: null,
    pollutantsExceed: null,
    source: null,
    deptId: null
  };
  proxy.resetForm("monitoringRef");
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
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加土壤环境调查监测信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMonitoring(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改土壤环境调查监测信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["monitoringRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMonitoring(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMonitoring(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除土壤环境调查监测信息编号为"' + _ids + '"的数据项？').then(function() {
    return delMonitoring(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/monitoring/export', {
    ...queryParams.value
  }, `monitoring_${new Date().getTime()}.xlsx`)
}

getList();
</script>
