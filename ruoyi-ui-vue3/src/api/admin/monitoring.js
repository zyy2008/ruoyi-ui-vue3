import request from "@/utils/request";

// 查询水质监测数据列表
export function listMonitoring(query) {
  return request({
    url: "/admin/monitoring/list",
    method: "get",
    params: query,
  });
}

// 查询水质监测数据详细
export function getMonitoring(id) {
  return request({
    url: "/admin/monitoring/" + id,
    method: "get",
  });
}

// 新增水质监测数据
export function addMonitoring(data) {
  return request({
    url: "/admin/monitoring",
    method: "post",
    data: data,
  });
}

// 修改水质监测数据
export function updateMonitoring(data) {
  return request({
    url: "/admin/monitoring",
    method: "put",
    data: data,
  });
}

// 删除水质监测数据
export function delMonitoring(id) {
  return request({
    url: "/admin/monitoring/" + id,
    method: "delete",
  });
}

// 导出水质监测数据
export function exportMonitoring(query) {
  return request({
    url: "/admin/monitoring/export",
    method: "get",
    params: query,
  });
}

// 监测数据管理
export function listMonitoringAmlist(query) {
  return request({
    url: "/admin/monitoring/amList",
    method: "get",
    params: query,
  });
}
