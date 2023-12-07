import { request } from '@/utils'

export function getMembers(data = {}) {
  return request.get('/members', data)
}

export function getMember(id) {
  if (id) {
    return request.get(`/member/${id}`)
  }
  return request.get('/member')
}

export function saveMember(data = {}, id) {
  if (id) {
    return request.put('/member', data)
  }

  return request.put(`/member/${id}`, data)
}
