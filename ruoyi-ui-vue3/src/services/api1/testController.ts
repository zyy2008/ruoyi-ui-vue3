// @ts-ignore
/* eslint-disable */
import request from "@/utils/request";

/** 获取用户详细 GET /test/user/${param0} */
export async function getTestUserUserId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getTestUserUserIdParams,
  options?: { [key: string]: any }
) {
  const { userId: param0, ...queryParams } = params;
  return request<API.RUserEntity>(`api1/test/user/${param0}`, {
    method: "GET",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 删除用户信息 DELETE /test/user/${param0} */
export async function deleteTestUserUserId(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteTestUserUserIdParams,
  options?: { [key: string]: any }
) {
  const { userId: param0, ...queryParams } = params;
  return request<API.Rstring>(`api1/test/user/${param0}`, {
    method: "DELETE",
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 获取用户列表 GET /test/user/list */
export async function getTestUserList(options?: { [key: string]: any }) {
  return request<API.RListUserEntity>(`api1/test/user/list`, {
    method: "GET",
    ...(options || {}),
  });
}

/** 新增用户 POST /test/user/save */
export async function postTestUserSave(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postTestUserSaveParams,
  options?: { [key: string]: any }
) {
  return request<API.Rstring>(`api1/test/user/save`, {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 更新用户 PUT /test/user/update */
export async function putTestUserUpdate(
  body: API.UserEntity,
  options?: { [key: string]: any }
) {
  return request<API.Rstring>(`api1/test/user/update`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}
