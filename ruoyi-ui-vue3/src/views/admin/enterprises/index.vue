<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="企业名称" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业名称"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否重点" prop="isKeyEnterprise">
        <el-select
          v-model="queryParams.isKeyEnterprise"
          placeholder="请选择是否重点"
          clearable
          style="width: 200px"
        >
          <el-option label="是" value="是" />
          <el-option label="否" value="否" />
        </el-select>
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
          v-hasPermi="['admin:enterprises:add']"
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
          v-hasPermi="['admin:enterprises:edit']"
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
          v-hasPermi="['admin:enterprises:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:enterprises:export']"
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
      :data="enterprisesList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="企业名称" align="center" prop="enterpriseName" />
      <el-table-column label="行业类别" align="center" prop="industryCategory" />
      <el-table-column label="生产年限" align="center" prop="productionYears" />
      <el-table-column label="存续状态" align="center" prop="status" />
      <el-table-column label="报送情况" align="center" prop="reportingStatus" />
      <el-table-column label="经度" align="center" prop="longitude" />
      <el-table-column label="纬度" align="center" prop="latitude" />
      <el-table-column label="占地面积" align="center" prop="area" />
      <el-table-column label="是否重点" align="center" prop="isKeyEnterprise" />
      <el-table-column
        label="操作"
        align="center"
        width="240"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleDetail(scope.row)"
            >详情</el-button
          >
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:enterprises:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="danger"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:enterprises:remove']"
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

    <!-- 添加或修改企业管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="enterprisesRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="企业名称" prop="enterpriseName">
          <el-input v-model="form.enterpriseName" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="行业类别" prop="industryCategory">
          <el-input v-model="form.industryCategory" placeholder="请输入行业类别" />
        </el-form-item>
        <el-form-item label="生产年限" prop="productionYears">
          <el-input v-model="form.productionYears" placeholder="请输入生产年限" />
        </el-form-item>
        <el-form-item label="存续状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择存续状态">
            <el-option label="存续" value="存续" />
            <el-option label="注销" value="注销" />
            <el-option label="吊销" value="吊销" />
          </el-select>
        </el-form-item>
        <el-form-item label="报送情况" prop="reportingStatus">
          <el-select v-model="form.reportingStatus" placeholder="请选择报送情况">
            <el-option label="已报送" value="已报送" />
            <el-option label="未报送" value="未报送" />
          </el-select>
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="占地面积" prop="area">
          <el-input v-model="form.area" placeholder="请输入占地面积" />
        </el-form-item>
        <el-form-item label="是否重点" prop="isKeyEnterprise">
          <el-radio-group v-model="form.isKeyEnterprise">
            <el-radio label="是">是</el-radio>
            <el-radio label="否">否</el-radio>
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

    <el-dialog v-model="dialogVisible" title="企业信息" width="100vw" destroy-on-close>
      <TabsCard :deptId="deptId" />
      <!-- <el-tabs
        v-model="activeName"
        type="card"
        class="map-tabs"
        @tab-click="handleClick"
      >
        <el-tab-pane label="企业地块基本情况" name="first"
          ><form_tab1
        /></el-tab-pane>
        <el-tab-pane label="企业污染源信息" name="second"
          ><form_tab2
        /></el-tab-pane>
        <el-tab-pane label="迁移途径信息" name="third"
          ><form_tab3
        /></el-tab-pane>
        <el-tab-pane label="敏感受体信息" name="fourth"
          ><form_tab4
        /></el-tab-pane>
        <el-tab-pane label="土壤或地下水环境监测" name="five"
          ><form_tab5
        /></el-tab-pane>
        <el-tab-pane label="环境监测和调查评估信息" name="six"
          ><form_tab6
        /></el-tab-pane>
      </el-tabs> -->
    </el-dialog>
  </div>
</template>

<script setup name="Enterprises">
import form_tab1 from "@/views/corporate/components/tab1/form";
import form_tab2 from "@/views/corporate/components/tab2/form";
import form_tab3 from "@/views/corporate/components/tab3/form";
import form_tab4 from "@/views/corporate/components/tab4/form";
import form_tab5 from "@/views/corporate/components/tab5/form";
import form_tab6 from "@/views/corporate/components/tab6/form";
import TabsCard from "@/views/corporate";
import { generateUUID } from "@/utils/index";
import {
  listEnterprises,
  getEnterprises,
  delEnterprises,
  addEnterprises,
  updateEnterprises,
} from "@/api/admin/enterprises";

const { proxy } = getCurrentInstance();

const enterprisesList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const deptId = ref(null);

const activeName = ref("first");

const handleClick = (tab, event) => {
  console.log(tab, event);
};

const dialogVisible = ref(false);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    enterpriseName: null,
    industryCategory: null,
    productionYears: null,
    status: null,
    reportingStatus: null,
    longitude: null,
    latitude: null,
    area: null,
    isKeyEnterprise: null,
  },
  rules: {},
});

const { queryParams, form, rules } = toRefs(data);

/** 查询企业管理列表 */
function getList() {
  loading.value = true;
  listEnterprises(queryParams.value).then((response) => {
    enterprisesList.value = response.rows;
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
    enterpriseName: null,
    industryCategory: null,
    productionYears: null,
    status: null,
    reportingStatus: null,
    longitude: null,
    latitude: null,
    area: null,
    isKeyEnterprise: null,
    id: null,
  };
  proxy.resetForm("enterprisesRef");
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
  title.value = "添加企业管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getEnterprises(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改企业管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["enterprisesRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateEnterprises(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        form.value.id = generateUUID();
        addEnterprises(form.value).then((response) => {
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
    .confirm('是否确认删除企业管理编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delEnterprises(_ids);
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
    "admin/enterprises/export",
    {
      ...queryParams.value,
    },
    `enterprises_${new Date().getTime()}.xlsx`
  );
}

function handleDetail(row) {
  dialogVisible.value = true;
  deptId.value = row.id;
}

getList();
</script>
