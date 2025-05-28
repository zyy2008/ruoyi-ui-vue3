// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改企业污染源 PUT /admin/pollution */
export async function putAdminPollution(
  body: API.qiyewuranyuan,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/pollution`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增企业污染源 POST /admin/pollution */
export async function postAdminPollution(
  body: API.qiyewuranyuan,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/pollution`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除企业污染源 DELETE /admin/pollution/${param0} */
export async function deleteAdminPollutionIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminPollutionIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/pollution/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取企业污染源详细信息 GET /admin/pollution/${param0} */
export async function getAdminPollutionId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPollutionIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/pollution/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出企业污染源列表 POST /admin/pollution/export */
export async function postAdminPollutionExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminPollutionExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/pollution/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询企业污染源列表 GET /admin/pollution/list */
export async function getAdminPollutionList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPollutionListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/pollution/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
