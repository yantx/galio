import { isWithoutToken, resolveResError } from './helpers'
import { isNullOrUndef, isString, getToken, toLogin } from '@/utils'

export function reqResolve(config) {
  // 防止缓存，给get请求加上时间戳
  if (config.method === 'get') {
    config.params = { ...config.params, t: new Date().getTime() }
  }
  // 处理不需要token的请求
  if (isWithoutToken(config)) {
    return config
  }

  const token = getToken()
  if (!token) {
    /**
     * * 未登录或者token过期的情况下
     * * 跳转登录页重新登录，携带当前路由及参数，登录成功会回到原来的页面
     */
    toLogin()
    return Promise.reject({ status: -1, msg: '未登录' })
  }

  /**
   * * jwt token
   * ! 认证方案: Bearer
   */
  config.headers.Authorization = config.headers.Authorization || 'Bearer ' + token

  return config
}

export function reqReject(error) {
  return Promise.reject(error)
}

export function resResolve(response) {
  return response?.data
}

export function resReject(error) {
  let code = error?.response?.status || error.status
  let msg = error.msg || error.message || error.response?.statusText
  if (isNullOrUndef(code)) {
    // 未知错误
    code = -1
    msg = msg || '接口异常！'
    return Promise.resolve({ code, msg, error })
  } else if (Math.floor(code / 100) !== 2) {
    msg = resolveResError(code, msg)
    return Promise.resolve({ code, msg, error })
  } else {
    let url = error.config.url
    let res = error.response.data
    if (res instanceof Blob || res + '' === '[object Blob]') {
      // 若是文件流，则直接返回
      return res
    } else {
      if (!res.meta) {
        return res
      }
      if (20000 === code) {
        return res instanceof Blob || res + '' === '[object Blob]' ? res : res.data
      } else {
        let error = url + '后台服务异常'
        const title = res.meta?.msg || res.data
        if (title && isString(title)) {
          error += '信息:' + title
        }
        return Promise.reject(new Error(title))
      }
    }
  }
}
