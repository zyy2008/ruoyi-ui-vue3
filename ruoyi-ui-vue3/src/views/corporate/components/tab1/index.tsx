import { defineComponent, ref } from "vue";
import ProTable from "@/components/ProTable/index.vue";
import type { ProTableProps } from "@/components/ProTable/interface";
import API from "@/services";
import { ElButton } from "element-plus";
import { CirclePlus, Delete } from "@element-plus/icons-vue";
import useCrud from "@/hooks/useCrud";
import Form from "./form";

const props: ProTableProps = {
  columns: [
    {
      label: "序号",
      prop: "address",
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
  dataCallback: (res) => {
    return {
      list: res?.rows ?? [],
    };
  },
  requestApi: (val) => {
    return API.getAdminEnterpriseList({});
  },
};

export default defineComponent({
  setup: () => {
    const divRef = ref(null);
    const { add } = useCrud({
      content: () => <Form />,
      apiAdd: API.postAdminEnterprise,
      ref: divRef,
    });
    return () => (
      <ProTable ref={divRef} {...props}>
        {{
          tableHeader: () => (
            <>
              <ElButton
                icon={CirclePlus}
                type="primary"
                onClick={() => {
                  add();
                }}
              >
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
