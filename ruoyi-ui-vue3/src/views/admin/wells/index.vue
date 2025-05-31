<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编码" prop="wellCode">
        <el-input
          v-model="queryParams.wellCode"
          placeholder="请输入编码"
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
          v-hasPermi="['admin:wells:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:wells:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:wells:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:wells:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编码" align="center" prop="wellCode" />
      <el-table-column label="位置" align="center" prop="location" />
      <el-table-column label="类型" align="center" prop="pointType" />
      <el-table-column label="水位埋深" align="center" prop="waterBuriedDepth" />
      <el-table-column label="井口高程" align="center" prop="wellElevation" />
      <el-table-column label="地下水类型" align="center" prop="groundwaterType" />
      <el-table-column label="经度" align="center" prop="longitude" />
      <el-table-column label="纬度" align="center" prop="latitude" />
      <el-table-column label="操作" align="center" width="240" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleDetail(scope.row)">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:wells:edit']">修改</el-button>
          <el-button link type="danger" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:wells:remove']">删除</el-button>
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

    <!-- 添加或修改园区初调监测井信息，记录每个监测井的基础属性与附加信息对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="wellsRef" :model="form" :rules="rules" label-width="120px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="监测井编码" prop="wellCode">
              <el-input v-model="form.wellCode" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="监测井位置" prop="location">
              <el-input v-model="form.location" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="成井时间" prop="completionDate">
              <el-input v-model="form.completionDate" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="井管材质" prop="pipeMaterial">
              <el-input v-model="form.pipeMaterial" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="埋深范围" prop="screenDepthRange">
              <el-input v-model="form.screenDepthRange" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="埋藏条件" prop="burialCondition">
              <el-input v-model="form.burialCondition" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="含水介质" prop="waterMedium">
              <el-input v-model="form.waterMedium" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="权属单位" prop="ownership">
              <el-input v-model="form.ownership" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="视频资料地址" prop="videoUrl">
              <el-input v-model="form.videoUrl" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="deptId">
              <el-input v-model="form.deptId" placeholder="请输入归属部门" />
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

    <!-- 详情对话框 -->
    <el-dialog title="监测井详情" v-model="detailOpen" width="800px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="编码">{{ detailForm.wellCode }}</el-descriptions-item>
        <el-descriptions-item label="位置">{{ detailForm.location }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ detailForm.pointType }}</el-descriptions-item>
        <el-descriptions-item label="时间">{{ detailForm.completionDate }}</el-descriptions-item>
        <el-descriptions-item label="水位埋深">{{ detailForm.waterBuriedDepth }}</el-descriptions-item>
        <el-descriptions-item label="井口高程">{{ detailForm.wellElevation }}</el-descriptions-item>
        <el-descriptions-item label="成井深度">{{ detailForm.wellDepth }}</el-descriptions-item>
        <el-descriptions-item label="井口内径">{{ detailForm.innerDiameter }}</el-descriptions-item>
        <el-descriptions-item label="材质">{{ detailForm.pipeMaterial }}</el-descriptions-item>
        <el-descriptions-item label="结构">{{ detailForm.multiScreenPipe }}</el-descriptions-item>
        <el-descriptions-item label="埋深范围">{{ detailForm.screenDepthRange }}</el-descriptions-item>
        <el-descriptions-item label="地下水类型">{{ detailForm.groundwaterType }}</el-descriptions-item>
        <el-descriptions-item label="埋藏条件">{{ detailForm.burialCondition }}</el-descriptions-item>
        <el-descriptions-item label="含水介质">{{ detailForm.waterMedium }}</el-descriptions-item>
        <el-descriptions-item label="权属单位">{{ detailForm.ownership }}</el-descriptions-item>
        <el-descriptions-item label="是否符合标准">{{ detailForm.suitableForLongterm }}</el-descriptions-item>
        <el-descriptions-item label="经度">{{ detailForm.longitude }}</el-descriptions-item>
        <el-descriptions-item label="纬度">{{ detailForm.latitude }}</el-descriptions-item>
        <el-descriptions-item label="视频">{{ detailForm.videoUrl }}</el-descriptions-item>
        <el-descriptions-item label="归属部门">{{ detailForm.deptId }}</el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="detailOpen = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Wells">
import { listWells, getWells, delWells, addWells, updateWells } from "@/api/admin/wells";

const { proxy } = getCurrentInstance();

const wellsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const detailOpen = ref(false);
const detailForm = ref({});

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    wellCode: null,
    location: null,
    pointType: null,
    completionDate: null,
    waterBuriedDepth: null,
    wellElevation: null,
    wellDepth: null,
    innerDiameter: null,
    pipeMaterial: null,
    multiScreenPipe: null,
    screenDepthRange: null,
    groundwaterType: null,
    burialCondition: null,
    waterMedium: null,
    ownership: null,
    suitableForLongterm: null,
    longitude: null,
    latitude: null,
    altitude: null,
    videoUrl: null,
    deptId: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息列表 */
function getList() {
  loading.value = true;
  listWells(queryParams.value).then(response => {
    wellsList.value = response.rows;
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
    wellCode: null,
    location: null,
    pointType: null,
    completionDate: null,
    waterBuriedDepth: null,
    wellElevation: null,
    wellDepth: null,
    innerDiameter: null,
    pipeMaterial: null,
    multiScreenPipe: null,
    screenDepthRange: null,
    groundwaterType: null,
    burialCondition: null,
    waterMedium: null,
    ownership: null,
    suitableForLongterm: null,
    longitude: null,
    latitude: null,
    altitude: null,
    videoUrl: null,
    deptId: null
  };
  proxy.resetForm("wellsRef");
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
  title.value = "添加园区初调监测井信息，记录每个监测井的基础属性与附加信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getWells(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改园区初调监测井信息，记录每个监测井的基础属性与附加信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["wellsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateWells(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addWells(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除园区初调监测井信息，记录每个监测井的基础属性与附加信息编号为"' + _ids + '"的数据项？').then(function() {
    return delWells(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/wells/export', {
    ...queryParams.value
  }, `wells_${new Date().getTime()}.xlsx`)
}

/** 查看详情按钮操作 */
function handleDetail(row) {
  getWells(row.id).then(response => {
    detailForm.value = response.data;
    detailOpen.value = true;
  });
}

getList();
</script>
