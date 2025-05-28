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
              collapse: {
                type: "void",
                "x-component": "FormCollapse",
                properties: {
                  tab1: {
                    type: "void",
                    "x-component": "FormCollapse.Item",
                    "x-component-props": {
                      title: "一、土壤途径",
                    },
                    properties: {
                      card: {
                        type: "void",
                        "x-component": "Card",
                        "x-component-props": {
                          header: "含水层以上土壤分层情况",
                        },
                        properties: {
                          array1: {
                            type: "array",
                            "x-component": "ArrayTable",
                            items: {
                              type: "object",
                              properties: {
                                column1: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    width: 80,
                                    title: "序号",
                                    align: "center",
                                  },
                                  properties: {
                                    index: {
                                      type: "void",
                                      "x-component": "ArrayTable.Index",
                                    },
                                  },
                                },
                                column2: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    title: "土层性质",
                                  },
                                  properties: {
                                    a1: {
                                      type: "string",
                                      "x-decorator": "FormItem",
                                      "x-component": "Input",
                                    },
                                  },
                                },
                                column3: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    title: "土层（上层）埋深（cm）",
                                  },
                                  properties: {
                                    a1: {
                                      type: "string",
                                      "x-decorator": "FormItem",
                                      "x-component": "Input",
                                    },
                                  },
                                },
                                column4: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    title: "土层厚度（cm）",
                                  },
                                  properties: {
                                    a1: {
                                      type: "string",
                                      "x-decorator": "FormItem",
                                      "x-component": "Input",
                                    },
                                  },
                                },
                                column7: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    title: "操作",
                                    prop: "operations",
                                    width: 300,
                                    fixed: "right",
                                  },
                                  properties: {
                                    item: {
                                      type: "void",
                                      "x-component": "FormItem",
                                      properties: {
                                        remove: {
                                          type: "void",
                                          "x-component": "ArrayTable.Remove",
                                        },
                                        moveDown: {
                                          type: "void",
                                          "x-component": "ArrayTable.MoveDown",
                                        },
                                        moveUp: {
                                          type: "void",
                                          "x-component": "ArrayTable.MoveUp",
                                        },
                                      },
                                    },
                                  },
                                },
                              },
                            },
                            properties: {
                              add: {
                                type: "void",
                                "x-component": "ArrayTable.Addition",
                                title: "添加条目",
                              },
                            },
                          },
                        },
                      },
                    },
                  },
                  tab2: {
                    type: "void",
                    "x-component": "FormCollapse.Item",
                    "x-component-props": {
                      title: "二、地下水途径",
                    },
                    properties: {
                      card: {
                        type: "void",
                        "x-component": "Card",
                        "x-component-props": {
                          header: "地下水深埋",
                        },
                        properties: {
                          a1: {
                            type: "string",
                            "x-decorator": "FormItem",
                            enum: [
                              {
                                label: "<3m",
                                value: "1",
                              },
                              {
                                label: "3-10m",
                                value: "2",
                              },
                              {
                                label: ">10m",
                                value: "3",
                              },
                              {
                                label: "不确定",
                                value: "4",
                              },
                            ],
                            "x-component": "Checkbox.Group",
                          },
                        },
                      },
                      card1: {
                        type: "void",
                        "x-component": "Card",
                        "x-component-props": {
                          header: "含水层渗透性",
                        },
                        properties: {
                          a1: {
                            type: "string",
                            "x-decorator": "FormItem",
                            enum: [
                              {
                                label: "硕沙土以上",
                                value: "1",
                              },
                              {
                                label: "粗沙土、中沙土及细沙土",
                                value: "2",
                              },
                              {
                                label: "粉沙土及以下",
                                value: "3",
                              },
                              {
                                label: "不确定",
                                value: "4",
                              },
                            ],
                            "x-component": "Checkbox.Group",
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
