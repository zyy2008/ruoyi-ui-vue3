<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="部门ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核员ID" prop="auditorId">
        <el-input
          v-model="queryParams.auditorId"
          placeholder="请输入审核员ID"
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
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:approval:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:approval:export']"
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
      :data="approvalList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="申请单名称" align="center" prop="name" />
      <el-table-column label="申请单内容" align="center" prop="content" />
      <el-table-column label="部门ID" align="center" prop="deptId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="审核员ID" align="center" prop="auditorId" />
      <el-table-column label="申请单状态" align="center" prop="state">
        <template #default="scope">
          <span>
            {{ findState(scope.row.state) }}
          </span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="申请参数" align="center" prop="param" /> -->
      <el-table-column width="400" label="文件下载路径" align="center" prop="fileUrl" />
      <el-table-column width="220" label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            v-if="scope.row.fileUrl"
            link
            type="primary"
            icon="Edit"
            @click="dowmLoad(scope.row)"
            >下载</el-button
          >
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:approval:edit']"
            >审批</el-button
          >
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:approval:remove']"
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

    <!-- 添加或修改申请审批对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="approvalRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审核状态" prop="state">
          <el-radio-group v-model="form.state">
            <el-radio border :value="1">通过</el-radio>
            <el-radio border :value="2">驳回</el-radio>
          </el-radio-group>
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

<script setup name="Approval">
import {
  listApproval,
  getApproval,
  delApproval,
  addApproval,
  updateApproval,
  setState,
} from "@/api/admin/approval";

const { proxy } = getCurrentInstance();
// const { apply_state } = proxy.useDict("apply_state");
const approvalList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

function findState(code) {
  if (code == 0) {
    return "待审核";
  }
  if (code == 1) {
    return "已通过";
  }
  if (code == 2) {
    return "未通过";
  }
}

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    content: null,
    deptId: null,
    userId: null,
    auditorId: null,
    state: null,
    param: null,
    fileUser: null,
  },
  rules: {
    state: [{ required: true, message: "审批结果不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

function dowmLoad(row){
  down_by_a(row.fileUrl,"裁剪区_"+Date.now()+".dxf")
}

function down_by_a(url, filename) {
  //proxy.download(url, {}, `${name}_${new Date().getTime()}.xlsx`);

  // 创建一个临时的 <a> 元素
  const a = document.createElement("a");

  // 创建一个 URL 对象
  a.href = url;

  // 设置 <a> 元素的下载文件名
  a.download = filename;

  // 触发点击事件
  document.body.appendChild(a);
  a.click();

  // 移除临时的 <a> 元素
  document.body.removeChild(a);

  // 释放 URL 对象
  URL.revokeObjectURL(url);
}

/** 查询申请审批列表 */
function getList() {
  loading.value = true;
  listApproval(queryParams.value).then((response) => {
    approvalList.value = response.rows;
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
    content: null,
    deptId: null,
    userId: null,
    auditorId: null,
    state: null,
    param: null,
    fileUser: null,
  };
  proxy.resetForm("approvalRef");
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
  title.value = "添加申请审批";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getApproval(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "审批";
  });
}

/** 提交按钮 */
function submitForm() {
  setState({
    id: form.value.id,
    state: form.value.state,
  }).then((response) => {
    proxy.$modal.msgSuccess("审批完成");
    open.value = false;
    getList();
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal
    .confirm('是否确认删除申请审批编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delApproval(_ids);
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
    "admin/approval/export",
    {
      ...queryParams.value,
    },
    `approval_${new Date().getTime()}.xlsx`
  );
}

getList();
</script>
