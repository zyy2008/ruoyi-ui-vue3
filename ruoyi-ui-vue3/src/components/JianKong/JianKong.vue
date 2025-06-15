<template>
  <div>
    <div id="container" ref="videoContainer"></div>
  </div>
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
const props = defineProps({
  JKParams: {
    type: Object,
    default: () => ({
      deviceSerial: "FT2988704",
      channelNo: 1,
      hd: false,
      width: 500,
      height: 300,
    }),
  },
});

// 监听视频容器大小变化
const resizeObserver = new ResizeObserver(() => {
  const container = videoContainer.value;
  if (container) {
    player.resize(container.offsetWidth, container.offsetHeight);
  }
});

// 监听播放器状态变化
player.on("stateChange", (state) => {
  console.log("🚀 ~ player.on ~ stateChange:", state);
  if (state === "PLAYING") {
    // 视频播放中
  } else if (state === "STOPPED") {
    // 视频停止
  } else if (state === "ERROR") {
    // 视频播放出错
  }
});

// 监听播放器网络状态变化
player.on("networkStateChange", (state) => {
  console.log("🚀 ~ player.on ~ networkStateChange:", state);
  if (state === "NETWORK_NORMAL") {
    // 网络状态正常
  } else if (state === "NETWORK_LOW") {
    // 网络状态较低
  } else if (state === "NETWORK_BAD") {
    // 网络状态较差
  }
});

// 监听播放器缓冲状态变化
onMounted(async () => {
  console.log("🚀 ~ props:", props);
  const player = new EZUIKitPlayer({
    id: videoContainer.value.id, // 视频容器ID
    url: `ezopen://open.ys7.com/${props.JKParams.deviceSerial}/${
      props.JKParams.channelNo
    }${props.JKParams.hd ? ".hd" : ""}.live`, // 直播地址
    accessToken: props.JKParams.AccessToken, // 访问令牌2
    width: props.JKParams.width, // 视频宽度
    height: props.JKParams.height, // 视频高度
    AppKey: props.JKParams.AppKey, // 应用标识

    handleError: (err) => {
      // 处理Token失效（具体错误码需查阅文档确认）
      if ([10002, 10004].includes(err.data?.nErrorCode)) {
        ElMessage.alert("监控Token失效，后台处理后重新登录查看监控");
      }

      // 原有错误处理（如加密错误）
      if (err.type === "handleRunTimeInfoError" && err.data.nErrorCode === 5) {
        // 加密设备密码错误逻辑
      }
    },
  });
  console.log("🚀 ~ onMounted ~ player:", player);
});

// 处理Token过期
const handleTokenExpired = async () => {
  try {
    // 1. 调用API获取新Token（替换为实际接口）
    const newToken = await fetchNewToken();

    // 2. 用新Token重新初始化
    await initPlayer(newToken);
  } catch (error) {
    console.error("Token刷新失败:", error);
    // 可添加重试机制或用户提示
  }
};
</script>

<style>
#container {
  width: 100%;
  height: 100%;
}
</style>
