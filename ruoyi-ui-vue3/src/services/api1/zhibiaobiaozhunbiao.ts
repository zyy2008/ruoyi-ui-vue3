// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改指标标准表 PUT /admin/indicators */
export async function putAdminIndicators(
  body: API.TIndicators,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/indicators`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增指标标准表 POST /admin/indicators */
export async function postAdminIndicators(
  body: API.TIndicators,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/indicators`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除指标标准表 DELETE /admin/indicators/${param0} */
export async function deleteAdminIndicatorsIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminIndicatorsIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/indicators/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取指标标准表详细信息 GET /admin/indicators/${param0} */
export async function getAdminIndicatorsId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminIndicatorsIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/indicators/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出指标标准表列表 POST /admin/indicators/export */
export async function postAdminIndicatorsExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminIndicatorsExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/indicators/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询指标标准表列表 GET /admin/indicators/list */
export async function getAdminIndicatorsList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminIndicatorsListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/indicators/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
