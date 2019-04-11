import request from '@/utils/request'

export function getRegulationList() {
  return request({
    url: '/attence/regulation/query',
    method: 'get'
  })
}

export function editRegulation(param) {
  return request({
    url: 'attence/regulation/update',
    method: 'post',
    body: param
  })
}

export function deleteRegulation(id) {
  return request({
    url: '/attence/regulation/delete',
    method: 'post',
    params: id
  })
}

export function getHolidayList(param) {
  return request({
    url: '/attence/holiday/query',
    method: 'get',
    params: param
  })
}

export function cancelHoliday(param) {
  return request({
    url: 'attence/holiday/cancel',
    method: 'post',
    params: param
  })
}

export function setHoliday(param) {
  return request({
    url: 'attence/holiday/new',
    method: 'post',
    params: param
  })
}
