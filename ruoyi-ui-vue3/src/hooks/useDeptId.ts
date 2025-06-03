import { inject, computed, unref } from "vue";
import userStore from "@/store/modules/user";

type InjectProps = {
  deptId: string;
};
const useDeptId = () => {
  const props = inject<InjectProps>("deptId");
  const { enterpriseId } = userStore();
  const deptId = computed<string>(() => {
    if (props?.deptId) {
      return props?.deptId;
    }
    return enterpriseId;
  });

  return {
    deptId: unref(deptId),
  };
};

export { useDeptId };
