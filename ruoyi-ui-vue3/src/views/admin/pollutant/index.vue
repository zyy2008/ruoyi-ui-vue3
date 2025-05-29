<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['admin:pollutant:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:pollutant:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:pollutant:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:pollutant:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pollutantList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="关联的土壤监测记录ID" align="center" prop="monitoringId" />
      <el-table-column label="超标污染物名称" align="center" prop="pollutantName" />
      <el-table-column label="最大浓度" align="center" prop="maxConcentrationMgkg" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:pollutant:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:pollutant:remove']">删除</el-button>
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

    <!-- 添加或修改土壤监测发现的超标污染物信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pollutantRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="超标污染物名称" prop="pollutantName">
          <el-input v-model="form.pollutantName" type="textarea" placeholder="请输入内容" />
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

<script setup name="Pollutant">
import { listPollutant, getPollutant, delPollutant, addPollutant, updatePollutant } from "@/api/admin/pollutant";

const { proxy } = getCurrentInstance();

const pollutantList = ref([]);
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
    pollutantName: null,
    maxConcentrationMgkg: null,
    deptId: null
  },
  rules: {
    pollutantName: [
      { required: true, message: "超标污染物名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询土壤监测发现的超标污染物信息列表 */
function getList() {
  loading.value = true;
  listPollutant(queryParams.value).then(response => {
    pollutantList.value = response.rows;
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
    monitoringId: null,
    pollutantName: null,
    maxConcentrationMgkg: null,
    deptId: null
  };
  proxy.resetForm("pollutantRef");
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
  title.value = "添加土壤监测发现的超标污染物信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPollutant(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改土壤监测发现的超标污染物信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pollutantRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePollutant(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPollutant(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除土壤监测发现的超标污染物信息编号为"' + _ids + '"的数据项？').then(function() {
    return delPollutant(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/pollutant/export', {
    ...queryParams.value
  }, `pollutant_${new Date().getTime()}.xlsx`)
}

getList();
</script>
