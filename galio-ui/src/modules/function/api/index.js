import { request } from '@/utils/request'

export function getFunctions(data = {}) {
  return request.get('/system/function/tree', data)
}

export function getFunction(id) {
  if (id) {
    return request.get(`/system/function/${id}`)
  }
  return request.get('/system/function')
}

export function saveFunction(data = {}, id) {
  if (id) {
    return request.put('/system/function', data)
  }

  return request.put(`/system/function/${id}`, data)
}
