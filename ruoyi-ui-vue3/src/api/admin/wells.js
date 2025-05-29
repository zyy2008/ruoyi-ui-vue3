import request from '@/utils/request'

// 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息列表
export function listWells(query) {
  return request({
    url: '/admin/wells/list',
    method: 'get',
    params: query
  })
}

// 查询园区初调监测井信息，记录每个监测井的基础属性与附加信息详细
export function getWells(id) {
  return request({
    url: '/admin/wells/' + id,
    method: 'get'
  })
}

// 新增园区初调监测井信息，记录每个监测井的基础属性与附加信息
export function addWells(data) {
  return request({
    url: '/admin/wells',
    method: 'post',
    data: data
  })
}

// 修改园区初调监测井信息，记录每个监测井的基础属性与附加信息
export function updateWells(data) {
  return request({
    url: '/admin/wells',
    method: 'put',
    data: data
  })
}

// 删除园区初调监测井信息，记录每个监测井的基础属性与附加信息
export function delWells(id) {
  return request({
    url: '/admin/wells/' + id,
    method: 'delete'
  })
}
