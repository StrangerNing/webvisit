import request from '@/utils/request'

export function getRegulationList() {
  return request({
    url: '/attence/regulation/query',
    method: 'get'
  })
}

export function getWorkdayList(id) {
  return request({
    url: '/attence/workday/query',
    method: 'get',
    params: id
  })
}

export function setWorkday(param) {
  return request({
    url: '/attence/workday/update',
    method: 'post',
    params: param
  })
}

export function editRegulation(param) {
  return request({
    url: 'attence/regulation/update',
    method: 'post',
    params: param
  })
}

export function deleteRegulation(id) {
  return request({
    url: '/attence/regulation/delete',
    method: 'post',
    params: id
  })
}

export function addRegulation(param) {
  return request({
    url: '/attence/regulation/new',
    method: 'post',
    params: param
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
