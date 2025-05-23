import request from '@/utils/request'

// 查询要素分类编码规范列表
export function listNorm(query) {
  return request({
    url: '/admin/norm/list',
    method: 'get',
    params: query
  })
}

// 查询要素分类编码规范详细
export function getNorm(id) {
  return request({
    url: '/admin/norm/' + id,
    method: 'get'
  })
}

// 新增要素分类编码规范
export function addNorm(data) {
  return request({
    url: '/admin/norm',
    method: 'post',
    data: data
  })
}

// 修改要素分类编码规范
export function updateNorm(data) {
  return request({
    url: '/admin/norm',
    method: 'put',
    data: data
  })
}

// 删除要素分类编码规范
export function delNorm(id) {
  return request({
    url: '/admin/norm/' + id,
    method: 'delete'
  })
}
