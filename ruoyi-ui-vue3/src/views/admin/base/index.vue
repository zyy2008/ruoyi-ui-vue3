<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" maxlength="30" />
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
          v-hasPermi="['admin:base:add']"
          >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:base:edit']"
          >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:base:remove']"
          >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:base:export']"
          >导出
        </el-button>
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="baseList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="编码" align="center" prop="code" />
      <el-table-column label="简称" align="center" prop="abbr" />
      <el-table-column label="位置" align="center" prop="position" />
      <el-table-column label="面积" align="center" prop="area" />
      <el-table-column label="土地" align="center" prop="haveLandArea" />
      <el-table-column label="上报面积" align="center" prop="reportArea" />
      <el-table-column label="高程系统" align="center" prop="elevationSystem" />
      <el-table-column
        show-overflow-tooltip
        width="300"
        label="管线布置基本原则"
        align="center"
        prop="pipelineArrangementPrinciple"
      />
      <el-table-column
        show-overflow-tooltip
        label="数据原点"
        align="center"
        prop="origin"
      />
      <el-table-column
        show-overflow-tooltip
        label="地图视角"
        align="center"
        prop="view"
      />
      <el-table-column
        width="260"
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Plus"
            @click="handleUpdateMap(scope.row)"
            v-hasPermi="['admin:set:edit']"
          >
            参数配置
          </el-button>
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:base:edit']"
          >
            修改
          </el-button>
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:base:remove']"
            >删除
          </el-button>
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

    <!-- 添加或修改基地对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="baseRef" :model="form" :rules="rules" label-width="140px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="form.code" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="简称" prop="abbr">
          <el-input v-model="form.abbr" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="位置" prop="position">
          <el-input v-model="form.position" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="面积" prop="area">
          <el-input v-model="form.area" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="拥有土地面积" prop="haveLandArea">
          <el-input v-model="form.haveLandArea" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="上报面积" prop="reportArea">
          <el-input v-model="form.reportArea" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="高程系统" prop="elevationSystem">
          <el-input v-model="form.elevationSystem" placeholder="请输入" maxlength="30" />
        </el-form-item>
        <el-form-item label="管线布置基本原则" prop="pipelineArrangementPrinciple">
          <el-input
            v-model="form.pipelineArrangementPrinciple"
            placeholder="请输入"
            maxlength="30"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog
      :destroy-on-close="true"
      title="参数配置"
      v-model="showMap"
      width="1400px"
      append-to-body
    >
      <MarsMap :url="configUrl" map-key="SYSMAP" @onload="marsOnload" />
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="opneOrigindDrawer">原点测试</el-button>
          <el-button type="primary" @click="opneOrigindConvert">坐标转换</el-button>
          <el-button type="primary" @click="flyToView">查看当前视角</el-button>
          <el-button type="primary" @click="setToView">更新当前视角</el-button>
        </div>
      </template>

      <el-drawer
        size="280"
        :modal="false"
        :close-on-click-modal="false"
        v-model="originDrawer"
        title="原点测试"
        direction="rtl"
        modal-class="SYSMAP-modal"
        class="SYSMAP-drawer"
      >
        <div class="demo-drawer__content">
          <el-form :model="origin">
            <el-form-item label-width="80" label="校准文件">
              <el-upload
                ref="uploadRef"
                class="upload-demo"
                action=""
                :auto-upload="true"
                :http-request="httpRequest"
              >
                <template #trigger>
                  <el-button type="primary">选择文件</el-button>
                </template>
                <template #tip>
                  <div class="el-upload__tip">json/geojson 文件</div>
                </template>
              </el-upload>
            </el-form-item>
            <el-form-item label-width="80" label="原点经度">
              <el-input v-model="origin.lng" />
            </el-form-item>
            <el-form-item label-width="80" label="原点纬度">
              <el-input v-model="origin.lat" />
            </el-form-item>
            <el-form-item label-width="80" label="X偏移量">
              <el-input v-model="origin.offset_x" />
            </el-form-item>
            <el-form-item label-width="80" label="Y偏移量">
              <el-input v-model="origin.offset_y" />
            </el-form-item>
            <el-form-item label-width="80" label="旋转角度">
              <el-input v-model="origin.rotate" />
            </el-form-item>
          </el-form>
          <div class="demo-drawer__footer">
            <el-button type="primary" :loading="loading" @click="verification">
              验证结果
            </el-button>

            <el-button type="primary" :loading="loading" @click="calculation">
              原点计算
            </el-button>
          </div>
        </div>

        <div>
          <h1 style="margin-top: 40px; margin-bottom: 20px">计算结果：</h1>
          <el-form :model="origin_res">
            <el-form-item label-width="80" label="原点经度">
              <el-input v-model="origin_res.lng" />
            </el-form-item>
            <el-form-item label-width="80" label="原点纬度">
              <el-input v-model="origin_res.lat" />
            </el-form-item>
            <el-form-item label-width="80" label="X偏移量">
              <el-input v-model="origin_res.offset_x" />
            </el-form-item>
            <el-form-item label-width="80" label="Y偏移量">
              <el-input v-model="origin_res.offset_y" />
            </el-form-item>
            <el-form-item label-width="80" label="旋转角度">
              <el-input v-model="origin_res.rotate" />
            </el-form-item>
          </el-form>

          <div class="demo-drawer__footer">
            <el-button type="primary" :loading="loading" @click="saveOrigin">
              保存原点
            </el-button>
          </div>
        </div>
      </el-drawer>
    </el-dialog>

    <el-dialog
      :destroy-on-close="true"
      title="坐标转换"
      v-model="showConvert"
      width="400px"
      append-to-body
    >
      <el-form style="margin-top: 20px">
        <el-form-item label-width="80" label="原始文件">
          <el-upload
            v-model:file-list="fileList"
            action=""
            :auto-upload="true"
            :multiple="true"
            :http-request="httpRequestConvert"
            :on-change="onChangeFile"
          >
            <template #trigger>
              <el-button type="primary">选择文件</el-button>
            </template>
            <template #tip>
              <div class="el-upload__tip">json/geojson 文件</div>
            </template>
          </el-upload>
        </el-form-item>
      </el-form>

      <div class="demo-drawer__footer">
        <el-button type="primary" :loading="loading" @click="exportRes">
          导出结果
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup name="Base">
import { listBase, getBase, delBase, addBase, updateBase } from "@/api/admin/base";

const { proxy } = getCurrentInstance();
import MarsMap from "@/components/Map/mars-map.vue";
const configUrl = "config/config.json";
import * as MapRegistration from "@/views/main/MapRegistration.js";
const originDrawer = ref(false);
const showMap = ref(false);
const showConvert = ref(false);
const baseList = ref([]);
const fileList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const origin = ref({
  lng: 119.63680685139437,
  lat: 26.479694853684066,
  offset_x: 0,
  offset_y: 0,
  rotate: 0,
});

const origin_res = ref({
  lng: 0,
  lat: 0,
  offset_x: 0,
  offset_y: 0,
  rotate: 0,
});

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    code: null,
    abbr: null,
    position: null,
    area: null,
    haveLandArea: null,
    reportArea: null,
    elevationSystem: null,
    pipelineArrangementPrinciple: null,
  },
  rules: {},
});

const { queryParams, form, rules } = toRefs(data);

/** 查询基地列表 */
function getList() {
  loading.value = true;
  listBase(queryParams.value).then((response) => {
    baseList.value = response.rows;
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
    code: null,
    abbr: null,
    position: null,
    area: null,
    havelandarea: null,
    reportarea: null,
    elevationsystem: null,
    pipelinearrangementprinciple: null,
  };
  proxy.resetForm("baseRef");
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
  title.value = "添加基地";
}
/*************************************/
let localRow;
let localMap;
const marsOnload = async (map) => {
  localMap = map;
};

function setToView() {
  localRow.view = JSON.stringify(localMap.getCameraView());
  updateBase(localRow).then((response) => {
    proxy.$modal.msgSuccess("修改成功");
    getList();
  });
}

//保存原点
function saveOrigin() {
  localRow.origin = JSON.stringify(origin_res.value);
  localRow.offset = JSON.stringify(origin_res.value);
  updateBase(localRow).then((response) => {
    proxy.$modal.msgSuccess("修改成功");
    getList();
  });
}

function flyToView() {
  if (localRow.view) {
    let view = JSON.parse(localRow.view);
    localMap.setCameraView(view);
  }
}

function handleUpdateMap(row) {
  localRow = row;
  showMap.value = true;
}

async function httpRequest(options) {
  let file = options.file;

  if (file) {
    const blob = new Blob([file], { type: file.type });
    const url = URL.createObjectURL(blob);
    origin.value.url = url;
    // const response = await fetch(url);
    // const textContent = await response.text();
    // console.log(textContent);
  }
  console.log(options);
}

function onChangeFile(file, files) {
  // console.log("file",file)
  // console.log("files",files)
  // console.log("fileList",fileList)
}
function httpRequestConvert(options) {
  // console.log(options);
}

async function exportRes() {
  loading.value = true;
  let list = fileList.value;
  let origin = JSON.parse(localRow.origin);

  console.log(origin);

  //
  MapRegistration.setOriginPoint(origin.lng, origin.lat);
  MapRegistration.setRotate(origin.rotate);
  MapRegistration.setOffset(origin.offset_x, origin.offset_y);

  for (let i = 0; i < list.length; i++) {
    let file = list[i].raw;
    const blob = new Blob([file], { type: file.type });
    const url = URL.createObjectURL(blob);
    const response = await fetch(url);
    const textContent = await response.text();

    //console.log(textContent)
    let res = formatGeoJSON(JSON.parse(textContent));
    dowmLObj(res, file.name);
  }

  loading.value = false;
}

function dowmLObj(obj, fileName) {
  // 将对象转换为JSON字符串
  const objString = JSON.stringify(obj); // 使用2作为缩进，使输出更易读

  // 创建一个Blob对象，类型为纯文本
  const blob = new Blob([objString], { type: "text/plain" });

  // 创建一个链接元素用于下载
  const link = document.createElement("a");
  link.href = window.URL.createObjectURL(blob);
  link.download = fileName;

  // 模拟点击链接进行下载
  document.body.appendChild(link);
  link.click();

  // 清除链接元素
  document.body.removeChild(link);
  window.URL.revokeObjectURL(link.href); // 释放掉blob对象使用的内存
}

function opneOrigindConvert() {
  if (!localRow.origin) {
    proxy.$modal.msgError("当前基地坐标原点为空，请先进行原点测试！");
    return;
  }
  showConvert.value = true;
}

function opneOrigindDrawer() {
  if (localRow.origin) {
    let origin = JSON.parse(localRow.origin);
    origin_res.value = origin;
  }
  originDrawer.value = true;
}

function formatGeoJSON(geojson) {
  let features = geojson.features;

  let geoType = null;
  if (features && features.length > 0) {
    geoType = features[0].geometry.type;
  }
  geojson = MapRegistration.formatGeoJSON_All(geojson, geoType);
  console.log(geojson);

  return geojson;
}

let geoJsonLayer = null;

function verification() {
  let url = origin.value.url;

  MapRegistration.setOriginPoint(origin.value.lng, origin.value.lat);
  MapRegistration.setRotate(origin.value.rotate);
  MapRegistration.setOffset(origin.value.offset_x, origin.value.offset_y);
  if (geoJsonLayer) {
    localMap.removeLayer(geoJsonLayer);
    geoJsonLayer = null;
  }
  geoJsonLayer = new mars3d.layer.GeoJsonLayer({
    format: formatGeoJSON, // 用于自定义处理geojson
    url: url,
    symbol: {
      styleOptions: {
        fill: true,
        randomColor: true, // 随机色
        opacity: 0.3,
        outline: true,
        outlineStyle: {
          color: "#FED976",
          width: 3,
          opacity: 1,
        },
      },
    },
    popup: "all",
    flyTo: true,
  });
  localMap.addLayer(geoJsonLayer);
}

function calculation() {
  let res = MapRegistration.getPositionTranslationCartographic({
    x: 0,
    y: 0,
    z: 0,
  });

  origin_res.value.lng = res[0];
  origin_res.value.lat = res[1];
  origin_res.value.rotate = origin.value.rotate;
}

/**************************************/

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getBase(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改基地";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["baseRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateBase(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addBase(form.value).then((response) => {
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
    .confirm('是否确认删除基地编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delBase(_ids);
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
    "admin/base/export",
    {
      ...queryParams.value,
    },
    `base_${new Date().getTime()}.xlsx`
  );
}

getList();
</script>

<style>
.SYSMAP-modal {
  pointer-events: none;
}
.SYSMAP-drawer {
  pointer-events: all;
}
</style>
