<template>
  <div class="crossSection-div">
    <div class="c-top">
      <div class="mainText">开始绘制目标管线</div>
      <div class="subText">绘制前先选择“目标管线”图层</div>
      <el-form
        ref="crossSectionRef"
        :model="form"
        :rules="rules"
        label-width="100"
      >
        <el-form-item label="目标管线">
          <el-switch
            @change="switchLayer"
            size="large"
            inline-prompt
            v-model="switchLayerFlag"
            class="mb-2"
            style="
              --el-switch-on-color: #13ce66;
              --el-switch-off-color: #ff4949;
            "
            active-text="开启"
            inactive-text="关闭"
          />

          <!-- <el-select
            @change="changeline"
            class="select-with-layer"
            v-model="form.line"
            multiple
            placeholder="请选择基地"
          >
            <el-option
              v-for="item in lineOptions"
              :key="item.id"
              :label="item.title"
              :value="item.id"
            ></el-option>
          </el-select> -->
          <!-- <el-cascader
            ref="cascaderRef"
            @change="changeline"
            v-model="form.line"
            multiple
            :options="lineOptions"
            :props="{
              expandTrigger: 'hover',
              value: 'id',
              label: 'title',
            }"
          /> -->
        </el-form-item>

        <el-form-item label="" prop="res">
          <div class="start-btn" @click="selectLine()">开始绘制</div>
          <div class="cancel-btn" @click="cancel">清除选择</div>
        </el-form-item>
      </el-form>
    </div>
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

<script setup name="CrossSection">
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
const lineOptions = computed(() => layerStore().sectionLayersOne);
import crossSectionHelp from "./CrossSectionHelp.js";
let crossSection;
import * as mars3d from "mars3d";
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
let FeatureGlobeArr = null;
let layerCache = null;
let graphic = null;
let switchLayerFlag = ref(false);
let visible_Chart = ref(false);
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
import * as mapWork from "./map.js";
const cascaderRef = ref(null);
onMounted(() => {
  console.log("canvas");
  crossSection = new crossSectionHelp("canvas");

  // crossSection.setOptions({
  //   space: "我是间距",
  //   overall_length: "我是总长",
  //   graphic:graphic
  // });
  // crossSection.drawAll();
});

onBeforeUnmount(() => {
  lineOptions.value.forEach((layer) => {
    HideLayer(layer);
  });
  cancel();
});

// 启用map.ts生命周期
useLifecycle(mapWork);

function switchLayer(val) {
  if (val) {
    lineOptions.value.forEach((layer) => {
      activeLayer(layer);
    });
    return;
  }

  lineOptions.value.forEach((layer) => {
    HideLayer(layer);
  });
}

mapWork.eventTarget.on("selectLine", function (e) {
  if (e && e.features && e.features.length) {
    FeatureGlobeArr = e.features; //取到第一个
    console.log(e);
    form.value.res = e.features[0].id;
  }
});

let selectedLayers = [];
function changeline(val) {
  selectedLayers.forEach((layer) => {
    HideLayer(layer);
  });

  for (let i = 0; i < val.length; i++) {
    const itemObj = lineOptions.value.find((item) => {
      return item.id === val[i];
    });

    console.log(itemObj);
    selectedLayers.push(itemObj);
  }

  selectedLayers.forEach((layer) => {
    activeLayer(layer);
  });
  // console.log(val)
  // if (cascaderRef.value) {
  //   const checkedNodes = cascaderRef.value.getCheckedNodes();
  //   console.log("Checked Nodes:", checkedNodes);
  //   if (checkedNodes && checkedNodes.length > 0) {
  //     form.value.data = checkedNodes[0].data;
  //     activeLayer();
  //   }
  // }
}

function HideLayer(item) {
  let layer = window.map.getLayer(item.title, "name");
  if (layer) {
    layer.show = false;
  }
}

function activeLayer(item) {
  if (layerCache) {
    layerCache.show = false;
  }
  let layer = window.map.getLayer(item.title, "name");
  if (!layer) {
    ElMessage({
      message: "图层未加载，请在图层面板中加载【" + item.title + "】图层",
      type: "warning",
    });
    return;
  }
  // layerCache = layer;
  layer.show = true;
  if (!layer.isAdded) {
    mapWork.addLayer(layer); //==
  }

  if (!layer.options.noCenter) {
    // 在对应config.json图层节点配置 noCenter:true 可以不定位
    layer.readyPromise.then(function (layer) {
      layer.flyTo({ scale: 2 });
    });
  }
}

async function selectLine() {
  //let layer = window.map.getLayer("工业管道_线", "name");

  // if (!layer || !layer.show) {
  //   ElMessage({
  //     message: "图层未加载，请在图层面板中加载【工业管道_线】图层",
  //     type: "warning",
  //   });
  //   return;
  // }

  // console.log(layer);
  graphic = null;
  mapWork.clearLayer();
  graphic = await mapWork.drawAll(lineOptions.value);
  //graphic = await mapWork.drawAll(selectedLayers);

  //   layer.bindPopup("all");
  //   layer.bindHighlight({
  //     type: "polylineP",
  //     width: 10,
  //     clampToGround: true,
  //     materialType: "PolylineGlow",
  //     materialOptions: {
  //       color: "#ff0000",
  //       glowPower: 0.1, // 发光强度
  //     },
  //   });

  //   layer.on(mars3d.EventType.click, function (e) {
  //     console.log(e);
  //     FeatureGlobe = null;
  //     form.value.line = null;
  //     if (e && e.features && e.features.length) {
  //       FeatureGlobe = e.features[0]; //取到第一个
  //       form.value.line = FeatureGlobe.position;
  //     }
  //   });
}

function submitForm() {
  proxy.$refs["crossSectionRef"].validate((valid) => {
    if (valid) {
      visible_Chart.value = true;
      crossSection.setOptions({
        FeatureGlobeArr: FeatureGlobeArr,
        graphic: graphic,
      });
      crossSection.drawAll();
    }
  });
}

function cancel() {
  visible_Chart.value = false;
  FeatureGlobeArr = null;
  form.value.line = null;
  form.value.data = null;
  form.value.res = null;
  if (layerCache) {
    layerCache.show = false;
  }

  selectedLayers.forEach((layer) => {
    HideLayer(layer);
  });
  selectedLayers.length = 0;
  // let layer = window.map.getLayer("工业管道_线", "name");
  // if (layer) {
  //   layer.closeHighlight();
  //   layer.closePopup();
  // }

  mapWork.clearLayer();
}
</script>
<style lang="scss">
.crossSection-div {
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
