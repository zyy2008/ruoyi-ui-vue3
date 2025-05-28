import { FormDialog } from "@formily/element-plus";
import { Component, VNode, Ref } from "vue";
import type { AxiosPromise } from "axios";
import { ProTableInstance } from "@/components/ProTable/interface";
import { ElMessage } from "element-plus";

type IProps = {
  apiAdd?: (K?: any, options?: { [key: string]: any }) => Promise<any>;
  content: Component | ((props: any) => VNode);
  ref: Ref<ProTableInstance>;
};

type OpenProps = {
  initialValues?: Partial<any>;
  api: (K?: any) => Promise<any>;
  title?: string;
};

const useCrud = ({ content, apiAdd, ref }: IProps) => {
  const open = (props?: OpenProps) => {
    const { initialValues, api, title } = props;
    FormDialog(
      {
        title,
        closeOnClickModal: false,
        width: "1200px",
      },
      content
    )
      .forOpen((_, next) => {
        next({
          initialValues,
        });
      })
      .forConfirm((payload: any, next) => {
        api?.(payload.values)
          .then((res) => {
            if (res.code === 200) {
              ElMessage.success(`${title}成功`);
              next(payload);
              ref.value?.search();
            } else {
              ElMessage.warning(`${title}失败`);
            }
          })
          .finally(() => {
            payload.setSubmitting(false);
          });
      })
      .open();
  };

  return {
    add: () => {
      open({
        title: "新增",
        api: apiAdd,
      });
    },
    edit: () => {},
    delete: () => {},
  };
};

export default useCrud;
