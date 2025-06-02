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
          header: "一、主要产品",
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
                    title: "产品名称",
                    width: 200,
                  },
                  properties: {
                    productName: {
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
                    title: "生产年代",
                    width: 200,
                  },
                  properties: {
                    productionYear: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                      "x-component-props": {
                        type: "year",
                      },
                      // "x-reactions": (field: Field) => {
                      //   setTimeout(() => {
                      //     field.setValue(`${field.value}`);
                      //   }, 0);
                      // },
                    },
                  },
                },
                column4: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "计量单位",
                    width: 200,
                  },
                  properties: {
                    unit: {
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
                    width: 200,
                  },
                  properties: {
                    annualOutput: {
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
                    title: "添加时间",
                    width: 300,
                  },

                  properties: {
                    createdAt: {
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

export default observer(
  defineComponent({
    setup() {
      const form = createForm();
      const { data, run } = useRequest(() =>
        API.getAdminMainProductsList({
          deptId: userStore().enterpriseId,
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
        apiAdd: API.postAdminMainProducts,
        apiEdit: API.putAdminMainProducts,
        apiDel: API.deleteAdminMainProductsIds,
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
        </FormProvider>
      );
    },
  })
);
