import { hasBtnPermission } from '@/utils'

export default function setupPermissionDirective(app) {
  function updateElVisible(el, permission) {
    if (!permission) {
      throw new Error(`need roles: like v-permission="'*:*:*'"`)
    }
    if (!hasBtnPermission(permission)) {
      el.parentElement?.removeChild(el)
    }
  }

  const permissionDirective = {
    mounted(el, binding) {
      updateElVisible(el, binding.value)
    },
  }

  app.directive('permission', permissionDirective)
}
