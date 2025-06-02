// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改园区监测井信息 PUT /admin/wells */
export async function putAdminWells(
  body: API.yuanqujiancejingxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/wells`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增园区监测井信息 POST /admin/wells */
export async function postAdminWells(
  body: API.yuanqujiancejingxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/wells`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除园区监测井信息 DELETE /admin/wells/${param0} */
export async function deleteAdminWellsIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminWellsIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/wells/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取园区监测井信息详细信息 GET /admin/wells/${param0} */
export async function getAdminWellsId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminWellsIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/wells/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出园区监测井信息列表 POST /admin/wells/export */
export async function postAdminWellsExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminWellsExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/wells/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询园区监测井信息列表 GET /admin/wells/list */
export async function getAdminWellsList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminWellsListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/wells/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
