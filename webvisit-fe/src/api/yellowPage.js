import request from '@/utils/request'

export function getCompanyInfo() {
  return request({
    url: '/yellowPage/query',
    method: 'get'
  })
}

export function editCompanyInfo(data) {
  return request({
    url: '/yellowPage/update',
    method: 'post',
    data: data
  })
}
