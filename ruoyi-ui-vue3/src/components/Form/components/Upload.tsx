import {
  defineComponent,
  inject,
  toRef,
  computed,
  Ref,
  ref,
  onMounted,
  watch,
  watchEffect,
} from "vue";
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
import API from "@/services";
import request from "@/utils/http";

const Index = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value = toRef(props, "value");
      const dialogVisible = ref(false);
      const field: Ref<Field> = useField();
      const { loading, runAsync } = useRequest(
        (formData: any) =>
          API.postFileUpload(formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }),
        {
          manual: true,
        }
      );
      const { data } = useRequest(
        () => {
          if (value.value) {
            return request(`/dev-api/${value.value}`, {
              method: "GET",
              responseType: "blob",
            }).then((res: Blob) => {
              const url = URL.createObjectURL(res);
              return url;
            });
          }
        },
        {
          refreshDeps: [value],
        }
      );
      return () => {
        return (
          <>
            <ElUpload
              {...field.value.componentProps}
              v-loading={loading.value}
              element-loading-text="上传中..."
              fileList={[]}
              httpRequest={async (opt) => {
                const { file } = opt;
                const formData = new FormData();
                formData.append("file", file);
                runAsync(formData).then((res) => {
                  if (res.code === 200) {
                    props.onChange(res.data);
                    ElMessage.success("上传成功");
                  } else {
                    ElMessage.success("上传失败");
                  }
                });
              }}
            >
              {value.value ? (
                <>
                  <img
                    class="el-upload-list__item-thumbnail"
                    src={data.value}
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
                src={data.value}
              />
            </ElDialog>
          </>
        );
      };
    },
  })
);

export default Index;
