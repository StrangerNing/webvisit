import request from '@/utils/request'

export function getLogList() {
  return request({
    url: '/log/query',
    method: 'get'
  })
}
