import request from '@/utils/request'

// 查询申请审批列表
export function listApproval(query) {
  return request({
    url: '/admin/approval/list',
    method: 'get',
    params: query
  })
}

// 查询申请审批详细
export function getApproval(id) {
  return request({
    url: '/admin/approval/' + id,
    method: 'get'
  })
}

// 新增申请审批
export function addApproval(data) {
  return request({
    url: '/admin/approval',
    method: 'post',
    data: data
  })
}

// 修改申请审批
export function updateApproval(data) {
  return request({
    url: '/admin/approval',
    method: 'put',
    data: data
  })
}

// 删除申请审批
export function delApproval(id) {
  return request({
    url: '/admin/approval/' + id,
    method: 'delete'
  })
}

//审批
export function setState(data) {
  return request({
    url: '/admin/approval/state',
    method: 'post',
    data: data
  })
}
