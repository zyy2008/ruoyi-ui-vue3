import {
  defineComponent,
  provide,
  watchEffect,
  toRefs,
  toRef,
  computed,
  ref,
} from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps } from "@formily/vue";
import { useRequest } from "vue-request";
import API from "@/services";
import userStore from "@/store/modules/user";
import { Field } from "@formily/core";
import { FormGrid } from "@formily/element-plus";
import {
  ElButton,
  ElMessage,
  ElCheckbox,
  ElCheckboxGroup,
  ElSpace,
  ElFormItem,
  ElInput,
} from "element-plus";
import { key } from "@mars/widgets/common/store/widget";

export default observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const modelValue = computed(() => {
        if (!value.value) {
          return [];
        }
        return value.value?.split(",");
      });
      return () => (
        <ElSpace>
          <ElFormItem label="经度">
            <ElInput
              modelValue={modelValue?.[0]}
              onChange={(val) => {
                props.onChange([val, modelValue?.[1]].join(","));
              }}
            />
          </ElFormItem>
          <ElFormItem label="纬度">
            <ElInput
              modelValue={modelValue?.[1]}
              onChange={(val) => {
                props.onChange([modelValue?.[0], val].join(","));
              }}
            />
          </ElFormItem>
        </ElSpace>
      );
    },
  })
);
