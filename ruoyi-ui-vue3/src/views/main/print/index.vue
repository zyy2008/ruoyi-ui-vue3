<template>
  <div class="print-div">
    <el-form ref="printRef" :model="form" :rules="rules" label-width="80px">
      <!-- <el-form-item label="目标图层" prop="ids">
        <el-select v-model="form.ids" placeholder="请选择标注类型">
          <el-option
            v-for="dict in mapNames"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item label="裁剪范围" prop="tagert">
        <el-button link type="primary" @click="drawPoint()">点击绘制</el-button>
      </el-form-item>
    </el-form>
    <div class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </div>
</template>

<script setup name="Print">
import { getDxfByTarget } from "@/api/print.js";
const { proxy } = getCurrentInstance();
const mapNames = [{ label: "建筑物面", value: "jzm" }];
import {
  ref,
  watch,
  onMounted,
  onBeforeUnmount,
  reactive,
  toRefs,
  getCurrentInstance,
} from "vue";
import layerStore from "@/store/modules/layer";
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map.js";

const data = reactive({
  form: {
    ids: [],
    tagert: [],
  },
  rules: {
    // ids: [{ required: true, message: "目标图层不能为空", trigger: "blur" }],
    tagert: [
      {
        required: true,
        message: "裁剪范围不能为空,请在地图中绘制裁剪范围",
        trigger: "blur",
      },
    ],
  },
});

const { form, rules } = toRefs(data);

//是否可以编辑
const enabledEdit = ref(true);
import * as mars3d from "mars3d";

onMounted(() => {});

onBeforeUnmount(() => {});

// 启用map.ts生命周期
useLifecycle(mapWork);

function drawPoint() {
  mapWork.drawAll(form, updateForm);
}

function updateForm(data) {
  form.value.data = data;
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    data: null,
    dataType: null,
    remark: null,
  };
  proxy.resetForm("labelRef");
}

/** 提交按钮 */
function submitForm() {
  //{"type":"polygon","style":{"pixelSize":14,"color":"#3388ff","materialType":
  //"Color","clampToGround":false,"label":{"font_size":20,"color":"#ffffff",
  //"outline":true,"outlineColor":"#000000","pixelOffsetY":-20}},"_promise":{},
  //"id":"m-51d5b3b2-0748-4115-a837-919c6a785df5","name":"",
  //"positions":[
  //[119.644228,26.491236,11.9],[119.646159,26.489566,10.1],[119.645035,26.488097,13.7],
  //[119.643253,26.489108,12.3]]}

  let layersIds = mapWork.getTargetLayers(layerStore().clipLayersCode);
  form.value.ids = layersIds;
  form.value.tagert = [];
  if (form.value.data) {
    let positions = form.value.data.positions;
    positions.forEach((element) => {
      let cood = element[0] + " " + element[1];
      form.value.tagert.push(cood);
    });
  }
  console.log(form);

  proxy.$refs["printRef"].validate((valid) => {
    if (valid) {
      getDxfByTarget(form.value)
        .then((res) => {
          console.log(res);
          proxy.$modal.msgSuccess(res.msg);
          //handleExport(res.data, "裁剪区");
        })
        .catch((err) => {
          console.error(err);
        });
    }
  });
}

/** 导出按钮操作 */
function handleExport(url, filename) {
  //proxy.download(url, {}, `${name}_${new Date().getTime()}.xlsx`);

  // 创建一个临时的 <a> 元素
  const a = document.createElement("a");

  // 创建一个 URL 对象
  a.href = url;

  // 设置 <a> 元素的下载文件名
  a.download = filename;

  // 触发点击事件
  document.body.appendChild(a);
  a.click();

  // 移除临时的 <a> 元素
  document.body.removeChild(a);

  // 释放 URL 对象
  URL.revokeObjectURL(url);
}
</script>

<style lang="scss">
.print-div {
  padding-top: 20px;

  .el-form-item__label,
  .el-radio__label {
    color: #fff;
  }
  .dialog-footer {
    text-align: center;
  }
}
</style>
