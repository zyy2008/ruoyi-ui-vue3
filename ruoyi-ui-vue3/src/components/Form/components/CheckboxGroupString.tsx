import {
  defineComponent,
  provide,
  watchEffect,
  toRefs,
  toRef,
  computed,
  ref,
  Ref,
} from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps, useField } from "@formily/vue";
import { useRequest } from "vue-request";
import API from "@/services";
import userStore from "@/store/modules/user";
import { Field } from "@formily/core";
import { FormGrid } from "@formily/element-plus";
import { ElButton, ElMessage, ElCheckbox, ElCheckboxGroup } from "element-plus";
import { key } from "@mars/widgets/common/store/widget";

export default observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const field: Ref<Field> = useField();
      const modelValue = computed(() => {
        if (!value.value) {
          return [];
        }
        return value.value?.split(",");
      });
      return () => (
        <ElCheckboxGroup
          modelValue={modelValue.value}
          onChange={(val) => {
            props.onChange(val.join(","));
          }}
        >
          {field.value?.dataSource?.map((item, index) => (
            <ElCheckbox key={index} {...item} />
          ))}
        </ElCheckboxGroup>
      );
    },
  })
);
