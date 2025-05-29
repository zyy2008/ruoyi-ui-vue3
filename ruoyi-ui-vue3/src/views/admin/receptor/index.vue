<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属部门" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入所属部门"
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
          v-hasPermi="['admin:receptor:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:receptor:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:receptor:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:receptor:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receptorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="东侧用地类型" align="center" prop="surroundingLandEast" />
      <el-table-column label="南侧用地类型" align="center" prop="surroundingLandSouth" />
      <el-table-column label="西侧用地类型" align="center" prop="surroundingLandWest" />
      <el-table-column label="北侧用地类型" align="center" prop="surroundingLandNorth" />
      <el-table-column label="地块内职工人数分类" align="center" prop="employeeCountCategory" />
      <el-table-column label="500m范围内常驻人口数量分类" align="center" prop="permanentPopulationCategory" />
      <el-table-column label="1km范围内敏感目标及距离分类" align="center" prop="sensitiveTargets" />
      <el-table-column label="所属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:receptor:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:receptor:remove']">删除</el-button>
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

    <!-- 添加或修改地块及周边敏感受体信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="receptorRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="东侧用地类型" prop="surroundingLandEast">
          <el-input v-model="form.surroundingLandEast" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="南侧用地类型" prop="surroundingLandSouth">
          <el-input v-model="form.surroundingLandSouth" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="西侧用地类型" prop="surroundingLandWest">
          <el-input v-model="form.surroundingLandWest" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="北侧用地类型" prop="surroundingLandNorth">
          <el-input v-model="form.surroundingLandNorth" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="地块内职工人数分类" prop="employeeCountCategory">
          <el-input v-model="form.employeeCountCategory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="500m范围内常驻人口数量分类" prop="permanentPopulationCategory">
          <el-input v-model="form.permanentPopulationCategory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="所属部门" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入所属部门" />
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

<script setup name="Receptor">
import { listReceptor, getReceptor, delReceptor, addReceptor, updateReceptor } from "@/api/admin/receptor";

const { proxy } = getCurrentInstance();

const receptorList = ref([]);
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
    surroundingLandEast: null,
    surroundingLandSouth: null,
    surroundingLandWest: null,
    surroundingLandNorth: null,
    employeeCountCategory: null,
    permanentPopulationCategory: null,
    sensitiveTargets: null,
    deptId: null
  },
  rules: {
    employeeCountCategory: [
      { required: true, message: "地块内职工人数分类不能为空", trigger: "blur" }
    ],
    permanentPopulationCategory: [
      { required: true, message: "500m范围内常驻人口数量分类不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询地块及周边敏感受体信息列表 */
function getList() {
  loading.value = true;
  listReceptor(queryParams.value).then(response => {
    receptorList.value = response.rows;
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
    surroundingLandEast: null,
    surroundingLandSouth: null,
    surroundingLandWest: null,
    surroundingLandNorth: null,
    employeeCountCategory: null,
    permanentPopulationCategory: null,
    sensitiveTargets: null,
    deptId: null
  };
  proxy.resetForm("receptorRef");
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
  title.value = "添加地块及周边敏感受体信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getReceptor(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改地块及周边敏感受体信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["receptorRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateReceptor(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addReceptor(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除地块及周边敏感受体信息编号为"' + _ids + '"的数据项？').then(function() {
    return delReceptor(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/receptor/export', {
    ...queryParams.value
  }, `receptor_${new Date().getTime()}.xlsx`)
}

getList();
</script>
