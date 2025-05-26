<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="投产时间" prop="startupDate">
        <el-date-picker clearable
          v-model="queryParams.startupDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择投产时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="储罐数量" prop="tankCount">
        <el-input
          v-model="queryParams.tankCount"
          placeholder="请输入储罐数量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="储存开始时间" prop="storageStartDate">
        <el-date-picker clearable
          v-model="queryParams.storageStartDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择储存开始时间">
        </el-date-picker>
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
          v-hasPermi="['admin:pollution:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:pollution:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:pollution:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:pollution:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pollutionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="产品生产年份范围" align="center" prop="productionYearRange" />
      <el-table-column label="产品单位" align="center" prop="productUnit" />
      <el-table-column label="年均产量" align="center" prop="productAvgOutput" />
      <el-table-column label="原料名称" align="center" prop="materialName" />
      <el-table-column label="原料使用年份范围" align="center" prop="materialUseYearRange" />
      <el-table-column label="原料单位" align="center" prop="materialUnit" />
      <el-table-column label="原料年均用量" align="center" prop="materialAvgUsage" />
      <el-table-column label="生产线名称" align="center" prop="processLineName" />
      <el-table-column label="生产线经度" align="center" prop="longitude" />
      <el-table-column label="生产线纬度" align="center" prop="latitude" />
      <el-table-column label="投产时间" align="center" prop="startupDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startupDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工艺描述" align="center" prop="processDescription" />
      <el-table-column label="是否存在泄漏" align="center" prop="leakStatus" />
      <el-table-column label="车间面积" align="center" prop="workshopArea" />
      <el-table-column label="储存区名称" align="center" prop="storageAreaName" />
      <el-table-column label="储存区经度" align="center" prop="storageLongitude" />
      <el-table-column label="储存区纬度" align="center" prop="storageLatitude" />
      <el-table-column label="储罐数量" align="center" prop="tankCount" />
      <el-table-column label="储存物料名称" align="center" prop="storedMaterial" />
      <el-table-column label="是否为危化品" align="center" prop="isHazardous" />
      <el-table-column label="储存量" align="center" prop="storageAmount" />
      <el-table-column label="储存单位" align="center" prop="storageUnit" />
      <el-table-column label="储存开始时间" align="center" prop="storageStartDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.storageStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否存在泄漏" align="center" prop="storageLeakStatus" />
      <el-table-column label="备注信息" align="center" prop="storageRemark" />
      <el-table-column label="储存区面积" align="center" prop="storageArea" />
      <el-table-column label="是否存在废气治理设施" align="center" prop="gasTreatmentExists" />
      <el-table-column label="废气排放总量" align="center" prop="totalGasEmission" />
      <el-table-column label="废气排放单位" align="center" prop="gasEmissionUnit" />
      <el-table-column label="归属部门" align="center" prop="deptId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:pollution:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:pollution:remove']">删除</el-button>
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

    <!-- 添加或修改企业污染源对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pollutionRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="产品生产年份范围" prop="productionYearRange">
          <el-input v-model="form.productionYearRange" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="产品单位" prop="productUnit">
          <el-input v-model="form.productUnit" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="原料名称" prop="materialName">
          <el-input v-model="form.materialName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="原料使用年份范围" prop="materialUseYearRange">
          <el-input v-model="form.materialUseYearRange" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="原料单位" prop="materialUnit">
          <el-input v-model="form.materialUnit" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="生产线名称" prop="processLineName">
          <el-input v-model="form.processLineName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="投产时间" prop="startupDate">
          <el-date-picker clearable
            v-model="form.startupDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择投产时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工艺描述" prop="processDescription">
          <el-input v-model="form.processDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="储存区名称" prop="storageAreaName">
          <el-input v-model="form.storageAreaName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="储罐数量" prop="tankCount">
          <el-input v-model="form.tankCount" placeholder="请输入储罐数量" />
        </el-form-item>
        <el-form-item label="储存物料名称" prop="storedMaterial">
          <el-input v-model="form.storedMaterial" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="储存单位" prop="storageUnit">
          <el-input v-model="form.storageUnit" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="储存开始时间" prop="storageStartDate">
          <el-date-picker clearable
            v-model="form.storageStartDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择储存开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注信息" prop="storageRemark">
          <el-input v-model="form.storageRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="废气排放单位" prop="gasEmissionUnit">
          <el-input v-model="form.gasEmissionUnit" type="textarea" placeholder="请输入内容" />
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

<script setup name="Pollution">
import { listPollution, getPollution, delPollution, addPollution, updatePollution } from "@/api/admin/pollution";

const { proxy } = getCurrentInstance();

const pollutionList = ref([]);
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
    productionYearRange: null,
    productUnit: null,
    productAvgOutput: null,
    materialName: null,
    materialUseYearRange: null,
    materialUnit: null,
    materialAvgUsage: null,
    processLineName: null,
    longitude: null,
    latitude: null,
    startupDate: null,
    processDescription: null,
    leakStatus: null,
    workshopArea: null,
    storageAreaName: null,
    storageLongitude: null,
    storageLatitude: null,
    tankCount: null,
    storedMaterial: null,
    isHazardous: null,
    storageAmount: null,
    storageUnit: null,
    storageStartDate: null,
    storageLeakStatus: null,
    storageRemark: null,
    storageArea: null,
    gasTreatmentExists: null,
    totalGasEmission: null,
    gasEmissionUnit: null,
    deptId: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询企业污染源列表 */
function getList() {
  loading.value = true;
  listPollution(queryParams.value).then(response => {
    pollutionList.value = response.rows;
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
    productionYearRange: null,
    productUnit: null,
    productAvgOutput: null,
    materialName: null,
    materialUseYearRange: null,
    materialUnit: null,
    materialAvgUsage: null,
    processLineName: null,
    longitude: null,
    latitude: null,
    startupDate: null,
    processDescription: null,
    leakStatus: null,
    workshopArea: null,
    storageAreaName: null,
    storageLongitude: null,
    storageLatitude: null,
    tankCount: null,
    storedMaterial: null,
    isHazardous: null,
    storageAmount: null,
    storageUnit: null,
    storageStartDate: null,
    storageLeakStatus: null,
    storageRemark: null,
    storageArea: null,
    gasTreatmentExists: null,
    totalGasEmission: null,
    gasEmissionUnit: null,
    deptId: null
  };
  proxy.resetForm("pollutionRef");
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
  title.value = "添加企业污染源";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPollution(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改企业污染源";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pollutionRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePollution(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPollution(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除企业污染源编号为"' + _ids + '"的数据项？').then(function() {
    return delPollution(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/pollution/export', {
    ...queryParams.value
  }, `pollution_${new Date().getTime()}.xlsx`)
}

getList();
</script>
