// @ts-ignore
/* eslint-disable */
import request from "@/utils/request";

/** 修改企业地块信息 PUT /admin/enterprise */
export async function putAdminEnterprise(
  body: API.zaichanqiyedekuaixinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`api1/admin/enterprise`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增企业地块信息 POST /admin/enterprise */
export async function postAdminEnterprise(
  body: API.zaichanqiyedekuaixinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`api1/admin/enterprise`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 根据 ID 查询企业地块信息 GET /admin/enterprise/${param0} */
export async function getAdminEnterpriseId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminEnterpriseIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<API.zaichanqiyedekuaixinxi>(
    `api1/admin/enterprise/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 根据 ID 删除企业地块信息 DELETE /admin/enterprise/${param0} */
export async function deleteAdminEnterpriseId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminEnterpriseIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`api1/admin/enterprise/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 批量删除企业地块信息 DELETE /admin/enterprise/batch */
export async function deleteAdminEnterpriseBatch(
  body: number[],
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`api1/admin/enterprise/batch`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 查询企业地块信息列表 GET /admin/enterprise/list */
export async function getAdminEnterpriseList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminEnterpriseListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`api1/admin/enterprise/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
