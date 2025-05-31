<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="使用年代" prop="usageYear">
        <el-input
          v-model="queryParams.usageYear"
          placeholder="请输入使用年代"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['admin:rawMaterials:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:rawMaterials:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:rawMaterials:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:rawMaterials:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rawMaterialsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="原辅材料名称" align="center" prop="materialName" />
      <el-table-column label="使用年代" align="center" prop="usageYear" />
      <el-table-column label="计量单位" align="center" prop="unit" />
      <el-table-column label="年平均用量" align="center" prop="annualUsage" />
      <el-table-column label="数据来源" align="center" prop="dataSource" />
      <el-table-column label="添加人" align="center" prop="createdBy" />
      <el-table-column label="添加时间" align="center" prop="createdAt" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:rawMaterials:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:rawMaterials:remove']">删除</el-button>
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

    <!-- 添加或修改原辅材料信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="rawMaterialsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="原辅材料名称" prop="materialName">
          <el-input v-model="form.materialName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="使用年代" prop="usageYear">
          <el-input v-model="form.usageYear" placeholder="请输入使用年代" />
        </el-form-item>
        <el-form-item label="计量单位" prop="unit">
          <el-input v-model="form.unit" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="数据来源" prop="dataSource">
          <el-input v-model="form.dataSource" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="添加人" prop="createdBy">
          <el-input v-model="form.createdBy" type="textarea" placeholder="请输入内容" />
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

<script setup name="RawMaterials">
import { listRawMaterials, getRawMaterials, delRawMaterials, addRawMaterials, updateRawMaterials } from "@/api/admin/rawMaterials";

const { proxy } = getCurrentInstance();

const rawMaterialsList = ref([]);
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
    materialName: null,
    usageYear: null,
    unit: null,
    annualUsage: null,
    dataSource: null,
    createdBy: null,
    createdAt: null,
    deptId: null
  },
  rules: {
    materialName: [
      { required: true, message: "原辅材料名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询原辅材料信息列表 */
function getList() {
  loading.value = true;
  listRawMaterials(queryParams.value).then(response => {
    rawMaterialsList.value = response.rows;
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
    materialName: null,
    usageYear: null,
    unit: null,
    annualUsage: null,
    dataSource: null,
    createdBy: null,
    createdAt: null,
    deptId: null
  };
  proxy.resetForm("rawMaterialsRef");
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
  title.value = "添加原辅材料信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getRawMaterials(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改原辅材料信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["rawMaterialsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateRawMaterials(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRawMaterials(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除原辅材料信息编号为"' + _ids + '"的数据项？').then(function() {
    return delRawMaterials(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/rawMaterials/export', {
    ...queryParams.value
  }, `rawMaterials_${new Date().getTime()}.xlsx`)
}

getList();
</script>
