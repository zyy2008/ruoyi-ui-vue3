import ajax from "./request";
import type { AxiosRequestConfig } from "axios";

interface IRequest {
  <T = any>(url: string, opts: AxiosRequestConfig): Promise<T>;
}

const request: IRequest = (url, opts: any = { method: "GET" }) => {
  return new Promise((resolve, reject) => {
    ajax(url, {
      ...opts,
      baseURL: "",
    })
      .then((res) => {
        resolve(res as any);
      })
      .catch(reject);
  });
};

export default request;
