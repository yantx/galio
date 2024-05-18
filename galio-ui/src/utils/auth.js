import { router } from '@/router'
import { useMemberStore } from '@/store'
import { isNullOrWhitespace } from '.'

export function toLogin() {
  const currentRoute = unref(router.currentRoute)
  const needRedirect = !currentRoute.meta?.requireAuth && !['/404', '/login'].includes(currentRoute.value?.path)
  router.replace({
    path: '/login',
    query: needRedirect ? { ...currentRoute.query, redirect: currentRoute.path } : {},
  })
}
/**
 * 按钮鉴权函数
 * @param {String} permission - 当前按钮权限
 * @returns Boolean true-有权限 false-无权限
 */
export function hasBtnPermission(permission) {
  const currentRoute = unref(router.currentRoute)
  let btnPermissions = currentRoute.meta.functionPerms || useMemberStore().functionPerms
  return (
    !isNullOrWhitespace(btnPermissions) && (btnPermissions.includes('*:*:*') || btnPermissions.includes(permission))
  )
}
