import { defineComponent, inject } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps } from "@formily/vue";
import { ElPopconfirm, ElButton, ElMessage } from "element-plus";
import { ArrayTable } from "@formily/element-plus";
import { useRequest } from "vue-request";
import useStore from "@/store/modules/user";
import { useDeptId } from "@/hooks";

type InjectProps = {
  apiDel: (T?: any) => Promise<any>;
  apiAdd: (T?: any) => Promise<any>;
  apiEdit: (T?: any) => Promise<any>;
  run: () => void;
};

const Remove = observer(
  defineComponent({
    setup() {
      const { run, apiDel } = inject<InjectProps>("form");
      const record = ArrayTable.useRecord();
      const base = ArrayTable.useArray();
      const index = ArrayTable.useIndex();
      const { runAsync, loading } = useRequest(apiDel, { manual: true });
      return () => (
        <ElPopconfirm
          title="确定删除当前条目吗？"
          onConfirm={() => {
            if (record.value?.id) {
              runAsync({
                ids: record.value?.id,
              }).then((res) => {
                if (res.code === 200) {
                  ElMessage.success("删除成功");
                  run();
                } else {
                  ElMessage.warning("删除失败");
                }
              });
            } else {
              base.field.value.remove(index.value);
            }
          }}
        >
          {{
            reference: () => (
              <ElButton type="danger" loading={loading.value}>
                删除
              </ElButton>
            ),
          }}
        </ElPopconfirm>
      );
    },
  })
);

const Save = observer(
  defineComponent({
    setup() {
      const { apiEdit, apiAdd, run: getList } = inject<InjectProps>("form");
      const record = ArrayTable.useRecord();
      const { deptId } = useDeptId();
      const { runAsync, loading } = useRequest(
        (id) => {
          if (id != null && id !== undefined && id !== "") {
            const { createdAt, ...others } = record.value;
            return apiEdit({
              ...others,
              deptId,
            });
          }
          return apiAdd({
            ...record.value,
            deptId,
          });
        },
        { manual: true }
      );

      return () => (
        <ElPopconfirm
          title="确定保存当前条目吗？"
          onConfirm={() => {
            runAsync(record.value?.id).then((res) => {
              if (res.code === 200) {
                ElMessage.success("保存成功");
                getList();
              } else {
                ElMessage.warning("保存失败");
              }
            });
          }}
        >
          {{
            reference: () => (
              <ElButton type="primary" loading={loading.value}>
                保存
              </ElButton>
            ),
          }}
        </ElPopconfirm>
      );
    },
  })
);

export default {
  Remove,
  Save,
};
