// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改地块及周边敏感受体信息 PUT /admin/receptor */
export async function putAdminReceptor(
  body: API.dekuaijizhoubianminganshoutixinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/receptor`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增地块及周边敏感受体信息 POST /admin/receptor */
export async function postAdminReceptor(
  body: API.dekuaijizhoubianminganshoutixinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/receptor`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除地块及周边敏感受体信息 DELETE /admin/receptor/${param0} */
export async function deleteAdminReceptorIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminReceptorIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/receptor/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取地块及周边敏感受体信息详细信息 GET /admin/receptor/${param0} */
export async function getAdminReceptorId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminReceptorIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/receptor/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出地块及周边敏感受体信息列表 POST /admin/receptor/export */
export async function postAdminReceptorExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminReceptorExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/receptor/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询地块及周边敏感受体信息列表 GET /admin/receptor/list */
export async function getAdminReceptorList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminReceptorListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/receptor/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
