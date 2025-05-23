<template>
  <div class="expImage-div">
    <div class="dialog-footer">
      <!-- <el-button type="primary" @click="submitForm">属性查询</el-button> -->

      <el-switch
        @change="submitForm"
        size="large"
        inline-prompt
        v-model="layerStore().attribute_flag"
        class="mb-2"
        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
        active-text="开启"
        inactive-text="关闭"
      />
    </div>
  </div>
</template>

<script setup name="Attribute">
const { proxy } = getCurrentInstance();
const mapNames = [{ label: "建筑物面", value: "jzm" }];
import * as mars3d from "mars3d";
import layerStore from "@/store/modules/layer";
import {
  ref,
  watch,
  onMounted,
  onBeforeUnmount,
  reactive,
  toRefs,
  getCurrentInstance,
} from "vue";

let showLayer = ref(false);
let htmlComponent = ref("");
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
let bloomTargetEffect;

onMounted(() => {
  // 添加特效
  // bloomTargetEffect = new mars3d.effect.BloomTargetEffect({
  //   // objectsToExclude: [tiles3dLayer, graphicModel], // 排除不拾取的对象
  //   eventType: mars3d.EventType.click, // 单击时拾取
  //   color: Cesium.Color.YELLOW,
  //   contrast: 119,
  //   brightness: 0.05,
  //   delta: 0.9,
  //   sigma: 3.78,
  //   stepSize: 5,
  // });
  // map.addEffect(bloomTargetEffect);
});

onBeforeUnmount(() => {
  htmlComponent.value = "";
  showLayer.value = false;
});

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

// watch(
//   () => layerStore().clipLayersCode,
//   (data) => {
//     if (data) {
//       addLiyersClick();
//     }
//   },
//   { immediate: true }
// );

function addLiyersClick() {
  let layerCodes = layerStore().clipLayersCode;

  for (let i = 0; i < layerCodes.length; i++) {
    let code = layerCodes[i];
    let layer = map.getLayer(code, "code");
    if (layer) {
      //layer.off(mars3d.EventType.click);
      //layer.unbindHighlight();
      layer.on(mars3d.EventType.click, function (event) {
        console.log("监听layer，单击了矢量对象", event);
        showLayer.value = false;
        if (layerStore().attribute_flag && event.attr) {
          // layer.openHighlight({
          //   type: "polylineP",
          //   width: 10,
          //   clampToGround: true,
          //   materialType: "PolylineGlow",
          //   materialOptions: {
          //     color: "#ff0000",
          //     glowPower: 0.1, // 发光强度
          //   },
          // });
          console.log(event.attr);
          htmlComponent.value = "";
          htmlComponent.value = layerStore.getPopup(event);
          showLayer.value = true;
        }
      });
    }
  }
}

/** 提交按钮 */
async function submitForm(val) {
  console.log(val);
  layerStore().setAttribute_query(val);
}
</script>

<style lang="scss">
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
