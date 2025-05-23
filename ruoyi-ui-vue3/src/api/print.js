import request from '@/utils/request'

//裁剪
export function getDxfByTarget(data) {
    return request({
        url: '/admin/gdal/getDxfByTarget',
        method: 'post',
        data: data
    })
}