import { defineStore } from 'pinia'
import { getFunctions } from '@/api/function'
import { useMemberStore } from '@/store'
import { transformAuthRouteToVueRoutes, transformAuthRouteToMenu } from '@/utils'
import { asyncRoutes, basicRoutes } from '@/router/routes'

function hasPermission(route, role) {
  // * 不需要权限直接返回true
  if (!route.meta?.requireAuth) return true

  // * 登录用户没有角色或者路由没有设置角色判定为没有权限
  const routeRole = route.meta?.role ? route.meta.role : []
  if (!role.length || !routeRole.length) return false

  // * 路由指定的角色包含任一登录用户角色则判定有权限
  return role.some((item) => routeRole.includes(item))
}

function filterAsyncRoutes(routes = [], role) {
  const ret = []
  routes.forEach((route) => {
    if (hasPermission(route, role)) {
      const curRoute = {
        ...route,
        children: [],
      }
      if (route.children && route.children.length) {
        curRoute.children = filterAsyncRoutes(route.children, role)
      } else {
        Reflect.deleteProperty(curRoute, 'children')
      }
      ret.push(curRoute)
    }
  })
  return ret
}

export const useRouterStore = defineStore('route-store', {
  state() {
    return {
      accessRoutes: [],
      accessMenus: [],
      authRouteMode: import.meta.env.VITE_AUTH_ROUTE_MODE,
    }
  },
  getters: {
    routes() {
      return basicRoutes.concat(this.accessRoutes)
    },
    menus() {
      return transformAuthRouteToMenu(this.accessRoutes)
    },
  },
  actions: {
    generateRoutes() {
      if (this.authRouteMode === 'dynamic') {
        return this.initDynamicRoute()
      } else {
        return this.initStaticRoute()
      }
      // return this.accessRoutes
    },
    /** 初始化动态路由 */
    initDynamicRoute() {
      const memberStore = useMemberStore()
      return this.handleAuthRoute(memberStore.menus)
    },
    /** 初始化静态路由 */
    initStaticRoute() {
      const memberStore = useMemberStore()
      const staticRoutes = filterAsyncRoutes(asyncRoutes, memberStore.role)
      return this.handleAuthRoute(staticRoutes)
    },
    /**
     * 处理权限路由
     * @param routes - 权限路由
     */
    handleAuthRoute(routes = []) {
      const vueRoutes = transformAuthRouteToVueRoutes(routes)
      console.log(vueRoutes)
      this.accessRoutes = vueRoutes
      return vueRoutes
    },
    resetRoute() {
      this.$reset()
    },
  },
})
