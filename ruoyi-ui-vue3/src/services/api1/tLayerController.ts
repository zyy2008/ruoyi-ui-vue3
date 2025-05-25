// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 查询图层列表 GET /admin/layer/list */
export async function getAdminLayerList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminLayerListParams,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/layer/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
