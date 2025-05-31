import request from '@/utils/request'

// 查询水质监测数据列表
export function listMonitoring(query) {
  return request({
    url: '/system/monitoring/list',
    method: 'get',
    params: query
  })
}

// 查询水质监测数据详细
export function getMonitoring(id) {
  return request({
    url: '/system/monitoring/' + id,
    method: 'get'
  })
}

// 新增水质监测数据
export function addMonitoring(data) {
  return request({
    url: '/system/monitoring',
    method: 'post',
    data: data
  })
}

// 修改水质监测数据
export function updateMonitoring(data) {
  return request({
    url: '/system/monitoring',
    method: 'put',
    data: data
  })
}

// 删除水质监测数据
export function delMonitoring(id) {
  return request({
    url: '/system/monitoring/' + id,
    method: 'delete'
  })
}

// 导出水质监测数据
export function exportMonitoring(query) {
  return request({
    url: '/system/monitoring/export',
    method: 'get',
    params: query
  })
}
