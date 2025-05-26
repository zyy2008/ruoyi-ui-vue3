import { createSchemaField } from "@formily/vue";
import {
  FormItem,
  Input,
  FormGrid,
  FormLayout,
  DatePicker,
  InputNumber,
} from "@formily/element-plus";

const schema = createSchemaField({
  components: {
    FormItem,
    Input,
    FormGrid,
    FormLayout,
    DatePicker,
    InputNumber,
  },
});

export default schema;
