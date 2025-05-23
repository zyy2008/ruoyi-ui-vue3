<template>
  <div class="SelectPoint">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="wgs84坐标" name="first">
        <el-form ref="postRef" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="经度" prop="jd">
            <el-input v-model="form.jd" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="纬度" prop="wd">
            <el-input v-model="form.wd" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="高程" prop="alt">
            <el-input v-model="form.alt" placeholder="请输入" />
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <!-- <el-tab-pane v-show="false" label="局部坐标" name="second">
        <el-form
          ref="postRef_JB"
          :model="formJB"
          :rules="rulesJB"
          label-width="80px"
        >
          <el-form-item label="X坐标" prop="jd">
            <el-input v-model="formJB.jd" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="Y坐标" prop="wd">
            <el-input v-model="formJB.wd" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="高程" prop="alt">
            <el-input v-model="formJB.alt" placeholder="请输入" />
          </el-form-item>
        </el-form>
      </el-tab-pane> -->
    </el-tabs>
    <div class="dialog-footer">
      <el-button type="primary" @click="bindMourseClick">图上拾取</el-button>
      <el-button type="primary" @click="submitForm">坐标定位</el-button>
    </div>
  </div>
</template>
<script  setup>
import {
  ref,
  watch,
  onMounted,
  onBeforeUnmount,
  reactive,
  toRefs,
  getCurrentInstance,
} from "vue";
import * as mars3d from "mars3d";
import {
  getPositionTranslationCartographic,
  getPositionTranslationOffest,
} from "./LocalWorldTransform";
const { proxy } = getCurrentInstance();
const data = reactive({
  form: {},
  formJB: {},
  rules: {
    jd: [{ required: true, message: "经度不能为空", trigger: "blur" }],
    wd: [{ required: true, message: "纬度不能为空", trigger: "blur" }],
    //alt: [{ required: true, message: "高程不能为空", trigger: "blur" }],
  },
  rulesJB: {
    jd: [{ required: true, message: "经度不能为空", trigger: "blur" }],
    wd: [{ required: true, message: "纬度不能为空", trigger: "blur" }],
    //alt: [{ required: true, message: "高程不能为空", trigger: "blur" }],
  },
});

const { form, rules, formJB, rulesJB } = toRefs(data);
let pointEntity = null;

const activeName = ref("first");

const handleClick = (tab, event) => {
  console.log(tab.paneName);
  activeName.value=tab.paneName;
  //84到局部
  if (tab.paneName == "second") {
    proxy.$refs["postRef"].validate((valid) => {
      if (valid) {
        console.log(1);
        let cart3 = Cesium.Cartesian3.fromDegrees(form.value.jd, form.value.wd);
        let offest = getPositionTranslationOffest(cart3);
        formJB.value.jd = offest.x;
        formJB.value.wd = offest.y;
        formJB.value.alt = form.value.alt;
      }
    });
  }

  if (tab.paneName == "first") {
    proxy.$refs["postRef_JB"].validate((valid) => {
      if (valid) {
        console.log(2);
        let arr = getPositionTranslationCartographic({
          x: formJB.value.jd,
          y: formJB.value.wd,
        });
        form.value.jd = arr[0];
        form.value.wd = arr[1];
        form.value.alt = formJB.value.alt;
      }
    });
  }
};

onMounted(() => {
  const point = map.getCenter();
  point.format();

  const currJD = point.lng;
  const currWD = point.lat;
  const currGD = 0;

  form.value.jd = mars3d.Util.formatNum(currJD, 6);
  form.value.wd = mars3d.Util.formatNum(currWD, 6);
  form.value.alt = mars3d.Util.formatNum(currGD, 6);
});

onBeforeUnmount(() => {
  if (pointEntity) {
    map.graphicLayer.removeGraphic(pointEntity);
    pointEntity = null;
  }
});

function bindMourseClick() {
  map.setCursor(true);
  map.once(mars3d.EventType.click, function (event) {
    map.setCursor(false);
    const cartesian = event.cartesian;
    const point = mars3d.LngLatPoint.fromCartesian(cartesian);
    point.format(); // 经度、纬度、高度

    const currJD = point.lng;
    const currWD = point.lat;
    const currGD = point.alt;

    form.value.jd = mars3d.Util.formatNum(currJD, 6);
    form.value.wd = mars3d.Util.formatNum(currWD, 6);
    form.value.alt = mars3d.Util.formatNum(currGD, 6);

    //
    if (activeName.value == "second") {
      proxy.$refs["postRef"].validate((valid) => {
        if (valid) {
          console.log(1);
          let cart3 = Cesium.Cartesian3.fromDegrees(
            form.value.jd,
            form.value.wd
          );
          let offest = getPositionTranslationOffest(cart3);
          formJB.value.jd = offest.x;
          formJB.value.wd = offest.y;
          formJB.value.alt = form.value.alt;
        }
      });
    }

    initPoint(false);
  });
}

/** 提交按钮 */
function submitForm() {
  if (activeName.value == "first") {
    proxy.$refs["postRef"].validate((valid) => {
      if (valid) {
        initPoint(true);
      }
    });
  }
  if (activeName.value == "second") {
    proxy.$refs["postRef_JB"].validate((valid) => {
      if (valid) {
        initPoint(true);
      }
    });
  }
}

function initPoint(hasCenter) {
  const position = [form.value.jd, form.value.wd, form.value.alt];

  if (pointEntity == null) {
    pointEntity = new mars3d.graphic.PointEntity({
      position,
      style: {
        color: "#3388ff",
        pixelSize: 10,
        outlineColor: "#ffffff",
        outlineWidth: 2,
      },
    });
    map.graphicLayer.addGraphic(pointEntity);
  } else {
    pointEntity.position = position;
  }
  if (hasCenter) {
    pointEntity.flyTo({ radius: 1000 });
  }
}
</script>

<style scoped lang="scss">
.SelectPoint {
  height: 28vh;
  padding-top: 16px;
}
.dialog-footer {
  text-align: center;
}
</style>
