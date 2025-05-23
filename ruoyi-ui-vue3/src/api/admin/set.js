import request from '@/utils/request'

// 查询图层集列表
export function listSet(query) {
  return request({
    url: '/admin/set/list',
    method: 'get',
    params: query
  })
}

// 查询图层集详细
export function getSet(id) {
  return request({
    url: '/admin/set/' + id,
    method: 'get'
  })
}

// 新增图层集
export function addSet(data) {
  return request({
    url: '/admin/set',
    method: 'post',
    data: data
  })
}

// 修改图层集
export function updateSet(data) {
  return request({
    url: '/admin/set',
    method: 'put',
    data: data
  })
}

// 删除图层集
export function delSet(id) {
  return request({
    url: '/admin/set/' + id,
    method: 'delete'
  })
}

export function getSetTree(data) {
  return request({
    url: '/admin/set/layerTree',
    method: 'get',
    params: data
  })
}