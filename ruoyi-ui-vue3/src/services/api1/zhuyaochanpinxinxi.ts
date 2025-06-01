// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改主要产品信息 PUT /admin/mainProducts */
export async function putAdminMainProducts(
  body: API.zhuyaochanpinxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/mainProducts`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增主要产品信息 POST /admin/mainProducts */
export async function postAdminMainProducts(
  body: API.zhuyaochanpinxinxi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/mainProducts`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除主要产品信息 DELETE /admin/mainProducts/${param0} */
export async function deleteAdminMainProductsIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminMainProductsIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/mainProducts/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取主要产品信息详细信息 GET /admin/mainProducts/${param0} */
export async function getAdminMainProductsId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminMainProductsIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/mainProducts/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出主要产品信息列表 POST /admin/mainProducts/export */
export async function postAdminMainProductsExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminMainProductsExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/mainProducts/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询主要产品信息列表 GET /admin/mainProducts/list */
export async function getAdminMainProductsList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminMainProductsListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/mainProducts/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
