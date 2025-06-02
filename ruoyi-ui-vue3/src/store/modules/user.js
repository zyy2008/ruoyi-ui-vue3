import { login, logout, getInfo } from "@/api/login";
import { getToken, setToken, removeToken } from "@/utils/auth";
import defAva from "@/assets/images/profile.jpg";
import layerStore from "@/store/modules/layer";
const useUserStore = defineStore("user", {
  state: () => ({
    token: getToken(),
    id: "",
    name: "",
    avatar: "",
    roles: [],
    permissions: [],
    roleId: "",
    baseIds: [],
    deptId: "",
    baseName: "",
    enterpriseId: "",
  }),
  actions: {
    // 登录
    login(userInfo) {
      const username = userInfo.username.trim();
      const password = userInfo.password;
      const code = userInfo.code;
      const uuid = userInfo.uuid;
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid)
          .then((res) => {
            setToken(res.token);
            this.token = res.token;
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    // 获取用户信息
    getInfo() {
      return new Promise((resolve, reject) => {
        getInfo()
          .then(async (res) => {
            const user = res.user;
            const enterprise = res?.enterprise;
            const avatar =
              user.avatar == "" || user.avatar == null
                ? defAva
                : (__APP_BASE_API__ || import.meta.env.VITE_APP_BASE_API) +
                  user.avatar;

            if (res.roles && res.roles.length > 0) {
              // 验证返回的roles是否是一个非空数组
              this.roles = res.roles;
              this.permissions = res.permissions;
            } else {
              this.roles = ["ROLE_DEFAULT"];
            }
            this.id = user.userId;
            this.deptId = user.deptId;
            this.name = user.userName;
            this.avatar = avatar;
            this.baseName = enterprise?.enterpriseName;
            this.enterpriseId = enterprise?.id;
            this.baseIds = user.baseIds; //多个基地
            if (user.roles && user.roles.length > 0) {
              this.roleId = user.roles[0].roleId;
            }

            // await layerStore().getBaseSelect(); //获取可以查看的基地

            // if (this.baseIds && this.baseIds.length == 1) {
            //   await layerStore().setBaseSelect(this.baseIds)
            // }
            resolve(res);
          })
          .catch((error) => {
            reject(error);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    // 退出系统
    logOut() {
      return new Promise((resolve, reject) => {
        logout(this.token)
          .then(() => {
            this.token = "";
            this.roles = [];
            this.permissions = [];
            removeToken();
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
});

export default useUserStore;
