import { defineComponent } from "vue";
import ProTable from "@/components/ProTable/index.vue";
import type { ProTableProps } from "@/components/ProTable/interface";
import API from "@/services";

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
    console.log(val);
    return API.getAdminEnterpriseList({});
  },
};

export default defineComponent({
  setup: () => {
    return () => (
      <ProTable {...props}>
        {{
          tableHeader: () => <div>123</div>,
        }}
      </ProTable>
    );
  },
});
