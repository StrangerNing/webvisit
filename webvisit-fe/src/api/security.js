import request from '@/utils/request'

export function getLogList(param) {
  return request({
    url: '/log/query',
    method: 'get',
    params: param
  })
}

export function exportLog(param) {
  return request({
    url: '/export/security/log',
    method: 'get',
    params: param
  })
}
