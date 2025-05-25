<template>
  <div class="head-div">
    <div class="header">
      <span class="main-text">清徐地下水在线监测软件平台</span>
    </div>
  </div>
  <div class="bottom">
    <div class="bottomIn">
      <div class="bottomInOt">
        <img src="@/assets/static/1.png" />
        <div class="text">
          指挥调度
        </div>
      </div>
      <div class="bottomInOt">
        <img src="@/assets/static/2.png" />
        <div class="text">
          在线检测
        </div>
      </div>
      <div class="bottomInOt">
        <img src="@/assets/static/3.png" />
        <div class="text">
          企业信息
        </div>
      </div>
      <div class="bottomInOt">
        <img src="@/assets/static/4.png" />
        <div class="text">
          报警预报
        </div>
      </div>
    </div>
  </div>
  <LeftTable class="leftTable" />
</template>

<script setup>
  import TopMenu from "./TopMenu.vue";
  import { ElMessageBox } from "element-plus";

  import useAppStore from "@/store/modules/app";
  import useUserStore from "@/store/modules/user";
  import useSettingsStore from "@/store/modules/settings";
  import { useRouter } from "vue-router";
  import LeftTable from './LeftTopTable.vue';
  const router = useRouter();
  import layerStore from "@/store/modules/layer";

  const appStore = useAppStore();
  const userStore = useUserStore();
  const settingsStore = useSettingsStore();

  const emits = defineEmits(["setLayout"]);
  function setLayout() {
    emits("setLayout");
  }

  function logout() {
    ElMessageBox.confirm("确定注销并退出系统吗？", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(() => {
        userStore.logOut().then(() => {
          location.href = "/index";
        });
      })
      .catch(() => { });
  }

  function goSystem() {
    router.push({ path: "/index" });
  }
  function handleCommand(command) {
    switch (command) {
      case "setLayout":
        setLayout();
        break;
      case "goSystem":
        goSystem();
        break;

      case "logout":
        logout();
        break;
      default:
        break;
    }
  }
</script>

<style lang="scss" scoped>
  .head-div {
    background: linear-gradient(180deg,
        #000000 0%,
        rgba(0, 0, 0, 0.5) 52%,
        rgba(0, 0, 0, 0) 100%);
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1;
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: space-between;
    width: 100vw;

    .header {
      width: 100%;
      height: 77px;
      line-height: 90px;
      background: url('@/assets/static/title.png') no-repeat;
      text-align: center;

      span {
        font-size: 1.6vw;
        font-weight: 700;
        background: linear-gradient(75deg, #21f1c2, #66fdf1, #1ebcea);
        -webkit-text-fill-color: transparent;
        -webkit-background-clip: text
      }

      background-color: rgba(9, 21, 42, .8) !important;
    }
  }

  .bottom {
    width: 100%;
    height: 80px;
    position: absolute;
    bottom: 26px;
    background: url('@/assets/static/bottom.png') no-repeat;
    z-index: 999;
    display: flex;
    justify-content: center;

    .bottomIn {
      width: 700px;
      height: 58px;
      margin-top: 10px;
      display: flex;
      justify-content: space-between;

      .bottomInOt {
        width: 25%;
        height: 100%;
        display: flex;
        justify-content: space-around;
        align-items: center;
      }

      .text {
        width: 108px;
        height: 46px;
        text-align: center;
        line-height: 46px;
        font-weight: 700;
        font-size: 18px;
        background: url('@/assets/static/textbg.png') no-repeat;
      }

    }
  }

  .leftTable {
    width: 464px;
    /* height: 285px; */
    position: absolute;
    left: 20px;
    top: 100px;
    z-index: 100;
    background-color: rgba(9, 21, 42, .8)
  }
</style>