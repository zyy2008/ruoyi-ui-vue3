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
import { asyncComputed } from "@vueuse/core";
import request from "@/utils/http";

const Index = observer(
  defineComponent({
    props: ["value", "onChange"],
    setup(props) {
      const value: Ref<string> = toRef(props, "value");
      const dialogImageUrl = ref("");
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
      const fileList = asyncComputed(async () => {
        const arr = value.value.split(",");
        const list = await Promise.all(
          arr.map(async (item) => {
            const [name, url] = item.split("&");
            if (name && url) {
              const res = await request(`/dev-api/${url}`, {
                method: "GET",
                responseType: "blob",
              }).then((res: Blob) => {
                const url = URL.createObjectURL(res);
                return url;
              });
              return {
                name,
                url: res,
                surl: url,
              };
            }
          })
        );
        return list?.filter(Boolean);
      });
      return () => {
        return (
          <>
            <ElUpload
              {...field.value.componentProps}
              v-loading={loading.value}
              element-loading-text="上传中..."
              fileList={fileList.value}
              onPreview={(uploadFile) => {
                dialogImageUrl.value = uploadFile.url!;
                dialogVisible.value = true;
              }}
              onRemove={(uploadFile: any) => {
                const surl = uploadFile.surl;
                const arr = value.value?.split(",");
                const filter = arr.filter((item) => {
                  const [name, url] = item.split("&");
                  return url !== surl;
                });
                props.onChange(filter.length === 0 ? "" : filter.join(","));
              }}
              httpRequest={async (opt) => {
                const { file } = opt;
                const formData = new FormData();
                formData.append("file", file);
                runAsync(formData).then((res) => {
                  if (res.code === 200) {
                    const arr = value.value?.split(",");
                    props.onChange(
                      value.value
                        ? [...(arr ?? []), `${file.name}&${res.data}`]?.join(
                            ","
                          )
                        : [`${file.name}&${res.data}`]?.join(",")
                    );
                    ElMessage.success("上传成功");
                  } else {
                    ElMessage.success("上传失败");
                  }
                });
              }}
            >
              <ElIcon>
                <Plus />
              </ElIcon>
            </ElUpload>
            <ElDialog
              appendToBody
              modelValue={dialogVisible.value}
              onClose={() => {
                dialogVisible.value = false;
              }}
            >
              <img
                w-full
                style={{
                  width: "100%",
                }}
                src={dialogImageUrl.value}
              />
            </ElDialog>
          </>
        );
      };
    },
  })
);

export default Index;
