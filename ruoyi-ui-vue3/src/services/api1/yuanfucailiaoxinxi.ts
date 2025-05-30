// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改原辅材料信息 PUT /admin/rawMaterials */
export async function putAdminRawMaterials(
  body: API.yuanfucailiaoxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/rawMaterials`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增原辅材料信息 POST /admin/rawMaterials */
export async function postAdminRawMaterials(
  body: API.yuanfucailiaoxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/rawMaterials`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除原辅材料信息 DELETE /admin/rawMaterials/${param0} */
export async function deleteAdminRawMaterialsIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminRawMaterialsIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/rawMaterials/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取原辅材料信息详细信息 GET /admin/rawMaterials/${param0} */
export async function getAdminRawMaterialsId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminRawMaterialsIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api1/admin/rawMaterials/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出原辅材料信息列表 POST /admin/rawMaterials/export */
export async function postAdminRawMaterialsExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminRawMaterialsExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/api1/admin/rawMaterials/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询原辅材料信息列表 GET /admin/rawMaterials/list */
export async function getAdminRawMaterialsList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminRawMaterialsListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/api1/admin/rawMaterials/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
