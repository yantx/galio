<template>
  <n-menu
    ref="menu"
    class="side-menu"
    :mode="props.menuMode"
    :indent="18"
    :collapsed-icon-size="22"
    :collapsed-width="64"
    :options="menuOptions"
    :value="activeKey"
    @update:value="handleMenuSelect"
  />
</template>

<script setup>
import { useRouterStore, useAppStore } from '@/store'
import { isExternal, isEmpty } from '@/utils'
import { cloneDeep } from 'lodash-es'

const props = defineProps({
  menuMode: {
    type: String,
    default: 'vertical',
  },
})
const responsive = ref(false)
const router = useRouter()
const curRoute = useRoute()
const routerStore = useRouterStore()
const appStore = useAppStore()

const activeKey = computed(() => curRoute.meta?.activeMenu || curRoute.name)

const menuOptions = computed(() => updateMenu())

const menu = ref(null)

watch(curRoute, async () => {
  await nextTick()
  menu.value?.showOption()
})
/**
 * 监听菜单配置变化 混合菜单模式下左侧菜单无数据则折叠起来
 */
watch(menuOptions, () => {
  if (props.menuMode === 'vertical') {
    appStore.setCollapsed(isEmpty(menuOptions.value))
  }
})
function handleMenuSelect(key, item) {
  if (isExternal(item.path)) {
    window.open(item.path)
  } else {
    router.push(item.path)
  }
}
/**
 * Updates the menu based on the current navigation mode.
 *
 * @return {Array} The updated menu.
 */
function updateMenu() {
  if (appStore.navMode === 'mix') {
    //混合菜单
    const firstRouteName = curRoute?.matched[0].name || ''
    return generatorMenuMix(routerStore.menus, firstRouteName)
  } else {
    return routerStore.menus
  }
}
/**
 * Generates a new menu mix based on the provided menus and first route name.
 *
 * @param {Array} menus - The original menus array.
 * @param {string} firstRouteName - The name of the first route.
 * @return {Array} The generated menu mix.
 */
function generatorMenuMix(menus, firstRouteName) {
  const res = cloneDeep(menus)
  let result
  if (props.menuMode === 'horizontal') {
    result = res.map((element) => {
      delete element.children
      return element
    })
  } else {
    result = res.filter((element) => {
      return element.key === firstRouteName
    })
  }
  return result
}
</script>

<style lang="scss">
.side-menu:not(.n-menu--collapsed) {
  .n-menu-item-content {
    &::before {
      left: 5px;
      right: 5px;
    }
    &.n-menu-item-content--selected,
    &:hover {
      &::before {
        border-left: 4px solid var(--primary-color);
      }
    }
  }
}
</style>
