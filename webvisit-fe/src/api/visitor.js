import request from '@/utils/request'

export function queryVisitors(data) {
  return request({
    url: '/visitor/query',
    method: 'get',
    params: data
  })
}

export function deleteVisitor(data) {
  return request({
    url: '/visitor/delete',
    method: 'get',
    params: data
  })
}
