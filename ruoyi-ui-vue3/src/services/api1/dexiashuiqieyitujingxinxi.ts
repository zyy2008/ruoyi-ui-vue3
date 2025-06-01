// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改地下水迁移途径信息 PUT /admin/groundwaterPathway */
export async function putAdminGroundwaterPathway(
  body: API.dexiashuiqieyitujingxinxiduixiang,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/groundwaterPathway`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增地下水迁移途径信息 POST /admin/groundwaterPathway */
export async function postAdminGroundwaterPathway(
  body: API.dexiashuiqieyitujingxinxiduixiang,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/groundwaterPathway`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除地下水迁移途径信息 DELETE /admin/groundwaterPathway/${param0} */
export async function deleteAdminGroundwaterPathwayIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminGroundwaterPathwayIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/groundwaterPathway/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取地下水迁移途径信息详细信息 GET /admin/groundwaterPathway/${param0} */
export async function getAdminGroundwaterPathwayId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminGroundwaterPathwayIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/groundwaterPathway/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出地下水迁移途径信息列表 POST /admin/groundwaterPathway/export */
export async function postAdminGroundwaterPathwayExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminGroundwaterPathwayExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/groundwaterPathway/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询地下水迁移途径信息列表 GET /admin/groundwaterPathway/list */
export async function getAdminGroundwaterPathwayList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminGroundwaterPathwayListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/groundwaterPathway/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
