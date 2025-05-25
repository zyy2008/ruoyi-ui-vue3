// @ts-ignore
/* eslint-disable */
import request from "@/utils/request";

/** 用户登录 POST /login */
export async function postLogin(
  body: API.LoginBody,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`api1/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}
