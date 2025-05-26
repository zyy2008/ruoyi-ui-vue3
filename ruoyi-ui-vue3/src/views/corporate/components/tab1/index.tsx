import { defineComponent } from "vue";
import ProTable from "@/components/ProTable/index.vue";
import type { ProTableProps } from "@/components/ProTable/interface";
import API from "@/services";
import { ElButton } from "element-plus";
import { CirclePlus, Delete } from "@element-plus/icons-vue";

const props: ProTableProps = {
  columns: [
    {
      label: "序号",
      prop: "id",
      type: "index",
      width: 80,
      align: "center",
    },
    {
      label: "姓名",
      prop: "name",
      width: 120,
    },
    {
      label: "性别",
      prop: "sex",
    },
  ],
  requestApi: (val) => {
    return API.getAdminEnterpriseList({});
  },
};

export default defineComponent({
  setup: () => {
    return () => (
      <ProTable {...props}>
        {{
          tableHeader: () => (
            <>
              <ElButton icon={CirclePlus} type="primary">
                新增
              </ElButton>
              <ElButton icon={Delete} type="danger">
                批量删除
              </ElButton>
            </>
          ),
        }}
      </ProTable>
    );
  },
});
