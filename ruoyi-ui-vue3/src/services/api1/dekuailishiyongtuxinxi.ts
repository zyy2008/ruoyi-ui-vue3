// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改地块历史用途信息 PUT /admin/landUseHistory */
export async function putAdminLandUseHistory(
  body: API.dekuailishiyongtuxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/landUseHistory`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增地块历史用途信息 POST /admin/landUseHistory */
export async function postAdminLandUseHistory(
  body: API.dekuailishiyongtuxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/landUseHistory`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除地块历史用途信息 DELETE /admin/landUseHistory/${param0} */
export async function deleteAdminLandUseHistoryIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminLandUseHistoryIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/landUseHistory/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取地块历史用途信息详细信息 GET /admin/landUseHistory/${param0} */
export async function getAdminLandUseHistoryId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminLandUseHistoryIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/landUseHistory/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出地块历史用途信息列表 POST /admin/landUseHistory/export */
export async function postAdminLandUseHistoryExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminLandUseHistoryExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/landUseHistory/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询地块历史用途信息列表 GET /admin/landUseHistory/list */
export async function getAdminLandUseHistoryList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminLandUseHistoryListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/landUseHistory/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
