import { createRouter, createWebHistory } from 'vue-router'
import { basicRoutes, EMPTY_ROUTE, NOT_FOUND_ROUTE } from './routes'
import { setupRouterGuard } from './guard'
import { getToken, isNullOrWhitespace } from '@/utils'
import { useMemberStore, useRouterStore } from '@/store'

export const router = createRouter({
  history: createWebHistory('/'),
  routes: basicRoutes,
  scrollBehavior: () => ({ left: 0, top: 0 }),
})

/**
 * 注册router组件
 * @param app
 */
export async function setupRouter(app) {
  await initAuthRoute()
  setupRouterGuard(router)
  app.use(router)
}

/**
 * 重置路由
 */
export async function resetRouter() {
  const basicRouteNames = getRouteNames(basicRoutes)
  router.getRoutes().forEach((route) => {
    const name = route.name
    if (!basicRouteNames.includes(name)) {
      router.removeRoute(name)
    }
  })
}

/**
 * 初始化路由信息
 * @returns
 */
export async function initAuthRoute() {
  const token = getToken()

  // 没有token情况
  if (isNullOrWhitespace(token)) {
    router.addRoute(EMPTY_ROUTE)
    return
  }

  // 有token的情况
  try {
    const memberStore = useMemberStore()
    const routerStore = useRouterStore()
    !memberStore.memberId && (await memberStore.getMemberInfo())
    const accessRoutes = await routerStore.generateRoutes()
    accessRoutes.forEach((route) => {
      !router.hasRoute(route.name) && router.addRoute(route)
    })
    router.hasRoute(EMPTY_ROUTE.name) && router.removeRoute(EMPTY_ROUTE.name)
    router.addRoute(NOT_FOUND_ROUTE)
  } catch (error) {
    console.error(error)
  }
}

export function getRouteNames(routes) {
  return routes.map((route) => getRouteName(route)).flat(1)
}

function getRouteName(route) {
  const names = [route.name]
  if (route.children && route.children.length) {
    names.push(...route.children.map((item) => getRouteName(item)).flat(1))
  }
  return names
}
