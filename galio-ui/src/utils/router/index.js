import { isDef, isNullOrUndef, isNullOrWhitespace } from '@/utils'
import { renderCustomIcon, renderIcon, isExternal } from '@/utils'
import { views } from '@/modules/view.js'

const Layout = () => import('@/layout/index.vue')
const IframePage = () => import('@/components/iframe/index.vue')
const authRouteMode = import.meta.env.VITE_AUTH_ROUTE_MODE
/**
 * 将权限路由转换成菜单
 * @param routes - 路由
 */
export function transformAuthRouteToMenu(routes = []) {
  return routes
    .filter((o) => !o.isHidden)
    .map((item) => doTransformMenu(item))
    .sort((a, b) => a.order - b.order)
}

/**
 * 将权限路由转换成vue路由
 * @param routes - 权限路由
 * @description
 */
export function transformAuthRouteToVueRoutes(routes = []) {
  const WHITE_LIST = ['/login', '/404']
  return routes
    .map((route) => (WHITE_LIST.includes(route.path) ? [route] : transformAuthRouteToVueRoute(route)))
    .flat(1)
}
function dynamicRouteFormat(item) {
  const resultRoute = []
  let itemRoute = {
    name: item.name,
    path: item.path.indexOf('/') !== 0 && item.functionType === '1' ? `/${item.path}` : item.path,
    isHidden: isNullOrUndef(item.visible) ? false : item.visible,
    children: item.children,
    singleLayout: item.singleLayout,
    meta: {
      title: item.title,
      icon: item.icon,
      order: item.order,
      keepAlive: Boolean(item.isCache),
    },
  }

  if (item.isFrame) {
    itemRoute.component = IframePage
    itemRoute.meta.externalUrl = item.externalUrl
  } else if (item.functionType === '1' && !isExternal(item.path)) {
    itemRoute.component = Layout
  } else if (!isExternal(item.path)) {
    itemRoute.component = getViewComponent(item.name)
  }
  return itemRoute
}
/**
 * 将单个权限路由转换成vue路由
 * @param item - 单个权限路由
 */
function transformAuthRouteToVueRoute(item) {
  const resultRoute = []
  item = authRouteMode === 'dynamic' ? dynamicRouteFormat(item) : item
  // 注意：单独路由没有children,且为一级节点
  if (isSingleRoute(item)) {
    // 单独路由增加目录
    const parentPath = isHomeRoute(item.name) ? '/' : `/${item.path}-parent`
    const parentRoute = {
      name: `${item.name}Parent`,
      path: parentPath,
      component: Layout,
      redirect: isHomeRoute(item.name) ? item.path : parentPath + '/' + item.path,
      children: [item],
    }
    return [parentRoute]
  }

  // 子路由
  if (hasChildren(item)) {
    const children = item.children
      .map((child) => {
        child.singleLayout = false
        return transformAuthRouteToVueRoute(child)
      })
      .flat()
    if (isNullOrUndef(item.redirect)) {
      // 找出第一个不为多级路由中间级的子路由路径作为重定向路径
      const tmpRoute = children.find((v) => !hasChildren(v))
      let redirectPath
      if (isDef(tmpRoute)) {
        redirectPath = isExternal(tmpRoute.path) ? tmpRoute.path : item.path + '/' + tmpRoute.path
      } else {
        redirectPath = isExternal(children[0].redirect) ? children[0].redirect : item.path + '/' + children[0].redirect
      }
      item.redirect = redirectPath
    }
    item.children = children
  }
  resultRoute.push(item)
  return resultRoute
}

/**
 * 格式化路由地址
 * @param {*} basePath
 * @param {*} path
 * @returns
 */
function resolvePath(basePath, path) {
  if (isExternal(path)) return path
  return (
    '/' +
    [basePath, path]
      .filter((path) => !!path && path !== '/')
      .map((path) => path.replace(/(^\/)|(\/$)/g, ''))
      .join('/')
  )
}

function doTransformMenu(route, basePath = '') {
  let menuItem = {
    label: (route.meta && route.meta.title) || route.name,
    key: route.name,
    path: resolvePath(basePath, route.path),
    icon: getIcon(route.meta),
    order: route.meta?.order || 0,
  }
  const visibleChildren = route.children ? route.children.filter((item) => item.name && !item.isHidden) : []

  if (!visibleChildren.length) return menuItem

  if (visibleChildren.length === 1) {
    // 单个子路由处理
    const singleRoute = visibleChildren[0]
    menuItem = {
      ...menuItem,
      label: singleRoute.meta?.title || singleRoute.name,
      key: singleRoute.name,
      path: resolvePath(menuItem.path, singleRoute.path),
      icon: getIcon(singleRoute.meta),
    }
    const visibleItems = singleRoute.children ? singleRoute.children.filter((item) => item.name && !item.isHidden) : []

    if (visibleItems.length === 1) {
      menuItem = doTransformMenu(visibleItems[0], menuItem.path)
    } else if (visibleItems.length > 1) {
      menuItem.children = visibleItems
        .map((item) => doTransformMenu(item, menuItem.path))
        .sort((a, b) => a.order - b.order)
    }
  } else {
    menuItem.children = visibleChildren
      .map((item) => doTransformMenu(item, menuItem.path))
      .sort((a, b) => a.order - b.order)
  }
  return menuItem
}

function getIcon(meta) {
  if (meta?.customIcon) return renderCustomIcon(meta.customIcon, { size: 18 })
  if (meta?.icon) return renderIcon(meta.icon, { size: 18 })
  return null
}
/**
 * 是否有子路由
 * @param item - 权限路由
 */
function hasChildren(item) {
  return Boolean(item.children && item.children.length)
}

/**
 * 是否是单层级路由
 * @param itemRoute - 权限路由
 */
function isSingleRoute(itemRoute) {
  // singleLayout 可以指定layout模版类型
  // Boolean(item.singleLayout);
  return !hasChildren(itemRoute) && itemRoute.singleLayout !== false
}

/** 路由不转换菜单 */
function hideInMenu(route) {
  return Boolean(route.isHidden)
}

/**
 * 是否是单层级路由
 * @param routeName - 路由名称 route.name属性
 */
function isHomeRoute(routeName) {
  return routeName === import.meta.env.VITE_ROUTE_HOME_NAME
}

/**
 * 获取页面导入的vue文件
 * @param routeKey - 路由key
 */
function getViewComponent(routeKey) {
  if (!views[routeKey]) {
    throw new Error(`路由“${routeKey}”没有对应的组件文件！`)
  }
  return views[routeKey]
}
