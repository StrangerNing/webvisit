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

export function setWorkday(data) {
  return request({
    url: '/attence/workday/update',
    method: 'post',
    data: data
  })
}

export function editRegulation(data) {
  return request({
    url: 'attence/regulation/update',
    method: 'post',
    data: data
  })
}

export function deleteRegulation(id) {
  return request({
    url: '/attence/regulation/delete',
    method: 'post',
    params: id
  })
}

export function addRegulation(data) {
  return request({
    url: '/attence/regulation/new',
    method: 'post',
    data: data
  })
}

export function getHolidayList(param) {
  return request({
    url: '/attence/holiday/query',
    method: 'get',
    params: param
  })
}

export function cancelHoliday(data) {
  return request({
    url: '/attence/holiday/cancel',
    method: 'post',
    params: data
  })
}

export function setHoliday(data) {
  return request({
    url: '/attence/holiday/new',
    method: 'post',
    params: data
  })
}

export function getLeaveList() {
  return request({
    url: '/attence/leave/query',
    method: 'get'
  })
}

export function addLeave(data) {
  return request({
    url: '/attence/leave/add',
    method: 'post',
    data: data
  })
}

export function editLeave(data) {
  return request({
    url: '/attence/leave/update',
    method: 'post',
    data: data
  })
}

export function deleteLeave(id) {
  return request({
    url: '/attence/leave/delete',
    method: 'post',
    params: id
  })
}

export function addAnnual(data) {
  return request({
    url: '/attence/annual/add',
    method: 'post',
    data: data
  })
}

export function deleteAnnual(id) {
  return request({
    url: '/attence/annual/delete',
    method: 'post',
    params: id
  })
}

export function updateAnnual(param) {
  return request({
    url: '/attence/annual/update',
    method: 'post',
    data: param
  })
}

export function queryAnnual() {
  return request({
    url: '/attence/annual/query',
    method: 'get'
  })
}

export function queryAnnualStep(id) {
  return request({
    url: '/attence/annual/step/query',
    method: 'get',
    params: id
  })
}

export function addAnnualStep(data) {
  return request({
    url: '/attence/annual/step/add',
    method: 'post',
    data: data
  })
}

export function deleteAnnualStep(id) {
  return request({
    url: '/attence/annual/step/delete',
    method: 'post',
    params: id
  })
}

export function updateAnnualStep(data) {
  return request({
    url: '/attence/annual/step/update',
    method: 'post',
    data: data
  })
}

export function queryAnnualReport() {
  return request({
    url: '/attence/annual/report/query',
    method: 'get'
  })
}

export function updateAnnualReport(data) {
  return request({
    url: '/attence/annual/report/update',
    method: 'post',
    data: data
  })
}

export function getAllPunchReport(param) {
  return request({
    url: '/attence/report/all/query',
    method: 'get',
    params: param
  })
}

export function getPunchDetailReport(data) {
  return request({
    url: '/attence/report/detail/query',
    method: 'post',
    data: data
  })
}

export function exportPunchDetail(param) {
  return request({
    url: '/export/punch/detail',
    method: 'get',
    params: param
  })
}

export function getDownloadUrl(param) {
  return request({
    url: '/export/download',
    method: 'get',
    params: param
  })
}

export function getDeptList(param) {
  return request({
    url: '/attence/dept/query',
    method: 'get',
    params: param
  })
}
