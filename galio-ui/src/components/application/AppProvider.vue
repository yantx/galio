<template>
  <n-config-provider
    wh-full
    :locale="zhCN"
    :date-locale="dateZhCN"
    :theme="appStore.isDark ? darkTheme : undefined"
    :theme-overrides="appStore.themeOverrides"
  >
    <slot />
  </n-config-provider>
</template>

<script setup>
import { useCssVar } from '@vueuse/core'
import { kebabCase } from 'lodash-es'
import { zhCN, dateZhCN, darkTheme } from 'naive-ui'
import { useAppStore } from '@/store'
import { lStorage } from '@/utils'
const appStore = useAppStore()
// function setupCssVar() {
//   const naiveThemeOverrides = appStore.themeOverrides()
//   const common = naiveThemeOverrides.common
//   for (const key in common) {
//     useCssVar(`--${kebabCase(key)}`, document.documentElement).value = common[key] || ''
//     if (key === 'primaryColor') window.localStorage.setItem('__THEME_COLOR__', common[key] || '')
//   }
// }
// setupCssVar()
window.addEventListener('beforeunload', function () {
  lStorage.clear()
})
</script>
