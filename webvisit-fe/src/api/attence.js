import request from '@/utils/request'

export function getRegulationList() {
  return request({
    url: '/attence/regulation/query',
    method: 'get'
  })
}
