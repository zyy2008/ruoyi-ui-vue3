import request from '@/utils/request'

// 查询企业污染源列表
export function listPollution(query) {
  return request({
    url: '/admin/pollution/list',
    method: 'get',
    params: query
  })
}

// 查询企业污染源详细
export function getPollution(id) {
  return request({
    url: '/admin/pollution/' + id,
    method: 'get'
  })
}

// 新增企业污染源
export function addPollution(data) {
  return request({
    url: '/admin/pollution',
    method: 'post',
    data: data
  })
}

// 修改企业污染源
export function updatePollution(data) {
  return request({
    url: '/admin/pollution',
    method: 'put',
    data: data
  })
}

// 删除企业污染源
export function delPollution(id) {
  return request({
    url: '/admin/pollution/' + id,
    method: 'delete'
  })
}
