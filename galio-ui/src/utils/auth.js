import { router } from '@/router'
import { useMemberStore } from '@/store'
import { isNullOrWhitespace } from '.'

export function toLogin() {
  const currentRoute = unref(router.currentRoute)
  const needRedirect = !currentRoute.meta.requireAuth && !['/404', '/login'].includes(currentRoute.value.path)
  router.replace({
    path: '/login',
    query: needRedirect ? { ...currentRoute.query, redirect: currentRoute.path } : {},
  })
}
/**
 * 按钮鉴权函数
 * @param {String} permission - 当前按钮权限
 * @param {Array} btnPermissions - 用户所有按钮权限集
 * @returns Boolean true-有权限 false-无权限
 */
export function hasBtnPermission(permission, btnPermissions) {
  if (isNullOrWhitespace(btnPermissions)) {
    btnPermissions = useMemberStore().functionPerms
  }
  return btnPermissions.includes(permission)
}
