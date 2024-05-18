import { defineStore } from 'pinia'
import { getFunctions } from '~/src/modules/function/api'
import { useMemberStore } from '@/store'
import { transformAuthRouteToVueRoutes, transformAuthRouteToMenu, filterAsyncRoutes } from '@/utils'
import { asyncRoutes, basicRoutes } from '@/router/routes'

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
    },
    /** 初始化动态路由 */
    async initDynamicRoute() {
      const res = await getFunctions()
      if (res.code === 20000) {
        const functionTree = res.data
        return this.handleAuthRoute(functionTree)
      } else {
        $message.error(res.msg)
        return {}
      }
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
      this.accessRoutes = vueRoutes
      return vueRoutes
    },
    resetRoute() {
      this.$reset()
    },
  },
})
