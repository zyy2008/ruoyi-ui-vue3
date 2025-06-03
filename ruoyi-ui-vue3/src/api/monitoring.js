import request from '@/utils/request'

export function selectGJJL(query) {
  return request({
    url: '/admin/monitoring/selectGJJL',
    method: 'get',
    params: query
  })
}


// 查询监测数据管理列表
export function listMonitoring(query) {
  return request({
    url: '/admin/monitoring/list',
    method: 'get',
    params: query
  })
}

// 查询监测数据管理详细
export function getMonitoring(id) {
  return request({
    url: '/admin/monitoring/' + id,
    method: 'get'
  })
}

// 新增监测数据管理
export function addMonitoring(data) {
  return request({
    url: '/admin/monitoring',
    method: 'post',
    data: data
  })
}

// 修改监测数据管理
export function updateMonitoring(data) {
  return request({
    url: '/admin/monitoring',
    method: 'put',
    data: data
  })
}

// 删除监测数据管理
export function delMonitoring(id) {
  return request({
    url: '/admin/monitoring/' + id,
    method: 'delete'
  })
}
