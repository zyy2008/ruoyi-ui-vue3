<template>
  <div class="app-container">
    <el-form
      :model="form"
      :rules="rules"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="归属基地" prop="baseId">
        <el-tree-select
          style="width: 200px"
          v-model="form.baseId"
          :data="baseOptions"
          :props="{ value: 'id', label: 'label', children: 'children' }"
          value-key="id"
          placeholder="请选择所属基地"
          check-strictly
        />
      </el-form-item>
      <el-form-item label="发布模式" prop="mode">
        <el-select
          style="width: 200px"
          v-model="form.mode"
          placeholder="发布模式"
          clearable
        >
          <el-option
            v-for="dict in mode_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="工作空间" prop="workspaces">
        <el-select
          @change="changeWorkspaces"
          style="width: 200px"
          v-model="form.workspaces"
          placeholder="工作空间"
          clearable
        >
          <el-option
            v-for="work in WorkspacesOptions"
            :key="work"
            :label="work"
            :value="work"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="存储仓库" prop="dataSource">
        <el-select
          style="width: 200px"
          v-model="form.dataSource"
          placeholder="存储仓库"
          clearable
        >
          <el-option
            v-for="dataSource in DataSourceOptions"
            :key="dataSource"
            :label="dataSource"
            :value="dataSource"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          >一键发布</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table height="75vh" v-loading="loading" :data="manageList">
      <el-table-column label="名称" align="center" prop="name">
        <template #default="scope">
          <el-input
            v-model="scope.row.name"
            placeholder="请输入名称"
            clearable
          />
        </template>
      </el-table-column>
      <el-table-column label="要素编码" align="center" prop="code">
        <template #default="scope">
          <el-select
            style="width: 200px"
            v-model="scope.row.code"
            placeholder="要素编码"
            clearable
          >
            <el-option
              v-for="dict in normList"
              :key="dict.code"
              :label="dict.name"
              :value="dict.code"
            />
          </el-select>
        </template>
      </el-table-column>
      <!-- <el-table-column label="图层参数" align="center" prop="layerParam">
        <template #default="scope">
          <el-input
            v-model="scope.row.layerParam"
            placeholder="请输入图层参数"
            clearable
          />
        </template>
      </el-table-column> -->
      <el-table-column label="对应表名" align="center" prop="tableName">
        <template #default="scope">
          <el-input
            v-model="scope.row.tableName"
            placeholder="请选择表名"
            clearable
          >
            <template #append>
              <el-button :icon="Search" @click="openImportTable(scope.row)"
                >点击选择</el-button
              >
            </template>
          </el-input>
        </template>
      </el-table-column>
      <el-table-column label="对应样式" align="center" prop="tableName">
        <template #default="scope">
          <el-tree-select
            style="width: 200px"
            v-model="scope.row.tableStyle"
            :data="styleOptions"
            :props="{ value: 'id', label: 'label', children: 'children' }"
            value-key="id"
            placeholder="请选择所属样式"
            check-strictly
          />
          <!-- <el-input
            v-model="scope.row.tableStyle"
            placeholder="请选择样式"
            clearable
          >
            <template #append>
              <el-button :icon="Search" @click="openImportTable(scope.row)"
                >点击选择</el-button
              >
            </template>
          </el-input> -->
        </template>
      </el-table-column>
      <el-table-column
        width="180"
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button link type="primary" icon="Plus" @click="addRow"
            >新增</el-button
          >
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.$index)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <import-table ref="importRef" @ok="handleQueryTabel" />
  </div>
</template>

<script setup name="Publish">
import importTable from "./importTable";
import {
  listBase,
  getLayerStyle,
  publishLayer,
  getStyleByStyleName,
  getAllWorkspaces,
  getDataSourceByWorkspace,
} from "@/api/admin/base";
import { listNorm } from "@/api/admin/norm";
const { proxy } = getCurrentInstance();
import { Search } from "@element-plus/icons-vue";
const manageList = ref([]);
const loading = ref(false);
const showSearch = ref(true);

/**----*/
let currentRow = null;
const baseOptions = ref([]);
const styleOptions = ref([]);
const WorkspacesOptions = ref([]);
const DataSourceOptions = ref([]);

const normList = ref([]);

const mode_type = ref([
  {
    label: "覆盖",
    value: 1,
  },
  {
    label: "追加",
    value: 2,
  },
]);

import { dataList } from "./template.js";

/**----*/

const data = reactive({
  form: {},
  queryParams: {},
  rules: {
    baseId: [{ required: true, message: "归属基地不能为空", trigger: "blur" }],
    mode: [{ required: true, message: "发布模式不能为空", trigger: "blur" }],
    workspaces: [
      { required: true, message: "工作空间不能为空", trigger: "blur" },
    ],
    dataSource: [
      { required: true, message: "存储仓库不能为空", trigger: "blur" },
    ],
  },
});

const { queryParams, form, rules } = toRefs(data);

function getList() {
  manageList.value = dataList.slice(0, 1);
}

function getListlistNorm() {
  loading.value = true;
  listNorm({
    pageNum: 1,
    pageSize: 500,
  }).then((response) => {
    normList.value = response.rows;
    loading.value = false;
  });
}

function handleAdd() {
  console.log(manageList);

  let layers = manageList.value.filter((l) => {
    if (l && l.tableName) {
      return true;
    }
    return false;
  });

  proxy.$refs["queryRef"].validate((valid) => {
    if (valid) {
      loading.value = true;

      publishLayer({
        forcePublish: form.value.mode == 1,
        baseId: form.value.baseId,
        workspace: form.value.workspaces,
        datastore: form.value.dataSource,
        publishLayer: layers.map((m) => {
          return {
            name: m.name,
            code: m.code,
            param: `layer=${form.value.workspaces}:${m.tableName}`,
            tableName: m.tableName,
            style: m.tableStyle,
            dataType: "批量导入",
            pid: 0,
            serverType: "wms",
          };
        }),
      })
        .then((res) => {
          loading.value = false;
          proxy.$modal.msgSuccess("发布成功");
        })
        .catch((err) => {
          loading.value = false;
          console.error(err);
        });
    }
  });
}

function addRow() {
  manageList.value.push({});
}

/** 删除按钮操作 */
function handleDelete(index) {
  proxy.$modal
    .confirm("是否确认删除该数据项？")
    .then(function () {
      manageList.value.splice(index, 1); // 使用下标删除对应的行
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
}

function handleQueryTabel(table) {
  currentRow.tableName = table.tableName;
  console.log(table);
}

/** 打开导入表弹窗 */
function openImportTable(row) {
  currentRow = row;
  proxy.$refs["importRef"].show();
}

// 表单重置
function reset() {
  form.value = {
    baseId: null,
    mode: null,
  };
  proxy.resetForm("queryRef");
}

/** 重置按钮操作 */
function resetQuery() {
  reset();
}

function getAllWorkspacesSelect() {
  getAllWorkspaces().then((response) => {
    WorkspacesOptions.value = response.data;
  });
}

function getLayerStyleSelect() {
  let p = {
    workspace: form.value.workspaces || "ne",
  };

  console.log("p", p);
  getStyleByStyleName(p).then((response) => {
    styleOptions.value = response.data.map((item) => {
      return {
        id: item,
        label: item,
        children: null,
      };
    });
    console.log(styleOptions.value);
  });
}

function getBase() {
  listBase({
    pageNum: 1,
    pageSize: 100,
  }).then((response) => {
    baseOptions.value = response.rows.map((item) => {
      return {
        id: item.id,
        label: item.name,
        children: null,
      };
    });
  });
}

function getDataSourceByWorkspaceSelect() {
  DataSourceOptions.value = [];
  let p = {
    workspace: form.value.workspaces || "ne",
  };
  getDataSourceByWorkspace(p).then((response) => {
    DataSourceOptions.value = response.data;
  });
}

function changeWorkspaces() {
  getDataSourceByWorkspaceSelect();
  getLayerStyleSelect();
}

getList();
getListlistNorm();
getBase();
getAllWorkspacesSelect();
getLayerStyleSelect();
</script>
