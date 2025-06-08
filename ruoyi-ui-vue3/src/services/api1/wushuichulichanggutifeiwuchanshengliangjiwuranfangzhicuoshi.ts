// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改污水处理厂固体废物产生量及污染防治措施 PUT /admin/sewageSolidWaste */
export async function putAdminSewageSolidWaste(
  body: API.wushuichulichanggutifeiwuchanshengliangjiwuranfangzhicuoshi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/sewageSolidWaste`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增污水处理厂固体废物产生量及污染防治措施 POST /admin/sewageSolidWaste */
export async function postAdminSewageSolidWaste(
  body: API.wushuichulichanggutifeiwuchanshengliangjiwuranfangzhicuoshi,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/sewageSolidWaste`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除污水处理厂固体废物产生量及污染防治措施 DELETE /admin/sewageSolidWaste/${param0} */
export async function deleteAdminSewageSolidWasteIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminSewageSolidWasteIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/sewageSolidWaste/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取污水处理厂固体废物产生量及污染防治措施详细信息 GET /admin/sewageSolidWaste/${param0} */
export async function getAdminSewageSolidWasteId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSewageSolidWasteIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/sewageSolidWaste/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出污水处理厂固体废物产生量及污染防治措施列表 POST /admin/sewageSolidWaste/export */
export async function postAdminSewageSolidWasteExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminSewageSolidWasteExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/sewageSolidWaste/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询污水处理厂固体废物产生量及污染防治措施列表 GET /admin/sewageSolidWaste/list */
export async function getAdminSewageSolidWasteList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminSewageSolidWasteListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/sewageSolidWaste/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
