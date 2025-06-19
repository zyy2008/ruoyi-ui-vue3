<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> 实时视频 </span>
    </div>

    <div class="video">
      <img
        v-for="item in images"
        :key="item.key"
        :src="[`@/assets/static/videoImage/${item.img}`]"
        @click="openVideoDialog"
      />
      <el-dialog
        v-model="dialogTableVisible"
        title="视频监控"
        width="100%"
        height="100%"
        :close-on-click-modal="false"
      >
        <el-col span="6">
          <el-menu>
            <el-sub-menu
              v-for="camera in cameraList"
              :key="camera.wellCode"
              @click="openVideo"
            >
              <template #header>
                <el-icon><VideoCamera /></el-icon>
              </template>
              <span>{{ camera.wellCode + camera.locaiton }}</span>
            </el-sub-menu>
          </el-menu>
        </el-col>
        <el-col span="12">
          <JianKong
            v-if="videoCompState"
            :JKParams="camera"
            class="video-player"
          />
        </el-col>
      </el-dialog>
    </div>
  </div>
</template>
<script setup>
import { onMounted, reactive, ref, watch } from "vue";
import JianKong from "@/components/JianKong/JianKong.vue";
import { getTokens } from "@/api/YingShiYunApi";
import { listWells } from "@/api/admin/wells";
import { ElMessage, ElMenu } from "element-plus";
import { VideoCamera } from "@element-plus/icons-vue";
import { useVideoStore } from "@/store/modules/app";

const cameraList = ref([]);
const AccessToken = ref("");
const tokenParams = {
  appKey: "9d96d9e9439248b3af163466f192ff07",
  appSecret: "0bafddff864cf320fcdebb2e75ac829b",
};
onMounted(async () => {});
const images = [
  { img: "1.png" },
  { img: "1.png" },
  { img: "1.png" },
  { img: "1.png" },
  { img: "1.png" },
  { img: "1.png" },
];
const videoStore = useVideoStore();
const dialogTableVisible = ref(false);
const openVideoDialog = async () => {
  dialogTableVisible.value = true;
  videoStore.videoState = true;
  cameraList.value = await getCameraList();
  openVideo(cameraList.value[0]);
};

const getCameraList = async () => {
  debugger;
  AccessToken.value = await getTokens(tokenParams);

  if (!AccessToken.value) {
    ElMessage.error("获取Token失败，请检查网络或联系管理员");
    return;
  }
  let { rows, total, code } = await listWells({
    pageNum: 1,
    pageSize: 100,
  });

  if (code !== 200) {
    ElMessage.error("获取监控列表失败，请检查网络或联系管理员");
    return;
  }

  // let rows = [{ videoUrl: "FT2988779" }];
  const cameraList = [];
  rows.map((item, index) => {
    if (!item.videoUrl) return;
    cameraList.push({
      accessToken: AccessToken.value,
      deviceSerial: item.videoUrl,
      hd: false,
      width: 800,
      height: 600,
      channelNo: 1,
      appKey: tokenParams.appKey,
      location: item.location,
      wellCode: item.wellCode,
    });
  });

  return cameraList;
};
const videoCompState = ref(false);
const camera = ref();
const openVideo = async (video) => {
  camera.value = "";
  videoCompState.value = false;
  camera.value = video;
  videoCompState.value = true;
};
watch(
  videoStore.videoState,
  (newValue) => {
    if (newValue === false) {
      dialogTableVisible.value = false;
    }
  },
  {
    immediate: true,
    deep: true,
  }
);
watch(dialogTableVisible, (newValue) => {
  if (newValue === false) {
    videoCompState.value = false;
  }
});
</script>

<style lang="scss" scoped>
.table {
  width: 604px;
  padding: 10px;
  background: url("@/assets/static/left.png") no-repeat;
  background-color: rgba(9, 21, 42, 0.8);

  .title {
    height: 42px;
    line-height: 42px;
    display: flex;

    span {
      font-size: 18px;
      display: inline-block;
    }
  }

  .video {
    width: 100%;
    height: calc(100% - 60px);
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    img {
      width: 240px;
      height: 180px;
    }
    .video-player {
      border: 1px solid #ccc;
      box-sizing: border-box;
    }
  }
}
</style>
