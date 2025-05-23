<template>
  <div class="head-div">
    <div class="log-div">
      <span class="main-text">清徐地下水在线监测软件平台</span>
      <span class="sub-text">{{
        layerStore()
          .setTrees.map((b) => b.name)
          .join(",")
      }}</span>
    </div>
    <div class="content-div">
      <!-- <TopMenu /> -->
      <div class="avatar-container">
        <span>当前用户: {{ userStore.name }}</span>
        <el-dropdown
          @command="handleCommand"
          class="right-menu-item hover-effect"
          trigger="click"
        >
          <div class="avatar-wrapper">
            <img :src="userStore.avatar" class="user-avatar" />
            <el-icon><caret-bottom /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="logout">
                <span>退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import TopMenu from "./TopMenu.vue";
import { ElMessageBox } from "element-plus";
import useAppStore from "@/store/modules/app";
import useUserStore from "@/store/modules/user";
import useSettingsStore from "@/store/modules/settings";
import { useRouter } from "vue-router";
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
    .catch(() => {});
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
  background: linear-gradient(
    180deg,
    #000000 0%,
    rgba(0, 0, 0, 0.5) 52%,
    rgba(0, 0, 0, 0) 100%
  );
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100vw;
  height: 50px;
  // box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
}
.log-div {
  display: flex;
  align-items: center;
  justify-content: center;
  padding-left: 40px;
  .main-text {
    font-family: MiSans;
    font-size: 24px;
    font-weight: 600;
    line-height: 24px;
    letter-spacing: 0em;
    font-variation-settings: "opsz" auto;
    font-feature-settings: "kern" on;
    color: #ffffff;
  }
  .sub-text {
    color: #ffffff;
    font-size: 16px;
    font-weight: 500;
    margin: 0;
  }
}

.log-div img {
  width: auto;
  width: 36px;
  border-radius: 4px;
}
.log-div span {
  font-size: 1.2rem;
  font-weight: bold;
  margin: 0 16px;
}
.content-div {
  display: flex;
  align-items: center;
}
.avatar-container {
  margin-right: 40px;
  display: flex;
  align-items: center;
  > span {
    font-family: MiSans;
    font-size: 14px;
    font-weight: 600;
    letter-spacing: 0em;
    font-variation-settings: "opsz" auto;
    font-feature-settings: "kern" on;
    color: #ffffff;
    margin: 0 4px;
  }
  .avatar-wrapper {
    margin-top: 5px;
    position: relative;
    .user-avatar {
      cursor: pointer;
      width: 40px;
      height: 40px;
      border-radius: 10px;
    }

    i {
      cursor: pointer;
      position: absolute;
      right: -20px;
      top: 14px;
      font-size: 16px;
      color: #fff;
    }
  }
}
</style>
