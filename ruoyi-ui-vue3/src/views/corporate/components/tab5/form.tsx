import { defineComponent, ExtractPropTypes, PropType, ref } from "vue";
import Schema from "@/components/Form";
import { ISchemaFieldProps, FormProvider, ISchema } from "@formily/vue";
import { Field, createForm, onFormInit, Form } from "@formily/core";
import { FormButtonGroup, Submit, Reset } from "@formily/element-plus";
import userStore from "@/store/modules/user";
import API from "@/services";
import { ElCard, ElButton, ElSpace, ElMessage } from "element-plus";
import { CloseBold, EditPen } from "@element-plus/icons-vue";
import { observer } from "@formily/reactive-vue";
import { observable, autorun } from "@formily/reactive";
import { useRequest } from "vue-request";
import { SoilMonitoring, GroundwaterMonitoring } from "./components";

const { SchemaField } = Schema;

export const handeSchema: (T?: ISchema["properties"]) => ISchema = (val) => {
  return {
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
              title: "一、土壤监测数据",
            },
            properties: {
              card: {
                type: "void",
                "x-component": <SoilMonitoring searchValue={"0"} />,
              },
            },
          },
          tab2: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "二、地下水监测数据",
            },
            properties: {
              card: {
                type: "void",
                "x-component": <GroundwaterMonitoring />,
              },
            },
          },
          ...(val as any),
        },
      },
    },
  };
};

const props: ISchemaFieldProps = {
  schema: handeSchema(),
};

// const actionProps = () => ({
//   disabled: Boolean,
//   form: { type: Object as PropType<Form<any>>, default: undefined },
// });

// type ActionProps = Partial<ExtractPropTypes<ReturnType<typeof actionProps>>>;

export default defineComponent({
  setup() {
    const disabled = ref<boolean>();
    const { enterpriseId: deptId } = userStore();
    const { runAsync, loading } = useRequest(
      () =>
        API.getAdminEnterpriseList({
          deptId,
        }),
      {
        manual: true,
      }
    );
    const form = createForm();
    // autorun(() => {
    //   disabled.value = form.disabled;
    // });

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
