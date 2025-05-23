

import request from '@/utils/request'

//裁剪
export function getShortestPath(data) {
    return request({
        url: '/admin/set/getShortestPath',
        method: 'post',
        data: data
    })
}