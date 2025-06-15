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
          header: "污染物痕迹",
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
                    title: "区域名称",
                  },
                  properties: {
                    areaName: {
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
                    title: "地点",
                  },
                  properties: {
                    location: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input.TextArea",
                    },
                  },
                },
                column4: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    width: 200,
                    title: "坐标位置（经度，纬度）",
                  },
                  properties: {
                    coordinates: {
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
                    title: "面积(m²)",
                  },
                  properties: {
                    area: {
                      type: "string",
                      required: true,
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
                    title: "固体废物名称",
                  },
                  properties: {
                    solidWasteName: {
                      type: "string",
                      required: true,
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                    },
                  },
                },
                column8: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    width: 200,
                    title: "是否为危险废物",
                  },
                  properties: {
                    isHazardousWaste: {
                      type: "string",
                      required: true,
                      enum: [
                        {
                          label: "是",
                          value: "0",
                        },
                        {
                          label: "否",
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
                    width: 200,
                    title: "贮存量或处置量（吨）",
                  },
                  properties: {
                    storageAndDisposalAmount: {
                      type: "string",
                      "x-decorator": "FormItem",
                      "x-component": "Input",
                    },
                  },
                },
                column11: {
                  type: "void",
                  "x-component": "ArrayTable.Column",
                  "x-component-props": {
                    width: 200,
                    title: "贮存或处置照片",
                  },
                  properties: {
                    storageAndDisposalPhotos: {
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
        API.getAdminSewageSolidWasteList({
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
        apiAdd: API.postAdminSewageSolidWaste,
        apiEdit: API.putAdminSewageSolidWaste,
        apiDel: API.deleteAdminSewageSolidWasteIds,
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
        </FormProvider>
      );
    },
  })
);
