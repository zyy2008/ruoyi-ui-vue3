// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改地下水监测发现的超标污染物信息 PUT /admin/groundwaterPollutant */
export async function putAdminGroundwaterPollutant(
  body: API.dexiashuijiancefaxiandechaobiaowuranwuxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/groundwaterPollutant`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增地下水监测发现的超标污染物信息 POST /admin/groundwaterPollutant */
export async function postAdminGroundwaterPollutant(
  body: API.dexiashuijiancefaxiandechaobiaowuranwuxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/groundwaterPollutant`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除地下水监测发现的超标污染物信息 DELETE /admin/groundwaterPollutant/${param0} */
export async function deleteAdminGroundwaterPollutantIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminGroundwaterPollutantIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/api1/admin/groundwaterPollutant/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取地下水监测发现的超标污染物信息详细信息 GET /admin/groundwaterPollutant/${param0} */
export async function getAdminGroundwaterPollutantId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminGroundwaterPollutantIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/api1/admin/groundwaterPollutant/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出地下水监测发现的超标污染物信息列表 POST /admin/groundwaterPollutant/export */
export async function postAdminGroundwaterPollutantExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminGroundwaterPollutantExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/groundwaterPollutant/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询地下水监测发现的超标污染物信息列表 GET /admin/groundwaterPollutant/list */
export async function getAdminGroundwaterPollutantList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminGroundwaterPollutantListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/groundwaterPollutant/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
