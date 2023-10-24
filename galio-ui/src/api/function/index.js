import { defAxios as request } from '@/utils'

export function getFunctions(data = {}) {
  return request({
    url: '/functions',
    method: 'get',
    data,
  })
}

export function getFunction(id) {
  if (id) {
    return request({
      url: `/function/${id}`,
      method: 'get',
    })
  }
  return request({
    url: '/function',
    method: 'get',
  })
}

export function saveFunction(data = {}, id) {
  if (id) {
    return request({
      url: '/function',
      method: 'put',
      data,
    })
  }

  return request({
    url: `/function/${id}`,
    method: 'put',
    data,
  })
}
