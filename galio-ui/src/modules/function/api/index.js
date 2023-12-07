import { request } from '@/utils'

export function getFunctions(data = {}) {
  return request.get('/functions', data)
}

export function getFunction(id) {
  if (id) {
    return request.get(`/function/${id}`)
  }
  return request.get('/functions')
}

export function saveFunction(data = {}, id) {
  if (id) {
    return request.put('/function', data)
  }

  return request.put(`/function/${id}`, data)
}
