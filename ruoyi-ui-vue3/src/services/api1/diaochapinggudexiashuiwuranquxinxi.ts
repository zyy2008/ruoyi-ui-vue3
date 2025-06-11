// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改调查评估地下水污染区信息 PUT /admin/surveyGroundwaterPollution */
export async function putAdminSurveyGroundwaterPollution(
  body: API.diaochapinggudexiashuiwuranquxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/surveyGroundwaterPollution`,
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

/** 新增调查评估地下水污染区信息 POST /admin/surveyGroundwaterPollution */
export async function postAdminSurveyGroundwaterPollution(
  body: API.diaochapinggudexiashuiwuranquxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/surveyGroundwaterPollution`,
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

/** 删除调查评估地下水污染区信息 DELETE /admin/surveyGroundwaterPollution/${param0} */
export async function deleteAdminSurveyGroundwaterPollutionIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSurveyGroundwaterPollutionIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/surveyGroundwaterPollution/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取调查评估地下水污染区信息详细信息 GET /admin/surveyGroundwaterPollution/${param0} */
export async function getAdminSurveyGroundwaterPollutionId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSurveyGroundwaterPollutionIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/surveyGroundwaterPollution/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出调查评估地下水污染区信息列表 POST /admin/surveyGroundwaterPollution/export */
export async function postAdminSurveyGroundwaterPollutionExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSurveyGroundwaterPollutionExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/surveyGroundwaterPollution/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询调查评估地下水污染区信息列表 GET /admin/surveyGroundwaterPollution/list */
export async function getAdminSurveyGroundwaterPollutionList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSurveyGroundwaterPollutionListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(
    `/dev-api/admin/surveyGroundwaterPollution/list`,
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
