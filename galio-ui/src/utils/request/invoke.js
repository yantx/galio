import axios from 'axios'
import { resResolve, resReject, reqResolve, reqReject } from './interceptors'

export function createAxios(defaultConfig) {
  const service = axios.create({
    ...defaultConfig,
  })
  service.interceptors.request.use(reqResolve, reqReject)
  service.interceptors.response.use(resResolve, resReject)

  /**
   * 异步promise请求
   * @param option - 请求参数
   * - url: 请求地址
   * - method: 请求方法(默认get)
   * - data: 请求的body的data
   * - defaultConfig: axios配置
   */
  async function asyncRequest(option) {
    const { method, url, data, axiosConfig } = option
    let resBody
    if (method === 'get' || method === 'delete') {
      resBody = await service[method](url, axiosConfig)
    } else {
      resBody = await service[method](url, data, axiosConfig)
    }
    return resBody
  }
  /**
   * get请求
   * @param url - 请求地址
   * @param config - axios配置
   */
  function get(url, config) {
    return asyncRequest({ url, method: 'get', axiosConfig: config })
  }

  /**
   * post请求
   * @param url - 请求地址
   * @param data - 请求的body的data
   * @param config - axios配置
   */
  function post(url, data, config) {
    return asyncRequest({ url, method: 'post', data, axiosConfig: config })
  }
  /**
   * put请求
   * @param url - 请求地址
   * @param data - 请求的body的data
   * @param config - axios配置
   */
  function put(url, data, config) {
    return asyncRequest({ url, method: 'put', data, axiosConfig: config })
  }

  /**
   * delete请求
   * @param url - 请求地址
   * @param config - axios配置
   */
  function handleDelete(url, config) {
    return asyncRequest({ url, method: 'delete', axiosConfig: config })
  }
  return {
    get,
    post,
    put,
    delete: handleDelete,
  }
}

export const defAxios = createAxios()
