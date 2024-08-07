import { createAxios } from './invoke'

// 根据base地址判断是否走mock
let defaultConfig = {
  baseURL: import.meta.env.VITE_USE_MOCK == 'true' ? '/mock' : import.meta.env.VITE_APP_BASE_API,
  timeout: import.meta.env.VITE_APP_API_TIMEOUT || 12000,
}

export const request = createAxios(defaultConfig)

export const mockRequest = createAxios({ baseURL: '/mock', timeout: defaultConfig.timeout })
