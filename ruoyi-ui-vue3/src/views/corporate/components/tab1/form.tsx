import {
  defineComponent,
  ExtractPropTypes,
  PropType,
  watchEffect,
  inject,
} from "vue";
import Schema from "@/components/Form";
import { ISchemaFieldProps, FormProvider } from "@formily/vue";
import { Field, createForm, onFormInit, Form } from "@formily/core";
import { FormButtonGroup, Submit, Reset } from "@formily/element-plus";
import userStore from "@/store/modules/user";
import API from "@/services";
import { ElCard, ElButton, ElSpace, ElMessage } from "element-plus";
import { CloseBold, EditPen } from "@element-plus/icons-vue";
import { observer } from "@formily/reactive-vue";
import { observable, autorun } from "@formily/reactive";
import { useRequest } from "vue-request";
import { FromTable } from "./components";
import { useDeptId } from "@/hooks";
import { listEnterprises, getEnterprises } from "@/api/admin/enterprises";

const { SchemaField } = Schema;

const props: ISchemaFieldProps = {
  schema: {
    type: "object",
    properties: {
      layout: {
        type: "void",
        "x-decorator": "FormLayout",
        "x-decorator-props": {
          labelCol: 10,
          wrapperCol: 14,
        },
        "x-component": "FormGrid",
        "x-component-props": {
          minColumns: 3,
          maxColumns: 3,
        },
        properties: {
          plotCode: {
            type: "string",
            title: "地块编码",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          plotName: {
            type: "string",
            title: "地块名称",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          companyName: {
            type: "string",
            title: "单位名称",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          companyCreditCode: {
            type: "string",
            title: "统一社会信用代码",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          legalRepresentative: {
            type: "string",
            title: "法定代表人",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          regionCode: {
            type: "string",
            title: "行政区划代码",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          address: {
            type: "string",
            title: "单位所在地详细地址",
            "x-decorator": "FormItem",
            "x-component": "Input.TextArea",
          },
          centerLongitude: {
            type: "string",
            title: "中心经度",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          centerLatitude: {
            type: "string",
            title: "中心纬度",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },

          areaLandCert: {
            type: "string",
            title: "地块占地面积(m2)",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          contactName: {
            type: "string",
            title: "联系人姓名",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          contactPhone: {
            type: "string",
            title: "联系人电话",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          industryCategory: {
            type: "string",
            title: "行业类别",
            "x-decorator": "FormItem",
            "x-component": "Select",
          },
          industryCode: {
            type: "string",
            title: "行业代码",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          registrationType: {
            type: "string",
            title: "登记注册类型",
            enum: [
              {
                label: "内资企业",
                options: [
                  {
                    label: "国有企业",
                    value: "110",
                  },
                  {
                    label: "集体企业",
                    value: "120",
                  },
                  {
                    label: "股份合作企业",
                    value: "130",
                  },
                  {
                    label: "联营企业",
                    value: "140",
                  },
                  {
                    label: "有限责任公司",
                    value: "150",
                  },
                  {
                    label: "股份有限公司",
                    value: "160",
                  },
                  {
                    label: "私营企业",
                    value: "170",
                  },
                  {
                    label: "其他企业",
                    value: "190",
                  },
                ],
              },
              {
                label: "港、澳、台商投资企业",
                options: [
                  {
                    label: "国有企业",
                    value: "210",
                  },
                  {
                    label: "集体企业",
                    value: "220",
                  },
                  {
                    label: "股份合作企业",
                    value: "230",
                  },
                  {
                    label: "联营企业",
                    value: "240",
                  },
                ],
              },
              {
                label: "外商投资企业",
                options: [
                  {
                    label: "中外合资经营企业",
                    value: "310",
                  },
                  {
                    label: "中外合作经营企业",
                    value: "320",
                  },
                  {
                    label: "外资企业",
                    value: "330",
                  },
                  {
                    label: "外商投资股份有限公司",
                    value: "340",
                  },
                ],
              },
            ],
            "x-decorator": "FormItem",
            "x-component": "SelectGroup",
          },
          enterpriseScale: {
            type: "string",
            title: "企业规模",
            "x-decorator": "FormItem",
            "x-component": "Select",
            enum: [
              {
                label: "大型",
                value: "0",
              },
              {
                label: "中型",
                value: "1",
              },
              {
                label: "小型",
                value: "2",
              },
              {
                label: "微型",
                value: "3",
              },
            ],
          },
          startDate: {
            type: "string",
            title: "开业时间",
            "x-decorator": "FormItem",
            "x-component": "DatePicker",
            "x-component-props": {
              type: "month",
              format: "YYYY-MM",
              valueFormat: "YYYY-MM",
            },
          },
          latestExpansionDate: {
            type: "string",
            title: "最新改扩建时间",
            "x-decorator": "FormItem",
            "x-component": "DatePicker",
            "x-component-props": {
              type: "month",
              format: "YYYY-MM",
              valueFormat: "YYYY-MM",
            },
          },
          inIndustrialPark: {
            type: "string",
            title: "是否位于工业园区或集聚区",
            enum: [
              {
                label: "是",
                value: "0",
              },
              {
                label: "否",
                value: "1",
              },
            ],
            "x-decorator": "FormItem",
            "x-component": "Radio.Group",
          },
          // areaActual: {
          //   type: "string",
          //   title: "地块实际使用面积",
          //   "x-decorator": "FormItem",
          //   "x-component": "Input",
          // },

          // deptId: {
          //   type: "number",
          //   title: "归属部门ID",
          //   "x-decorator": "FormItem",
          //   "x-component": "InputNumber",
          // },

          // historyEndYear: {
          //   type: "number",
          //   title: "利用历史结束年份",
          //   "x-decorator": "FormItem",
          //   "x-component": "Input",
          // },
          historyLandUse: {
            type: "string",
            title: "地块利用历史",
            "x-decorator": "FormItem",
            "x-component": "SelectString",
            enum: [
              {
                label: "工业类用地",
                value: "0",
              },
              {
                label: "住宅类用地",
                value: "1",
              },
              {
                label: "商业类用地",
                value: "2",
              },
              {
                label: "其他用途-荒地",
                value: "3",
              },
              {
                label: "不确定",
                value: "4",
              },
            ],
          },
          // historyStartYear: {
          //   type: "number",
          //   title: "利用历史起始年份",
          //   "x-decorator": "FormItem",
          //   "x-component": "Input",
          // },
          // history: {
          //   type: "array",
          //   title: "地块利用历史年份",
          //   "x-decorator": "FormItem",
          //   "x-component": "DatePicker",
          //   "x-component-props": {
          //     type: "yearrange",
          //     startPlaceholder: "开始年份",
          //     endPlaceholder: "结束年份",
          //     format: "YYYY",
          //     valueFormat: "YYYY",
          //   },
          //   "x-reactions": (field: Field) => {
          //     const form = field.form;
          //     field.componentProps = {
          //       ...field.componentProps,
          //       onChange: (val: any) => {
          //         console.log(val);
          //         const [historyStartYear, historyEndYear] = val;
          //         form.setValuesIn("historyStartYear", historyStartYear);
          //         form.setValuesIn("historyEndYear", historyEndYear);
          //       },
          //     };
          //     const { historyStartYear, historyEndYear } = field.form.values;
          //     if (historyStartYear && historyEndYear) {
          //       form.setValuesIn("historyStartYear", historyStartYear);
          //       form.setValuesIn("historyEndYear", historyEndYear);
          //     }
          //   },
          // },
        },
      },
      card: {
        type: "void",
        "x-component": <FromTable />,
      },
    },
  },
};

const form = createForm();

export default defineComponent({
  setup() {
    const { deptId } = useDeptId();
    const { run, loading, data } = useRequest<any>(() =>
      getEnterprises(deptId)
    );
    watchEffect(() => {
      if (data.value?.code === 200) {
        const item = data.value.data;
        form.reset().then(() => {
          form.setValues(item);
        });
      }
    });

    return () => (
      <div class="app-container">
        <ElCard>
          <FormProvider form={form}>
            <SchemaField {...props} />
            <FormButtonGroup
              align="center"
              style={{
                margin: "10px",
              }}
            >
              <ElButton
                type="primary"
                loading={loading.value}
                onClick={() => {
                  form.submit().then((val: any) => {
                    const { id } = val;
                    const api = id
                      ? API.putAdminEnterprise
                      : API.postAdminEnterprise;
                    api({
                      ...val,
                      deptId,
                    }).then((res) => {
                      if (res.code === 200) {
                        ElMessage.success("保存成功");
                        run();
                      } else {
                        ElMessage.success("保存失败");
                      }
                    });
                  });
                }}
              >
                保存
              </ElButton>
            </FormButtonGroup>
          </FormProvider>
        </ElCard>
      </div>
    );
  },
});
