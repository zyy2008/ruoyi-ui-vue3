<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产年代" prop="productionYear">
        <el-input
          v-model="queryParams.productionYear"
          placeholder="请输入生产年代"
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
          v-hasPermi="['admin:mainProducts:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:mainProducts:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:mainProducts:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:mainProducts:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mainProductsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="生产年代" align="center" prop="productionYear" />
      <el-table-column label="计量单位" align="center" prop="unit" />
      <el-table-column label="年平均产量" align="center" prop="annualOutput" />
      <el-table-column label="产品数据来源" align="center" prop="dataSource" />
      <el-table-column label="添加人" align="center" prop="createdBy" />
      <el-table-column label="添加时间" align="center" prop="createdAt" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:mainProducts:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:mainProducts:remove']">删除</el-button>
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

    <!-- 添加或修改主要产品信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="mainProductsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="生产年代" prop="productionYear">
          <el-input v-model="form.productionYear" placeholder="请输入生产年代" />
        </el-form-item>
        <el-form-item label="计量单位" prop="unit">
          <el-input v-model="form.unit" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="产品数据来源" prop="dataSource">
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

<script setup name="MainProducts">
import { listMainProducts, getMainProducts, delMainProducts, addMainProducts, updateMainProducts } from "@/api/admin/mainProducts";

const { proxy } = getCurrentInstance();

const mainProductsList = ref([]);
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
    productName: null,
    productionYear: null,
    unit: null,
    annualOutput: null,
    dataSource: null,
    createdBy: null,
    createdAt: null,
    deptId: null
  },
  rules: {
    productName: [
      { required: true, message: "产品名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询主要产品信息列表 */
function getList() {
  loading.value = true;
  listMainProducts(queryParams.value).then(response => {
    mainProductsList.value = response.rows;
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
    productName: null,
    productionYear: null,
    unit: null,
    annualOutput: null,
    dataSource: null,
    createdBy: null,
    createdAt: null,
    deptId: null
  };
  proxy.resetForm("mainProductsRef");
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
  title.value = "添加主要产品信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMainProducts(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改主要产品信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["mainProductsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMainProducts(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMainProducts(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除主要产品信息编号为"' + _ids + '"的数据项？').then(function() {
    return delMainProducts(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/mainProducts/export', {
    ...queryParams.value
  }, `mainProducts_${new Date().getTime()}.xlsx`)
}

getList();
</script>
