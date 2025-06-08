// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改地块污染情况分析 PUT /admin/landPollutionAnalysis */
export async function putAdminLandPollutionAnalysis(
  body: API.dekuaiwuranqingkuangfenxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/landPollutionAnalysis`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增地块污染情况分析 POST /admin/landPollutionAnalysis */
export async function postAdminLandPollutionAnalysis(
  body: API.dekuaiwuranqingkuangfenxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/landPollutionAnalysis`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除地块污染情况分析 DELETE /admin/landPollutionAnalysis/${param0} */
export async function deleteAdminLandPollutionAnalysisIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminLandPollutionAnalysisIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/landPollutionAnalysis/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取地块污染情况分析详细信息 GET /admin/landPollutionAnalysis/${param0} */
export async function getAdminLandPollutionAnalysisId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminLandPollutionAnalysisIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/landPollutionAnalysis/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出地块污染情况分析列表 POST /admin/landPollutionAnalysis/export */
export async function postAdminLandPollutionAnalysisExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminLandPollutionAnalysisExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/landPollutionAnalysis/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询地块污染情况分析列表 GET /admin/landPollutionAnalysis/list */
export async function getAdminLandPollutionAnalysisList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminLandPollutionAnalysisListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(
    `/dev-api/admin/landPollutionAnalysis/list`,
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
