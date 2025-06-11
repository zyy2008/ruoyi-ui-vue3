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
import { FormButtonGroup } from "@formily/element-plus";
import { ElButton, ElMessage } from "element-plus";
import {
  Radios,
  SurveySoilPollution,
  SurveyGroundwaterPollution,
} from "./index";

const props: ISchemaFieldProps = {
  schema: {
    type: "void",
    properties: {
      card: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header:
            "调查评估（若选择正在开展或已经完成，则需填写以下第2-5项内容，否则不填）",
        },
        properties: {
          assessment: {
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
          assessmentTime: {
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
            "调查结果显示是否有土壤污染 （若选择是，则需填写（一）土壤污染区内容，否则不填）",
        },
        properties: {
          hasSoilPollution: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": <Radios.Input />,
          },
        },
      },
      card4: {
        type: "void",
        "x-component": "Card",
        "x-component-props": {
          header:
            "调查结果显示是否有地下水污染 （若选择是，则需填写（二）地下水污染区内容，否则不填）",
        },
        properties: {
          hasGroundwaterPollution: {
            type: "string",
            "x-decorator": "FormItem",
            "x-component": <Radios.Input />,
          },
        },
      },
      card6: {
        type: "void",
        "x-component": <SurveyGroundwaterPollution />,
      },
      card3: {
        type: "void",
        "x-component": <SurveySoilPollution />,
      },
    },
  },
};

export default observer(
  defineComponent({
    setup() {
      const form = createForm();
      const { deptId } = useDeptId();
      const { data, run, loading } = useRequest(() =>
        API.getAdminSurveyAssessmentList({
          deptId,
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
                    ? API.putAdminSurveyAssessment
                    : API.postAdminSurveyAssessment;
                  api({
                    ...val,
                    deptId,
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
