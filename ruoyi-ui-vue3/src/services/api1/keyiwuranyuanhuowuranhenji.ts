// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改可疑污染源或污染痕迹 PUT /admin/suspectedPollutionTrace */
export async function putAdminSuspectedPollutionTrace(
  body: API.keyiwuranyuanhuowuranhenji,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/suspectedPollutionTrace`,
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

/** 新增可疑污染源或污染痕迹 POST /admin/suspectedPollutionTrace */
export async function postAdminSuspectedPollutionTrace(
  body: API.keyiwuranyuanhuowuranhenji,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(
    `/dev-api/admin/suspectedPollutionTrace`,
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

/** 删除可疑污染源或污染痕迹 DELETE /admin/suspectedPollutionTrace/${param0} */
export async function deleteAdminSuspectedPollutionTraceIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSuspectedPollutionTraceIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/suspectedPollutionTrace/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取可疑污染源或污染痕迹详细信息 GET /admin/suspectedPollutionTrace/${param0} */
export async function getAdminSuspectedPollutionTraceId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSuspectedPollutionTraceIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/suspectedPollutionTrace/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出可疑污染源或污染痕迹列表 POST /admin/suspectedPollutionTrace/export */
export async function postAdminSuspectedPollutionTraceExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSuspectedPollutionTraceExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/suspectedPollutionTrace/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询可疑污染源或污染痕迹列表 GET /admin/suspectedPollutionTrace/list */
export async function getAdminSuspectedPollutionTraceList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSuspectedPollutionTraceListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(
    `/dev-api/admin/suspectedPollutionTrace/list`,
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
