import { defineComponent, toRef, provide, unref } from "vue";
import { ElCard, ElTabs, ElTabPane } from "element-plus";
import { Tab1, Tab2, Tab3, Tab4, Tab5, Tab6 } from "./components";

export default defineComponent({
  props: ["deptId"],
  setup(props) {
    const deptId = toRef(props, "deptId");
    provide("deptId", {
      deptId: unref(deptId),
    });
    return () => (
      <ElTabs type="card">
        <ElTabPane label="企业地块基本情况">
          <Tab1 />
        </ElTabPane>
        <ElTabPane label="企业污染源信息">
          <Tab2 />
        </ElTabPane>
        <ElTabPane label="迁移途径信息">
          <Tab3 />
        </ElTabPane>
        <ElTabPane label="敏感受体信息">
          <Tab4 />
        </ElTabPane>
        <ElTabPane label="土壤或地下水环境监测">
          <Tab5 />
        </ElTabPane>
        <ElTabPane label="环境监测和调查评估信息">
          <Tab6 />
        </ElTabPane>
      </ElTabs>
    );
  },
});
