import request from '@/utils/request'

// 查询图层列表
export function listLayer(query) {
  return request({
    url: '/admin/layer/list',
    method: 'get',
    params: query
  })
}

// 查询图层详细
export function getLayer(id) {
  return request({
    url: '/admin/layer/' + id,
    method: 'get'
  })
}

// 新增图层
export function addLayer(data) {
  return request({
    url: '/admin/layer',
    method: 'post',
    data: data
  })
}

// 修改图层
export function updateLayer(data) {
  return request({
    url: '/admin/layer',
    method: 'put',
    data: data
  })
}

// 删除图层
export function delLayer(id) {
  return request({
    url: '/admin/layer/' + id,
    method: 'delete'
  })
}
