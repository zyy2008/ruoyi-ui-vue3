<template>
  <div class="Distance">
    <el-button class="b288" type="primary" @click="measureArea">开始量算</el-button>
    <el-button class="b288" type="danger" @click="removeAll">清除结果</el-button>
  </div>
</template>
<script setup>
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
      background: false
    },
    isAutoEditing: false // 绘制完成后是否自动激活编辑
    // drawEndEventType: mars3d.EventType.rightClick,
    // drawDelEventType: mars3d.EventType.middleClick
  });
  map.addThing(measure);
});

onBeforeUnmount(() => {
  removeAll();
});

// 空间距离
async function measureArea() {
  const graphic = await measure.area({});
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
