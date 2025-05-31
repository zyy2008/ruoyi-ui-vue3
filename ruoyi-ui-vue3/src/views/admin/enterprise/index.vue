<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单位名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入单位名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开业时间" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择开业时间">
        </el-date-picker>
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
          v-hasPermi="['admin:enterprise:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:enterprise:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:enterprise:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:enterprise:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enterpriseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="地块编码" align="center" prop="plotCode" />
      <el-table-column label="地块名称" align="center" prop="plotName" />
      <el-table-column label="单位名称" align="center" prop="companyName" />
      <el-table-column label="统一社会信用代码" align="center" prop="companyCreditCode" />
      <el-table-column label="法定代表人" align="center" prop="legalRepresentative" />
      <el-table-column label="行业类别" align="center" prop="industryCategory" />
      <el-table-column label="开业时间" align="center" prop="startDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleDetail(scope.row)">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:enterprise:edit']">修改</el-button>
          <el-button link type="danger" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:enterprise:remove']">删除</el-button>
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

    <!-- 添加或修改企业信息对话框 -->
    <el-dialog :title="title" v-model="open" width="1400px" append-to-body>
      <el-form ref="enterpriseRef" :model="form" :rules="rules" label-width="180px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="地块编码" prop="plotCode">
              <el-input v-model="form.plotCode" placeholder="请输入地块编码" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地块名称" prop="plotName">
              <el-input v-model="form.plotName" placeholder="请输入地块名称" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位名称" prop="companyName">
              <el-input v-model="form.companyName" placeholder="请输入单位名称" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="统一社会信用代码" prop="companyCreditCode">
              <el-input v-model="form.companyCreditCode" placeholder="请输入统一社会信用代码" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="法定代表人" prop="legalRepresentative">
              <el-input v-model="form.legalRepresentative" placeholder="请输入法定代表人" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入地址" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行政区划代码" prop="regionCode">
              <el-input v-model="form.regionCode" placeholder="请输入行政区划代码" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="中心经度" prop="centerLongitude">
              <el-input v-model="form.centerLongitude" placeholder="请输入中心经度" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="中心纬度" prop="centerLatitude">
              <el-input v-model="form.centerLatitude" placeholder="请输入中心纬度" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地块占地面积" prop="areaLandCert">
              <el-input v-model="form.areaLandCert" placeholder="请输入地块占地面积" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地块实际使用面积" prop="areaActual">
              <el-input v-model="form.areaActual" placeholder="请输入地块实际使用面积" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人姓名" prop="contactName">
              <el-input v-model="form.contactName" placeholder="请输入联系人姓名" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人电话" prop="contactPhone">
              <el-input v-model="form.contactPhone" placeholder="请输入联系人电话" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行业类别" prop="industryCategory">
              <el-input v-model="form.industryCategory" placeholder="请输入行业类别" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行业代码" prop="industryCode">
              <el-input v-model="form.industryCode" placeholder="请输入行业代码" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登记注册类型" prop="registrationType">
              <el-input v-model="form.registrationType" placeholder="请输入登记注册类型" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业规模" prop="enterpriseScale">
              <el-input v-model="form.enterpriseScale" placeholder="请输入企业规模" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开业时间" prop="startDate">
              <el-date-picker clearable
                v-model="form.startDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择开业时间"
                style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最新改扩建时间" prop="latestExpansionDate">
              <el-date-picker clearable
                v-model="form.latestExpansionDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择最新改扩建时间"
                style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否位于工业园区" prop="inIndustrialPark">
              <el-input v-model="form.inIndustrialPark" placeholder="请输入是否位于工业园区" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地块利用历史" prop="historyLandUse">
              <el-input v-model="form.historyLandUse" placeholder="请输入地块利用历史" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="利用历史起始年份" prop="historyStartYear">
              <el-input v-model="form.historyStartYear" placeholder="请输入利用历史起始年份" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="利用历史结束年份" prop="historyEndYear">
              <el-input v-model="form.historyEndYear" placeholder="请输入利用历史结束年份" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 企业详情对话框 -->
    <el-dialog title="企业详情" v-model="detailOpen" width="800px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="地块编码">{{ detailData.plotCode }}</el-descriptions-item>
        <el-descriptions-item label="地块名称">{{ detailData.plotName }}</el-descriptions-item>
        <el-descriptions-item label="单位名称">{{ detailData.companyName }}</el-descriptions-item>
        <el-descriptions-item label="统一社会信用代码">{{ detailData.companyCreditCode }}</el-descriptions-item>
        <el-descriptions-item label="法定代表人">{{ detailData.legalRepresentative }}</el-descriptions-item>
        <el-descriptions-item label="地址">{{ detailData.address }}</el-descriptions-item>
        <el-descriptions-item label="行政区划代码">{{ detailData.regionCode }}</el-descriptions-item>
        <el-descriptions-item label="中心经度">{{ detailData.centerLongitude }}</el-descriptions-item>
        <el-descriptions-item label="中心纬度">{{ detailData.centerLatitude }}</el-descriptions-item>
        <el-descriptions-item label="地块占地面积">{{ detailData.areaLandCert }}</el-descriptions-item>
        <el-descriptions-item label="地块实际使用面积">{{ detailData.areaActual }}</el-descriptions-item>
        <el-descriptions-item label="联系人姓名">{{ detailData.contactName }}</el-descriptions-item>
        <el-descriptions-item label="联系人电话">{{ detailData.contactPhone }}</el-descriptions-item>
        <el-descriptions-item label="行业类别">{{ detailData.industryCategory }}</el-descriptions-item>
        <el-descriptions-item label="行业代码">{{ detailData.industryCode }}</el-descriptions-item>
        <el-descriptions-item label="登记注册类型">{{ detailData.registrationType }}</el-descriptions-item>
        <el-descriptions-item label="企业规模">{{ detailData.enterpriseScale }}</el-descriptions-item>
        <el-descriptions-item label="开业时间">{{ parseTime(detailData.startDate, '{y}-{m}-{d}') }}</el-descriptions-item>
        <el-descriptions-item label="最新改扩建时间">{{ parseTime(detailData.latestExpansionDate, '{y}-{m}-{d}') }}</el-descriptions-item>
        <el-descriptions-item label="是否位于工业园区或集聚区">{{ detailData.inIndustrialPark }}</el-descriptions-item>
        <el-descriptions-item label="地块利用历史">{{ detailData.historyLandUse }}</el-descriptions-item>
        <el-descriptions-item label="利用历史起始年份">{{ detailData.historyStartYear }}</el-descriptions-item>
        <el-descriptions-item label="利用历史结束年份">{{ detailData.historyEndYear }}</el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="detailOpen = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Enterprise">
import { listEnterprise, getEnterprise, delEnterprise, addEnterprise, updateEnterprise } from "@/api/admin/enterprise";

const { proxy } = getCurrentInstance();

const enterpriseList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const detailOpen = ref(false);
const detailData = ref({});

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    plotName: null,
    companyName: null,
    companyCreditCode: null,
    legalRepresentative: null,
    address: null,
    regionCode: null,
    centerLongitude: null,
    centerLatitude: null,
    areaLandCert: null,
    areaActual: null,
    contactName: null,
    contactPhone: null,
    industryCategory: null,
    industryCode: null,
    registrationType: null,
    startDate: null,
    latestExpansionDate: null,
    inIndustrialPark: null,
    historyLandUse: null,
    historyStartYear: null,
    historyEndYear: null,
    deptId: null
  },
  rules: {
    plotName: [
      { required: true, message: "地块名称不能为空", trigger: "blur" }
    ],
    companyName: [
      { required: true, message: "单位名称不能为空", trigger: "blur" }
    ],
    companyCreditCode: [
      { required: true, message: "统一社会信用代码不能为空", trigger: "blur" }
    ],
    legalRepresentative: [
      { required: true, message: "法定代表人不能为空", trigger: "blur" }
    ],
    address: [
      { required: true, message: "单位所在地详细地址不能为空", trigger: "blur" }
    ],
    regionCode: [
      { required: true, message: "行政区划代码不能为空", trigger: "blur" }
    ],
    centerLongitude: [
      { required: true, message: "中心经度不能为空", trigger: "blur" }
    ],
    centerLatitude: [
      { required: true, message: "中心纬度不能为空", trigger: "blur" }
    ],
    areaLandCert: [
      { required: true, message: "地块占地面积不能为空", trigger: "blur" }
    ],
    areaActual: [
      { required: true, message: "地块实际使用面积不能为空", trigger: "blur" }
    ],
    contactName: [
      { required: true, message: "联系人姓名不能为空", trigger: "blur" }
    ],
    contactPhone: [
      { required: true, message: "联系人电话不能为空", trigger: "blur" }
    ],
    industryCategory: [
      { required: true, message: "行业类别不能为空", trigger: "blur" }
    ],
    industryCode: [
      { required: true, message: "行业代码不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询企业信息列表 */
function getList() {
  loading.value = true;
  listEnterprise(queryParams.value).then(response => {
    enterpriseList.value = response.rows;
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
    plotCode: null,
    plotName: null,
    companyName: null,
    companyCreditCode: null,
    legalRepresentative: null,
    address: null,
    regionCode: null,
    centerLongitude: null,
    centerLatitude: null,
    areaLandCert: null,
    areaActual: null,
    contactName: null,
    contactPhone: null,
    industryCategory: null,
    industryCode: null,
    registrationType: null,
    enterpriseScale: null,
    startDate: null,
    latestExpansionDate: null,
    inIndustrialPark: null,
    historyLandUse: null,
    historyStartYear: null,
    historyEndYear: null,
    deptId: null
  };
  proxy.resetForm("enterpriseRef");
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
  title.value = "添加企业信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getEnterprise(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改企业信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["enterpriseRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateEnterprise(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addEnterprise(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除企业信息编号为"' + _ids + '"的数据项？').then(function() {
    return delEnterprise(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/enterprise/export', {
    ...queryParams.value
  }, `enterprise_${new Date().getTime()}.xlsx`)
}

/** 查看详情按钮操作 */
function handleDetail(row) {
  getEnterprise(row.id).then(response => {
    detailData.value = response.data;
    detailOpen.value = true;
  });
}

getList();
</script>
