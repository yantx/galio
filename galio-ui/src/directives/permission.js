import { hasBtnPermission } from '@/utils'

export default function setupPermissionDirective(app) {
  function updateElVisible(el, param) {
    if (!param.perm) {
      throw new Error(`need roles: like v-permission="'member.add'"`)
    }
    if (!hasBtnPermission(param.perm, param.perms)) {
      el.parentElement?.removeChild(el)
    }
  }

  const permissionDirective = {
    mounted(el, binding) {
      updateElVisible(el, binding.value)
    },
    beforeUpdate(el, binding) {
      updateElVisible(el, binding.value)
    },
  }

  app.directive('permission', permissionDirective)
}
