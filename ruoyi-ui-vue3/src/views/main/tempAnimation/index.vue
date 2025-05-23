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

//import { consts, DataAnimationService, getBinary, GridDataGLFillMode, QEGridDataProvider, resourceService } from "@quickearth/core"
//import { CPixelLayer } from "@quickearth/cesium";
const {
  consts,
  DataAnimationService,
  getBinary,
  GridDataGLFillMode,
  QEGridDataProvider,
  resourceService,
} = window.QE; // quickearth.core.js
const { CPixelLayer } = window.QEC; // quickearth.cesium.js
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
let aniServiceGlobe;
function clearData() {
  if (aniServiceGlobe) {
    aniServiceGlobe.stop();
    map.scene.primitives.remove(layerGlobe);
    layerGlobe = null;
    aniServiceGlobe = null;
  }
}

async function initDemoData() {
  // public静态资源的路径
  consts.resourcePath = "quickearth";
  //consts.resourcePath = "https://data.mars3d.cn/file/qe"
  // consts.defaultLegendPath = "//data.mars3d.cn/file/qe/styles/colors"
  // consts.wasmPath = "/lib/mars3d/thirdParty/quickearth/wasm"
  // consts.workerPath = "/lib/mars3d/thirdParty/quickearth/workers"

  globalMsg("数据加载中...");
  // config资源配置
  await resourceService.loadResourceFromConfigPath("demos/styles/demo.config.json");
  //await resourceService.loadResourceFromConfigPath("styles/demo.config.json")

  createTempAnimation();
}

const createTempAnimation = async () => {
  const buffers = await getBinary("quickearth/year.ano.zip");
  const provider = new QEGridDataProvider(buffers[0]);
  const layer = new CPixelLayer({
    interpFromPreSource: true,
  })
    .setDrawOptions({
      fillColor: "color-temp-ano#res",
      fillMode: GridDataGLFillMode.pixel1,
      fillModeForLine: GridDataGLFillMode.shaded2,
      lineColor: "white",
      showLine: true,
    })
    .setDataSource(provider);
  map.scene.primitives.add(layer);
  layerGlobe=layer;
  const aniService = new DataAnimationService(provider, {
    layer: layer,
    all: provider.allGrids().length,
  });
  aniService.start();
  aniServiceGlobe=aniService;
  globalMsg("数据加载完成");
};
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
