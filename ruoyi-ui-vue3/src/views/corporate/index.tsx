import { defineComponent } from "vue";
import { ElCard, ElTabs, ElTabPane } from "element-plus";
import ProTable from "@/components/ProTable/index.vue";
import { Tab1 } from "./components";

export default defineComponent({
  setup() {
    return () => (
      <div class="app-container">
        <ElCard header="企业信息管理">
          <ElTabs type="card">
            <ElTabPane label="企业地块基本情况">
              <Tab1 />
            </ElTabPane>
            <ElTabPane label="企业污染源信息">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="迁移途径信息">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="敏感受体信息">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="土壤或地下水环境监测">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="关闭搬迁企业地块基本情况">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="关闭搬迁企业污染源信息调查">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="环境监测和调查评估信息">
              <ProTable />
            </ElTabPane>
          </ElTabs>
        </ElCard>
      </div>
    );
  },
});
