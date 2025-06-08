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
          header: "储存区",
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
                    width: 200,
                    title: "区域类型",
                  },
                  properties: {
                    areaType: {
                      type: "string",
                      required: true,
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
                    title: "储罐数量",
                  },
                  properties: {
                    tankCount: {
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
                    title: "储罐类型",
                  },
                  properties: {
                    tankType: {
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
                    title: "储存物质名称",
                  },
                  properties: {
                    storedSubstanceName: {
                      type: "string",
                      required: true,
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                    },
                  },
                },
                column9: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    width: 200,
                    title: "是否为危险化学品",
                  },
                  properties: {
                    isHazardousChemical: {
                      type: "string",
                      required: true,
                      enum: [
                        { label: "是", value: "0" },
                        { label: "否", value: "1" },
                      ],
                      "x-decorator": "FormItem",
                      "x-component": "Select",
                    },
                  },
                },
                column6: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    width: 200,
                    title: "储存量",
                  },
                  properties: {
                    storageAmount: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                    },
                  },
                },
                column8: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "计量单位",
                    width: 200,
                  },

                  properties: {
                    unit: {
                      type: "string",
                      required: true,
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                    },
                  },
                },
                column10: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "储存年代",
                    width: 200,
                  },

                  properties: {
                    storageYear: {
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
                column11: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "泄漏或污染痕迹",
                    width: 200,
                  },

                  properties: {
                    leakageOrPollutionTraces: {
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
                column12: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    title: "照片",
                    width: 200,
                  },
                  properties: {
                    photos: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "UploadAjax",
                      "x-component-props": {
                        listType: "picture-card",
                        accept: ".jpg,.png",
                      },
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
        API.getAdminStorageAreaList({
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
        apiAdd: API.postAdminStorageArea,
        apiEdit: API.putAdminStorageArea,
        apiDel: API.deleteAdminStorageAreaIds,
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
        </FormProvider>
      );
    },
  })
);
