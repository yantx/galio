import { request } from '@/utils'

export function getMembers(data = {}) {
  return request.get('/system/members', data)
}

export function getMember(id) {
  if (id) {
    return request.get(`/system/member/${id}`)
  }
  return request.get('/system/member/personal/info')
}

export function saveMember(data = {}, id) {
  if (id) {
    return request.put('/system/member', data)
  }

  return request.put(`/system/member/${id}`, data)
}
