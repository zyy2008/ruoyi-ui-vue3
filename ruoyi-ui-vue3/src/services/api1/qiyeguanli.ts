// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 查询企业管理列表 GET /admin/enterprises/list */
export async function getAdminEnterprisesList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminEnterprisesListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/enterprises/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
