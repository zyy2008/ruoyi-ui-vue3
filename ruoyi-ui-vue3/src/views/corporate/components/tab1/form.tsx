import { defineComponent } from "vue";
import Schema from "@/components/Form";
import { ISchemaFieldProps } from "@formily/vue";

const { SchemaField } = Schema;

const props: ISchemaFieldProps = {
  schema: {
    type: "object",
    properties: {
      layout: {
        type: "void",
        "x-decorator": "FormLayout",
        "x-component": "FormGrid",
        properties: {
          name: {},
        },
      },
    },
  },
};

export default defineComponent({
  setup() {
    return () => <SchemaField {...props} />;
  },
});
