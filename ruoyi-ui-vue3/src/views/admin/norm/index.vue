<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" maxlength="30" />
      </el-form-item>
      <el-form-item label="代码" prop="code">
        <el-input v-model="queryParams.code" placeholder="请输入代码" maxlength="30" />
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
          v-hasPermi="['admin:norm:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:norm:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:norm:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:norm:export']"
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
      :data="normList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="要素大类ID" align="center" prop="elmentId" />
      <el-table-column label="编码规范名称" align="center" prop="name" />
      <el-table-column label="代码" align="center" prop="code" />
      <el-table-column label="几何特征" align="center" prop="geometricFeatures" />
      <el-table-column label="属性表" align="center" prop="attribute" />
      <el-table-column label="颜色" align="center" prop="color" />
      <el-table-column label="RGB值" align="center" prop="rgb" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:norm:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:norm:remove']"
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

    <!-- 添加或修改要素分类编码规范对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="normRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="编码规范名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="要素大类" prop="elmentId">
          <el-select v-model="form.elmentId" placeholder="请选择">
            <el-option
              v-for="dict in checkedTElementClassification"
              :key="dict.id"
              :label="dict.name"
              :value="dict.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="代码" prop="code">
          <el-input v-model="form.code" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="几何特征" prop="geometricFeatures">
          <el-input
            v-model="form.geometricFeatures"
            placeholder="请输入"
            maxlength="30"
          />
        </el-form-item>
        <el-form-item label="属性表" prop="attribute">
          <el-input
            v-model="form.attribute"
            placeholder="请输入访问地址"
            maxlength="255"
          />
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="form.color" placeholder="请输入访问地址" maxlength="255" />
        </el-form-item>
        <el-form-item label="RGB值" prop="rgb">
          <el-input v-model="form.rgb" placeholder="请输入访问地址" maxlength="255" />
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

<script setup name="Norm">
import { listNorm, getNorm, delNorm, addNorm, updateNorm } from "@/api/admin/norm";
import { listClassification } from "@/api/admin/classification";
const { proxy } = getCurrentInstance();

const normList = ref([]);
const tElementClassificationList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedTElementClassification = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    code: null,
    geometricFeatures: null,
    attribute: null,
    color: null,
    rgb: null,
    elmentId: null,
  },
  rules: {
    name: [{ required: true, message: "编码规范名称不能为空", trigger: "blur" }],
    elmentId: [{ required: true, message: "要素大类ID不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询要素分类编码规范列表 */
function getList() {
  loading.value = true;
  listNorm(queryParams.value).then((response) => {
    normList.value = response.rows;
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
    name: null,
    code: null,
    geometricFeatures: null,
    attribute: null,
    color: null,
    rgb: null,
    elmentId: null,
  };
  tElementClassificationList.value = [];
  proxy.resetForm("normRef");
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
  title.value = "添加要素分类编码规范";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getNorm(_id).then((response) => {
    form.value = response.data;
    tElementClassificationList.value = response.data.tElementClassificationList;
    open.value = true;
    title.value = "修改要素分类编码规范";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["normRef"].validate((valid) => {
    if (valid) {
      form.value.tElementClassificationList = tElementClassificationList.value;
      if (form.value.id != null) {
        updateNorm(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addNorm(form.value).then((response) => {
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
    .confirm('是否确认删除要素分类编码规范编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delNorm(_ids);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
}

/** 要素分类序号 */
function rowTElementClassificationIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 要素分类添加按钮操作 */
function handleAddTElementClassification() {
  let obj = {};
  obj.name = "";
  obj.pipelineCategorize = "";
  obj.code = "";
  tElementClassificationList.value.push(obj);
}

/** 要素分类删除按钮操作 */
function handleDeleteTElementClassification() {
  if (checkedTElementClassification.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的要素分类数据");
  } else {
    const tElementClassifications = tElementClassificationList.value;
    const checkedTElementClassifications = checkedTElementClassification.value;
    tElementClassificationList.value = tElementClassifications.filter(function (item) {
      return checkedTElementClassifications.indexOf(item.index) == -1;
    });
  }
}

/** 复选框选中数据 */
function handleTElementClassificationSelectionChange(selection) {
  checkedTElementClassification.value = selection.map((item) => item.index);
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "admin/norm/export",
    {
      ...queryParams.value,
    },
    `norm_${new Date().getTime()}.xlsx`
  );
}
function getlistClassification() {
  listClassification({
    pageNum: 1,
    pageSize: 100,
  }).then((response) => {
    checkedTElementClassification.value = response.rows;
  });
}
getlistClassification();
getList();
</script>
