<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程编号" prop="engineeringCode">
        <el-input
          v-model="queryParams.engineeringCode"
          placeholder="请输入工程编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程时间" prop="engineeringDate">
        <el-date-picker clearable
          v-model="queryParams.engineeringDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择工程时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="设计单位" prop="designUnit">
        <el-input
          v-model="queryParams.designUnit"
          placeholder="请输入设计单位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设计者" prop="designer">
        <el-input
          v-model="queryParams.designer"
          placeholder="请输入设计者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设计阶段" prop="designPhase">
        <el-select style="width: 200px" v-model="queryParams.designPhase" placeholder="请选择设计阶段" clearable>
          <el-option
            v-for="dict in design_phase"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="归属人" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入归属人"
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
          v-hasPermi="['admin:manage:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:manage:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:manage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:manage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="manageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="工程编号" align="center" prop="engineeringCode" />
      <el-table-column label="工程时间" align="center" prop="engineeringDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.engineeringDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="设计单位" align="center" prop="designUnit" />
      <el-table-column label="设计者" align="center" prop="designer" />
      <el-table-column label="设计阶段" align="center" prop="designPhase">
        <template #default="scope">
          <dict-tag :options="design_phase" :value="scope.row.designPhase"/>
        </template>
      </el-table-column>
      <el-table-column label="归属人" align="center" prop="userId" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="文件地址" align="center" prop="fileUrl" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:manage:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:manage:remove']">删除</el-button>
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

    <!-- 添加或修改项目管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="manageRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="工程编号" prop="engineeringCode">
          <el-input v-model="form.engineeringCode" placeholder="请输入工程编号" />
        </el-form-item>
        <el-form-item label="工程时间" prop="engineeringDate">
          <el-date-picker clearable
            v-model="form.engineeringDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择工程时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="设计单位" prop="designUnit">
          <el-input v-model="form.designUnit" placeholder="请输入设计单位" />
        </el-form-item>
        <el-form-item label="设计者" prop="designer">
          <el-input v-model="form.designer" placeholder="请输入设计者" />
        </el-form-item>
        <el-form-item label="设计阶段" prop="designPhase">
          <el-select v-model="form.designPhase" placeholder="请选择设计阶段">
            <el-option
              v-for="dict in design_phase"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="归属人" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入归属人" />
        </el-form-item>
        <el-form-item label="归属部门" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入归属部门" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="文件地址" prop="fileUrl">
          <el-input v-model="form.fileUrl" type="textarea" placeholder="请输入内容" />
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

<script setup name="Manage">
import { listManage, getManage, delManage, addManage, updateManage } from "@/api/admin/manage";

const { proxy } = getCurrentInstance();
const { design_phase } = proxy.useDict('design_phase');

const manageList = ref([]);
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
    projectName: null,
    engineeringCode: null,
    engineeringDate: null,
    designUnit: null,
    designer: null,
    designPhase: null,
    userId: null,
    deptId: null,
    fileUrl: null
  },
  rules: {
    projectName: [
      { required: true, message: "项目名称不能为空", trigger: "blur" }
    ],
    engineeringCode: [
      { required: true, message: "工程编号不能为空", trigger: "blur" }
    ],
    engineeringDate: [
      { required: true, message: "工程时间不能为空", trigger: "blur" }
    ],
    designUnit: [
      { required: true, message: "设计单位不能为空", trigger: "blur" }
    ],
    designer: [
      { required: true, message: "设计者不能为空", trigger: "blur" }
    ],
    fileUrl: [
      { required: true, message: "文件地址不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目管理列表 */
function getList() {
  loading.value = true;
  listManage(queryParams.value).then(response => {
    manageList.value = response.rows;
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
    projectName: null,
    engineeringCode: null,
    engineeringDate: null,
    designUnit: null,
    designer: null,
    designPhase: null,
    userId: null,
    deptId: null,
    remark: null,
    fileUrl: null
  };
  proxy.resetForm("manageRef");
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
  title.value = "添加项目管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getManage(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["manageRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateManage(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addManage(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除项目管理编号为"' + _ids + '"的数据项？').then(function() {
    return delManage(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/manage/export', {
    ...queryParams.value
  }, `manage_${new Date().getTime()}.xlsx`)
}

getList();
</script>
