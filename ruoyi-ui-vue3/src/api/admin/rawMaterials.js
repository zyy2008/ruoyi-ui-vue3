import request from '@/utils/request'

// 查询原辅材料信息列表
export function listRawMaterials(query) {
  return request({
    url: '/admin/rawMaterials/list',
    method: 'get',
    params: query
  })
}

// 查询原辅材料信息详细
export function getRawMaterials(id) {
  return request({
    url: '/admin/rawMaterials/' + id,
    method: 'get'
  })
}

// 新增原辅材料信息
export function addRawMaterials(data) {
  return request({
    url: '/admin/rawMaterials',
    method: 'post',
    data: data
  })
}

// 修改原辅材料信息
export function updateRawMaterials(data) {
  return request({
    url: '/admin/rawMaterials',
    method: 'put',
    data: data
  })
}

// 删除原辅材料信息
export function delRawMaterials(id) {
  return request({
    url: '/admin/rawMaterials/' + id,
    method: 'delete'
  })
}
