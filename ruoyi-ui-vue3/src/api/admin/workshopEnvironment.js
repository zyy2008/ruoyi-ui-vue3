import request from '@/utils/request'

// 查询生产车间分布与环境情况列表
export function listWorkshopEnvironment(query) {
  return request({
    url: '/admin/workshopEnvironment/list',
    method: 'get',
    params: query
  })
}

// 查询生产车间分布与环境情况详细
export function getWorkshopEnvironment(id) {
  return request({
    url: '/admin/workshopEnvironment/' + id,
    method: 'get'
  })
}

// 新增生产车间分布与环境情况
export function addWorkshopEnvironment(data) {
  return request({
    url: '/admin/workshopEnvironment',
    method: 'post',
    data: data
  })
}

// 修改生产车间分布与环境情况
export function updateWorkshopEnvironment(data) {
  return request({
    url: '/admin/workshopEnvironment',
    method: 'put',
    data: data
  })
}

// 删除生产车间分布与环境情况
export function delWorkshopEnvironment(id) {
  return request({
    url: '/admin/workshopEnvironment/' + id,
    method: 'delete'
  })
}
