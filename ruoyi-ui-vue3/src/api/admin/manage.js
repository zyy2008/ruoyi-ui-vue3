import request from '@/utils/request'

// 查询项目管理列表
export function listManage(query) {
  return request({
    url: '/admin/manage/list',
    method: 'get',
    params: query
  })
}

// 查询项目管理详细
export function getManage(id) {
  return request({
    url: '/admin/manage/' + id,
    method: 'get'
  })
}

// 新增项目管理
export function addManage(data) {
  return request({
    url: '/admin/manage',
    method: 'post',
    data: data
  })
}

// 修改项目管理
export function updateManage(data) {
  return request({
    url: '/admin/manage',
    method: 'put',
    data: data
  })
}

// 删除项目管理
export function delManage(id) {
  return request({
    url: '/admin/manage/' + id,
    method: 'delete'
  })
}
