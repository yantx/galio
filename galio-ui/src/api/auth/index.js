import { request, mockRequest } from '@/utils'

export const login = (data) => {
  return request.post('/auth/login', data)
}

export const refreshToken = () => {
  return mockRequest.post('/auth/refreshToken')
}
