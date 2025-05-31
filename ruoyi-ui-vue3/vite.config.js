import { defineConfig, loadEnv } from "vite";
import path from "path";
import createVitePlugins from "./vite/plugins";
import { mars3dPlugin } from "vite-plugin-mars3d";
import {
  createStyleImportPlugin,
  AndDesignVueResolve,
} from "vite-plugin-style-import";
import vueJsx from "@vitejs/plugin-vue-jsx";

// https://vitejs.dev/config/
export default defineConfig(({ mode, command }) => {
  const env = loadEnv(mode, process.cwd());
  const { VITE_APP_ENV } = env;
  return {
    // 部署生产环境和开发环境下的URL。
    // 默认情况下，vite 会假设你的应用是被部署在一个域名的根路径上
    // 例如 https://www.ruoyi.vip/。如果应用被部署在一个子路径上，你就需要用这个选项指定这个子路径。例如，如果你的应用被部署在 https://www.ruoyi.vip/admin/，则设置 baseUrl 为 /admin/。
    base: VITE_APP_ENV === "production" ? "./" : "./",
    // plugins: createVitePlugins(env, command === 'build'),
    plugins: [
      vueJsx(),
      createVitePlugins(env, command === "build"),
      mars3dPlugin(),
      createStyleImportPlugin({
        resolves: [AndDesignVueResolve()],
        libs: [
          {
            libraryName: "ant-design-vue",
            esModule: true,
            resolveStyle: (name) => {
              if (name === "auto-complete") {
                return `ant-design-vue/es/${name}/index`;
              }
              return `ant-design-vue/es/${name}/style/index`;
            },
          },
        ],
      }),
    ],
    resolve: {
      // https://cn.vitejs.dev/config/#resolve-alias
      alias: {
        // 设置路径
        "~": path.resolve(__dirname, "./"),
        // 设置别名
        "@": path.resolve(__dirname, "./src"),

        "@mars": path.join(__dirname, "src"),
      },
      // https://cn.vitejs.dev/config/#resolve-extensions
      extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"],
    },
    // vite 相关配置
    server: {
      port: 8016,
      host: true,
      open: true,
      proxy: {
        // https://cn.vitejs.dev/config/#server-proxy
        "/dev-api": {
          target: "http://39.100.43.147:11004/",
          // target: 'http://localhost:8080/',
          changeOrigin: true,
          rewrite: (p) => p.replace(/^\/dev-api/, ""),
        },
        "/project": {
          target: "http://39.100.43.147:11004/",
          // target: 'http://localhost:8080/',
          changeOrigin: true,
          rewrite: (p) => {
            console.log("🚀 ~ defineConfig ~ p:", p);
            p.replace(/^\/project/, "");

            console.log("🚀 ~ defineConfig ~ p:", p);
            return p;
          },
        },
        "/api1": {
          target: "http://39.100.43.147:11004/",
          // target: 'http://localhost:8080/',
          changeOrigin: true,
          rewrite: (p) => p.replace(/^\/api1/, ""),
        },
      },
    },
    //fix:error:stdin>:7356:1: warning: "@charset" must be the first rule in the file
    css: {
      preprocessorOptions: {
        sass: {
          javascriptEnabled: true,
          additionalData: `@import "${path.resolve(
            __dirname,
            "src/components/mars-ui/base.scss"
          )}";`,
        },
      },
      postcss: {
        plugins: [
          {
            postcssPlugin: "internal:charset-removal",
            AtRule: {
              charset: (atRule) => {
                if (atRule.name === "charset") {
                  atRule.remove();
                }
              },
            },
          },
        ],
      },
    },
  };
});
