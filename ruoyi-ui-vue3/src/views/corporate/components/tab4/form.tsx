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
          b: {
            type: "void",
            "x-component": "FormGrid.GridColumn",
            "x-component-props": {
              gridSpan: 4,
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "地块及周边用地类型",
                },
                properties: {
                  a1: {
                    type: "string",
                    title: "东",
                    "x-decorator": "FormItem",
                    "x-component": "Select",
                  },
                  a2: {
                    type: "string",
                    title: "西",
                    "x-decorator": "FormItem",
                    "x-component": "Select",
                  },
                  a3: {
                    type: "string",
                    title: "南",
                    "x-decorator": "FormItem",
                    "x-component": "Select",
                  },
                  a4: {
                    type: "string",
                    title: "北",
                    "x-decorator": "FormItem",
                    "x-component": "Select",
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
                  a5: {
                    type: "string",
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: ">5000",
                        value: "1",
                      },
                      {
                        label: "1000-5000",
                        value: "2",
                      },
                      {
                        label: "100-1000",
                        value: "3",
                      },
                      {
                        label: "<100",
                        value: "4",
                      },
                    ],
                    "x-component": "Checkbox.Group",
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
                  a5: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "Checkbox.Group",
                    enum: [
                      {
                        label: ">5000",
                        value: "1",
                      },
                      {
                        label: "1000-5000",
                        value: "2",
                      },
                      {
                        label: "100-1000",
                        value: "3",
                      },
                      {
                        label: "<100",
                        value: "4",
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
                  grid: {
                    type: "void",
                    "x-component": "FormGrid",
                    "x-component-props": {
                      minColumns: 4,
                      maxColumns: 4,
                    },
                    properties: {
                      div: {
                        type: "void",
                        "x-component": "div",
                        properties: {
                          a33: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "无敏感目标",
                                value: "1",
                              },
                            ],
                          },
                          a37: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "幼儿园",
                                value: "1",
                              },
                            ],
                          },
                          a38: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "学校",
                                value: "1",
                              },
                            ],
                          },
                        },
                      },
                      div1: {
                        type: "void",
                        "x-component": "div",
                        properties: {
                          a33: {
                            "x-decorator": "FormItem",
                          },
                          a37: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "<100m",
                                value: "1",
                              },
                            ],
                          },
                          a38: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "<100m",
                                value: "1",
                              },
                            ],
                          },
                        },
                      },
                      div2: {
                        type: "void",
                        "x-component": "div",
                        properties: {
                          a33: {
                            "x-decorator": "FormItem",
                          },
                          a37: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "100m<300m",
                                value: "1",
                              },
                            ],
                          },
                          a38: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "100m<300m",
                                value: "1",
                              },
                            ],
                          },
                        },
                      },
                      div3: {
                        type: "void",
                        "x-component": "div",
                        properties: {
                          a33: {
                            "x-decorator": "FormItem",
                          },
                          a37: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "300m<1000m",
                                value: "1",
                              },
                            ],
                          },
                          a38: {
                            type: "string",
                            "x-decorator": "FormItem",
                            "x-component": "Checkbox.Group",
                            enum: [
                              {
                                label: "300m<1000m",
                                value: "1",
                              },
                            ],
                          },
                        },
                      },
                    },
                  },
                },
              },
            },
          },
        },
      },
    },
  },
};

// const actionProps = () => ({
//   disabled: Boolean,
//   form: { type: Object as PropType<Form<any>>, default: undefined },
// });

// type ActionProps = Partial<ExtractPropTypes<ReturnType<typeof actionProps>>>;

export default defineComponent({
  setup() {
    const disabled = ref<boolean>();
    const { deptId } = userStore();
    const { runAsync, loading } = useRequest(
      () =>
        API.getAdminEnterpriseList({
          deptId,
        }),
      {
        manual: true,
      }
    );
    const form = createForm();
    // autorun(() => {
    //   disabled.value = form.disabled;
    // });

    return () => (
      <ElCard
        v-loading={loading.value}
        v-slots={{
          header: () => (
            <ElSpace>
              <ElButton
                icon={EditPen}
                disabled={!disabled.value}
                onClick={() => {
                  form.disabled = false;
                }}
                type="primary"
              >
                编辑
              </ElButton>
            </ElSpace>
          ),
        }}
      >
        <FormProvider form={form}>
          <SchemaField {...props} />
          <FormButtonGroup
            align="center"
            style={{
              margin: "10px",
            }}
          >
            <Submit
              disabled={disabled.value}
              onSubmit={(val) => {
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
                    runAsync();
                  } else {
                    ElMessage.success("保存失败");
                  }
                });
              }}
            >
              提交
            </Submit>
            <ElButton
              disabled={disabled.value}
              onClick={() => {
                form.disabled = true;
              }}
            >
              取消
            </ElButton>
          </FormButtonGroup>
        </FormProvider>
      </ElCard>
    );
  },
});
