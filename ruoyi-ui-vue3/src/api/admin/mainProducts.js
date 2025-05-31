import request from '@/utils/request'

// 查询主要产品信息列表
export function listMainProducts(query) {
  return request({
    url: '/admin/mainProducts/list',
    method: 'get',
    params: query
  })
}

// 查询主要产品信息详细
export function getMainProducts(id) {
  return request({
    url: '/admin/mainProducts/' + id,
    method: 'get'
  })
}

// 新增主要产品信息
export function addMainProducts(data) {
  return request({
    url: '/admin/mainProducts',
    method: 'post',
    data: data
  })
}

// 修改主要产品信息
export function updateMainProducts(data) {
  return request({
    url: '/admin/mainProducts',
    method: 'put',
    data: data
  })
}

// 删除主要产品信息
export function delMainProducts(id) {
  return request({
    url: '/admin/mainProducts/' + id,
    method: 'delete'
  })
}
