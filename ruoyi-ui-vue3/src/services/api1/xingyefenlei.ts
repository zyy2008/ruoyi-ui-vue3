// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改行业分类 PUT /admin/industry */
export async function putAdminIndustry(
  body: API.xingyefenlei,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/industry`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增行业分类 POST /admin/industry */
export async function postAdminIndustry(
  body: API.xingyefenlei,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/industry`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除行业分类 DELETE /admin/industry/${param0} */
export async function deleteAdminIndustryIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminIndustryIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/industry/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取行业分类详细信息 GET /admin/industry/${param0} */
export async function getAdminIndustryId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminIndustryIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/industry/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出行业分类列表 POST /admin/industry/export */
export async function postAdminIndustryExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminIndustryExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/industry/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询行业分类列表 GET /admin/industry/list */
export async function getAdminIndustryList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminIndustryListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/industry/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询行业分类树形结构 GET /admin/industry/treeList */
export async function getAdminIndustryTreeList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminIndustryTreeListParams,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/industry/treeList`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
