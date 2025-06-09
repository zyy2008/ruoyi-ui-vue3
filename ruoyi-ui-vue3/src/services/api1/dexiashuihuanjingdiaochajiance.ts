// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改地下水环境调查监测信息 PUT /admin/groundwaterMonitoring */
export async function putAdminGroundwaterMonitoring(
  body: API.dexiashuijianceshuju,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/groundwaterMonitoring`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增地下水环境调查监测信息 POST /admin/groundwaterMonitoring */
export async function postAdminGroundwaterMonitoring(
  body: API.dexiashuijianceshuju,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/groundwaterMonitoring`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除地下水环境调查监测信息 DELETE /admin/groundwaterMonitoring/${param0} */
export async function deleteAdminGroundwaterMonitoringIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminGroundwaterMonitoringIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/groundwaterMonitoring/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取地下水环境调查监测信息详细信息 GET /admin/groundwaterMonitoring/${param0} */
export async function getAdminGroundwaterMonitoringId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminGroundwaterMonitoringIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/groundwaterMonitoring/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出地下水环境调查监测信息列表 POST /admin/groundwaterMonitoring/export */
export async function postAdminGroundwaterMonitoringExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminGroundwaterMonitoringExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/groundwaterMonitoring/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询地下水环境调查监测信息列表 GET /admin/groundwaterMonitoring/list */
export async function getAdminGroundwaterMonitoringList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminGroundwaterMonitoringListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(
    `/dev-api/admin/groundwaterMonitoring/list`,
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
