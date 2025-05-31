import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listMonitoring(query) {
    return request({
        url: '/system/monitoring/list',
        method: 'get',
        params: query
    })
}

// 查询【请填写功能名称】详细
export function getMonitoring(pointId) {
    return request({
        url: '/system/monitoring/' + pointId,
        method: 'get'
    })
}

// 新增【请填写功能名称】
export function addMonitoring(data) {
    return request({
        url: '/system/monitoring',
        method: 'post',
        data: data
    })
}

// 修改【请填写功能名称】
export function updateMonitoring(data) {
    return request({
        url: '/system/monitoring',
        method: 'put',
        data: data
    })
}

// 删除【请填写功能名称】
export function delMonitoring(pointId) {
    return request({
        url: '/system/monitoring/' + pointId,
        method: 'delete'
    })
}
