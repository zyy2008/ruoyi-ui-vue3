import ajax from "./request";
import type { AxiosPromise, AxiosRequestConfig } from "axios";

const request: <T>(
  url: string,
  config?: AxiosRequestConfig
) => AxiosPromise<T> = (url, config) => {
  return ajax(url, {
    ...config,
    baseURL: "",
  });
};

export default request;
