<template>
  <div class="expImage-div">
    <div class="dialog-footer">
      <!-- <el-button type="primary" @click="submitForm">属性查询</el-button> -->

      <el-switch
        @change="submitForm"
        size="large"
        inline-prompt
        v-model="showLayer"
        class="mb-2"
        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
        active-text="开启"
        inactive-text="关闭"
      />
    </div>
  </div>
</template>

<script setup name="AempAnimation">
const { proxy } = getCurrentInstance();
import * as mars3d from "mars3d";
import {
  ref,
  watch,
  onMounted,
  onBeforeUnmount,
  reactive,
  toRefs,
  getCurrentInstance,
} from "vue";

let hasLoad = false;
let showLayer = ref(false);
const data = reactive({
  form: {
    ids: [],
    tagert: [],
  },
  rules: {},
});

const { form, rules } = toRefs(data);
let bloomTargetEffect;

onMounted(() => {});

onBeforeUnmount(() => {
  showLayer.value = false;
  clearData();
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

/** 提交按钮 */
async function submitForm(val) {
  console.log(val);

  if (val) {
    initDemoData();
    return;
  }
  clearData();
}
let layerGlobe;
function clearData() {
  if (layerGlobe) {
    map.graphicLayer.removeGraphic(layerGlobe)
    layerGlobe = null;
  }
}

async function initDemoData() {
    const graphic = new mars3d.graphic.RectangleEntity({
    coordinates: Cesium.Rectangle.fromDegrees(-180.0, -90.0, 180.0, 90.0),
    style: {
      height: 6000,
      materialType: mars3d.MaterialType.RectSlide,
      materialOptions: {
        image: "img/textures/cloud.png",
        color: Cesium.Color.WHITE.withAlpha(0.6),
        speed: 0.5,
        pure: true
      },
      distanceDisplayCondition: new Cesium.DistanceDisplayCondition(3000000, 100000000)
    }
  })
  map.graphicLayer.addGraphic(graphic)
  layerGlobe=graphic;
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
