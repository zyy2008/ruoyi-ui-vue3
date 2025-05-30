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
import { MainProducts } from "./components";

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
            title: "企业存在以下设施或区域",
            "x-decorator": "FormItem",
            enum: [
              {
                label: "生产区",
                value: "1",
              },
              {
                label: "储存区",
                value: "2",
              },
              {
                label: "管道",
                value: "3",
              },
              {
                label: "废气治理设施",
                value: "4",
              },
              {
                label: "废水治理区域",
                value: "5",
              },
              {
                label: "固体废物贮存或处置区",
                value: "6",
              },
              {
                label: "空地",
                value: "7",
              },
            ],
            "x-component": "Checkbox.Group",
          },
          plotName: {
            type: "string",
            title: "平面布置图",
            "x-decorator": "FormItem",
            "x-component": "Upload",
            "x-component-props": {
              listType: "picture-card",
            },
          },
          a: {
            "x-decorator": "FormItem",
          },
          col: {
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
                      title: "一、生产区",
                    },
                    properties: {
                      card: {
                        type: "void",
                        "x-component": <MainProducts />,
                      },
                      card1: {
                        type: "void",
                        "x-component": "Card",
                        "x-component-props": {
                          header: "二、主要原辅材料",
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
                                    title: "原辅材料名称",
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
                                    title: "使用年代",
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
                                    title: "计量单位",
                                  },
                                  properties: {
                                    a1: {
                                      type: "string",
                                      "x-decorator": "FormItem",
                                      "x-component": "Input",
                                    },
                                  },
                                },
                                column5: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    title: "年平均产量",
                                  },
                                  properties: {
                                    a1: {
                                      type: "string",
                                      "x-decorator": "FormItem",
                                      "x-component": "Input",
                                    },
                                  },
                                },
                                column6: {
                                  type: "void",
                                  "x-component": "ArrayTable.Column",
                                  "x-component-props": {
                                    title: "添加人",
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
                      card2: {
                        type: "void",
                        "x-component": "Card",
                        "x-component-props": {
                          header: "三、主要中间产物",
                        },
                        properties: {
                          a23: {
                            type: "string",
                            title: "主要中间产物名称",
                            "x-decorator": "FormItem",
                            "x-component": "Input.TextArea",
                          },
                        },
                      },
                    },
                  },
                  tab2: {
                    type: "void",
                    "x-component": "FormCollapse.Item",
                    "x-component-props": {
                      title: "二、存储区",
                    },
                    properties: {
                      array2: {
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
                                width: 200,
                                title: "产品名称",
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
                                width: 200,
                                title: "生产年代",
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
                                width: 200,
                                title: "计量单位",
                              },
                              properties: {
                                a1: {
                                  type: "string",
                                  "x-decorator": "FormItem",
                                  "x-component": "Input",
                                },
                              },
                            },
                            column5: {
                              type: "void",
                              "x-component": "ArrayTable.Column",
                              "x-component-props": {
                                width: 200,
                                title: "年平均产量",
                              },
                              properties: {
                                a1: {
                                  type: "string",
                                  "x-decorator": "FormItem",
                                  "x-component": "Input",
                                },
                              },
                            },
                            column6: {
                              type: "void",
                              "x-component": "ArrayTable.Column",
                              "x-component-props": {
                                width: 200,
                                title: "添加人",
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
                                width: 200,
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
            },
          },
        },
      },
    },
  },
};

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

    return () => (
      <div class="app-container">
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
      </div>
    );
  },
});
