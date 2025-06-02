import { defineComponent, inject, toRef, computed, Ref } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm, Field } from "@formily/core";
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
      const field: Ref<Field> = useField();
      const modelValue = computed(() => {
        if (value.value) {
          return value.value?.split(",");
        }
        return [];
      });
      console.log(field);
      return () => {
        return (
          <ElSelect
            {...field.value.componentProps}
            modelValue={value.value}
            onChange={props.onChange}
          >
            {field.value?.dataSource?.map((item) => {
              return (
                <ElSelect.OptionGroup {...item} key={item.label}>
                  {item?.options?.map((item) => (
                    <ElSelect.Option {...item} key={item.value} />
                  ))}
                </ElSelect.OptionGroup>
              );
            })}
          </ElSelect>
        );
      };
    },
  })
);

export default Index;
