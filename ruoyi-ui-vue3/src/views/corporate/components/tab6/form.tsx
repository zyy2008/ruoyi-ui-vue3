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
import { handeSchema } from "../tab5/form";

const { SchemaField } = Schema;

const props: ISchemaFieldProps = {
  schema: handeSchema({
    tab3: {
      type: "void",
      "x-component": "FormCollapse.Item",
      "x-component-props": {
        title: "三、调查评估",
      },
      properties: {
        card: {
          type: "void",
          "x-component": "Card",
          "x-component-props": {
            header: "调查评估",
          },
          properties: {
            a1: {
              type: "string",
              "x-decorator": "FormItem",
              "x-component": "Radio.Group",
              enum: [
                {
                  label: "未开展过",
                  value: "0",
                },
                {
                  label: "正在开展",
                  value: "1",
                },
                {
                  label: "己经完成",
                  value: "2",
                },
              ],
            },
          },
        },
        card1: {
          type: "void",
          "x-component": "Card",
          "x-component-props": {
            header: "调查时间",
          },
          properties: {
            a5: {
              type: "string",
              "x-decorator": "FormItem",
              "x-component": "DatePicker",
              "x-component-props": {
                type: "year",
                format: "YYYY",
                valueFormat: "YYYY",
              },
            },
          },
        },
        card2: {
          type: "void",
          "x-component": "Card",
          "x-component-props": {
            header:
              "是否检出污染物超标 (若选择是，则须填写以下第4-6项内容，否则不填)",
          },
          properties: {
            a1: {
              type: "string",
              "x-decorator": "FormItem",
              "x-component": "Radio.Group",
              enum: [
                {
                  label: "是",
                  value: "1",
                },
                {
                  label: "否",
                  value: "0",
                },
              ],
            },
          },
        },
        card3: {
          type: "void",
          "x-component": "Card",
          "x-component-props": {
            header: "超标污染物",
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
                      title: "超标污染物名称",
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
                      title: "最大实测浓度(mg/kg)",
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
        card4: {
          type: "void",
          "x-component": "Card",
          "x-component-props": {
            header: "数据来源",
          },
          properties: {
            a1: {
              type: "string",
              "x-decorator": "FormItem",
              "x-component": "Input",
            },
          },
        },
      },
    },
  }),
};

const form = createForm();

export default defineComponent({
  setup() {
    const disabled = ref<boolean>();
    const { enterpriseId: deptId } = userStore();
    const { runAsync, loading } = useRequest(
      () =>
        API.getAdminEnterpriseList({
          deptId,
        }),
      {
        manual: true,
      }
    );

    return () => (
      <div class="app-container">
        <ElCard>
          <FormProvider form={form}>
            <SchemaField {...props} />
          </FormProvider>
        </ElCard>
      </div>
    );
  },
});
