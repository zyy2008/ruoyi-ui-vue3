<template>
  <div class="flowDirection-div">
    <div class="c-top">
      <!-- <div class="mainText">开始分析目标管线</div>
      <div class="subText">分析前先打开“工业管道_线”图层</div> -->

      <el-form ref="flowDirectionRef" :model="form" :rules="rules" label-width="100">
        <el-form-item label="分析方式" prop="radioType">
          <el-radio-group v-model="form.radioType" size="small">
            <el-radio @change="changeType" value="1" border>全部管线</el-radio>
            <el-radio @change="changeType" value="2" border>手动绘制</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="目标管线" prop="line">
          <el-cascader
            ref="cascaderRef"
            @change="changeline"
            v-model="form.line"
            :options="lineOptions"
            :props="{
              expandTrigger: 'hover',
              value: 'id',
              label: 'title',
            }"
          />
        </el-form-item>
      </el-form>
    </div>
    <div class="dialog-footer">
      <el-button class="submit-btn" type="primary" @click="submitForm"
        >开始分析</el-button
      >
    </div>
  </div>
</template>

<script setup name="FlowDirection">
import {
  ref,
  watch,
  onMounted,
  onBeforeUnmount,
  reactive,
  toRefs,
  getCurrentInstance,
  computed,
} from "vue";
import { ElMessage } from "element-plus";
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map.js";
import layerStore from "@/store/modules/layer";
const lineOptions = computed(() => layerStore().flowDirection);
const cascaderRef = ref(null);
const data = reactive({
  form: {
    radioType: "1",
    line: null,
    data: null,
  },
  rules: {
    radioType: [{ required: true, message: "分析方式不能为空", trigger: "change" }],
    line: [{ required: true, message: "目标管线不能为空", trigger: "change" }],
  },
});
const { form, rules } = toRefs(data);
const { proxy } = getCurrentInstance();
// 启用map.ts生命周期
useLifecycle(mapWork);

onBeforeUnmount(() => {
});

function changeline(val) {
  if (cascaderRef.value) {
    const checkedNodes = cascaderRef.value.getCheckedNodes();
    console.log("Checked Nodes:", checkedNodes);
    if (checkedNodes && checkedNodes.length > 0) {
      form.value.data = checkedNodes[0].data;
    }
  }
}

function submitForm() {
  proxy.$refs["flowDirectionRef"].validate((valid) => {
    if (valid) {
      mapWork.query(form.value);
    }
  });
}

function changeType() {
  cancel();
  mapWork.StopDraw(); //关闭编辑
  if (form.value.radioType == "2") {
    selectLine();
  }
}

function selectLine() {
  mapWork.drawPolygon();
}

function cancel() {
  mapWork.clearLayer();
}
</script>
<style lang="scss">
.flowDirection-div {
  padding-top: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .el-form-item__label,
  .el-radio__label {
    color: #fff;
  }
  .c-top {
    background-image: url("../../../assets/images/t-bg.png");
    background-size: cover;
    padding: 24px 16px 8px 16px;
    margin-bottom: 16px;
  }
  .mainText {
    font-family: MiSans;
    font-size: 16px;
    font-weight: bold;
    line-height: 16px;
    text-align: center;
    letter-spacing: 0em;
    font-variation-settings: "opsz" auto;
    font-feature-settings: "kern" on;
    color: #ffffff;
    text-align: left;
  }
  .subText {
    font-family: MiSans;
    font-size: 14px;
    font-weight: normal;
    line-height: 14px;
    text-align: center;
    letter-spacing: 0em;
    font-variation-settings: "opsz" auto;
    font-feature-settings: "kern" on;
    color: #ffffff;
    text-align: left;
    margin-top: 8px;
    margin-bottom: 16px;
  }
  > div {
    width: 98%;
  }
  .dialog-footer {
    text-align: center;
    padding-top: 16px;
    margin-bottom: 16px;
  }

  .start-btn {
    width: 120px;
    height: 32px;
    border-radius: 16px;
    opacity: 1;
    background: #ffffff;
    text-align: center;
    font-family: MiSans;
    font-size: 14px;
    font-weight: normal;
    text-align: center;
    letter-spacing: 0em;
    font-variation-settings: "opsz" auto;
    font-feature-settings: "kern" on;
    color: #000000;
    cursor: pointer;
  }
  .cancel-btn {
    margin-left: 8px;
    cursor: pointer;
    width: 120px;
    height: 32px;
    border-radius: 16px;
    opacity: 1;
    box-sizing: border-box;
    border: 1px solid #ffffff;
    font-family: MiSans;
    font-size: 14px;
    font-weight: normal;
    text-align: center;
    letter-spacing: 0em;
    font-variation-settings: "opsz" auto;
    font-feature-settings: "kern" on;
    color: #ffffff;
  }

  .submit-btn {
    width: 240px;
    height: 40px;
    border-radius: 4px;
    opacity: 1;
    background: #42be93;
    border: none;
  }
}
</style>
