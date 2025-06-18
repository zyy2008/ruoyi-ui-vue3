// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改实时数据监测 PUT /admin/realtimeWaterQualityMonitoring */
export async function putAdminRealtimeWaterQualityMonitoring(
  body: API.shishishujujiance,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/realtimeWaterQualityMonitoring`,
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      data: body,
      ...(options || {}),
    }
  );
}

/** 新增实时数据监测 POST /admin/realtimeWaterQualityMonitoring */
export async function postAdminRealtimeWaterQualityMonitoring(
  body: API.shishishujujiance,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/realtimeWaterQualityMonitoring`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: body,
      ...(options || {}),
    }
  );
}

/** 删除实时数据监测 DELETE /admin/realtimeWaterQualityMonitoring/${param0} */
export async function deleteAdminRealtimeWaterQualityMonitoringIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminRealtimeWaterQualityMonitoringIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/realtimeWaterQualityMonitoring/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取实时数据监测详细信息 GET /admin/realtimeWaterQualityMonitoring/${param0} */
export async function getAdminRealtimeWaterQualityMonitoringId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminRealtimeWaterQualityMonitoringIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/realtimeWaterQualityMonitoring/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出实时数据监测列表 POST /admin/realtimeWaterQualityMonitoring/export */
export async function postAdminRealtimeWaterQualityMonitoringExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminRealtimeWaterQualityMonitoringExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/realtimeWaterQualityMonitoring/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 获取监测井最新数据 GET /admin/realtimeWaterQualityMonitoring/getBatchData */
export async function getAdminRealtimeWaterQualityMonitoringGetBatchData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminRealtimeWaterQualityMonitoringGetBatchDataParams,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/realtimeWaterQualityMonitoring/getBatchData`,
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** 获取单个监测井折线图数据 GET /admin/realtimeWaterQualityMonitoring/getSingleWellMonitoringLineChartData */
export async function getAdminRealtimeWaterQualityMonitoringGetSingleWellMonitoringLineChartData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminRealtimeWaterQualityMonitoringGetSingleWellMonitoringLineChartDataParams,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/realtimeWaterQualityMonitoring/getSingleWellMonitoringLineChartData`,
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** 查询实时数据监测列表 GET /admin/realtimeWaterQualityMonitoring/list */
export async function getAdminRealtimeWaterQualityMonitoringList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminRealtimeWaterQualityMonitoringListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(
    `/dev-api/admin/realtimeWaterQualityMonitoring/list`,
    {
      method: "GET",
      params: {
        ...params,
        params: undefined,
        ...params["params"],
      },
      ...(options || {}),
    }
  );
}
