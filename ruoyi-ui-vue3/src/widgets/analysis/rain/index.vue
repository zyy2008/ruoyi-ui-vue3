<template>
  <mars-dialog  draggable  title="降雨分析" right="10" top="100" width="350">
    <div >
      <div class="f-mb">
        <a-space>
          <span class="mars-pannel-item-label">启用状态:</span>
          <mars-switch v-model:checked="formState.enabled" @change="onChangeState" />
        </a-space>
      </div>

      <div class="f-mb">
        <a-space>
          <span class="mars-pannel-item-label">粒子速度:</span>
          <mars-slider @change="onChangeSpeed" v-model:value="formState.speed" :min="1" :max="100" />
        </a-space>
      </div>

      <div class="f-mb">
        <a-space>
          <span class="mars-pannel-item-label">粒子大小:</span>
          <mars-slider @change="onChangeSize" v-model:value="formState.size" :min="1" :max="100" />
        </a-space>
      </div>

      <div>
        <a-space>
          <span class="mars-pannel-item-label">粒子方向:</span>
          <mars-slider @change="onChangeDirection" v-model:value="formState.direction" :min="-89" :max="89" />
        </a-space>
      </div>
    </div>
  </mars-dialog>
</template>

<script setup lang="ts">
import { reactive } from "vue"
import * as mapWork from "./map.js"
import useLifecycle from "@mars/widgets/common/uses/use-lifecycle";
const formState = reactive({
  enabled: true,
  speed: 10,
  size: 20,
  direction: -30
})

// 启用map.ts生命周期
useLifecycle(mapWork);

// 是否开启特效
const onChangeState = () => {
  mapWork.setEffect(formState.enabled)
}

// 粒子速度
const onChangeSpeed = () => {
  mapWork.setSpeed(formState.speed)
}

// 粒子大小
const onChangeSize = () => {
  mapWork.setSize(formState.size)
}

// 粒子方向
const onChangeDirection = () => {
  mapWork.setDirection(formState.direction)
}
</script>
<style scoped lang="scss">
.ant-slider {
  width: 220px;
} 
</style>
