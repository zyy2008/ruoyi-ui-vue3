import request from '@/utils/request'

// 查询企业管理列表
export function listEnterprises(query) {
  return request({
    url: '/admin/enterprises/list',
    method: 'get',
    params: query
  })
}

// 查询企业管理详细
export function getEnterprises(id) {
  return request({
    url: '/admin/enterprises/' + id,
    method: 'get'
  })
}

// 新增企业管理
export function addEnterprises(data) {
  return request({
    url: '/admin/enterprises',
    method: 'post',
    data: data
  })
}

// 修改企业管理
export function updateEnterprises(data) {
  return request({
    url: '/admin/enterprises',
    method: 'put',
    data: data
  })
}

// 删除企业管理
export function delEnterprises(id) {
  return request({
    url: '/admin/enterprises/' + id,
    method: 'delete'
  })
}
