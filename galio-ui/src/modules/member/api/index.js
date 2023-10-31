import { defAxios as request } from '@/utils'

export function getMembers(data = {}) {
  return request({
    url: '/members',
    method: 'get',
    data,
  })
}

export function getMember(id) {
  if (id) {
    return request({
      url: `/member/${id}`,
      method: 'get',
    })
  }
  return request({
    url: '/member',
    method: 'get',
  })
}

export function saveMember(data = {}, id) {
  if (id) {
    return request({
      url: '/member',
      method: 'put',
      data,
    })
  }

  return request({
    url: `/member/${id}`,
    method: 'put',
    data,
  })
}
