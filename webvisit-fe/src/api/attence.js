import request from '@/utils/request'

export function getRegulationList() {
  return request({
    url: '/attence/regulation/query',
    method: 'get'
  })
}

export function getHolidayList(param) {
  return request({
    url: '/attence/holiday/query',
    method: 'get',
    params: param
  })
}
