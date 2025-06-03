import { defineComponent, inject, toRef, computed, onMounted } from "vue";
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
      onMounted(() => {
        if (!value.value) {
          props?.onChange(useStore().name);
        }
      });
      return () => {
        return <div>{value.value}</div>;
      };
    },
  })
);

export default Index;
