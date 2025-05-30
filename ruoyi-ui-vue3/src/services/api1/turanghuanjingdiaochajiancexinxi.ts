// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改土壤环境调查监测信息 PUT /admin/soilMonitoring */
export async function putAdminSoilMonitoring(
  body: API.turanghuanjingdiaochajiancexinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/soilMonitoring`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增土壤环境调查监测信息 POST /admin/soilMonitoring */
export async function postAdminSoilMonitoring(
  body: API.turanghuanjingdiaochajiancexinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/soilMonitoring`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除土壤环境调查监测信息 DELETE /admin/soilMonitoring/${param0} */
export async function deleteAdminSoilMonitoringIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSoilMonitoringIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/soilMonitoring/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取土壤环境调查监测信息详细信息 GET /admin/soilMonitoring/${param0} */
export async function getAdminSoilMonitoringId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSoilMonitoringIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/soilMonitoring/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出土壤环境调查监测信息列表 POST /admin/soilMonitoring/export */
export async function postAdminSoilMonitoringExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSoilMonitoringExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/soilMonitoring/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询土壤环境调查监测信息列表 GET /admin/soilMonitoring/list */
export async function getAdminSoilMonitoringList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSoilMonitoringListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/soilMonitoring/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
