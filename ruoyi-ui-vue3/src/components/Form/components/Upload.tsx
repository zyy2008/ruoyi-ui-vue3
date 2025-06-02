import { defineComponent, inject, toRef, computed, Ref, ref } from "vue";
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
  ElUpload,
  ElIcon,
  ElDialog,
} from "element-plus";
import { ArrayTable } from "@formily/element-plus";
import { useRequest } from "vue-request";
import useStore from "@/store/modules/user";
import { Plus, Delete } from "@element-plus/icons-vue";

const Index = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const dialogVisible = ref(false);
      const field: Ref<Field> = useField();
      return () => {
        return (
          <>
            <ElUpload {...field.value.componentProps}>
              {value.value ? (
                <>
                  <img
                    class="el-upload-list__item-thumbnail"
                    src={value.value}
                    alt=""
                  />
                  <span
                    class="el-upload-list__item-actions"
                    onClick={(e) => {
                      e.stopPropagation();
                    }}
                  >
                    <span
                      class="el-upload-list__item-preview"
                      onClick={(e) => {
                        e.stopPropagation();
                        dialogVisible.value = true;
                      }}
                    >
                      <el-icon>
                        <zoom-in />
                      </el-icon>
                    </span>
                    <span
                      class="el-upload-list__item-delete"
                      onClick={(e) => {
                        e.stopPropagation();
                        props.onChange(void 0);
                      }}
                    >
                      <el-icon>
                        <Delete />
                      </el-icon>
                    </span>
                  </span>
                </>
              ) : (
                <ElIcon>
                  <Plus />
                </ElIcon>
              )}
            </ElUpload>
            <ElDialog
              modelValue={dialogVisible.value}
              onClose={() => {
                dialogVisible.value = false;
              }}
            >
              <img
                style={{
                  width: "100%",
                }}
                src={value.value}
              />
            </ElDialog>
          </>
        );
      };
    },
  })
);

export default Index;
