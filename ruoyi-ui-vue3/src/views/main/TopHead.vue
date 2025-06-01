<template>
  <div class="head-div">
    <div class="header">
      <span class="main-text">清徐地下水在线监测软件平台</span>

      <div class="avatar-container">
        <el-dropdown @command="handleCommand" class="right-menu-item hover-effect" trigger="click">
          <div class="avatar-wrapper">
            <img :src="userStore.avatar" class="user-avatar" />
            <el-icon><caret-bottom /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item divided command="goSystem">
                <span>系统管理</span>
              </el-dropdown-item>
              <el-dropdown-item divided command="logout">
                <span>退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
  <div class="bottom">
    <div class="bottomIn">
      <div class="bottomInOt">
        <img src="@/assets/static/1.png" />
        <div class="text">指挥调度</div>
      </div>
      <div class="bottomInOt">
        <img src="@/assets/static/2.png" />
        <div class="text">在线监测</div>
      </div>
      <div class="bottomInOt">
        <img src="@/assets/static/3.png" />
        <div class="text">企业信息</div>
      </div>
      <div class="bottomInOt">
        <img src="@/assets/static/4.png" />
        <div class="text">报警预报</div>
      </div>
    </div>
  </div>
  <LeftTopTable class="leftTable" />
  <RightTopLine class="RightLine" />
  <RightBottomTable class="RightTable" />
</template>

<script setup>
  import TopMenu from "./TopMenu.vue";
  import { ElMessageBox } from "element-plus";
  import useAppStore from "@/store/modules/app";
  import useUserStore from "@/store/modules/user";
  import useSettingsStore from "@/store/modules/settings";
  import { useRouter } from "vue-router";
  import RightBottomTable from "./RightBottomTable.vue";
  import LeftTopTable from "./LeftTopTable.vue";
  import RightTopLine from "./RightTopLine.vue";
  const router = useRouter();
  import layerStore from "@/store/modules/layer";

  const appStore = useAppStore();
  const userStore = useUserStore();
  const settingsStore = useSettingsStore();

  const emits = defineEmits(["setLayout"]);
  function setLayout() {
    emits("setLayout");
  }

  function handleCommand(command) {
    switch (command) {
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

    .avatar-container {
      right: 40px;
      position: absolute;
      top: 26px;

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
          top: 25px;
          font-size: 12px;
        }
      }
    }


    .header {
      width: 100%;
      height: 77px;
      line-height: 90px;
      background: url("@/assets/static/title.png") no-repeat;
      background-size: 100vw 10vh;
      text-align: center;

      span {
        font-size: 1.6vw;
        font-weight: 700;
        background: linear-gradient(75deg, #21f1c2, #66fdf1, #1ebcea);
        -webkit-text-fill-color: transparent;
        -webkit-background-clip: text;
      }

      background-color: rgba(9, 21, 42, 0.8) !important;
    }
  }

  .bottom {
    width: 100%;
    height: 80px;
    position: absolute;
    bottom: 26px;
    background: url("@/assets/static/bottom.png") no-repeat;
    background-size: 100vw 10vh; 
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
        cursor: pointer;
      }

      .text {
        width: 108px;
        height: 46px;
        text-align: center;
        line-height: 46px;
        font-weight: 700;
        font-size: 18px;
        background: url("@/assets/static/textbg.png") no-repeat;
      }
    }
  }

  .leftTable {
    width: 430px;
    height: 760px;
    position: absolute;
    left: 20px;
    top: 100px;
    z-index: 100;
    background-color: rgba(9, 21, 42, 0.8);
  }

  .RightLine {
    width: 430px;
    height: 400px;
    position: absolute;
    top: 100px;
    right: 20px;
    z-index: 20;
  }

  .RightTable {
    width: 430px;
    height: 350px;
    position: absolute;
    top: 510px;
    right: 20px;
    z-index: 100;
  }
    :deep() {
    .el-table tr {
      background-color: rgba(21, 85, 75, 0.2);
    }

    .el-table {
      --el-table-bg-color: none;
      --el-table-text-color: white;
      --el-table-border-color: none;
      --el-table-row-hover-bg-color: none
    }

    .el-table .el-table__row:hover {
    cursor: pointer;
}

    .el-table .el-table__header-wrapper th,
    .el-table .el-table__fixed-header-wrapper th {
      background-color: #155548 !important;
      color: white;
    }
  }
</style>