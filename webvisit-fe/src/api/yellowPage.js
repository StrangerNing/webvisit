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

export const uploadApi = {
  saveImg: process.env.VUE_APP_BASE_API + '/yellowPage/img/save'
}

export function uploadImg(data) {
  return request({
    url: process.env.VUE_APP_BASE_API + '/yellowPage/img/save',
    method: 'post',
    data: data
  })
}

export function getCompanyProduct() {
  return request({
    url: '/yellowPage/product/query',
    method: 'get'
  })
}

export function addCompanyProduct(data) {
  return request({
    url: '/yellowPage/product/add',
    method: 'post',
    data: data
  })
}

export function editCompanyProduct(data) {
  return request({
    url: '/yellowPage/product/update',
    method: 'post',
    data: data
  })
}

export function deleteCompanyProduct(param) {
  return request({
    url: '/yellowPage/product/delete',
    method: 'post',
    params: param
  })
}

export function addProductImg(data) {
  return request({
    url: '/yellowPage/product/img/add',
    method: 'post',
    data: data
  })
}

export function updateProductImg(data) {
  return request({
    url: '/yellowPage/product/img/update',
    method: 'post',
    data: data
  })
}

export function deleteProductImg(param) {
  return request({
    url: '/yellowPage/product/img/delete',
    method: 'post',
    params: param
  })
}
