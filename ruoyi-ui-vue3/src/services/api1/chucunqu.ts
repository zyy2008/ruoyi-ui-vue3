// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改储存区 PUT /admin/storageArea */
export async function putAdminStorageArea(
  body: API.chucunqu,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/storageArea`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增储存区 POST /admin/storageArea */
export async function postAdminStorageArea(
  body: API.chucunqu,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/storageArea`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除储存区 DELETE /admin/storageArea/${param0} */
export async function deleteAdminStorageAreaIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminStorageAreaIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/storageArea/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取储存区详细信息 GET /admin/storageArea/${param0} */
export async function getAdminStorageAreaId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminStorageAreaIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/storageArea/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出储存区列表 POST /admin/storageArea/export */
export async function postAdminStorageAreaExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminStorageAreaExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/storageArea/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询储存区列表 GET /admin/storageArea/list */
export async function getAdminStorageAreaList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminStorageAreaListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/storageArea/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
