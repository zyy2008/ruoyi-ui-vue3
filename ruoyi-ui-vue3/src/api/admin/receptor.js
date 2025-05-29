import request from '@/utils/request'

// 查询地块及周边敏感受体信息列表
export function listReceptor(query) {
  return request({
    url: '/admin/receptor/list',
    method: 'get',
    params: query
  })
}

// 查询地块及周边敏感受体信息详细
export function getReceptor(id) {
  return request({
    url: '/admin/receptor/' + id,
    method: 'get'
  })
}

// 新增地块及周边敏感受体信息
export function addReceptor(data) {
  return request({
    url: '/admin/receptor',
    method: 'post',
    data: data
  })
}

// 修改地块及周边敏感受体信息
export function updateReceptor(data) {
  return request({
    url: '/admin/receptor',
    method: 'put',
    data: data
  })
}

// 删除地块及周边敏感受体信息
export function delReceptor(id) {
  return request({
    url: '/admin/receptor/' + id,
    method: 'delete'
  })
}
