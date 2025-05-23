<template>
  <div class="verticalSection-div">
    <div class="c-top">
      <div class="mainText">开始绘制目标管线</div>
      <div class="subText">绘制前先选择“目标管线”图层</div>
      <el-form
        ref="verticalSectionRef"
        :model="form"
        :rules="rules"
        label-width="100"
      >
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
        <el-form-item label="" prop="res">
          <div class="start-btn" @click="selectLine()">开始选择</div>
          <div class="cancel-btn" @click="cancel">清除选择</div>
        </el-form-item>
      </el-form>
    </div>
    <!-- <el-form ref="verticalSectionRef" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="目标管线" prop="line">
        <el-button link type="primary" @click="selectLine()">点击选择</el-button>
      </el-form-item>
    </el-form>
    <div class="dialog-footer">
      <el-button type="primary" @click="submitForm">开始分析</el-button>
      <el-button type="danger" @click="cancel">清除选择</el-button>
    </div> -->

    <canvas
      v-show="visible_Chart"
      id="canvas"
      width="500"
      height="560"
    ></canvas>
    <div class="dialog-footer">
      <el-button class="submit-btn" type="primary" @click="submitForm"
        >开始分析</el-button
      >
    </div>
  </div>
</template>

<script setup name="VerticalSection">
const { proxy } = getCurrentInstance();
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
const lineOptions = computed(() => layerStore().sectionLayers);
import VerticalSectionHelp from "./VerticalSectionHelp.js";
let verticalSection;
import * as mars3d from "mars3d";
const cascaderRef = ref(null);
const data = reactive({
  form: {
    line: null,
    res: null,
  },
  rules: {
    line: [{ required: true, message: "目标管线不能为空", trigger: "blur" }],
    res: [
      { required: true, message: "查询结果为空，请重新选择", trigger: "blur" },
    ],
  },
});

const { form, rules } = toRefs(data);
import { ElMessage } from "element-plus";
let FeatureGlobe = null;
let layerCache = null;
let visible_Chart = ref(false);
onMounted(() => {
  console.log("canvas");
  verticalSection = new VerticalSectionHelp("canvas");

  // verticalSection.setOptions({
  //   space: "我是间距",
  //   overall_length: "我是总长",
  // });
  // verticalSection.drawAll();
});

onBeforeUnmount(() => {
  cancel();
});

function selectLine() {
  let layer = layerCache;

  //layer.bindPopup("all");
  layer.bindHighlight({
    type: "polylineP",
    width: 10,
    clampToGround: true,
    materialType: "PolylineGlow",
    materialOptions: {
      color: "#ff0000",
      glowPower: 0.1, // 发光强度
    },
  });
  $showLoading();
  layer.on(mars3d.EventType.click, function (e) {
    $hideLoading();
    console.log(e);
    if (e && e.features && e.features.length) {
      FeatureGlobe = e.features[0]; //取到第一个
      form.value.res = FeatureGlobe.properties.shape_leng;
    }

    window.showLayerAttribute(e)
  });
}

function changeline(val) {
  if (cascaderRef.value) {
    const checkedNodes = cascaderRef.value.getCheckedNodes();
    console.log("Checked Nodes:", checkedNodes);
    if (checkedNodes && checkedNodes.length > 0) {
      form.value.data = checkedNodes[0].data;
      activeLayer();
    }
  }
}

function activeLayer() {
  if (layerCache) {
    layerCache.show = false;
  }
  let layer = window.map.getLayer(form.value.data.title, "name");
  if (!layer) {
    ElMessage({
      message:
        "图层未加载，请在图层面板中加载【" + form.value.data.title + "】图层",
      type: "warning",
    });
    return;
  }
  layerCache = layer;
  layer.show = true;
  if (!layer.isAdded) {
    addLayer(layer); //==
  }

  if (!layer.options.noCenter) {
    // 在对应config.json图层节点配置 noCenter:true 可以不定位
    layer.readyPromise.then(function (layer) {
      layer.flyTo({ scale: 2 });
    });
  }
}
function addLayer(layer) {
  map.addLayer(layer);
}

function submitForm() {
  proxy.$refs["verticalSectionRef"].validate((valid) => {
    if (valid) {
      visible_Chart.value = true;
      let space = FeatureGlobe.properties.shape_leng;
      let overall_length = FeatureGlobe.properties.shape_leng;
      let pipelineElevation = [
        FeatureGlobe.properties.startdep_1,
        FeatureGlobe.properties.enddepthgc,
      ];
      let groundElevation = [
        FeatureGlobe.properties.start_surf,
        FeatureGlobe.properties.end_surf_h,
      ];

      let specification = [
        FeatureGlobe.properties.standard,
        FeatureGlobe.properties.standard,
      ];

      let classifys = [
        FeatureGlobe.properties.classify,
        FeatureGlobe.properties.classify,
      ];

      verticalSection.setOptions({
        space: space,
        overall_length: overall_length,
        pipelineElevation: pipelineElevation,
        groundElevation: groundElevation,
        specification: specification,
        classifys: classifys,
      });
      verticalSection.drawAll();
    }
  });
}

function cancel() {
  visible_Chart.value = false;
  FeatureGlobe = null;
  form.value.line = null;
  form.value.data = null;
  form.value.res = null;
  let layer = layerCache;
  if (layer) {
    layer.closeHighlight();
    layer.closePopup();
    layer.show = false;
  }
}
</script>
<style  lang="scss">
.verticalSection-div {
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
