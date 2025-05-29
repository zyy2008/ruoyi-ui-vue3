import request from '@/utils/request'

// 查询土壤监测发现的超标污染物信息列表
export function listPollutant(query) {
  return request({
    url: '/admin/pollutant/list',
    method: 'get',
    params: query
  })
}

// 查询土壤监测发现的超标污染物信息详细
export function getPollutant(id) {
  return request({
    url: '/admin/pollutant/' + id,
    method: 'get'
  })
}

// 新增土壤监测发现的超标污染物信息
export function addPollutant(data) {
  return request({
    url: '/admin/pollutant',
    method: 'post',
    data: data
  })
}

// 修改土壤监测发现的超标污染物信息
export function updatePollutant(data) {
  return request({
    url: '/admin/pollutant',
    method: 'put',
    data: data
  })
}

// 删除土壤监测发现的超标污染物信息
export function delPollutant(id) {
  return request({
    url: '/admin/pollutant/' + id,
    method: 'delete'
  })
}
