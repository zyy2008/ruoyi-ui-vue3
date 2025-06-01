import { defineComponent, ExtractPropTypes, PropType, watchEffect } from "vue";
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
          legalRepresentative: {
            type: "string",
            title: "法定代表人",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          address: {
            type: "string",
            title: "单位所在地详细地址",
            "x-decorator": "FormItem",
            "x-component": "Input.TextArea",
          },
          regionCode: {
            type: "string",
            title: "行政区划代码",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          centerLatitude: {
            type: "string",
            title: "中心纬度",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          centerLongitude: {
            type: "string",
            title: "中心经度",
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
            "x-decorator": "FormItem",
            "x-component": "Select",
          },
          enterpriseScale: {
            type: "string",
            title: "企业规模",
            "x-decorator": "FormItem",
            "x-component": "Select",
          },
          startDate: {
            type: "string",
            title: "开业时间",
            "x-decorator": "FormItem",
            "x-component": "DatePicker",
            "x-component-props": {
              type: "datetime",
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
                value: true,
              },
              {
                label: "否",
                value: false,
              },
            ],
            "x-decorator": "FormItem",
            "x-component": "Radio.Group",
          },
          areaActual: {
            type: "string",
            title: "地块实际使用面积",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },

          companyCreditCode: {
            type: "string",
            title: "统一社会信用代码",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },
          companyName: {
            type: "string",
            title: "单位名称",
            "x-decorator": "FormItem",
            "x-component": "Input",
          },

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
            "x-component": "Input",
          },
          // historyStartYear: {
          //   type: "number",
          //   title: "利用历史起始年份",
          //   "x-decorator": "FormItem",
          //   "x-component": "Input",
          // },
          history: {
            type: "array",
            title: "地块利用历史年份",
            "x-decorator": "FormItem",
            "x-component": "DatePicker",
            "x-component-props": {
              type: "yearrange",
              startPlaceholder: "开始年份",
              endPlaceholder: "结束年份",
              format: "YYYY",
              valueFormat: "YYYY",
            },
            "x-reactions": (field: Field) => {
              const form = field.form;
              field.componentProps = {
                ...field.componentProps,
                onChange: (val: any) => {
                  console.log(val);
                  const [historyStartYear, historyEndYear] = val;
                  form.setValuesIn("historyStartYear", historyStartYear);
                  form.setValuesIn("historyEndYear", historyEndYear);
                },
              };
              const { historyStartYear, historyEndYear } = field.form.values;
              if (historyStartYear && historyEndYear) {
                form.setValuesIn("historyStartYear", historyStartYear);
                form.setValuesIn("historyEndYear", historyEndYear);
              }
            },
          },
        },
      },
    },
  },
};

const form = createForm();

export default defineComponent({
  setup() {
    const { enterpriseId: deptId } = userStore();
    const { run, loading, data } = useRequest(() =>
      API.getAdminEnterpriseList({
        deptId,
      })
    );
    watchEffect(() => {
      if (data.value?.code === 200) {
        const [item] = data.value.rows;
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
