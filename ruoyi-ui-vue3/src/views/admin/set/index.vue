<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="父节点" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入父节点"
          clearable
          @keyup.enter="handleQuery"
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
          v-hasPermi="['admin:set:add']"
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
      v-if="refreshTable"
      v-loading="loading"
      :data="setList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column label="名称" prop="name" />
      <el-table-column label="所属基地" align="center" prop="baseName" />
      <el-table-column label="部门" align="center" prop="deptName" />
      <el-table-column label="用户" align="center" prop="userId" />
      <el-table-column label="角色" align="center" prop="roleId" />
      <el-table-column label="是否是组节点" align="center" prop="isGroup" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:set:edit']"
          >
            修改
          </el-button>
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:set:remove']"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改图层集对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="setRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" maxlength="30" />
        </el-form-item>
        <el-form-item label="所属基地" prop="baseId">
          <el-tree-select
            v-model="form.baseId"
            :data="baseOptions"
            :props="{ value: 'id', label: 'label', children: 'children' }"
            value-key="id"
            placeholder="请选择所属基地"
            check-strictly
            @change="baseIdChange"
          />
        </el-form-item>
        <el-form-item label="所属部门" prop="deptId">
          <el-tree-select
            v-model="form.deptId"
            :data="deptOptions"
            :props="{ value: 'id', label: 'label', children: 'children' }"
            value-key="id"
            placeholder="请选择归属部门"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="图层组合">
          <el-checkbox
            v-model="menuExpand"
            @change="handleCheckedTreeExpand($event, 'menu')"
            >展开/折叠</el-checkbox
          >
          <el-checkbox
            v-model="menuNodeAll"
            @change="handleCheckedTreeNodeAll($event, 'menu')"
            >全选/全不选</el-checkbox
          >
          <el-checkbox
            v-model="form.menuCheckStrictly"
            @change="handleCheckedTreeConnect($event, 'menu')"
            >父子联动</el-checkbox
          >
          <el-tree
            class="tree-border"
            :data="menuOptions"
            show-checkbox
            ref="menuRef"
            node-key="id"
            :check-strictly="!form.menuCheckStrictly"
            empty-text="请先选择所属基地"
            :props="{
              label: 'label',
              children: 'children',
              disabled: 'disabled',
            }"
          ></el-tree>
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

<script setup name="Set">
import { listSet, getSet, delSet, addSet, updateSet } from "@/api/admin/set";
import { deptTreeSelect, listUser } from "@/api/system/user";
import { listBase } from "@/api/admin/base";
import { classificationTree } from "@/api/layers.js";
const { proxy } = getCurrentInstance();
import { getSetTree } from "@/api/admin/set";
import useUserStore from "@/store/modules/user";
const userStore = useUserStore();
const setList = ref([]);
const setOptions = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const isExpandAll = ref(true);
const refreshTable = ref(true);
const deptOptions = ref(undefined);
const baseOptions = ref(undefined);

const userOptions = ref(undefined);

const menuRef = ref("menuRef");
const menuExpand = ref(false);
const menuNodeAll = ref(false);
const menuOptions = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    name: null,
    deptId: null,
    userId: null,
    roleId: null,
    isGroup: null,
    pid: null,
    menuCheckStrictly: true,
  },
  rules: {
    name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
    baseId: [{ required: true, message: "基地不能为空", trigger: "change" }],
    deptId: [{ required: true, message: "部门不能为空", trigger: "change" }],
    userId: [{ required: true, message: "用户不能为空", trigger: "change" }],
    roleId: [{ required: true, message: "角色不能为空", trigger: "change" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 树权限（展开/折叠）*/
function handleCheckedTreeExpand(value, type) {
  if (type == "menu") {
    let treeList = menuOptions.value;
    for (let i = 0; i < treeList.length; i++) {
      menuRef.value.store.nodesMap[treeList[i].id].expanded = value;
    }
  } else if (type == "dept") {
    let treeList = deptOptions.value;
    for (let i = 0; i < treeList.length; i++) {
      deptRef.value.store.nodesMap[treeList[i].id].expanded = value;
    }
  }
}

/** 树权限（全选/全不选） */
function handleCheckedTreeNodeAll(value, type) {
  if (type == "menu") {
    menuRef.value.setCheckedNodes(value ? menuOptions.value : []);
  } else if (type == "dept") {
    deptRef.value.setCheckedNodes(value ? deptOptions.value : []);
  }
}

/** 树权限（父子联动） */
function handleCheckedTreeConnect(value, type) {
  if (type == "menu") {
    form.value.menuCheckStrictly = value ? true : false;
  } else if (type == "dept") {
    form.value.deptCheckStrictly = value ? true : false;
  }
}

/** 查询图层集列表 */
function getList() {
  loading.value = true;
  listSet(queryParams.value).then((response) => {
    setList.value = proxy.handleTree(response.data, "id", "pid");
    loading.value = false;
  });
}

/** 查询图层集下拉树结构 */
function getTreeselect() {
  listSet().then((response) => {
    setOptions.value = [];
    const data = { id: 0, name: "顶级节点", children: [] };
    data.children = proxy.handleTree(response.data, "id", "pid");
    setOptions.value.push(data);
  });
}

/** 查询部门下拉树结构 */
function getDeptTree() {
  let params = {};
  if (form.value.baseId) {
    params.baseId = form.value.baseId;
  }
  deptTreeSelect(params).then((response) => {
    deptOptions.value = response.data;
  });
}
function getUserTree() {
  listUser().then((response) => {
    userOptions.value = response.rows;
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
    deptId: null,
    userId: null,
    roleId: null,
    isGroup: null,
    pid: null,
  };
  proxy.resetForm("setRef");
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
  title.value = "添加图层集";
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false;
  isExpandAll.value = !isExpandAll.value;
  nextTick(() => {
    refreshTable.value = true;
  });
}

//根据基地ID查询树形结构
function getSetMenuTreeselect(row) {
  return getSetTree({
    baseId: [row.baseId].join(","),
  }).then((response) => {
    // let data = response.data;
    // if (data && data.length > 0) {
    //   let base = data[0];
    //   let layerSetVoList = base.layerSetVoList.filter((s) => s.id == row.id);
    //   if (layerSetVoList && layerSetVoList.length > 0) {
    //     setMenuOptions(layerSetVoList[0].elementClassificationVoList);
    //   }
    // }
    return response;
  });
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset();
  if (row != null) {
    form.value.pid = row.pid;
  }
  const roleMenu = getSetMenuTreeselect(row);

  let resClass = await classificationTree({
    baseId: row.baseId,
  });

  //查询所有的
  let data_resClass = resClass.data;
  setMenuOptions(data_resClass);

  getSet(row.id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改图层集";

    nextTick(() => {
      roleMenu.then((res) => {
        let data = res.data;
        if (data && data.length > 0) {
          let base = data[0];
          let layerSetVoList = base.layerSetVoList.filter((s) => s.id == row.id);
          if (layerSetVoList && layerSetVoList.length > 0) {
            console.log(layerSetVoList[0]);
            let elementClassificationVoList =
              layerSetVoList[0].elementClassificationVoList;
            elementClassificationVoList.forEach((element) => {
              element.elementClassificationNormVoList.map((node) => {
                let key = node.id + "_" + node.name;
                if (node.layer) {
                  nextTick(() => {
                    console.log(key);
                    menuRef.value.setChecked(element.id, true, false); //父节点
                    menuRef.value.setChecked(key, true, false);
                  });
                }
              });
            });
          }
        }
      });
    });
  });
}

//只获取子节点被选中的id
function getMenuAllCheckedKeys() {
  // 目前被选中的菜单节点
  let checkedKeys = menuRef.value.getCheckedKeys();
  // 半选中的菜单节点
  let halfCheckedKeys = menuRef.value.getHalfCheckedKeys();
  checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);

  let leafs = [];
  checkedKeys.forEach((ele) => {
    let ele_str = ele + "";
    let arr = ele_str.split("_");
    if (arr.length > 1) {
      leafs.push(Number(arr[0]));
    }
  });
  return leafs;
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["setRef"].validate((valid) => {
    if (valid) {
      let codes = getMenuAllCheckedKeys();
      form.value.isGroup = false;
      form.value.pid = 0;
      form.value.norm = codes;
      form.value.userId = userStore.id;
      form.value.roleId = userStore.roleId;
      console.log(form.value);
      if (form.value.id != null) {
        updateSet(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSet(form.value).then((response) => {
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
    .confirm('是否确认删除图层集编号为"' + row.id + '"的数据项？')
    .then(function () {
      return delSet(row.id);
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

function setMenuOptions(data) {
  let optios = [];
  data.forEach((element) => {
    optios.push({
      id: element.id,
      label: element.name,
      children: element.elementClassificationNormVoList.map((node) => {
        let status = node.optional ? "已上传" : "未上传";
        return {
          id: node.id + "_" + node.name,
          label: node.name + `(${status})`,
          disabled: !node.optional,
        };
      }),
    });
  });

  menuOptions.value = optios;
}

function getClassificationTree() {
  classificationTree({
    baseId: form.value.baseId,
  }).then((response) => {
    let data = response.data;
    setMenuOptions(data);
  });
}

function baseIdChange(val) {
  form.value.deptId = null;
  getClassificationTree();
  getDeptTree();
}

getBase();
getDeptTree();
getUserTree();
getList();
</script>
