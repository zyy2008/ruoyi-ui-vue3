<!--
 * @Author: 18794202870 8023630+xiaofu2870@user.noreply.gitee.com
 * @Date: 2025-06-17 20:10:59
 * @LastEditors: 18794202870 8023630+xiaofu2870@user.noreply.gitee.com
 * @LastEditTime: 2025-06-19 01:20:09
 * @FilePath: \ruoyi-ui-vue3\src\components\JianKong\JianKong.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div ref="videoContainer" style="width: 100%; height: 100%"></div>
</template>
<script setup>
import { onMounted, ref, defineProps, onBeforeUnmount } from "vue";

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

console.log("🚀 ~ props:", props);

//监听播放器缓冲状态变化
let player;
onMounted(() => {
  if (videoContainer.value) {
    const id = `video_${Math.random().toString(36).substr(2, 9)}`;
    console.log("🚀 ~ onMounted ~ id:", id);
    videoContainer.value.id = id;
  }
  setTimeout(() => {
    const param = {
      id: videoContainer.value.id, // 视频容器ID
      url: `ezopen://open.ys7.com/${props.JKParams.deviceSerial}/1${
        props.JKParams?.hd ? ".hd" : ""
      }.live`, // 直播地址

      accessToken: props.JKParams.AccessToken, // 访问令牌2
      width: props.JKParams.width, // 视频宽度
      height: props.JKParams.height, // 视频高度
      AppKey: props.JKParams.AppKey, // 应用标识
    };

    player = new EZUIKitPlayer(param);

    console.log("🚀 ~ setTimeout ~ param:", param);
  }, 500);
});
onBeforeUnmount(() => {
  player.destroy();
  player.none;
  videoContainer.value = null;
  console.log("🚀 ~ onBeforeUnmount ~ player:", 111111111);

  console.log("🚀 ~ onBeforeUnmount ~ props.JKParams:", props.JKParams);
});
</script>

<style></style>
