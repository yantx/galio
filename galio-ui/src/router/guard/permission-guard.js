import { getToken, refreshAccessToken, isNullOrWhitespace } from '@/utils'

const WHITE_LIST = ['/login', '/404']
export function createPermissionGuard(router) {
  router.beforeEach((to, form) => {
    const token = getToken()
    /** 没有token的情况 */
    if (isNullOrWhitespace(token)) {
      if (WHITE_LIST.includes(to.path)) return true
      return { path: 'login', query: { ...to.query, redirect: to.path } }
    }
    /** 有token的情况 */
    if (to.path === '/login') return { name: import.meta.env.VITE_ROUTE_HOME_NAME }

    refreshAccessToken()
    return true
  })
}
