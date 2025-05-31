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
import SoilPollutant from "./soilPollutant";

const props: ISchemaFieldProps = {
  schema: {
    type: "void",
    properties: {
      card: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header:
            "土壤环境调查检测工作未展开过(若选择曾展开过，则须填写以下第2-7项内容，否则不填)",
        },
        properties: {
          hasMonitoring: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": "Radio.Group",
            enum: [
              {
                label: "未展开过",
                value: "0",
              },
              {
                label: "曾展开过",
                value: "1",
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
          surveyDate: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": "DatePicker",
            "x-component-props": {
              type: "date",
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
          pollutantsExceed: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": "Radio.Group",
            enum: [
              {
                label: "否",
                value: "0",
              },
              {
                label: "是",
                value: "1",
              },
            ],
          },
        },
      },
      card3: {
        type: "void",
        "x-component": <SoilPollutant />,
      },
      card4: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header: "数据来源",
        },
        properties: {
          source: {
            type: "string",
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
    props: ["searchValue"],
    setup(prop) {
      const form = createForm();
      const { data, run, loading } = useRequest(() =>
        API.getAdminSoilMonitoringList({
          deptId: userStore().deptId,
          // searchValue: prop.searchValue,
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
                    ? API.putAdminSoilMonitoring
                    : API.postAdminSoilMonitoring;
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
