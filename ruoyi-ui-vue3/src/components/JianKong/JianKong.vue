<template>
  <div id="container" ref="videoContainer"></div>
</template>
<script setup>
import { onMounted, ref, defineProps } from "vue";

import { EZUIKitPlayer } from "ezuikit-js";
import { ElMessage } from "element-plus";

const videoContainer = ref(null);
const a = {
  AppKey: "9d96d9e9439248b3af163466f192ff07",
  AccessToken:
    "at.4vrce2vj7wfrxlk4011o2xlu0kjnru5e-4g4rbs3nln-1qgxm7j-er7is3exy",
  Url: "ezopen://open.ys7.com/FT2988704/1.hd.live",
};
const props = defineProps(["JKParams"]);



// 监听播放器缓冲状态变化
onMounted(async () => {
  console.log("🚀 ~ props:", props);
  props.JKParams.value.array.forEach((item) => {
    const player = new EZUIKitPlayer({
      id: videoContainer.value.id, // 视频容器ID
      url: `ezopen://open.ys7.com/${item.deviceSerial}/${item.channelNo}${
        item.hd ? ".hd" : ""
      }.live`, // 直播地址
      accessToken: item.AccessToken, // 访问令牌2
      width: item.width, // 视频宽度
      height: item.height, // 视频高度
      AppKey: item.AppKey, // 应用标识
    });
    console.log("🚀 ~ onMounted ~ player:", player);
  });
});
</script>

<style>
#container {
  width: 100%;
  height: 100%;
}
</style>
