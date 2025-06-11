// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 修改管道泄漏 PUT /admin/pipelineLeak */
export async function putAdminPipelineLeak(
  body: API.guandaoxielou,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/pipelineLeak`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 新增管道泄漏 POST /admin/pipelineLeak */
export async function postAdminPipelineLeak(
  body: API.guandaoxielou,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/admin/pipelineLeak`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除管道泄漏 DELETE /admin/pipelineLeak/${param0} */
export async function deleteAdminPipelineLeakIds(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAdminPipelineLeakIdsParams,
  options?: { [key: string]: any }
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/pipelineLeak/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取管道泄漏详细信息 GET /admin/pipelineLeak/${param0} */
export async function getAdminPipelineLeakId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPipelineLeakIdParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/dev-api/admin/pipelineLeak/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 导出管道泄漏列表 POST /admin/pipelineLeak/export */
export async function postAdminPipelineLeakExport(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminPipelineLeakExportParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/admin/pipelineLeak/export`, {
    method: "POST",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}

/** 查询管道泄漏列表 GET /admin/pipelineLeak/list */
export async function getAdminPipelineLeakList(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAdminPipelineLeakListParams,
  options?: { [key: string]: any }
) {
  return request<API.TableDataInfo>(`/dev-api/admin/pipelineLeak/list`, {
    method: "GET",
    params: {
      ...params,
      params: undefined,
      ...params["params"],
    },
    ...(options || {}),
  });
}
