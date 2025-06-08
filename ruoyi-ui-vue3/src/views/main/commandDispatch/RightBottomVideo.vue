<template>
  <div class="table">
    <div class="title">
      <img src="@/assets/static/subTitle.png" alt="">
      <span>
        实时视频
      </span>
    </div>

    <div class="video">
      <video src="https://www.w3schools.com/html/mov_bbb.mp4" controls style="width: 250px">
      </video>
      <video src="https://www.w3schools.com/html/mov_bbb.mp4" controls style="width: 250px">
      </video>
      <video src="https://www.w3schools.com/html/mov_bbb.mp4" controls style="width: 250px">
      </video>
      <video src="https://www.w3schools.com/html/mov_bbb.mp4" controls style="width: 250px">
      </video>
      <video src="https://www.w3schools.com/html/mov_bbb.mp4" controls style="width: 250px">
      </video>
    </div>
  </div>
</template>
<script setup>
  import { onMounted, reactive, toRaw, ref, nextTick } from "vue";
  const tableData = []

</script>

<style lang="scss" scoped>
  .table {
    width: 604px;
    padding: 10px;
    background: url('@/assets/static/left.png') no-repeat;
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
      flex-wrap: wrap;
      justify-content: space-around;
    }
  }
</style>