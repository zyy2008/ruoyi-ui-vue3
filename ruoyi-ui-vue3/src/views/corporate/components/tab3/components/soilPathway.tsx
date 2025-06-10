import { defineComponent, provide, watchEffect } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps } from "@formily/vue";
import { useRequest } from "vue-request";
import API from "@/services";
import userStore from "@/store/modules/user";
import { Field } from "@formily/core";
import { useDeptId } from "@/hooks";

const props: ISchemaFieldProps = {
  schema: {
    type: "void",
    properties: {
      card: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "含水层以上士壤分层情况(自上而下)",
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
                    title: "土层性质",
                    width: 200,
                  },
                  properties: {
                    soilType: {
                      type: "string",
                      enum: [
                        {
                          label: "碎石土",
                          value: "0",
                        },
                        {
                          label: "砂土",
                          value: "1",
                        },
                        {
                          label: "粉土",
                          value: "2",
                        },
                        {
                          label: "黏性土",
                          value: "3",
                        },
                        {
                          label: "不确定",
                          value: "4",
                        },
                      ],
                      "x-decorator": "FormItem",
                      "x-component": "Select",
                    },
                  },
                },
                column3: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "土层(上层)埋深(cm)",
                    width: 200,
                  },
                  properties: {
                    depthTopCm: {
                      type: "number",
                      "x-decorator": "FormItem",
                      "x-component": "InputNumber",
                    },
                  },
                },
                column4: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "土层厚度",
                    width: 200,
                  },
                  properties: {
                    thicknessCm: {
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
                      "x-component": "AddPeople",
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

export default observer(
  defineComponent({
    setup() {
      const form = createForm();
      const { deptId } = useDeptId();
      const { data, run } = useRequest(() =>
        API.getAdminSoilPathwayList({
          deptId,
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
        apiAdd: API.postAdminSoilPathway,
        apiEdit: API.putAdminSoilPathway,
        apiDel: API.deleteAdminSoilPathwayIds,
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
        </FormProvider>
      );
    },
  })
);
