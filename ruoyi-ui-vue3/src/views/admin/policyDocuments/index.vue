<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
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
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['admin:policyDocuments:add']"
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
          v-hasPermi="['admin:policyDocuments:edit']"
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
          v-hasPermi="['admin:policyDocuments:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:policyDocuments:export']"
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
      :data="policyDocumentsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="发布时间" align="center" prop="publishDate" />
      <el-table-column width="600" label="下载连接" align="center" prop="downloadUrl" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['admin:policyDocuments:edit']"
            >下载</el-button
          >
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:policyDocuments:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="danger"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:policyDocuments:remove']"
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

    <!-- 添加或修改政策文件对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form
        ref="policyDocumentsRef"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="标题">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="时间">
          <el-date-picker
            v-model="form.publishDate"
            type="date"
            placeholder="请选择日期"
            style="width: 100%"
          />
        </el-form-item>
        <!-- 其他表单项 -->
        <el-form-item label="政策文件">
          <el-upload
            ref="uploadRef"
            :action="Uploadurl"
            :on-success="handleUploadSuccess"
            v-model:file-list="fileList"
            :limit="1"
            :headers="uploadHeaders"
            :auto-upload="false"
          >
            <el-button type="primary">点击上传</el-button>
            <template #tip>
              <div class="el-upload__tip">支持上传一个文件</div>
            </template>
          </el-upload>
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

<script setup name="PolicyDocuments">
import {
  listPolicyDocuments,
  getPolicyDocuments,
  delPolicyDocuments,
  addPolicyDocuments,
  updatePolicyDocuments,
  download,
} from "@/api/admin/policyDocuments";
import { getToken } from "@/utils/auth";
import { ElMessage } from "element-plus";
const { proxy } = getCurrentInstance();
const Uploadurl = import.meta.env.VITE_APP_BASE_API + "/file/upload";
const policyDocumentsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const uploadRef = ref();
const fileList = ref([]);
const uploadHeaders = {
  //   "Content-Type": "application/json",
  Authorization: "Bearer " + getToken(),
};
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    publishDate: null,
    downloadUrl: null,
  },
  rules: {
    title: [
      {
        required: true,
        message: "标题，政策文件的名称不能为空",
        trigger: "blur",
      },
    ],
    publishDate: [
      {
        required: true,
        message: "发布时间，政策发布的具体日期不能为空",
        trigger: "blur",
      },
    ],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询政策文件列表 */
function getList() {
  loading.value = true;
  listPolicyDocuments(queryParams.value).then((response) => {
    policyDocumentsList.value = response.rows;
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
    title: null,
    publishDate: null,
    downloadUrl: null,
  };
  proxy.resetForm("policyDocumentsRef");
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
  title.value = "添加政策文件";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getPolicyDocuments(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改政策文件";
  });
}

function handleDownload(row) {
  let path = row.downloadUrl;
  download(path).then((response) => {
    // 从路径中提取文件名
    const fileName = path.substring(path.lastIndexOf("/") + 1);
    const blob = new Blob([response]);
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  });
}
function handleUploadSuccess(response, file) {
  form.value.downloadUrl = response.data; // 假设接口返回url
  // 文件上传成功后再提交表单
  proxy.$refs["policyDocumentsRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updatePolicyDocuments(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPolicyDocuments(form.value).then((response) => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 提交按钮 */
function submitForm() {
  if (!form.value.id) {
    if (fileList.value.length === 0) {
      ElMessage.warning("请先选择要上传的文件！");
      return;
    }
    // 有文件，先上传
    uploadRef.value.submit();
    return;
  }

  proxy.$refs["policyDocumentsRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updatePolicyDocuments(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPolicyDocuments(form.value).then((response) => {
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
    .confirm('是否确认删除政策文件编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delPolicyDocuments(_ids);
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
    "admin/policyDocuments/export",
    {
      ...queryParams.value,
    },
    `policyDocuments_${new Date().getTime()}.xlsx`
  );
}

getList();
</script>
