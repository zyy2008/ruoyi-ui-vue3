<template>
  <div class="expImage-div">
    <div class="dialog-footer">
      <el-button type="primary" @click="submitForm">导出当前场景</el-button>
    </div>
  </div>
</template>

<script setup name="ExpImage">
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

import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map.js";

const data = reactive({
  form: {
    ids: [],
    tagert: [],
  },
  rules: {
    ids: [{ required: true, message: "目标图层不能为空", trigger: "blur" }],
    tagert: [
      {
        required: true,
        message: "裁剪范围不能为空,请在地图中绘制标注",
        trigger: "blur",
      },
    ],
  },
});

const { form, rules } = toRefs(data);

onMounted(() => {});

onBeforeUnmount(() => {});

// 启用map.ts生命周期
useLifecycle(mapWork);

// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    data: null,
    dataType: null,
    remark: null,
  };
}

/** 提交按钮 */
async function submitForm() {


  // map.expImage();
  // return;
   let img = await mapWork.showMapImg();
  //let img = mapWork.getScale();

  mapWork.exportImg(img)

}
</script>

<style  lang="scss">
.expImage-div {
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
