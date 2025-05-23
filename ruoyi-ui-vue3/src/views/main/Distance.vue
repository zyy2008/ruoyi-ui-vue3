<template>
  <div class="Distance">
    <el-button class="b288" type="primary" @click="measureLength">开始量算</el-button>
    <el-button class="b288" type="danger" @click="removeAll">清除结果</el-button>
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
const { proxy } = getCurrentInstance();
let measure = null;
onMounted(() => {
  // 修改编辑点样式，比如大小
  //mars3d.DrawUtil.setAllEditPointStyle({ pixelSize: 14 });

  measure = new mars3d.thing.Measure({
    label: {
      color: "#ffffff",
      font_family: "楷体",
      font_size: 20,
      background: false,
    },
    isAutoEditing: false, // 绘制完成后是否自动激活编辑
    // drawEndEventType: mars3d.EventType.rightClick,
    // drawDelEventType: mars3d.EventType.middleClick
  });
  map.addThing(measure);

  measure.on(mars3d.EventType.start, function (e) {
    console.log("开始异步分析", e);
  });
  measure.on(mars3d.EventType.end, function (e) {
    console.log("完成异步分析", e);
  });
});

onBeforeUnmount(() => {
  removeAll();
});

// 空间距离
async function measureLength() {
  const graphic = await measure.distance({
    showAddText: true,
    label: {
      // 自定义显示label的graphic类型
      type: "div",
      updateText: function (text, graphic) {
        // updateText是必须，用于动态更新 text
        graphic.html = `<div class="marsGreenGradientPnl" >${text}</div>`;
      },
      // 下面是graphic对应类型本身的参数
      html: `<div class="marsGreenGradientPnl" ></div>`,
      horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
      verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
      // pointerEvents: false
    },
    // style: {
    //   color: '#ffff00',
    //   width: 3,
    //   clampToGround: false //是否贴地
    // }
  });
}

function removeAll() {
  measure.clear();
}
</script>

<style scoped lang="scss">
.Distance {
  height: 10vh;
  display: flex;
  gap: 16px;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
</style>
