import { defineComponent, provide, watchEffect } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps } from "@formily/vue";
import { useRequest } from "vue-request";
import API from "@/services";
import userStore from "@/store/modules/user";
import { Field } from "@formily/core";

const props: ISchemaFieldProps = {
  schema: {
    type: "void",
    properties: {
      card: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "超标污染物",
        },
        properties: {
          array: {
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
                    width: 200,
                  },
                  properties: {
                    pollutantName: {
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
                    title: "最大浓度(mg/l)",
                    width: 200,
                  },
                  properties: {
                    maxConcentrationMgl: {
                      type: "number",
                      "x-decorator": "FormItem",
                      "x-component": "InputNumber",
                    },
                  },
                },
                column6: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "添加人",
                    width: 200,
                  },
                  properties: {
                    createdBy: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                      "x-editable": false,
                    },
                  },
                },
                column7: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "创建时间",
                    width: 300,
                  },

                  properties: {
                    createTime: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                      "x-editable": false,
                    },
                  },
                },
                column8: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "操作",
                    prop: "operations",
                    width: 160,
                    fixed: "right",
                  },
                  properties: {
                    item: {
                      type: "void",
                      "x-component": "FormItem",
                      properties: {
                        remove: {
                          type: "void",
                          "x-component": "PopconfirmDel.Remove",
                        },
                        save: {
                          type: "void",
                          "x-component": "PopconfirmDel.Save",
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
};
const form = createForm();
export default observer(
  defineComponent({
    setup() {
      const { data, run } = useRequest(() =>
        API.getAdminGroundwaterPollutantList({
          deptId: userStore().deptId,
        })
      );
      watchEffect(() => {
        if (data.value?.code === 200) {
          form.reset().then(() => {
            form.setValues({
              array: data.value.rows ?? [],
            });
          });
        }
      });
      provide("form", {
        run,
        apiAdd: API.postAdminGroundwaterPollutant,
        apiEdit: API.putAdminGroundwaterPollutant,
        apiDel: API.deleteAdminGroundwaterPollutantIds,
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
        </FormProvider>
      );
    },
  })
);
