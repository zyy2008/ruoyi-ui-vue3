// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改在产企业地块信息 PUT /admin/enterprise */
export async function putAdminEnterprise(
  body: API.zaichanqiyedekuaixinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/enterprise`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增在产企业地块信息 POST /admin/enterprise */
export async function postAdminEnterprise(
  body: API.zaichanqiyedekuaixinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/enterprise`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除在产企业地块信息 DELETE /admin/enterprise/${param0} */
export async function deleteAdminEnterpriseIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminEnterpriseIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/enterprise/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取在产企业地块信息详细信息 GET /admin/enterprise/${param0} */
export async function getAdminEnterpriseId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminEnterpriseIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/enterprise/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出在产企业地块信息列表 POST /admin/enterprise/export */
export async function postAdminEnterpriseExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminEnterpriseExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/enterprise/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询在产企业地块信息列表 GET /admin/enterprise/list */
export async function getAdminEnterpriseList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminEnterpriseListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/enterprise/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
