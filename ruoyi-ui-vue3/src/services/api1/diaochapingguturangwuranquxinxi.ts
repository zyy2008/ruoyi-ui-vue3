// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改调查评估土壤污染区信息 PUT /admin/surveySoilPollution */
export async function putAdminSurveySoilPollution(
  body: API.diaochapingguturangwuranquxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/surveySoilPollution`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增调查评估土壤污染区信息 POST /admin/surveySoilPollution */
export async function postAdminSurveySoilPollution(
  body: API.diaochapingguturangwuranquxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/surveySoilPollution`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除调查评估土壤污染区信息 DELETE /admin/surveySoilPollution/${param0} */
export async function deleteAdminSurveySoilPollutionIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSurveySoilPollutionIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/surveySoilPollution/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取调查评估土壤污染区信息详细信息 GET /admin/surveySoilPollution/${param0} */
export async function getAdminSurveySoilPollutionId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSurveySoilPollutionIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/surveySoilPollution/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出调查评估土壤污染区信息列表 POST /admin/surveySoilPollution/export */
export async function postAdminSurveySoilPollutionExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSurveySoilPollutionExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/surveySoilPollution/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询调查评估土壤污染区信息列表 GET /admin/surveySoilPollution/list */
export async function getAdminSurveySoilPollutionList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSurveySoilPollutionListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/surveySoilPollution/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
