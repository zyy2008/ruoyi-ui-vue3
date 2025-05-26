import { createSchemaField } from "@formily/vue";
import { FormItem, Input, FormGrid, FormLayout } from "@formily/element-plus";

const schema = createSchemaField({
  components: {
    FormItem,
    Input,
    FormGrid,
    FormLayout,
  },
});

export default schema;
