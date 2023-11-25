import { useMemberStore } from '@/store'

const WITHOUT_TOKEN_API = [{ url: 'auth/login', method: 'POST' },{ url: 'auth/getPublicKey', method: 'GET' }]

export function isWithoutToken({ url, method = '' }) {
  let prefixFlag = url.indexOf('/') === 0;
  url = prefixFlag ? url.substr(0) : url
  return WITHOUT_TOKEN_API.some((item) => item.url === url && item.method === method.toUpperCase())
}

export function addBaseParams(params) {
  if (!params.memberId) {
    params.memberId = useMemberStore().memberId
  }
}

export function resolveResError(code, message) {
  switch (code) {
    case 400:
      message = message ?? '请求参数错误'
      break
    case 401:
      message = message ?? '登录已过期'
      useMemberStore().logout()
      break
    case 403:
      message = message ?? '没有权限'
      break
    case 404:
      message = message ?? '资源或接口不存在'
      break
    case 500:
      message = message ?? '服务器异常'
      break
    default:
      message = message ?? `【${code}】: 未知异常!`
      break
  }
  return message
}
