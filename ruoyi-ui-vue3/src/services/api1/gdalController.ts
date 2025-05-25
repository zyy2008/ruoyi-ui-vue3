// @ts-ignore
/* eslint-disable */
import request from "@/utils/http";

/** 获取所有坐标系 POST /admin/gdal/getAllSpatialRefSystems */
export async function postAdminGdalGetAllSpatialRefSystems(options?: {
  [key: string]: any;
}) {
  return request<Record<string, any>>(
    `/api1/admin/gdal/getAllSpatialRefSystems`,
    {
      method: "POST",
      ...(options || {}),
    }
  );
}

/** 获取裁剪区域 POST /admin/gdal/getIntersectionExt */
export async function postAdminGdalGetIntersectionExt(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.postAdminGdalGetIntersectionExtParams,
  options?: { [key: string]: any }
) {
  return request<Record<string, any>>(`/api1/admin/gdal/getIntersectionExt`, {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
