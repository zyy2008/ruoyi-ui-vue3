import request from '@/utils/request'

// 查询政策文件列表
export function listPolicyDocuments(query) {
  return request({
    url: '/admin/policyDocuments/list',
    method: 'get',
    params: query
  })
}

// 查询政策文件详细
export function getPolicyDocuments(id) {
  return request({
    url: '/admin/policyDocuments/' + id,
    method: 'get'
  })
}

// 新增政策文件
export function addPolicyDocuments(data) {
  return request({
    url: '/admin/policyDocuments',
    method: 'post',
    data: data
  })
}

// 修改政策文件
export function updatePolicyDocuments(data) {
  return request({
    url: '/admin/policyDocuments',
    method: 'put',
    data: data
  })
}

// 删除政策文件
export function delPolicyDocuments(id) {
  return request({
    url: '/admin/policyDocuments/' + id,
    method: 'delete'
  })
}


export function download(path ) {
  return request({
    url: '/file/download',
    method: 'get',
    params: { path: path },
  })
}
