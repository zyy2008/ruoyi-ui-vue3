import { defineComponent } from "vue";
import { ElCard, ElTabs, ElTabPane } from "element-plus";
import ProTable from "@/components/ProTable/index.vue";

export default defineComponent({
  setup() {
    return () => (
      <div class="app-container">
        <ElCard header="企业信息管理">
          <ElTabs type="border-card">
            <ElTabPane label="User">
              <ProTable />
            </ElTabPane>
            <ElTabPane label="User">1</ElTabPane>
            <ElTabPane label="User">1</ElTabPane>
          </ElTabs>
        </ElCard>
      </div>
    );
  },
});
