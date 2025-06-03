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

const datas = [
  [
    "地面无任何处理",
    "铺设防渗材料",
    "有表面发盖",
    "四周有国堰或围墙",
    "有雨水收集池或导排管道",
  ],
  [
    "地面硬化且完整",
    "防渗材料完整无破损",
    "表面覆盖完整",
    "国娱或围墙完整",
    "雨水收集池或导排管道有漆漏",
  ],
  [
    "硬化地面有裂経",
    "防渗材料有破损",
    "表面镋盖有破损",
    "围堰或国墙不完整，可随意进入",
  ],
];
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
                  {item.map((item, rowindex) => (
                    <ElCheckbox
                      key={rowindex}
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
