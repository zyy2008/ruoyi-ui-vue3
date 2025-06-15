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
import { ElButton, ElMessage, ElCheckbox, ElCheckboxGroup } from "element-plus";
import { key } from "@mars/widgets/common/store/widget";

export default observer(
  defineComponent({
    props: ["value", "onChange", "datas"],
    setup(props) {
      const value = toRef(props, "value");
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
          <FormGrid>
            {(props?.datas ?? []).map((item, colIndex) => {
              return (
                <FormGrid.GridColumn
                  key={colIndex}
                  style={{
                    display: "flex",
                    flexDirection: "column",
                  }}
                >
                  {item.map((item, rowindex) => (
                    <ElCheckbox
                      key={rowindex}
                      label={item}
                      value={`${colIndex}-${rowindex}`}
                    />
                  ))}
                </FormGrid.GridColumn>
              );
            })}
          </FormGrid>
        </ElCheckboxGroup>
      );
    },
  })
);
