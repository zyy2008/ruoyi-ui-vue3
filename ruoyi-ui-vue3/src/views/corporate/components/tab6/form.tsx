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
import { useDeptId } from "@/hooks";
import { SurveyAssessment } from "./components";

const { SchemaField } = Schema;

const props: ISchemaFieldProps = {
  schema: handeSchema(
    {
      tab3: {
        type: "void",
        "x-component": "FormCollapse.Item",
        "x-component-props": {
          title: "三、调查评估",
        },
        properties: {
          card: {
            type: "void",
            "x-component": <SurveyAssessment />,
          },
        },
      },
    },
    "环境监测和调查评估信息"
  ),
};

const form = createForm();

export default defineComponent({
  setup() {
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
