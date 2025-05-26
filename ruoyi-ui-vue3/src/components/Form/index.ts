import { createSchemaField } from "@formily/vue";
import {
  FormItem,
  Input,
  FormGrid,
  FormLayout,
  DatePicker,
  InputNumber,
  Select,
  Radio,
} from "@formily/element-plus";

const schema = createSchemaField({
  components: {
    FormItem,
    Input,
    FormGrid,
    FormLayout,
    DatePicker,
    InputNumber,
    Select,
    Radio,
  },
});

export default schema;
