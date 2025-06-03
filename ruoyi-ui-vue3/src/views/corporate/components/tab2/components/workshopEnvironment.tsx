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
          header: "五、生产车间分布与环境情况",
        },
        properties: {
          array: {
            type: "array",
            "x-decorator": "FormItem",
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
                    title: "车间名称",
                    width: 200,
                  },
                  properties: {
                    workshopName: {
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
                    title: "坐标位置",
                    width: 200,
                  },
                  properties: {
                    coordinate: {
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
                    title: "生产起始日期",
                    width: 200,
                  },
                  properties: {
                    startDate: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "DatePicker",
                      "x-component-props": {
                        type: "date",
                      },
                    },
                  },
                },
                column5: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "生产工艺描述",
                    width: 200,
                  },
                  properties: {
                    processDescription: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input.TextArea",
                    },
                  },
                },
                column9: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "滴漏现象",
                    width: 200,
                  },
                  properties: {
                    leakage: {
                      type: "string",
                      enum: [
                        {
                          label: "有",
                          value: "0",
                        },
                        {
                          label: "无",
                          value: "1",
                        },
                      ],
                      "x-decorator": "FormItem",
                      "x-component": "Select",
                    },
                  },
                },
                column10: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "漏点照片",
                    width: 200,
                  },
                  properties: {
                    leakImagePath: {
                      type: "string",
                      "x-component": "UploadAjax",
                      "x-component-props": {
                        listType: "picture-card",
                        accept: ".jpg,.png",
                      },
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
          b1: {
            type: "string",
            title: "生产车问总面积",
            "x-decorator": "FormItem",
            "x-component": "Input",
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
        API.getAdminWorkshopEnvironmentList({
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
        apiAdd: API.postAdminWorkshopEnvironment,
        apiEdit: API.putAdminWorkshopEnvironment,
        apiDel: API.deleteAdminWorkshopEnvironmentIds,
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
        </FormProvider>
      );
    },
  })
);
