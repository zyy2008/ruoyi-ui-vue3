// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改政策文件 PUT /admin/policyDocuments */
export async function putAdminPolicyDocuments(
  body: API.zhengcewenjian,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/policyDocuments`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增政策文件 POST /admin/policyDocuments */
export async function postAdminPolicyDocuments(
  body: API.zhengcewenjian,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/policyDocuments`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除政策文件 DELETE /admin/policyDocuments/${param0} */
export async function deleteAdminPolicyDocumentsIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminPolicyDocumentsIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/policyDocuments/${param0}`,
    {
      method: "DELETE",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 获取政策文件详细信息 GET /admin/policyDocuments/${param0} */
export async function getAdminPolicyDocumentsId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPolicyDocumentsIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(
    `/dev-api/admin/policyDocuments/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** 导出政策文件列表 POST /admin/policyDocuments/export */
export async function postAdminPolicyDocumentsExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminPolicyDocumentsExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/policyDocuments/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询政策文件列表 GET /admin/policyDocuments/list */
export async function getAdminPolicyDocumentsList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPolicyDocumentsListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/policyDocuments/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
