import request from '@/utils/request'

// 查询实时数据监测列表
export function listRealtimeWaterQualityMonitoring(query) {
  return request({
    url: '/admin/realtimeWaterQualityMonitoring/list',
    method: 'get',
    params: query
  })
}

// 查询实时数据监测详细
export function getRealtimeWaterQualityMonitoring(id) {
  return request({
    url: '/admin/realtimeWaterQualityMonitoring/' + id,
    method: 'get'
  })
}

// 新增实时数据监测
export function addRealtimeWaterQualityMonitoring(data) {
  return request({
    url: '/admin/realtimeWaterQualityMonitoring',
    method: 'post',
    data: data
  })
}

// 修改实时数据监测
export function updateRealtimeWaterQualityMonitoring(data) {
  return request({
    url: '/admin/realtimeWaterQualityMonitoring',
    method: 'put',
    data: data
  })
}

// 删除实时数据监测
export function delRealtimeWaterQualityMonitoring(id) {
  return request({
    url: '/admin/realtimeWaterQualityMonitoring/' + id,
    method: 'delete'
  })
}
