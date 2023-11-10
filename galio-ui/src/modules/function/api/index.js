import { mockRequest } from '@/utils'

export function getFunctions(data = {}) {
  return mockRequest.get('/functions', data)
}

export function getFunction(id) {
  if (id) {
    return mockRequest.get(`/function/${id}`)
  }
  return mockRequest.get('/functions')
}

export function saveFunction(data = {}, id) {
  if (id) {
    return mockRequest.put('/function', data)
  }

  return mockRequest.put(`/function/${id}`, data)
}
