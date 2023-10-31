import { router } from '@/router'
import { useMemberStore } from '@/store'
import { isNullOrWhitespace } from '.'

export function toLogin() {
  const currentRoute = unref(router.currentRoute)
  const needRedirect = !currentRoute.meta.requireAuth && !['/404', '/login'].includes(router.currentRoute.value.path)
  router.replace({
    path: '/login',
    query: needRedirect ? { ...currentRoute.query, redirect: currentRoute.path } : {},
  })
}

export function hasBtnPermission(permission, btnPermissions) {
  if (isNullOrWhitespace(btnPermissions)) {
    btnPermissions = useMemberStore().functionPerms
  }
  return btnPermissions.includes(permission)
}
