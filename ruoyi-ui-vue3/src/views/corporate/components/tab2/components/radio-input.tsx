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
  ElInput,
  ElMessage,
  ElRadio,
  ElRadioGroup,
} from "element-plus";
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
const Input = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const radioValue = ref();
      const inputValue = ref();
      watchEffect(() => {
        if (value.value) {
          const [radio, input] = value.value.split(",");
          radioValue.value = radio;
          inputValue.value = input;
        }
      });
      return () => (
        <ElRadioGroup
          v-model={radioValue.value}
          onChange={(val) => {
            if (val === "0" && inputValue.value) {
              props.onChange(`${val},${inputValue.value}`);
            }
            if (val === "0" && !inputValue.value) {
              props.onChange("");
            }
            if (val === "1") {
              props.onChange(val);
            }
          }}
        >
          <ElRadio value="0">
            <div>
              是 (发生过
              <ElInput
                v-model={inputValue.value}
                onChange={(val) => {
                  props.onChange(`0,${val}`);
                }}
                placeholder="请输入泄漏次数"
                style={{
                  width: "150px",
                  margin: "0 5px",
                }}
              />
              次)
            </div>
          </ElRadio>
          <ElRadio value="1">否</ElRadio>
        </ElRadioGroup>
      );
    },
  })
);

const Input1 = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const radioValue = ref();
      const inputValue = ref();
      watchEffect(() => {
        if (value.value) {
          const [radio, input] = value.value.split(",");
          radioValue.value = radio;
          inputValue.value = input;
        }
      });
      return () => (
        <ElRadioGroup
          v-model={radioValue.value}
          onChange={(val) => {
            if (val === "0" && inputValue.value) {
              props.onChange(`${val},${inputValue.value}`);
            }
            if (val === "0" && !inputValue.value) {
              props.onChange("");
            }
            if (val === "1" || val === "2") {
              props.onChange(val);
            }
          }}
        >
          <ElRadio value="0">
            <div>
              曾发生过 (发生过
              <ElInput
                v-model={inputValue.value}
                onChange={(val) => {
                  props.onChange(`0,${val}`);
                }}
                placeholder="请输入泄漏次数"
                style={{
                  width: "150px",
                  margin: "0 5px",
                }}
              />
              次)
            </div>
          </ElRadio>
          <ElRadio value="1">未发生过</ElRadio>
          <ElRadio value="2">不确定</ElRadio>
        </ElRadioGroup>
      );
    },
  })
);

const Radio = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const radioValue = ref();
      const inputValue = ref();
      watchEffect(() => {
        if (value.value) {
          const [radio, input] = value.value.split(",");
          radioValue.value = radio;
          inputValue.value = input;
        }
      });
      return () => (
        <ElRadioGroup
          v-model={radioValue.value}
          onChange={(val) => {
            if (val === "0" && inputValue.value) {
              props.onChange(`${val},${inputValue.value}`);
            }
            if (val === "0" && !inputValue.value) {
              props.onChange("");
            }
            if (val === "1" || val === "2") {
              props.onChange(val);
            }
          }}
        >
          <ElRadio value="0">
            <div>
              有（
              <ElRadioGroup
                style={{
                  verticalAlign: "bottom",
                }}
                v-model={inputValue.value}
                onChange={(val) => {
                  props.onChange(`0,${val}`);
                }}
              >
                <ElRadio value="0">地块内</ElRadio>
                <ElRadio value="1">地块外</ElRadio>
              </ElRadioGroup>
              ）
            </div>
          </ElRadio>
          <ElRadio value="1">无</ElRadio>
        </ElRadioGroup>
      );
    },
  })
);

const Radios = {
  Input,
  Input1,
  Radio,
};

export default Radios;
