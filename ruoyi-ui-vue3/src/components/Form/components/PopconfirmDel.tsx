import { defineComponent, inject } from "vue";
import { observer } from "@formily/reactive-vue";
import Schema from "@/components/Form";
import { createForm } from "@formily/core";
import { FormProvider, ISchemaFieldProps } from "@formily/vue";
import { ElPopconfirm, ElButton } from "element-plus";

export default observer(
  defineComponent({
    setup() {
      const res = inject("form");
      console.log(res);
      return () => (
        <ElPopconfirm title="确定删除当前条目吗？">
          {{
            reference: () => <ElButton>删除</ElButton>,
          }}
        </ElPopconfirm>
      );
    },
  })
);
