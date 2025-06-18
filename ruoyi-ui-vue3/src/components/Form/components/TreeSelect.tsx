import { defineComponent, inject, toRef, computed, Ref } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm, Field } from "@formily/core";
import { FormProvider, ISchemaFieldProps, useField } from "@formily/vue";
import { ElTreeSelect } from "element-plus";
import { ArrayTable } from "@formily/element-plus";
import { useRequest } from "vue-request";
import useStore from "@/store/modules/user";

const Index = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const field: Ref<Field> = useField();
      return () => {
        return (
          <ElTreeSelect
            {...field.value.componentProps}
            data={field.value.dataSource}
            modelValue={value.value}
            onChange={props.onChange}
          />
        );
      };
    },
  })
);

export default Index;
