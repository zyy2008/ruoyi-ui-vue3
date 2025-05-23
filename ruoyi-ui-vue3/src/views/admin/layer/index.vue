<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="归属基地" prop="baseId">
        <el-tree-select
          style="width: 200px"
          v-model="queryParams.baseId"
          :data="baseOptions"
          :props="{ value: 'id', label: 'label', children: 'children' }"
          value-key="id"
          placeholder="请选择所属基地"
          check-strictly
        />
      </el-form-item>
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
          v-hasPermi="['admin:layer:add']"
          >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Sort" @click="toggleExpandAll"
          >展开/折叠
        </el-button>
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      height="75vh"
      v-if="refreshTable"
      v-loading="loading"
      :data="layerList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column label="父节点" align="center" prop="pid" />
      <el-table-column label="基地" prop="baseName" />
      <el-table-column label="名称" prop="name" />
      <el-table-column label="编码" prop="code" />
      <el-table-column label="服务类型" align="center" prop="serverType">
        <template #default="scope">
          <dict-tag :options="server_type" :value="scope.row.serverType" />
        </template>
      </el-table-column>
      <el-table-column width="420" label="访问地址" align="center" prop="url" />
      <el-table-column width="320" label="图层参数" align="center" prop="layerParam">
      </el-table-column>
      <el-table-column label="是否显示" align="center" prop="isShow" />
      <el-table-column
        width="220"
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:layer:edit']"
          >
            修改
          </el-button>
          <el-button
            link
            type="primary"
            icon="Plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['admin:layer:add']"
            >新增
          </el-button>
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:layer:remove']"
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

    <!-- 添加或修改图层对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="layerRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父节点" prop="pid">
          <el-tree-select
            v-model="form.pid"
            :data="layerOptions"
            :props="{ value: 'id', label: 'name', children: 'children' }"
            value-key="id"
            placeholder="请选择父节点"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="所属基地" prop="baseId">
          <el-tree-select
            v-model="form.baseId"
            :data="baseOptions"
            :props="{ value: 'id', label: 'label', children: 'children' }"
            value-key="id"
            placeholder="请选择所属基地"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" maxlength="30" />
        </el-form-item>
        <el-form-item label="要素编码" prop="code">
          <el-tree-select
            v-model="form.code"
            :data="normOptions"
            :props="{ value: 'id', label: 'label', children: 'children' }"
            value-key="id"
            placeholder="请选择要素编码"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="服务类型" prop="serverType">
          <el-select v-model="form.serverType" placeholder="请选择服务类型">
            <el-option
              v-for="dict in server_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="访问地址" prop="url">
          <el-input v-model="form.url" placeholder="请输入访问地址" maxlength="255" />
        </el-form-item>
        <el-form-item label="图层参数" prop="layerParam">
          <el-input
            v-model="form.layerParam"
            placeholder="属性名:属性值 示例(layer=ne:CL_X,type=line) 符号均为英文字符,多个参数使用逗号分隔"
          />
        </el-form-item>
        <el-form-item label="默认显示">
          <el-radio-group v-model="form.isShow">
            <el-radio value="true">是</el-radio>
            <el-radio value="false">否</el-radio>
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

<script setup name="Layer">
import { listLayer, getLayer, delLayer, addLayer, updateLayer } from "@/api/admin/layer";
import { listBase } from "@/api/admin/base";
import { listNorm } from "@/api/admin/norm";
const { proxy } = getCurrentInstance();
const { server_type, data_type } = proxy.useDict("server_type", "data_type");

const layerList = ref([]);
const layerOptions = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const isExpandAll = ref(true);
const refreshTable = ref(true);
const baseOptions = ref(undefined);
const normOptions = ref(undefined);
const total = ref(0);
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    url: null,
    serverType: null,
    pid: null,
    isShow: false,
    dataType: null,
  },
  rules: {
    name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
    serverType: [{ required: true, message: "服务类型不能为空", trigger: "blur" }],
    baseId: [{ required: true, message: "所属基地不能为空", trigger: "blur" }],
    code: [{ required: true, message: "要素编码不能为空", trigger: "blur" }],
    url: [{ required: true, message: "访问地址不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询图层列表 */
function getList() {
  loading.value = true;
  listLayer(queryParams.value).then((response) => {
    layerList.value = proxy.handleTree(response.data, "id", "pid");
    total.value = response.total;
    loading.value = false;
  });
}

/** 查询图层下拉树结构 */
function getTreeselect() {
  listLayer().then((response) => {
    layerOptions.value = [];
    const data = { id: 0, name: "顶级节点", children: [] };
    data.children = proxy.handleTree(response.data, "id", "pid");
    layerOptions.value.push(data);
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
    baseId: null,
    code: null,
    name: null,
    url: null,
    serverType: null,
    pid: null,
    isShow: null,
    dataType: null,
  };
  proxy.resetForm("layerRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset();
  getTreeselect();
  if (row != null && row.id) {
    form.value.pid = row.id;
  } else {
    form.value.pid = 0;
  }
  open.value = true;
  title.value = "添加图层";
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false;
  isExpandAll.value = !isExpandAll.value;
  nextTick(() => {
    refreshTable.value = true;
  });
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset();
  await getTreeselect();
  if (row != null) {
    form.value.pid = row.pid;
  }
  getLayer(row.id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改图层";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["layerRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateLayer(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addLayer(form.value).then((response) => {
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
  proxy.$modal
    .confirm('是否确认删除图层编号为"' + row.id + '"的数据项？')
    .then(function () {
      return delLayer(row.id);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
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

function getNorm() {
  listNorm({
    pageNum: 1,
    pageSize: 1000,
  }).then((response) => {
    normOptions.value = response.rows.map((item) => {
      return {
        id: item.code,
        label: item.code + `(${item.name})`,
        children: null,
      };
    });
  });
}
getNorm();
getBase();
getList();
</script>
