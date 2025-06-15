<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="" />
      <span> 实时视频 </span>
    </div>

    <div class="video">
      <JianKong
        v-for="item in cameraList"
        :key="item.id"
        :JKParams="item"
        class="video-player"
      />
    </div>
  </div>
</template>
<script setup>
import { onMounted, reactive, ref } from "vue";
import JianKong from "@/components/JianKong/JianKong.vue";
import { getTokens } from "@/api/YingShiYunApi";
import { listWells } from "@/api/admin/wells";
import { ElMessage } from "element-plus";

const cameraList = ref([]);
const AccessToken = ref("");
const tokenParams = {
  appKey: "9d96d9e9439248b3af163466f192ff07",
  appSecret: "0bafddff864cf320fcdebb2e75ac829b",
};
onMounted(async () => {
  AccessToken.value = await getTokens(tokenParams);

  if (!AccessToken.value) {
    ElMessage.error("获取Token失败，请检查网络或联系管理员");
    return;
  }
  const { rows, total, code } = await listWells({
    pageNum: 1,
    pageSize: 100,
  });

  if (code !== 200) {
    ElMessage.error("获取监控列表失败，请检查网络或联系管理员");
    return;
  }
  rows.forEach((item) => {
    if (!item.videoUrl) return;
    cameraList.value.push({
      accessToken: AccessToken.value,
      deviceSerial: item.videoUrl,
      hd: false,
      width: 500,
      height: 300,
      channelNo: 1,
    });
  });

  console.log("🚀 ~ rows.forEach ~ cameraList:", cameraList);
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
    .video-player {
      width: 300px;
      height: 200px;
      border: 1px solid #ccc;
      box-sizing: border-box;
    }
  }
}
</style>
