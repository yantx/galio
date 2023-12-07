import { request } from '@/utils'

export const login = (data) => {
  return request.post('auth/login', data)
}

export const getPublicKey = () => {
  return request.get('auth/getPublicKey')
}
export const refreshToken = () => {
  return request.post('/auth/refreshToken')
}
