// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改调查评估基本信息 PUT /admin/surveyAssessment */
export async function putAdminSurveyAssessment(
  body: API.diaochapinggujibenxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/surveyAssessment`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增调查评估基本信息 POST /admin/surveyAssessment */
export async function postAdminSurveyAssessment(
  body: API.diaochapinggujibenxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/surveyAssessment`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除调查评估基本信息 DELETE /admin/surveyAssessment/${param0} */
export async function deleteAdminSurveyAssessmentIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSurveyAssessmentIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/surveyAssessment/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取调查评估基本信息详细信息 GET /admin/surveyAssessment/${param0} */
export async function getAdminSurveyAssessmentId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSurveyAssessmentIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/surveyAssessment/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出调查评估基本信息列表 POST /admin/surveyAssessment/export */
export async function postAdminSurveyAssessmentExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSurveyAssessmentExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/surveyAssessment/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询调查评估基本信息列表 GET /admin/surveyAssessment/list */
export async function getAdminSurveyAssessmentList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSurveyAssessmentListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/surveyAssessment/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
