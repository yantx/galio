import { request } from '@/utils'
export function getDictData(params) {
  return request.post('/system/dict/list', { params })
}

export function getDictPage(params) {
  return request.get('/system/dict/page', { params })
}

export function getDictDetail(id) {
  return request.get(`/system/dict/${id}`)
}

export function addDict(data) {
  return request.post('/system/dict', data)
}

export function updateDict(data) {
  return request.put(`/system/dict/${data.id}`, data)
}

export function deleteDict(id) {
  return request.delete(`/system/dict/${id}`)
}
