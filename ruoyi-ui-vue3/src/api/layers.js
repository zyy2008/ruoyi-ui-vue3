import request from '@/utils/request'

//图层分类树
export function classificationTree(data) {
    return request({
        url: '/admin/classification/layerTree',
        method: 'get',
        params: data
    })
}