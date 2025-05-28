import request from '@/utils/request'

// 查询地下水迁移途径信息列表
export function listPathway(query) {
  return request({
    url: '/admin/pathway/list',
    method: 'get',
    params: query
  })
}

// 查询地下水迁移途径信息详细
export function getPathway(id) {
  return request({
    url: '/admin/pathway/' + id,
    method: 'get'
  })
}

// 新增地下水迁移途径信息
export function addPathway(data) {
  return request({
    url: '/admin/pathway',
    method: 'post',
    data: data
  })
}

// 修改地下水迁移途径信息
export function updatePathway(data) {
  return request({
    url: '/admin/pathway',
    method: 'put',
    data: data
  })
}

// 删除地下水迁移途径信息
export function delPathway(id) {
  return request({
    url: '/admin/pathway/' + id,
    method: 'delete'
  })
}
