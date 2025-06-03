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
import { SoilPathway, GroundwaterPathway } from "./components";
import { useDeptId } from "@/hooks";

const { SchemaField } = Schema;

const props: ISchemaFieldProps = {
  schema: {
    type: "object",
    properties: {
      collapse: {
        type: "void",
        "x-component": "FormCollapse",
        properties: {
          tab1: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "一、土壤途径",
            },
            properties: {
              card: {
                type: "void",
                "x-component": <SoilPathway />,
              },
            },
          },
          tab2: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "二、地下水途径",
            },
            properties: {
              card: {
                type: "void",
                "x-component": <GroundwaterPathway />,
              },
            },
          },
        },
      },
    },
  },
};
const form = createForm();
export default defineComponent({
  setup() {
    const { deptId } = useDeptId();

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
