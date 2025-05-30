// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改土壤分层信息 PUT /admin/soilPathway */
export async function putAdminSoilPathway(
  body: API.turanghuanjingdiaochajiancexinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/soilPathway`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增土壤分层信息 POST /admin/soilPathway */
export async function postAdminSoilPathway(
  body: API.turanghuanjingdiaochajiancexinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/soilPathway`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除土壤分层信息 DELETE /admin/soilPathway/${param0} */
export async function deleteAdminSoilPathwayIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSoilPathwayIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/soilPathway/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取土壤分层信息详细信息 GET /admin/soilPathway/${param0} */
export async function getAdminSoilPathwayId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSoilPathwayIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/soilPathway/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出土壤分层信息列表 POST /admin/soilPathway/export */
export async function postAdminSoilPathwayExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSoilPathwayExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/soilPathway/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询土壤分层信息列表 GET /admin/soilPathway/list */
export async function getAdminSoilPathwayList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSoilPathwayListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/soilPathway/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
