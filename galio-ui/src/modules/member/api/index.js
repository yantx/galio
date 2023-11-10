import { mockRequest } from '@/utils'

export function getMembers(data = {}) {
  return mockRequest.get('/members', data)
}

export function getMember(id) {
  if (id) {
    return mockRequest.get(`/member/${id}`)
  }
  return mockRequest.get('/member')
}

export function saveMember(data = {}, id) {
  if (id) {
    return mockRequest.put('/member', data)
  }

  return mockRequest.put(`/member/${id}`, data)
}
