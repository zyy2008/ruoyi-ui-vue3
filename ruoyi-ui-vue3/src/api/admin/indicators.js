import request from '@/utils/request'

// 查询指标分类列表
export function listIndicators(query) {
  return request({
    url: '/admin/indicators/list',
    method: 'get',
    params: query
  })
}

// 查询指标分类详细
export function getIndicators(id) {
  return request({
    url: '/admin/indicators/' + id,
    method: 'get'
  })
}

// 新增指标分类
export function addIndicators(data) {
  return request({
    url: '/admin/indicators',
    method: 'post',
    data: data
  })
}

// 修改指标分类
export function updateIndicators(data) {
  return request({
    url: '/admin/indicators',
    method: 'put',
    data: data
  })
}

// 删除指标分类
export function delIndicators(id) {
  return request({
    url: '/admin/indicators/' + id,
    method: 'delete'
  })
}
