// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改生产车间分布与环境情况 PUT /admin/workshopEnvironment */
export async function putAdminWorkshopEnvironment(
  body: API.TWorkshopEnvironment,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/workshopEnvironment`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增生产车间分布与环境情况 POST /admin/workshopEnvironment */
export async function postAdminWorkshopEnvironment(
  body: API.TWorkshopEnvironment,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/workshopEnvironment`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除生产车间分布与环境情况 DELETE /admin/workshopEnvironment/${param0} */
export async function deleteAdminWorkshopEnvironmentIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminWorkshopEnvironmentIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/api1/admin/workshopEnvironment/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取生产车间分布与环境情况详细信息 GET /admin/workshopEnvironment/${param0} */
export async function getAdminWorkshopEnvironmentId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminWorkshopEnvironmentIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/api1/admin/workshopEnvironment/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出生产车间分布与环境情况列表 POST /admin/workshopEnvironment/export */
export async function postAdminWorkshopEnvironmentExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminWorkshopEnvironmentExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/workshopEnvironment/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询生产车间分布与环境情况列表 GET /admin/workshopEnvironment/list */
export async function getAdminWorkshopEnvironmentList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminWorkshopEnvironmentListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/workshopEnvironment/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
