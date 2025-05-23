<template>
  <div class="shortestPath-div">
    <el-form ref="shortestPathRef" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="起点" prop="start">
        <a @click="drawPointQD()" class="a-btn">点击绘制</a>
      </el-form-item>
      <el-form-item label="终点" prop="end">
        <a @click="drawPointZD()" class="a-btn">点击绘制</a>
      </el-form-item>
    </el-form>
    <div class="dialog-footer">
      <el-button class="b288" type="primary" @click="submitForm">确 定</el-button>
      <el-button class="b288" type="danger" @click="cancel">清 除</el-button>
    </div>
  </div>
</template>

<script setup name="shortestPath">
import { getShortestPath } from "@/api/shortestPath.js";
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
import { ElMessage } from "element-plus";
const data = reactive({
  form: {
    start: null,
    end: null,
  },
  rules: {
    start: [{ required: true, message: "起点不能为空不能为空", trigger: "blur" }],
    end: [
      {
        required: true,
        message: "终点不能为空不能为空",
        trigger: "blur",
      },
    ],
  },
});

const { queryParams, form, rules } = toRefs(data);

onMounted(() => {});

onBeforeUnmount(() => {
  cancel();
});

// 启用map.ts生命周期
useLifecycle(mapWork);

function drawPointQD() {
  mapWork.startPoint();
}

function drawPointZD() {
  mapWork.endPoint();
}

// 表单重置
function cancel() {
  form.value = {
    start: null,
    end: null,
  };
  proxy.resetForm("shortestPathRef");
  mapWork.clearLayer();
}

/** 提交按钮 */
function submitForm() {
  form.value.start = mapWork.getDataQD();
  form.value.end = mapWork.getDataZD();
  proxy.$refs["shortestPathRef"].validate((valid) => {
    if (valid) {
      console.log(form);
      let data = {
        startAxisX: form.value.start[0],
        startAxisY: form.value.start[1],
        endAxisX: form.value.end[0],
        endAxisY: form.value.end[1],
      };

      getShortestPath(data)
        .then((res) => {
          console.log(res);

          if (!res || !res.data || res.data.length <= 1) {
            ElMessage({
              message: "未在当前路网数据中检索出路径，请修改后重新检索",
              type: "warning",
            });
            return;
          }
          mapWork.clearPath(); //清除历史
          res.data.forEach((element) => {
            mapWork.addGraphic(JSON.parse(element.geom));
          });
        })
        .catch((err) => {
          console.error(err);
        });
    }
  });
}
</script>

<style scoped lang="scss">
.shortestPath-div {
  padding-top: 20px;
  .dialog-footer {
    text-align: center;
    display: flex;
    gap: 16px;
    align-items: center;
    justify-content: center;
    flex-direction: column;
  }
}
</style>
