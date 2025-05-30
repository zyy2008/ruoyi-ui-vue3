// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改土壤监测超标污染物信息 PUT /admin/soilPollutant */
export async function putAdminSoilPollutant(
  body: API.turangjiancefaxiandechaobiaowuranwuxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/soilPollutant`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增土壤监测超标污染物信息 POST /admin/soilPollutant */
export async function postAdminSoilPollutant(
  body: API.turangjiancefaxiandechaobiaowuranwuxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/soilPollutant`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除土壤监测超标污染物信息 DELETE /admin/soilPollutant/${param0} */
export async function deleteAdminSoilPollutantIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSoilPollutantIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/soilPollutant/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取土壤监测超标污染物信息详细信息 GET /admin/soilPollutant/${param0} */
export async function getAdminSoilPollutantId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSoilPollutantIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/soilPollutant/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出土壤监测超标污染物信息列表 POST /admin/soilPollutant/export */
export async function postAdminSoilPollutantExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSoilPollutantExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/soilPollutant/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询土壤监测超标污染物信息列表 GET /admin/soilPollutant/list */
export async function getAdminSoilPollutantList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSoilPollutantListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/soilPollutant/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
