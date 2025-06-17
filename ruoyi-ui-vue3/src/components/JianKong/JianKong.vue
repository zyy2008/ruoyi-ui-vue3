<template>
  <div ref="videoContainer" style="width: 100%; height: 100%"></div>
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
  console.log(
    "🚀 ~ props:",
    `ezopen://open.ys7.com/${props.JKParams.deviceSerial}/${
      props.JKParams.channelNo
    }${props.JKParams.hd ? ".hd" : ""}.live`
  );
  if (videoContainer.value) {
    const id = `video_${Math.random().toString(36).substr(2, 9)}`;
    console.log("🚀 ~ onMounted ~ id:", id);
    videoContainer.value.id = id;
  }
  const player = new EZUIKitPlayer({
    id: videoContainer.value.id, // 视频容器ID
    url: `ezopen://open.ys7.com/${props.JKParams.deviceSerial}/1${
      props.JKParams?.hd ? ".hd" : ""
    }.live`, // 直播地址

    accessToken: props.JKParams.AccessToken, // 访问令牌2
    width: props.JKParams.width, // 视频宽度
    height: props.JKParams.height, // 视频高度
    AppKey: props.JKParams.AppKey, // 应用标识
    onPlayerReady: () => {
      console.log("🚀 ~ onPlayerReady ~ player:", player);
      player.play(); // 播放直播
    },
  });
  console.log("🚀 ~ onMounted ~ player:", player);
});
</script>

<style></style>
