import request from '@/utils/request'

// 查询基地列表
export function listBase(query) {
  return request({
    url: '/admin/base/list',
    method: 'get',
    params: query
  })
}

// 查询基地详细
export function getBase(id) {
  return request({
    url: '/admin/base/' + id,
    method: 'get'
  })
}

// 新增基地
export function addBase(data) {
  return request({
    url: '/admin/base',
    method: 'post',
    data: data
  })
}

// 修改基地
export function updateBase(data) {
  return request({
    url: '/admin/base',
    method: 'put',
    data: data
  })
}

// 删除基地
export function delBase(id) {
  return request({
    url: '/admin/base/' + id,
    method: 'delete'
  })
}


export function getBaseSelect() {
  return request({
    url: '/admin/base/getBase',
    method: 'get'
  })
}


export function getLayerStyle() {
  return request({
    url: '/admin/layer/getLayerStyle',
    method: 'get'
  })
}

export function publishLayer(data) {
  return request({
    url: '/admin/layer/publishLayer',
    method: 'post',
    data: data
  })
}

export function getAllWorkspaces() {
  return request({
    url: '/geoserver/getAllWorkspaces',
    method: 'get'
  })
}

export function getDataSourceByWorkspace(query) {
  return request({
    url: '/geoserver/getDataSourceByWorkspace',
    method: 'get',
    params: query
  })
}

export function getStyleByStyleName(query) {
  return request({
    url: '/geoserver/getStyleByStyleName',
    method: 'get',
    params: query
  })
}


