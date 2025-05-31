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
  Checkbox,
  Upload,
  FormCollapse,
  ArrayTable,
} from "@formily/element-plus";
import { ElCard } from "element-plus";
import { PopconfirmDel } from "./components";

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
    Checkbox,
    Upload,
    Card: ElCard,
    FormCollapse,
    ArrayTable,
    PopconfirmDel,
  },
});

export default schema;
