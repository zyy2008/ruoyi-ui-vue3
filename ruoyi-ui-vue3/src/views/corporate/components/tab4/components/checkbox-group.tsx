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

const cols = [
  "无敏感目标",
  "幼儿园",
  "学校",
  "居民曲",
  "医院",
  "集中式饮用水水源地",
  "饮用水井",
  "食用农产品产地",
];
const rows = ["", "<100m", "100m-300m", "300m-1000m"];

const datas = rows.map((val, rowIndex) => {
  return cols.map((item, colIndex) => {
    return {
      col: val,
      row: item,
      value: `${colIndex}- ${rowIndex}`,
    };
  });
});
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
        <ElCheckboxGroup
          modelValue={modelValue.value}
          onChange={(val) => {
            props.onChange(val.join(","));
          }}
        >
          <FormGrid>
            {datas.map((item, colIndex) => {
              return (
                <FormGrid.GridColumn
                  key={colIndex}
                  style={{
                    display: "flex",
                    flexDirection: "column",
                  }}
                >
                  {item.map((item, index) => (
                    <ElCheckbox
                      key={item.value}
                      label={colIndex === 0 ? item.row : item.col}
                      value={item.value}
                      style={{
                        visibility:
                          colIndex > 0 && index === 0 ? "hidden" : " visible",
                      }}
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
