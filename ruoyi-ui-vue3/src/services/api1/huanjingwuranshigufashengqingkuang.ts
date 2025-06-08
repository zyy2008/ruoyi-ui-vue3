// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改环境污染事故发生情况 PUT /admin/pollutionIncident */
export async function putAdminPollutionIncident(
  body: API.huanjingwuranshigufashengqingkuang,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/pollutionIncident`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增环境污染事故发生情况 POST /admin/pollutionIncident */
export async function postAdminPollutionIncident(
  body: API.huanjingwuranshigufashengqingkuang,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/pollutionIncident`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除环境污染事故发生情况 DELETE /admin/pollutionIncident/${param0} */
export async function deleteAdminPollutionIncidentIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminPollutionIncidentIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/pollutionIncident/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取环境污染事故发生情况详细信息 GET /admin/pollutionIncident/${param0} */
export async function getAdminPollutionIncidentId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPollutionIncidentIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/pollutionIncident/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出环境污染事故发生情况列表 POST /admin/pollutionIncident/export */
export async function postAdminPollutionIncidentExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminPollutionIncidentExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/pollutionIncident/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询环境污染事故发生情况列表 GET /admin/pollutionIncident/list */
export async function getAdminPollutionIncidentList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPollutionIncidentListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/pollutionIncident/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
