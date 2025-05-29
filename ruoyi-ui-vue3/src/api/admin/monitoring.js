import request from '@/utils/request'

// 查询土壤环境调查监测信息列表
export function listMonitoring(query) {
  return request({
    url: '/admin/monitoring/list',
    method: 'get',
    params: query
  })
}

// 查询土壤环境调查监测信息详细
export function getMonitoring(id) {
  return request({
    url: '/admin/monitoring/' + id,
    method: 'get'
  })
}

// 新增土壤环境调查监测信息
export function addMonitoring(data) {
  return request({
    url: '/admin/monitoring',
    method: 'post',
    data: data
  })
}

// 修改土壤环境调查监测信息
export function updateMonitoring(data) {
  return request({
    url: '/admin/monitoring',
    method: 'put',
    data: data
  })
}

// 删除土壤环境调查监测信息
export function delMonitoring(id) {
  return request({
    url: '/admin/monitoring/' + id,
    method: 'delete'
  })
}
