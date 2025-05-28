import { defineComponent, ref } from "vue";
import ProTable from "@/components/ProTable/index.vue";
import type { ProTableProps } from "@/components/ProTable/interface";
import API from "@/services";
import { ElButton } from "element-plus";
import { CirclePlus, Delete, EditPen } from "@element-plus/icons-vue";
import useCrud from "@/hooks/useCrud";
import Form from "./form";

const props: ProTableProps = {
  columns: [
    { type: "selection", fixed: "left", width: 70 },
    {
      label: "序号",
      type: "index",
      width: 80,
      align: "center",
    },
    {
      label: "单位所在地详细地址",
      prop: "address",
    },
    {
      label: "地块实际使用面积",
      prop: "areaActual",
    },
    {
      label: "地块占地面积",
      prop: "areaLandCert",
    },
    {
      label: "中心纬度",
      prop: "centerLatitude",
    },
    {
      label: "中心经度",
      prop: "centerLongitude",
    },
    {
      label: "统一社会信用代码",
      prop: "companyCreditCode",
    },
    {
      label: "单位名称",
      prop: "companyName",
    },
    {
      label: "联系人姓名",
      prop: "contactName",
    },
    {
      label: "联系人电话",
      prop: "contactPhone",
    },
    {
      label: "归属部门ID",
      prop: "deptId",
    },
    {
      label: "企业规模",
      prop: "enterpriseScale",
    },
    {
      label: "利用历史起始年份",
      prop: "historyStartYear",
    },
    {
      label: "利用历史结束年份",
      prop: "historyEndYear",
    },
    {
      label: "地块利用历史",
      prop: "historyLandUse",
    },
    {
      label: "是否位于工业园区或集聚区",
      prop: "inIndustrialPark",
    },
    {
      label: "行业类别",
      prop: "industryCategory",
    },
    {
      label: "行业代码",
      prop: "industryCode",
    },
    {
      label: "最新改扩建时间",
      prop: "latestExpansionDate",
    },
    {
      label: "法定代表人",
      prop: "legalRepresentative",
    },
    {
      label: "地块编码",
      prop: "plotCode",
    },
    {
      label: "地块名称",
      prop: "plotName",
    },
    {
      label: "行政区划代码",
      prop: "regionCode",
    },
    {
      label: "登记注册类型",
      prop: "registrationType",
    },
    {
      label: "开业时间",
      prop: "startDate",
    },
    {
      label: "操作",
      fixed: "right",
      width: 200,
      render: () => {
        return <ElButton icon={EditPen}>123</ElButton>;
      },
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
          tableHeader: (scope) => (
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
              <ElButton
                icon={Delete}
                disabled={!scope.isSelected}
                type="danger"
              >
                批量删除
              </ElButton>
            </>
          ),
        }}
      </ProTable>
    );
  },
});
