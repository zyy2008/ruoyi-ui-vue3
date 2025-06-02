// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

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
