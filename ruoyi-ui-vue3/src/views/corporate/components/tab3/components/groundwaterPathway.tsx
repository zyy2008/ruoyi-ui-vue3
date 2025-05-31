import { defineComponent, provide, watchEffect } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps } from "@formily/vue";
import { useRequest } from "vue-request";
import API from "@/services";
import userStore from "@/store/modules/user";
import { Field } from "@formily/core";
import { FormButtonGroup, Submit, Reset } from "@formily/element-plus";
import { ElButton, ElMessage } from "element-plus";

const props: ISchemaFieldProps = {
  schema: {
    type: "void",
    properties: {
      card: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "地下水深埋",
        },
        properties: {
          waterDepthCategory: {
            type: "string",
            "x-decorator": "FormItem",
            enum: [
              {
                label: "<3m",
                value: "0",
              },
              {
                label: "3-10m",
                value: "1",
              },
              {
                label: ">10m",
                value: "2",
              },
              {
                label: "不确定",
                value: "3",
              },
            ],
            "x-component": "Radio.Group",
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
          permeabilityCategory: {
            type: "string",
            "x-decorator": "FormItem",
            enum: [
              {
                label: "硕沙土以上",
                value: "0",
              },
              {
                label: "粗沙土、中沙土及细沙土",
                value: "1",
              },
              {
                label: "粉沙土及以下",
                value: "2",
              },
              {
                label: "不确定",
                value: "3",
              },
            ],
            "x-component": "Radio.Group",
          },
        },
      },
      card2: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "地块所在区域是否属于喀斯特地貌",
        },
        properties: {
          isKarstArea: {
            type: "string",
            "x-decorator": "FormItem",
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
            "x-component": "Radio.Group",
          },
        },
      },
      card3: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "年降雨量",
        },
        properties: {
          annualRainfallCategory: {
            type: "string",
            "x-decorator": "FormItem",
            enum: [
              {
                label: ">1000mm",
                value: "0",
              },
              {
                label: "400mm-1000mm",
                value: "1",
              },
              {
                label: "<400mm",
                value: "2",
              },
              {
                label: "不确定",
                value: "3",
              },
            ],
            "x-component": "Radio.Group",
          },
        },
      },
      card4: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "地下水用途",
        },
        properties: {
          groundwaterUse: {
            type: "string",
            "x-decorator": "FormItem",
            enum: [
              {
                label: "饮用或生活用水",
                value: "0",
              },
              {
                label: "农业灌溉",
                value: "1",
              },
              {
                label: "畜牧养殖",
                value: "2",
              },
              {
                label: "工业生产",
                value: "3",
              },
              {
                label: "景观",
                value: "4",
              },
              {
                label: "其他",
                value: "5",
              },
            ],
            "x-component": "Radio.Group",
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
      const { data, run, loading } = useRequest(() =>
        API.getAdminGroundwaterPathwayList({
          deptId: userStore().deptId,
        })
      );
      watchEffect(() => {
        if (data.value?.code === 200 && data.value?.rows.length > 0) {
          const [item] = data.value?.rows;
          form.reset().then(() => {
            form.setValues(item);
          });
        }
      });
      return () => (
        <FormProvider form={form}>
          <Schema.SchemaField {...props} />
          <FormButtonGroup
            align="center"
            style={{
              margin: "10px",
            }}
          >
            <ElButton
              type="primary"
              loading={loading.value}
              onClick={() => {
                form.submit().then((val: any) => {
                  const { id } = val;
                  const api = id
                    ? API.putAdminGroundwaterPathway
                    : API.postAdminGroundwaterPathway;
                  api({
                    ...val,
                    deptId: userStore().deptId,
                  }).then((res) => {
                    if (res.code === 200) {
                      ElMessage.success("保存成功");
                      run();
                    } else {
                      ElMessage.success("保存失败");
                    }
                  });
                });
              }}
            >
              保存
            </ElButton>
          </FormButtonGroup>
        </FormProvider>
      );
    },
  })
);
