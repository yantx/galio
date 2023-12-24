import { defineStore } from 'pinia'
import { useDark } from '@vueuse/core'
import themeSetting from '@/settings/themeSetting'

const { getThemeOverrides, themeConfigDefault, header, tags, sider } = themeSetting
const isDark = useDark()
/** 主题配置 */
export const useAppStore = defineStore('app', {
  state() {
    return {
      collapsed: false,
      isDark,
      headerVisible: header.visible,
      tagsVisible: tags.visible,
      siderVisible: sider.visible,
      onlyMainPageVisible: false,
      themeConfig: themeConfigDefault,
      navMode: 'vertical',
    }
  },
  getters: {
    themeOverrides() {
      return getThemeOverrides(this.themeConfig, isDark)
    },
  },
  actions: {
    switchCollapsed() {
      this.collapsed = !this.collapsed
    },
    setCollapsed(collapsed) {
      this.collapsed = collapsed
    },
    setOnlyMainPageVisible(isVisible) {
      this.onlyMainPageVisible = isVisible
      this.siderVisible = !isVisible
      this.headerVisible = !isVisible
      this.tagsVisible = !isVisible
    },
    /** 设置暗黑模式 */
    setDark(isDark) {
      this.isDark = isDark
    },
    /** 切换/关闭 暗黑模式 */
    toggleDark() {
      this.isDark = !this.isDark
    },
    /** 手动设置主题 */
    setThemeConfig(config) {
      themeConfig = {
        ...themeConfig,
        ...config,
      }
    },
  },
})
