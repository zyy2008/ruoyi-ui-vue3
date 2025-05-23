<template>
  <div class="app-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>监测数据管理</span>
          <el-button type="primary" @click="handleAdd">新增数据</el-button>
        </div>
      </template>
      
      <!-- 搜索区域 -->
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="编号" prop="sampleId">
          <el-input v-model="queryParams.sampleId" placeholder="请输入编号" clearable />
        </el-form-item>
        <el-form-item label="名称" prop="sampleName">
          <el-input v-model="queryParams.sampleName" placeholder="请输入名称" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">搜索</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <!-- 表格区域 -->
      <el-table v-loading="loading" :data="detectionList" style="width: 100%; min-width: 1600px; overflow-x: auto">
        <el-table-column type="index" label="序号" width="60" />
        <el-table-column prop="sampleId" label="编号" width="90" />
        <el-table-column prop="sampleName" label="名称" width="110" />
        <el-table-column prop="sampleDate" label="采样日期" width="100" />
        <el-table-column prop="ph" label="pH" width="60" />
        <el-table-column prop="ss" label="悬浮物" width="70" />
        <el-table-column prop="cod" label="COD" width="70" />
        <el-table-column prop="bod5" label="BOD₅" width="70" />
        <el-table-column prop="nh3n" label="氨氮" width="70" />
        <el-table-column prop="tp" label="总磷" width="70" />
        <el-table-column prop="tn" label="总氮" width="70" />
        <el-table-column prop="cu" label="铜" width="60" />
        <el-table-column prop="zn" label="锌" width="60" />
        <el-table-column prop="mn" label="锰" width="60" />
        <el-table-column prop="cd" label="镉" width="60" />
        <el-table-column prop="pb" label="铅" width="60" />
        <el-table-column prop="hg" label="汞" width="60" />
        <el-table-column prop="as" label="砷" width="60" />
        <el-table-column prop="cr6" label="六价铬" width="70" />
        <el-table-column prop="f" label="氟化物" width="70" />
        <el-table-column prop="sulfide" label="硫化物" width="70" />
        <el-table-column prop="oil" label="石油类" width="70" />
        <el-table-column prop="phenol" label="挥发酚" width="70" />
        <el-table-column prop="anion" label="阴离子" width="100" />
        <el-table-column prop="coli" label="粪大肠菌群" width="100" />
        <el-table-column prop="createTime" label="创建时间" width="130" />
        <el-table-column label="操作" align="center" width="220" fixed="right">
          <template #default="scope">
            <el-button type="text" @click="handleDetail(scope.row)">详情</el-button>
            <el-button type="text" @click="handleUpdate(scope.row)">修改</el-button>
            <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="detectionForm" :model="form" :rules="rules" label-width="120px">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="编号" prop="sampleId">
              <el-input v-model="form.sampleId" placeholder="请输入编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="名称" prop="sampleName">
              <el-input v-model="form.sampleName" placeholder="请输入名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采样日期" prop="sampleDate">
              <el-input v-model="form.sampleDate" placeholder="请输入采样日期" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="pH" prop="ph">
              <el-input v-model="form.ph" placeholder="请输入pH" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="悬浮物" prop="ss">
              <el-input v-model="form.ss" placeholder="请输入悬浮物" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="COD" prop="cod">
              <el-input v-model="form.cod" placeholder="请输入COD" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="BOD₅" prop="bod5">
              <el-input v-model="form.bod5" placeholder="请输入BOD₅" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="氨氮" prop="nh3n">
              <el-input v-model="form.nh3n" placeholder="请输入氨氮" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总磷" prop="tp">
              <el-input v-model="form.tp" placeholder="请输入总磷" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总氮" prop="tn">
              <el-input v-model="form.tn" placeholder="请输入总氮" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="铜" prop="cu">
              <el-input v-model="form.cu" placeholder="请输入铜" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="锌" prop="zn">
              <el-input v-model="form.zn" placeholder="请输入锌" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="锰" prop="mn">
              <el-input v-model="form.mn" placeholder="请输入锰" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="镉" prop="cd">
              <el-input v-model="form.cd" placeholder="请输入镉" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="铅" prop="pb">
              <el-input v-model="form.pb" placeholder="请输入铅" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="汞" prop="hg">
              <el-input v-model="form.hg" placeholder="请输入汞" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="砷" prop="as">
              <el-input v-model="form.as" placeholder="请输入砷" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="六价铬" prop="cr6">
              <el-input v-model="form.cr6" placeholder="请输入六价铬" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="氟化物" prop="f">
              <el-input v-model="form.f" placeholder="请输入氟化物" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="硫化物" prop="sulfide">
              <el-input v-model="form.sulfide" placeholder="请输入硫化物" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="石油类" prop="oil">
              <el-input v-model="form.oil" placeholder="请输入石油类" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挥发酚" prop="phenol">
              <el-input v-model="form.phenol" placeholder="请输入挥发酚" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="阴离子" prop="anion">
              <el-input v-model="form.anion" placeholder="请输入阴离子" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="粪大肠菌群" prop="coli">
              <el-input v-model="form.coli" placeholder="请输入粪大肠菌群" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建时间" prop="createTime">
              <el-input v-model="form.createTime" placeholder="请输入创建时间" />
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

    <!-- 详情弹窗 -->
    <el-dialog title="检测数据详情" v-model="detailOpen" width="600px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="采样点编号">{{ detailData.sampleId }}</el-descriptions-item>
        <el-descriptions-item label="采样点名称">{{ detailData.sampleName }}</el-descriptions-item>
        <el-descriptions-item label="采样日期">{{ detailData.sampleDate }}</el-descriptions-item>
        <el-descriptions-item label="pH">{{ detailData.ph }}</el-descriptions-item>
        <el-descriptions-item label="COD">{{ detailData.cod }}</el-descriptions-item>
        <el-descriptions-item label="氨氮">{{ detailData.nh3n }}</el-descriptions-item>
        <el-descriptions-item label="总磷">{{ detailData.tp }}</el-descriptions-item>
        <el-descriptions-item label="总氮">{{ detailData.tn }}</el-descriptions-item>
        <el-descriptions-item label="BOD₅">{{ detailData.bod5 }}</el-descriptions-item>
        <el-descriptions-item label="悬浮物">{{ detailData.ss }}</el-descriptions-item>
        <el-descriptions-item label="铜">{{ detailData.cu }}</el-descriptions-item>
        <el-descriptions-item label="锌">{{ detailData.zn }}</el-descriptions-item>
        <el-descriptions-item label="锰">{{ detailData.mn }}</el-descriptions-item>
        <el-descriptions-item label="镉">{{ detailData.cd }}</el-descriptions-item>
        <el-descriptions-item label="铅">{{ detailData.pb }}</el-descriptions-item>
        <el-descriptions-item label="汞">{{ detailData.hg }}</el-descriptions-item>
        <el-descriptions-item label="砷">{{ detailData.as }}</el-descriptions-item>
        <el-descriptions-item label="六价铬">{{ detailData.cr6 }}</el-descriptions-item>
        <el-descriptions-item label="氟化物">{{ detailData.f }}</el-descriptions-item>
        <el-descriptions-item label="硫化物">{{ detailData.sulfide }}</el-descriptions-item>
        <el-descriptions-item label="石油类">{{ detailData.oil }}</el-descriptions-item>
        <el-descriptions-item label="挥发酚">{{ detailData.phenol }}</el-descriptions-item>
        <el-descriptions-item label="阴离子表面活性剂">{{ detailData.anion }}</el-descriptions-item>
        <el-descriptions-item label="粪大肠菌群">{{ detailData.coli }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ detailData.createTime }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import Pagination from '@/components/Pagination'

// 遮罩层
const loading = ref(false)
// 总条数
const total = ref(0)
// 弹出层标题
const title = ref("")
// 是否显示弹出层
const open = ref(false)

// 查询参数
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  sampleId: undefined,
  sampleName: undefined
})

// 表单参数
const form = reactive({
  id: undefined,
  sampleId: undefined,
  sampleName: undefined,
  sampleDate: undefined,
  ph: undefined,
  ss: undefined,
  cod: undefined,
  bod5: undefined,
  nh3n: undefined,
  tp: undefined,
  tn: undefined,
  cu: undefined,
  zn: undefined,
  mn: undefined,
  cd: undefined,
  pb: undefined,
  hg: undefined,
  as: undefined,
  cr6: undefined,
  f: undefined,
  sulfide: undefined,
  oil: undefined,
  phenol: undefined,
  anion: undefined,
  coli: undefined,
  createTime: undefined
})

// 表单校验
const rules = {
  sampleId: [
    { required: true, message: "编号不能为空", trigger: "blur" }
  ],
  sampleName: [
    { required: true, message: "名称不能为空", trigger: "blur" }
  ],
  sampleDate: [
    { required: true, message: "采样日期不能为空", trigger: "blur" }
  ],
  ph: [
    { required: true, message: "pH不能为空", trigger: "blur" }
  ],
  ss: [
    { required: true, message: "悬浮物不能为空", trigger: "blur" }
  ],
  cod: [
    { required: true, message: "COD不能为空", trigger: "blur" }
  ],
  bod5: [
    { required: true, message: "BOD₅不能为空", trigger: "blur" }
  ],
  nh3n: [
    { required: true, message: "氨氮不能为空", trigger: "blur" }
  ],
  tp: [
    { required: true, message: "总磷不能为空", trigger: "blur" }
  ],
  tn: [
    { required: true, message: "总氮不能为空", trigger: "blur" }
  ],
  cu: [
    { required: true, message: "铜不能为空", trigger: "blur" }
  ],
  zn: [
    { required: true, message: "锌不能为空", trigger: "blur" }
  ],
  mn: [
    { required: true, message: "锰不能为空", trigger: "blur" }
  ],
  cd: [
    { required: true, message: "镉不能为空", trigger: "blur" }
  ],
  pb: [
    { required: true, message: "铅不能为空", trigger: "blur" }
  ],
  hg: [
    { required: true, message: "汞不能为空", trigger: "blur" }
  ],
  as: [
    { required: true, message: "砷不能为空", trigger: "blur" }
  ],
  cr6: [
    { required: true, message: "六价铬不能为空", trigger: "blur" }
  ],
  f: [
    { required: true, message: "氟化物不能为空", trigger: "blur" }
  ],
  sulfide: [
    { required: true, message: "硫化物不能为空", trigger: "blur" }
  ],
  oil: [
    { required: true, message: "石油类不能为空", trigger: "blur" }
  ],
  phenol: [
    { required: true, message: "挥发酚不能为空", trigger: "blur" }
  ],
  anion: [
    { required: true, message: "阴离子不能为空", trigger: "blur" }
  ],
  coli: [
    { required: true, message: "粪大肠菌群不能为空", trigger: "blur" }
  ],
  createTime: [
    { required: true, message: "创建时间不能为空", trigger: "blur" }
  ]
}

// 检测数据列表
const detectionList = ref([
  {
    id: 1,
    sampleId: 'JZ01',
    sampleName: '进水口',
    sampleDate: '2024-05-01',
    ph: 7.12,
    ss: 12,
    cod: 35,
    bod5: 10,
    nh3n: 1.1,
    tp: 0.13,
    tn: 2.0,
    cu: 0.01,
    zn: 0.02,
    mn: 0.03,
    cd: 0.001,
    pb: 0.002,
    hg: 0.0001,
    as: 0.001,
    cr6: 0.002,
    f: 0.5,
    sulfide: 0.01,
    oil: 0.02,
    phenol: 0.001,
    anion: 0.05,
    coli: 200,
    createTime: '2024-05-01 08:00:00'
  },
  {
    id: 2,
    sampleId: 'JZ02',
    sampleName: '出水口',
    sampleDate: '2024-05-01',
    ph: 7.25,
    ss: 10,
    cod: 30,
    bod5: 8,
    nh3n: 0.9,
    tp: 0.11,
    tn: 1.8,
    cu: 0.009,
    zn: 0.018,
    mn: 0.025,
    cd: 0.0008,
    pb: 0.0018,
    hg: 0.00009,
    as: 0.0009,
    cr6: 0.0018,
    f: 0.45,
    sulfide: 0.009,
    oil: 0.018,
    phenol: 0.0009,
    anion: 0.045,
    coli: 180,
    createTime: '2024-05-01 08:10:00'
  },
  {
    id: 3,
    sampleId: 'JZ03',
    sampleName: '备用点',
    sampleDate: '2024-05-01',
    ph: 7.00,
    ss: 13,
    cod: 36,
    bod5: 11,
    nh3n: 1.2,
    tp: 0.14,
    tn: 2.2,
    cu: 0.012,
    zn: 0.021,
    mn: 0.031,
    cd: 0.0011,
    pb: 0.0022,
    hg: 0.00011,
    as: 0.0011,
    cr6: 0.0022,
    f: 0.51,
    sulfide: 0.011,
    oil: 0.021,
    phenol: 0.0011,
    anion: 0.051,
    coli: 210,
    createTime: '2024-05-01 08:20:00'
  },
  {
    id: 4,
    sampleId: 'JZ04',
    sampleName: '监测点A',
    sampleDate: '2024-05-01',
    ph: 7.18,
    ss: 11,
    cod: 33,
    bod5: 9,
    nh3n: 1.0,
    tp: 0.12,
    tn: 1.9,
    cu: 0.011,
    zn: 0.019,
    mn: 0.028,
    cd: 0.0009,
    pb: 0.0019,
    hg: 0.00010,
    as: 0.0010,
    cr6: 0.0020,
    f: 0.48,
    sulfide: 0.010,
    oil: 0.019,
    phenol: 0.0010,
    anion: 0.048,
    coli: 190,
    createTime: '2024-05-01 08:30:00'
  },
  {
    id: 5,
    sampleId: 'JZ05',
    sampleName: '监测点B',
    sampleDate: '2024-05-01',
    ph: 7.22,
    ss: 12,
    cod: 34,
    bod5: 10,
    nh3n: 1.1,
    tp: 0.13,
    tn: 2.0,
    cu: 0.010,
    zn: 0.020,
    mn: 0.030,
    cd: 0.0010,
    pb: 0.0020,
    hg: 0.00010,
    as: 0.0010,
    cr6: 0.0020,
    f: 0.50,
    sulfide: 0.010,
    oil: 0.020,
    phenol: 0.0010,
    anion: 0.050,
    coli: 200,
    createTime: '2024-05-01 08:40:00'
  }
])

// 获取检测数据列表
const getList = () => {
  loading.value = true
  // 模拟数据
  const mockData = Array.from({ length: 10 }, (_, index) => ({
    id: index + 1,
    name: `检测${index + 1}`,
    type: ['A', 'B', 'C'][Math.floor(Math.random() * 3)],
    value: Math.floor(Math.random() * 100),
    createTime: new Date().toLocaleString()
  }))
  
  detectionList.value = mockData
  total.value = mockData.length
  loading.value = false
}

// 搜索按钮操作
const handleQuery = () => {
  queryParams.pageNum = 1
  getList()
}

// 重置按钮操作
const resetQuery = () => {
  queryParams.sampleId = undefined
  queryParams.sampleName = undefined
  handleQuery()
}

// 新增按钮操作
const handleAdd = () => {
  title.value = "添加检测数据"
  open.value = true
  Object.assign(form, {
    id: undefined,
    sampleId: undefined,
    sampleName: undefined,
    sampleDate: undefined,
    ph: undefined,
    ss: undefined,
    cod: undefined,
    bod5: undefined,
    nh3n: undefined,
    tp: undefined,
    tn: undefined,
    cu: undefined,
    zn: undefined,
    mn: undefined,
    cd: undefined,
    pb: undefined,
    hg: undefined,
    as: undefined,
    cr6: undefined,
    f: undefined,
    sulfide: undefined,
    oil: undefined,
    phenol: undefined,
    anion: undefined,
    coli: undefined,
    createTime: undefined
  })
}

// 修改按钮操作
const handleUpdate = (row) => {
  title.value = "修改检测数据"
  open.value = true
  Object.assign(form, row)
}

// 提交按钮
const submitForm = () => {
  if (form.id) {
    // 修改操作
    const index = detectionList.value.findIndex(item => item.id === form.id)
    if (index !== -1) {
      detectionList.value[index] = { ...form }
    }
  } else {
    // 新增操作
    form.id = detectionList.value.length + 1
    form.createTime = new Date().toLocaleString()
    detectionList.value.unshift({ ...form })
  }
  open.value = false
  ElMessage.success("操作成功")
}

// 取消按钮
const cancel = () => {
  open.value = false
}

// 删除按钮操作
const handleDelete = (row) => {
  ElMessageBox.confirm('是否确认删除该检测数据?', "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(() => {
    const index = detectionList.value.findIndex(item => item.id === row.id)
    if (index !== -1) {
      detectionList.value.splice(index, 1)
    }
    ElMessage.success("删除成功")
  })
}

// 详情弹窗
const detailOpen = ref(false)
const detailData = ref({})
const handleDetail = (row) => {
  detailData.value = row
  detailOpen.value = true
}

onMounted(() => {
  //getList()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 