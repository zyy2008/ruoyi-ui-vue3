// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 下载文件 GET /file/download */
export async function getFileDownload(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getFileDownloadParams,
  options?: { [key: string]: any }
) {
  return request<any>(`/dev-api/file/download`, {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 上传文件返回路径 POST /file/upload */
export async function postFileUpload(
  body: string,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/dev-api/file/upload`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}
