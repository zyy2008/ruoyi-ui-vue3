import request from '@/utils/request'

// 查询要素分类列表
export function listClassification(query) {
  return request({
    url: '/admin/classification/list',
    method: 'get',
    params: query
  })
}

// 查询要素分类详细
export function getClassification(id) {
  return request({
    url: '/admin/classification/' + id,
    method: 'get'
  })
}

// 新增要素分类
export function addClassification(data) {
  return request({
    url: '/admin/classification',
    method: 'post',
    data: data
  })
}

// 修改要素分类
export function updateClassification(data) {
  return request({
    url: '/admin/classification',
    method: 'put',
    data: data
  })
}

// 删除要素分类
export function delClassification(id) {
  return request({
    url: '/admin/classification/' + id,
    method: 'delete'
  })
}
