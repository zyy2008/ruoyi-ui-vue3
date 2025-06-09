<template>
  <div class="video-container">
    <video
      ref="videoPlayer"
      class="video-player"
      autoplay
      muted
      playsinline
    ></video>

    <div class="floating-info">
      <div class="recording-indicator">
        <div class="recording-dot"></div>
        <span>录制中</span>
      </div>
      <span>摄像头: {{ currentCamera.name }}</span>
    </div>

    <div class="timestamp">
      {{ currentTime }}
    </div>

    <div v-if="loading" class="loading-overlay">
      <div class="spinner"></div>
      <p>正在连接视频流...</p>
    </div>

    <div v-if="error" class="loading-overlay">
      <div class="error-message">
        <h3><i class="fas fa-exclamation-triangle"></i> 视频加载失败</h3>
        <p>{{ error }}</p>
        <button
          @click="initPlayer"
          class="control-btn"
          style="margin-top: 15px"
        >
          <i class="fas fa-redo"></i>
        </button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, onUnmounted, computed } from "vue";

// 视频播放器引用
const videoPlayer = ref(null); // 状态变量

const loading = ref(true);
const error = ref(null);
const isRecording = ref(false);
const recordingDuration = ref(0);
const recordingTimer = ref(null);
const currentTime = ref("00:00:00");
const bandwidth = ref(2.4);
const currentQuality = ref("hd");

// const cameraList = (list) => {
//   return list.map((item, index) => {
//     return {
//       name: item.name,
//       id: index,
//       streamUrl: "https://example.com/stream/cam1.flv",
//       location: item.location,
//       status: "online",
//     };
//   });
// };
// 摄像头数据
const cameras = reactive([
  {
    id: "cam1",
    name: "主入口",
    thumbnail:
      "https://images.unsplash.com/photo-1566665797739-1674de7a421a?ixlib=rb-4.0.3&auto=format&fit=crop&w=200&q=80",
    status: "online",
    location: "公司正门",
    streamUrl: "https://example.com/stream/cam1.flv",
  },
  {
    id: "cam2",
    name: "停车场",
    thumbnail:
      "https://images.unsplash.com/photo-1589802829985-817e51171b92?ixlib=rb-4.0.3&auto=format&fit=crop&w=200&q=80",
    status: "online",
    location: "地下停车场A区",
    streamUrl: "https://example.com/stream/cam2.flv",
  },
  {
    id: "cam3",
    name: "办公区",
    thumbnail:
      "https://images.unsplash.com/photo-1497366754035-f200968a6e72?ixlib=rb-4.0.3&auto=format&fit=crop&w=200&q=80",
    status: "online",
    location: "三楼办公区",
    streamUrl: "https://example.com/stream/cam3.flv",
  },
  {
    id: "cam4",
    name: "仓库",
    thumbnail:
      "https://images.unsplash.com/photo-1505373877841-8d25f7d46678?ixlib=rb-4.0.3&auto=format&fit=crop&w=200&q=80",
    status: "offline",
    location: "一号仓库",
    streamUrl: "https://example.com/stream/cam4.flv",
  },
]);

// 当前选中的摄像头
const currentCamera = ref(cameras[0]);

// 视频质量选项
const qualityOptions = ref([
  { label: "流畅", value: "low" },
  { label: "标清", value: "sd" },
  { label: "高清", value: "hd" },
  { label: "超清", value: "fhd" },
]);

// 计算属性
const onlineCameras = computed(() => {
  return cameras.filter((cam) => cam.status === "online").length;
});

const currentQualityLabel = computed(() => {
  const quality = qualityOptions.value.find(
    (q) => q.value === currentQuality.value
  );
  return quality ? quality.label : "高清";
});

// FLV播放器实例
let flvPlayer = null;

// 初始化播放器
const initPlayer = () => {
  destroyPlayer();
  loading.value = true;
  error.value = null;

  // 在实际项目中，这里应该是从后端获取的流地址
  // 当前使用模拟的流地址
  const streamUrl = currentCamera.value.streamUrl;

  if (flvjs.isSupported()) {
    try {
      flvPlayer = flvjs.createPlayer({
        type: "flv",
        url: streamUrl,
        isLive: true,
        hasAudio: false,
      });

      flvPlayer.attachMediaElement(videoPlayer.value);
      flvPlayer.load();

      flvPlayer.on(flvjs.Events.MEDIA_INFO, () => {
        flvPlayer
          .play()
          .then(() => {
            loading.value = false;
          })
          .catch((err) => {
            handlePlayerError("播放失败", err);
          });
      });

      flvPlayer.on(flvjs.Events.ERROR, (errType, errDetail) => {
        handlePlayerError(errType, errDetail);
      });
    } catch (err) {
      handlePlayerError("播放器初始化失败", err);
    }
  } else {
    handlePlayerError("浏览器不支持", "当前浏览器不支持FLV视频播放");
  }
};

// 处理播放错误
const handlePlayerError = (type, detail) => {
  console.error("播放器错误:", type, detail);
  error.value = `视频加载失败: ${type}`;
  loading.value = false;

  // 10秒后自动重试
  setTimeout(initPlayer, 10000);
};

// 销毁播放器
const destroyPlayer = () => {
  if (flvPlayer) {
    flvPlayer.pause();
    flvPlayer.unload();
    flvPlayer.detachMediaElement();
    flvPlayer.destroy();
    flvPlayer = null;
  }
};

// 切换摄像头
const switchCamera = (camera) => {
  if (camera.status !== "online") {
    error.value = "该摄像头当前离线，无法查看";
    return;
  }

  currentCamera.value = camera;
  initPlayer();
};
// 切换视频质量
const changeQuality = (quality) => {
  currentQuality.value = quality;
  // 在实际项目中，这里会请求不同质量的视频流
  console.log(`切换到 ${quality} 画质`);
};

// 开始/停止录制
const toggleRecording = () => {
  isRecording.value = !isRecording.value;

  if (isRecording.value) {
    // 开始录制
    recordingTimer.value = setInterval(() => {
      recordingDuration.value++;
    }, 1000);
  } else {
    // 停止录制
    clearInterval(recordingTimer.value);
  }
};

// 格式化时间
const formatDuration = (seconds) => {
  const hrs = Math.floor(seconds / 3600);
  const mins = Math.floor((seconds % 3600) / 60);
  const secs = seconds % 60;

  return `${hrs.toString().padStart(2, "0")}:${mins
    .toString()
    .padStart(2, "0")}:${secs.toString().padStart(2, "0")}`;
};

// 更新时间显示
const updateTime = () => {
  const now = new Date();
  currentTime.value = now.toTimeString().substring(0, 8);
};

// 模拟带宽变化
const updateBandwidth = () => {
  // 随机生成1.5-3.0之间的带宽值
  bandwidth.value = (1.5 + Math.random() * 1.5).toFixed(1);
};

// 生命周期钩子
onMounted(() => {
  initPlayer();

  // 每秒更新时间
  setInterval(updateTime, 1000);
  updateTime();

  // 每5秒更新带宽
  setInterval(updateBandwidth, 5000);
});

onUnmounted(() => {
  destroyPlayer();
  if (recordingTimer.value) {
    clearInterval(recordingTimer.value);
  }
});
</script>

<style lang="scss">
.video-container {
  background: rgba(15, 23, 42, 0.7);
  border-radius: 15px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
  position: relative;
}

.video-wrapper {
  position: relative;
  padding-top: 56.25%;
  /* 16:9 Aspect Ratio */
  background: #000;
}

.video-player {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #000;
}

.controls {
  display: flex;
  justify-content: center;
  gap: 15px;
  padding: 20px;
  background: rgba(15, 23, 42, 0.9);
}

.control-btn {
  background: linear-gradient(45deg, #3498db, #1e5f8a);
  border: none;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 18px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.control-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3);
}

.control-btn:active {
  transform: translateY(1px);
}

.control-btn.active {
  background: linear-gradient(45deg, #2ecc71, #27ae60);
}

.sidebar {
  background: rgba(15, 23, 42, 0.7);
  border-radius: 15px;
  padding: 20px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.cameras-section,
.info-section {
  background: rgba(30, 41, 59, 0.6);
  border-radius: 10px;
  padding: 20px;
}

.section-title {
  font-size: 1.3rem;
  margin-bottom: 15px;
  color: #60a5fa;
  display: flex;
  align-items: center;
  gap: 10px;
}

.cameras-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(130px, 1fr));
  gap: 15px;
}

.camera-card {
  background: rgba(51, 65, 85, 0.7);
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 2px solid transparent;
}

.camera-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.camera-card.active {
  border-color: #3b82f6;
  background: rgba(59, 130, 246, 0.2);
}

.camera-preview {
  height: 80px;
  background-size: cover;
  background-position: center;
  position: relative;
}

.camera-info {
  padding: 8px;
  font-size: 0.85rem;
  text-align: center;
}

.status-indicator {
  position: absolute;
  top: 5px;
  right: 5px;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: #2ecc71;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 12px;
}

.info-card {
  background: rgba(51, 65, 85, 0.5);
  border-radius: 8px;
  padding: 12px;
  font-size: 0.9rem;
}

.info-label {
  color: #94a3b8;
  font-size: 0.8rem;
  margin-bottom: 5px;
}

.info-value {
  font-weight: 500;
}

.loading-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  z-index: 10;
}

.spinner {
  width: 50px;
  height: 50px;
  border: 5px solid rgba(255, 255, 255, 0.1);
  border-radius: 50%;
  border-top-color: #3b82f6;
  animation: spin 1s linear infinite;
  margin-bottom: 15px;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.error-message {
  background: rgba(239, 68, 68, 0.8);
  padding: 15px;
  border-radius: 8px;
  text-align: center;
  max-width: 90%;
  margin: 20px auto;
}

.stats-bar {
  display: flex;
  justify-content: space-around;
  padding: 15px;
  background: rgba(15, 23, 42, 0.8);
  border-radius: 0 0 15px 15px;
}

.stat-item {
  text-align: center;
}

.stat-value {
  font-size: 1.2rem;
  font-weight: bold;
  color: #60a5fa;
}

.stat-label {
  font-size: 0.85rem;
  color: #94a3b8;
}

.quality-selector {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

.quality-btn {
  flex: 1;
  padding: 8px;
  background: rgba(30, 41, 59, 0.8);
  border: 1px solid #334155;
  border-radius: 5px;
  color: #cbd5e1;
  cursor: pointer;
  transition: all 0.2s;
  text-align: center;
}

.quality-btn.active {
  background: #3b82f6;
  color: white;
  border-color: #3b82f6;
}

.floating-info {
  position: absolute;
  top: 15px;
  left: 15px;
  background: rgba(0, 0, 0, 0.6);
  padding: 8px 15px;
  border-radius: 20px;
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  gap: 8px;
  z-index: 5;
}

.timestamp {
  position: absolute;
  bottom: 15px;
  right: 15px;
  background: rgba(0, 0, 0, 0.6);
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 0.8rem;
  z-index: 5;
}

.recording-indicator {
  display: flex;
  align-items: center;
  gap: 5px;
  color: #ef4444;
}

.recording-dot {
  width: 10px;
  height: 10px;
  background: #ef4444;
  border-radius: 50%;
  animation: pulse 1.5s infinite;
}

@keyframes pulse {
  0% {
    opacity: 1;
  }

  50% {
    opacity: 0.4;
  }

  100% {
    opacity: 1;
  }
}

footer {
  text-align: center;
  margin-top: 40px;
  padding: 20px;
  color: #94a3b8;
  font-size: 0.9rem;
}
</style>
