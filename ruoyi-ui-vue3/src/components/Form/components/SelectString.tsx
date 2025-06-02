import { defineComponent, inject, toRef, computed } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps, useField } from "@formily/vue";
import {
  ElPopconfirm,
  ElButton,
  ElMessage,
  ElSelect,
  ElOption,
} from "element-plus";
import { ArrayTable } from "@formily/element-plus";
import { useRequest } from "vue-request";
import useStore from "@/store/modules/user";

const Index = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const field = useField();
      const modelValue = computed(() => {
        return value.value?.split(",");
      });
      return () => {
        return (
          <ElSelect
            modelValue={modelValue.value}
            multiple
            onChange={(val) => {
              props.onChange(val?.join(","));
            }}
          >
            {(field.value as any)?.dataSource?.map((item) => {
              return <ElSelect.Option {...item} key={item.value} />;
            })}
          </ElSelect>
        );
      };
    },
  })
);

export default Index;
