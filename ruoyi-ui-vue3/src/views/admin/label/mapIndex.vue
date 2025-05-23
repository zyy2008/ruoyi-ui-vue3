<template>
  <div class="app-container map-label-div">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="60px"
    >
      <el-form-item v-show="false" label="部门" prop="thisDeptId">
        <el-select
          style="width: 150px"
          v-model="form.thisDeptId"
          clearable
          placeholder="请选择归属部门"
        >
          <el-option
            v-for="item in layerSetIdOptions"
            :key="item.deptId"
            :label="item.deptName"
            :value="item.deptId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          style="width: 140px"
          v-model="queryParams.name"
          placeholder="请输入名称"
          maxlength="30"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <!-- <el-button icon="Refresh" @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
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
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Share"
          @click="handleShare"
          >分享</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="labelList"
      height="500"
      ref="multipleTableRef"
      @select-all="selectAll"
      @select="selectChange"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" width="180" align="center" prop="name" />
      <el-table-column label="类型" align="center" prop="dataType">
        <template #default="scope">
          <dict-tag :options="map_label_data_type" :value="scope.row.dataType" />
        </template>
      </el-table-column>
      <el-table-column
        :show-overflow-tooltip="true"
        label="备注"
        align="center"
        prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <a @click="handleFlyTo(scope.row)" class="a-btn">定位</a>
          <!-- <el-button
              link
              type="primary"
              icon="Delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['admin:label:remove']"
              >删除</el-button
            > -->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
      :layout="'total, prev, pager, next, jumper'"
    />

    <!-- 添加或修改地图标注对话框 -->
    <el-dialog
      draggable
      modal-class="dialog-pd-20 map-label-div-dialog map-dialog"
      :title="title"
      v-model="open"
      width="500px"
      :modal="false"
      :close-on-click-modal="false"
      append-to-body
    >
      <el-form ref="labelRef" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item v-show="!selectedlayerSet" label="图层集" prop="layerSetId">
          <el-tree-select
            v-model="form.layerSetId"
            :data="layerSetIdOptions"
            :props="{ value: 'id', label: 'name', children: 'layerSetVoList' }"
            value-key="id"
            placeholder="请选择归属图层集"
            @node-click="nodeClick"
          />
        </el-form-item> -->
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" maxlength="30" />
        </el-form-item>
        <el-form-item label="标注类型" prop="dataType">
          <!-- <el-select v-model="form.dataType" placeholder="请选择标注类型">
            <el-option
              v-for="dict in map_label_data_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select> -->

          <el-radio-group v-model="form.dataType">
            <el-radio
              :key="dict.value"
              v-for="(dict, index) in map_label_data_type"
              :value="dict.value"
              border
            >
              <img :src="getImageUrl(index)" />
              {{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="标注对象" prop="data">
          <!-- <el-input
              v-model="form.data"
              type="textarea"
              placeholder="请输入标注对象的JSON字符串"
              maxlength="30"
            /> -->
          <a @click="drawPoint()" class="a-btn">点击绘制</a>
        </el-form-item>

        <el-form-item label="备注">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button type="danger" @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog
      draggable
      modal-class="dialog-pd-20 map-dialog"
      :title="'分享'"
      v-model="openDept"
      width="80%"
      :modal="false"
      :close-on-click-modal="false"
      append-to-body
    >
      <DeptPage ref="queryDeptPage" />
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitShare">确 定</el-button>
          <el-button type="danger" @click="cancelShare">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Label">
import DeptPage from "@/views/main/label/share.vue";
import {
  shareLabel,
  listLabel,
  getLabel,
  delLabel,
  addLabel,
  updateLabel,
} from "@/api/admin/label";

const { proxy } = getCurrentInstance();
const { map_label_data_type } = proxy.useDict("map_label_data_type");
import {
  ref,
  watch,
  onMounted,
  onBeforeUnmount,
  reactive,
  toRefs,
  getCurrentInstance,
} from "vue";

import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map.js";
import useUserStore from "@/store/modules/user";
import layerStore from "@/store/modules/layer";
const multipleTableRef = ref(null);
const selectionRows = ref([]);
const labelList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const openDept = ref(false);

const data = reactive({
  form: {
    name: "标注",
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    data: null,
    dataType: null,
    layerSetId: "",
    deptId: null,
    userId: useUserStore().id
  },
  rules: {
    thisDeptId: [{ required: true, message: "归属部门不能为空", trigger: "blur" }],
    name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
    data: [
      {
        required: true,
        message: "标注不能为空,请在地图中绘制标注",
        trigger: "blur",
      },
    ],
    dataType: [{ required: true, message: "类型不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

const layerSetIdOptions = computed(() => layerStore().setDeps);
const thisDeptId = computed(() => {
  // queryParams.value.deptId = layerStore().selectedlayerSet;
  // form.value.thisDeptId = layerStore().selectedlayerSet;
  //getList();
  return layerStore().selectedlayerSet;
});
//是否可以编辑
const enabledEdit = ref(true);
import { useWidget } from "@mars/widgets/common/store/widget";
import * as mars3d from "mars3d";

// 数据编辑属性面板 相关处理
const { activate, disable, isActivate, updateWidget } = useWidget();
onMounted(() => {
  proxy.$bus.$on("labelStyleChange", (data) => {
    console.log(data);
    form.value.data = JSON.stringify(data);
  });
  //const mars3d = mapWork.mars3d;
  // if (!mapWork || mapWork.graphicLayer || !mapWork.graphicLayer.on) {
  //   return;
  // }
  // 矢量数据创建完成
  mapWork.graphicLayer.on(mars3d.EventType.drawCreated, function (e) {
    if (enabledEdit.value) {
      showEditor(e);
    }
  });
  // 修改了矢量数据
  mapWork.graphicLayer.on(
    [
      mars3d.EventType.editStart,
      mars3d.EventType.editMovePoint,
      mars3d.EventType.editStyle,
      mars3d.EventType.editRemovePoint,
    ],
    function (e) {
      showEditor(e);
    }
  );
  // 停止编辑
  mapWork.graphicLayer.on(
    [mars3d.EventType.editStop, mars3d.EventType.removeGraphic],
    function (e) {
      setTimeout(() => {
        if (!mapWork.graphicLayer.isEditing) {
          disable("graphic-editor");
        }
      }, 100);
    }
  );
});

onBeforeUnmount(() => {
  mapWork.clearData();
  proxy.$bus.$off("labelStyleChange");
});

function handleShare() {
  // if (!form.value.thisDeptId) {
  //   proxy.$modal.msgError("请先选择需要分享的归属部门");
  //   return;
  // }
  openDept.value = true;
}

const showEditor = (e) => {
  const graphic = e.graphic;
  if (!graphic._conventStyleJson) {
    graphic.options.style = graphic.toJSON().style; // 因为示例中的样式可能有复杂对象，需要转为单个json简单对象
    graphic._conventStyleJson = true; // 只处理一次
  }

  if (!isActivate("graphic-editor")) {
    activate({
      name: "graphic-editor",
      data: {
        graphic: markRaw(graphic),
      },
    });
  } else {
    updateWidget("graphic-editor", {
      data: {
        graphic: markRaw(graphic),
      },
    });
  }
};

// 启用map.ts生命周期
useLifecycle(mapWork);

function selectAll(selection) {
  selection.forEach((row) => {
    mapWork.loadLabel(row, true);
  });
}

function selectChange(selection, row) {
  let show = selection.length > 0 ? true : false;
  if (show) {
    if (row.view) {
      let view = JSON.parse(row.view);
      mapWork.flytoView(view);
    }
  }
  mapWork.loadLabel(row, show);
}

// 多选框选中数据
function handleSelectionChange(selection) {
  selectionRows.value = selection;
  ids.value = selection.map((item) => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
  //定位
  labelList.value.forEach((row) => {
    let show = ids.value.includes(row.id);
    mapWork.loadLabel(row, show);
  });
}

function drawPoint() {
  mapWork.drawAll(form, updateForm);
}

function updateForm(data) {
  form.value.data = data;
}
/** 查询地图标注列表 */
function getList() {
  loading.value = true;
  listLabel(queryParams.value).then((response) => {
    labelList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });


  getMapLabelIdList();
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 取消按钮
function cancelShare() {
  openDept.value = false;
}

// 表单重置
function reset() {
  form.value.id=null;
  form.value.name=null;
  form.value.data=null;
  form.value.dataType=null;
  form.value.remark=null;
  proxy.resetForm("labelRef");
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

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加地图标注";
}

function handleFlyTo(row) {
  toggleSelection([row]);
  if (row.view) {
    let view = JSON.parse(row.view);
    mapWork.flytoView(view);
  }
  mapWork.loadLabel(row, true);
}

function toggleSelection(rows, ignoreSelectable) {
  if (rows) {
    rows.forEach((row) => {
      multipleTableRef.value.toggleRowSelection(row, undefined, ignoreSelectable);
    });
  }
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getLabel(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改地图标注";
    mapWork.setUpdateForm(form.value, updateForm);
  });
}

function nodeClick(node) {
  //form.value.baseId = node.baseId;
}

/** 提交按钮 */
function submitForm() {
  console.log("标注", form);
  proxy.$refs["labelRef"].validate((valid) => {
    if (valid) {
      let view = mapWork.getMapView();

      //form.value.layerSetId = form.value.layerSetId;
      form.value.layerSetId = 0;
      form.value.view = JSON.stringify(view);
      form.value.deptId = useUserStore().deptId;
      form.value.userId = useUserStore().id;
      form.value.remark = form.value.remark || "无";
      if (form.value.id != null) {
        updateLabel(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addLabel(form.value).then((response) => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          disable("graphic-editor");
          getList();
        });
      }
    }
  });
}
let mapLabelIdList = [];
/** 提交按钮 */
function submitShare() {
  let ids = proxy.$refs["queryDeptPage"].getSelectIds();
  let baseIds = proxy.$refs["queryDeptPage"].getSelectBaseIds();
  console.log(ids);
  if (!ids || !ids.length) {
    proxy.$modal.msgError("请先选择需要分享的用户");
    return;
  }

  shareLabel({
    mapLabelId: mapLabelIdList.map((l) => l.id),
    //layerSetId: form.value.layerSetId,
    thisDeptId: [form.value.thisDeptId],
    userId: ids,
    baseId: baseIds,
  }).then((respnose) => {
    proxy.$modal.msgSuccess("分享成功");
    openDept.value = false;
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal
    .confirm('是否确认删除地图标注编号为"' + _ids + '"的数据项？')
    .then(function () {
      selectionRows.value.forEach((item) => {
        mapWork.removeGraphic(item);
      });
      return delLabel(_ids);
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
    "admin/label/export",
    {
      ...queryParams.value,
    },
    `label_${new Date().getTime()}.xlsx`
  );
}

function getImageUrl(index) {
  return new URL(`../../../assets/images/p_${index}.png`, import.meta.url).href;
}

function getMapLabelIdList() {
  listLabel({
    pageNum: 1,
    pageSize: 100,
    deptId: form.value.thisDeptId,
  }).then((response) => {
    mapLabelIdList = response.rows || [];
    mapLabelIdList = mapLabelIdList.filter((l) => !l.mapLabelId);
  });
}

queryParams.value.deptId = layerStore().selectedlayerSet;
form.value.thisDeptId = layerStore().selectedlayerSet;


getList();
</script>

<style scoped lang="scss">
.app-container {
  height: 80vh;
}
</style>

<style lang="scss">
.map-label-div-dialog {
  .el-radio-group {
    display: block;
    img {
      width: 24px;
      height: 24px;
      margin-right: 8px;
    }
    .el-radio.is-bordered {
      width: 140px;
      margin-right: 18px;
      .el-radio__label {
        display: flex;
        align-items: center;
      }
    }
  }
}

.map-label-div {
  .el-form-item__label,
  .el-radio__label {
    color: #fff;
  }
  .pagination-container {
    background: transparent;
    .el-pagination__jump,
    .el-pagination__sizes,
    .el-pagination__total {
      color: #fff;
    }
  }
}
</style>
