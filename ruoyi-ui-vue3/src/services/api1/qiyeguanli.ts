// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改企业管理 PUT /admin/enterprises */
export async function putAdminEnterprises(
  body: API.qiyeguanli,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/enterprises`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增企业管理 POST /admin/enterprises */
export async function postAdminEnterprises(
  body: API.qiyeguanli,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/enterprises`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除企业管理 DELETE /admin/enterprises/${param0} */
export async function deleteAdminEnterprisesIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminEnterprisesIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/enterprises/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取企业管理详细信息 GET /admin/enterprises/${param0} */
export async function getAdminEnterprisesId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminEnterprisesIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/enterprises/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出企业管理列表 POST /admin/enterprises/export */
export async function postAdminEnterprisesExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminEnterprisesExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/enterprises/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

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
