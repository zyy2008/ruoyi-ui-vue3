import { defineComponent, ExtractPropTypes, PropType, ref } from "vue";
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
import { CheckboxGroup } from "./components";

const { SchemaField } = Schema;

const enumValue = ["工业", "住宅", "商业", "多村", "城镇", "农田"].map(
  (label, index) => ({
    label,
    value: `${index}`,
  })
);

const props: ISchemaFieldProps = {
  schema: {
    type: "object",
    properties: {
      card: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header:
            "地块及周边用地类型(说明:按工业、住宅、商业、多村、城镇、农田填写)",
        },
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
              surroundingLandEast: {
                type: "string",
                title: "东",
                enum: enumValue,
                "x-decorator": "FormItem",
                "x-component": "Select",
              },
              surroundingLandWest: {
                type: "string",
                title: "西",
                enum: enumValue,
                "x-decorator": "FormItem",
                "x-component": "Select",
              },
              surroundingLandSouth: {
                type: "string",
                title: "南",
                enum: enumValue,
                "x-decorator": "FormItem",
                "x-component": "Select",
              },
              surroundingLandNorth: {
                type: "string",
                title: "北",
                enum: enumValue,
                "x-decorator": "FormItem",
                "x-component": "Select",
              },
            },
          },
        },
      },
      card1: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "地块内职工人数",
        },
        properties: {
          employeeCountCategory: {
            type: "string",
            "x-decorator": "FormItem",
            enum: [
              {
                label: ">5000",
                value: "0",
              },
              {
                label: "1000-5000",
                value: "1",
              },
              {
                label: "100-1000",
                value: "2",
              },
              {
                label: "<100",
                value: "3",
              },
            ],
            "x-component": "Radio.Group",
          },
        },
      },
      card2: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "地块周边500m范围内常驻人口数量",
        },
        properties: {
          permanentPopulationCategory: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": "Checkbox.Group",
            enum: [
              {
                label: ">5000",
                value: "0",
              },
              {
                label: "1000-5000",
                value: "1",
              },
              {
                label: "100-1000",
                value: "2",
              },
              {
                label: "<100",
                value: "3",
              },
            ],
          },
        },
      },
      card3: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header:
            "地块周边1km范围内存在以下敏感目标到最近的重点区域距离（可多选）",
        },
        properties: {
          sensitiveTargets: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": <CheckboxGroup />,
          },
          // grid: {
          //   type: "void",
          //   "x-component": "FormGrid",
          //   "x-component-props": {
          //     minColumns: 4,
          //     maxColumns: 4,
          //   },
          //   properties: {
          //     div: {
          //       type: "void",
          //       "x-component": "div",
          //       properties: {
          //         a33: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "无敏感目标",
          //               value: "1",
          //             },
          //           ],
          //         },
          //         a37: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "幼儿园",
          //               value: "1",
          //             },
          //           ],
          //         },
          //         a38: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "学校",
          //               value: "1",
          //             },
          //           ],
          //         },
          //       },
          //     },
          //     div1: {
          //       type: "void",
          //       "x-component": "div",
          //       properties: {
          //         a33: {
          //           "x-decorator": "FormItem",
          //         },
          //         a37: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "<100m",
          //               value: "1",
          //             },
          //           ],
          //         },
          //         a38: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "<100m",
          //               value: "1",
          //             },
          //           ],
          //         },
          //       },
          //     },
          //     div2: {
          //       type: "void",
          //       "x-component": "div",
          //       properties: {
          //         a33: {
          //           "x-decorator": "FormItem",
          //         },
          //         a37: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "100m<300m",
          //               value: "1",
          //             },
          //           ],
          //         },
          //         a38: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "100m<300m",
          //               value: "1",
          //             },
          //           ],
          //         },
          //       },
          //     },
          //     div3: {
          //       type: "void",
          //       "x-component": "div",
          //       properties: {
          //         a33: {
          //           "x-decorator": "FormItem",
          //         },
          //         a37: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "300m<1000m",
          //               value: "1",
          //             },
          //           ],
          //         },
          //         a38: {
          //           type: "string",
          //           "x-decorator": "FormItem",
          //           "x-component": "Checkbox.Group",
          //           enum: [
          //             {
          //               label: "300m<1000m",
          //               value: "1",
          //             },
          //           ],
          //         },
          //       },
          //     },
          //   },
          // },
        },
      },
    },
  },
};

export default defineComponent({
  setup() {
    const { deptId } = userStore();
    const { run, data, loading } = useRequest(() =>
      API.getAdminReceptorList({
        deptId,
      })
    );
    const form = createForm();

    return () => (
      <div class="app-container">
        <FormProvider form={form}>
          <SchemaField {...props} />
          <FormButtonGroup
            align="center"
            style={{
              margin: "10px",
            }}
          >
            <ElButton
              loading={loading.value}
              type="primary"
              onClick={() => {
                form.submit().then((val: any) => {
                  const { id } = val;
                  const api = id ? API.putAdminReceptor : API.postAdminReceptor;
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
              提交
            </ElButton>
          </FormButtonGroup>
        </FormProvider>
      </div>
    );
  },
});
