import request from '@/utils/request'

// 查询地图标注列表
export function listLabel(query) {
  return request({
    url: '/admin/label/list',
    method: 'get',
    params: query
  })
}

// 查询地图标注详细
export function getLabel(id) {
  return request({
    url: '/admin/label/' + id,
    method: 'get'
  })
}

// 新增地图标注
export function addLabel(data) {
  return request({
    url: '/admin/label',
    method: 'post',
    data: data
  })
}

// 修改地图标注
export function updateLabel(data) {
  return request({
    url: '/admin/label',
    method: 'put',
    data: data
  })
}

// 删除地图标注
export function delLabel(id) {
  return request({
    url: '/admin/label/' + id,
    method: 'delete'
  })
}


//分享地图标注
export function shareLabel(data) {
  return request({
    url: '/admin/label/share',
    method: 'post',
    data: data
  })
}

