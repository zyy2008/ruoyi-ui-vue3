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
          v-hasPermi="['admin:pathway:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:pathway:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:pathway:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:pathway:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pathwayList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="地下水埋深" align="center" prop="waterDepthCategory" />
      <el-table-column label="含水层渗透性分类" align="center" prop="permeabilityCategory" />
      <el-table-column label="是否属于喀斯特地貌" align="center" prop="isKarstArea" />
      <el-table-column label="年降雨量范围" align="center" prop="annualRainfallCategory" />
      <el-table-column label="地下水用途" align="center" prop="groundwaterUse" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:pathway:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:pathway:remove']">删除</el-button>
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

    <!-- 添加或修改地下水迁移途径信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pathwayRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="地下水埋深" prop="waterDepthCategory">
          <el-input v-model="form.waterDepthCategory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="含水层渗透性分类" prop="permeabilityCategory">
          <el-input v-model="form.permeabilityCategory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="年降雨量范围" prop="annualRainfallCategory">
          <el-input v-model="form.annualRainfallCategory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="地下水用途" prop="groundwaterUse">
          <el-input v-model="form.groundwaterUse" type="textarea" placeholder="请输入内容" />
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

<script setup name="Pathway">
import { listPathway, getPathway, delPathway, addPathway, updatePathway } from "@/api/admin/pathway";

const { proxy } = getCurrentInstance();

const pathwayList = ref([]);
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
    waterDepthCategory: null,
    permeabilityCategory: null,
    isKarstArea: null,
    annualRainfallCategory: null,
    groundwaterUse: null,
    deptId: null
  },
  rules: {
    waterDepthCategory: [
      { required: true, message: "地下水埋深不能为空", trigger: "blur" }
    ],
    permeabilityCategory: [
      { required: true, message: "含水层渗透性分类不能为空", trigger: "blur" }
    ],
    isKarstArea: [
      { required: true, message: "是否属于喀斯特地貌不能为空", trigger: "blur" }
    ],
    annualRainfallCategory: [
      { required: true, message: "年降雨量范围不能为空", trigger: "blur" }
    ],
    groundwaterUse: [
      { required: true, message: "地下水用途不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询地下水迁移途径信息列表 */
function getList() {
  loading.value = true;
  listPathway(queryParams.value).then(response => {
    pathwayList.value = response.rows;
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
    waterDepthCategory: null,
    permeabilityCategory: null,
    isKarstArea: null,
    annualRainfallCategory: null,
    groundwaterUse: null,
    deptId: null
  };
  proxy.resetForm("pathwayRef");
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
  title.value = "添加地下水迁移途径信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPathway(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改地下水迁移途径信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pathwayRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePathway(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPathway(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除地下水迁移途径信息编号为"' + _ids + '"的数据项？').then(function() {
    return delPathway(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/pathway/export', {
    ...queryParams.value
  }, `pathway_${new Date().getTime()}.xlsx`)
}

getList();
</script>
