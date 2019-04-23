import request from '@/utils/request'

export function getLogList(param) {
  return request({
    url: '/log/query',
    method: 'get',
    params: param
  })
}
